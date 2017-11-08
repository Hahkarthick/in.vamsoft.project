package in.vamsoft.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import in.vamsoft.project.DbConnectionUtil;

public class RetailStore {

	String storeName;
	ResultSet resultSet = null;
	Statement statement = null;
	Connection connection = DbConnectionUtil.getConnection();
	PreparedStatement preparedStatement = null;

	// Add customer
	public void addCustomer(String name, String phoneno) {
		Customer c1 = new Customer(name, phoneno);
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `customer`(`name`, `phoneno`) VALUES(?,?)");
			preparedStatement.setString(1, c1.getCustomerName());
			preparedStatement.setString(2, c1.getPhoneNo());
			int rowsUpdate = preparedStatement.executeUpdate();
			System.out.println("Customer Inserted Sucessfully");
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			DbConnectionUtil.closeConnection(connection);
		}
	}

	// Add Product
	public void addProduct(String productName, int quantity, float price) {
		Products product = new Products(productName, quantity, price);
		connection = DbConnectionUtil.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement("INSERT INTO `product`(`productname`, `quantity`, `price`) VALUES(?,?,?)");
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setInt(2, product.getQuantity());
			preparedStatement.setFloat(3, product.getPrice());
			int rowsUpdate = preparedStatement.executeUpdate();
			System.out.println("Product Insert Sucessfully");
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			DbConnectionUtil.closeConnection(connection);
		}

	}

	// Check Product
	public int checkProducts(String productname) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT quantity, COUNT(CASE WHEN quantity > 0 THEN 1 END) AS NumberOfGreaterThan0 FROM product where productname=?")) {
			preparedStatement.setString(1, productname);

			resultSet = preparedStatement.executeQuery();
			int available = 0;
			while (resultSet.next()) {
				available = resultSet.getInt(1);
			}
			return available;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	// Book Product
	public boolean bookProduct(int id, String product, int quantity) {
		float price = 0;
		int bqty = 0;
		connection = DbConnectionUtil.getConnection();
		try {
			int prodCount = checkProducts(product);
			// System.out.println(prodCount);
			if (prodCount > 0) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT `price`,`quantity` FROM product WHERE productname=?");
						PreparedStatement prepareStatement2 = connection.prepareStatement(
								"INSERT INTO `bookedProduct`(`custId`, `products`, `quantity`,`price`) VALUES(?,?,?,?,?)");
						PreparedStatement prepareStatement3 = connection
								.prepareStatement("Update product set quantity=? where productname=?");) {
					preparedStatement.setString(1, product);
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						price = resultSet.getFloat(1);
						bqty = resultSet.getInt(2);
					}

					int updateQty = bqty - quantity;

					prepareStatement3.setInt(1, updateQty);
					prepareStatement3.setString(2, product);
					int updateRow = prepareStatement3.executeUpdate();

					prepareStatement2.setInt(1, id);
					prepareStatement2.setString(2, product);
					prepareStatement2.setInt(3, quantity);
					prepareStatement2.setFloat(4, price);

					int rowsUpdated = prepareStatement2.executeUpdate();
					System.out.println("Product Added Sucessufully");
					return rowsUpdated > 0 ? true : false;
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	public double calculateNetAmount(int custId, double discount) {
		float netAmount = 0;
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT SUM(price) FROM bookedProduct WHERE custId=?");) {
			preparedStatement.setFloat(1, custId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				netAmount = resultSet.getFloat(1);
				System.out.println("Net Amount:" + netAmount);
				if (discount > 0) {
					double discountAmount = ((netAmount * discount) / 100);
					double totalAmount = discountAmount - netAmount;
					System.out.println("Total Amount=" + totalAmount);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	public RetailStore(String storeName) {
		super();
		this.storeName = storeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
