import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
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
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;

public class addPanel extends JPanel {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	// 原本為：com.mysql.jdbc.Driver，但系統顯示驅動程式異用
	static final String DB_URL = "jdbc:mysql://localhost:3306/final?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "abcd";
	static final String PASS = "1234";

	private ResultSet result = null;
	private Connection conn;

	private JButton backBtn, addBtn;
	private JLabel titleLabel, itemNameLabel, itemPriceLabel, itemTypeLabel;
	private JTextField nameField, priceField;
	private JRadioButton type_eat, type_drink;
	private ButtonGroup typeGroup;
	CardLayout card;

	public addPanel() throws SQLException {
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
		creaNameComp();
		creaPriceComp();
		creaTypeComp();
		creaBtn();
		creaTotalPanel();
	}

	public void creaTitleLabel() {
		titleLabel = new JLabel("新增品項");
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel.setBounds(20, 45, 126, 38);
	}

	public void creaNameComp() {
		itemNameLabel = new JLabel("名 稱：");
		itemNameLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		itemNameLabel.setBounds(20, 95, 75, 25);

		nameField = new JTextField();
		nameField.setBounds(95, 95, 280, 25);
		nameField.setColumns(10);
	}

	public void creaPriceComp() {
		itemPriceLabel = new JLabel("價 錢：");
		itemPriceLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		itemPriceLabel.setBounds(20, 145, 75, 25);

		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(95, 145, 280, 25);
	}

	public void creaTypeComp() {
		itemTypeLabel = new JLabel("種 類：");
		itemTypeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		itemTypeLabel.setBounds(20, 195, 75, 25);

		type_eat = new JRadioButton("好吃的");
		type_eat.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		type_eat.setBounds(95, 195, 105, 25);

		type_drink = new JRadioButton("好喝的");
		type_drink.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		type_drink.setBounds(200, 195, 105, 25);

		typeGroup = new ButtonGroup();
		typeGroup.add(type_eat);
		typeGroup.add(type_drink);
	}

	public void creaBtn() {
		backBtn = new JButton("返 回");
		backBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		backBtn.setBounds(10, 10, 70, 25);

		addBtn = new JButton("新 增");
		addBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		addBtn.setBounds(255, 435, 120, 40);
	}

	public void addBtnListener(final JPanel panel1) {
		class ClickListener_back implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "MenuManage");
				nameField.setText("");
				priceField.setText("");	
				typeGroup.clearSelection();
			}
		}
		ActionListener backbtn = new ClickListener_back();
		backBtn.addActionListener(backbtn);

		class ClickListener_add implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO `Item` (ShopID,ItemID,ItemName,ItemPrice,Type) VALUES(?,?,?,?,?)";
					PreparedStatement addItem = conn.prepareStatement(query);

					// 檢查價格欄是否為數字
					/*
					 * String str = priceField.getText(); System.out.print(str); boolean isNumeric =
					 * true; for (int i = 0; i < str.length(); i++) { if
					 * (!Character.isDigit(str.charAt(i))) { isNumeric = false; } }
					 */

					////////////要再加上檢查品名是否已存在！！！//////////////
					if (nameField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "請輸入品項名稱！", "Error", JOptionPane.ERROR_MESSAGE);
					} else if (priceField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "請輸入品項價格！", "Error", JOptionPane.ERROR_MESSAGE);
					} else if (type_eat.isSelected() == false && type_drink.isSelected() == false) {
						JOptionPane.showMessageDialog(null, "請選擇品項種類！", "Error", JOptionPane.ERROR_MESSAGE);
						/*
						 * } else if (isNumeric = false) { //檢查價格欄是否為數字
						 * JOptionPane.showMessageDialog(null, "價格須為數字！", "Error",
						 * JOptionPane.ERROR_MESSAGE);
						 */
					} else {
						// shopID
						String shopID = "1";//get!!!
						addItem.setString(1, shopID); // getShopID()

						// itemID
						String query2 = "SELECT * FROM `item` WHERE `ShopID`= ? ORDER BY `ItemID` DESC LIMIT 0,1"; // 最後一筆資料
						PreparedStatement itemID = conn.prepareStatement(query2);
						itemID.setString(1, shopID);
						result = itemID.executeQuery();

						if (result.next()) {
							addItem.setInt(2, result.getInt("ItemID") + 1); // 最後一個itemID + 1
						} else {
							addItem.setInt(2, 001);
						}

						addItem.setString(3, nameField.getText());
						addItem.setInt(4, Integer.parseInt(priceField.getText()));

						// type
						String type = "";
						if (type_eat.isSelected()) {
							addItem.setString(5, type_eat.getText());
							type = type_eat.getText();
						} else if (type_drink.isSelected()) {
							addItem.setString(5, type_drink.getText());
							type = type_drink.getText();
						}

						// 確認新增訊息
						int result = JOptionPane.showConfirmDialog(null,
								String.format("確定要新增此項目嗎?\n\n品名：%s\n價錢：%d\n種類：%s\n", nameField.getText(),
										Integer.parseInt(priceField.getText()), type),
								"確認訊息", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							addItem.executeUpdate();

							JOptionPane.showMessageDialog(null,
									String.format("新增項目資訊\n\n品名：%s\n價錢：%d\n種類：%s\n", nameField.getText(),
											Integer.parseInt(priceField.getText()), type),
									"新增成功！", JOptionPane.PLAIN_MESSAGE);
							nameField.setText("");
							priceField.setText("");
							typeGroup.clearSelection();
						}
						
						// 品項清單更新 //Jlist還是舊的QQ
						deletePanel d = new deletePanel();
						d.listQuery();
						System.out.println("addpanel call delete listquery");
						
						updatePanel u = new updatePanel();
						u.listQuery();
						System.out.println("addpanel call update listquery");													
					}
				} catch (SQLException sqlex) {

				}
			}
		}
		ActionListener addbtn = new ClickListener_add();
		addBtn.addActionListener(addbtn);
	}

	public void creaTotalPanel() {
		setLayout(null);
		add(titleLabel);
		add(backBtn);
		add(addBtn);
		add(itemNameLabel);
		add(nameField);
		add(itemPriceLabel);
		add(priceField);
		add(itemTypeLabel);
		add(type_eat);
		add(type_drink);
	}
}

