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
 * 部屋番号が一致する部屋のユーザ名の配列をJSONで送り返す
 * 
 * @author 15-1-037-0051 岩田蓮
 *
 */
@WebServlet("/project/battinggame/connect")
public class ConnectServlet extends HttpServlet {
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
			// 部屋が開始しているなら処理終了
			if (room.isStart()) {
				response.setContentType("application/text");
				PrintWriter writer = response.getWriter();
				writer.append("start");
				writer.flush();
				return;
			}

			StringBuilder builder = new StringBuilder();

			/*
			 * json
			 * {
			 * name: ["user1","user2", ... ]
			 * }
			 */
			builder.append("{");
			builder.append("\"name\":[");
			// iteratorを使った処理, 最後の要素のみ","を入れない
			for (Iterator<User> iterator = room.getUserList().iterator(); iterator.hasNext();) {
				User u = iterator.next();
				builder.append("\"").append(u.getName()).append("\"");
				if (iterator.hasNext())
					builder.append(",");
			}
			builder.append("]");
			builder.append("}");

			String json = builder.toString();

			response.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			writer.append(json);
			writer.flush();

		}
	}
}
