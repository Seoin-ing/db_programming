package hi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

class DB_conn {
	Connection con = null;

	public DB_conn() {
		String url = "jdbc:oracle:thin:@eventadmin.kro.kr:1521:orcl";
		String id = "event18";
		String password = "1234";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("No Driver.");
		} catch (SQLException e) {
			System.out.println("Connection Fail");
		}
	}

	public void sqlrun(DefaultTableModel model) {
		String query = "select EVENT_NO, EVENT_NAME, EVENT_TYPE, EVENT_COMM from EVENT";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String row[] = new String[4];
			while (rs.next()) {
				row[0] = Integer.toString(rs.getInt(1));
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				model.addRow(row);
			}
			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
