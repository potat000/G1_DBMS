import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class test_new extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel cardPanel, startpanel, addpanel, modifypanel, buttonPanel;
    //private JLabel chooseLabel, addLabel, modifyLabel;
    private JButton addbtn, modifybtn;
    private CardLayout cardLayout = new CardLayout();
     
    public test_new() {
        setTitle("功能選擇");
        setSize(400, 520);
        cardPanel = new JPanel();
        cardPanel.setSize(400, 520);
        buttonPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        startpanel = new JPanel();
        addpanel = new JPanel();
        modifypanel = new JPanel();
        //chooseLabel = new JLabel("功能選擇");
        //addLabel = new JLabel("資料新增");
        //modifyLabel = new JLabel("資料修改");
        //startpanel.add(chooseLabel);
        startpanel.setLayout(null);
        addpanel.setLayout(null);
        modifypanel.setLayout(null);
        
        //新增資料介面
        //addpanel.add(addLabel);
        //addpanel.setSize(400, 520);
        JLabel info_Label = new JLabel("咖啡廳資訊");
        info_Label.setFont(new Font("微軟正黑體", Font.BOLD, 17));
		info_Label.setBounds(10, 20, 140, 30);
		addpanel.add(info_Label);
		
		JLabel nameLabel = new JLabel("店名:");
		nameLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		nameLabel.setBounds(35, 55, 60, 30);
		addpanel.add(nameLabel);
		
		final JTextField nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(75, 60, 220, 20);
		addpanel.add(nameField);
		String shopname = nameField.getText();
		
		JLabel addressLabel = new JLabel("地址:");
		addressLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		addressLabel.setBounds(35, 90, 60, 40);
		addpanel.add(addressLabel);
		
		final JTextField addressField = new JTextField();
		addressField.setColumns(20);
		addressField.setBounds(75, 100, 220, 20);
		addpanel.add(addressField);
		String address = addressField.getText();
		
		JLabel phoneLabel = new JLabel("電話:");
		phoneLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		phoneLabel.setBounds(35, 130, 65, 40);
		addpanel.add(phoneLabel);
		
		final JTextField phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(75, 140, 220, 20);
		addpanel.add(phoneField);
		String phone = phoneField.getText();
		
		JLabel timeLabel = new JLabel("營業時間:");
		timeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		timeLabel.setBounds(35, 170, 115, 40);
		addpanel.add(timeLabel);
		
		JLabel openLabel = new JLabel("OPEN:");
		openLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		openLabel.setBounds(55, 210, 60, 40);
		addpanel.add(openLabel);

		final JComboBox<String> open_choice = new JComboBox<String>();
		open_choice.addItem("請選擇");
		open_choice.addItem("7:00");
		open_choice.addItem("7:30");
		open_choice.addItem("8:00");
		open_choice.addItem("8:30");
		open_choice.addItem("9:00");
		open_choice.addItem("9:30");
		open_choice.addItem("10:00");
		open_choice.addItem("10:30");
		open_choice.addItem("11:00");
		open_choice.addItem("11:30");
		open_choice.addItem("12:00");
		open_choice.addItem("12:30");
		open_choice.addItem("13:00");
		open_choice.addItem("13:30");
		open_choice.addItem("14:00");
		open_choice.addItem("14:30");
		open_choice.addItem("15:00");
		open_choice.addItem("15:30");
		open_choice.addItem("16:00");
		open_choice.addItem("16:30");
		open_choice.addItem("17:00");
		open_choice.addItem("17:30");
		open_choice.addItem("18:00");
		open_choice.addItem("18:30");
		open_choice.addItem("19:00");
		open_choice.setBounds(115, 220, 180, 25);
		open_choice.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		addpanel.add(open_choice);
		open_choice.getSelectedItem();

		JLabel closeLabel = new JLabel("CLOSE:");
		closeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		closeLabel.setBounds(55, 250, 60, 40);
		addpanel.add(closeLabel);
		
		final JComboBox<String> close_choice = new JComboBox<String>();
		close_choice.addItem("請選擇");
		close_choice.addItem("12:00");
		close_choice.addItem("12:30");
		close_choice.addItem("13:00");
		close_choice.addItem("13:30");
		close_choice.addItem("14:00");
		close_choice.addItem("14:30");
		close_choice.addItem("15:00");
		close_choice.addItem("15:30");
		close_choice.addItem("16:00");
		close_choice.addItem("16:30");
		close_choice.addItem("17:00");
		close_choice.addItem("17:30");
		close_choice.addItem("18:00");
		close_choice.addItem("18:30");
		close_choice.addItem("19:00");
		close_choice.addItem("19:30");
		close_choice.addItem("20:00");
		close_choice.addItem("20:30");
		close_choice.addItem("21:00");
		close_choice.addItem("21:30");
		close_choice.addItem("22:00");
		close_choice.addItem("22:30");
		close_choice.addItem("23:00");
		close_choice.addItem("23:30");
		close_choice.addItem("00:00");
		close_choice.setBounds(115, 260, 180, 25);
		close_choice.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		addpanel.add(close_choice);
		close_choice.getSelectedItem();

		JLabel otherLabel = new JLabel("其他資訊:");
		otherLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		otherLabel.setBounds(35, 285, 80, 40);
		addpanel.add(otherLabel);

	    JLabel WIFI_Label = new JLabel("提供WIFI");
	    WIFI_Label.setFont(new Font("微軟正黑體", Font.BOLD, 15));
	    ButtonGroup bg1 = new ButtonGroup();
	    final JRadioButton WIFI_RB_Yes = new JRadioButton("有");
	    final JRadioButton WIFI_RB_No = new JRadioButton("無");
	    WIFI_RB_Yes.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
	    WIFI_RB_No.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
	    WIFI_Label.setBounds(55, 320, 100, 20);
	    WIFI_RB_Yes.setBounds(150,320, 40, 20);
	    WIFI_RB_No.setBounds(190,320,40,20);
	    bg1.add(WIFI_RB_No);
	    bg1.add(WIFI_RB_Yes);
	    addpanel.add(WIFI_Label);
	    addpanel.add(WIFI_RB_No);
	    addpanel.add(WIFI_RB_Yes);
	    
	    JLabel time_Label = new JLabel("有無限時");
	    time_Label.setFont(new Font("微軟正黑體", Font.BOLD, 15));
	    ButtonGroup bg2 = new ButtonGroup();
	    final JRadioButton time_RB_Yes = new JRadioButton("有");
	    final JRadioButton time_RB_No = new JRadioButton("無");
	    time_RB_Yes.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
	    time_RB_No.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
	    time_Label.setBounds(55, 350, 100, 20);
	    time_RB_Yes.setBounds(150,350, 40, 20);
	    time_RB_No.setBounds(190,350,40,20);
	    bg2.add(time_RB_No);
	    bg2.add(time_RB_Yes);
	    addpanel.add(time_Label);
	    addpanel.add(time_RB_No);
	    addpanel.add(time_RB_Yes);
	    
	    JLabel order_Label = new JLabel("最低消費");
	    order_Label.setFont(new Font("微軟正黑體", Font.BOLD, 15));
	    ButtonGroup bg3 = new ButtonGroup();
	    final JRadioButton order_RB_Yes = new JRadioButton("有");
	    final JRadioButton order_RB_No = new JRadioButton("無");
	    order_RB_Yes.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
	    order_RB_No.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
	    order_Label.setBounds(55, 380, 100, 20);
	    order_RB_Yes .setBounds(150,380, 40, 20);
	    order_RB_No.setBounds(190,380,40,20);
	    bg3.add(order_RB_No);
	    bg3.add(order_RB_Yes);
	    addpanel.add(order_Label);
	    addpanel.add(order_RB_No);
	    addpanel.add(order_RB_Yes);

	    JLabel socket_Label = new JLabel("提供插座");
	    socket_Label.setFont(new Font("微軟正黑體", Font.BOLD, 15));
	    ButtonGroup bg4 = new ButtonGroup();
	    final JRadioButton socket_RB_Yes = new JRadioButton("有");
	    final JRadioButton socket_RB_No = new JRadioButton("無");
	    socket_RB_Yes.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
	    socket_RB_No.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
	    socket_Label.setBounds(55, 410, 100, 20);
	    socket_RB_Yes .setBounds(150,410, 40, 20);
	    socket_RB_No.setBounds(190,410,40,20);
	    bg4.add(socket_RB_No);
	    bg4.add(socket_RB_Yes);
	    addpanel.add(socket_Label);
	    addpanel.add(socket_RB_No);
	    addpanel.add(socket_RB_Yes);
		
		JButton comfirmButton = new JButton("確定");
		comfirmButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		comfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				  //Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final?user=abcd&password=1234&autoReconnect=true");
			        Statement statement = con.createStatement();  
			        ResultSet result = statement.executeQuery("show databases;");
			        System.out.println("Connected");  
			        
			        String wifi="";
			        if (WIFI_RB_Yes.isSelected()) {
			            wifi = WIFI_RB_Yes.getText();
			        } else if (WIFI_RB_No.isSelected()) { 
			        	wifi = WIFI_RB_No.getText();
			        }
			        String socket="";
			        if (socket_RB_Yes.isSelected()) {
			        	socket = socket_RB_Yes.getText();
			        } else if (socket_RB_No.isSelected()) { 
			        	socket = socket_RB_No.getText();
			        }
			        String time_limit="";
			        if (time_RB_Yes.isSelected()) {
			        	time_limit = time_RB_Yes.getText();
			        } else if (time_RB_No.isSelected()) { 
			        	time_limit = time_RB_No.getText();
			        }
			        String order="";
			        if (order_RB_Yes.isSelected()) {
			        	order = order_RB_Yes.getText();
			        } else if (order_RB_No.isSelected()) { 
			        	order = order_RB_No.getText();
			        }
			        
			     // shop ID
			        String id="";
					String query2 = "SELECT * FROM final.shop_information ORDER BY `shopID` DESC LIMIT 0,1"; // 最後一筆資料
					PreparedStatement getLastID = con.prepareStatement(query2);
					ResultSet result2 = getLastID.executeQuery();

					if (result2.next()) {
						id=String.valueOf(Integer.parseInt(result2.getString("shopID"))+1);// 最後一個shopID + 1
					} else {
						id="1";
					}
					
			        String query = "INSERT INTO `shop_information`\n" + 
			                       "VALUES ('"+ id +"','"+ nameField.getText() +"','"+ addressField.getText() +"','"+ phoneField.getText() +"','"
	        		                          + open_choice.getSelectedItem() +"','"+ close_choice.getSelectedItem() +"','"
	        		                          + wifi +"','"+ socket +"','"+ time_limit +"','"+ order +"');";
			        		             
			           
					PreparedStatement addDataStat = con.prepareStatement(query);
				    addDataStat.executeUpdate(query);
				    
					JOptionPane.showMessageDialog(null,"輸入成功!");
					statement.close();
					//dispose();
					addpanel.setVisible(false);
	                buttonPanel.setVisible(true);
				}
