//----------------------------------------
// Assignment2
// Part1
// Written by: Linlin Xie (id:2343895)
//----------------------------------------

package assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LadderAndSnake {

	private int[][] board;
	private int numOfPlayers;

	public LadderAndSnake(int numOfPlayers) {

		this.board = new int[10][10];
		this.numOfPlayers = numOfPlayers;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public int getNumOfPlayers() {
		return numOfPlayers;
	}

	public void setNumOfPlayers(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}

	@Override
	public String toString() {
		return "LadderAndSnake [board=" + Arrays.toString(board) + ", numOfPlayers=" + numOfPlayers + "]";
	}
	
	// generate random numbers 1-6
	public static int flipDice() {
		return (int) (Math.random() * 6 + 1);
	}

	// generate final order for the players
	public List<Map.Entry<Integer, Integer>> playOrder(int numOfPlayers) {
		HashMap<Integer, Integer> playersAndOrder = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicateDice = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> clone_playersAndOrder;
		List<Map.Entry<Integer, Integer>> playersAndOrderList = new ArrayList<>();
		List<Map.Entry<Integer, Integer>> new_playersAndOrderList = new ArrayList<>();

		// initialize the players with dices
		for (int i = 1; i <= numOfPlayers; i++) {
			int dice = LadderAndSnake.flipDice();
			System.out.println("Player " + i + " got a dice value of " + dice);
			playersAndOrder.put(i, dice);
		}
		System.out.println();

		// find the duplicate dice numbers and move them from the original order to a new hashmap
		duplicateDice = (HashMap<Integer, Integer>) playersAndOrder.clone();
		while (duplicateDice.size() != 0) {
			playersAndOrder = (HashMap<Integer, Integer>) duplicateDice.clone();
			duplicateDice.clear();
			clone_playersAndOrder = (HashMap<Integer, Integer>) playersAndOrder.clone();
			Object[] keys = clone_playersAndOrder.keySet().toArray();
			for (int i = 0; i < keys.length; i++) {
				Integer value = (Integer) clone_playersAndOrder.get(keys[i]);
				clone_playersAndOrder.remove(keys[i]);
				if (clone_playersAndOrder.containsValue(value)) {
					duplicateDice.put((Integer) keys[i], value);
					playersAndOrder.remove(keys[i]);
				}
				if (duplicateDice.containsValue(value)) {
					duplicateDice.put((Integer) keys[i], value);
					playersAndOrder.remove(keys[i]);
				}
			}

			// sort the players without repeated dice numbers from large dice to small and put them in the new order list
			playersAndOrderList.addAll(playersAndOrder.entrySet());
			Collections.sort(playersAndOrderList, new Comparator<Map.Entry<Integer, Integer>>() {
				@Override
				public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
			});
			for (Map.Entry<Integer, Integer> entry : playersAndOrderList) {
				new_playersAndOrderList.add(entry);
			}
			playersAndOrderList.clear();

			// get new dice number for the players with repeated dice number
			if (duplicateDice.size() != 0) {
				Iterator<Integer> iterator = duplicateDice.keySet().iterator();
				System.out.print("A tie was achieved between ");
				while (iterator.hasNext()) {
					System.out.print("play " + iterator.next() + " and ");
				}
				System.out.println();
				System.out.println("Attempting to break the tie ");
				Iterator<Integer> iterator2 = duplicateDice.keySet().iterator();
				while (iterator2.hasNext()) {
					Integer key = iterator2.next();
					int dice = LadderAndSnake.flipDice();
					System.out.println("Player " + key + " got a dice value of " + dice);
					duplicateDice.put(key, dice);
				}
				System.out.println();
			}
		}

		// print the final order
		System.out.print("Reached final decision on order of playing: ");
		System.out.println();
		for (Map.Entry<Integer, Integer> entry : new_playersAndOrderList) {
			System.out.print("player " + entry.getKey() + ", ");
		}
		System.out.println();
		System.out.println();
		return new_playersAndOrderList;

	}

	// playing process according to the rules
	public void play(List<Map.Entry<Integer, Integer>> new_playersAndOrderList) {

		for (Map.Entry<Integer, Integer> entry : new_playersAndOrderList) {
			entry.setValue(0);
		}

		boolean flag = true;
		int totalDice;
		while (flag) {
			for (Map.Entry<Integer, Integer> entry : new_playersAndOrderList) {

				int dice = LadderAndSnake.flipDice();
				int afterChangeTotalDice = 0;
				totalDice = entry.getValue() + dice;
				if (totalDice == 1)
					afterChangeTotalDice = 38;
				if (totalDice == 4)
					afterChangeTotalDice = 14;
				if (totalDice == 9)
					afterChangeTotalDice = 31;
				if (totalDice == 16)
					afterChangeTotalDice = 6;
				if (totalDice == 21)
					afterChangeTotalDice = 42;
				if (totalDice == 28)
					afterChangeTotalDice = 84;
				if (totalDice == 48)
					afterChangeTotalDice = 30;
				if (totalDice == 51)
					afterChangeTotalDice = 67;
				if (totalDice == 62)
					afterChangeTotalDice = 19;
				if (totalDice == 63)
					afterChangeTotalDice = 60;
				if (totalDice == 64)
					afterChangeTotalDice = 60;
				if (totalDice == 71)
					afterChangeTotalDice = 91;
				if (totalDice == 80)
					afterChangeTotalDice = 100;
				if (totalDice == 86)
					afterChangeTotalDice = 24;
				if (totalDice == 87)
					afterChangeTotalDice = 24;
				if (totalDice == 93)
					afterChangeTotalDice = 68;
				if (totalDice == 94)
					afterChangeTotalDice = 24;
				if (totalDice == 95)
					afterChangeTotalDice = 24;
				if (totalDice == 97)
					afterChangeTotalDice = 76;
				if (totalDice == 98)
					afterChangeTotalDice = 78;
				if (totalDice > 100)
					afterChangeTotalDice = 100 - (totalDice - 100);

				if (afterChangeTotalDice == 0) {
					entry.setValue(totalDice);
					System.out.println(
							"Play " + entry.getKey() + " got a dice value of " + dice + "; now in square " + totalDice);
				} else if (afterChangeTotalDice > totalDice) {
					entry.setValue(afterChangeTotalDice);
					System.out.println("Play " + entry.getKey() + " got a dice value of " + dice + "; gone to square "
							+ totalDice + " ,then up to square " + afterChangeTotalDice);
				} else if (afterChangeTotalDice < totalDice) {
					entry.setValue(afterChangeTotalDice);
					System.out.println("Play " + entry.getKey() + " got a dice value of " + dice + "; gone to square "
							+ totalDice + " ,then down to square " + afterChangeTotalDice);
				}

				if (totalDice == 100 || afterChangeTotalDice == 100) {
					System.out.println("Play " + entry.getKey() + " wins!!!");
					System.out.println("===================== Game Over ===================");
					flag = false;
					break;
				}
			}
			if (flag == true) {
				System.out.println("Game not over; flipping again ");
				System.out.println(" ");
			}

		}

	}

}
