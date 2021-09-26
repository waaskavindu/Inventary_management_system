package sample.stock;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.InitializeDatabase;

import java.awt.event.ActionEvent;

public class EditStock {
    public static String DBProductName;
    public static String Product;
    public static String ProductNameCheck;
    public static String CurrentAmount;
    @FXML
    TextField txtNewAmount;
    @FXML
    ChoiceBox selectProduct;


    public void initialize(){
        InitializeDatabase.initCategory();
        DBCollection productCheck = InitializeDatabase.database.getCollection("Stock_Table");
        DBCursor findIterable = productCheck.find();
        ObservableList dropDownList = FXCollections.observableArrayList();
        for (DBObject count : findIterable) {
            DBProductName = (String) count.get("ProductName");
            dropDownList.add(DBProductName);
        }
        selectProduct.setItems(dropDownList);
    }
    @FXML
    public void Edit(){
        Boolean foundCategory=false;
        String ModifiedStock=txtNewAmount.getText();
        Product = (String) selectProduct.getValue();
        if (!ModifiedStock.equals("")){
            InitializeDatabase.initCategory();
            DBCollection categoryCheck = InitializeDatabase.database.getCollection("Stock_Table");
            DBCursor findIterable = categoryCheck.find();
            for (DBObject count : findIterable) {
                ProductNameCheck = (String) count.get("ProductName");
                if (Product.equals(ProductNameCheck))
                {
                    CurrentAmount = (String) count.get("ProductId");
                    foundCategory = true;
                }

            }
            if (foundCategory) {
                BasicDBObject queryForProductName = new BasicDBObject();
                queryForProductName.put("StockAmount", CurrentAmount);
                BasicDBObject newValueforStock = new BasicDBObject();
                newValueforStock.put("StockAmount", ModifiedStock);
                BasicDBObject updateObject1 = new BasicDBObject();
                updateObject1.put("$set", newValueforStock);
                InitializeDatabase.database.getCollection("Stock_Table").update(queryForProductName,updateObject1);
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Stock updated Successfully");
                a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Error");
                a.showAndWait();
            }


        }else{
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Stock field is empty");
            a.showAndWait();
        }
    }
}
