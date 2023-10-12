import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import javax.swing.SwingConstants;

public class addItemCommentPanel extends JPanel {

	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 原本為：com.mysql.jdbc.Driver，但系統顯示驅動程式異用
	static final String DB_URL = "jdbc:mysql://localhost:3306/final?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "abcd";
	static final String PASS = "1234";

	private Dimension maximumSize;

	private JButton backBtn, confirmBtn;
	private JLabel shopNameLabel, itemLabel, commentLabel;
	private JComboBox<String> itemComboBox;
	private JTextArea commentTextArea;

	private Connection conn;

	public addItemCommentPanel() throws SQLException {
		initialize();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("mysql Connection Success ");

		creaBtn();
		creaShopNameLabel("小公寓");
		creaItemChooseComp();
		creaCommentComp();
		creaTotalPanel();
	}

	public void creaBtn() {
		backBtn = new JButton("返回");
		backBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		backBtn.setBounds(10, 10, 70, 25);

		confirmBtn = new JButton("確認送出");
		confirmBtn.setBounds(215, 435, 140, 40);
		confirmBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
	}

	public void creaShopNameLabel(String shopName) {// init
		shopNameLabel = new JLabel(shopName);
		shopNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		shopNameLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		shopNameLabel.setForeground(new Color(139, 69, 19));
		shopNameLabel.setBounds(50, 40, 300, 40);

		shopNameLabel.setMaximumSize(maximumSize);
	}

	public void creaItemChooseComp() {
		itemLabel = new JLabel("品項：");
		itemLabel.setBounds(40, 110, 70, 30);
		itemLabel.setFont(new Font("微軟正黑體", Font.BOLD, 22));

		itemComboBox = new JComboBox<String>();
		itemComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		itemComboBox.setBounds(120, 110, 100, 30);

		try {
			// 表格關係(?
			String rq1 = "SELECT `ItemName` FROM final.item WHERE `ShopID`= ?";
			PreparedStatement stat = conn.prepareStatement(rq1);
			stat.setInt(1, 001); // getShopID
			// stat.setString(2, "小公寓"); // getShopName
			ResultSet rs1 = stat.executeQuery();

			for (int i = 1; i < 100; i++) {
				while (rs1.next()) {
					itemComboBox.addItem(rs1.getString(i));
				}
			}
			rs1.close();
		} catch (SQLException e) {

		}
	}

	public void creaCommentComp() {
		commentLabel = new JLabel("評論：");
		commentLabel.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		commentLabel.setBounds(40, 150, 70, 30);
		commentLabel.setMaximumSize(maximumSize);

		commentTextArea = new JTextArea();
		commentTextArea.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
		commentTextArea.setBounds(40, 190, 315, 235);

		// ?????
		commentTextArea.setVisible(true);
		commentTextArea.setLineWrap(true);
		commentTextArea.setBackground(Color.lightGray);
		commentTextArea.setWrapStyleWord(true);
		commentTextArea.setVerifyInputWhenFocusTarget(true);
	}

	public void addBtnListener(final JPanel panel) {
		// 返回
		class ClickListener_back implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) (panel.getLayout());
				cardLayout.show(panel, "addComment");
				commentTextArea.setText("");
			}
		}
		ActionListener backbtn = new ClickListener_back();
		backBtn.addActionListener(backbtn);

		// 確認
		class ClickListener_confirm implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					String rqaddcomment = "INSERT INTO final.itemcomment (userID,shopID,shopname,itemID,item,commentID,itemcomment) VALUES(?,?,?,?,?,?,?)";
					PreparedStatement addComm = conn.prepareStatement(rqaddcomment);

					addComm.setString(1, "test"); // get
					addComm.setInt(2, 001); // get
					addComm.setString(3, "小公寓"); // get

					// Item ID
					String getItemID = "SELECT `ItemID` FROM final.item WHERE `ShopID`= ? AND `ItemName`= ?";
					PreparedStatement getitemID = conn.prepareStatement(getItemID);
					getitemID.setInt(1, 001); // get
					getitemID.setString(2, (String) itemComboBox.getSelectedItem());
					ResultSet result = getitemID.executeQuery();

					result.next();
					addComm.setInt(4, result.getInt("ItemID"));
					addComm.setString(5, (String) itemComboBox.getSelectedItem());

					// comment ID
					String query2 = "SELECT * FROM final.itemcomment WHERE `ShopID`= ? ORDER BY `commentID` DESC LIMIT 0,1"; // 最後一筆資料
					PreparedStatement getLastComment = conn.prepareStatement(query2);
					getLastComment.setInt(1, 001);
					ResultSet result2 = getLastComment.executeQuery();

					if (result2.next()) {
						addComm.setString(6, String.valueOf(result2.getInt("commentID") + 1)); // 最後一個commentID + 1
					} else {
						addComm.setString(6, "1");
					}
					addComm.setString(7, commentTextArea.getText());

					// 確認新增
					int check = JOptionPane.showConfirmDialog(null,
							String.format("確定要對此項商品新增此評論嗎?\n\n品名：%s\n評論：%s", itemComboBox.getSelectedItem(),
									commentTextArea.getText()),
							"確認訊息", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					if (check == JOptionPane.YES_OPTION) {
						addComm.executeUpdate();
						JOptionPane.showMessageDialog(null, "SUCCESS", "新增成功！", JOptionPane.PLAIN_MESSAGE);
						commentTextArea.setText("");
					}
				} catch (SQLException e1) {

				}
			}
		}
		ActionListener confirmbtn = new ClickListener_confirm();
		confirmBtn.addActionListener(confirmbtn);
	}

	public void creaTotalPanel() {
		setLayout(null);
		add(backBtn);
		add(shopNameLabel);
		add(itemLabel);
		add(itemComboBox);
		add(commentLabel);
		add(commentTextArea);
		add(confirmBtn);
	}

}
