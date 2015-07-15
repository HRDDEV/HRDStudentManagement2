package controller.student;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.StudentDAO;
import model.dto.Student;
import Controller.Action;
import Controller.ActionForward;

public class ListStudent implements Action {

	
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String stuName = request.getParameter("stuName");
		String className = request.getParameter("className");
		String status = request.getParameter("status");
		ArrayList<Student> students = new StudentDAO().list(stuName, className, status);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String student = new Gson().toJson(students);
		
		response.getWriter().write(student);
	
		System.out.println(student);
		
		return null;
	}
	public static void main(String[] args) {
		
	}

}
