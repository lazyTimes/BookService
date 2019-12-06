package com.itcast.zxd.domain;

/**
 * 	商品对应的一个pojo表
 * 	可以对于商品信息进行一一对应
 * 
 * */
public class Product {
	private String Proid;
	private String Author;
	private String ProductName;
	private int Pnum;
	private String Description;
	private String Imgurl;
	private int CategoryId;
	private double price;
	private int pcount = 1;	//标记字段，记住购物车商品数量
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String proid, String author, String productName, int pnum, String description, String imgurl,
			int categoryId) {
		super();
		Proid = proid;
		Author = author;
		ProductName = productName;
		Pnum = pnum;
		Description = description;
		Imgurl = imgurl;
		CategoryId = categoryId;
	}
	
	
	

	@Override
	public String toString() {
		return "Product [Proid=" + Proid + ", Author=" + Author + ", ProductName=" + ProductName + ", Pnum=" + Pnum
				+ ", Description=" + Description + ", Imgurl=" + Imgurl + ", CategoryId=" + CategoryId + ", price="
				+ price + "]";
	}

	public String getProid() {
		return Proid;
	}

	public void setProid(String proid) {
		Proid = proid;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public int getPnum() {
		return Pnum;
	}

	public void setPnum(int pnum) {
		Pnum = pnum;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getImgurl() {
		return Imgurl;
	}

	public void setImgurl(String imgurl) {
		Imgurl = imgurl;
	}

	public int getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(int categoryId) {
		CategoryId = categoryId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPcount() {
		return pcount;
	}

	public void setPcount(int pcount) {
		this.pcount = pcount;
	}
	
	
}
