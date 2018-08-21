package io.github.bradenhc.stillface.client.tasks;

import javafx.concurrent.Task;

import java.io.File;
import java.util.logging.Logger;

import io.github.bradenhc.stillface.client.api.model.IModelController;
import io.github.bradenhc.stillface.client.api.tasks.IDMTask;
import io.github.bradenhc.stillface.client.model.ProfileInfo;

/**
 * Implementation of the IDMTask interface. Wraps the execution of importing new still face data from a CSV file
 * into the database. This task is executed on a separate thread from the GUI.
 */
public class ImportTask implements IDMTask {

    /* Grab an instance of the logger */
    private final static Logger logger =Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /* The file to import data from (CSV) */
    private File importFile;
    /* The import data object containing information about he new import entry */
    private ProfileInfo importData;
    /* The database access object used to modify the database */
    private IModelController mModelCtrl;
    /* Callback functionality provided by the developer */
    private StillFaceTaskCallback callback;

    public ImportTask(File importFile, ProfileInfo importData, StillFaceTaskCallback callback){
        this.importFile = importFile;
        this.importData = importData;
        this.dao = StillFaceDAO.generateFromConfig();
        this.callback = callback;
    }

    /**
     * Execute the task. Attempts to import new data into the database from a CSV file
     */
    @Override
    public void execute() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                onImportFromFile();
                return null;
            }

            @Override
            protected void succeeded() {
                callback.onSuccess();
                super.succeeded();
            }

            @Override
            protected void failed() {
                callback.onFail(this.getException());
                super.failed();
            }
        };
        new Thread(task).start();
    }

    /**
     * Where the import happens. Takes the data from a CSV file and populates a list of StillFaceData objects. Then
     * it will attempt to first write the import data to the database. After successfully inserting a new import
     * entry, it will use the generated key to populate the import id field of the StillFaceData objects and then
     * write them to the database. If there is an error encountered during the process, it will rollback the changes
     * to the state of the database before the attempt to import occurred.
     *
     * @throws Exception If an error occurs, this will trigger the failed() method in the thread to be called and the
     *                   developer's provided onFail() implementation to be called
     */
    private void onImportFromFile() throws Exception {
        logger.fine("Performing import task...");
        StillFaceVideoData videoData = new StillFaceVideoData();
        new StillFaceCSVParser().parseFromCSVIntoCodedVideoData(importFile.getAbsolutePath(), videoData);
        dao.lockConnection();
        int key = dao.insertImportData(importData);
        if(key > 0){
            logger.fine("Import entry created, importing data...");
            for(StillFaceData data : videoData.getData()){
                data.setImportID(key);
                int dKey = dao.insertCodeData(data);
                if(dKey < 0){
                    dao.cleanImportData(key);
                    throw new Exception("Import data failed while importing file");
                }
            }
            StillFaceModel.getInstance().refreshImportData();
            StillFaceModel.getInstance().refreshCodeData();
            StillFaceModel.getInstance().refreshCodes();
            logger.fine("Import task completed");
        }
        else{
            logger.severe("Unable to insert data into database");
            throw new Exception("Import failed while loading file information");
        }
        dao.unlockConnection();
    }
}