//				catch(ClassNotFoundException e) { 
//					System.out.println("DriverClassNotFound :"+ e.toString()); 
//				}
				catch(SQLException x) { 
					System.out.println("Exception :"+ x.toString()); 
				}
			}
		});
		comfirmButton.setBounds(230, 440, 70, 32);
		addpanel.add(comfirmButton);
		
		JButton cancelButton = new JButton("取消");
		cancelButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"確認取消!");
				addpanel.setVisible(false);
                buttonPanel.setVisible(true);
			}
		});
		cancelButton.setBounds(305, 440, 70, 32);
		addpanel.add(cancelButton);
		
		//修改資料介面
        //modifypanel.add(modifyLabel);
        //modifypanel.setSize(400, 520);
		JLabel shopnameLabel = new JLabel("店名:");
		shopnameLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		shopnameLabel.setBounds(30, 73, 50, 30);
		modifypanel.add(shopnameLabel);
		
		final JTextField nameTextField = new JTextField();
		nameTextField.setBounds(110, 75, 150, 25);
		modifypanel.add(nameTextField);
		nameTextField.setColumns(10);
		String text = nameTextField.getText();
		
		JLabel chooseLabel = new JLabel("修改項目：");
		chooseLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		chooseLabel.setBounds(30, 117, 95, 30);
		modifypanel.add(chooseLabel);
		
		final JComboBox<String> choosebox = new JComboBox<String>();
		choosebox.addItem("請選擇");
		choosebox.addItem("地址");
		choosebox.addItem("電話");
		choosebox.addItem("開始營業時間");
		choosebox.addItem("結束營業時間");
		//choosebox.addItem("other");
		choosebox.setBounds(110, 120, 200, 25);
		choosebox.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		modifypanel.add(choosebox);
		choosebox.getSelectedItem();
		
		JLabel TextLabel = new JLabel("修改內容：");
		TextLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		TextLabel.setBounds(30, 157, 95, 30);
		modifypanel.add(TextLabel);
		
		final JTextField TextField = new JTextField("");
		TextField.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		TextField.setBounds(110, 160, 200, 25);
		TextField.setColumns(20);
		modifypanel.add(TextField);
		String field = TextField.getText();
		
		JButton backButton = new JButton("返回");
		backButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "1");
				modifypanel.setVisible(false);
                buttonPanel.setVisible(true);
			}
		});
		backButton.setBounds(10, 10, 75, 25);
		modifypanel.add(backButton);
		
		JButton confirmButton = new JButton("確認");
		confirmButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"確認送出!");
				try{
					  //Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final?user=abcd&password=1234&autoReconnect=true");
				        Statement statement = con.createStatement();  
				        ResultSet result = statement.executeQuery("show databases;");
				        System.out.println("Connected");  
				      
				        //String query = "UPDATE `information` SET address = '台北市文山區指南路二段2樓56號' WHERE shopname = '小公寓'";
				        if(choosebox.getSelectedItem() == "地址") {
				        	String query = "UPDATE `shop_information` SET address = '"+ TextField.getText() + "' WHERE shopname = '"+ nameTextField.getText() +"'";
							PreparedStatement addDataStat = con.prepareStatement(query);
						    addDataStat.executeUpdate(query);
				        }
				        if(choosebox.getSelectedItem() == "電話") {
				        	String query = "UPDATE `shop_information` SET telephone = '"+ TextField.getText() + "' WHERE shopname = '"+ nameTextField.getText() +"'";
							PreparedStatement addDataStat = con.prepareStatement(query);
						    addDataStat.executeUpdate(query);
				        }
				        if(choosebox.getSelectedItem() == "開始營業時間") {
				        	String query = "UPDATE `shop_information` SET open_time = '"+ TextField.getText() + "' WHERE shopname = '"+ nameTextField.getText() +"'";
							PreparedStatement addDataStat = con.prepareStatement(query);
						    addDataStat.executeUpdate(query);
				        }
				        if(choosebox.getSelectedItem() == "結束營業時間") {
				        	String query = "UPDATE `shop_information` SET open_time = '"+ TextField.getText() + "' WHERE shopname = '"+ nameTextField.getText() +"'";
							PreparedStatement addDataStat = con.prepareStatement(query);
						    addDataStat.executeUpdate(query);
				        }
				     
//						JOptionPane.showMessageDialog(null,"輸入成功!");
						statement.close();
						//dispose();
						modifypanel.setVisible(false);
		                buttonPanel.setVisible(true);
					}
