package in.vamsoft.project;

public class Products {
  private int productId;
  private String productName;
  private int quantity;
  private float price;

  /**
   * @param id Sending product id.
   * @param productName Sending product Name.
   * @param quantity Sending product Quantity.
   * @param price Sending product Price.
   */
  public Products(int id, String productName, int quantity, float price) {
    super();
    this.productId = id;
    this.productName = productName;
    this.quantity = quantity;
    this.price = price;
  }

  /**
   * @param productName Sending product Name.
   * @param quantity Sending product Quantity.
   * @param price Sending product Price.
   */ 
  public Products(String productName, int quantity, float price) {
    super();
    this.productName = productName;
    this.quantity = quantity;
    this.price = price;
  }

  public Products() {
    // TODO Auto-generated constructor stub
  }

  public int getId() {
    return productId;
  }

  public void setId(int id) {
    this.productId = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

}
