package project.battinggame;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Score
 */
@WebServlet("/project/battinggame/score")
public class ScoreServlet extends HttpServlet {
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

		if (room != null) {
			StringBuilder builder = new StringBuilder();

			builder.append("{");
			builder.append("\"userList\":[");

			// iteratorを使った処理, 最後の要素のみ","を入れない
			for (Iterator<User> iterator = room.getUserList().iterator(); iterator.hasNext();) {
				User u = iterator.next();

				builder.append("{");
				builder.append("\"name\":\"").append(u.getName()).append("\"");
				builder.append(",");
				builder.append("\"score\":\"").append(u.getScore()).append("\"");
				builder.append("}");

				if (iterator.hasNext())
					builder.append(",");
			}

			builder.append("]");
			builder.append("}");

			String json = builder.toString();

			System.out.println(json);

			response.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			writer.append(json);
			writer.flush();
		}
	}
}
