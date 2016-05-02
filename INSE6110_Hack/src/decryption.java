import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextArea;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class decryption {

	private JFrame decipher;
	private JTable table;
	private JTextField textField;
	private Object[][] data;
	private String ciphertext;
	private String[] result;
	private String plaintext;
	private String hack_key;
	private String key;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					decryption window = new decryption();
					window.decipher.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public decryption() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		decipher = new JFrame();
		decipher.setBounds(100, 100, 452, 499);
		decipher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		decipher.getContentPane().setLayout(null);
		decipher.setTitle("Hacking");
		decipher.setResizable(false);
		
		JLabel lblPleaseEnterYour = new JLabel("Please enter your cipher text:");
		lblPleaseEnterYour.setBounds(10, 6, 199, 24);
		decipher.getContentPane().add(lblPleaseEnterYour);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 33, 428, 120);
		decipher.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JButton btnCrackIt = new JButton("Crack it");
		
		btnCrackIt.setBounds(329, 157, 117, 29);
		decipher.getContentPane().add(btnCrackIt);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(214, 157, 117, 29);
		decipher.getContentPane().add(btnClear);
		
		JLabel lblTheKeyIs = new JLabel("The key is:");
		lblTheKeyIs.setBounds(10, 165, 199, 24);
		decipher.getContentPane().add(lblTheKeyIs);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 184, 428, 41);
		decipher.getContentPane().add(scrollPane_1);
		
		Object[][] data=new Object[1][26];
		table = new JTable();
		Object[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		table = new JTable(data,letters);
		scrollPane_1.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);

		
		JLabel lblThePlainText = new JLabel("The plain text is:");
		lblThePlainText.setBounds(10, 223, 199, 24);
		decipher.getContentPane().add(lblThePlainText);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 245, 428, 120);
		decipher.getContentPane().add(scrollPane_2);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setLineWrap(true);
		scrollPane_2.setViewportView(textArea_1);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the expected key to get the accuracy:");
		lblPleaseEnterThe.setBounds(10, 369, 346, 16);
		decipher.getContentPane().add(lblPleaseEnterThe);
		
		textField = new JTextField();
		
		textField.setBounds(12, 390, 331, 26);
		decipher.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		
		btnConfirm.setBounds(345, 391, 93, 25);
		decipher.getContentPane().add(btnConfirm);
		
		JLabel lblTheAccuracyIs = new JLabel("The accuracy is:");
		lblTheAccuracyIs.setBounds(10, 421, 199, 29);
		decipher.getContentPane().add(lblTheAccuracyIs);
		
		JButton btnTryAnother = new JButton("Try another");
		
		btnTryAnother.setBounds(321, 442, 117, 29);
		decipher.getContentPane().add(btnTryAnother);
		
		btnCrackIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ciphertext="";
				for (String line : textArea.getText().split("\\n"))
					ciphertext=ciphertext+line;
				ciphertext.toLowerCase().replaceAll("[^\\w\\s]","").replaceAll("_","").replaceAll("\\s+","");
				if(!ciphertext.equals("")){
				try {
					result=Hacking.run(ciphertext).clone();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				hack_key=result[0];
				plaintext=result[1];
				ciphertext="";
				String[] k=hack_key.split("");
				for(int i=0;i<26;i++){
					table.setValueAt(k[i],0,i);}
				scrollPane_1.setViewportView(table);
				scrollPane_1.repaint();
				textArea_1.setText(plaintext);	
			}
				else ;

			}
		});
		
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				for(int i=0;i<26;i++){
					table.setValueAt(" ",0,i);}
				scrollPane_1.setViewportView(table);
				scrollPane_1.repaint();
				textArea_1.setText("");	
			}
		});
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key=textField.getText();
				String Key=key.toLowerCase().replaceAll("[\\-\\+\\.\\^:,]","");
				if(key.length()<26||key.matches(".*\\d.*")||Hacking.Duplicate(key)){
					String msg="Invalid key, length of key must be 26 non-duplicate letters.";
					JOptionPane.showMessageDialog(null, msg);
					}
				else{
				float accuracy=Hacking.accuracy(key,hack_key)*100;
				lblTheAccuracyIs.setText("The accuracy is: "+accuracy+"%");
				}
			}
		});
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key=textField.getText();
				String Key=key.toLowerCase().replaceAll("[\\-\\+\\.\\^:,]","");
				if(key.length()<26||key.matches(".*\\d.*")||Hacking.Duplicate(key)){
					String msg="Invalid key, length of key must be 26 non-duplicate letters.";
					JOptionPane.showMessageDialog(null, msg);
					}
				else{
				float accuracy=Hacking.accuracy(key,hack_key)*100;
				lblTheAccuracyIs.setText("The accuracy is: "+accuracy+"%");
				}
			}
		});
		
		btnTryAnother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				for(int i=0;i<26;i++){
					table.setValueAt(" ",0,i);}
				scrollPane_1.setViewportView(table);
				scrollPane_1.repaint();
				textArea_1.setText("");	
				textField.setText("");
				lblTheAccuracyIs.setText("The accuracy is:");
				
			}
		});

	}

}
