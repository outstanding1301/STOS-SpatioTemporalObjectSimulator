package kpu.bigdata.stos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SettingDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField min_width;
	private JTextField max_width;
	private JTextField max_height;
	private JTextField min_height;
	
	public SettingDialog(LiveStream app, int border[]) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Setting");
		setBounds(100, 100, 325, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		min_width = new JTextField();
		min_width.setBounds(92, 84, 70, 21);
		contentPanel.add(min_width);
		min_width.setColumns(10);
		min_width.setText(""+border[0]);
		min_width.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					if(e.getKeyChar() == '-'){
						if(min_width.getText().length() == 0){
							return;
						}
					}
					e.consume();
				}
			}
		});
		
		max_width = new JTextField();
		max_width.setBounds(223, 84, 70, 21);
		contentPanel.add(max_width);
		max_width.setColumns(10);
		max_width.setText(""+border[1]);
		max_width.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					if(e.getKeyChar() == '-'){
						if(max_width.getText().length() == 0){
							return;
						}
					}
					e.consume();
				}
			}
		});

		min_height = new JTextField();
		min_height.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					if(e.getKeyChar() == '-'){
						if(min_height.getText().length() == 0){
							return;
						}
					}
					e.consume();
				}
			}
		});
		min_height.setBounds(12, 114, 70, 21);
		contentPanel.add(min_height);
		min_height.setColumns(10);
		min_height.setText(""+border[2]);
		
		max_height = new JTextField();
		max_height.setBounds(12, 197, 70, 21);
		contentPanel.add(max_height);
		max_height.setColumns(10);
		max_height.setText(""+border[3]);
		max_height.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					if(e.getKeyChar() == '-'){
						if(max_height.getText().length() == 0){
							return;
						}
					}
					e.consume();
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(92, 114, 200, 104);
		contentPanel.add(panel);
		
		JLabel lblMinWidth = new JLabel("min width");
		lblMinWidth.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMinWidth.setBounds(92, 67, 70, 15);
		contentPanel.add(lblMinWidth);
		
		JLabel lblMaxWidth = new JLabel("max width");
		lblMaxWidth.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMaxWidth.setBounds(223, 67, 70, 15);
		contentPanel.add(lblMaxWidth);
		
		JLabel lblMinHeight = new JLabel("min height");
		lblMinHeight.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMinHeight.setBounds(12, 98, 68, 15);
		contentPanel.add(lblMinHeight);
		
		JLabel lblMaxHeight = new JLabel("max height");
		lblMaxHeight.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMaxHeight.setBounds(12, 179, 70, 15);
		contentPanel.add(lblMaxHeight);
		
		JLabel lblBorderSettings = new JLabel("Border Settings");
		lblBorderSettings.setFont(new Font("Arial", Font.BOLD, 16));
		lblBorderSettings.setBounds(12, 10, 127, 32);
		contentPanel.add(lblBorderSettings);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int minw = Integer.parseInt(min_width.getText());
						int maxw = Integer.parseInt(max_width.getText());
						int minh = Integer.parseInt(min_height.getText());
						int maxh = Integer.parseInt(max_height.getText());
						app.setBound(Math.min(minw, maxw), Math.max(minw, maxw), Math.min(minh, maxh), Math.max(minh, maxh));
						dispose();
					}
				});
				okButton.setFont(new Font("Arial", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Arial", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public SettingDialog(OperatorSimulator app, int border[]) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Setting");
		setBounds(100, 100, 325, 300);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		min_width = new JTextField();
		min_width.setBounds(92, 84, 70, 21);
		contentPanel.add(min_width);
		min_width.setColumns(10);
		min_width.setText(""+border[0]);
		min_width.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					if(e.getKeyChar() == '-'){
						if(min_width.getText().length() == 0){
							return;
						}
					}
					e.consume();
				}
			}
		});
		
		max_width = new JTextField();
		max_width.setBounds(223, 84, 70, 21);
		contentPanel.add(max_width);
		max_width.setColumns(10);
		max_width.setText(""+border[1]);
		max_width.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					if(e.getKeyChar() == '-'){
						if(max_width.getText().length() == 0){
							return;
						}
					}
					e.consume();
				}
			}
		});

		min_height = new JTextField();
		min_height.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					if(e.getKeyChar() == '-'){
						if(min_height.getText().length() == 0){
							return;
						}
					}
					e.consume();
				}
			}
		});
		min_height.setBounds(12, 114, 70, 21);
		contentPanel.add(min_height);
		min_height.setColumns(10);
		min_height.setText(""+border[2]);
		
		max_height = new JTextField();
		max_height.setBounds(12, 197, 70, 21);
		contentPanel.add(max_height);
		max_height.setColumns(10);
		max_height.setText(""+border[3]);
		max_height.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					if(e.getKeyChar() == '-'){
						if(max_height.getText().length() == 0){
							return;
						}
					}
					e.consume();
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(92, 114, 200, 104);
		contentPanel.add(panel);
		
		JLabel lblMinWidth = new JLabel("min width");
		lblMinWidth.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMinWidth.setBounds(92, 67, 70, 15);
		contentPanel.add(lblMinWidth);
		
		JLabel lblMaxWidth = new JLabel("max width");
		lblMaxWidth.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMaxWidth.setBounds(223, 67, 70, 15);
		contentPanel.add(lblMaxWidth);
		
		JLabel lblMinHeight = new JLabel("min height");
		lblMinHeight.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMinHeight.setBounds(12, 98, 68, 15);
		contentPanel.add(lblMinHeight);
		
		JLabel lblMaxHeight = new JLabel("max height");
		lblMaxHeight.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMaxHeight.setBounds(12, 179, 70, 15);
		contentPanel.add(lblMaxHeight);
		
		JLabel lblBorderSettings = new JLabel("Border Settings");
		lblBorderSettings.setFont(new Font("Arial", Font.BOLD, 16));
		lblBorderSettings.setBounds(12, 10, 127, 32);
		contentPanel.add(lblBorderSettings);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int minw = Integer.parseInt(min_width.getText());
						int maxw = Integer.parseInt(max_width.getText());
						int minh = Integer.parseInt(min_height.getText());
						int maxh = Integer.parseInt(max_height.getText());
						app.setBound(Math.min(minw, maxw), Math.max(minw, maxw), Math.min(minh, maxh), Math.max(minh, maxh));
						dispose();
					}
				});
				okButton.setFont(new Font("Arial", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Arial", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
