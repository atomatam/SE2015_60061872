package kr.ac.mju.model;
//year varchar(5) not null,
//hakki varchar(2)
public class Gwamok {
	private String gwamokID;
	private String gwamokName;
	private String gwamokHakjeom;
	private String haknyun;
	private String year;
	private String hakki;
	//getter and setter
	public String getGwamokID() {return gwamokID;}
	public void setGwamokID(String gwamokID) {this.gwamokID = gwamokID;}
	public String getGwamokName() {return gwamokName;}
	public void setGwamokName(String gwamokName) {this.gwamokName = gwamokName;}
	public String getGwamokHakjeom() {return gwamokHakjeom;}
	public void setGwamokHakjeom(String gwamokHakjeom) {this.gwamokHakjeom = gwamokHakjeom;}
	public String getHaknyun() {return haknyun;}
	public void setHaknyun(String haknyun) {this.haknyun = haknyun;}
	public String getYear() {return year;}
	public void setYear(String year) {this.year = year;	}	
	public String getHakki() {return hakki;	}
	public void setHakki(String hakki) {this.hakki = hakki;}
	
	public Gangjwa getGangjwa() {
		Gangjwa gangjwa = new Gangjwa();
		gangjwa.setGwamokID(this.gwamokID);
		gangjwa.setGwamokName(this.gwamokName);
		gangjwa.setGwamokHakjeom(this.gwamokHakjeom);
		gangjwa.setHaknyun(this.haknyun);
		gangjwa.setYear(this.year);
		gangjwa.setHakki(this.hakki);
		
		return gangjwa;
	}
}
