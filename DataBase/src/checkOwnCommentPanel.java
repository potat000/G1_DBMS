import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Component;

public class checkOwnCommentPanel extends JPanel {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 原本為：com.mysql.jdbc.Driver，但系統顯示驅動程式異用
	static final String DB_URL = "jdbc:mysql://localhost:3306/final?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	static final String USER = "abcd";
	static final String PASS = "1234";

	private Connection conn;

	private JButton backBtn;
	private JLabel titleLabel;
	private JLabel userIDTitleLabel;
	private JLabel userIDLabel = new JLabel();
	private JScrollPane scrollPane;
	private JList list;
	private JButton item;
	private JButton service;
	private JLabel itemNameTitleLabel;

	public checkOwnCommentPanel() throws SQLException {
		initialize();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void initialize() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);

		creaTitalLabel();
		creaUserIDComp();
		creaBtn();
		creaTotalPanel();
	}

	public void creaTitalLabel() {
		titleLabel = new JLabel("評論歷史");
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel.setBounds(20, 45, 126, 38);
	}

	public void creaUserIDComp() {
		userIDTitleLabel = new JLabel("使用者ID：");
		userIDTitleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		userIDTitleLabel.setBounds(30, 90, 85, 20);

		itemNameTitleLabel = new JLabel("選 項：");
		itemNameTitleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		itemNameTitleLabel.setBounds(30, 115, 70, 25);
	}

	public void creaBtn() {
		backBtn = new JButton("返回");
		backBtn.setBounds(10, 10, 70, 25);
		backBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		item = new JButton("品項評論");
		item.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		item.setBounds(205, 115, 100, 25);

		service = new JButton("服務評論");
		service.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		service.setBounds(90, 115, 100, 25);
	}

	public void addBtnListener(final JPanel panel) {
		// 返回
		class ClickListener_back implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) (panel.getLayout());
				cardLayout.show(panel, "userFunction");
			}
		}
		ActionListener backbtn = new ClickListener_back();
		backBtn.addActionListener(backbtn);

		// 服務評論
		class ClickListener_service implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					userIDLabel.setText(login2.getUserID());
					userIDLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
					userIDLabel.setBounds(117, 90, 85, 20);

					JList list_service = new JList();
					list_service.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					DefaultListModel model_service = new DefaultListModel();
					list_service.setBounds(30, 145, 340, 330);

					CellRenderer cellRenderer = new CellRenderer(80);
					list_service.setCellRenderer(cellRenderer);

					add(list_service);

					String query = "SELECT * FROM `servicecomment` WHERE `userID`= ?";
					PreparedStatement listQ = conn.prepareStatement(query);
					listQ.setString(1, login2.getUserID()); // get

					ResultSet result = listQ.executeQuery();
					while (result.next()) {
						model_service.addElement(String.format("店名-%s：（%s）\n%s", result.getString("shopname"),
								result.getString("service"), result.getString("serviceComment")));
					}
					list_service.setModel(model_service);

					JScrollPane scrollPane = new JScrollPane(list_service);
					scrollPane.setBounds(30, 145, 340, 330);
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
					userIDLabel.setText(login2.getUserID());
					userIDLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
					userIDLabel.setBounds(117, 90, 85, 20);

					JList list_item = new JList();
					list_item.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					DefaultListModel model_item = new DefaultListModel();
					list_item.setBounds(30, 145, 340, 330);

					CellRenderer cellRenderer = new CellRenderer(80);
					list_item.setCellRenderer(cellRenderer);

					add(list_item);

					String query2 = "SELECT * FROM `itemcomment` WHERE `userID`= ?";
					PreparedStatement listQ = conn.prepareStatement(query2);
					listQ.setString(1, login2.getUserID()); // get

					ResultSet result2 = listQ.executeQuery();
					while (result2.next()) {
						model_item.addElement(String.format("店名-%s（品項：%s）：\n%s", result2.getString("shopname"),
								result2.getString("item"), result2.getString("itemcomment")));
					}
					list_item.setModel(model_item);

					JScrollPane scrollPane = new JScrollPane(list_item);
					scrollPane.setBounds(30, 145, 340, 330);
					add(scrollPane);
				} catch (SQLException x) {

				}
			}
		}
		ActionListener itembtn = new ClickListener_item();
		item.addActionListener(itembtn);

	}

	class CellRenderer extends DefaultListCellRenderer {
		public static final String HTML_1 = "<html><body style='width: ";
		public static final String HTML_2 = "px'>";
		public static final String HTML_3 = "</html>";
		private int width;

		public CellRenderer(int width) {
			this.width = width;
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			// String text = HTML_1 + String.valueOf(width) + HTML_2 + value.toString() +
			// HTML_3;
			list.setEnabled(false);
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		}
	}

	public void creaTotalPanel() {
		setLayout(null);
		add(backBtn);
		add(titleLabel);
		add(userIDTitleLabel);
		add(userIDLabel);
		add(itemNameTitleLabel);
		add(item);
		add(service);
	}
}
