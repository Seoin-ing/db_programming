package hi;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class DB_Conn_Query3 {
	Connection con = null;

	public DB_Conn_Query3() {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "HMART";
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
		String query = "select 고객아이디, 고객이름, 적립금 from 고객";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String row[] = new String[3];
			while (rs.next()) {
				row[0] = rs.getString("고객아이디");
				row[1] = rs.getString(2);
				row[2] = Integer.toString(rs.getInt(3));
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

class Menu7 extends JFrame {
	// Table 생성
	private String colName[] = { "고객ID", "이름", "적립금" };
	public DefaultTableModel model = new DefaultTableModel(colName, 0);
	// Table에 들어갈 데이터 목록들 (헤더정보, 추가 될 row 개수)
	private JTable table = new JTable(model);
	private JScrollPane jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public Menu7() {
		setTitle("예매 확인 화면"); // 프레임 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 400); // 프레임 크기 설정

		Container contentPane = getContentPane(); // 프레임에서 컨텐트팬 받아오기
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("확인");
		btnNewButton.setFont(new Font("굴림체", Font.BOLD, 10));
		btnNewButton.setBounds(530, 250, 96, 21);
		btnNewButton.setSize(120, 50);
		contentPane.add(btnNewButton);

		JButton btnNewButton1 = new JButton("취소");
		btnNewButton1.setFont(new Font("굴림체", Font.BOLD, 10));
		btnNewButton1.setBounds(680, 250, 96, 21);
		btnNewButton1.setSize(120, 50);
		contentPane.add(btnNewButton1);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		jsp.setBounds(40, 100, 800, 100);
		add(jsp);

		setVisible(true); // 화면에 프레임 출력
	}	
	public static void main(String[] args) {
		DB_Conn_Query3 dbc3 = new DB_Conn_Query3();
		if (dbc3.con == null)
			return;

		Menu7 jt3 = new Menu7();
		dbc3.sqlrun(jt3.model);
	}
}