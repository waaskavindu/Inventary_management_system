package sample.stock;

import javafx.beans.property.SimpleStringProperty;

public class ModelForStock {
    private String ProductId;

    private String ProductName;

    private String CategoryName;

    private SimpleStringProperty stockAmount;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getStockAmount() {

        return stockAmount.get();
    }

    public void setStockAmount(String stockAmount) {

        this.stockAmount = new SimpleStringProperty(stockAmount);
    }
}
