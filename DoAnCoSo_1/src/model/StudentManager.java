package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentManager implements IManager {
	private Connection connection;

	public StudentManager() {
		connection = Ketnoi.getConnection();
	}

	@Override
	public ArrayList<Student> getAll() {

		ArrayList<Student> ans = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM student";
			ResultSet rs = statement.executeQuery(query); // lưu kết quả
			while (rs.next()) {
				int id = rs.getInt("ID");
				String masv = rs.getString("masv");
				String ten = rs.getString("ten");
				String lop = rs.getString("lop");
				String phong = rs.getString("phong");
				String khu = rs.getString("khu");
				Student student = new Student(id, masv, ten, lop, phong, khu);
				ans.add(student);
			}
		} catch (SQLException e) {

			return ans; // báo lỗi
		}

		return ans;
	}

	@Override
	public boolean add(Student s) {

		try {
			String query = "INSERT INTO student(masv, ten, lop, phong, khu) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, s.getMasv());
			ps.setString(2, s.getTen());
			ps.setString(3, s.getLop());
			ps.setString(4, s.getPhong());
			ps.setString(5,  s.getKhu());


			int row = ps.executeUpdate();
			if (row > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
		return false;
	}

	@Override
	public boolean edit(String masv, Student s) {

		try {

			String query = "UPDATE student SET masv = ?, ten = ?, lop = ?, phong = ?, khu = ? WHERE masv = ?";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, s.getMasv());
			ps.setString(2, s.getTen());
			ps.setString(3, s.getLop());
			ps.setString(4, s.getPhong());
			ps.setString(5, s.getKhu());
			ps.setString(6, masv);

			int row = ps.executeUpdate();

			if (row > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
		return false;
	}

	@Override
	public boolean delete(String masv) {

		try {
			String query = "DELETE FROM student WHERE masv = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, masv);

			int row = ps.executeUpdate();

			if (row > 0) {
				return true;
			}

		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	@Override
	public ArrayList<Student> findByMasv(String masv) {
		ArrayList<Student> res = new ArrayList<>();

		try {
			String query = "SELECT * FROM student WHERE masv = ?";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, masv);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String ma = rs.getString("masv");
				String ten = rs.getString("ten");
				String lop = rs.getString("lop");
				String phong = rs.getString("phong");
				String khu = rs.getString("khu");
				Student student = new Student(id, ma, ten, lop, phong, khu);
				res.add(student);}
		} catch (SQLException e) {

			return res;
		}

		return res;
	}

	// Trong lớp StudentManager
	public ArrayList<Student> findByPhong(String phong) {
		 ArrayList<Student> result = new ArrayList<>();

		    try {
		        String query = "SELECT * FROM student WHERE phong = ?";
		        PreparedStatement ps = connection.prepareStatement(query);

		        ps.setString(1, phong);

		        ResultSet rs = ps.executeQuery();

		        while (rs.next()) {
		            int id = rs.getInt("id");
		            String masv = rs.getString("masv");
		            String ten = rs.getString("ten");
		            String lop = rs.getString("lop");
		            String phongResult = rs.getString("phong");
		            String khu = rs.getString("khu");
		            Student student = new Student(id, masv, ten, lop, phongResult, khu);
		            result.add(student);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return result;
	}


	


	
}