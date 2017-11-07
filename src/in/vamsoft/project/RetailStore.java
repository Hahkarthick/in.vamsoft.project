package in.vamsoft.project;

import java.util.ArrayList;
import java.lang.Number;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

import in.vamsoft.project.DbConnectionUtil;

public class RetailStore {
	
	String storeName;	
	ResultSet resultSet=null;
	Statement statement=null;
	Connection connection=DbConnectionUtil.getConnection();
	PreparedStatement preparedStatement=null;
	//connection=DbConnectionUtil.getConnection();

	
	
	
	public static class Sequence {

		  private static final AtomicInteger counter = new AtomicInteger();

		  public static int nextValue() {
		    return counter.getAndIncrement();
		  }
		}
	
	//Add customer	
	public void addCustomer(String name,String phoneno) {
		int custId = Sequence.nextValue();		
		Customer c1=new Customer(custId	,name, phoneno);		
//		Connection connection=null;
//		PreparedStatement preparedStatement=null;
//		connection=DbConnectionUtil.getConnection();
		try {			
			preparedStatement=connection.prepareStatement("INSERT INTO `customer`(`id`, `name`, `phoneno`) VALUES(?,?,?)");
			preparedStatement.setInt(1, c1.getCustomerId());
			preparedStatement.setString(2, c1.getCustomerName());
			preparedStatement.setString(3, c1.getPhoneNo());
			int rowsUpdate=preparedStatement.executeUpdate();
			System.out.println("Customer Inserted Sucessfully");
		}catch (SQLException se) {
			se.printStackTrace();
		}
		finally {
			try {
				preparedStatement.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
			DbConnectionUtil.closeConnection(connection);
		}
	}
	
	//Add Product
	public void addProduct(String productName, int quantity, float price) {
		int prodId = Sequence.nextValue();
		Products product=new Products(prodId, productName, quantity, price);
//		Connection connection=null;
//		PreparedStatement preparedStatement=null;
		connection=DbConnectionUtil.getConnection();
		try {			
			preparedStatement=connection.prepareStatement("INSERT INTO `product`(`id`, `productname`, `quantity`, `price`) VALUES(?,?,?,?)");
			preparedStatement.setInt(1,product.getId());
			preparedStatement.setString(2,product.getProductName());
			preparedStatement.setInt(3,product.getQuantity());
			preparedStatement.setFloat(4,product.getPrice());
			int rowsUpdate=preparedStatement.executeUpdate();
			System.out.println("Product Insert Sucessfully");
		}catch (SQLException se) {
			se.printStackTrace();
		}
		finally {
			try {
				preparedStatement.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
			DbConnectionUtil.closeConnection(connection);
		}
		
		
	}
	
	//Check Product
	public int checkProducts(String productname) {
		System.out.println("RetailStore.checkProducts()"+productname);
		try(PreparedStatement preparedStatement=connection.prepareStatement("SELECT quantity, COUNT(CASE WHEN quantity > 0 THEN 1 END) AS NumberOfGreaterThan0 FROM product where productname=?")){
			preparedStatement.setString(1, productname);
			
			resultSet=preparedStatement.executeQuery();
			int available=0;
			while(resultSet.next()) {
				available=resultSet.getInt(1);
			}
			return available;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}
	
	//Book Product
	public boolean bookProduct(String name,String product,int quantity) {
		int bookingId = Sequence.nextValue();		
//		Connection connection=null;
//		Statement statement=null;
//		ResultSet resultSet=null;
//		PreparedStatement preparedStatement=null;
		connection=DbConnectionUtil.getConnection();
		try {
			int prodCount=checkProducts(product);
			if (prodCount>0) {
				try(PreparedStatement prepareStatement=connection.prepareStatement("INSERT INTO `bookedProduct`(`bookingId`, `custName`, `products`, `quantity`) VALUES(?,?,?,?)");){
					prepareStatement.setInt(1, bookingId);
					prepareStatement.setString(2,name);
					prepareStatement.setString(3,product);
					prepareStatement.setInt(4,quantity);					
					
					int rowsUpdated=prepareStatement.executeUpdate();
					return rowsUpdated>0?true:false;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {

			}
		}
		finally {
			try {
				preparedStatement.close();
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
		
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
		
		