//					catch(ClassNotFoundException e) { 
//						System.out.println("DriverClassNotFound :"+ e.toString()); 
//					}
					catch(SQLException x) { 
						System.out.println("Exception :"+ x.toString()); 
					}
			}
		});
		confirmButton.setBounds(220, 435, 65, 30);
		modifypanel.add(confirmButton);
		
		JButton cancelButton_1 = new JButton("取消");
		cancelButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		cancelButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"確認取消!");
				modifypanel.setVisible(false);
                buttonPanel.setVisible(true);
			}
		});
		cancelButton_1.setBounds(295, 435, 65, 30);
		modifypanel.add(cancelButton_1);
        
		
		//加進cardpanel裡面
        cardPanel.add(startpanel, "1");
        cardPanel.add(addpanel, "2");
        cardPanel.add(modifypanel, "3");
        
        JButton backBtn = new JButton("返 回");
        backBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        backBtn.setBounds(10, 10, 75, 25);
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        startpanel.add(backBtn);
        
        addbtn = new JButton("新增資料");
        addbtn.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        addbtn.setBounds(100, 200, 170, 50);
        addbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "2");
                buttonPanel.setVisible(false);
            }
        });
        modifybtn = new JButton("修改資料");
        modifybtn.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        modifybtn.setBounds(100, 280, 170, 50);
        modifybtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "3");
                buttonPanel.setVisible(false);
            }
        });
        buttonPanel.add(addbtn);
        buttonPanel.add(modifybtn);
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
            	test_new frame = new test_new();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}


