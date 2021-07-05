package com.te.student;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class StaticCrud {

	static Scanner scanner = new Scanner(System.in);

	public static void staticCrud() {
		System.out.println("1. Add");
		System.out.println("2. Read");
		System.out.println("3. Update");
		System.out.println("4. Delete");
		System.out.println("5. Exit");
		System.out.println("Enter your choice : ");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			add();
			break;
		case 2:
			read();
			break;
		case 3:
			update();
			break;
		case 4:
			delete();
			break;
		case 5:
			System.exit(0);
			break;
		default:
			System.out.println("invalid choice!!");
		}
		scanner.close();
	}

	public static void add() {
		Connection connection = null;
		Statement statement = null;

		try {
			FileInputStream fileInputStream = new FileInputStream("dbInfo.properties");
			Properties properties = new Properties();

			properties.load(fileInputStream);
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("dbUrl"), "root", "root");
			String query = "insert into student values (10,'akash',76.8,'cholanagar')";
			statement = connection.createStatement();
			int result = statement.executeUpdate(query);

			if (result != 0) {
				System.out.println(result + " rows affected");
				System.out.println("inserted succesfully!!");
			} else {
				System.out.println("unable to insert!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void read() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			FileInputStream fileInputStream = new FileInputStream("dbInfo.properties");
			Properties properties = new Properties();

			properties.load(fileInputStream);
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("dbUrl"), "root", "root");
			String query = "select * from student";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				System.out.println(" Id = " + resultSet.getInt(1));
				System.out.println(" Name = " + resultSet.getString(2));
				System.out.println(" Marks = " + resultSet.getDouble(3));
				System.out.println(" Address = " + resultSet.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void update() {
		Connection connection = null;
		Statement statement = null;

		try {
			FileInputStream fileInputStream = new FileInputStream("dbInfo.properties");
			Properties properties = new Properties();

			properties.load(fileInputStream);
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("dbUrl"), "root", "root");

			String query = "update student set name='Soji' where id=1";
			statement = connection.createStatement();
			int result = statement.executeUpdate(query);

			if (result != 0) {
				System.out.println(result + " rows affected");
				System.out.println("updated succesfully!!");
			} else {
				System.out.println("unable to update!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void delete() {
		Connection connection = null;
		Statement statement = null;

		try {
			FileInputStream fileInputStream = new FileInputStream("dbInfo.properties");
			Properties properties = new Properties();

			properties.load(fileInputStream);
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("dbUrl"), "root", "root");

			String query = "delete from student where id=2";
			statement = connection.createStatement();
			int result = statement.executeUpdate(query);

			if (result != 0) {
				System.out.println(result + " rows affected");
				System.out.println("deleted succesfully!!");
			} else {
				System.out.println("unable to delete!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
