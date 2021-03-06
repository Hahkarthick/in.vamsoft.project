package in.vamsoft.project;

public class Customer {
  private int customerId;
  private String customerName;
  private String phoneNo;

  /**
   * @param customerId Passing Customer Id.
   * @param customerName Passing Customer Name.
   * @param phoneNo Passing Customer Phone No.
   */
  public Customer(int customerId, String customerName, String phoneNo) {
    super();
    this.customerId = customerId;
    this.customerName = customerName;
    this.phoneNo = phoneNo;
  }

  /**
   * @param customerName Passing Customer Name.
   * @param phoneNo Passing Phone No.
   */
  public Customer(String customerName, String phoneNo) {
    super();
    this.customerName = customerName;
    this.phoneNo = phoneNo;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

}
