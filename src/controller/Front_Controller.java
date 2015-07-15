package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.student.AddStudent;
import controller.student.DeleteStudent;
import controller.student.GetClassName;
import controller.student.ListStudent;
import controller.student.UpdateStudent;
import controller.student.Validation;



@WebServlet("*.hrd")
public class Front_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Front_Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;

		switch(command){
			case "/liststudent.hrd":
				action = new ListStudent();
				break;
			case "/addstudent.hrd":
				action = new AddStudent();
				break;
			case "/deletestudent.hrd":
				action = new DeleteStudent();
				break;
			case "/updatestudent.hrd":
				action = new UpdateStudent();
				break;
			case "/classlist.hrd":
				action = new GetClassName();
				break;
			case "/validation.hrd":
				action = new Validation();
				break;
			default :
				forward = new ActionForward();
				forward.setPath("404.jsp");
				forward.setRedirect(false);
				break;
		}
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}

		}

	}
}
