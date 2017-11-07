package in.vamsoft.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		RetailStore ns=new RetailStore("Croma");
		
		System.out.println("Enter 1 to add customer");
		System.out.println("Enter 2 to add Product");
		System.out.println("Enter 3 to Check Product");
		System.out.println("Enter 4 to Book Product");
		System.out.println();
		Scanner scanner=new Scanner(System.in);
		int T= Integer.parseInt(br.readLine());
		
		switch (T) {
		case 1:
			System.out.println("Enter Customer Name");
			String name=scanner.nextLine();
			System.out.println("Enter Customer Phone No");
			String phoneno=scanner.nextLine();
			ns.addCustomer(name, phoneno);		
			break;
		case 2:
			System.out.println("Enter Product Name");
			String productName=scanner.nextLine();
			System.out.println("Enter Product Quantity");
			int quantity=scanner.nextInt();
			System.out.println("Enter Product Price");
			Float price=scanner.nextFloat();
			ns.addProduct(productName, quantity, price);
			break;
		case 3:
			System.out.println("Enter The Product Name To Search");
			String ptname=scanner.nextLine();
			System.out.println("No of Quantity Avaliable="+ns.checkProducts(ptname));
			break;
		case 4:
			System.out.println("Enter Customer Name");
			String prdname=scanner.nextLine();
			System.out.println("Enter Product Name");
			String product=scanner.nextLine();			
			System.out.println("Enter Quantity");
			int qty=scanner.nextInt();
			ns.bookProduct(prdname, product, qty);
			break;
		default:
			break;
		}

	}

}
