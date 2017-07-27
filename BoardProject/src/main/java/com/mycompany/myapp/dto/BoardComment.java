package com.mycompany.myapp.dto;

import java.util.Date;

public class BoardComment {
	private int bno;
	private int bcno;
	private String bcwriter;
	private Date bcdate;
	private String bccomment;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getBcno() {
		return bcno;
	}
	public void setBcno(int bcno) {
		this.bcno = bcno;
	}
	public String getBcuserid() {
		return bcwriter;
	}
	public void setBcuserid(String bcwriter) {
		this.bcwriter = bcwriter;
	}
	public Date getBcdate() {
		return bcdate;
	}
	public void setBcdate(Date bcdate) {
		this.bcdate = bcdate;
	}
	public String getBccomment() {
		return bccomment;
	}
	public void setBccomment(String bccomment) {
		this.bccomment = bccomment;
	}
	
	
	
}
