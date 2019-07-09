package project.battinggame;

import java.util.ArrayList;
import java.util.Collections;

public class Room {
	private int roomNumber;
	private ArrayList<User> userList = new ArrayList<User>();
	private ArrayList<Integer> scoreCardList = new ArrayList<Integer>();
	private int scoreCard;

	/**
	 * 引いたスコアカードのゲームが終わっているかどうか
	 */
	private boolean scoreCardFlg = true;

	/**
	 * 部屋でゲームが開始されているかどうか
	 * 開始されているならtrueになる
	 */
	private boolean startFlg = false;

	/**
	 * スコアを計算しても良いかどうか
	 */
	private boolean addScoreFlg = true;

	public Room(int roomNumber) {
		this.setRoomNumber(roomNumber);
		setScoreCardList();
		shuffle();
	}

	public Room() {

	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setUser(String name) {
		userList.add(new User(name));
	}

	public void addUser(User user) {
		userList.add(user);
	}

	public User getUserByName(String name) {
		for (User user : this.userList) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	public void setHand() {
		for (User user : userList)
			user.setHandList();
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void shuffle() {
		Collections.shuffle(scoreCardList);
	}

	public void setScoreCardList() {
		for (int i = -5; i <= 10; i++) {
			if (i == 0) {
				i++;
			}
			scoreCardList.add(i);
		}
	}

	public int drawScoreCard() {
		this.scoreCard = scoreCardList.get(0);
		scoreCardList.remove(0);
		// ゲームのジャッジが終わるまでfalseにする
		scoreCardFlg = false;
		return this.scoreCard;
	}

	public int getScoreCard() {
		return this.scoreCard;
	}

	public boolean isEnd() {
		if (scoreCardList.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isChoise() {
		for (User user : userList) {
			if (!user.isChoise()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * isStartのセッター
	 * 
	 * @param flag true or false
	 */
	public void setIsStart(boolean flag) {
		startFlg = flag;
	}

	/**
	 * isStartのゲッター
	 * 
	 * @return この部屋のゲームが開始しているかどうか
	 */
	public boolean isStart() {
		return startFlg;
	}

	/**
	 * scoreCardFlgのゲッター
	 * 
	 * @return カードを引いていいかどうか
	 */
	public boolean isScoreCardFlg() {
		return scoreCardFlg;
	}

	public void setScoreCardFlg(boolean scoreCardFlg) {
		this.scoreCardFlg = scoreCardFlg;
	}

	public boolean isAddScoreFlg() {
		return addScoreFlg;
	}

	public void setAddScoreFlg(boolean addScoreFlg) {
		this.addScoreFlg = addScoreFlg;
	}

	/**
	 * 一つでも同期待ち状態になってないならfalse
	 * 
	 * @return 全員同期待ちかどうか
	 */
	public boolean isAllUserSync() {
		boolean flg = true;
		for (User user : userList)
			if (!user.isSyncFlg())
				flg = false;
		return flg;
	}

	/**
	 * 全員部屋から退出してるかどうか
	 * 
	 * @return 全員部屋から退出してるかどうか
	 */
	public boolean isLeaving() {
		boolean flg = true;
		for (User user : userList)
			if (!user.isLeavingFlg())
				flg = false;
		return flg;
	}
}
