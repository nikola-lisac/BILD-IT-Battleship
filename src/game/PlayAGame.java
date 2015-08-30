package game;

public class PlayAGame {

	/**
	 * Playing the game
	 */
	public static void newGame(){
		
		Battleship game = new Battleship();
		
		game.initBoards(); // initializing boards
		game.insertComputerShips(); // inserting computer's ships on random positions
		game.usersShipsPlacements(); // placing user's ships
		System.out.println("\n\t*** The game is starting! ***");
		game.printBoards(); // printing user's board with ships, and his hits and misses board
		
		// until the game ends (game ends if we have a winner)
		while(!game.gameOver()){
			
			// user's shot on computer's ships
			int[] usersShot = game.usersMove();
			// marking user's hit or miss
			game.markUsersHitOrMiss(usersShot);
			// printing boards
			game.printBoards();
			
			// checking if the user won
			if(game.userWon()){
				break;
			}
			// computer's shot on user's ships
			int[] computersShot = game.computersMove();
			// marking computer's hit or miss
			game.markComputersHitOrMiss(computersShot);
		}
		
		// print message of the winner
		if(game.userWon()){
			System.out.println("-------------------------------------");
			System.out.println("Congratulations, you won!");
		}
		else {
			System.out.println("-------------------------------------");
			System.out.println("The enemy won, better luck next time!");
		}
	}

	/**
	 * printing game header
	 */
	public static void printHeader(){
		System.out.println("*************************");
		System.out.println("*\tBattleships v1 \t*");
		System.out.println("*************************");
		System.out.println();
	}
	public static void main(String[] args) {
		
		printHeader(); // print game header
		newGame(); // start new game
	}

}
