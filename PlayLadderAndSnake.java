//----------------------------------------
// Assignment2
// Part2
// Written by: Linlin Xie (id:2343895)
//----------------------------------------

package assignment2;

import java.util.Scanner;

public class PlayLadderAndSnake {

	public static void main(String[] args) {
		
		int numOfPlayers = 0, attemptNum = 0;
		Scanner kb = new Scanner(System.in);
		while(numOfPlayers<2 || numOfPlayers>4){
			System.out.print("Please enter the players for your game - Number must be between 2 and 4 inclusively: ");
			numOfPlayers = kb.nextInt();
			attemptNum++;
			if(attemptNum>=1 && attemptNum<=3) {
				System.out.println("Bad Attempt "+attemptNum+" -Invalid number of players. ");
			}
			if(attemptNum == 4) {
				System.out.print("Bad Attempt! You have exhausted all your chances. Program will terminate!");
				System.exit(0);
			}
		}
		
		LadderAndSnake las= new LadderAndSnake(numOfPlayers);
		System.out.println("Game is played by "+las.getNumOfPlayers()+" players");
		System.out.println("===================== Game Start ===================");
		System.out.println();
		System.out.println("Now deciding which player will start playing");
		las.play(las.playOrder(las.getNumOfPlayers()));
		

	}

}
