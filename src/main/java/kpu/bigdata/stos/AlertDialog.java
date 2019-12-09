package kpu.bigdata.stos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kpu.bigdata.st.type.STObject;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;

public class AlertDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private LinkedList<JCheckBox> objBox = new LinkedList<>();

	public AlertDialog(LinkedList<STObject> objects, LinkedList<STObject> alerts) {
		
		setTitle("Alert");
		setBounds(100, 100, 179, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 150, 241);
		contentPanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		setVisible(true);
		setResizable(false);
		
		for(STObject obj : objects){
			JCheckBox box = new JCheckBox(obj.getName());
			if(alerts.contains(obj)){
				box.setSelected(true);
			}
			panel.add(box);
			objBox.add(box);
			box.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(box.isSelected()){
						if(!alerts.contains(obj)){
							alerts.add(obj);
						}
					}
					else {
						if(alerts.contains(obj)){
							alerts.remove(obj);
						}
					}
				}
			});
		}
		
	}
}
