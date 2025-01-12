package com.mycompany.mypackage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.mycompany.packageofmybean.MyBean;

// Extend HttpServlet class
public class ControllerServletMBeanReader extends HttpServlet {
    private String message = "Hello world";
    private String message2;

    public void init() throws ServletException {
        // Do required initialization
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			MyBean bean = (MyBean) envCtx.lookup("bean/MyBeanFactoryDefinedInContext");
			message2 = "foo = " + bean.getFoo() + "foo2 = " + bean.getFoo2() + "foo3 = " + bean.getFoo3() + ", bar = " +bean.getBar();
		} catch(Exception e){
			message2 = e.getMessage();
		}
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");
        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
        out.println("<h2>" + message2 + "</h2>");
    }

    public void destroy() {
        // do nothing.
    }
}