package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class DeleteCategory {
    public static String CategoryNameCheck;
    public static String DBProductCategory;
    public static String CategoryNametoDelete;
    @FXML
    TextField txtDelete;
    @FXML
    ChoiceBox selectCategory;
    public void initialize() {
        InitializeDatabase.initCategory();
        DBCollection productCheck = InitializeDatabase.database.getCollection("Category_Table");
        DBCursor findIterable = productCheck.find();
        ObservableList dropDownList = FXCollections.observableArrayList();
        for (DBObject count : findIterable) {
            DBProductCategory = (String) count.get("CategoryName");
            dropDownList.add(DBProductCategory);
        }
        selectCategory.setItems(dropDownList);
    }
    @FXML
    public void Delete(){
        Boolean foundCategory=false;
        CategoryNametoDelete= (String) selectCategory.getValue();
        //String categorynametoDelete=txtDelete.getText();
        if (!CategoryNametoDelete.equals("")){
            if(CategoryNametoDelete.equals(CategoryNametoDelete.toLowerCase())) {
                InitializeDatabase.initCategory();
                DBCollection categoryCheck = InitializeDatabase.database.getCollection("Category_Table");
                DBCursor findIterable = categoryCheck.find();
                for (DBObject count : findIterable) {
                    CategoryNameCheck = (String) count.get("CategoryName");
                    if (CategoryNametoDelete.equals(CategoryNameCheck)) {
                        foundCategory = true;
                        BasicDBObject category = new BasicDBObject();
                        category.put("CategoryName", CategoryNametoDelete);
                        categoryCheck.findAndRemove(category);
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Category deleted Successfully");
                        a.showAndWait();
                    }

                }
                if (!foundCategory) {


                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Category doesn't exist to delete");
                    a.showAndWait();
                }
            }else{
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Category field must contain lowercase letters");
                a.showAndWait();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Category field is empty");
            a.showAndWait();
        }
    }


}
