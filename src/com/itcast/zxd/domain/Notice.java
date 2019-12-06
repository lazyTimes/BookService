package com.itcast.zxd.domain;

/**
 * 	公告板对象
 * 	根据时间先后顺序进行排序
 * 
 * */
public class Notice {
	private int N_id;
	private String Title;
	private String Detail;
	private String Createtime;
	
	public Notice() {
		super();
	}

	public Notice(String title, String detail) {
		super();
		Title = title;
		Detail = detail;
	}



	public int getN_id() {
		return N_id;
	}
	public void setN_id(int n_id) {
		N_id = n_id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDetail() {
		return Detail;
	}
	public void setDetail(String detail) {
		Detail = detail;
	}
	public String getCreatetime() {
		return Createtime;
	}
	public void setCreatetime(String createtime) {
		Createtime = createtime;
	}
	
	
}
