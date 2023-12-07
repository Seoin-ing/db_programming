package hi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu3 extends JFrame {
	private JTable table;
	private JButton button1, button2, button3, button4, button5;

	public Menu3() {
		// 프레임 설정
		setTitle("행사 조회");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 300);
		setLocationRelativeTo(null);

		// 데이터 배열 생성
		Object[][] data = { { "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "" } };

		// 열 제목 배열 생성
		Object[] columns = { "행사번호", "주소", "시작일수", "종료일시", "장소이름", "수용인원수", "예약가능여부", "가능일수" };

		// JTable 생성
		table = new JTable(data, columns);

		// JScrollPane을 사용하여 JTable을 감싸기 (스크롤 가능하도록)
		JScrollPane scrollPane = new JScrollPane(table);

		// 프레임에 JScrollPane 추가
		add(scrollPane, BorderLayout.CENTER);

		// 버튼 생성
		button1 = new JButton("수정");
		button2 = new JButton("새로고침");
		button3 = new JButton("삭제");
		button4 = new JButton("확인");
		button5 = new JButton("취소");

		// 버튼을 담을 패널 생성
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);
		buttonPanel.add(button5);

		// 패널을 프레임의 SOUTH 위치에 추가
		add(buttonPanel, BorderLayout.SOUTH);

		// 각 버튼에 대한 이벤트 리스너 등록
		button1.addActionListener(new ButtonClickListener());
		button2.addActionListener(new ButtonClickListener());
		button3.addActionListener(new ButtonClickListener());
		button4.addActionListener(new ButtonClickListener());
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

	private class ButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			JOptionPane.showMessageDialog(Menu3.this, source.getText() + "이 클릭되었습니다.");
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Menu3().setVisible(true);
			}
		});
	}
}