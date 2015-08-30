package game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputClass {

	/**
	 * Getting an integer input from user which is in the range of 0 - 10
	 * 
	 * @return integer in the range of 0 to 10
	 */
	public static int inputInt() {
		Scanner input = null;
		int number = 0;
		try {
			input = new Scanner(System.in);
			boolean isOk = false;
			while (!isOk) {
				isOk = true;
				number = input.nextInt();
				if (number < 0 || number > 9) {
					System.out.println("Try again: ");
					isOk = false;
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("Wrong input, you need to enter an integer, try again: ");
			inputInt();
		}

		return number;
	}
	
	public static char inputChar(){
		Scanner input = null;
		char character = ' ';
		try {
			input = new Scanner(System.in);
			boolean isOk = false;
			while (!isOk) {
				isOk = true;
				character = input.next().charAt(0);
				character = Character.toUpperCase(character);
				if (character != 'H' && character != 'V') {
					System.out.println("Try again: ");
					isOk = false;
				}
			}
		} catch (Exception e) {
			System.out.println("Wrong input, try again: ");
			inputChar();
		}

		return character;
	}
}
