package com.te.student;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class DynamicCrud {

	static Scanner scanner = new Scanner(System.in);

	public static void dynamicCrud() {
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
	}

	public static void add() {
		System.out.println("Enter the id : ");
		int id = scanner.nextInt();
		System.out.println("Enter the name : ");
		String name = scanner.next();
		System.out.println("Enter the marks : ");
		double marks = scanner.nextDouble();
		System.out.println("Enter the address : ");
		String address = scanner.next();

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			FileInputStream fileInputStream = new FileInputStream("dbInfo.properties");
			Properties properties = new Properties();

			properties.load(fileInputStream);
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("dbUrl"), "root", "root");
			String query = "insert into student values (?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setDouble(3, marks);
			preparedStatement.setString(4, address);

			int result = preparedStatement.executeUpdate();

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
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (scanner != null) {
					scanner.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void read() {
		System.out.println("Enter the id to search : ");
		int searchId = scanner.nextInt();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			FileInputStream fileInputStream = new FileInputStream("dbInfo.properties");
			Properties properties = new Properties();

			properties.load(fileInputStream);
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("dbUrl"), "root", "root");
			String query = "select * from student where id=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, searchId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println(" Id : " + resultSet.getInt(1));
				System.out.println(" Name : " + resultSet.getString(2));
				System.out.println(" Marks : " + resultSet.getDouble(3));
				System.out.println(" Address : " + resultSet.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (scanner != null) {
					scanner.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public static void update() {
		System.out.println("Enter the name to update : ");
		String updateName = scanner.next();

		System.out.println("Enter the id to set : ");
		int id = scanner.nextInt();

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			FileInputStream fileInputStream = new FileInputStream("dbInfo.properties");
			Properties properties = new Properties();

			properties.load(fileInputStream);
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("dbUrl"), "root", "root");
			String query = "update student set name=? where id=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, updateName);
			preparedStatement.setInt(2, id);

			int result = preparedStatement.executeUpdate();

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
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (scanner != null) {
					scanner.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void delete() {
		System.out.println("Enter the id : ");
		int id = scanner.nextInt();

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			FileInputStream fileInputStream = new FileInputStream("dbInfo.properties");
			Properties properties = new Properties();

			properties.load(fileInputStream);
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("dbUrl"), "root", "root");

			String query = "delete from student where id=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, id);

			int result = preparedStatement.executeUpdate();

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
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (scanner != null) {
					scanner.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
