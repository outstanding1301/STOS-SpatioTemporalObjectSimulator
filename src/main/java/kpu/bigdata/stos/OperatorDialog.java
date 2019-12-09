package kpu.bigdata.stos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import kpu.bigdata.st.type.STObject;
import kpu.bigdata.st.type.STObjectMultiViz;
import kpu.bigdata.temporal.type.Period;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;

public class OperatorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Operator operator = null;
	private LinkedList<STObject> objects = null;
	private OperatorSimulator os;
	private JTextField tfYears_f;
	private JTextField tfMonth_f;
	private JTextField tfDays_f;
	private JTextField tfDays_t;
	private JTextField tfMonth_t;
	private JTextField tfYears_t;
	private JTextField tfHours_f;
	private JTextField tfMinutes_f;
	private JTextField tfSeconds_f;
	private JTextField tfHours_t;
	private JTextField tfMinutes_t;
	private JTextField tfSeconds_t;
	
	public OperatorDialog(OperatorSimulator os, Operator operator, LinkedList<STObject> objects) {
		this.os = os;
		this.operator = operator;
		switch (operator) {
		case rCONTAINS:
			setTitle("Relations - Contains");
			break;
		case rCROSSES:
			setTitle("Relations - Crosses");
			break;
		case rDISJOINT:
			setTitle("Relations - Disjoint");
			break;
		case rEQUALS:
			setTitle("Relations - Equals");
			break;
		case rOVERLAPS:
			setTitle("Relations - Overlaps");
			break;
		case rTOUCHES:
			setTitle("Relations - Touches");
			break;
		case tENTERS:
			setTitle("Trajectories - Enters");
			break;
		case tINSIDES:
			setTitle("Trajectories - Insides");
			break;
		case tLEAVES:
			setTitle("Trajectories - Leaves");
			break;
		case tMEETS:
			setTitle("Trajectories - Meets");
			break;
		case tPASSES:
			setTitle("Trajectories - Passes");
			break;
		default:
			setTitle("");
			break;
		}
		setBounds(100, 100, 561, 326);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		DefaultListModel<String> model = new DefaultListModel<>();
		
		for(STObject obj : objects){
			String name = obj.getName();
			if(name != null){
				model.addElement(name);
			}
		}
		
		JList list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(12, 41, 126, 236);
		contentPanel.add(list);
		
		JList list_1 = new JList(model);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		list_1.setBounds(150, 41, 126, 236);
		contentPanel.add(list_1);
		
		JLabel lblObject1 = new JLabel("Object 1");
		lblObject1.setBounds(12, 16, 57, 15);
		contentPanel.add(lblObject1);
		
		JLabel lblObject2 = new JLabel("Object 2");
		lblObject2.setBounds(150, 16, 57, 15);
		contentPanel.add(lblObject2);

		JLabel lblResult_1 = new JLabel("Result:");
		lblResult_1.setBounds(287, 172, 57, 15);
		contentPanel.add(lblResult_1);

		JLabel lblResult = new JLabel("TRUE");
		lblResult.setForeground(Color.BLUE);
		lblResult.setFont(new Font("Arial", Font.BOLD, 16));
		lblResult.setBounds(288, 188, 104, 23);
		contentPanel.add(lblResult);
		
		JPanel panelPeriod = new JPanel();
		panelPeriod.setBackground(Color.WHITE);
		panelPeriod.setBounds(288, 70, 262, 96);
		contentPanel.add(panelPeriod);
		panelPeriod.setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setEnabled(false);
		lblFrom.setForeground(Color.DARK_GRAY);
		lblFrom.setFont(new Font("����", Font.ITALIC, 12));
		lblFrom.setBounds(0, 4, 29, 15);
		panelPeriod.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setEnabled(false);
		lblTo.setFont(new Font("����", Font.ITALIC, 12));
		lblTo.setForeground(Color.DARK_GRAY);
		lblTo.setBounds(0, 48, 57, 15);
		panelPeriod.add(lblTo);
		
		tfYears_f = new JTextField();
		tfYears_f.setEnabled(false);
		tfYears_f.setText("2019");
		tfYears_f.setBounds(0, 21, 41, 21);
		panelPeriod.add(tfYears_f);
		tfYears_f.setColumns(4);
		tfYears_f.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		tfMonth_f = new JTextField();
		tfMonth_f.setEnabled(false);
		tfMonth_f.setText("01");
		tfMonth_f.setBounds(53, 21, 29, 21);
		panelPeriod.add(tfMonth_f);
		tfMonth_f.setColumns(2);
		tfMonth_f.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		tfDays_f = new JTextField();
		tfDays_f.setEnabled(false);
		tfDays_f.setText("01");
		tfDays_f.setColumns(2);
		tfDays_f.setBounds(94, 21, 29, 21);
		panelPeriod.add(tfDays_f);
		tfDays_f.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		JLabel label = new JLabel("/");
		label.setEnabled(false);
		label.setBounds(44, 24, 15, 15);
		panelPeriod.add(label);
		
		JLabel label_1 = new JLabel("/");
		label_1.setEnabled(false);
		label_1.setBounds(85, 24, 15, 15);
		panelPeriod.add(label_1);
		
		tfDays_t = new JTextField();
		tfDays_t.setEnabled(false);
		tfDays_t.setText("31");
		tfDays_t.setColumns(2);
		tfDays_t.setBounds(94, 65, 29, 21);
		panelPeriod.add(tfDays_t);
		tfDays_t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		JLabel label_2 = new JLabel("/");
		label_2.setEnabled(false);
		label_2.setBounds(85, 68, 15, 15);
		panelPeriod.add(label_2);
		
		tfMonth_t = new JTextField();
		tfMonth_t.setEnabled(false);
		tfMonth_t.setText("12");
		tfMonth_t.setColumns(2);
		tfMonth_t.setBounds(53, 65, 29, 21);
		panelPeriod.add(tfMonth_t);
		tfMonth_t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		JLabel label_3 = new JLabel("/");
		label_3.setEnabled(false);
		label_3.setBounds(44, 68, 15, 15);
		panelPeriod.add(label_3);
		
		tfYears_t = new JTextField();
		tfYears_t.setEnabled(false);
		tfYears_t.setText("2019");
		tfYears_t.setColumns(4);
		tfYears_t.setBounds(0, 65, 41, 21);
		panelPeriod.add(tfYears_t);
		tfYears_t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		tfHours_f = new JTextField();
		tfHours_f.setEnabled(false);
		tfHours_f.setText("00");
		tfHours_f.setColumns(2);
		tfHours_f.setBounds(145, 21, 29, 21);
		panelPeriod.add(tfHours_f);
		tfHours_f.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		tfMinutes_f = new JTextField();
		tfMinutes_f.setEnabled(false);
		tfMinutes_f.setText("00");
		tfMinutes_f.setColumns(2);
		tfMinutes_f.setBounds(186, 21, 29, 21);
		panelPeriod.add(tfMinutes_f);
		tfMinutes_f.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		tfSeconds_f = new JTextField();
		tfSeconds_f.setEnabled(false);
		tfSeconds_f.setText("00");
		tfSeconds_f.setColumns(2);
		tfSeconds_f.setBounds(227, 21, 29, 21);
		panelPeriod.add(tfSeconds_f);
		tfSeconds_f.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		tfHours_t = new JTextField();
		tfHours_t.setEnabled(false);
		tfHours_t.setText("23");
		tfHours_t.setColumns(2);
		tfHours_t.setBounds(145, 65, 29, 21);
		panelPeriod.add(tfHours_t);
		tfHours_t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		tfMinutes_t = new JTextField();
		tfMinutes_t.setEnabled(false);
		tfMinutes_t.setText("59");
		tfMinutes_t.setColumns(2);
		tfMinutes_t.setBounds(186, 65, 29, 21);
		panelPeriod.add(tfMinutes_t);
		tfMinutes_t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		tfSeconds_t = new JTextField();
		tfSeconds_t.setEnabled(false);
		tfSeconds_t.setText("59");
		tfSeconds_t.setColumns(2);
		tfSeconds_t.setBounds(227, 65, 29, 21);
		panelPeriod.add(tfSeconds_t);
		tfSeconds_t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
		});
		
		JLabel label_4 = new JLabel(":");
		label_4.setEnabled(false);
		label_4.setBounds(176, 24, 15, 15);
		panelPeriod.add(label_4);
		
		JLabel label_5 = new JLabel(":");
		label_5.setEnabled(false);
		label_5.setBounds(217, 24, 15, 15);
		panelPeriod.add(label_5);
		
		JLabel label_6 = new JLabel(":");
		label_6.setEnabled(false);
		label_6.setBounds(176, 68, 15, 15);
		panelPeriod.add(label_6);
		
		JLabel label_7 = new JLabel(":");
		label_7.setEnabled(false);
		label_7.setBounds(217, 68, 15, 15);
		panelPeriod.add(label_7);
		

		JCheckBox chckbxCustomPeriod = new JCheckBox("Custom Period");
		chckbxCustomPeriod.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxCustomPeriod.isSelected()){
					tfYears_f.setEnabled(true);
					tfMonth_f.setEnabled(true);
					tfDays_f.setEnabled(true);
					tfHours_f.setEnabled(true);
					tfMinutes_f.setEnabled(true);
					tfSeconds_f.setEnabled(true);

					tfYears_t.setEnabled(true);
					tfMonth_t.setEnabled(true);
					tfDays_t.setEnabled(true);
					tfHours_t.setEnabled(true);
					tfMinutes_t.setEnabled(true);
					tfSeconds_t.setEnabled(true);
					
					lblFrom.setEnabled(true);
					lblTo.setEnabled(true);
					label.setEnabled(true);
					label_1.setEnabled(true);
					label_2.setEnabled(true);
					label_3.setEnabled(true);
					label_4.setEnabled(true);
					label_5.setEnabled(true);
					label_6.setEnabled(true);
					label_7.setEnabled(true);
				}
				else {
					tfYears_f.setEnabled(false);
					tfMonth_f.setEnabled(false);
					tfDays_f.setEnabled(false);
					tfHours_f.setEnabled(false);
					tfMinutes_f.setEnabled(false);
					tfSeconds_f.setEnabled(false);

					tfYears_t.setEnabled(false);
					tfMonth_t.setEnabled(false);
					tfDays_t.setEnabled(false);
					tfHours_t.setEnabled(false);
					tfMinutes_t.setEnabled(false);
					tfSeconds_t.setEnabled(false);

					lblFrom.setEnabled(false);
					lblTo.setEnabled(false);
					label.setEnabled(false);
					label_1.setEnabled(false);
					label_2.setEnabled(false);
					label_3.setEnabled(false);
					label_4.setEnabled(false);
					label_5.setEnabled(false);
					label_6.setEnabled(false);
					label_7.setEnabled(false);
				}
			}
		});
		chckbxCustomPeriod.setBounds(284, 41, 115, 23);
		contentPanel.add(chckbxCustomPeriod);
		

		JButton btnResult = new JButton("Result");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idx1 = list.getSelectedIndex();
				int idx2 = list_1.getSelectedIndex();
				STObject obj1 = objects.get(idx1);
				STObject obj2 = objects.get(idx2);
				
				if(chckbxCustomPeriod.isSelected()){
					obj1 = obj1.periodFilter(new Period(
							String.format("%s-%s-%s %s:%s:%s", 
									tfYears_f.getText(), tfMonth_f.getText(), tfDays_f.getText(), tfHours_f.getText(), tfMinutes_f.getText(), tfSeconds_f.getText()), 
							String.format("%s-%s-%s %s:%s:%s", 
									tfYears_t.getText(), tfMonth_t.getText(), tfDays_t.getText(), tfHours_t.getText(), tfMinutes_t.getText(), tfSeconds_t.getText())));
					obj2 = obj2.periodFilter(new Period(
							String.format("%s-%s-%s %s:%s:%s", 
									tfYears_f.getText(), tfMonth_f.getText(), tfDays_f.getText(), tfHours_f.getText(), tfMinutes_f.getText(), tfSeconds_f.getText()), 
							String.format("%s-%s-%s %s:%s:%s", 
									tfYears_t.getText(), tfMonth_t.getText(), tfDays_t.getText(), tfHours_t.getText(), tfMinutes_t.getText(), tfSeconds_t.getText())));
				}
				
				boolean result = false;
				String ops = "";
				switch (operator) {
				case rCONTAINS:
					result = obj1.contains(obj2);
					ops = "CONTAINS";
					break;
				case rCROSSES:
					result = obj1.crosses(obj2);
					ops = "CROSSES";
					break;
				case rDISJOINT:
					result = obj1.disjoint(obj2);
					ops = "DISJOINT";
					break;
				case rEQUALS:
					result = obj1.equals(obj2);
					ops = "EQUALS";
					break;
				case rOVERLAPS:
					result = obj1.overlaps(obj2);
					ops = "OVERLAPS";
					break;
				case rTOUCHES:
					result = obj1.touches(obj2);
					ops = "TOUCHES";
					break;
				case tENTERS:
					result = obj1.enters(obj2);
					ops = "ENTERS";
					break;
				case tINSIDES:
					result = obj1.insides(obj2);
					ops = "INSIDES";
					break;
				case tLEAVES:
					result = obj1.leaves(obj2);
					ops = "LEAVES";
					break;
				case tMEETS:
					result = obj1.meets(obj2);
					ops = "MEETS";
					break;
				case tPASSES:
					result = obj1.passes(obj2);
					ops = "PASSES";
					break;
				default:
					break;
				}
				os.writeLog(obj1.getName()+" "+ops+" "+obj2.getName()+" : "+result);
				lblResult.setText((""+result).toUpperCase());
			}
		});
		btnResult.setBounds(288, 221, 104, 23);
		contentPanel.add(btnResult);
		
		JButton btnVisualize = new JButton("Visualize");
		btnVisualize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx1 = list.getSelectedIndex();
				int idx2 = list_1.getSelectedIndex();
				STObject obj1 = objects.get(idx1);
				STObject obj2 = objects.get(idx2);
				
				if(chckbxCustomPeriod.isSelected()){
					obj1 = obj1.periodFilter(new Period(
							String.format("%s-%s-%s %s:%s:%s", 
									tfYears_f.getText(), tfMonth_f.getText(), tfDays_f.getText(), tfHours_f.getText(), tfMinutes_f.getText(), tfSeconds_f.getText()), 
							String.format("%s-%s-%s %s:%s:%s", 
									tfYears_t.getText(), tfMonth_t.getText(), tfDays_t.getText(), tfHours_t.getText(), tfMinutes_t.getText(), tfSeconds_t.getText())));
					obj2 = obj2.periodFilter(new Period(
							String.format("%s-%s-%s %s:%s:%s", 
									tfYears_f.getText(), tfMonth_f.getText(), tfDays_f.getText(), tfHours_f.getText(), tfMinutes_f.getText(), tfSeconds_f.getText()), 
							String.format("%s-%s-%s %s:%s:%s", 
									tfYears_t.getText(), tfMonth_t.getText(), tfDays_t.getText(), tfHours_t.getText(), tfMinutes_t.getText(), tfSeconds_t.getText())));
				}
				
				new STObjectMultiViz(obj1.getName()+", "+obj2.getName(), obj1, obj2, os.c_w, os.c_h);
			}
		});
		btnVisualize.setBounds(288, 254, 104, 23);
		contentPanel.add(btnVisualize);
		
		setVisible(true);
		setResizable(false);
	}
}
