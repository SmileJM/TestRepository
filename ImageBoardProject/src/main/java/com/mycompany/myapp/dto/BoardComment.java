package com.mycompany.myapp.dto;

import java.util.Date;

public class BoardComment {
	private int bno;
	private int cno;
	private String cwriter;
	private Date cdate;
	private String ccomment;

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCwriter() {
		return cwriter;
	}

	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getCcomment() {
		return ccomment;
	}

	public void setCcomment(String ccomment) {
		this.ccomment = ccomment.replace("<", "&lt;");
		this.ccomment = ccomment.replace(">", "&gt;");
		this.ccomment = ccomment.replace("  ", "&nbsp;&nbsp;");
		this.ccomment = ccomment.replace("\n", "<br/>");
	}
}
