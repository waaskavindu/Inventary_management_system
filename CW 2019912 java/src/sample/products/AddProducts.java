package sample.products;

import com.mongodb.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.InitializeDatabase;

public class AddProducts {
    public static String ProductIdCheck;
    public static String ProductNameCheck;
    public static String DBCategoryName;
    public static String CategorySelected;


    @FXML
    TextField txtProductName;
    @FXML
    TextField txtProductId;
    @FXML
    ChoiceBox cmbCategory;
    @FXML
    public void initialize(){
        InitializeDatabase.initCategory();
        DBCollection productCheck = InitializeDatabase.database.getCollection("Category_Table");
        DBCursor findIterable = productCheck.find();
        ObservableList dropDownList = FXCollections.observableArrayList();
        for (DBObject count : findIterable) {
            DBCategoryName = (String) count.get("CategoryName");
            dropDownList.add(DBCategoryName);
        }
        cmbCategory.setItems(dropDownList);

    }
    public void AddProducts(){
        Boolean foundProducts=false;
        String productName=txtProductName.getText();
        String productId=txtProductId.getText();
        CategorySelected= (String)cmbCategory.getValue();
        if (!productName.equals("")|!productId.equals("")){
            if(productName.equals(productName.toLowerCase())) {
                InitializeDatabase.initCategory();
                DBCollection productCheck = InitializeDatabase.database.getCollection("Product_Table");
                DBCursor findIterable = productCheck.find();
                for (DBObject count : findIterable) {
                    ProductNameCheck = (String) count.get("ProductName");
                    ProductIdCheck= (String) count.get("ProductId");
                    if (productName.equals(ProductNameCheck)|productId.equals(ProductIdCheck)) {
                        foundProducts = true;
                    }

                }
                if (foundProducts) {
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Product Name or Product Id already exists");
                    a.showAndWait();
                } else {
                    BasicDBObject variableProduct = new BasicDBObject();
                    variableProduct.put("ProductName", productName);
                    variableProduct.put("ProductId",productId);
                    variableProduct.put("CategoryName",CategorySelected);
                    InitializeDatabase.initCategory();
                    DBCollection collection = InitializeDatabase.database.getCollection("Product_Table");
                    collection.insert(variableProduct);
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Product added Successfully");
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
            a.setContentText("Product Name or Product Id fields are empty");
            a.showAndWait();
        }
    }
}

