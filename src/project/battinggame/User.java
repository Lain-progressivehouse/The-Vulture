package project.battinggame;

import java.util.ArrayList;

public class User {
	private String name;
	private int score = 0;
	private ArrayList<Integer> handList = new ArrayList<Integer>();
	private int choiseCard;
	private boolean flg;

	/**
	 * userの同期待フラグ
	 * どちらもフラグが立ったら処理を続ける
	 */
	private boolean syncFlg = false;

	/**
	 * updateでユーザ情報を送って更新する必要があるかどうかのフラグ
	 */
	private boolean updateUserInfoFlg = true;

	/**
	 * 部屋から退出してるかどうか
	 */
	private boolean leavingFlg = false;

	public User(String name) {
		setName(name);
	}

	public User() {

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public void setHandList() {
		for (int i = 1; i < 16; i++) {
			handList.add(i);
		}
	}

	public void setChoiseCard(int cardNumber) {
		this.flg = true;
		handList.remove(handList.indexOf(cardNumber));
		this.choiseCard = cardNumber;
	}

	public int getChoiseCard() {
		return this.choiseCard;
	}

	public void setFlg() {
		this.flg = false;
	}

	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}

	public ArrayList<Integer> getHandList() {
		return this.handList;
	}

	public boolean isChoise() {
		return flg;
	}

	public boolean isUpdateUserInfoFlg() {
		return updateUserInfoFlg;
	}

	public void setUpdateUserInfoFlg(boolean updateUserInfoFlg) {
		this.updateUserInfoFlg = updateUserInfoFlg;
	}

	public boolean isSyncFlg() {
		return syncFlg;
	}

	public void setSyncFlg(boolean syncFlg) {
		this.syncFlg = syncFlg;
	}

	public boolean isLeavingFlg() {
		return leavingFlg;
	}

	public void setLeavingFlg(boolean leavingFlg) {
		this.leavingFlg = leavingFlg;
	}

}
