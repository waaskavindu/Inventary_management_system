package sample.stock;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.InitializeDatabase;

public class AddStock {
    public static String loadingCategoryName;
    public static String loadingProductId;
    public static String ProductNameSelected;
    public static String DBProductName;
    public static String ProductSelected;
    @FXML
    TextField txtAmount;
    @FXML
    ChoiceBox selectProduct;


    public void initialize(){
        InitializeDatabase.initCategory();
        DBCollection productCheck = InitializeDatabase.database.getCollection("Product_Table");
        DBCursor findIterable = productCheck.find();
        ObservableList dropDownList = FXCollections.observableArrayList();
        for (DBObject count : findIterable) {
            DBProductName = (String) count.get("ProductName");
            dropDownList.add(DBProductName);
        }
        selectProduct.setItems(dropDownList);


    }
    public void AddProducts(){
        Boolean foundProduct=false;
        String StockAmount=txtAmount.getText();
        ProductSelected= (String)selectProduct.getValue();
        if (!StockAmount.equals("")){
            InitializeDatabase.initCategory();
            DBCollection productCheck = InitializeDatabase.database.getCollection("Product_Table");
            DBCursor findIterable = productCheck.find();
            for (DBObject count : findIterable) {
                ProductNameSelected = (String) count.get("ProductName");
                if (ProductSelected.equals(ProductNameSelected)) {
                    loadingCategoryName = (String) count.get("CategoryName");
                    loadingProductId = (String) count.get("ProductId");
                    foundProduct = true;
                }
            }
            if (foundProduct) {
                BasicDBObject variableForStock = new BasicDBObject();
                variableForStock.put("ProductName", ProductSelected);
                variableForStock.put("ProductId", loadingProductId);
                variableForStock.put("CategoryName", loadingCategoryName);
                variableForStock.put("StockAmount", StockAmount);

                InitializeDatabase.initCategory();
                DBCollection collection = InitializeDatabase.database.getCollection("Stock_Table");
                collection.insert(variableForStock);

                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Stock added Successfully");
                a.showAndWait();
            }else{
                System.out.println("Error");
            }

        }else{
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Product Name or Product Id fields are empty");
            a.showAndWait();
        }
    }
}
