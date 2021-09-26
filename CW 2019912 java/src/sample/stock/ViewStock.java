package sample.stock;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.InitializeDatabase;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewStock <findIterable> implements Initializable {
    @FXML
    private TableView<ModelForStock> tblView;

    @FXML
    public TableColumn<ModelForStock, String> tblColumnCategory;
    @FXML
    public TableColumn<ModelForStock, String> tblColumnId;
    @FXML
    public TableColumn<ModelForStock, String> tblColumnName;
    @FXML
    public TableColumn<ModelForStock, String> tblColumnStock;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblColumnId.setCellValueFactory(new PropertyValueFactory<>("ProductId"));
        tblColumnCategory.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
        tblColumnName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        tblColumnStock.setCellValueFactory(new PropertyValueFactory<>("StockAmount"));


        display();


    }
    public void display(){
        ObservableList<ModelForStock> mainList2 = FXCollections.observableArrayList();
        InitializeDatabase.initCategory();
        DBCollection ProductCheck = InitializeDatabase.database.getCollection("Stock_Table");
        DBCursor findIterable = ProductCheck.find();
        for (DBObject count : findIterable) {
            ModelForStock stock = new ModelForStock();
            stock.setProductId((String) count.get("ProductId"));
            stock.setCategoryName((String) count.get("CategoryName"));
            stock.setProductName((String) count.get("ProductName"));
            stock.setStockAmount((String) count.get("StockAmount"));
            mainList2.add(stock);
        }
        tblView.setItems(mainList2);
    }

}

