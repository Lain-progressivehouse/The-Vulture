package project.battinggame;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/project/battinggame/makeRoom")
public class MakeRoomServlet extends HttpServlet {

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
		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		int roomNumber = Integer.parseInt(request.getParameter("room"));

		ServletContext context = this.getServletContext();
		Game game = (Game) context.getAttribute("game");
		if (game.getRoomByRoomNumber(roomNumber) == null) {
			Room room = game.makeRoom(roomNumber);

			User user = room.getUserByName(name);
			if (user == null) {
				user = new User();
				user.setName(name);
				room.addUser(user);
			}

			user.setHandList();

			context.setAttribute("room", room);
			session.setAttribute("user", user);

			response.sendRedirect("main.html");
		} else {
			response.sendRedirect("index.html");
		}

	}

}
