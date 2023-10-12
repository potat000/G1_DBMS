import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

public class shopCheckCommentPanel extends JPanel {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 原本為：com.mysql.jdbc.Driver，但系統顯示驅動程式異用
	static final String DB_URL = "jdbc:mysql://localhost:3306/final?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "abcd";
	static final String PASS = "1234";

	private Connection conn;
	private JLabel titleLabel, itemNameTitleLabel;
	private JButton backBtn, service, item;

	public shopCheckCommentPanel() throws SQLException {
		initialize();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("mysql Connection Success ");

		creaTitleLabel();
		creaItemChooseComp();
		creaBtn();
		creaTotalPanel();
	}

	public void creaTitleLabel() {
		titleLabel = new JLabel("評論");
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel.setBounds(20, 45, 126, 38);
	}

	public void creaItemChooseComp() {
		itemNameTitleLabel = new JLabel("選 項：");
		itemNameTitleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		itemNameTitleLabel.setBounds(20, 95, 70, 25);
	}

	public void creaBtn() {
		backBtn = new JButton("返 回");
		backBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		backBtn.setBounds(10, 10, 70, 25);

		service = new JButton("服務評論");
		service.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		service.setBounds(102, 95, 100, 25);

		item = new JButton("品項評論");
		item.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		item.setBounds(215, 95, 100, 25);
	}

	public void addBtnListener(final JPanel panel1) {
		class ClickListener_back implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "MainManage");
			}
		}
		ActionListener backbtn = new ClickListener_back();
		backBtn.addActionListener(backbtn);

		// 服務評論
		class ClickListener_service implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					JList list_service = new JList();
					list_service.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					DefaultListModel model_service = new DefaultListModel();
					list_service.setBounds(20, 140, 345, 320);
					add(list_service);

					String query = "SELECT * FROM `servicecomment` WHERE `shopID`= ?";
					PreparedStatement listQ = conn.prepareStatement(query);
					listQ.setString(1, login.getShopID()); // get

					ResultSet result = listQ.executeQuery();
					while (result.next()) {
						model_service.addElement(String.format("%s：\n（%s）%s", result.getString("userID"),
								result.getString("service"), result.getString("serviceComment")));
					}
					list_service.setModel(model_service);

					JScrollPane scrollPane = new JScrollPane(list_service);
					scrollPane.setBounds(20, 140, 345, 320);
					add(scrollPane);

				} catch (SQLException x) {

				}
			}
		}
		ActionListener servicebtn = new ClickListener_service();
		service.addActionListener(servicebtn);

		// 品項評論
		class ClickListener_item implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					JList list_item = new JList();
					list_item.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					DefaultListModel model_item = new DefaultListModel();
					list_item.setBounds(20, 140, 345, 320);
					add(list_item);

					String query2 = "SELECT * FROM `itemcomment` WHERE `shopID`= ?";
					PreparedStatement listQ = conn.prepareStatement(query2);
					listQ.setString(1, login.getShopID()); // get

					ResultSet result2 = listQ.executeQuery();
					while (result2.next()) {
						model_item.addElement(String.format("%s：\n（%s）%s", result2.getString("userID"),
								result2.getString("item"), result2.getString("itemcomment")));
					}
					list_item.setModel(model_item);

					JScrollPane scrollPane = new JScrollPane(list_item);
					scrollPane.setBounds(20, 140, 345, 320);
					add(scrollPane);

				} catch (SQLException x) {

				}
			}
		}
		ActionListener itembtn = new ClickListener_item();
		item.addActionListener(itembtn);
	}

	public void creaTotalPanel() {
		setLayout(null);
		add(titleLabel);
		add(itemNameTitleLabel);
		add(service);
		add(item);
		add(backBtn);
	}
}
