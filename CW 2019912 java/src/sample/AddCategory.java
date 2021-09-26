package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddCategory {
    @FXML
    TextField txtCategory;
    public static String CategoryNameCheck;

    public void AddCategory(){
        Boolean foundCategory=false;
        String category=txtCategory.getText();
        if (!category.equals("")){
            if(category.equals(category.toLowerCase())) {
                InitializeDatabase.initCategory();
                DBCollection categoryCheck = InitializeDatabase.database.getCollection("Category_Table");
                DBCursor findIterable = categoryCheck.find();
                for (DBObject count : findIterable) {
                    CategoryNameCheck = (String) count.get("CategoryName");
                    if (category.equals(CategoryNameCheck)) {
                        foundCategory = true;
                    }

                }
                if (foundCategory) {
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Category already exists");
                    a.showAndWait();
                } else {
                    BasicDBObject variableName = new BasicDBObject();
                    variableName.put("CategoryName", category);
                    InitializeDatabase.initCategory();
                    DBCollection collection = InitializeDatabase.database.getCollection("Category_Table");
                    collection.insert(variableName);
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Category added Successfully");
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
