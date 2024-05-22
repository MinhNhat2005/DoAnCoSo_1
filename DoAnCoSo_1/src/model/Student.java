package model;

import java.util.Scanner;

public class Student {
	private int id;
	private String masv;
	private String ten;
	private String lop;
	private String phong;
	private String khu;


	public Student() {
		this.masv = "";
		this.ten = "";
		this.lop = "";
		this.phong = "";
		this.khu = "";
		
	}

	public Student(String masv, String ten, String lop, String phong, String khu) {
		this.masv = masv;
		this.ten = ten;
		this.lop = lop;
		this.phong = phong;
		this.khu = khu;
		
	}

	public Student(int id, String masv, String ten, String lop, String phong, String khu) {
		this.id = id;
		this.masv = masv;
		this.ten = ten;
		this.lop = lop;
		this.phong = phong;
		this.khu = khu;
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMasv() {
		return masv;
	}

	public void setMasv(String masv) {
		this.masv = masv;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public String getPhong() {
		return phong;
	}

	public void setPhong(String phong) {
		this.phong = phong;
	}
	public String getKhu() {
		return khu;
	}
	public void setKhu(String khu) {
		this.khu = khu;
	}
	

	public void input() {
		try (Scanner sc = new Scanner(System.in)) {
			try {

				System.out.print("Nhập mã sinh viên: ");
				this.masv = sc.nextLine();
				System.out.print("Nhập tên: ");
				this.ten = sc.nextLine();
				System.out.println("Nhập tên lớp: ");
				this.lop = sc.nextLine();
				System.out.print("Nhập địa chỉ: ");
				this.phong = sc.nextLine();
				System.out.print("Nhâp số dãy");
				this.khu = sc.nextLine();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", masv=" + masv + ", ten=" + ten + ", lop=" + lop + ", phong=" + phong + ", khu=" + khu +  "]";
	}

}