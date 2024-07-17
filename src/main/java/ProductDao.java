import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDao {

	public void saveProduct(Product product , Scanner ip) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eca_jdbc", "postgres", "root");

		PreparedStatement pstm = conn.prepareStatement("Insert into product values(?,?,?,?)");

		System.out.print("Enter Product Id : ");
		product.setProductId(ip.nextInt());ip.nextLine();
		System.out.print("Enter Product Name : ");
		product.setProductName(ip.nextLine());

		System.out.print("Enter Product Type : ");
		product.setProductType(ip.nextLine());

		System.out.print("Enter Product Cost : ");
		product.setProductCost(ip.nextDouble());

		pstm.setInt(1, product.getProductId());
		pstm.setString(2, product.getProductName());
		pstm.setString(3, product.getProductType());
		pstm.setDouble(4, product.getProductCost());

		pstm.execute();
		
		System.out.println("\nProduct Inserted Successfully...!");

	}

	public void updateProduct(Product product , Scanner ip) throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eca_jdbc", "postgres", "root");
		
		System.out.println("1. Update Product Name");
		System.out.println("2. Update Type Name");
		System.out.println("3. Update Cost Name");
		System.out.print("\nSelect option : ");
		int choice =ip.nextInt();ip.nextLine();
		
		switch (choice) {
		case 1 : {
			System.out.print("Enter the new Product Name : ");
			product.setProductName(ip.nextLine());
			System.out.print("Enter the Old Product ID : ");
			product.setProductId(ip.nextInt());
			
			PreparedStatement pstm = conn.prepareStatement("Update product set productname = ? where productid = ?");
			
			pstm.setString(1, product.getProductName());
			pstm.setInt(2, product.getProductId());
			
			pstm.execute();
			
		}break;
		
		case 2 : {
			System.out.print("Enter the new Product Type : ");
			product.setProductType(ip.nextLine());
			System.out.print("Enter the Product ID : ");
			product.setProductId(ip.nextInt());
			
			PreparedStatement pstm = conn.prepareStatement("Update product set productype = ? where productid = ?");
			
			pstm.setString(1, product.getProductType());
			pstm.setInt(2, product.getProductId());
			
			pstm.execute();
			
		}break;
		
		case 3 : {
			System.out.print("Enter the new Product Cost : ");
			product.setProductCost(ip.nextDouble());
			System.out.print("Enter the Product ID : ");
			product.setProductId(ip.nextInt());
			
			PreparedStatement pstm = conn.prepareStatement("Update product set productcost = ? where productid = ?");
			
			pstm.setDouble(1, product.getProductCost());
			pstm.setInt(2,product.getProductId()); 
			
			pstm.execute();
			
		}break;

		default: System.out.println("\nSomething is wrong...!");
			break;
		}

		System.out.println("Update Successfully...!");
		conn.close();
		
	}

	public Product findById(int productId,Scanner ip) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eca_jdbc", "postgres", "root");
		
		PreparedStatement pstm = conn.prepareStatement("Select * from product where productId="+productId);
//		pstm.setInt(1, productId);
		
		ResultSet rs = pstm.executeQuery();
		
		if(rs.next()) {
			return new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
		}
		else {
			return null;
		}
		
	}
	
	public List<Product> displayAll() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eca_jdbc", "postgres", "root");
		
		PreparedStatement pstm = conn.prepareStatement("Select * from product");
		
		ResultSet rs = pstm.executeQuery();
		
		ArrayList<Product> products = new ArrayList<>();
		
		while(rs.next()) {
			products.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4)));
		}
		
		return products;
		
	}

	public void deleteProduct(int productId) throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eca_jdbc", "postgres", "root");

		PreparedStatement pstm = conn.prepareStatement("delete from product where productid = ?");
		pstm.setInt(1, productId);
		
		pstm.execute();
		
		System.out.println("\nProduct Deleted Successfully...!");
		
		
	}

}
