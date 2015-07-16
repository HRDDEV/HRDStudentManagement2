package controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.ActionForward;
import model.dao.StudentDAO;

public class StudentDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		response.setContentType("text/plain");
		if(new StudentDAO().delete(id)){
			System.out.println("Delete SUCCESS");
			response.getWriter().write("success");
		}else{
			System.err.println("Delete FAIL");
			response.getWriter().write("fail");
		}
		return null;
	}

}
