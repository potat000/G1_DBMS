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

public class userFunctionPanel extends JPanel {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	private JButton backBtn, infoCheckBtn, addCommentBtn, commentCheckBtn;
	private JLabel titleLabel;

	public userFunctionPanel() throws SQLException {
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
		backBtn = new JButton("返 回");
		backBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		backBtn.setBounds(10, 10, 70, 25);

		infoCheckBtn = new JButton("查看咖啡廳資訊");
		infoCheckBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		infoCheckBtn.setBounds(100, 190, 200, 45);

		/*addCommentBtn = new JButton("新增評論");
		addCommentBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		addCommentBtn.setBounds(100, 220, 200, 45);*/

		commentCheckBtn = new JButton("查看個人評論");
		commentCheckBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		commentCheckBtn.setBounds(100, 250, 200, 45);
	}

	public void addBtnListener(final JPanel panel1) {
		// 返回
		class ClickListener_back implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "login2");
			}
		}
		ActionListener backbtn = new ClickListener_back();
		backBtn.addActionListener(backbtn);

		// 查看資訊
		class ClickListener_add implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					shopInfoFrame frame = new shopInfoFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (SQLException | ClassNotFoundException ex) {

				}
			}
		}
		ActionListener addbtn = new ClickListener_add();
		infoCheckBtn.addActionListener(addbtn);

		// 新增評論
	/*	class ClickListener_update implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "addComment");
			}
		}
		ActionListener updatebtn = new ClickListener_update();
		addCommentBtn.addActionListener(updatebtn);*/

		// 查看評論
		class ClickListener_delete implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "checkOwnComment");
				
				try {
					checkOwnCommentPanel c = new checkOwnCommentPanel();
					
				} catch (SQLException e1) {
				}
			}
		}
		ActionListener deletebtn = new ClickListener_delete();
		commentCheckBtn.addActionListener(deletebtn);
	}

	public void creaTotalPanel() {
		setLayout(null);
		add(titleLabel);
		add(backBtn);
		add(infoCheckBtn);
	//	add(addCommentBtn);
		add(commentCheckBtn);
	}
}