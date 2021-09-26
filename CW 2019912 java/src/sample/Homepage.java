package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Homepage {
    public static Stage AddCategoryStage = new Stage();
    public static Stage DeleteCategoryStage = new Stage();
    public static Stage EditCategoryStage = new Stage();
    public static Stage ViewCategoryStage = new Stage();
    public static Stage AddStockStage = new Stage();
    public static Stage EditStockStage = new Stage();
    public static Stage ViewStockStage = new Stage();
    public static Stage AddProductsStage = new Stage();
    public static Stage DeleteProductsStage = new Stage();
    public static Stage EditProductsStage = new Stage();
    public static Stage ViewProductsStage = new Stage();
    public static Stage LogOutStage = new Stage();

    public void AddCategory() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AddCategory.fxml"));
        AddCategoryStage.setTitle("Add Category");
        AddCategoryStage.setScene(new Scene(root, 650, 650));
        AddCategoryStage.show();
    }

    public void DeleteCategory() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DeleteCategory.fxml"));
        DeleteCategoryStage.setTitle("Delete Category");
        DeleteCategoryStage.setScene(new Scene(root, 650, 650));
        DeleteCategoryStage.show();
    }

    public void EditCategory() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EditCategory.fxml"));
        EditCategoryStage.setTitle("Edit Category");
        EditCategoryStage.setScene(new Scene(root, 650, 650));
        EditCategoryStage.show();
    }

    public void ViewCategory() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ViewCategory.fxml"));
        ViewCategoryStage.setTitle("View Categories");
        ViewCategoryStage.setScene(new Scene(root, 600, 600));
        ViewCategoryStage.show();
    }

    public void AddStock() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("stock/AddStock.fxml"));
        AddStockStage.setTitle("Add Stock");
        AddStockStage.setScene(new Scene(root, 650, 650));
        AddStockStage.show();
    }

    public void EditStock() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("stock/EditStock.fxml"));
        EditStockStage.setTitle("Edit Stock");
        EditStockStage.setScene(new Scene(root, 650, 650));
        EditStockStage.show();
    }

    public void ViewStock() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("stock/ViewStock.fxml"));
        ViewStockStage.setTitle("View Stock");
        ViewStockStage.setScene(new Scene(root, 650, 650));
        ViewStockStage.show();
    }

    public void AddProducts() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("products/AddProducts.fxml"));
        AddProductsStage.setTitle("Add Products");
        AddProductsStage.setScene(new Scene(root, 650, 650));
        AddProductsStage.show();
    }

    public void DeleteProducts() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("products/DeleteProducts.fxml"));
        DeleteProductsStage.setTitle("Delete Products");
        DeleteProductsStage.setScene(new Scene(root, 650, 650));
        DeleteProductsStage.show();
    }

    public void EditProducts() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("products/EditProducts.fxml"));
        EditProductsStage.setTitle("Edit Products");
        EditProductsStage.setScene(new Scene(root, 650, 650));
        EditProductsStage.show();
    }

    public void ViewProducts() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("products/ViewProducts.fxml"));
        ViewProductsStage.setTitle("View Products");
        ViewProductsStage.setScene(new Scene(root, 650, 650));
        ViewProductsStage.show();
    }
    public void Logout(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        LogOutStage.setTitle("Loging page");
        LogOutStage.setScene(new Scene(root, 450, 500));
        LogOutStage.show();
        Stage close = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        close.close();

    }
}
