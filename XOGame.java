package xsAndOs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// X's and O's game against the computer
// basic x and o game played in the console against the computer, which randomly picks empty boxes
// Human player enters number between 1-9 to place X in box of their choice on their turn
// Each turn checks if any version of a winning combination has been reached, or if the board is full - meaning there's no winners
public class XOGame {
	// map used to keep track of boxes with keys being their position and values being the X or O
	static Map<Integer, String> boxes = new HashMap<Integer, String>();
	// Integer list to keep track of empty boxes to make sure computer and human select correctly
	static List<Integer> emptyBoxes = new ArrayList<Integer>();
	// scanner used for human selection
	static Scanner scanner;
	// random used for computer selection
	static Random random;
	// booleans to control turns, game status and human choice validation
	static boolean humanTurn, gameActive, validChoice;
	// integer variables to store computer and human choices
	static int computerChoice, humanChoice;

	// main method starts game
	public static void main(String[] args) {
		random = new Random();
		scanner = new Scanner(System.in);

		startGame();
		
		// close scanner at end
		scanner.close();
	}
	
	// start game method sets up array and welcomes player
	public static void startGame() {
		gameActive = true;
		setUpArray();
		System.out.println("Welcome to X's and O's");
		System.out.print("Picking first player");
		loadingDots();
		firstPlayer();
	}
	
	// controls back and forth of human/computer turns
	// calls methods to check if the game has been won or if no more turns are available
	public static void gamePlay() {
		while(gameActive) {
			
			if(humanTurn) {
				humanTurn();
			} else {
				computerTurn();
			}
			displayBoard();
			checkGameComplete();
			checkBoardFull();
		}
	}
	
	// carries out computers random selection
	public static void computerTurn() {
		System.out.println("\nCOMPUTER'S TURN:");
		// get current empty boxes
		getEmptyBoxes();
		// random computer choice
		computerChoice = random.nextInt(emptyBoxes.size());
		boxes.put(emptyBoxes.get(computerChoice), "O");
		
		humanTurn = true;
	}
	
	// carries out human turn
	public static void humanTurn() {
		System.out.println("\nHUMAN'S TURN:");
		System.out.println("Enter a number between 1 and 9 to place an X in the corresponding boxes...");
		// get current empty boxes
		getEmptyBoxes();
		
		// makes sure human enters valid choice
		while(!validChoice) {
			
			// try/catch to validate user entry
			try {
				humanChoice = scanner.nextInt();
			} catch(Exception E) {
				System.out.println("Invalid entry - pick a number between 1-9");
				scanner.nextLine();
			}
			
			if(emptyBoxes.contains(humanChoice)) {
				boxes.put(humanChoice, "X");
				validChoice = true;
			} else {
				System.out.println("Please choose an empty box...");
			}
		}
		
		validChoice = false;
		humanTurn = false;
	}
	
