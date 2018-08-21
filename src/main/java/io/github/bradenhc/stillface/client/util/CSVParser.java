package io.github.bradenhc.stillface.client.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import io.github.bradenhc.stillface.client.model.Code;
import io.github.bradenhc.stillface.client.model.CodeCount;
import io.github.bradenhc.stillface.client.model.DataPoint;

/**
 * Provides the ability to parse data from properly formatted CSV files into the data structures of the StillFaceModel.
 * This also provides the ability to write data to an external file as an entire video or as summary data.
 */
public class CSVParser {

    /* Grab an instance of the logger */
    private final static Logger logger =Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /* The delimiter used in reading and writing CSV files. Defaults to a comma */
    private String delimeter;

    public CSVParser(){
        this.delimeter = ",";
    }

    /**
     * Reads properly formatted data from a CSV file into DataPoint objects and creates a list of the data that
     * can be used to write to the database.
     *
     * @param filename The file to read
     * @param videoData The StillFaceVideoData object to populate with the data from the file
     *
     * @return True if successful, false otherwise
     */
    public List<DataPoint> parseFromCSV(String filename){

        logger.fine("Parsing data from " + filename + " into CodedVideoDataObject");
        
        List<DataPoint> videoData = new ArrayList<>();

        try{
            // Create a new buffered reader to read the CSV file
            BufferedReader br = new BufferedReader(new FileReader(filename));


            // Clear any existing data from the StillFaceVideoData object. Placing this line after creating the
            // buffered reader ensures that the provided CSV file exists
            videoData.clear();

            // Begin parsing from the CSV file
            String line;
            while((line = br.readLine()) != null){

                // Get the data from the line
                String[] data = line.split(this.delimeter);

                // Strip away the header contents from the CSV file (if it exists)
                try{
                    videoData.add(new DataPoint(0, Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]),
                            new Code(data[2]),
                            data[3]));
                }
                catch(NumberFormatException e){
                    logger.fine("Detected possible CSV header, skipping...");
                }

            }

            br.close();
        }
        catch(FileNotFoundException e){
            logger.severe("Could not read file, file not found: " + filename);
            return null;
        }
        catch(IOException e){
            logger.severe("Caught IOException: " + e.getMessage());
            return null;
        }

        logger.fine("Parsing complete");

        return videoData;
    }

    /**
     * Takes data from the provided StillFaceVideoData object and writes it to a CSV file.
     *
     * @param videoData The object containing information to write
     * @param filename The name of the file to write to
     * @return True if successful, false otherwise
     */
    public boolean serializeToCSV(List<DataPoint> videoData, String filename){

        logger.fine("Serialize StillFaceVideoData to CSV file: " + filename);

        try{

            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

            for(DataPoint stamp : videoData){
                StringBuilder sb = new StringBuilder();
                sb.append(stamp.getTime());
                sb.append(this.delimeter);
                sb.append(stamp.getDuration());
                sb.append(this.delimeter);
                sb.append(stamp.getCode().getName());
                sb.append(this.delimeter);
                sb.append(stamp.getComment());
                sb.append("\n");

                bw.write(sb.toString());
            }

            bw.close();
            logger.fine("Serialization complete");
            return true;
        }
        catch(FileNotFoundException e){
            logger.severe("Could not write to file, file not found: " + filename);
            return false;
        }
        catch(IOException e){
            logger.severe("Caught IOException: " + e.getMessage());
            return false;
        }
    }

    /**
     * Given a list of summary information from a set of data objects, this will write those to a CSV file with the code
     * delimiters provided used to separate the sections of information
     *
     * @param delim1 The first StillFaceCode object acting as a delimiter used in the summary data
     * @param delim2 The second StillFaceCode object acting as a delimiter used in the summary data
     * @param summary A list of the three summary lists to write to the file. These list contain CodeCount
     *                objects
     * @param filename The name of the file to write to
     * @return True if the serialization succeeds. False otherwise.
     */
    public boolean serializeSummaryToCSV(Code delim1, Code delim2,
                                                  List<List<CodeCount>> summary, String filename){
        logger.fine("Serializing maps to summary file...");
        if(summary.size() != 3){
            logger.warning("Incorrect number of summaries in list. Should be 3");
            return false;
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

            bw.write("Before " + delim1.getName() + " code name, count\n");
            for(CodeCount count : summary.get(0)) {
                bw.write(count.name + this.delimeter + count.count + "\n");
            }
            bw.write("After " + delim1.getName() + " code name, count\n");
            for(CodeCount count : summary.get(1)) {
                bw.write(count.name + this.delimeter + count.count + "\n");
            }
            bw.write("After " + delim2.getName() + " code name, count\n");
            for(CodeCount count : summary.get(2)) {
                bw.write(count.name + this.delimeter + count.count + "\n");
            }

            bw.close();
            logger.fine("Finished serializing summary data to file");
            return true;

        }
        catch(FileNotFoundException e){
            logger.severe("Could not write to file, file not found: " + filename);
            return false;
        }
        catch(IOException e){
            logger.severe("Caught IOException: " + e.getMessage());
            return false;
        }
    }
}

