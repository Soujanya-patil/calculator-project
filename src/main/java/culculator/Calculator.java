package culculator;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calc")
//public class Calculator extends HttpServlet {
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
//		int a =Integer.parseInt(req.getParameter("num1"));
//		int b =Integer.parseInt(req.getParameter("num2"));@WebServlet("/calc")
		public class Calculator extends HttpServlet {

		    public void doPost(HttpServletRequest req, HttpServletResponse res)
		            throws IOException, ServletException {

		        int a = Integer.parseInt(req.getParameter("num1"));
		        int b = Integer.parseInt(req.getParameter("num2"));

		        String op = req.getParameter("operation");

		        int result = 0;

		        if(op.equals("add")) {
		            result = a + b;
		        }
		        else if(op.equals("sub")) {
		            result = a - b;
		        }
		        else if(op.equals("mul")) {
		            result = a * b;
		        }
		        else if(op.equals("div")) {
		            result = a / b;
		        }

		        res.setContentType("text/html");
		        PrintWriter out = res.getWriter();

		        out.println("<h1>Result = " + result + "</h1>");
		    }
//		}
//		PrintWriter out = res.getWriter();
//		int resu=0;
//		String op=req.getParameter("operation");
//		if(op.equals("add")) {
//			resu=a+b;
//		}
//		else if(op.equals("sub")) {
//			resu=a-b;
//		}
//		else if(op.equals("mul")) {
//			resu=a*b;
//		}
//		else if(op.equals("div")) {
//			resu=a/b;
//		}
//		out.print(resu);
//		
//	}
		
}
