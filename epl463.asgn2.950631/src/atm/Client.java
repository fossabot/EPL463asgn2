/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

/**
 *
 * @author EPL463
 */
public class Client implements ActionListener {

	/**
	 * Security algorithm instance
	 */
	private static SecurityAlgorithm sa;

	/**
	 * main JFrame
	 */
	private JFrame main;
	/**
	 * JTextfield for input account number
	 */
	private JTextField textFieldAccountNumber;
	/**
	 * GetStatement button
	 */
	private JButton btnGetStatement;
	/**
	 * JRadiobutton for BalanceOnly option
	 */
	private JRadioButton rdbtnBalanceOnly;
	/**
	 * JRadiobutton for MiniStatement option
	 */
	private JRadioButton rdbtnMiniStatement;
	/**
	 * JRadiobutton for DetailedStatement option
	 */
	private JRadioButton rdbtnDetailedStatement;
	/**
	 * Group for JRadiobuttons
	 */
	private ButtonGroup group;
	/**
	 * JPasswordField for input PIN
	 */
	private JPasswordField passwordField;

	/**
	 * Constructor
	 */
	public Client() {

		// build a containing JFrame for display
		main = new JFrame("EPL463 - Assignment 1");
		main.getContentPane().setLayout(null);
		main.setBounds(100, 100, 440, 220);

		JLabel lblAtmMachine = new JLabel("ATM Machine");
		lblAtmMachine.setBounds(0, 0, 432, 16);
		lblAtmMachine.setHorizontalAlignment(SwingConstants.CENTER);
		main.getContentPane().add(lblAtmMachine);

		JLabel lblEnterAccountNumber = new JLabel("Enter Account Number:");
		lblEnterAccountNumber.setBounds(10, 29, 133, 42);
		main.getContentPane().add(lblEnterAccountNumber);

		JLabel EnterSecretPIN = new JLabel("Enter secret PIN:");
		EnterSecretPIN.setBounds(10, 63, 133, 42);
		main.getContentPane().add(EnterSecretPIN);

		textFieldAccountNumber = new JTextField();
		textFieldAccountNumber.setBounds(155, 42, 265, 29);
		main.getContentPane().add(textFieldAccountNumber);
		textFieldAccountNumber.setColumns(10);

		btnGetStatement = new JButton("Get Statement");
		btnGetStatement.addActionListener(this);
		btnGetStatement.setBounds(10, 108, 144, 29);
		main.getContentPane().add(btnGetStatement);

		rdbtnBalanceOnly = new JRadioButton("Balance Only");
		rdbtnBalanceOnly.setBounds(10, 146, 101, 25);
		main.getContentPane().add(rdbtnBalanceOnly);

		rdbtnMiniStatement = new JRadioButton("Mini Statement");
		rdbtnMiniStatement.setBounds(145, 146, 127, 25);
		main.getContentPane().add(rdbtnMiniStatement);

		rdbtnDetailedStatement = new JRadioButton("Detailed Statement");
		rdbtnDetailedStatement.setBounds(276, 146, 144, 25);
		group = new ButtonGroup();

		group.add(rdbtnBalanceOnly);
		group.add(rdbtnMiniStatement);
		group.add(rdbtnDetailedStatement);

		main.getContentPane().add(rdbtnDetailedStatement);

		passwordField = new JPasswordField();
		passwordField.setBounds(155, 73, 265, 29);
		main.getContentPane().add(passwordField);
		main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		main.setVisible(true);

	}

	/**
	 * main
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		sa = new SecurityAlgorithm();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Client();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Listener for button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String accountNumber = textFieldAccountNumber.getText();
		String secretPIN = String.valueOf(passwordField.getPassword());
		String selection = getSelectedButtonText(group);

		if (selection == null) {
			// System.out.println("Nothing selected");
			JOptionPane.showMessageDialog(this.main, "Please select a statement", "Nothing selected",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Account acc = AccountsDatabase.getAccountsDatabase().getAccount(accountNumber);
		if (acc == null) {

			JOptionPane.showMessageDialog(this.main, "Account doesn't exists", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (sa.checkPIN(accountNumber, secretPIN)) {
			
			StatementType statement = AbstractFactoryMaker.getStatementFactory("StatementFactory").createStatements(selection, acc);
			// createStatement
			@SuppressWarnings("unused")
			iTextPDFWriter pdf = new iTextPDFWriter(selection+"&&"+statement.print());
			Object[] options = { "Open with Desktop App", "Open here using IcePDFViewer", "Don't Open" };
			int selectedOption = JOptionPane.showOptionDialog(this.main,
					selection+".pdf created!\n" + "How would you like to open it?", "Statement",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

			if (selectedOption == 0) {// Desktop open
				desktopOpenPDF(selection+".pdf");
			} else if (selectedOption == 1) {// IcePDFViewer open
				JPanel pdfView=IcePDFViewer.getIcePDFViewer().createViewer(selection+".pdf");
				// Create a JFrame to display the panel in
				JFrame window = new JFrame(selection);
				window.getContentPane().add(pdfView);
				window.pack();
				window.setVisible(true);
			} else
				return;
		} else {

			JOptionPane.showMessageDialog(this.main, "Wrong PIN", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

	}

	/**
	 * Get the selected radio button text from group
	 * 
	 * @param buttonGroup
	 *            group of buttons
	 * @return selected radio button name
	 */
	private String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}

	/**
	 * Open a PDF file using the Desktop application
	 * 
	 * @param filepath
	 *            path of PDF
	 */
	private void desktopOpenPDF(String filepath) {
		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File(filepath);
				Desktop.getDesktop().open(myFile);
			} catch (IOException ex) {
				System.err.println("No application registered for PDFs");
				ex.printStackTrace();
			}
		}
	}
}
