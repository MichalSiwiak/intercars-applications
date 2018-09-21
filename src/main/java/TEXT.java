import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TEXT {

	private static BufferedReader br;

	public static void main(String[] argv) throws IOException {

		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5500/step", "step",
					"step");

			final String FILENAME = "C:\\oleje.txt";
			br = new BufferedReader(new FileReader(FILENAME));
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				
				PreparedStatement preparedStatement = null;
				
				String string = "ktokolwiek";

				String insertTableSQL = "insert into skus(sku) values ('"+sCurrentLine+"')";
				preparedStatement = connection.prepareStatement(insertTableSQL);
				preparedStatement.executeUpdate();

				System.out.println("Record is inserted into DBUSER table!");
				
				System.out.println(sCurrentLine);
			}

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
			
		}
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

}