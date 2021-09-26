package sample;

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

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCategory <findIterable> implements Initializable {
    @FXML
    private TableView<ModelForCategory> tblView;

    @FXML
    public TableColumn<ModelForCategory, String> tblColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        display();

    }
    public void display(){
        ObservableList<ModelForCategory> mainList = FXCollections.observableArrayList();
        InitializeDatabase.initCategory();
        DBCollection categoryCheck = InitializeDatabase.database.getCollection("Category_Table");
        DBCursor findIterable = categoryCheck.find();
        for (DBObject count : findIterable) {
            ModelForCategory category = new ModelForCategory((String) count.get("CategoryName"));
            category.setCategory((String) count.get("CategoryName"));
            mainList.add(category);

        }
        tblView.setItems(mainList);
    }

}

