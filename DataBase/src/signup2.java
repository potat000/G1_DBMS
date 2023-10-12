import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class signup2 extends JPanel {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	private JButton signup2Btn, returnBtn;
	private JTextField account, password;
	private JLabel titleLabel, signup1Btn, loginBtn;
	Connection con = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/final?useUnicode=true&characterEncoding=Big5", "abcd", "1234");
	Statement statement = con.createStatement();
	ResultSet result = statement.executeQuery("show databases;");

	public signup2() throws SQLException {
		initialize();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
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
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			System.out.println("Connected");

			// jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
			// localhost是主機名,test是database名
			// useUnicode=true&characterEncoding=Big5使用的編碼

		} catch (ClassNotFoundException e) {
			System.out.println("DriverClassNotFound :" + e.toString());
		}

	}

	public void creaTitleLabel() {
		titleLabel = new JLabel("一般註冊");
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel.setBounds(20, 45, 126, 38);
	}

	public void creaTextFeild() {
		account = new JTextField();
		account.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		password = new JTextField();
		password.setFont(new Font("微軟正黑體", Font.PLAIN, 16));

		account.setBounds(125, 145, 150, 45);
		password.setBounds(125, 260, 150, 45);
	}

	public void creaBtn() {

		loginBtn = new JLabel("帳號");
		loginBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		loginBtn.setBounds(125, 100, 150, 45);

		signup1Btn = new JLabel("密碼");
		signup1Btn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		signup1Btn.setBounds(125, 215, 150, 45);

		signup2Btn = new JButton("註冊");
		signup2Btn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		signup2Btn.setBounds(125, 375, 150, 45);

		returnBtn = new JButton("返回");
		returnBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		returnBtn.setBounds(10, 10, 75, 25);
	}

	public void addBtnListener(final JPanel panel1) {
		class ClickListener_add implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					String a = account.getText();
					String p = password.getText();
					String sql1 = "INSERT INTO normalaccount\n" + "VALUES ('" + a + "','" + p + "');";
					int affect = statement.executeUpdate(sql1);

					JOptionPane.showMessageDialog(null, "註冊成功！\n請回前一頁登入！", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
					account.setText(null);
					password.setText(null);
				} catch (SQLException x) {

				}
			}
		}
		ActionListener addbtn = new ClickListener_add();
		signup2Btn.addActionListener(addbtn);

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
		add(signup1Btn);
		add(signup2Btn);
		add(returnBtn);
		add(account);
		add(password);

	}

}
