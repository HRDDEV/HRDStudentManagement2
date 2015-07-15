package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.dto.Student;

public class StudentDAO {
	Connection conn;

	public StudentDAO() throws Exception {
		InitialContext init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/hrd_student");
		this.conn = ds.getConnection();
	}

	public boolean update(String id, int status) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("UPDATE hrd_students SET stu_status=? WHERE stu_id=?");
		ps.setInt(1, status);
		ps.setString(2, id);
		int i = ps.executeUpdate();
		if (i > 0) {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					throw e;
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					throw e;
				}
			return true;
		}
		return false;
	}

	public ArrayList<Student> list(String stuName, String className, String status) throws SQLException {
		String sql = "SELECT * FROM hrd_students WHERE stu_name LIKE ? AND stu_class LIKE ? AND stu_status LIKE ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + stuName + "%");
		ps.setString(2, "%" + className + "%");
		ps.setString(3, "%" + status + "%");
		ResultSet rs = ps.executeQuery();
		ArrayList<Student> article = new ArrayList<Student>();
		try {
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString("stu_id"));
				student.setName(rs.getString("stu_name"));
				student.setGender(rs.getInt("stu_gender") + "");
				student.setUniversity(rs.getString("stu_university"));
				student.setClassName(rs.getString("stu_class"));
				student.setStatus(rs.getInt("stu_status"));
				article.add(student);
			}
			return article;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					throw e;
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					throw e;
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					throw e;
				}
		}
	}

	public ArrayList<String> classList() throws SQLException {
		String sql = "SELECT DISTINCT stu_class FROM hrd_students";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<String> list = new ArrayList<String>();
		try {
			while (rs.next()) {
				list.add(rs.getString("stu_class"));
			}
			return list;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					throw e;
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					throw e;
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					throw e;
				}
		}
	}
	
	public boolean insert(Student stu) throws SQLException {
		System.out.println(stu.getGender());
		int gender = (stu.getGender() == "Male") ? 1 : 0;
		String sql = "INSERT INTO hrd_students (stu_id,stu_name,stu_gender,stu_university,stu_class,stu_status)"
				+ "VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "007");
		ps.setString(2, stu.getName());
		ps.setInt(3, gender);
		ps.setString(4, stu.getUniversity());
		ps.setString(5, stu.getClassName());
		ps.setInt(6, 1);
		int i = ps.executeUpdate();
		if (i > 0) {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					throw e;
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					throw e;
				}
			return true;
		}
		return false;
	}
}
