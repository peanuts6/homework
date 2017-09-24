package aoptest1;

/**
 * Created by xqy on 17/9/21.
 */
public class Product {
    private int productId;
    private String productName;
    private int price;
    private int  stock;
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName="
                + productName + ", price=" + price + ", stock=" + stock + "]";
    }
}
