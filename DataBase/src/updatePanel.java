import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class updatePanel extends JPanel {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 原本為：com.mysql.jdbc.Driver，但系統顯示驅動程式異用
	static final String DB_URL = "jdbc:mysql://localhost:3306/final?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "abcd";
	static final String PASS = "1234";

	private JButton backBtn, updateBtn;
	private JLabel titleLabel;
	private JTextField nameField, priceField;
	private JLabel itemIDTitleLabel, itemIDLabel, itemNameLabel, itemPriceLabel, itemTypeLabel;
	private JRadioButton type_eat, type_drink;
	private JList list;
	private JScrollPane scrollPane;
	DefaultListModel model;

	private Connection conn;
	int chooseID;

	public updatePanel() throws SQLException {
		initialize();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);

	}

	private void initialize() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("mysql Connection Success ");

		creaTitleLabel();
		creaUpdateComp();
		creaBtn();
		creaTotalPanel();
		listQuery();
		listSelectionListener();
		// creaList();
	}

	public void creaTitleLabel() {
		titleLabel = new JLabel("更新品項");
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel.setBounds(20, 45, 126, 38);
	}

	public void creaList() {
		list = new JList();
		list.setFont(new Font("微軟正黑體", Font.PLAIN, 18));

		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(20, 95, 355, 190);
		add(scrollPane);
	}

	public void listQuery() {
		// creaList()
		list = new JList();
		list.setFont(new Font("微軟正黑體", Font.PLAIN, 18));

		String shopID = "1";//get!!!!
		try {
			String query = "SELECT * FROM `item` WHERE `ShopID`= ?";
			PreparedStatement listQ = conn.prepareStatement(query);
			listQ.setString(1, shopID);
			ResultSet result = listQ.executeQuery();
			int columnCount = result.getMetaData().getColumnCount();
			//System.out.println("Column Count: " + columnCount);

			String query2 = "SELECT COUNT(*) FROM `item` WHERE `ShopID`= ?";
			PreparedStatement row = conn.prepareStatement(query2);
			row.setString(1, shopID);
			ResultSet result2 = row.executeQuery();
			result2.next();
			int rowCount = result2.getInt("COUNT(*)");
			//System.out.println("Row Count: " + rowCount);

			ArrayList<ArrayList<Object>> itemList = new ArrayList<ArrayList<Object>>();
			while (result.next()) {
				ArrayList<Object> item = new ArrayList<Object>();
				for (int i = 1; i <= columnCount; i++) {
					Object o = result.getObject(i);
					item.add(o);
				}
				itemList.add(item);
			}

			model = new DefaultListModel();
			for (int i = 0; i < itemList.size(); i++) {
				model.addElement(/* String.valueOf(itemList.get(i).get(1)) + "  " + */(String) itemList.get(i).get(2));
			} // list要顯示什麼資訊(now only itemName)
			//System.out.println(model.lastElement());
			list.setModel(model);
			
			scrollPane = new JScrollPane(list);
			scrollPane.setBounds(20, 95, 355, 190);
			add(scrollPane);
			//list.setListData(v);
		} catch (SQLException sqle) {

		}
	}

	public void creaUpdateComp() {
		itemIDTitleLabel = new JLabel("編 號：");
		itemIDTitleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		itemIDTitleLabel.setBounds(20, 295, 75, 25);

		itemIDLabel = new JLabel("");
		itemIDLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		itemIDLabel.setBounds(95, 295, 280, 25);

		itemNameLabel = new JLabel("名 稱：");
		itemNameLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		itemNameLabel.setBounds(20, 330, 75, 25);

		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(95, 330, 280, 25);

		itemPriceLabel = new JLabel("價 錢：");
		itemPriceLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		itemPriceLabel.setBounds(20, 365, 75, 25);

		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(95, 365, 280, 25);

		itemTypeLabel = new JLabel("種 類：");
		itemTypeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		itemTypeLabel.setBounds(20, 400, 75, 25);

		type_eat = new JRadioButton("好吃的");
		type_eat.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		type_eat.setBounds(95, 400, 105, 25);

		type_drink = new JRadioButton("好喝的");
		type_drink.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		type_drink.setBounds(200, 400, 105, 25);
	}

	public int listSelectionListener() { // list按下去跳出品項資訊
		final String shopID = "1";//get

		class SharedListSelectionHandler implements ListSelectionListener {
			public void valueChanged(ListSelectionEvent e) {
				try {
					String query3 = "SELECT * FROM `item` WHERE `ShopID`= ? AND `ItemName` = ?";
					PreparedStatement showInfo = conn.prepareStatement(query3);
					showInfo.setString(1, shopID);
					showInfo.setString(2, (String) list.getSelectedValue());
					ResultSet result3 = showInfo.executeQuery();
					int colCount = result3.getMetaData().getColumnCount();

					ArrayList<ArrayList<Object>> infoList = new ArrayList<ArrayList<Object>>();
					while (result3.next()) {
						ArrayList<Object> info = new ArrayList<Object>();
						for (int i = 1; i <= colCount; i++) {
							Object o = result3.getObject(i);
							info.add(o);
						}
						infoList.add(info);
					}

					itemIDLabel.setText(String.valueOf(infoList.get(0).get(1)));
					nameField.setText((String) infoList.get(0).get(2));
					priceField.setText(String.valueOf(infoList.get(0).get(3)));
					if (infoList.get(0).get(4).equals("好吃的")) {
						type_eat.setSelected(true);
						type_drink.setSelected(false);
					} else if (infoList.get(0).get(4).equals("好喝的")) {
						type_eat.setSelected(false);
						type_drink.setSelected(true);
					}
					chooseID = (int) infoList.get(0).get(1);
				} catch (SQLException sqlex) {

				}
			}
		}
		ListSelectionListener listener = new SharedListSelectionHandler();
		list.addListSelectionListener(listener);

		return chooseID;
	}

	public void creaBtn() {
		backBtn = new JButton("返 回");
		backBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		backBtn.setBounds(10, 10, 70, 25);

		updateBtn = new JButton("更 新");
		updateBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		updateBtn.setBounds(255, 435, 120, 40);
	}

	public void addBtnListener(final JPanel panel1) {
		class ClickListener_back implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "MenuManage");
			}
		}
		ActionListener backbtn = new ClickListener_back();
		backBtn.addActionListener(backbtn);

		class ClickListener_update implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "UPDATE `Item` SET `ItemName` = ?, `ItemPrice` = ?, `Type` = ? WHERE `ItemID` = ?";
					PreparedStatement updateItem = conn.prepareStatement(query);
					updateItem.setString(1, nameField.getText());
					updateItem.setInt(2, Integer.parseInt(priceField.getText()));
					String type = "";
					if (type_eat.isSelected()) {
						updateItem.setString(3, type_eat.getText());
						type = type_eat.getText();
					} else if (type_drink.isSelected()) {
						updateItem.setString(3, type_drink.getText());
						type = type_drink.getText();
					}
					updateItem.setInt(4, chooseID);

					int result = JOptionPane.showConfirmDialog(null,
							String.format("確定要將此項目修改為以下資訊嗎?\n\n品名：%s\n價錢：%d\n種類：%s\n", nameField.getText(),
									Integer.parseInt(priceField.getText()), type),
							"確認訊息", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						updateItem.executeUpdate();
						JOptionPane.showMessageDialog(null,
								String.format("更新項目資訊\n\n品名：%s\n價錢：%d\n種類：%s", nameField.getText(),
										Integer.parseInt(priceField.getText()), type),
								"修改成功！", JOptionPane.PLAIN_MESSAGE);
					}
				} catch (SQLException sqlex) {

				}
			}
		}
		ActionListener updatebtn = new ClickListener_update();
		updateBtn.addActionListener(updatebtn);
	}

	public void creaTotalPanel() {
		setLayout(null);
		add(titleLabel);
		// add(scrollPane);不能放這邊?_?
		add(itemIDTitleLabel);
		add(itemIDLabel);
		add(itemNameLabel);
		add(nameField);
		add(itemPriceLabel);
		add(priceField);
		add(itemTypeLabel);
		add(type_eat);
		add(type_drink);
		add(backBtn);
		add(updateBtn);

	}
	public JList getList() {
		return list;
	}
}

