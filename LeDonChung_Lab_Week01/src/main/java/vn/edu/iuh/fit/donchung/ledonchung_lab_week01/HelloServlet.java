package vn.edu.iuh.fit.donchung.ledonchung_lab_week01;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.utils.AppUtils;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {

        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        var em = AppUtils.getEntityManager();
        em.close();
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}