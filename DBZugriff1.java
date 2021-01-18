package giu14;



/*
 * Programm DBZugriff1
 * Das Programm kann als Schablone für den Datenbankzugriff verwendet werden.
 * Das Programm fragt Buchdaten (Id und Titel) aus der mysql-Datenbank xxx ab.
 * Die Daten werden zeilenweise in der Console ausgegeben.
 *  
 * @author Hans-Peter Habelitz
 * @date 2011-12-28
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBZugriff1 {
    static String connectURL = "jdbc:mysql://3306/login";
    static String user = "root";
    static String password = "";
    static Connection conn;

    public static void main(String Args[]) {
	try {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch (Exception e) {
	    System.err.println("Treiber konnte nicht geladen werden.");
	    System.err.println(e);
	    e.printStackTrace();
	    System.exit(-1);
	}
	System.out.println("Treiber wurde geladen.");
	try {
	    conn = DriverManager.getConnection(connectURL, user, password);
	    System.out.println("Verbindung aufgebaut.");
	    Statement stmt = conn.createStatement();
	    // Datenabfrage aufbereiten
	    String query = "SELECT ID, Titel FROM Buecher";
	    // Schritt 3: Datenabfrage ausführen
	    ResultSet rs = stmt.executeQuery(query);
	    // Schritt 4: Ergebnismenge verarbeiten
	    System.out.println("ID\t\tTitel");
	    System.out
		    .println("-----------------------------------------------");
	    while (rs.next()) {
		System.out.println(rs.getString("ID") + "\t"
			+ rs.getString("Titel"));
	    }
	    // ResultSet und Statement schließen
	    rs.close();
	    stmt.close();
	} catch (SQLException e) {
	    System.err.println("Keine Verbindung möglich.");
	    e.printStackTrace();
	    System.err.println("SQLExecption: " + e.getMessage());
	    System.err.println("SQLState: " + e.getSQLState());
	    System.err.println("VendorError: " + e.getErrorCode());
	    System.exit(-1);
	} finally {
	    // Verbindung schließen
	    if (conn != null) {
		try {
		    conn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
