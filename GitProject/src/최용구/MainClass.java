package 최용구;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MainClass extends JFrame implements ActionListener {
	ProgTest1 pt1 = new ProgTest1();
	ProgTest2 pt2 = new ProgTest2();
	JTextField inputId, inputName, inputTelNum;
	ButtonGroup bGroup;
	JTextArea showResult;

	MainClass() {
		super("프로그래밍언어활용평가");
		setLayout(new BorderLayout());
		JPanel topPanel, centerPanel, bottomPanel;
		JLabel id, memberName, telNum, gender;
		JRadioButton male, female;
		JButton enroll, delete, allMemberSearch, showGugudan;

		topPanel = new JPanel(new FlowLayout());
		topPanel.setBorder(new TitledBorder(new EtchedBorder(), "회원등록, 삭제, 조회"));
		centerPanel = new JPanel(new FlowLayout());
		centerPanel.setBorder(new TitledBorder(new EtchedBorder(), "구구단"));
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBorder(new TitledBorder(new EtchedBorder(), "결과"));

		id = new JLabel("회원ID : ");
		memberName = new JLabel("회원이름 : ");
		telNum = new JLabel("전화번호 : ");
		gender = new JLabel("성별 : ");

		inputId = new JTextField(12);
		inputName = new JTextField(12);
		inputTelNum = new JTextField(12);

		male = new JRadioButton("남");
		female = new JRadioButton("여");
		bGroup = new ButtonGroup();
		bGroup.add(male);
		bGroup.add(female);

		enroll = addButton("회원등록");
		delete = addButton("회원삭제");
		allMemberSearch = addButton("전체회원조회");
		showGugudan = addButton("구구단보기");

		showResult = new JTextArea(5, 60);

		Box hbox1 = Box.createHorizontalBox();
		hbox1.add(id);
		hbox1.add(inputId);
		hbox1.add(Box.createHorizontalStrut(10));
		hbox1.add(memberName);
		hbox1.add(inputName);
		hbox1.add(Box.createHorizontalStrut(10));
		hbox1.add(telNum);
		hbox1.add(inputTelNum);
		hbox1.add(Box.createHorizontalStrut(10));
		hbox1.add(gender);
		hbox1.add(male);
		hbox1.add(female);

		Box hbox2 = Box.createHorizontalBox();
		hbox2.add(enroll);
		hbox2.add(Box.createHorizontalStrut(10));
		hbox2.add(delete);
		hbox2.add(Box.createHorizontalStrut(10));
		hbox2.add(allMemberSearch);

		Box vbox = Box.createVerticalBox();
		vbox.add(hbox1);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(hbox2);
		topPanel.add(vbox);

		Box hbox3 = Box.createHorizontalBox();
		hbox3.add(Box.createHorizontalGlue());
		hbox3.add(showGugudan);
		hbox3.add(Box.createHorizontalGlue());
		centerPanel.add(hbox3);

		bottomPanel.add(showResult, BorderLayout.CENTER);

		Box cBox = Box.createVerticalBox();
		cBox.add(topPanel);
		cBox.add(centerPanel);
		cBox.add(bottomPanel);

		add(cBox, BorderLayout.CENTER);
	}

	JButton addButton(String str) {
		JButton button = new JButton(str);
		button.addActionListener(this);
		return button;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("회원등록")) {
			String gender = "";
			Enumeration<AbstractButton> enums = bGroup.getElements();
			while (enums.hasMoreElements()) {
				AbstractButton ab = enums.nextElement();
				JRadioButton jb = (JRadioButton) ab;
				if (jb.isSelected())
					gender = jb.getText();
			}
			int con = JOptionPane.showConfirmDialog(this, "회원을 등록하시겠습니까?", "회원등록", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (con == JOptionPane.YES_OPTION)
			{
				pt2.mapListAdd(inputId.getText(), inputName.getText(), inputTelNum.getText(), gender);
				inputId.setText(""); inputName.setText(""); inputTelNum.setText(""); bGroup.clearSelection();
			}
			else;
		} else if (e.getActionCommand().equals("회원삭제")) {
			String targetId = inputId.getText();
			int con = JOptionPane.showConfirmDialog(this, "회원을 삭제하시겠습니까?", "회원삭제", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (con == JOptionPane.YES_OPTION)
			{
				pt2.mapListRemove(targetId);
				inputId.setText(""); inputName.setText(""); inputTelNum.setText(""); bGroup.clearSelection();
			}
			else;
		} else if (e.getActionCommand().equals("전체회원조회")) {
			String showList = "회원ID" + "\t" + "회원이름" + "\t" + "전화번호" + "\t" + "성별\r\n";
			ArrayList<MemberData> data = pt2.getMapList();
			for (MemberData member : data) {
				showList += member.getMemberID() + "\t" + member.getName() + "\t" + member.getTel() + "\t"
						+ member.getGender() + "\r\n";
			}
			showResult.setText(showList);
		} else if (e.getActionCommand().equals("구구단보기")) {
			String dan = JOptionPane.showInputDialog(this, "몇단을 보시겠습니까?", "입력", JOptionPane.QUESTION_MESSAGE);
			String result = pt1.getGugudan(Integer.parseInt(dan));
			showResult.setText(result);
		} else;
	}

	public static void main(String[] args) {
		MainClass main = new MainClass();
		main.setSize(800, 400);
		main.pack();
		// main.inputId.requestFocusInWindow();
		
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}