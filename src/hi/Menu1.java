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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class DB_Conn_Query {
	Connection con = null;

	public DB_Conn_Query() {
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

class Menu1 extends JFrame {
	// Table 생성
	public Menu1() {
		setTitle("1"); // 프레임 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 800); // 프레임 크기 설정

		Container contentPane = getContentPane(); // 프레임에서 컨텐트팬 받아오기
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("행사 관리");
		lblNewLabel.setFont(new Font("굴림체", Font.BOLD, 50));
		lblNewLabel.setBounds(300, 100, 350, 125); // 레이블 위치 설정
		contentPane.add(lblNewLabel); // 콘텐트팬에 레이블 붙이기

		JTextField textField = new JTextField();
		textField.setFont(new Font("굴림체", Font.BOLD, 40));
		textField.setBounds(200, 250, 96, 21);
		textField.setSize(350, 80);
		contentPane.add(textField);
		textField.setColumns(10); // 텍스트 필드 기본 입력문자 갯수

		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("굴림체", Font.BOLD, 10));
		btnNewButton.setBounds(530, 250, 96, 21);
		btnNewButton.setSize(120, 80);
		contentPane.add(btnNewButton);

		JButton btnNewButton1 = new JButton("행사 목록");
		btnNewButton1.setFont(new Font("굴림체", Font.BOLD, 30));
		btnNewButton1.setBounds(200, 450, 96, 21);
		btnNewButton1.setSize(190, 80);
		contentPane.add(btnNewButton1);

		JButton btnNewButton2 = new JButton("행사 추가");
		btnNewButton2.setFont(new Font("굴림체", Font.BOLD, 30));
		btnNewButton2.setBounds(450, 450, 96, 21);
		btnNewButton2.setSize(190, 80);
		contentPane.add(btnNewButton2);
		
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Menu2(); //로그인 버튼을 누르면 로그인 페이지로 이동
				setVisible(false);
			}
		});
		
		btnNewButton1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        SwingUtilities.invokeLater(() -> {
		            new Menu4().setVisible(true);
		            setVisible(false); // Hide the current frame
		        });
		    }
		});
		
		btnNewButton2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Menu5(); //로그인 버튼을 누르면 로그인 페이지로 이동
				setVisible(false);
			}
		});

		setVisible(true); // 화면에 프레임 출력
	}
	
	public static void main(String[] args) {
		DB_Conn_Query dbc = new DB_Conn_Query();
		if (dbc.con == null)
			return;

		Menu1 jt = new Menu1();
	}
}