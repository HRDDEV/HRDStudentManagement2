package controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.ActionForward;
import model.dao.StudentDAO;

public class StudentValidation implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		response.setContentType("text/plain");
		if(new StudentDAO().validateId(id)){
			System.out.println("Found");
			response.getWriter().write("found");
		}else{
			System.err.println("Not Found");
			response.getWriter().write("not found");
		}
		return null;
	}

}
