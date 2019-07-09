package project.battinggame;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/project/battinggame/send")
public class SendServlet extends HttpServlet {
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
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// 送られたroomNumberから部屋を割り出す
		String roomNumber = (request.getParameter("room") == null) ? "" : (String) request.getParameter("room");
		ServletContext context = this.getServletContext();
		Game game = (Game) context.getAttribute("game");
		Room room = game.getRoomByRoomNumber(Integer.parseInt(roomNumber));

		// nameからuserを割り出す
		String name = (request.getParameter("name") == null) ? "" : (String) request.getParameter("name");
		User user = room.getUserByName(name);

		for (User u : room.getUserList())
			u.setSyncFlg(false);

		// カードをセット
		if (user != null) {
			int choiseCard = Integer.parseInt((request.getParameter("card") == null) ? "" : (String) request
					.getParameter("card"));
			user.setChoiseCard(choiseCard);
			user.setUpdateUserInfoFlg(true);
		}
	}
}
