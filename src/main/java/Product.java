
public class Product {

	private int productId;
	private String productName;
	private String productType;
	private double productCost;
	
	// getter() and setter()
	
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
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public double getProductCost() {
		return productCost;
	}
	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}
	
	// default constructor 
	Product(){}
	
	@Override
	public String toString() {
		return "productId = " + productId + ", productName = " + productName + ", productType = " + productType
				+ ", productCost=" + productCost;
	}
	// constructor to initialize the attributes
	public Product(int productId, String productName, String productType, double productCost) {
		super();
		setProductCost(productCost);
		setProductName(productName);
		setProductType(productType);
		setProductCost(productCost);
	}
}