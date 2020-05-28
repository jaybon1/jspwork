

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChoiceDogServlet
 */
@WebServlet("/choiceDog")
public class ChoiceDogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChoiceDogServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		String[] dog =request.getParameterValues("dog");
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"    <title>Document</title>\r\n" + 
				"</head>\r\n" + 
				"<body bgcolor=\"black\">\r\n" + 
				"    <table align=\"center\" bgcolor=\"yellow\">\r\n" + 
				"        <tr>\r\n");
		for (int i = 0; i < dog.length; i++) {
			out.println("<td>");
			out.println("<imgsrc='"+dog[i]+"'/>");
			out.println("</td>");
		}
		out.println("        </tr>\r\n" + 
				"    </table>\r\n" + 
				"    \r\n" + 
				"</body>\r\n" + 
				"</html>");
	}

}
