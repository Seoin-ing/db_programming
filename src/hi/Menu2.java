package hi;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

class DB_Conn_Query1 {
	Connection con = null;

	public DB_Conn_Query1() {
		String url = "";
		String id = "";
		String password = "";
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

public class Menu2 extends JFrame {
	// Table 생성
	private String colName[] = { "행사번호", "행사명", "행사유형","행사설명","조회" };
	public DefaultTableModel model = new DefaultTableModel(colName, 0);
	// Table에 들어갈 데이터 목록들 (헤더정보, 추가 될 row 개수)
	private JTable table = new JTable(model);
	private JScrollPane jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public Menu2() {
		DB_Conn_Query1 dbc1 = new DB_Conn_Query1();  // 생성자 명시적으로 호출
        if (dbc1.con == null)
            return;
		setTitle("행사 검색 화면"); // 프레임 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 800); // 프레임 크기 설정

		Container contentPane = getContentPane(); // 프레임에서 컨텐트팬 받아오기
		contentPane.setLayout(null);

		JTextField textField = new JTextField();
		textField.setFont(new Font("굴림체", Font.BOLD, 40));
		textField.setBounds(200, 50, 96, 21);
		textField.setSize(350, 80);
		contentPane.add(textField);
		textField.setColumns(10); // 텍스트 필드 기본 입력문자 갯수

		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("굴림체", Font.BOLD, 10));
		btnNewButton.setBounds(530, 50, 96, 21);
		btnNewButton.setSize(120, 80);
		contentPane.add(btnNewButton);
		
		table.getColumnModel().getColumn(4).setCellRenderer(new TableCell1());
		table.getColumnModel().getColumn(4).setCellEditor(new TableCell1());

		jsp.setBounds(40, 150, 800, 500);
		add(jsp);
		
		SwingUtilities.invokeLater(() -> {
            dbc1.sqlrun(model);
        });

		setVisible(true); // 화면에 프레임 출력
	}
	
	public static void main(String[] args) {
        Menu2 jt1 = new Menu2();
        
    }
}

class TableCell1 extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	JButton jb;

	public TableCell1() {
		jb = new JButton("조회");
		jb.addActionListener(e -> {
			new Menu3().setVisible(true);
		});
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return jb;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return jb;
	}
}