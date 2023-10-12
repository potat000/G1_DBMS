import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class addCommentMenuPanel extends JPanel {

	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 520;

	private JButton itemCommentBtn, serviceCommentBtn, backBtn;
	private JLabel titleLabel;

	public addCommentMenuPanel() throws SQLException {
		initialize();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void initialize() throws SQLException {
		creaTitalLabel();
		creaBtn();
		creaTotalPanel();
	}

	public void creaTitalLabel() {
		titleLabel = new JLabel("新增評論");
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel.setBounds(20, 45, 126, 38);
	}

	public void creaBtn() {
		itemCommentBtn = new JButton("品項評論");
		itemCommentBtn.setBounds(118, 100, 150, 45);
		itemCommentBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));

		serviceCommentBtn = new JButton("服務評論");
		serviceCommentBtn.setBounds(118, 160, 150, 45);
		serviceCommentBtn.setFont(new Font("微軟正黑體", Font.BOLD, 22));

		backBtn = new JButton("返回");
		backBtn.setBounds(10, 10, 70, 25);
		backBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
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

		// 品項評論
		class ClickListener_item implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) (panel.getLayout());
				cardLayout.show(panel, "addItemComment");
			}
		}
		ActionListener itemBtn = new ClickListener_item();
		itemCommentBtn.addActionListener(itemBtn);

		// 服務評論
		class ClickListener_service implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) (panel.getLayout());
				cardLayout.show(panel, "addServiceComment");
			}
		}
		ActionListener serviceBtn = new ClickListener_service();
		serviceCommentBtn.addActionListener(serviceBtn);
	}

	public void creaTotalPanel() {
		setLayout(null);
		add(backBtn);
		add(titleLabel);
		add(itemCommentBtn);
		add(serviceCommentBtn);
	}
}
