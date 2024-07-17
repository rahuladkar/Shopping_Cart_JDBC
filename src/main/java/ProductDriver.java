import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductDriver {

	public static void main(String[] args) {

		System.out.println("\n--------------------------- Welcome To ECA ---------------------------");

		try {

			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eca_jdbc", "postgres",
					"root");

			Scanner ip = new Scanner(System.in);
			Product product = new Product();
			ProductDao dao = new ProductDao();

			while (true) {

				System.out.println("\n1. Save Product (add)");
				System.out.println("2. Update Product ");
				System.out.println("3. Find Product (productId)");
				System.out.println("4. Display All Product ");
				System.out.println("5. Delete Product");
				System.out.println("0. exit");
				System.out.print("\nSelect option : ");
				int choice = ip.nextInt();

				switch (choice) {
				case 1: {
					dao.saveProduct(product, ip,conn);
				}
					break;

				case 2: {
					dao.updateProduct(product, ip,conn);
				}
					break;

				case 3: {

					System.out.print("Enter Product ID : ");
					product.setProductId(ip.nextInt());

					System.out.println(dao.findById(product.getProductId(), ip,conn));
				}
					break;

				case 4: {
					dao.displayAll(conn).forEach(System.out::println);
					;
				}
					break;

				case 5: {

					System.out.print("Enter Product ID : ");
					product.setProductId(ip.nextInt());
					dao.deleteProduct(product.getProductId(),conn);
				}
					break;

				case 0: {
					System.out.println("\nThank You");
					System.exit(1);
				}

				default:
					break;
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
