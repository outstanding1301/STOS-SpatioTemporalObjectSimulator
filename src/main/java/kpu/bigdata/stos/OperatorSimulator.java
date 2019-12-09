package kpu.bigdata.stos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import kpu.bigdata.st.type.STObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OperatorSimulator extends JFrame {

	private JPanel contentPane;
	private LinkedList<STObject> objects = new LinkedList<>();
	private JTextArea txtrlog;
	int c_w[] = {-203, -170}; // 1264
	int c_h[] = {-117, -91}; // 563
	
	public OperatorSimulator() {
		setTitle("Operator Simulator");
		objects.add(STObject.getFromString("ST_OBJECT<name:o1> ((PERIOD (2019-11-06 18:53:07 ~ 2019-11-06 18:54:17), POLYGON ((-196.83987209316118 -111.99043011955311, -191.7230378225612 -111.99043011955311, -191.7230378225612 -106.98771796745513, -196.83987209316118 -106.98771796745513, -196.83987209316118 -111.99043011955311))))"));
		objects.add(STObject.getFromString("ST_OBJECT<name:o2> ((PERIOD (2019-11-06 18:53:07 ~ 2019-11-06 18:54:17), POLYGON ((-194.3849853309394 -111.67608287564249, -189.22973350803835 -111.67608287564249, -189.22973350803835 -104.23739790178081, -194.3849853309394 -104.23739790178081, -194.3849853309394 -111.67608287564249))))"));
		objects.add(STObject.getFromString("ST_OBJECT<name:o3> ((PERIOD (2019-11-06 18:53:07 ~ 2019-11-06 18:54:17), POLYGON ((-183.96062569179404 -110.02258261311228, -173.49541839024292 -110.02258261311228, -173.49541839024292 -100.10376571589654, -183.96062569179404 -100.10376571589654, -183.96062569179404 -110.02258261311228))))"));
		objects.add(STObject.getFromString("ST_OBJECT<name:o4> ((PERIOD (2019-11-06 18:53:07 ~ 2019-11-06 18:54:17), POLYGON ((-181.10353372160412 -108.08461056093486, -175.91330172958138 -108.08461056093486, -175.91330172958138 -102.91667282728193, -181.10353372160412 -102.91667282728193, -181.10353372160412 -108.08461056093486))))"));
		objects.add(STObject.getFromString("ST_OBJECT<name:player;> ((INSTANT (2019-11-06 18:53:07), POINT (-192.96994989108273 -92.638541739499)) (INSTANT (2019-11-06 18:53:08), POINT (-192.90679011943138 -95.18077663058526)) (INSTANT (2019-11-06 18:53:09), POINT (-192.8950116181033 -99.49682179941054)) (INSTANT (2019-11-06 18:53:10), POINT (-191.64314953618566 -103.04391955548672)) (INSTANT (2019-11-06 18:53:11), POINT (-189.53620054056944 -106.29742764639315)) (INSTANT (2019-11-06 18:53:12), POINT (-190.48166467552105 -105.7938959115035)) (INSTANT (2019-11-06 18:53:13), POINT (-192.4799850503796 -105.81000225010521)) (INSTANT (2019-11-06 18:53:14), POINT (-193.0641788156504 -108.42374259964484)) (INSTANT (2019-11-06 18:53:15), POINT (-193.40535068960736 -109.13730081387558)) (INSTANT (2019-11-06 18:53:16), POINT (-194.18038922086973 -110.99495989484684)) (INSTANT (2019-11-06 18:53:17), POINT (-196.15818980943826 -108.93187052477552)) (INSTANT (2019-11-06 18:53:18), POINT (-193.7439810022891 -108.06801520065255)) (INSTANT (2019-11-06 18:53:19), POINT (-192.65729955972446 -108.69999998807909)) (INSTANT (2019-11-06 18:53:20), POINT (-192.94547579489736 -111.67088381065072)) (INSTANT (2019-11-06 18:53:21), POINT (-192.7673749501763 -113.87618196754681)) (INSTANT (2019-11-06 18:53:22), POINT (-190.30739331568293 -116.87653664266978)) (INSTANT (2019-11-06 18:53:23), POINT (-188.63727983202887 -115.23820014946709)) (INSTANT (2019-11-06 18:53:24), POINT (-184.61264089189982 -114.86930850851363)) (INSTANT (2019-11-06 18:53:25), POINT (-182.28783594005517 -112.35423477739094)) (INSTANT (2019-11-06 18:53:26), POINT (-182.75853778479063 -112.0804146599873)) (INSTANT (2019-11-06 18:53:27), POINT (-182.68975383324883 -108.4208302873618)) (INSTANT (2019-11-06 18:53:28), POINT (-182.37641491072898 -106.20550816064147)) (INSTANT (2019-11-06 18:53:29), POINT (-179.79261563242846 -106.48819373265528)) (INSTANT (2019-11-06 18:53:30), POINT (-179.06012278789976 -106.50620742951166)) (INSTANT (2019-11-06 18:53:31), POINT (-177.96509100244714 -105.30024171806869)) (INSTANT (2019-11-06 18:53:32), POINT (-178.61800225439544 -104.42074509540275)) (INSTANT (2019-11-06 18:53:33), POINT (-181.2279520698343 -104.48179468616313)) (INSTANT (2019-11-06 18:53:34), POINT (-184.09382157929966 -104.49733216286135)) (INSTANT (2019-11-06 18:53:35), POINT (-184.64338013884935 -104.4997489192147)) (INSTANT (2019-11-06 18:53:36), POINT (-188.7805642968982 -104.3090989076216)) (INSTANT (2019-11-06 18:53:37), POINT (-191.0899453684312 -103.73681699961726)) (INSTANT (2019-11-06 18:53:38), POINT (-193.69999998807907 -105.33288452799428)) (INSTANT (2019-11-06 18:53:39), POINT (-194.1207275072402 -107.6565345996779)) (INSTANT (2019-11-06 18:53:40), POINT (-193.42988299903752 -111.23809975139888)) (INSTANT (2019-11-06 18:53:41), POINT (-191.46365744210692 -111.03968066559038)) (INSTANT (2019-11-06 18:53:42), POINT (-191.22056029858675 -108.07870046774518)) (INSTANT (2019-11-06 18:53:43), POINT (-190.09955365082058 -105.71627327595789)) (INSTANT (2019-11-06 18:53:44), POINT (-190.3390681057842 -105.72112207591718)) (INSTANT (2019-11-06 18:53:45), POINT (-189.28954607108608 -104.24381067441402)) (INSTANT (2019-11-06 18:53:46), POINT (-190.4593217331757 -103.71599788320556)) (INSTANT (2019-11-06 18:53:47), POINT (-190.53268305738874 -103.89847843898542)) (INSTANT (2019-11-06 18:53:48), POINT (-187.32449126818815 -104.17208114481824)) (INSTANT (2019-11-06 18:53:49), POINT (-182.30000001192093 -104.22161948562642)) (INSTANT (2019-11-06 18:53:50), POINT (-180.52060801614306 -105.5405137226212)) (INSTANT (2019-11-06 18:53:51), POINT (-177.63214028190126 -108.08723081376118)) (INSTANT (2019-11-06 18:53:52), POINT (-179.2636345192347 -113.08002458201008)) (INSTANT (2019-11-06 18:53:53), POINT (-179.56212816745168 -118.72204182734443)) (INSTANT (2019-11-06 18:53:54), POINT (-184.70470329242127 -118.44969311483932)) (INSTANT (2019-11-06 18:53:55), POINT (-188.0230063579146 -112.88414381698622)) (INSTANT (2019-11-06 18:53:56), POINT (-190.2388786083222 -107.94967684346543)) (INSTANT (2019-11-06 18:53:57), POINT (-194.10012999451405 -108.33441140003508)) (INSTANT (2019-11-06 18:53:58), POINT (-199.95219613014055 -110.24403411516508)) (INSTANT (2019-11-06 18:53:59), POINT (-201.94228379134177 -110.85154393099168)) (INSTANT (2019-11-06 18:54:00), POINT (-199.31141133242105 -108.36878743161608)) (INSTANT (2019-11-06 18:54:01), POINT (-193.16970446131737 -108.91513110066275)) (INSTANT (2019-11-06 18:54:02), POINT (-186.56963484006624 -109.09449344771743)) (INSTANT (2019-11-06 18:54:03), POINT (-180.38150340744022 -108.78680058794782)) (INSTANT (2019-11-06 18:54:04), POINT (-178.56837384359764 -104.46360158284051)) (INSTANT (2019-11-06 18:54:05), POINT (-180.02837559990715 -106.95469113037085)) (INSTANT (2019-11-06 18:54:06), POINT (-185.43247930203466 -107.75692843119175)) (INSTANT (2019-11-06 18:54:07), POINT (-191.75498778359452 -105.26211608382431)) (INSTANT (2019-11-06 18:54:08), POINT (-195.77908236996691 -105.04562060325154)) (INSTANT (2019-11-06 18:54:09), POINT (-199.59966583886165 -106.99306102681689)) (INSTANT (2019-11-06 18:54:10), POINT (-195.7041279026669 -111.20744647663098)) (INSTANT (2019-11-06 18:54:11), POINT (-192.30000001192093 -110.81717386845295)) (INSTANT (2019-11-06 18:54:12), POINT (-188.271865448836 -110.64885221306267)) (INSTANT (2019-11-06 18:54:13), POINT (-182.24873651717346 -108.22906519625414)) (INSTANT (2019-11-06 18:54:14), POINT (-178.01266226625307 -104.43085100115836)) (INSTANT (2019-11-06 18:54:15), POINT (-175.41331401662953 -98.9402390769543)) (INSTANT (2019-11-06 18:54:16), POINT (-175.94125105158358 -93.51228820025956)) (PERIOD (2019-11-06 18:54:17 ~ 2019-11-06 18:53:07), POINT (-176.05752266181085 -93.39070750052223)))"));
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		txtrlog = new JTextArea();
		txtrlog.setEditable(false);
		txtrlog.setText("[Log]");
		

		JScrollPane scroll = new JScrollPane(txtrlog);
		getContentPane().add(scroll, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		JMenu mnObjects = new JMenu("Objects");
		mnObjects.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ObjectsDialog bd = new ObjectsDialog(objects);
				bd.setVisible(true);
				mnObjects.setSelected(false);
			}
		});
		menuBar.add(mnObjects);
		
		JMenu mnOperation = new JMenu("Operation");
		menuBar.add(mnOperation);
		
		JMenu mnRelations = new JMenu("Relations");
		mnOperation.add(mnRelations);
		
		JMenuItem mntmEquals = new JMenuItem("equals");
		mntmEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new OperatorDialog(OperatorSimulator.this, Operator.rEQUALS, objects);
			}
		});
		mnRelations.add(mntmEquals);
		
		JMenuItem mntmCrosses = new JMenuItem("crosses");
		mntmCrosses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperatorDialog(OperatorSimulator.this, Operator.rCROSSES, objects);
			}
		});
		mnRelations.add(mntmCrosses);
		
		JMenuItem mntmContains = new JMenuItem("contains");
		mntmContains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperatorDialog(OperatorSimulator.this, Operator.rCONTAINS, objects);
			}
		});
		mnRelations.add(mntmContains);
		
		JMenuItem mntmDisjoint = new JMenuItem("disjoint");
		mntmDisjoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperatorDialog(OperatorSimulator.this, Operator.rDISJOINT, objects);
			}
		});
		mnRelations.add(mntmDisjoint);
		
		JMenuItem mntmTouches = new JMenuItem("touches");
		mntmTouches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperatorDialog(OperatorSimulator.this, Operator.rTOUCHES, objects);
			}
		});
		mnRelations.add(mntmTouches);
		
		JMenuItem mntmOverlaps = new JMenuItem("overlaps");
		mntmOverlaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperatorDialog(OperatorSimulator.this, Operator.rOVERLAPS, objects);
			}
		});
		mnRelations.add(mntmOverlaps);
		
		JMenu mnTrajectories = new JMenu("Trajectories");
		mnOperation.add(mnTrajectories);
		
		JMenuItem mntmEnters = new JMenuItem("enters");
		mntmEnters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperatorDialog(OperatorSimulator.this, Operator.tENTERS, objects);
			}
		});
		mnTrajectories.add(mntmEnters);
		
		JMenuItem mntmInsides = new JMenuItem("insides");
		mntmInsides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperatorDialog(OperatorSimulator.this, Operator.tINSIDES, objects);
			}
		});
		mnTrajectories.add(mntmInsides);
		
		JMenuItem mntmLeaves = new JMenuItem("leaves");
		mntmLeaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperatorDialog(OperatorSimulator.this, Operator.tLEAVES, objects);
			}
		});
		mnTrajectories.add(mntmLeaves);
		
		JMenuItem mntmMeets = new JMenuItem("meets");
		mntmMeets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperatorDialog(OperatorSimulator.this, Operator.tMEETS, objects);
			}
		});
		mnTrajectories.add(mntmMeets);
		
		JMenuItem mntmPasses = new JMenuItem("passes");
		mntmPasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperatorDialog(OperatorSimulator.this, Operator.tPASSES, objects);
			}
		});
		mnTrajectories.add(mntmPasses);
		
		JMenu mnSetting = new JMenu("Setting");
		mnSetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int border[] = {c_w[0], c_w[1], c_h[0], c_h[1]};
				SettingDialog sd = new SettingDialog(OperatorSimulator.this, border);
				sd.setVisible(true);;
				mnSetting.setSelected(false);
			}
		});
		menuBar.add(mnSetting);

		setVisible(true);
		setResizable(false);
	}
	public void writeLog(String log){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd HH:mm:ss");
		txtrlog.append("\n["+sdf.format(d)+"] "+log);
		System.out.println("[OS]["+sdf.format(d)+"] "+log);
		txtrlog.setCaretPosition(txtrlog.getDocument().getLength());
	}
	public void setBound(int w1, int w2, int h1, int h2){
		this.c_w[0] = w1;
		this.c_w[1] = w2;
		this.c_h[0] = h1;
		this.c_h[1] = h2;
	}
}
