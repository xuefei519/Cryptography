import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;

import java.awt.Font;
import java.util.HashMap;

import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;


public class encipher {

	private JFrame frame;
	private JTextField textField;
	private JTextField lblLetter;

	private String key;
	private JFrame encipher;
	private JTable table;
	private String[] k;
	private Object[][] data;
	private String plaintxt;
	private JTextArea textArea2;
	private JTextArea textArea;

	private String ciphertxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					encipher window = new encipher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		    encipher en = new encipher();

		

	}

	/**
	 * Create the application.
	 */
	public encipher() {
		run();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void run() {
		Object[][] data=new Object[1][26];
	    JFrame.setDefaultLookAndFeelDecorated(true);

		frame = new JFrame();
		frame.setBounds(100, 100, 465, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("encryption");
		
		JLabel lblPleaseEnterYour = new JLabel("Please 1) enter your key:");
		lblPleaseEnterYour.setBounds(39, 11, 301, 16);
		frame.getContentPane().add(lblPleaseEnterYour);
		
		JLabel instruct = new JLabel("The key should only be 26 non-duplicate characters.");
		instruct.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		instruct.setBounds(39, 54, 333, 22);
		frame.getContentPane().add(instruct);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39,120,405,41);
		frame.getContentPane().add(scrollPane);
		
		JLabel key_result = new JLabel();
		scrollPane.setViewportView(key_result);

		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key = textField.getText();
				String Key=key.toLowerCase().replaceAll("[\\-\\+\\.\\^:,]","");
				if(key.length()<26||key.matches(".*\\d.*")||Duplicate(key)){
					String msg="Invalid key, length of key must be 26 non-duplicate letters.";
					JOptionPane.showMessageDialog(null, msg);
					}
				else{
					k=Key.split("");
					Object[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
					data[0]=k.clone();
					
					table = new JTable(data,letters);
					scrollPane.setViewportView(table);
					table.setFillsViewportHeight(true);
					table.setEnabled(false);
	
				}
				
			}
		});
		textField.setBounds(39, 26, 289, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		this.key = textField.getText();
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(49, 169, 1, 16);
		textArea.setLineWrap(true);
		
		
		frame.getContentPane().add(textArea);
		JScrollPane scrollPane1 = new JScrollPane(textArea);
		scrollPane1.setBounds(39,185,405,80);
		frame.getContentPane().add(scrollPane1);
		
		JTextArea textArea2 = new JTextArea();
		textArea2.setEditable(false);
		textArea2.setBounds(49, 169, 1, 16);
		textArea2.setLineWrap(true);
		
		
		frame.getContentPane().add(textArea2);
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		scrollPane2.setBounds(39,315,405,80);
		frame.getContentPane().add(scrollPane2);
		
		JLabel lblLetter = new JLabel("0 letter");
		lblLetter.setBounds(39, 254, 200, 41);
		frame.getContentPane().add(lblLetter);
		
		JLabel lblLetter_1 = new JLabel("0 letter");
		lblLetter_1.setBounds(39, 392, 179, 28);
		frame.getContentPane().add(lblLetter_1);		
		
		Object[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		table = new JTable(data,letters);
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);

		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key=key_generation.random_key();
				textField.setText(key);
				textField.setEditable(false);
				k=key.split("");
				data[0]=k.clone();
				
				for(int i=0;i<26;i++){
					table.setValueAt(k[i],0,i);}
					scrollPane.setViewportView(table);

					scrollPane.repaint();
			}
		});
		btnGenerate.setBounds(327, 79, 117, 29);
		frame.getContentPane().add(btnGenerate);

		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//textArea.setText("");
				textArea2.setText("");

				key = textField.getText();
				String Key=key.toLowerCase().replaceAll("[\\-\\+\\.\\^:,]","");
				if(key.length()<26||key.matches(".*\\d.*")||Duplicate(key)){
					String msg="Invalid key, length of key must be 26 non-duplicate letters.";
					JOptionPane.showMessageDialog(null, msg);
					}
				else{
					k=Key.split("");
					//Object[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
					data[0]=k.clone();
					
					/*table = new JTable(data,letters);
					scrollPane.setViewportView(table);
					table.setFillsViewportHeight(true);
					table.setEnabled(false);*/
					for(int i=0;i<26;i++){
						table.setValueAt(k[i],0,i);}
						scrollPane.setViewportView(table);

						scrollPane.repaint();

	
					
				}
			}
		});
		btnConfirm.setBounds(349, 27, 95, 28);
		frame.getContentPane().add(btnConfirm);
		
		JLabel lblYourKeyIs = new JLabel("Your key is:");
		lblYourKeyIs.setBounds(39, 103, 200, 16);
		frame.getContentPane().add(lblYourKeyIs);
		
		JLabel lblPlaintxt = new JLabel("Please enter your plain text:");
		lblPlaintxt.setBounds(39, 169, 200, 16);
		frame.getContentPane().add(lblPlaintxt);
		plaintxt="";
		//StringBuffer input=new StringBuffer();
		
		
		
		
		JButton btnConfirm_1 = new JButton("Confirm");
		btnConfirm_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				


				//plaintxt=textArea.getText();
				for (String line : textArea.getText().split("\\n"))
					plaintxt=plaintxt+line;
				String key2=key.replaceAll("[\\-\\+\\.\\^:,]","");
				System.out.println(key2);
				ciphertxt=encipher(key2,plaintxt);
				textArea2.setText(ciphertxt);	
				if(plaintxt.length()>0)
				lblLetter.setText(plaintxt.replaceAll("[^\\w\\s]","").replaceAll("_","").replaceAll("\\s+","").length()+" letters");
				else;
				
				if(ciphertxt.length()>0)
					lblLetter_1.setText(ciphertxt.length()+" letters");
					else;
				
				plaintxt="";

			}
		});

		btnConfirm_1.setBounds(362, 274, 82, 29);
		frame.getContentPane().add(btnConfirm_1);
		
		JLabel lblTheCipherText = new JLabel("The cipher text is:");
		lblTheCipherText.setBounds(39, 291, 119, 22);
		frame.getContentPane().add(lblTheCipherText);
		
		JButton btnClear = new JButton("Clear All");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField.setEditable(true);
				textArea.setText("");
				textArea2.setText("");
				lblLetter.setText("0 letter");
				lblLetter_1.setText("0 letter");
				for(int i=0;i<26;i++){
				table.setValueAt(" ",0,i);}
				scrollPane.setViewportView(table);

				scrollPane.repaint();

			}
		});
		btnClear.setBounds(364, 404, 95, 29);
		frame.getContentPane().add(btnClear);
		
		JButton btnClear_1 = new JButton("Clear");
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				lblLetter.setText("0 letter");
			}
		});
		btnClear_1.setBounds(276, 274, 82, 29);
		frame.getContentPane().add(btnClear_1);
		
		JButton btnCopy = new JButton("Copy Ciphertext");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea2.selectAll();
				textArea2.copy();
			}
		});
		btnCopy.setBounds(241, 404, 127, 29);
		frame.getContentPane().add(btnCopy);
		
				
		JLabel lblOrGenerateA = new JLabel("Or 2) generate a random key:");
		lblOrGenerateA.setBounds(39, 75, 200, 16);
		frame.getContentPane().add(lblOrGenerateA);
		
		JButton btnCopyKey = new JButton("Copy Key");
		btnCopyKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.selectAll();
				textField.copy();
			}
		});
		btnCopyKey.setBounds(123, 404, 117, 29);
		frame.getContentPane().add(btnCopyKey);
		
		
		
		
		
		
	}
	
	private void encipher(String key){
		encipher= new JFrame();
		encipher.setBounds(100, 100, 450, 300);
		encipher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		encipher.getContentPane().setLayout(null);
		encipher.setResizable(true);
		
	}
	
	public void print(String text) {
	    System.out.println(text);
	    
	}
	

	public static boolean Duplicate(String key){
		String[]  key_array= key.split("");
		
		for(int i=0;i<key_array.length-1;i++){
			for(int j=i+1;j<key_array.length;j++){
				if(key_array[i].equals(key_array[j]))
					return true;
			}
		}
		return false;
}
	public static String encipher(String key,String txt){
		HashMap<String, String> encipher=new HashMap<String, String>();

		
		String alphabet="abcdefghijklmnopqrstuvwxyz";
		String[] alphabet_array=alphabet.split(""); 
		String plaintext =txt.toLowerCase().replaceAll("[^\\w\\s]","").replaceAll("_","").replaceAll("\\s+","");
		String[] key_array= key.split("");for(int i=0;i<key_array.length;i++){
			//encipher.put(key_array[i], alphabet_array[i]);
			encipher.put(alphabet_array[i],key_array[i]);

		}
		
		String[] character = plaintext.split("");

		StringBuffer result = new StringBuffer();
		for(int i=0;i<character.length;i++){
			//System.out.println(character[i]);
			if(character[i].matches(".*\\d.*")) //do nothing if it is digit
				;
			else  { //substitution
				//System.out.println(character[i]+" to "+encipher.get(character[i]));		
				character[i]=encipher.get(character[i]);
			}
			result.append( character[i] );
		}
		String ciphertext=result.toString();
		return ciphertext;
	}
}

