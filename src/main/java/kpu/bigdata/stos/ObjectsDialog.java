package kpu.bigdata.stos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kpu.bigdata.st.type.STObject;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.LinkedList;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ObjectsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public ObjectsDialog(LinkedList<STObject> objects) {
		setTitle("Objects");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 404, 224);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
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
		list.setBorder(UIManager.getBorder("Spinner.border"));
		list.setBounds(12, 10, 253, 165);
		contentPanel.add(list);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = JOptionPane.showInputDialog(null, "Input ST_OBJECT String... (ex: ST_OBJECT<name:something> ((INSTANT (0000-00-00 00:00:00), POINT (0 0))))", "add building", JOptionPane.OK_CANCEL_OPTION);
                STObject building = STObject.getFromString(str);
                objects.add(building);
                model.addElement(building.getName());
			}
		});
		btnAdd.setBounds(277, 10, 97, 23);
		contentPanel.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				objects.remove(index);
				model.removeElementAt(index);
			}
		});
		btnRemove.setBounds(277, 43, 97, 23);
		contentPanel.add(btnRemove);
		setResizable(false);
	}
}
