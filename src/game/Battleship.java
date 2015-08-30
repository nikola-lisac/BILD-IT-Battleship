package game;

/**
 * Classic console game of battleship
 * 
 * @author Nikola Lisicic
 *
 */
public class Battleship {

	private static final int SIZE = 10; // number of rows and number of columns on the boards
	private char[][] computerBoard; // computer's board
	private char[][] computersHitsAndMissesBoard;
	private char[][] usersHitsAndMissesBoard; // computer's board with hidden locations of the chips
	private char[][] userBoard; // user's board

	private int userHitCount; // user's counter of hits
	private int computerHitCount; // computer's counter of hits
	private final char HIT = 'H';
	private final char MISS = 'X';

	/** default constructor */
	Battleship() {
		// setting sizes of the boards to 10 by 10's
		computerBoard = new char[SIZE][SIZE];
		userBoard = new char[SIZE][SIZE];
		usersHitsAndMissesBoard = new char[SIZE][SIZE];
		computersHitsAndMissesBoard = new char[SIZE][SIZE];
		// setting user's and computer's hit count to 0
		userHitCount = 0;
		computerHitCount = 0;
	}

	/**
	 * Initialize boards Fill computer's board and user's board with spaces
	 */
	public void initBoards() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				computerBoard[i][j] = ' ';
				userBoard[i][j] = ' ';
				usersHitsAndMissesBoard[i][j] = ' ';
				computersHitsAndMissesBoard[i][j] = ' ';
			}
		}
	}

	/**
	 * Printing computer's board and user's board side by side
	 */
	public void printBoards() {
		System.out.println();
		System.out.println("\t     Computers's board\t\t\t\t\t\tUser's board");
		System.out.println(
				"--------------------------------------------------------------------------------------------------");

		for (int i = 0; i < SIZE; i++) {

			// print row for display board
			for (int j = 0; j < SIZE; j++) {
				System.out.print(" | " + usersHitsAndMissesBoard[i][j]);
			}
			// print end of the display row "|" and asterisk between the boards
			System.out.print(" |       *      ");

			// print row for user's board
			for (int k = 0; k < SIZE; k++) {
				System.out.print(" | " + userBoard[i][k]);
			}
			// print end of the user's board row "|"
			System.out.print(" |");
			System.out.println();
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------");

	}

	/**
	 * Place the computer's ships on random positions on the board
	 */
	public void insertComputerShips() {
		// ship legths go from 5 to 2
		int shipLength = 5;
		// loop until every ship is placed
		do {
			// horizontal or vertical placement of the ship
			// if it's 0 - horizontal, if it's 1 vertical
			int horizontalOrVertical = (int) (Math.random() * 2);

			// if the placement is horizontal
			if (horizontalOrVertical == 0) {

				boolean isFree = false;
				int i = 0;
				int j = 0;

				// loop until the available row for ship to be placed in is
				// found
				while (!isFree) {
					isFree = true;
					i = (int) (Math.random() * SIZE); // random row index
					j = (int) (Math.random() * (SIZE - shipLength)); // random
																		// column
																		// which
																		// must
																		// have
																		// enough
																		// room
																		// to
																		// place
																		// the
																		// ship

					// check if there is enough space in the row to place the
					// ship
					// if there isn't loop again
					for (int k = j; k < j + shipLength; k++) {
						if (computerBoard[i][k] != ' ') {
							isFree = false;
						}
					}

				}

				// place the ship on the board in the available row
				for (int k = j; k < j + shipLength; k++) {
					computerBoard[i][k] = 'S';

				}

			}

			// if position is vertical, place ship in the random available
			// column
			else {
				boolean isFree = false;
				int i = 0;
				int j = 0;

				// loop until the available column for ship to be placed in is
				// found
				while (!isFree) {
					isFree = true;
					j = (int) (Math.random() * SIZE); // random column index
					i = (int) (Math.random() * (SIZE - shipLength)); // random
																		// row
																		// index
																		// which
																		// must
																		// have
																		// enough
																		// room
																		// to
																		// place
																		// the
																		// ship

					// check if there is free space to place the ship
					// if there isn't loop again
					for (int k = i; k < i + shipLength; k++) {
						if (computerBoard[k][j] != ' ') {
							isFree = false;
						}
					}

				}

				// place the ship in the found column
				for (int k = i; k < i + shipLength; k++) {
					computerBoard[k][j] = 'S';
				}
			}
			// decrement the ship length
			shipLength--;
		} while (shipLength > 1);
	}
	
	/**
	 * User is placing his ships by entering the direction 
	 * and indices of the row and column where he want the ship to start
	 */
	public void usersShipsPlacements() {
		System.out.println("Place the ships wherever you want, game is starting as soon as you're done.");
		int shipLength = 5;
		do {
			System.out.println("Placing the ship with the length: " + shipLength);
			System.out.println("Enter the direction of the placement (H - horizontal, V - vertical): ");
			char direction = InputClass.inputChar(); // getting direction character
			
			//if the direction is horizontal
			if (direction == 'H') {
				int i = 0;
				int j = 0;
				boolean isFree = false;
				
				// loop until the position the user enters is available for placing the ship
				while (!isFree) {
					isFree = true;
					boolean isOk = false;
					
					// loop until the user enters the position where the ship can be placed if the slots aren't occupied
					while (!isOk) {
						isOk = true;
						
						System.out.print("Enter row number (0 - 9): ");
						i = InputClass.inputInt(); // getting an integer from protected input nethod
						System.out.print("Enter column number (0 - " + (SIZE - shipLength) + "): ");
						j = InputClass.inputInt(); // getting an integer from protected input nethod
						
						// if the entered column index is bigger than this value, the ship is out of bounds
						if (j > SIZE - shipLength) {
							System.out.println("There isn't enough available space to place the ship.");
							System.out.println("Try again: ");
							System.out.println("Placing the ship with the length: " + shipLength);
							isOk = false;
						}

					}
					
					// check if there is available space in the row to place the
					// ship
					// if there isn't loop again
					for (int k = j; k < j + shipLength; k++) {
						if (userBoard[i][k] != ' ') {
							System.out.println("One or more spaces on that position are occupied by other ship.");
							System.out.println("Try again: ");
							System.out.println("Placing the ship with the length: " + shipLength);

							isFree = false;
							break;
						}
					}

				}

				// place the ship on the available spaces
				for (int k = j; k < j + shipLength; k++) {
					userBoard[i][k] = 'S';

				}
			}
			
			// if direction is vertical
			else {
				int i = 0; // column number
				int j = 0; // row number
				boolean isFree = false;
				
				// loop until the user enters the position where the ship can be placed if the slots aren't occupied
				while (!isFree) {
					isFree = true;
					boolean isOk = false;
					
					// loop until the user enters the position where the ship can be placed if the slots aren't occupied
					while (!isOk) {
						isOk = true;
						
						System.out.print("Enter column number (0 - 9): ");
						i = InputClass.inputInt(); // getting an integer from keyboard
						System.out.print("Enter row number (0 - "+(SIZE-shipLength)+"): ");
						j = InputClass.inputInt();

						// if entered row index is greater than this value, the ship is out of bounds of the board
						if (j > SIZE - shipLength) {
							System.out.println("There isn't enough available space to place the ship.");
							System.out.println("Try again: ");
							System.out.println("Placing the ship with the length: " + shipLength);
							isOk = false;
						}
					}
					
					// checking if the entered position is free to place the ship
					for (int k = j; k < j + shipLength; k++) {
						if (userBoard[k][i] != ' ') {
							isFree = false;
							System.out.println("One or more spaces on that position are occupied by other ship.");
							System.out.println("Try again: ");
							System.out.println("Placing the ship with the length: " + shipLength);
							break;
						}
					}

				}

				// place the ship in the found column
				for (int k = j; k < j + shipLength; k++) {
					userBoard[k][i] = 'S';
				}
			}
			System.out.println("The ship has been placed.");
			printUserBoard(); // printing the board so the user can see where did he put his ship
			shipLength--;

		} while (shipLength > 1);

	}

	/**
	 * Generate random position coordinates for computers move
	 * 
	 * @return array with index of the row, and index of the column
	 */
	public int[] computersMove() {
		int row = 0;
		int column = 0;
		boolean isOk = false;
		// loop until the position that hasn't been played is found
		while (!isOk) {
			isOk = true;
			row = (int) (Math.random() * SIZE);
			column = (int) (Math.random() * SIZE);
			// if computer already played that position, generate other position
			if (computersHitsAndMissesBoard[row][column] == HIT || computersHitsAndMissesBoard[row][column] == MISS) {
				isOk = false;
			}
		}
		// return position
		return new int[] { row, column };
	}

	/**
	 * User is entering coordinates where he wants to shoot if he already missed
	 * or hit entered position, he must try again
	 * 
	 * @return array with number of row and column where user wants to shoot
	 */
	public int[] usersMove() {
		int row = 0;
		int column = 0;
		boolean isOk = false;

		// loop until the user enters the position which he hasn't been played
		// yet
		while (!isOk) {
			isOk = true;
			System.out.println("Enter the number of row where you want to shoot: ");
			row = InputClass.inputInt();
			System.out.println("Enter the number of column where you want to shoot: ");
			column = InputClass.inputInt();

			// if entered position has been played, ask to try again
			if (usersHitsAndMissesBoard[row][column] == HIT || usersHitsAndMissesBoard[row][column] == MISS) {
				System.out.println("You already shot at that position.");
				System.out.println("Try again: ");
				isOk = false;
			}
		}

		return new int[] { row, column };
	}

	/**
	 * Marking the computer's hits and misses board with a hit or a miss
	 * depending on the user's ships locations
	 * 
	 * @param position
	 *            array with number of row and column to mark a hit or a miss
	 */
	public void markComputersHitOrMiss(int[] position) {
		int row = position[0];
		int column = position[1];

		// if user has a part of the ship on the location with indices row and
		// column
		// mark a hit and increase the count of computer's hits
		if (userBoard[row][column] == 'S') {
			System.out.println("The enemy hit your ship on the position: " + row + " " + column);
			computersHitsAndMissesBoard[row][column] = HIT;
			computerHitCount++;
		}
		// mark a miss
		else {
			System.out.println("Enemy missed your ship!");
			computersHitsAndMissesBoard[row][column] = MISS;
		}
	}

	/**
	 * Marking the user's hits and misses board with a hit or a miss depending
	 * on the computer's ships locations
	 * 
	 * @param position
	 *            array with number of row and column to mark a hit or a miss
	 */
	public void markUsersHitOrMiss(int[] position) {
		int row = position[0];
		int column = position[1];

		// if the computer has a part of the ship on the location with indices
		// row and column
		// mark a hit and increase the count of user's hits
		if (computerBoard[row][column] == 'S') {
			System.out.println("\n** Good job! You hit the enemy ship! **");
			usersHitsAndMissesBoard[row][column] = HIT;
			userHitCount++;
		}
		// mark a miss
		else {
			System.out.println("\n** You missed the enemy ship! **");
			usersHitsAndMissesBoard[row][column] = MISS;
		}
	}

	/**
	 * The game is over if either user or computer won the game
	 * 
	 * @return true if someone won, otherwise false
	 */
	public boolean gameOver() {
		if (userWon() || computerWon()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * If the user has 14 hits, that is if he sank all the computer's ships, he
	 * won
	 * 
	 * @return true if user won, otherwise false
	 */
	public boolean userWon() {
		if (userHitCount == 14) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * If the computer has 14 hits, he sank all user's ships and he won
	 * 
	 * @return true if computer won, otherwise false
	 */
	public boolean computerWon() {
		if (computerHitCount == 14) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Printing the user's board
	 */
	public void printUserBoard() {
		System.out.println();
		for (int i = 0; i < SIZE; i++) {
			for (int k = 0; k < SIZE; k++) {
				System.out.print(" | " + userBoard[i][k]);
			}
			System.out.print(" |");
			System.out.println();
		}
	}
}
