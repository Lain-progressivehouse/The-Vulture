package project.battinggame;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/project/battinggame/judge")
public class JudgeServlet extends HttpServlet {
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

			User winner = game.judge(room);

			if (winner != null) {
				builder.append("{");
				builder.append("\"name\":\"").append(winner.getName()).append("\"");
				builder.append("}");
			} else {
				builder.append("{");
				builder.append("\"name\":\"").append("\"");
				builder.append("}");
			}

			String json = builder.toString();

			System.out.println(json);

			response.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			writer.append(json);
			writer.flush();

		}
	}
}
