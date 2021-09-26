package sample.products;

import com.sun.org.apache.xpath.internal.objects.XString;
import javafx.beans.property.SimpleStringProperty;

public class ModelForProducts {
    private String productId;

    private String productName;

    private String CategoryName;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

