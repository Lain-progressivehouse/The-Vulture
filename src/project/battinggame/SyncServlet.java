package project.battinggame;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 同期する
 * 
 * @author 15-1-037-0051 岩田蓮
 *
 */
@WebServlet("/project/battinggame/sync")
public class SyncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ServletContext context = this.getServletContext();

		Game game = (Game) context.getAttribute("game");
		if (game == null) {
			game = new Game();
			context.setAttribute("game", game);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 送られたroomNumberから部屋を割り出す
		String roomNumber = (request.getParameter("room") == null) ? "" : (String) request.getParameter("room");
		ServletContext context = this.getServletContext();
		Game game = (Game) context.getAttribute("game");
		Room room = game.getRoomByRoomNumber(Integer.parseInt(roomNumber));

		// nameからuserを割り出す
		String name = (request.getParameter("name") == null) ? "" : (String) request.getParameter("name");
		User user = room.getUserByName(name);

		user.setSyncFlg(true);

		if (room.isAllUserSync()) {
			for (User u : room.getUserList()) {
				u.setFlg();
			}
			System.out.println("ユーザー: " + user.getName());
			room.setAddScoreFlg(true);
			room.setScoreCardFlg(true);
			response.setContentType("application/text");
			PrintWriter writer = response.getWriter();
			writer.append("true");
			writer.flush();
			return;
		}

		response.setContentType("application/text");
		PrintWriter writer = response.getWriter();
		writer.append("false");
		writer.flush();
	}
}
