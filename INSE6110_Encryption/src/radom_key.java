import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class radom_key {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					radom_key window = new radom_key();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public radom_key() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnGenerateRandomKey = new JButton("Generate Random Key");
		
		btnGenerateRandomKey.setBounds(131, 52, 180, 47);
		frame.getContentPane().add(btnGenerateRandomKey);
		
		JLabel lblTheRandomGenerated = new JLabel("The random generated key is:");
		lblTheRandomGenerated.setBounds(23, 133, 194, 22);
		frame.getContentPane().add(lblTheRandomGenerated);
		
		textField = new JTextField();
		textField.setBounds(33, 171, 315, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		JButton btnCopy = new JButton("Copy");
		btnCopy.setBounds(327, 205, 117, 29);
		frame.getContentPane().add(btnCopy);
		
		btnGenerateRandomKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=key_generation.random_key();
				textField.setText(key);
			}
		});
		
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.selectAll();
				textField.copy();
			}
		});
	}
}
