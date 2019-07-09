package project.battinggame;

import java.util.ArrayList;
import java.util.Collections;

public class Judge {

	// ゲームの勝敗を決める．
	public ArrayList<User> win(ArrayList<User> userList) {
		int maxScore = -100;
		ArrayList<User> winnerList = new ArrayList<User>();
		for (User user : userList) {
			if (maxScore < user.getScore()) {
				maxScore = user.getScore();
				winnerList.clear();
				winnerList.add(user);
			} else if (maxScore == user.getScore()) {
				winnerList.add(user);
			}
		}

		return winnerList;
	}

	// スコアカードを受け取る人を決める．
	public User judgeGame(ArrayList<User> userList, int scoreCard) {
		int max = -100;
		int min = 100;
		int count = 0;
		ArrayList<Integer> choiseCardList = new ArrayList<Integer>();
		User ret = null;

		for (User user : userList) {
			choiseCardList.add(user.getChoiseCard());
		}
		Collections.sort(choiseCardList);
		if (scoreCard < 0) {
			max = choiseCardList.get(0);

			for (int i = 1; i < choiseCardList.size(); i++) {
				if (max > choiseCardList.get(i) && count == 0) {
					break;
				} else if (max == choiseCardList.get(i)) {
					count++;
				} else if (count != 0) {
					max = choiseCardList.get(i);
					count = 0;
				}
			}

			if (count == 0) {
				for (User user : userList) {
					if (max == user.getChoiseCard()) {
						ret = user;
						break;
					}
				}
			}

		} else {
			Collections.reverse(choiseCardList);
			min = choiseCardList.get(0);

			for (int i = 1; i < choiseCardList.size(); i++) {
				if (min < choiseCardList.get(i) && count == 0) {
					break;
				} else if (min == choiseCardList.get(i)) {
					count++;
				} else if (count != 0) {
					min = choiseCardList.get(i);
					count = 0;
				}
			}

			if (count == 0) {
				for (User user : userList) {
					if (min == user.getChoiseCard()) {
						ret = user;
						break;
					}
				}
			}
		}
		return ret;
	}
}
