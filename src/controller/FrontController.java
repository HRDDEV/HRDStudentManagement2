package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.student.StudentClass;
import controller.student.StudentDelete;
import controller.student.StudentInsert;
import controller.student.StudentList;
import controller.student.StudentUpdate;
import controller.student.StudentValidation;

@WebServlet("*.etv")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;

		switch (command) {
		case "/liststudent.etv":
			action = new StudentList();
			break;
		case "/addstudent.etv":
			action = new StudentInsert();
			break;
		case "/deletestudent.etv":
			action = new StudentDelete();
			break;
		case "/updatestudent.etv":
			action = new StudentUpdate();
			break;
		case "/classlist.etv":
			action = new StudentClass();
			break;
		case "/validation.etv":
			action = new StudentValidation();
			break;
		default:
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
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}

		}

	}
}
