import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class shopInfoFrame extends JFrame {
	private JPanel contentPane, cardPanel, cafePanel, menuPanel, serviceCommentPanel, itemCommentPanel,commentMenuPanel,addServicePanel,addItemPanel;

	private CardLayout cardLayout = new CardLayout();

	private JLabel titleLabel = new JLabel("選擇咖啡廳：");
	
	private JLabel menuLabel = new JLabel("菜單");
	
	private JLabel imformationLabel = new JLabel("咖啡廳資訊");
	private JLabel nameLabel = new JLabel("店名：");
	private JLabel addressLabel = new JLabel("地址：");
	private JLabel phoneLabel = new JLabel("電話：");
	private JLabel timeLabel = new JLabel("營業時間");
	private JLabel openLabel = new JLabel("OPEN: ");
	private JLabel closeLabel = new JLabel("CLOSE: ");
	private JLabel otherLabel = new JLabel("其他資訊");
	private JLabel WIFILabel = new JLabel("WIFI: ");
	private JLabel time_limitLabel = new JLabel("限時：");
	private JLabel min_orderLabel = new JLabel("最低消費：");
	private JLabel socketLabel = new JLabel("插座：");
	
	private JLabel itemCommemtLabel = new JLabel("商品評論");
	private JLabel itemNameCommemtLabel = new JLabel("品項：");
	
	private JLabel serviceCommemtLabel = new JLabel("服務評論");
	private JLabel serviceLabel = new JLabel("項目：");

	private JLabel itemLabel = new JLabel("品項：");
	private JLabel commentLabel_addItemComment = new JLabel("評論：");
	
	private JLabel itemNameLabel = new JLabel("");
	private JLabel nameLabel_print = new JLabel("");
	private JLabel addressLabel_print = new JLabel("");
	private JLabel phoneLabel_print = new JLabel("");
	private JLabel opentimeLabel = new JLabel("");
	private JLabel closetimeLabel = new JLabel("");
	private JLabel WIFILabel_print = new JLabel("");
	private JLabel limitLabel = new JLabel("");
	private JLabel orderLabel = new JLabel("");
	private JLabel socketLabel_print = new JLabel("");
	
	private JLabel shopNameLabel_addServiceComment= new JLabel("");
	private JLabel shopNameLabel_addItemComment = new JLabel("");

	private String selected = "";
	private String selected_cafe = "";
	private String id="";
	private String price="";
	String shopID = login.getShopID(); 
	String userID = login2.getUserID(); 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					shopInfoFrame frame = new shopInfoFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public shopInfoFrame() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		final Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/final?user=abcd&password=1234&autoReconnect=true");

		setTitle("咖啡廳資訊");
		setSize(400, 520);

		cardPanel = new JPanel();
		cardPanel.setSize(400, 520);
		cardPanel.setLayout(cardLayout);

		menuPanel = new JPanel();
		menuPanel.setLayout(null);

		serviceCommentPanel = new JPanel();
		serviceCommentPanel.setLayout(null);

		itemCommentPanel = new JPanel();
		itemCommentPanel.setLayout(null);

		cafePanel = new JPanel();
		cafePanel.setLayout(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		commentMenuPanel= new JPanel();
		commentMenuPanel.setLayout(null);
		
		addServicePanel= new JPanel();
		addServicePanel.setLayout(null);
		
		addItemPanel= new JPanel();
		addItemPanel.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 520);

		// 選咖啡廳
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel.setBounds(20, 45, 149, 30);
		cafePanel.add(titleLabel);

		final JList list_cafe = new JList();
		list_cafe.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		DefaultListModel model_cafe = new DefaultListModel();

		try {
			String query = "SELECT * FROM `shop_information`";
			PreparedStatement listQ = con.prepareStatement(query);

			ResultSet result = listQ.executeQuery();/////
			while (result.next()) {
				model_cafe.addElement(result.getString("shopname"));
			}
			list_cafe.setModel(model_cafe);

			JScrollPane scrollPane = new JScrollPane(list_cafe);
			scrollPane.setBounds(20, 95, 345, 320);
			cafePanel.add(scrollPane);
		} catch (SQLException x) {

		}
		
		// 咖啡廳資訊
		class SharedListSelectionHandler_2 implements ListSelectionListener {
			public void valueChanged(ListSelectionEvent e) {
				cardLayout.show(cardPanel, "2");
				selected_cafe = (String) list_cafe.getSelectedValue();

				shopNameLabel_addServiceComment.setText(selected_cafe); // confirm
				shopNameLabel_addServiceComment.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				shopNameLabel_addServiceComment.setForeground(new Color(139, 69, 19));
				shopNameLabel_addServiceComment.setBounds(50, 40, 300, 40);
				shopNameLabel_addServiceComment.setHorizontalAlignment(SwingConstants.CENTER);
				addServicePanel.add(shopNameLabel_addServiceComment);
				
				shopNameLabel_addItemComment.setText(selected_cafe); // confirm
				shopNameLabel_addItemComment.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				shopNameLabel_addItemComment.setForeground(new Color(139, 69, 19));
				shopNameLabel_addItemComment.setBounds(50, 40, 300, 40);
				shopNameLabel_addItemComment.setHorizontalAlignment(SwingConstants.CENTER);
				addItemPanel.add(shopNameLabel_addItemComment);
				
				imformationLabel.setForeground(Color.BLACK);
				imformationLabel.setFont(new Font("微軟正黑體", Font.BOLD, 18));
				imformationLabel.setBounds(20, 40, 140, 30);
				contentPane.add(imformationLabel);

				nameLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
				nameLabel.setBounds(45, 70, 60, 35);
				contentPane.add(nameLabel);

				addressLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
				addressLabel.setBounds(45, 105, 60, 35);
				contentPane.add(addressLabel);

				phoneLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
				phoneLabel.setBounds(45, 140, 65, 35);
				contentPane.add(phoneLabel);

				timeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
				timeLabel.setBounds(45, 175, 115, 35);
				contentPane.add(timeLabel);

				openLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
				openLabel.setBounds(65, 210, 60, 35);
				contentPane.add(openLabel);

				closeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
				closeLabel.setBounds(65, 245, 60, 35);
				contentPane.add(closeLabel);

				otherLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
				otherLabel.setBounds(45, 280, 80, 35);
				contentPane.add(otherLabel);

				WIFILabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
				WIFILabel.setBounds(65, 315, 60, 35);
				contentPane.add(WIFILabel);

				time_limitLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
				time_limitLabel.setBounds(65, 350, 60, 35);
				contentPane.add(time_limitLabel);

				min_orderLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
				min_orderLabel.setBounds(65, 385, 95, 35);
				contentPane.add(min_orderLabel);

				socketLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
				socketLabel.setBounds(220, 315, 60, 35);
				contentPane.add(socketLabel);

				try {
					Statement statement = con.createStatement();
					ResultSet result = statement.executeQuery("show databases;");
					System.out.println("Connected");

					// 從資料庫取得資料
					ResultSet info_result = statement
							.executeQuery("SELECT * FROM `shop_information` WHERE shopname = \"" + selected_cafe + "\"");

					while (info_result.next()) {
						shopID = info_result.getString("shopID");
						String name = info_result.getString("shopname");
						String address = info_result.getString("address");
						String telephone = info_result.getString("telephone");
						String open_time = info_result.getString("open_time");
						String close_time = info_result.getString("close_time");
						String WIFI = info_result.getString("WIFI");
						String socket = info_result.getString("socket");
						String time_limit = info_result.getString("time_limit");
						String min_order = info_result.getString("minimum_order");

						System.out.println("ID:" + shopID + ", Name:" + name + ", Address:" + address + ", Telephone:"
								+ telephone + ", 營業時間:OPEN:" + open_time + ", CLOSE:" + close_time + ", WIFI:" + WIFI
								+ ", Socket:" + socket + ", Limit_time:" + time_limit + ", Min_order:" + min_order);

						nameLabel_print.setText(name);
						nameLabel_print.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
						nameLabel_print.setBounds(90, 70, 300, 35);
						contentPane.add(nameLabel_print);

						addressLabel_print.setText(address);
						addressLabel_print.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
						addressLabel_print.setBounds(90, 105, 300, 35);
						contentPane.add(addressLabel_print);

						phoneLabel_print.setText(telephone);
						phoneLabel_print.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
						phoneLabel_print.setBounds(90, 140, 200, 35);
						contentPane.add(phoneLabel_print);

						opentimeLabel.setText(open_time);
						opentimeLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
						opentimeLabel.setBounds(120, 210, 120, 35);
						contentPane.add(opentimeLabel);

						closetimeLabel.setText(close_time);
						closetimeLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
						closetimeLabel.setBounds(120, 245, 120, 35);
						contentPane.add(closetimeLabel);

						WIFILabel_print.setText(WIFI);
						WIFILabel_print.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
						WIFILabel_print.setBounds(110, 315, 60, 35);
						contentPane.add(WIFILabel_print);

						limitLabel.setText(time_limit);
						limitLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
						limitLabel.setBounds(110, 350, 60, 35);
						contentPane.add(limitLabel);

						orderLabel.setText(min_order);
						orderLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
						orderLabel.setBounds(140, 385, 95, 35);
						contentPane.add(orderLabel);

						socketLabel_print.setText(socket);
						socketLabel_print.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
						socketLabel_print.setBounds(265, 315, 60, 35);
						contentPane.add(socketLabel_print);
					}

					// shop ID
					selected_cafe = (String) list_cafe.getSelectedValue();
					String getShopID = "SELECT `shopID` FROM final.shop_information WHERE `shopname`= ?";
					PreparedStatement getshopitemID = con.prepareStatement(getShopID);
					getshopitemID.setString(1, selected_cafe); 
					ResultSet re = getshopitemID.executeQuery();
					re.next();
					id=re.getString("shopID");
					System.out.println(selected_cafe+""+id);
					
					// 顯示菜單
					menuLabel.setBounds(20, 45, 125, 40);
					menuLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
					menuPanel.add(menuLabel);
					
					final JList list_menu = new JList();
					list_menu.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					DefaultListModel model_menu = new DefaultListModel();
					
					String query = "SELECT * FROM `item` WHERE `ShopID`= ?";
					PreparedStatement listQ = con.prepareStatement(query);
					listQ.setString(1, id); // shopID
					System.out.println("顯示菜單:"+""+id);
					
					ResultSet result2 = listQ.executeQuery();/////
					while (result2.next()) {
						model_menu.addElement(result2.getString("ItemName"));
					}
					list_menu.setModel(model_menu);

					JScrollPane scrollPane = new JScrollPane(list_menu);
					scrollPane.setBounds(20, 95, 345, 320);
					menuPanel.add(scrollPane);
					
					// 顯示商品評論 //點完菜單品項
					class SharedListSelectionHandler implements ListSelectionListener {
						public void valueChanged(ListSelectionEvent e) {
							selected = (String) list_menu.getSelectedValue();
							//價錢
							try {
							String qu = "SELECT ItemPrice FROM `item` WHERE `ItemName`= ?";
							PreparedStatement list = con.prepareStatement(qu);
							list.setString(1, selected); 
							ResultSet r = list.executeQuery();
							r.next();
							price=String.valueOf(r.getInt("ItemPrice"));
							
							itemNameLabel.setText(selected+"     $ "+price);
							}catch(SQLException exc) {
								
							}
							cardLayout.show(cardPanel, "5");
							
							itemNameLabel.setBounds(90, 85, 280, 40);
							itemNameLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
							itemCommentPanel.add(itemNameLabel);

							itemCommemtLabel.setBounds(20, 45, 125, 40);
							itemCommemtLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
							itemCommentPanel.add(itemCommemtLabel);

							itemNameCommemtLabel.setBounds(20, 85, 80, 40);
							itemNameCommemtLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
							itemCommentPanel.add(itemNameCommemtLabel);

							JList list_itemComment = new JList();
							list_itemComment.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
							DefaultListModel model_item = new DefaultListModel();
							list_itemComment.setBounds(20, 135, 345, 320);
							itemCommentPanel.add(list_itemComment);
							try {
								String query = "SELECT * FROM `itemcomment` WHERE `shopname`= ? AND `item`= ?";
								PreparedStatement listQ = con.prepareStatement(query);
								listQ.setString(1, selected_cafe);
								listQ.setString(2, selected);

								ResultSet result2 = listQ.executeQuery();

								while (result2.next()) {
									model_item.addElement(String.format("%s：\n%s", result2.getString("userID"),
											result2.getString("itemcomment")));
								}
								list_itemComment.setModel(model_item);

								// 如果沒東西就跳回菜單
								String query2 = "SELECT COUNT(*) FROM `itemcomment` WHERE `shopname`= ? AND `item`= ?";
								PreparedStatement row = con.prepareStatement(query2);
								row.setString(1, selected_cafe);
								row.setString(2, selected);
								ResultSet r2 = row.executeQuery();
								r2.next();
								int rowCount = r2.getInt("COUNT(*)");
								if (rowCount == 0) {
									JTextPane textPane = new JTextPane();
									textPane.setBounds(20, 135, 345, 320);
									itemCommentPanel.add(textPane);
									textPane.setVisible(true);

									JOptionPane.showMessageDialog(null, "目前尚無該商品的相關評論！><", "查無資料", JOptionPane.ERROR_MESSAGE);
									//cardLayout.show(cardPanel, "4");
								}

								JScrollPane scrollPane = new JScrollPane(list_itemComment);
								scrollPane.setBounds(20, 135, 345, 320);
								itemCommentPanel.add(scrollPane);
								
								
							} catch (SQLException x) {

							}
						}
					}
					ListSelectionListener listener = new SharedListSelectionHandler();
					list_menu.addListSelectionListener(listener);
					
					// 顯示服務評論
					serviceCommemtLabel.setBounds(20, 45, 125, 40);
					serviceCommemtLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
					serviceCommentPanel.add(serviceCommemtLabel);

					JList list_serviceComment = new JList();
					list_serviceComment.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					DefaultListModel model_service = new DefaultListModel();
					list_serviceComment.setBounds(20, 95, 345, 320);
					serviceCommentPanel.add(list_serviceComment);
					try {
						String quer = "SELECT * FROM `servicecomment` WHERE `shopname`= ?";
						PreparedStatement lis = con.prepareStatement(quer);
						lis.setString(1, selected_cafe); // get

						ResultSet res = lis.executeQuery();
						while (res.next()) {
							model_service.addElement(String.format("%s：\n（%s）%s", res.getString("userID"),
									res.getString("service"), res.getString("serviceComment")));
						}
						list_serviceComment.setModel(model_service);

						JScrollPane scrollPane2 = new JScrollPane(list_serviceComment);
						scrollPane2.setBounds(20, 95, 345, 320);
						serviceCommentPanel.add(scrollPane2);
					} catch (SQLException x) {

					}
					
					//新增服務評論
					serviceLabel.setBounds(40, 110, 70, 30);
					serviceLabel.setFont(new Font("微軟正黑體", Font.BOLD, 22));
					addServicePanel.add(serviceLabel);
					
					final JComboBox serviceComboBox = new JComboBox<String>();
					serviceComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
					serviceComboBox.setBounds(120, 110, 100, 30);
					addServicePanel.add(serviceComboBox);
					
					String[] choice = { "服務態度", "用餐氣氛", "設備", "裝潢" };
					for (int i = 0; i < choice.length; i++) {
						serviceComboBox.addItem(choice[i]);
					}
					
					JLabel commentLabel = new JLabel("評論：");
					commentLabel.setFont(new Font("微軟正黑體", Font.BOLD, 22));
					commentLabel.setBounds(40, 150, 70, 30);
					addServicePanel.add(commentLabel);
					
					final JTextArea commentTextArea = new JTextArea();
					commentTextArea.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
					commentTextArea.setBounds(40, 190, 315, 235);
					commentTextArea.setBackground(Color.lightGray);
					addServicePanel.add(commentTextArea);		

					JButton backBtn_addServiceComment = new JButton("返回");
					backBtn_addServiceComment.setFont(new Font("微軟正黑體", Font.BOLD, 14));
					backBtn_addServiceComment.setBounds(10, 10, 70, 25);
					backBtn_addServiceComment.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cardLayout.show(cardPanel, "6");
							commentTextArea.setText("");
						}
					});
					addServicePanel.add(backBtn_addServiceComment);
					
					JButton confirmBtn_addServiceComment = new JButton("確認送出");
					confirmBtn_addServiceComment.setBounds(215, 435, 140, 40);
					confirmBtn_addServiceComment.setFont(new Font("微軟正黑體", Font.BOLD, 22));
					confirmBtn_addServiceComment.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								String getShopID = "SELECT `shopID` FROM final.shop_information WHERE `shopname`= ?";
								PreparedStatement getshopitemID = con.prepareStatement(getShopID);
								getshopitemID.setString(1, selected_cafe); 
								ResultSet re = getshopitemID.executeQuery();
								re.next();
								id=re.getString("shopID");
								
								String rqaddcomment = "INSERT INTO final.servicecomment (userID,shopID,shopname,service,commentID,serviceComment) VALUES(?,?,?,?,?,?)";
								PreparedStatement addComm = con.prepareStatement(rqaddcomment);

								addComm.setString(1, userID); 
								addComm.setString(2, id); //改！
								addComm.setString(3, selected_cafe); 
								addComm.setString(4, (String) serviceComboBox.getSelectedItem());

								// comment ID 
								String query2 = "SELECT * FROM final.servicecomment WHERE `userID`= ? ORDER BY `commentID` DESC LIMIT 0,1"; // 最後一筆資料
								PreparedStatement getLastComment = con.prepareStatement(query2);
								getLastComment.setString(1, userID);
								ResultSet result2 = getLastComment.executeQuery();

								if (result2.next()) {
									System.out.println("新增服務評論:"+String.valueOf(result2.getInt("commentID") + 1));
									addComm.setString(5, String.valueOf(result2.getInt("commentID") + 1)); // 最後一個commentID + 1
								} else {
									addComm.setString(5, "1");
								}
								addComm.setString(6, commentTextArea.getText());

								//確認新增
								int check = JOptionPane.showConfirmDialog(null,
										String.format("確定要對%s新增此評論嗎?\n\n品名：%s\n評論：%s", selected_cafe, serviceComboBox.getSelectedItem(),
												commentTextArea.getText()),
										"確認訊息", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
								if (check == JOptionPane.YES_OPTION) {
									addComm.executeUpdate();
									JOptionPane.showMessageDialog(null, "SUCCESS", "新增成功！", JOptionPane.PLAIN_MESSAGE);
									commentTextArea.setText("");
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
					addServicePanel.add(confirmBtn_addServiceComment);
					
					//新增品項評論
					itemLabel.setBounds(40, 110, 70, 30);
					itemLabel.setFont(new Font("微軟正黑體", Font.BOLD, 22));
					addItemPanel.add(itemLabel);
					
					final JComboBox itemComboBox = new JComboBox<String>();
					itemComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
					itemComboBox.setBounds(120, 110, 100, 30);
					try {
						System.out.println(selected_cafe+id);
						String rq1 = "SELECT `ItemName` FROM final.item WHERE `ShopID`= ?";
						PreparedStatement stat = con.prepareStatement(rq1);
						stat.setString(1, id); // getShopID
						ResultSet rs1 = stat.executeQuery();

						for (int i = 1; i < 100; i++) {
							while (rs1.next()) {
								itemComboBox.addItem(rs1.getString(i));
							}
						}
						rs1.close();
					} catch (SQLException ex) {

					}
					addItemPanel.add(itemComboBox);
					
					commentLabel_addItemComment.setFont(new Font("微軟正黑體", Font.BOLD, 22));
					commentLabel_addItemComment.setBounds(40, 150, 70, 30);
					addItemPanel.add(commentLabel_addItemComment);

					final JTextArea commentTextArea_addItemComment = new JTextArea();
					commentTextArea_addItemComment.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
					commentTextArea_addItemComment.setBounds(40, 190, 315, 235);
					commentTextArea_addItemComment.setBackground(Color.lightGray);
					addItemPanel.add(commentTextArea_addItemComment);
							
					JButton backBtn_addItemComment = new JButton("返回");
					backBtn_addItemComment.setFont(new Font("微軟正黑體", Font.BOLD, 14));
					backBtn_addItemComment.setBounds(10, 10, 70, 25);
					backBtn_addItemComment.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cardLayout.show(cardPanel, "6");
							commentTextArea_addItemComment.setText("");
						}
					});
					addItemPanel.add(backBtn_addItemComment);
					
					JButton confirmBtn_addItemComment = new JButton("確認送出");
					confirmBtn_addItemComment.setBounds(215, 435, 140, 40);
					confirmBtn_addItemComment.setFont(new Font("微軟正黑體", Font.BOLD, 22));
					confirmBtn_addItemComment.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {							
								String rqaddcomment = "INSERT INTO final.itemcomment (userID,shopID,shopname,itemID,item,commentID,itemcomment) VALUES(?,?,?,?,?,?,?)";
								PreparedStatement addComm = con.prepareStatement(rqaddcomment);

								addComm.setString(1, userID); 
								addComm.setString(2, id); //改！！
								addComm.setString(3, (String)list_cafe.getSelectedValue()); 

								// Item ID
								String getItemID = "SELECT `ItemID` FROM final.item WHERE `ShopID`= ? AND `ItemName`= ?";
								PreparedStatement getitemID = con.prepareStatement(getItemID);
								getitemID.setString(1, id); // 改！！
								getitemID.setString(2, (String) itemComboBox.getSelectedItem());
								ResultSet result = getitemID.executeQuery();

								result.next();
								addComm.setInt(4, result.getInt("ItemID"));
								addComm.setString(5, (String) itemComboBox.getSelectedItem());

								// comment ID
								String query2 = "SELECT * FROM final.itemcomment WHERE `userID`= ? ORDER BY `commentID` DESC LIMIT 0,1"; // 最後一筆資料
								PreparedStatement getLastComment = con.prepareStatement(query2);
								getLastComment.setString(1, userID);
								ResultSet result2 = getLastComment.executeQuery();

								if (result2.next()) {
									System.out.println("新增商品評論:"+String.valueOf(result2.getInt("commentID") + 1));
									addComm.setString(6, String.valueOf(result2.getInt("commentID")+1)); // 最後一個commentID + 1									
								} else {
									addComm.setString(6, "1");
								}
								addComm.setString(7, commentTextArea_addItemComment.getText());

								// 確認新增
								int check = JOptionPane.showConfirmDialog(null,
										String.format("確定要對此項商品新增此評論嗎?\n\n品名：%s\n評論：%s", itemComboBox.getSelectedItem(),
												commentTextArea_addItemComment.getText()),
										"確認訊息", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
								if (check == JOptionPane.YES_OPTION) {
									addComm.executeUpdate();
									JOptionPane.showMessageDialog(null, "SUCCESS", "新增成功！", JOptionPane.PLAIN_MESSAGE);
									commentTextArea_addItemComment.setText("");
								}
							} catch (SQLException e1) {

							}
						}
					});
					addItemPanel.add(confirmBtn_addItemComment);
				} catch (SQLException x) {
					System.out.println("Exception :" + x.toString());
				}
			}
		}
		ListSelectionListener listener_2 = new SharedListSelectionHandler_2();
		list_cafe.addListSelectionListener(listener_2);

		//評論選擇
		JLabel titleLabel_addComment = new JLabel("新增評論");
		titleLabel_addComment.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel_addComment.setBounds(20, 45, 126, 38);
		commentMenuPanel.add(titleLabel_addComment);
		
		JButton itemCommentBtn = new JButton("品項評論");
		itemCommentBtn.setBounds(118, 100, 150, 45);
		itemCommentBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		itemCommentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "7");
			}
		});
		commentMenuPanel.add(itemCommentBtn);
		
		JButton serviceCommentBtn = new JButton("服務評論");
		serviceCommentBtn.setBounds(118, 160, 150, 45);
		serviceCommentBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		serviceCommentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "8");
			}
		});
		commentMenuPanel.add(serviceCommentBtn);
		
		JButton backBtn_addCommentMenu = new JButton("返回");
		backBtn_addCommentMenu.setBounds(10, 10, 70, 25);
		backBtn_addCommentMenu.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		backBtn_addCommentMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "2");
			}
		});
		commentMenuPanel.add(backBtn_addCommentMenu);

		// Btn
		JButton cancelButton_cafe = new JButton("返回");
		cancelButton_cafe.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		cancelButton_cafe.setBounds(10, 10, 70, 25);
		cancelButton_cafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cardLayout.previous(cardPanel);
				// Viewer v=new Viewer();
				dispose();
			}
		});
		cafePanel.add(cancelButton_cafe);//先關掉 因為按了沒反應

		JButton cancelButton_info = new JButton("返回");
		cancelButton_info.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		cancelButton_info.setBounds(10, 10, 70, 25);
		cancelButton_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "1");

			}
		});
		contentPane.add(cancelButton_info);

		JButton cancelButton_serviceComment = new JButton("返回");
		cancelButton_serviceComment.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		cancelButton_serviceComment.setBounds(10, 10, 70, 25);
		cancelButton_serviceComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "2");
			}
		});
		serviceCommentPanel.add(cancelButton_serviceComment);

		JButton cancelButton_menu = new JButton("返回");
		cancelButton_menu.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		cancelButton_menu.setBounds(10, 10, 70, 25);
		cancelButton_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "2");
			}
		});
		menuPanel.add(cancelButton_menu);

		JButton cancelButton_itemComment = new JButton("返回");
		cancelButton_itemComment.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		cancelButton_itemComment.setBounds(10, 10, 70, 25);
		cancelButton_itemComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "4");
			}
		});
		itemCommentPanel.add(cancelButton_itemComment);

		JButton menuButton = new JButton("查看菜單");
		contentPane.add(menuButton);
		menuButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "4");
			}
		});
		menuButton.setBounds(130, 435, 100, 35);

		JButton checkServiceCommentBtn = new JButton("查看服務評論");
		checkServiceCommentBtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		checkServiceCommentBtn.setBounds(245, 435, 130, 35);
		contentPane.add(checkServiceCommentBtn);
		checkServiceCommentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "3");

				// 如果沒東西就跳回資訊頁
				String query2 = "SELECT COUNT(*) FROM `servicecomment` WHERE `shopname`= ?";
				try {
					PreparedStatement row = con.prepareStatement(query2);
					row.setString(1, selected_cafe);
					ResultSet r = row.executeQuery();
					r.next();

					int rowCount = r.getInt("COUNT(*)");
					if (rowCount == 0) {
						JOptionPane.showMessageDialog(null, "目前尚無該商家的相關服務評論！><", "查無資料", JOptionPane.ERROR_MESSAGE);
						cardLayout.show(cardPanel, "2");
					}
				} catch (SQLException x) {

				}
			}

		});

		JButton addCommentBtn = new JButton("新增評論");
		addCommentBtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		addCommentBtn.setBounds(15, 435, 100, 35);
		addCommentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "6");
			}
		});
		contentPane.add(addCommentBtn);

		// 加進cardPanel
		cardPanel.add(cafePanel, "1");
		cardPanel.add(contentPane, "2");		
		cardPanel.add(serviceCommentPanel, "3");
		cardPanel.add(menuPanel, "4");
		cardPanel.add(itemCommentPanel, "5");
		cardPanel.add(commentMenuPanel, "6");
		cardPanel.add(addItemPanel, "7");
		cardPanel.add(addServicePanel, "8");

		getContentPane().add(cardPanel);
	}

}
