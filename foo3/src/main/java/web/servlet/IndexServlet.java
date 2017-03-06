package web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import web.content.MyContentInit;

@WebServlet(urlPatterns = "/indexServlet")
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = -5002337314141864621L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 取得 spring 管理的 bean
		WebApplicationContext requiredWebApplicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(req.getServletContext());
		MyContentInit bean = requiredWebApplicationContext.getBean(MyContentInit.class);
		System.out.println("indexServlet request ... " + new Date() + "  " + bean.getName());
	}

}
