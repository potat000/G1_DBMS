import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainManage extends JPanel {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	private JButton informationBtn, menuBtn, commentBtn, backBtn;
	private JLabel titleLabel;

	public MainManage() throws SQLException {
		initialize();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		System.out.println("SQLite Connection Success ");

		creaTitleLabel();
		creaBtn();
		creaTotalPanel();
	}

	public void creaTitleLabel() {
		titleLabel = new JLabel("功能選擇");
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel.setBounds(20, 45, 126, 38);
	}

	public void creaBtn() {

		informationBtn = new JButton("資訊管理");
		informationBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		informationBtn.setBounds(125, 160, 150, 45);

		menuBtn = new JButton("菜單管理");
		menuBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		menuBtn.setBounds(125, 220, 150, 45);

		commentBtn = new JButton("查看評論");
		commentBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		commentBtn.setBounds(125, 280, 150, 45);

		backBtn = new JButton("返 回");
		backBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		backBtn.setBounds(10, 10, 70, 25);
	}

	public void addBtnListener(final JPanel panel1) {
		// 返回
		class ClickListener_back implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "login");
			}
		}
		ActionListener backbtn = new ClickListener_back();
		backBtn.addActionListener(backbtn);

		class ClickListener_add implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				test_new frame = new test_new();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

			}
		}
		ActionListener addbtn = new ClickListener_add();
		informationBtn.addActionListener(addbtn);

		class ClickListener_add2 implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "MenuManage");

			}
		}
		ActionListener addbtn2 = new ClickListener_add2();
		menuBtn.addActionListener(addbtn2);

		class ClickListener_add3 implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "shopCheckComment");
			}
		}
		ActionListener addbtn3 = new ClickListener_add3();
		commentBtn.addActionListener(addbtn3);
	}

	public void creaTotalPanel() {
		setLayout(null);
		add(titleLabel);
		add(informationBtn);
		add(menuBtn);
		add(commentBtn);
		add(backBtn);

	}
}
