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

class DB_Conn_Query2 {
	Connection con = null;

	public DB_Conn_Query2() {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "hmart";
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

public class Menu5 extends JFrame {
	// Table 생성
	public Menu5() {
		setTitle("3"); // 프레임 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 800); // 프레임 크기 설정

		Container contentPane = getContentPane(); // 프레임에서 컨텐트팬 받아오기
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("행사명");
		lblNewLabel.setFont(new Font("굴림체", Font.BOLD, 20));
		lblNewLabel.setBounds(150, 100, 350, 125); // 레이블 위치 설정
		contentPane.add(lblNewLabel); // 콘텐트팬에 레이블 붙이기

		JTextField textField = new JTextField();
		textField.setFont(new Font("굴림체", Font.BOLD, 20));
		textField.setBounds(220, 140, 350, 125);
		textField.setSize(150, 40);
		contentPane.add(textField);
		textField.setColumns(10); // 텍스트 필드 기본 입력문자 갯수

		JLabel lblNewLabel2 = new JLabel("텍스트 C");
		lblNewLabel2.setFont(new Font("굴림체", Font.BOLD, 20));
		lblNewLabel2.setBounds(150, 170, 350, 125); // 레이블 위치 설정
		contentPane.add(lblNewLabel2); // 콘텐트팬에 레이블 붙이기

		JTextField textField2 = new JTextField();
		textField2.setFont(new Font("굴림체", Font.BOLD, 20));
		textField2.setBounds(220, 210, 350, 125);
		textField2.setSize(150, 40);
		contentPane.add(textField2);
		textField2.setColumns(10); // 텍스트 필드 기본 입력문자 갯수

		JLabel lblNewLabel3 = new JLabel("텍스트 A");
		lblNewLabel3.setFont(new Font("굴림체", Font.BOLD, 20));
		lblNewLabel3.setBounds(150, 240, 350, 125); // 레이블 위치 설정
		contentPane.add(lblNewLabel3); // 콘텐트팬에 레이블 붙이기

		JTextField textField3 = new JTextField();
		textField3.setFont(new Font("굴림체", Font.BOLD, 20));
		textField3.setBounds(220, 280, 350, 125);
		textField3.setSize(150, 40);
		contentPane.add(textField3);
		textField3.setColumns(10); // 텍스트 필드 기본 입력문자 갯수

		JLabel lblNewLabel4 = new JLabel("텍스트 B");
		lblNewLabel4.setFont(new Font("굴림체", Font.BOLD, 20));
		lblNewLabel4.setBounds(150, 310, 350, 125); // 레이블 위치 설정
		contentPane.add(lblNewLabel4); // 콘텐트팬에 레이블 붙이기

		JTextField textField4 = new JTextField();
		textField4.setFont(new Font("굴림체", Font.BOLD, 20));
		textField4.setBounds(220, 350, 350, 125);
		textField4.setSize(150, 40);
		contentPane.add(textField4);
		textField4.setColumns(10); // 텍스트 필드 기본 입력문자 갯수

		JLabel lblNewLabel5 = new JLabel("텍스트 D");
		lblNewLabel5.setFont(new Font("굴림체", Font.BOLD, 20));
		lblNewLabel5.setBounds(450, 100, 350, 125); // 레이블 위치 설정
		contentPane.add(lblNewLabel5); // 콘텐트팬에 레이블 붙이기

		JTextField textField5 = new JTextField();
		textField5.setFont(new Font("굴림체", Font.BOLD, 20));
		textField5.setBounds(520, 140, 350, 125);
		textField5.setSize(150, 40);
		contentPane.add(textField5);
		textField.setColumns(10); // 텍스트 필드 기본 입력문자 갯수

		JLabel lblNewLabel6 = new JLabel("텍스트 C");
		lblNewLabel6.setFont(new Font("굴림체", Font.BOLD, 20));
		lblNewLabel6.setBounds(450, 170, 350, 125); // 레이블 위치 설정
		contentPane.add(lblNewLabel6); // 콘텐트팬에 레이블 붙이기

		JTextField textField6 = new JTextField();
		textField6.setFont(new Font("굴림체", Font.BOLD, 20));
		textField6.setBounds(520, 210, 350, 125);
		textField6.setSize(150, 40);
		contentPane.add(textField6);
		textField6.setColumns(10); // 텍스트 필드 기본 입력문자 갯수

		JLabel lblNewLabel7 = new JLabel("텍스트 A");
		lblNewLabel7.setFont(new Font("굴림체", Font.BOLD, 20));
		lblNewLabel7.setBounds(450, 240, 350, 125); // 레이블 위치 설정
		contentPane.add(lblNewLabel7); // 콘텐트팬에 레이블 붙이기

		JTextField textField7 = new JTextField();
		textField7.setFont(new Font("굴림체", Font.BOLD, 20));
		textField7.setBounds(520, 280, 350, 125);
		textField7.setSize(150, 40);
		contentPane.add(textField7);
		textField7.setColumns(10); // 텍스트 필드 기본 입력문자 갯수

		JLabel lblNewLabel8 = new JLabel("텍스트 B");
		lblNewLabel8.setFont(new Font("굴림체", Font.BOLD, 20));
		lblNewLabel8.setBounds(450, 310, 350, 125); // 레이블 위치 설정
		contentPane.add(lblNewLabel8); // 콘텐트팬에 레이블 붙이기

		JTextField textField8 = new JTextField();
		textField8.setFont(new Font("굴림체", Font.BOLD, 20));
		textField8.setBounds(520, 350, 350, 125);
		textField8.setSize(150, 40);
		contentPane.add(textField8);
		textField8.setColumns(10); // 텍스트 필드 기본 입력문자 갯수

		JButton btnNewButton1 = new JButton("추가");
		btnNewButton1.setFont(new Font("굴림체", Font.BOLD, 30));
		btnNewButton1.setBounds(200, 450, 96, 21);
		btnNewButton1.setSize(190, 80);
		contentPane.add(btnNewButton1);

		JButton btnNewButton2 = new JButton("취소");
		btnNewButton2.setFont(new Font("굴림체", Font.BOLD, 30));
		btnNewButton2.setBounds(450, 450, 96, 21);
		btnNewButton2.setSize(190, 80);
		contentPane.add(btnNewButton2);
		
		btnNewButton1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Menu1(); //로그인 버튼을 누르면 로그인 페이지로 이동
				setVisible(false);
			}
		});
		
		btnNewButton2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Menu1(); //로그인 버튼을 누르면 로그인 페이지로 이동
				setVisible(false);
			}
		});

		setVisible(true); // 화면에 프레임 출력
	}
	
	public static void main(String[] args) {
		DB_Conn_Query2 dbc = new DB_Conn_Query2();
		if (dbc.con == null)
			return;

		Menu5 jt2 = new Menu5();
	}
}
