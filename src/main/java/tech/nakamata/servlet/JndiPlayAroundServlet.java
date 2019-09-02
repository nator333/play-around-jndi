package tech.nakamata.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class JndiPlayAroundServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public JndiPlayAroundServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Set the response message's MIME type
    response.setContentType("text/html;charset=UTF-8");
    // Allocate a output writer to write the response message into the network socket

    // Write the response message, in an HTML page
    try (PrintWriter out = response.getWriter()) {
      out.println("<!DOCTYPE html>");
      out.println("<html><head>");
      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
      out.println("<title>Hello, World</title></head>");
      out.println("<body>");
      out.println("<h1>Hello, world!</h1>");  // says Hello
      // Echo client's request information
      out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
      out.println("<p>Protocol: " + request.getProtocol() + "</p>");
      out.println("<p>PathInfo: " + request.getPathInfo() + "</p>");
      out.println("<p>Remote Address: " + request.getRemoteAddr() + "</p>");
      // Generate a random number upon each request
      out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");

      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/test");
        System.out.println(ds.toString());
        Connection con = ds.getConnection();
        System.out.println("con: " + con);
        Statement smt = con.createStatement();

        ResultSet rs = smt.executeQuery("SELECT * FROM test.staffs");

        out.println("<table style='width:100%'>");
        out.println("<tr><th>id</th><th>Name</th></tr>");

        while (rs.next()) {
          out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("name") + "</td></td>");
        }

        out.println("</table>");
        con.close();
      } catch (SQLException | NamingException | ClassNotFoundException sqlE) {
        sqlE.printStackTrace();
      }

      out.println("</body>");
      out.println("</html>");
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
