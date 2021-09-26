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

public class DeleteProducts {
    public static String ProductNameCheck;
    public static String ProductIdCheck;
    @FXML
    TextField txtDelete;

    @FXML
    ChoiceBox NameId;

    public void initialize() {
        ObservableList array = FXCollections.observableArrayList();
        array.add("Product Id");
        array.add("ProductName");
        NameId.setItems(array);
    }

    @FXML
    public void Delete() {
        Boolean foundCategory = false;
        String productNameToDelete = txtDelete.getText();
        String productIdToDelete = txtDelete.getText();
        String choice = (String) NameId.getValue();
        if (!productNameToDelete.equals("") || !productIdToDelete.equals("")) {
            InitializeDatabase.initCategory();
            DBCollection productsCheck = InitializeDatabase.database.getCollection("Product_Table");
            DBCursor findIterable = productsCheck.find();
            if (choice.equals("ProductName")) {
                if (productNameToDelete.equals(productNameToDelete.toLowerCase())) {
                    for (DBObject count : findIterable) {
                        ProductNameCheck = (String) count.get("ProductName");
                        if (productNameToDelete.equals(ProductNameCheck)) {
                            foundCategory = true;
                            BasicDBObject variableProduct = new BasicDBObject();
                            variableProduct.put("ProductName", productNameToDelete);
                            productsCheck.findAndRemove(variableProduct);
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Product deleted Successfully");
                            a.showAndWait();
                        }
                        if (!foundCategory) {
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.ERROR);
                            a.setContentText("Product Name doesn't exist to delete");
                            a.showAndWait();
                        }
                    }
                }else{
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Product field must contain lowercase letters");
                    a.showAndWait();
                }

            }else if(choice.equals("Product Id")){
                for (DBObject count : findIterable) {
                    ProductIdCheck = (String) count.get("ProductId");
                    if (productIdToDelete.equals(ProductIdCheck)) {
                        BasicDBObject variableProductId = new BasicDBObject();
                        variableProductId.put("ProductId", productIdToDelete);
                        productsCheck.findAndRemove(variableProductId);
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Product deleted Successfully");
                        a.showAndWait();
                    }
                }
            }else{
                    //if (!foundCategory) {
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.setContentText("Product Id doesn't exist to delete");
                        a.showAndWait();


            }
        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Product/ID field is empty");
            a.showAndWait();
        }
    }

}








