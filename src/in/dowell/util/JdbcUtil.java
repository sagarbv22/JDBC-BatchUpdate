package in.dowell.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {

	public static Connection getConnection() {
		Connection connection = null;
		FileInputStream fis = null;
		Properties properties = null;

		try {

			fis = new FileInputStream("Application.properties");

			if (fis != null) {
				properties = new Properties();
				properties.load(fis);
			}

			String url = properties.getProperty("url");
			String userId = properties.getProperty("userId");
			String passWord = properties.getProperty("passWord");

			connection = DriverManager.getConnection(url, userId, passWord);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;

	}

	public static void close(Connection c, Statement s, ResultSet r) {

		try {
			if (c != null)
				c.close();
			if (s != null)
				s.close();
			if (r != null)
				r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
