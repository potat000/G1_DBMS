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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class login extends JPanel {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;
	
	private JButton signup2Btn, returnBtn;
	private JLabel titleLabel, loginBtn, signup1Btn;
	private JTextField account, password;
	Viewer v = new Viewer();
	Connection con = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/final?useUnicode=true&characterEncoding=Big5", "abcd", "1234");
	Statement statement = con.createStatement();
	ResultSet result = statement.executeQuery("show databases;");

	private static String shopID;

	public login() throws SQLException {
		initialize();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	public static String getShopID() {
		return shopID;
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		creaTextFeild();
		creaTitleLabel();
		creaBtn();
		creaTotalPanel();
	}

	public void creaTextFeild() {
		account = new JTextField();
		account.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		password = new JTextField();
		password.setFont(new Font("微軟正黑體", Font.PLAIN, 16));

		account.setBounds(125, 145, 150, 45);
		password.setBounds(125, 260, 150, 45);
	}

	public void creaTitleLabel() {
		titleLabel = new JLabel("商家登入");
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel.setBounds(20, 45, 126, 38);
	}

	public void creaBtn() {
		loginBtn = new JLabel("帳號");
		loginBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		loginBtn.setBounds(125, 100, 150, 45);

		signup1Btn = new JLabel("密碼");
		signup1Btn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		signup1Btn.setBounds(125, 215, 150, 45);

		signup2Btn = new JButton("登入");
		signup2Btn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		signup2Btn.setBounds(125, 375, 150, 45);

		returnBtn = new JButton("返回");
		returnBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		returnBtn.setBounds(10, 10, 70, 25);
	}

	public void addBtnListener(final JPanel panel1) {
		class ClickListener_add implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.out.print("Yes");
				try {
					String a = account.getText();
					String p = password.getText();
					String query1 = "SELECT COUNT(*) FROM cafeaccount WHERE Account ='" + a + "'";
					shopID = account.getText();
					updatePanel u = new updatePanel();
					u.listQuery();
					deletePanel d = new deletePanel();
					d.listQuery();
					statement.execute(query1);

					ResultSet result = statement.getResultSet();
					result.next();
					int count = Integer.parseInt(result.getString(1));

					if (count != 0) {
						String query = "SELECT Password FROM cafeaccount WHERE Account ='" + a + "'";
						statement.execute(query);
						result = statement.getResultSet();
						result.next();
						if (result.getString(1).equals(p)) {
							JOptionPane jop = new JOptionPane();
							jop.showMessageDialog(null, "Login", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
							System.out.println("login!");
							System.out.println("login test1:" + getShopID());

							account.setText(null);
							password.setText(null);

							CardLayout card = (CardLayout) (panel1.getLayout());
							card.show(panel1, "MainManage");
						} else {
							account.setText(null);
							password.setText(null);
							JOptionPane jop1 = new JOptionPane();
							jop1.showMessageDialog(null, "The password is wrong", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						account.setText(null);
						password.setText(null);
						JOptionPane jop2 = new JOptionPane();
						jop2.showMessageDialog(null, "The username is wrong", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}

				catch (Exception a) {
					a.getMessage();
					System.out.print("wrong!");
				}

			}
		}

		ActionListener loginbtn = new ClickListener_add();
		signup2Btn.addActionListener(loginbtn);

		class ClickListener_add2 implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "Main");
			}
		}
		ActionListener addbtn2 = new ClickListener_add2();
		returnBtn.addActionListener(addbtn2);

	}

	public void creaTotalPanel() {
		setLayout(null);
		add(titleLabel);
		add(loginBtn);
		add(returnBtn);
		add(signup1Btn);
		add(signup2Btn);
		add(account);
		add(password);
	}

}
