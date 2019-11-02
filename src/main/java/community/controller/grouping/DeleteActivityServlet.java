package community.controller.grouping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.service.ActivityService;
import community.service.impl.ActivityServiceImpl;
import register.model.MemberBean;

@WebServlet("/grouping/delete.do")
public class DeleteActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("./"); // !!!=> login
			return;
		}
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect("./"); // !!!=> login
			return;
		}

		String strId = "";
		Integer intId = null;
		Boolean b = false;
		
		// 1.
		strId = request.getParameter("d");

		
		// 2.
		if (strId == null || strId.equals("")) {
			// 沒有查詢字串 | ""
			response.sendRedirect("./");
			return;
			
		} else if (strId != null && !strId.equals("")) {
			try {
				intId = Integer.parseInt(strId);
			} catch (NumberFormatException e) {
				// 不是數字
				response.sendRedirect("./");
				return;
			}
			
			
			// 3.

			
			// 4.
//			---------------------------------------------------
			ActivityService ds = new ActivityServiceImpl();
			b = ds.deleteActivity(mb, intId);
//			---------------------------------------------------

			
			// 5.
			if (b) {
				// 刪除成功
				response.sendRedirect("./");
			} else {
				// 刪除失敗
				response.sendRedirect("activity?d=" + intId);
			}

		}
	}

}
