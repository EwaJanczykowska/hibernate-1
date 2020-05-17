import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCMain {
	public static void main(String[] args) {
		executeSQL();
	}

	private static void executeSQL() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:school.db", "", "");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM schools";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("School name: " + rs.getString("name"));
				System.out.println("       address: " + rs.getString("address"));
				
				int id = rs.getInt("id");				
				
				Statement stmt2 = conn.createStatement();
				String sql2 = "SELECT * FROM schoolClasses WHERE school_id = " + id;
				ResultSet rs2 = stmt2.executeQuery(sql2);
				while (rs2.next()) {
					System.out.println("       -  Class profile: " + rs2.getString("profile"));
					System.out.println("          current year: " + rs2.getInt("currentYear"));
					System.out.println("          start year: " + rs2.getInt("startYear"));
				}
				rs2.close();
				stmt2.close();
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
