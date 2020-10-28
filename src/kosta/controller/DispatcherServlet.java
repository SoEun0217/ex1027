package kosta.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 사용자 요청을 중앙집중적으로 관리해 줄 FrontController : 사용자 요청을 받아서 그에 해당하는 Controller를 찾아서
 * 호출하고 그결과(ModelAndView)를 받아서 해당하는 뷰페이지로 이동시킨다.
 * 
 */
@WebServlet(urlPatterns = "/elec", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

	private Map<String, Controller> map;

	@Override
	public void init() throws ServletException {
		System.out.println("init call...");
		map = (Map<String, Controller>) super.getServletContext().getAttribute("map");
	}

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("요청됨..");
		String key = request.getParameter("command");
		System.out.println("key" + key);

		// command값이 없을 때 default값 설정하기
		if (key == null || key.equals(""))
			key = "list";

		try {
			ModelAndView mv = map.get(key).handleRequset(request, response);
			if (mv.isRedirect()) {
				response.sendRedirect(mv.getViewName());
			} else {
				request.getRequestDispatcher(mv.getViewName()).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();//개발할때는 이렇게 오류메시지 확인하자
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("errorView/error.jsp").forward(request, response);
		}
	}

}
