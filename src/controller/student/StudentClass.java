package controller.student;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controller.Action;
import controller.ActionForward;
import model.dao.StudentDAO;

public class StudentClass implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<String> student_class = new StudentDAO().classList();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String class_list = new Gson().toJson(student_class);
		response.getWriter().write(class_list);
		System.out.println(class_list);
		return null;
	}

}
