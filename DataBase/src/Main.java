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

public class Main extends JPanel {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	private JButton signup1Btn, signup2Btn, loginBtn, loginBtn2;
	private JLabel titleLabel;

	public Main() throws SQLException {
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
		titleLabel = new JLabel("主頁");
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel.setBounds(20, 45, 126, 38);
	}

	public void creaBtn() {

		loginBtn = new JButton("商家登入");
		loginBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		loginBtn.setBounds(100, 130, 200, 45);

		loginBtn2 = new JButton("一般用戶登入");
		loginBtn2.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		loginBtn2.setBounds(100, 190, 200, 45);

		signup1Btn = new JButton("商家註冊");
		signup1Btn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		signup1Btn.setBounds(100, 250, 200, 45);

		signup2Btn = new JButton("一般註冊");
		signup2Btn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		signup2Btn.setBounds(100, 310, 200, 45);
	}

	public void addBtnListener(final JPanel panel1) {
		class ClickListener_add implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "login");

			}
		}
		ActionListener addbtn = new ClickListener_add();
		loginBtn.addActionListener(addbtn);

		class ClickListener_add2 implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "login2");

			}
		}
		ActionListener addbtn2 = new ClickListener_add2();
		loginBtn2.addActionListener(addbtn2);

		class ClickListener_update implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "signup1");
			}
		}
		ActionListener updatebtn = new ClickListener_update();
		signup1Btn.addActionListener(updatebtn);

		class ClickListener_s2 implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "signup2");
			}
		}
		ActionListener s2 = new ClickListener_s2();
		signup2Btn.addActionListener(s2);
	}

	public void creaTotalPanel() {
		setLayout(null);
		add(titleLabel);
		add(loginBtn);
		add(loginBtn2);
		add(signup1Btn);
		add(signup2Btn);

	}

}
