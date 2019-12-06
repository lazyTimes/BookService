package com.itcast.zxd.domain;

public class Order {
	private String Orderid;
	private int Userid;
	private String OrderName;
	private double Price;
	private int PayState;
	private String Receivename;
	private String ReceivePhone;
	private String ReceiveAddress;
	private String OrderCreatetime;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public String toString() {
		return "Order [Orderid=" + Orderid + ", Userid=" + Userid + ", OrderName=" + OrderName + ", Price=" + Price
				+ ", PayState=" + PayState + ", Receivename=" + Receivename + ", ReceivePhone=" + ReceivePhone
				+ ", ReceiveAddress=" + ReceiveAddress + ", OrderCreatetime=" + OrderCreatetime + "]";
	}



	public String getReceivePhone() {
		return ReceivePhone;
	}
	public void setReceivePhone(String receivePhone) {
		ReceivePhone = receivePhone;
	}
	public String getOrderid() {
		return Orderid;
	}
	public void setOrderid(String orderid) {
		Orderid = orderid;
	}
	public int getUserid() {
		return Userid;
	}
	public void setUserid(int userid) {
		Userid = userid;
	}
	public String getOrderName() {
		return OrderName;
	}
	public void setOrderName(String orderName) {
		OrderName = orderName;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public int getPayState() {
		return PayState;
	}
	public void setPayState(int payState) {
		PayState = payState;
	}
	public String getReceivename() {
		return Receivename;
	}
	public void setReceivename(String receivename) {
		Receivename = receivename;
	}
	public String getReceiveAddress() {
		return ReceiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		ReceiveAddress = receiveAddress;
	}
	public String getOrderCreatetime() {
		return OrderCreatetime;
	}
	public void setOrderCreatetime(String orderCreatetime) {
		OrderCreatetime = orderCreatetime;
	}
	
	
}
