package controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.StudentDAO;
import Controller.Action;
import Controller.ActionForward;

public class EditStudent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		int status = Integer.parseInt(request.getParameter("status"));

		response.setContentType("text/plain");
		if(new StudentDAO().update(id,status)){
			System.out.println("Update SUCCESS");
			response.getWriter().write("success");
		}else{
			System.err.println("Update FAIL");
			response.getWriter().write("fail");
		}
		return null;
	}

}
