package uk.co.kyleharrison.jobseeker.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uk.co.kyleharrison.jobseeker.model.appSession;

@WebServlet("/requestController")
public class requestController extends HttpServlet {
private static final long serialVersionUID = 1L;
       
	public requestController() {
	    super();
	 }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	directToHome(request, response);
	}
	
	private void directToHome(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		HttpSession session = req.getSession();
		appSession currentUserSession = (appSession)session.getAttribute("Session");
		
		System.out.println("Accessed Server");
		
		try	{
			if(currentUserSession != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(req, res);
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(req, res);
		}
		}catch (Exception e){
			System.out.print(e.getMessage()+"Home Controller");
		}
	}

}