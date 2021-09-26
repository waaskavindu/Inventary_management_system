package sample.products;

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
import sample.InitializeDatabase;

public class EditProducts {
    public static String ProductNameCheck;
    public static String ProductIdCheck;

    @FXML
    ChoiceBox cmbCategory;
    @FXML
    ChoiceBox NameId;
    @FXML
    TextField txtExisting;
    @FXML
    TextField txtNew;

    public void initialize() {
        ObservableList array = FXCollections.observableArrayList();
        array.add("Product Id");
        array.add("ProductName");
        NameId.setItems(array);

    }
    @FXML
    public void Update(){
        Boolean foundProduct=false;
        String ExistingProduct=txtExisting.getText();
        String ModifiedProduct=txtNew.getText();
        String choice = (String) NameId.getValue();
        if (!ExistingProduct.equals("") && !ModifiedProduct.equals("")){
            if(ExistingProduct.equals(ExistingProduct.toLowerCase())&& ModifiedProduct.equals(ModifiedProduct.toLowerCase())) {
                InitializeDatabase.initCategory();
                DBCollection categoryCheck = InitializeDatabase.database.getCollection("Product_Table");
                DBCursor findIterable = categoryCheck.find();
                for (DBObject count : findIterable) {
                    ProductNameCheck = (String) count.get("ProductName");
                    ProductIdCheck = (String) count.get("ProductId");
                    if (ExistingProduct.equals(ProductNameCheck)|ExistingProduct.equals(ProductIdCheck)) {
                        foundProduct = true;
                    }

                }
                if (foundProduct) {
                    if (choice.equals("ProductName")){
                        BasicDBObject queryForProductName = new BasicDBObject();
                        queryForProductName.put("ProductName", ExistingProduct);
                        BasicDBObject newProductName = new BasicDBObject();
                        newProductName.put("ProductName", ModifiedProduct);
                        BasicDBObject updateObject1 = new BasicDBObject();
                        updateObject1.put("$set", newProductName);
                        InitializeDatabase.database.getCollection("Product_Table").update(queryForProductName,updateObject1);
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Product Name updated Successfully");
                        a.showAndWait();
                    }else if (choice.equals("Product Id")){
                        BasicDBObject queryForProductId = new BasicDBObject();
                        queryForProductId.put("ProductId", ExistingProduct);
                        BasicDBObject newProductId = new BasicDBObject();
                        newProductId.put("ProductId", ModifiedProduct);
                        BasicDBObject updateId = new BasicDBObject();
                        updateId.put("$set", newProductId);
                        InitializeDatabase.database.getCollection("Product_Table").update(queryForProductId,updateId);
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Product Id updated Successfully");
                        a.showAndWait();

                    }else{
                        System.out.println("Error");
                    }

                } else {
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Product doesn't exist to update");
                    a.showAndWait();
                }
            }else{
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Product Name field must contain lowercase letters");
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












