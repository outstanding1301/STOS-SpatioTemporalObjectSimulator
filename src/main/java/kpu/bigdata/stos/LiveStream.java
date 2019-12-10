package kpu.bigdata.stos;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuListener;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import kpu.bigdata.st.type.STObject;
import kpu.bigdata.st.type.utils;

import javax.swing.event.MenuEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LiveStream extends JFrame {
	private STObject appObj;
	private JTextArea txtrlog;
	private JLayeredPane p_canvas;
	private JPanel bg;
	private JPanel fg;
	private int c_w[] = {-203, -170}; // 1264
	private int c_h[] = {-117, -91}; // 563
	private FileStreamer fs;
	private LinkedList<STObject> objects;
	private LinkedList<STObject> alerts;
	public LiveStream() {
		setTitle("SpatioTemporalJTS Visualizer");
		appObj = new STObject();
		
		objects = new LinkedList<>();
		alerts = new LinkedList<>();
		
		fs = new FileStreamer(this);
		
		bg = new JPanel(){
			@Override
			public void paintComponent(Graphics g) {
				LinkedList<Long> keyList = new LinkedList<>(appObj.getGeomtryMap().keySet());
				Collections.sort(keyList);
				Collections.reverse(keyList);
				if(keyList.size() == 0) return;
				//g.clearRect(0, 0, getWidth(), getHeight());
				double w_rate = p_canvas.getWidth()/(c_w[1]-c_w[0]);
				double h_rate = p_canvas.getHeight()/(c_h[1]-c_h[0]);
				
				for(STObject tmp : (LinkedList<STObject>)alerts.clone()){
					if(!objects.contains(tmp)){
						alerts.remove(tmp);
					}
				}
				
				for(STObject b : objects){
					Geometry geom = b.getGeomtryMap().getOrDefault(keyList.get(0), null);
					if(geom == null){
						continue;
					}
					System.out.println(b.getName());
					
					int x[] = new int[geom.getCoordinates().length];
					int y[] = new int[geom.getCoordinates().length];
					int j=0;
					for(Coordinate c : geom.getCoordinates()) {
						x[j] = (int)((c.x-c_w[0])*w_rate);
						y[j] = (int)((c.y-c_h[0])*h_rate);
						j++;
						g.fillOval((int)((c.x-c_w[0])*w_rate)-2, (int)((c.y-c_h[0])*h_rate)-2, 5, 5);
					}
					Polygon p = new Polygon(x, y, x.length);
					g.setColor(Color.yellow);
					g.fillPolygon(p);
					g.setColor(Color.red);
					g.drawPolygon(p);
					g.drawString(((HashMap<String, String>)b.getUserData()).get("name"), x[0], y[0]+10);
				}
			}
		};
		
		fg = new JPanel(){
			@Override
			public void paintComponent(Graphics g) {
				LinkedList<Long> keyList = new LinkedList<>(appObj.getGeomtryMap().keySet());
				Collections.sort(keyList);
				Collections.reverse(keyList);
				if(keyList.size() == 0) return;
				//g.clearRect(0, 0, getWidth(), getHeight());
				double w_rate = p_canvas.getWidth()/(c_w[1]-c_w[0]);
				double h_rate = p_canvas.getHeight()/(c_h[1]-c_h[0]);
				for(int i=Math.min(keyList.size()-1, 8);i>=0;i--){
					long time = keyList.get(i);
					Geometry geom = appObj.getGeomtryMap().get(time);
					
					int x[] = new int[geom.getCoordinates().length];
					int y[] = new int[geom.getCoordinates().length];
					int j=0;
					for(Coordinate c : geom.getCoordinates()) {
						x[j] = (int)((c.x-c_w[0])*w_rate);
						y[j] = (int)((c.y-c_h[0])*h_rate);
						if(i == 0){
							g.setColor(Color.getHSBColor(0.4f, 0.76f, 0.45f));
							g.drawString(utils.getDateTimeString(time*1000), x[j]-40, y[j]-10);
							g.setColor(Color.magenta);
							g.drawString(appObj.getName(), x[j]-30, y[j]-25);
						}
						j++;
						g.setColor(Color.black);
						if(i != 0){
							g.setColor(Color.getHSBColor(0f, 0f, Math.min(0.4f + i*0.08f, 1)));
							g.fillOval((int)((c.x-c_w[0])*w_rate)-2, (int)((c.y-c_h[0])*h_rate)-2, 5, 5);
						}
						else{
							g.fillOval((int)((c.x-c_w[0])*w_rate)-2, (int)((c.y-c_h[0])*h_rate)-2, 5, 5);
							for(STObject a : alerts){
								Geometry al = a.getGeomtryMap().get(time);
								if(al.contains(geom)){
									writeLog(appObj.getName()+" is in "+a.getName());
									g.setColor(Color.red);
									g.fillOval((int)((c.x-c_w[0])*w_rate)-2, (int)((c.y-c_h[0])*h_rate)-2, 5, 5);
								}
							}
						}
					}
				}
			}
		};
		
		p_canvas = new JLayeredPane(){
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(Color.white);
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		
		p_canvas.setPreferredSize(new Dimension(1264, 563));
		getContentPane().add(p_canvas, BorderLayout.CENTER);
		
		fg.setSize(p_canvas.getPreferredSize());
		bg.setSize(p_canvas.getPreferredSize());
		
		fg.setOpaque(false);
		
		fg.setLocation(0, 0);
		bg.setLocation(0, 0);
		
		p_canvas.setBackground(Color.WHITE);
		p_canvas.add(fg);
		p_canvas.add(bg);

		txtrlog = new JTextArea();
		txtrlog.setText("[Log]");
		txtrlog.setToolTipText("log");
		txtrlog.setRows(5);
		txtrlog.setBackground(UIManager.getColor("info"));
		txtrlog.setEditable(false);

		JScrollPane scroll = new JScrollPane(txtrlog);
		getContentPane().add(scroll, BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		JMenu mnLoadData = new JMenu("Load Data");
		menuBar.add(mnLoadData);
		
		JMenuItem mntmFromFile = new JMenuItem("From File");
		mntmFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openFile();
				mnLoadData.setSelected(false);
			}
		});
		mnLoadData.add(mntmFromFile);
		
		JMenuItem mntmFromServer = new JMenuItem("From Server");
		mntmFromServer.setEnabled(false);
		mnLoadData.add(mntmFromServer);
		
		JMenu mnStart = new JMenu("Start");
		mnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				startStream();
				mnStart.setSelected(false);
			}
		});
		menuBar.add(mnStart);
		
		JMenu mnPause = new JMenu("Pause");
		mnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pauseStream();
				mnPause.setSelected(false);
			}
		});
		menuBar.add(mnPause);
		
		JMenu mnobjects = new JMenu("Objects");
		mnobjects.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ObjectsDialog bd = new ObjectsDialog(objects);
				bd.setVisible(true);
				mnobjects.setSelected(false);
			}
		});
		menuBar.add(mnobjects);
		
		JMenu mnSetting = new JMenu("Setting");
		mnSetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int border[] = {c_w[0], c_w[1], c_h[0], c_h[1]};
				SettingDialog sd = new SettingDialog(LiveStream.this, border);
				sd.setVisible(true);;
				mnSetting.setSelected(false);
			}
		});
		
		JMenu mnAlert = new JMenu("Alert");
		mnAlert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new AlertDialog(objects, alerts);
				mnAlert.setSelected(false);
			}
		});
		menuBar.add(mnAlert);
		menuBar.add(mnSetting);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				fs.destroy();
			}
		});
		
		setBounds(100, 100, 1280, 720);
		setVisible(true);

	}
	
	public void setBound(int w1, int w2, int h1, int h2){
		this.c_w[0] = w1;
		this.c_w[1] = w2;
		this.c_h[0] = h1;
		this.c_h[1] = h2;
	}
	
	public void writeLog(String log){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd HH:mm:ss");
		txtrlog.append("\n["+sdf.format(d)+"] "+log);
		System.out.println("[LS]["+sdf.format(d)+"] "+log);
		txtrlog.setCaretPosition(txtrlog.getDocument().getLength());
	}
	
	public void drawObj(STObject obj){
		LinkedList<Long> keys = new LinkedList<>(obj.getGeomtryMap().keySet());
		appObj.setUserData(obj.getUserData());
		appObj.put(keys.getFirst(), obj.getGeomtryMap().get(keys.getFirst()));
		p_canvas.repaint();
		bg.repaint();
		fg.repaint();
	}

	public void openFile(){
		FileDialog fd = new FileDialog(this);
		fd.setVisible(true);
		if(fd.getFile() == null){
			return;
		}
		fs.openFile(fd.getDirectory()+fd.getFile());
		writeLog("File \""+fd.getFile()+"\" Loaded");
	}

	public void startStream(){
		fs.startStream();
	}
	public void pauseStream(){
		fs.pauseStream();
	}
}
