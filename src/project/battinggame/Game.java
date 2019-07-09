package project.battinggame;

import java.util.ArrayList;

public class Game {
	private ArrayList<Room> roomList = new ArrayList<Room>();
	private Room room;
	private Judge judge;
	private int scorePool = 0;

	public Game() {
		this.room = new Room();
		this.judge = new Judge();
	}

	public Room makeRoom(int roomNumber) {
		this.room = new Room(roomNumber);
		roomList.add(room);
		return room;
	}

	public Room joinRoom(int roomNumber) {
		this.room = getRoomByRoomNumber(roomNumber);
		return room;
	}

	public void removeRoom(Room room) {
		roomList.remove(roomList.indexOf(room));
	}

	public void begin() {

	}

	public void setup() {
		room.setHand();
	}

	public User judge(Room room) {
		User winner = judge.judgeGame(room.getUserList(), room.getScoreCard());
		if (winner != null) {
			if (room.isAddScoreFlg()) {
				winner.addScore(room.getScoreCard() + scorePool);
				room.setAddScoreFlg(false);
				System.out.println("スコアをプラスしたよ");
			}
			scorePool = 0;
		} else {
			scorePool += room.getScoreCard();
		}
		return winner;
	}

	public ArrayList<User> win(Room room) {
		return judge.win(room.getUserList());
	}

	public boolean isEnd() {
		return room.isEnd();
	}

	public Room getRoomByRoomNumber(int roomNumber) {
		for (Room room : this.roomList) {
			if (room.getRoomNumber() == roomNumber) {
				return room;
			}
		}
		return null;
	}
}
