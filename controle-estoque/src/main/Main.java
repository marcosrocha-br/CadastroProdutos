package main;

import java.util.Scanner;

import dao.DaoControl;

/**
 * @author Marcos Rocha
 */

public class Main {

//	Method to interact with the program
	public static String action() {
		Scanner input = new Scanner(System.in);
		String response;

		System.out.println("\tSELECT AN ACTION");
		System.out.println("(l) List\t(i) Insert\n(u) Update\t(d) Delete\n(e) Exit");
		System.out.printf("Action: ");
		response = input.next();

		return response;
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		DaoControl dao = new DaoControl();

		String R, name, content;
		int id, amount;
		float price;

		System.out.println("\tSTOCK CONTROL\n");
		R = action();

		while (!R.equals("e")) {
			switch (R) {
			case "l":// SELECT
				System.out.printf("\n%-2s\t%-15s\t%-15S\t%-15S\t%S\n", "ID", "NAME", "AMOUNT", "UNITARY PRICE",
						"FULL PRICE");
				dao.getProdutos();
				break;
			case "i":// INSERT
				input.nextLine();// buffer cleaning

				System.out.println("INSERT PRODUCT");

				System.out.print("NAME: ");
				name = input.nextLine();

				System.out.print("AMOUNT: ");
				amount = input.nextInt();

				System.out.print("PRICE: R$ ");
				price = input.nextFloat();

				dao.setInserir(name, amount, price);

				break;
			case "u":// UPDATE
				System.out.print("ID: ");
				id = input.nextInt();
				System.out.print("\nUpdate:\n\t(n)name\t(a)amount\t(p)price\nAction: ");
				content = input.next();

				switch (content) {
				case "n":
					input.nextLine();// buffer cleaning

					System.out.print("Name: ");
					name = input.nextLine();
					dao.setNome(id, name);
					break;
				case "a":
					input.nextLine();// buffer cleaning

					System.out.print("Amount: ");
					amount = input.nextInt();
					dao.setQuantidade(id, amount);
					break;
				case "p":
					input.nextLine();// buffer cleaning

					System.out.print("Price: ");
					price = input.nextFloat();
					dao.setValor(id, price);
					break;
				default:
					System.out.println("Type a corresponding letter!");
				}// END SWITCH

				break;
			case "d":// DELETE
				System.out.println("DELETE PRODUCT");
				System.out.print("ID: ");
				id = input.nextInt();
				dao.setDelete(id);
				break;
			default:
				System.out.println("Type a corresponding letter!");
			}// END SWITCH

			System.out.println("\n|l|i|u|d|e|");
			System.out.print("Action: ");
			R = input.next();

		} // END WHILE

		System.out.println("\t\tProgram Finished!\n");

	}
}
