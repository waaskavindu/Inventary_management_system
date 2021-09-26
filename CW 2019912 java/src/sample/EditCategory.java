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

public class EditCategory {

    @FXML
    ChoiceBox selectCategory;
    @FXML
    TextField txtNew;

    public static String CategoryNameCheck;
    public static String DBProductCategory;
    public static String ExistingCategory;

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
    public void Update(){
        Boolean foundCategory=false;
        ExistingCategory= (String) selectCategory.getValue();
        //String ExistingCategory=txtExisting.getText();
        String ModifiedCategory=txtNew.getText();
        if (!ModifiedCategory.equals("")){
            if(ModifiedCategory.equals(ModifiedCategory.toLowerCase())) {
                InitializeDatabase.initCategory();
                DBCollection categoryCheck = InitializeDatabase.database.getCollection("Category_Table");
                DBCursor findIterable2 = categoryCheck.find();
                for (DBObject count : findIterable2) {
                    CategoryNameCheck = (String) count.get("CategoryName");
                    if (ExistingCategory.equals(CategoryNameCheck)) {
                        foundCategory = true;
                    }

                }
                if (foundCategory) {

                    BasicDBObject queryForProductCategory = new BasicDBObject();
                    queryForProductCategory.put("CategoryName", ExistingCategory);
                    BasicDBObject newNameCategory = new BasicDBObject();
                    newNameCategory.put("CategoryName", ModifiedCategory);
                    BasicDBObject updateObject1 = new BasicDBObject();
                    updateObject1.put("$set", newNameCategory);
                    InitializeDatabase.database.getCollection("Category_Table").update(queryForProductCategory,updateObject1);
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Category updated Successfully");
                    a.showAndWait();
                } else {
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Category doesn't exist to update");
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
            a.setContentText("One of the two fields are empty");
            a.showAndWait();
        }
    }
}
