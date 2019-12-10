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
