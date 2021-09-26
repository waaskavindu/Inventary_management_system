package sample.products;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;
import sample.InitializeDatabase;
import sample.ModelForCategory;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewProducts <findIterable> implements Initializable {
    @FXML
    private TableView<ModelForProducts> tblView;

    @FXML
    public TableColumn<ModelForProducts, String> tblColumnCategory;
    @FXML
    public TableColumn<ModelForProducts, String> tblColumnId;
    @FXML
    public TableColumn<ModelForProducts, String> tblColumnName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblColumnId.setCellValueFactory(new PropertyValueFactory<>("ProductId"));
        tblColumnCategory.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
        tblColumnName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));

        display();


    }
    public void display(){
        ObservableList<ModelForProducts> mainList = FXCollections.observableArrayList();
        InitializeDatabase.initCategory();
        DBCollection ProductCheck = InitializeDatabase.database.getCollection("Product_Table");
        DBCursor findIterable = ProductCheck.find();
        for (DBObject count : findIterable) {
            ModelForProducts products = new ModelForProducts();
            products.setProductId((String) count.get("ProductId"));
            products.setCategoryName((String) count.get("CategoryName"));
            products.setProductName((String) count.get("ProductName"));
            mainList.add(products);

        }
        tblView.setItems(mainList);
    }

}
