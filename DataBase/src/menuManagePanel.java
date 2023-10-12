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

public class menuManagePanel extends JPanel {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	private JButton backBtn, addBtn, updateBtn, deleteBtn;
	private JLabel titleLabel;

	public menuManagePanel() throws SQLException {
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
		titleLabel = new JLabel("菜單管理");
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel.setBounds(20, 45, 126, 38);
	}

	public void creaBtn() {
		backBtn = new JButton("返 回");
		backBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		backBtn.setBounds(10, 10, 70, 25);

		addBtn = new JButton("新增品項");
		addBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		addBtn.setBounds(125, 160, 150, 45);

		updateBtn = new JButton("更新品項");
		updateBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		updateBtn.setBounds(125, 220, 150, 45);

		deleteBtn = new JButton("刪除品項");
		deleteBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		deleteBtn.setBounds(125, 280, 150, 45);
	}

	public void addBtnListener(final JPanel panel1) {
		// 返回
		class ClickListener_back implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "MainManage");
			}
		}
		ActionListener backbtn = new ClickListener_back();
		backBtn.addActionListener(backbtn);

		class ClickListener_add implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "Add");
			}
		}
		ActionListener addbtn = new ClickListener_add();
		addBtn.addActionListener(addbtn);

		class ClickListener_update implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "Update");
				try {
					updatePanel u = new updatePanel();
					u.listQuery();
				} catch (SQLException e1) {

				}

			}
		}
		ActionListener updatebtn = new ClickListener_update();
		updateBtn.addActionListener(updatebtn);

		class ClickListener_delete implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) (panel1.getLayout());
				card.show(panel1, "Delete");
				try {
					deletePanel d = new deletePanel();
					d.listQuery();
				} catch (SQLException e1) {

				}

			}
		}
		ActionListener deletebtn = new ClickListener_delete();
		deleteBtn.addActionListener(deletebtn);
	}

	public void creaTotalPanel() {
		setLayout(null);
		add(titleLabel);
		add(backBtn);
		add(addBtn);
		add(updateBtn);
		add(deleteBtn);
	}
}
