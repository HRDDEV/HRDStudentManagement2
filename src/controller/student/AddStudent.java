package controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.StudentDAO;
import model.dto.Student;
import Controller.Action;
import Controller.ActionForward;

public class AddStudent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String university = request.getParameter("university");
		String className = request.getParameter("className");
		
		Student stu = new Student();
		stu.setName(name);
		System.out.println(gender);
		stu.setGender(gender);
		System.out.println(stu.getGender());
		stu.setUniversity(university);
		stu.setClassName(className);
		
		response.setContentType("text/plain");
		if(new StudentDAO().insert(stu)){
			System.out.println("Insert SUCCESS");
			response.getWriter().write("success");
		}else{
			System.err.println("Insert FAIL");
			response.getWriter().write("fail");
		}
		return null;
	}

}
