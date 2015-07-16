package controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.ActionForward;
import model.dao.StudentDAO;
import model.dto.Student;

public class StudentUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String university = request.getParameter("university");
		String className = request.getParameter("className");
		
		Student stu = new Student();
		stu.setId(id);
		stu.setName(name);
		stu.setGender(gender);
		stu.setUniversity(university);
		stu.setClassName(className);

		response.setContentType("text/plain");
		if(new StudentDAO().update(stu)){
			System.out.println("Successful Update");
			response.getWriter().write("success");
		}else{
			System.err.println("Fail to Update");
			response.getWriter().write("fail");
		}
		return null;
	}

}
