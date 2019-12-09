package kpu.bigdata.stos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App extends JFrame {

	private JPanel contentPane;
	private JTextArea txtrLiveStream;
	public static int c_w[] = {-350, 100}; // 1264
	public static int c_h[] = {-400, 150}; // 563
	public App() {
		setTitle("STJTSV");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 192, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea txtrLiveStream = new JTextArea();
		txtrLiveStream.setEditable(false);
		
		JButton btnLiveStream = new JButton("Live Stream");
		btnLiveStream.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LiveStream ls = new LiveStream();
			}
		});
		btnLiveStream.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {		
				txtrLiveStream.setText("- Live Stream\r\n\r\n\uC2E4\uC2DC\uAC04\uC73C\uB85C \uC785\uB825\uBC1B\uC740\r\n\uB370\uC774\uD130\uB97C \uCD9C\uB825\uD569\uB2C8\uB2E4.\r\n\uB370\uC774\uD130\uC758 \uADA4\uC801\uC744 \uCD94\uC801\uD558\uBA70\r\n\uC120\uD0DD\uD55C \uACF5\uAC04\uC73C\uB85C\uC758\r\n\uC785\uC7A5\uC744 \uAC10\uC2DC\uD569\uB2C8\uB2E4.");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtrLiveStream.setText("");
			}
		});
		btnLiveStream.setBounds(12, 10, 162, 23);
		contentPane.add(btnLiveStream);
		
		JButton btnOperation = new JButton("Operator Simulator");
		btnOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperatorSimulator os = new OperatorSimulator();
			}
		});
		btnOperation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtrLiveStream.setText("- Operator Simulator\r\n\r\n\uC2DC\uACF5\uAC04 \uAC1D\uCCB4\uB4E4\uC5D0\r\n\uAD00\uACC4 \uC5F0\uC0B0\uC790\uC640\r\n\uADA4\uC801 \uC5F0\uC0B0\uC790\uB97C\r\n\uC2DC\uBBAC\uB808\uC774\uC158\uD569\uB2C8\uB2E4.");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtrLiveStream.setText("");
			}
		});
		btnOperation.setBounds(12, 43, 162, 23);
		contentPane.add(btnOperation);

		txtrLiveStream.setFont(new Font("�������", Font.PLAIN, 11));
		txtrLiveStream.setBounds(12, 76, 162, 132);
		contentPane.add(txtrLiveStream);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
}
