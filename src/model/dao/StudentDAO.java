package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.dto.Student;

public class StudentDAO  {
	Connection cn;
	public StudentDAO() throws Exception{
		InitialContext init = new InitialContext();
  	    DataSource ds = (DataSource) init.lookup("java:comp/env/hrd_student");
  	    this.cn = ds.getConnection();		
	}
	
	public boolean insert(Student stu) throws SQLException{
		String sql = "INSERT INTO hrd_students (stu_id,stu_name,stu_gender,stu_university,stu_class,stu_status)"
					+ "VALUES (?,?,?,?,?,1)";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, "131N" + stu.getId());
		ps.setString(2, stu.getName());
		ps.setInt(3, Integer.parseInt(stu.getGender()));
		ps.setString(4, stu.getUniversity());
		ps.setString(5, stu.getClassName());
		int i = ps.executeUpdate();
		if(i>0){
			if(ps!=null)try{ps.close();}catch(SQLException e){throw e;}
			if(cn!=null)try{cn.close();}catch(SQLException e){throw e;}
			return true;
		}
		return false;
	}
	
	public boolean delete(String id) throws SQLException{
		String sql = "DELETE FROM hrd_students WHERE stu_id = ?";
		PreparedStatement ps = cn.prepareStatement(sql );
		ps.setString(1, id);
		int i = ps.executeUpdate();
		if(i>0){
			if(ps!=null)try{ps.close();}catch(SQLException e){throw e;}
			if(cn!=null)try{cn.close();}catch(SQLException e){throw e;}
			return true;
		}
		return false;
	}
	
	public boolean validateId(String id) throws SQLException{
		String sql = "SELECT stu_id FROM hrd_students WHERE stu_id = ?";
		PreparedStatement ps = cn.prepareStatement(sql );
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			if(ps!=null)try{ps.close();}catch(SQLException e){throw e;}
			if(rs!=null)try{rs.close();}catch(SQLException e){throw e;}
			if(cn!=null)try{cn.close();}catch(SQLException e){throw e;}
			return true;
		}
		return false;
	}
	
	public boolean update(Student stu) throws SQLException{
		String sql = "UPDATE hrd_students SET stu_name = ?, stu_gender = ?, stu_university = ?, "
				+ "stu_class = ? WHERE stu_id = ?";
		PreparedStatement ps = cn.prepareStatement(sql );
		ps.setString(1, stu.getName());
		ps.setString(2, stu.getGender());
		ps.setString(3, stu.getUniversity());
		ps.setString(4, stu.getClassName());
		ps.setString(5, stu.getId());
		int i = ps.executeUpdate();
		if(i>0){
			if(ps!=null)try{ps.close();}catch(SQLException e){throw e;}
			if(cn!=null)try{cn.close();}catch(SQLException e){throw e;}
			return true;
		}
		return false;
	}
	
	public ArrayList<Student> list(String stuName, String className, String status) throws SQLException{
		String gender = "";
		String sql = "SELECT * FROM hrd_students WHERE stu_name LIKE ? AND stu_class LIKE ? ORDER BY stu_id ASC";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, "%"+stuName+"%");
		ps.setString(2, "%"+className+"%");
		ResultSet rs = ps.executeQuery();
		ArrayList<Student> article = new ArrayList<Student>();
		try{
			while(rs.next()){
				gender = (rs.getInt("stu_gender") == 1) ? "Male" : "Female";
				Student student = new Student();
				student.setId(rs.getString("stu_id"));
				student.setName(rs.getString("stu_name"));
				student.setGender(gender);
				student.setUniversity(rs.getString("stu_university"));
				student.setClassName(rs.getString("stu_class"));
				article.add(student);
			}
			return article;
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){throw e;}
			if(ps!=null)try{ps.close();}catch(SQLException e){throw e;}
			if(cn!=null)try{cn.close();}catch(SQLException e){throw e;}
		}
	}
	
	public ArrayList<String> classList() throws SQLException{
		String sql = "SELECT DISTINCT stu_class FROM hrd_students ORDER BY stu_class";
		PreparedStatement ps = cn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<String> list = new ArrayList<String>();
		try{
			while(rs.next()){
				list.add(rs.getString("stu_class"));
			}
			return list;
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){throw e;}
			if(ps!=null)try{ps.close();}catch(SQLException e){throw e;}
			if(cn!=null)try{cn.close();}catch(SQLException e){throw e;}
		}
	}
}
