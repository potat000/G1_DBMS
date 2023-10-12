import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
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
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class deletePanel extends JPanel {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 原本為：com.mysql.jdbc.Driver，但系統顯示驅動程式異用
	static final String DB_URL = "jdbc:mysql://localhost:3306/final?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "abcd";
	static final String PASS = "1234";

	private Connection conn;

	private JButton backBtn, deleteBtn;
	private JLabel titleLabel;
	private JList<String> list;
	private JScrollPane scrollPane;
	DefaultListModel model;
	String choose = "";

	public deletePanel() throws SQLException {
		initialize();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void initialize() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("mysql Connection Success ");

		creaTitleLabel();
		creaList();
		creaBtn();
		creaTotalPanel();
		listQuery(); // 在這初始就無法更新list為最新(新增後還是舊的)
	}

	public void creaTitleLabel() {
		titleLabel = new JLabel("刪除品項");
		titleLabel.setBounds(20, 45, 126, 38);
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
	}

	public void creaList() {
		list = new JList();
		list.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(20, 95, 355, 320);
	}

	public String listQuery() { // 新增後還是舊list！！！BUGGGGG
		// model.removeAllElements();
		String shopID = "1";//get!!!!!
		try {
			String query = "SELECT * FROM `item` WHERE `ShopID`= ?";
			PreparedStatement listQ = conn.prepareStatement(query);
			listQ.setString(1, shopID);
			ResultSet result = listQ.executeQuery();

			model = new DefaultListModel();
			ArrayList<ArrayList<Object>> itemList = new ArrayList<ArrayList<Object>>();
			while (result.next()) {
				model.addElement(result.getString("ItemName"));// 只顯示品名		
			}
			
			list.removeAll();
			list.setModel(model);

			list.setVisibleRowCount(itemList.size());
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 單選
			DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
			renderer.setHorizontalAlignment(JLabel.CENTER); // 置中

			list.addMouseListener(new MouseAdapter() {// 列表框添加鼠標事件
				public void mousePressed(MouseEvent e) {
					System.out.println(list.getSelectedValue());
					choose = list.getSelectedValue();
				}
			});
			scrollPane.repaint(); //沒用
			this.revalidate(); //沒用
			this.repaint(); //沒用
		} catch (SQLException sqle) {

		}
		return choose;
	}

	public void creaBtn() {
		backBtn = new JButton("返 回");
		backBtn.setBounds(10, 10, 70, 25);
		backBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		deleteBtn = new JButton("刪 除");
		deleteBtn.setBounds(255, 435, 120, 40);
		deleteBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
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

		class ClickListener_delete implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String shopID = login.getShopID();
				try {
					if (choose == "") {
						JOptionPane.showMessageDialog(null, "請選擇欲刪除品項！", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						String query = "DELETE FROM `item` WHERE `ShopID`= ? AND `ItemName` = ?"; // 最好再加個ItemID判斷
						PreparedStatement delete = conn.prepareStatement(query);
						delete.setString(1, shopID);
						delete.setString(2, choose);

						int result = JOptionPane.showConfirmDialog(null, String.format("確定要刪除此項目嗎?\n\n品名：%s\n", choose),
								"確認訊息", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							delete.executeUpdate();

							JOptionPane.showMessageDialog(null, String.format("刪除項目：\n\n品名：%s\n", choose), "刪除成功！",
									JOptionPane.PLAIN_MESSAGE);
							// model.removeAllElements();
							listQuery();// 刪完後顯示新list OK
							updatePanel u = new updatePanel(); // 更新頁沒更新！
							u.listQuery();
						}
					}
				} catch (SQLException sqlex) {

				}
			}
		}
		ActionListener deletebtn = new ClickListener_delete();
		deleteBtn.addActionListener(deletebtn);
	}

	public void creaTotalPanel() {
		setLayout(null);
		add(titleLabel);
		add(scrollPane);
		add(backBtn);
		add(deleteBtn);
	}

	public JList getList() {
		return list;
	}
}
