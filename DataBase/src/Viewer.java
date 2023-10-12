
import java.awt.CardLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Viewer {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("Tester");
		CardLayout card = new CardLayout();
		JPanel panel = new JPanel(card);

		Main m = new Main();
		login l = new login();
		login2 l2 = new login2();
		signup1 s1 = new signup1();
		signup2 s2 = new signup2();
		MainManage ma = new MainManage();
		menuManagePanel me = new menuManagePanel();
		addPanel a = new addPanel();
		updatePanel u = new updatePanel();
		deletePanel d = new deletePanel();
		shopCheckCommentPanel scc = new shopCheckCommentPanel();
		userFunctionPanel user = new userFunctionPanel();
		addCommentMenuPanel acm = new addCommentMenuPanel();
		addItemCommentPanel aic = new addItemCommentPanel();
		addServiceCommentPanel asc = new addServiceCommentPanel();
		checkOwnCommentPanel coc = new checkOwnCommentPanel();

		frame.add(panel);

		panel.add(m, "Main");
		panel.add(l, "login");
		panel.add(l2, "login2");
		panel.add(s1, "signup1");
		panel.add(s2, "signup2");
		panel.add(ma, "MainManage");
		panel.add(me, "MenuManage");
		panel.add(a, "Add");
		panel.add(u, "Update");
		panel.add(d, "Delete");
		panel.add(scc, "shopCheckComment");
		panel.add(user, "userFunction");
		panel.add(acm, "addComment");
		panel.add(aic, "addItemComment");
		panel.add(asc, "addServiceComment");
		panel.add(coc, "checkOwnComment");

		frame.add(panel);

		m.addBtnListener(panel);
		l.addBtnListener(panel);
		l2.addBtnListener(panel);
		s1.addBtnListener(panel);
		s2.addBtnListener(panel);
		me.addBtnListener(panel);
		a.addBtnListener(panel);
		u.addBtnListener(panel);
		d.addBtnListener(panel);
		ma.addBtnListener(panel);
		scc.addBtnListener(panel);
		user.addBtnListener(panel);
		acm.addBtnListener(panel);
		aic.addBtnListener(panel);
		asc.addBtnListener(panel);
		coc.addBtnListener(panel);

		frame.setSize(400, 520);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
