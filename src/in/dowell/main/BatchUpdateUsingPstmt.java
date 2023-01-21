package in.dowell.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.dowell.util.JdbcUtil;

public class BatchUpdateUsingPstmt {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner sc = null;
		try {
			//establish the connection
			connection = JdbcUtil.getConnection();
			String insert = "Insert into Employee(name, age, city)values(?,?,?)";
			if (connection != null)
				pstmt = connection.prepareStatement(insert);
			
			//condition for repeating insertion
			if (pstmt != null) {
				while (true) {
					sc = new Scanner(System.in);
					System.out.print("Enter the Name::");
					String name = sc.next();
					System.out.print("Enter the Age::");
					int age = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter the City::");
					String city = sc.next();
					
					//set the values
					pstmt.setString(1, name);
					pstmt.setInt(2, age);
					pstmt.setString(3, city);
					
					//add to batch
					pstmt.addBatch();
					System.out.print("Do you want to inser[yes/no]::");
					String option = sc.next();
					//condition to stop the loop
					if (option.equalsIgnoreCase("no"))
						break;

				}
			}

			if (pstmt != null)
				//execute the batch
				pstmt.executeBatch();
			System.out.println("Inserted Sucessfully..");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//close the resource
			JdbcUtil.close(connection, pstmt, null);
		}

	}

}
