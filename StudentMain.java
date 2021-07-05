package com.te.student;

import java.util.Scanner;

public class StudentMain {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception{
		while (true) {
			System.out.println("1. static crud");
			System.out.println("2. dynamic crud");
			System.out.println("3. Exit");
			System.out.println("Enter your choice : ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				StaticCrud.staticCrud();
				break;

			case 2:
				DynamicCrud.dynamicCrud();
				break;

			case 3:
				System.exit(0);
				break;	
				
			default:
				System.out.println("invalid choice!!");
			}
		}
	}

}
