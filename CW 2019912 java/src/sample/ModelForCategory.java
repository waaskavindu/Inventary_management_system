package sample;

import javafx.beans.property.SimpleStringProperty;

public class ModelForCategory{
    private SimpleStringProperty category;

    public ModelForCategory(String category) {

        this.category = new SimpleStringProperty(category);
    }

    public String getCategory() {

        return category.get();
    }

    public void setCategory(String category) {

        this.category = new SimpleStringProperty(category);
    }
}
