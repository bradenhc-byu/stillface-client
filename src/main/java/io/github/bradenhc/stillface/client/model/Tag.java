package io.github.bradenhc.stillface.client.model;

import com.googlecode.cqengine.attribute.Attribute;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import static com.googlecode.cqengine.query.QueryFactory.attribute;

/**
 * Tag
 * Represents the internal data structure that holds tag data from entries in the database.
 */
public class Tag {

    /*
     * These variables are for the JavaFX Table object in the GUI. The table cells are populated using a CellFactory,
     * and in order to do this we need to have special variable types
     */
    private SimpleIntegerProperty tagID = new SimpleIntegerProperty();
    private SimpleStringProperty tagValue = new SimpleStringProperty();

    /*
     * The following variables are defined for use with the CQEngine IndexedCollections. This allows us to
     * create extremely fast indexing capabilities and cache data in memory for use. Data is only cached if
     * the model.cache configuration option is set to 'true'
     */
    public static final Attribute<Tag, Integer> TAG_ID =
            attribute("tagID", Tag::getTagID);
    public static final Attribute<Tag, String> TAG_VALUE =
            attribute("tagValue", Tag::getTagValue);

    public Tag(String tagValue){
        this.setTagValue(tagValue);
    }

    public Tag(int tagID, String tagValue){
        this.tagID.set(tagID);
        this.setTagValue(tagValue);
    }

    public void setTagValue(String tagValue) {
        this.tagValue.set(tagValue);
    }

    public int getTagID() {
        return tagID.get();
    }

    public String getTagValue() {
        return tagValue.get();
    }

    @Override
    public String toString(){
        return this.getTagValue();
    }

    @Override
    public boolean equals(Object o){
        if(o == null || !o.getClass().equals(Tag.class)) return false;
        Tag t = (Tag)o;
        if(!t.getTagValue().equals(this.getTagValue())
                || t.getTagID() != this.getTagID()){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        return tagValue.get().hashCode();
    }
}