	// checks all versions of a winning combination of boxes for both X and O
	public static void checkGameComplete() {
		if(boxes.get(1) == "X" && boxes.get(2) == "X" && boxes.get(3) == "X"){
			System.out.println("Congratulations, Human Wins!");
			gameActive = false;
		} else if(boxes.get(1) == "O" && boxes.get(2) == "O" && boxes.get(3) == "O"){
			System.out.println("Sorry, Computer Wins.");
			gameActive = false;
		} else if(boxes.get(4) == "X" && boxes.get(5) == "X" && boxes.get(6) == "X"){
			System.out.println("Congratulations, Human Wins!");
			gameActive = false;
		} else if(boxes.get(4) == "O" && boxes.get(5) == "O" && boxes.get(6) == "O"){
			System.out.println("Sorry, Computer Wins.");
			gameActive = false;
		} else if(boxes.get(7) == "X" && boxes.get(8) == "X" && boxes.get(9) == "X"){
			System.out.println("Congratulations, Human Wins!");
			gameActive = false;
		} else if(boxes.get(7) == "O" && boxes.get(8) == "O" && boxes.get(9) == "O"){
			System.out.println("Sorry, Computer Wins.");
			gameActive = false;
		} else if(boxes.get(1) == "X" && boxes.get(5) == "X" && boxes.get(9) == "X"){
			System.out.println("Congratulations, Human Wins!");
			gameActive = false;
		} else if(boxes.get(1) == "O" && boxes.get(5) == "O" && boxes.get(9) == "O"){
			System.out.println("Sorry, Computer Wins.");
			gameActive = false;
		} else if(boxes.get(7) == "X" && boxes.get(5) == "X" && boxes.get(3) == "X"){
			System.out.println("Congratulations, Human Wins!");
			gameActive = false;
		} else if(boxes.get(7) == "O" && boxes.get(5) == "O" && boxes.get(3) == "O"){
			System.out.println("Sorry, Computer Wins.");
			gameActive = false;
		} else if(boxes.get(1) == "X" && boxes.get(4) == "X" && boxes.get(7) == "X"){
			System.out.println("Congratulations, Human Wins!");
			gameActive = false;
		} else if(boxes.get(1) == "O" && boxes.get(4) == "O" && boxes.get(7) == "O"){
			System.out.println("Sorry, Computer Wins.");
			gameActive = false;
		} else if(boxes.get(2) == "X" && boxes.get(5) == "X" && boxes.get(8) == "X"){
			System.out.println("Congratulations, Human Wins!");
			gameActive = false;
		} else if(boxes.get(2) == "O" && boxes.get(5) == "O" && boxes.get(8) == "O"){
			System.out.println("Sorry, Computer Wins.");
			gameActive = false;
		} else if(boxes.get(3) == "X" && boxes.get(6) == "X" && boxes.get(9) == "X"){
			System.out.println("Congratulations, Human Wins!");
			gameActive = false;
		} else if(boxes.get(3) == "O" && boxes.get(6) == "O" && boxes.get(9) == "O"){
			System.out.println("Sorry, Computer Wins.");
			gameActive = false;
		}
	}
	
	// check if the board is full and no more moves can be made
	public static void checkBoardFull() {
		getEmptyBoxes();
		if(emptyBoxes.size()==0) {
			System.out.println("GAME OVER");
			System.out.println("No more available moves");
			gameActive = false;
		}
	}
	
	// gets a list of empty boxes at the point when it's calles
	public static void getEmptyBoxes() {
		// reset empty boxes list
		emptyBoxes.clear();
		// re-add empty boxes
		for(Integer key : boxes.keySet()) {
			if(boxes.get(key) == " ") {
				emptyBoxes.add(key);
			}
		}
	}
	
	// randomly decides the first player
	public static void firstPlayer() {
		if(random.nextInt()%2 == 0) {
			System.out.println("Human goes first");
			displayBoard();
			humanTurn = true;
		}else {
			System.out.println("Computer goes first");
			humanTurn = false;
		}
		// moves to game play method once first player has been picked
		gamePlay();
	}
	
	// sets up boxes array at the beginning of the game, sets all values to empty space strings
	public static void setUpArray() {
		boxes.put(1, " ");
		boxes.put(2, " ");
		boxes.put(3, " ");
		boxes.put(4, " ");
		boxes.put(5, " ");
		boxes.put(6, " ");
		boxes.put(7, " ");
		boxes.put(8, " ");
		boxes.put(9, " ");
	}
	
	// loading dot animation used when picking first player
	public static void loadingDots(){
		for(int i=0; i<3; i++) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.print(".");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}
	
	// displays board layout when called, updates with values of the boxes map
	public static void displayBoard() {	
		// board layout
		System.out.println();
		System.out.println("-------------------");
		System.out.println("|     |     |     |");
		System.out.println("|  "+boxes.get(1)+"  |  "+boxes.get(2)+"  |  "+boxes.get(3)+"  |");
		System.out.println("|     |     |     |");
		System.out.println("-------------------");
		System.out.println("|     |     |     |");
		System.out.println("|  "+boxes.get(4)+"  |  "+boxes.get(5)+"  |  "+boxes.get(6)+"  |");
		System.out.println("|     |     |     |");
		System.out.println("-------------------");
		System.out.println("|     |     |     |");
		System.out.println("|  "+boxes.get(7)+"  |  "+boxes.get(8)+"  |  "+boxes.get(9)+"  |");
		System.out.println("|     |     |     |");
		System.out.println("-------------------");
	}

}
