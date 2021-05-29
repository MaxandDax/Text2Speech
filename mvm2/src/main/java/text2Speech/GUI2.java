package text2Speech;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;

import commands.CommandsFactory;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;

public class GUI2 {

	private JFrame frmAdvancedtextspeech;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI2 window = new GUI2();
					window.frmAdvancedtextspeech.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final CommandsFactory cmd = new CommandsFactory();
		frmAdvancedtextspeech = new JFrame();
		frmAdvancedtextspeech.setTitle("AdvancedText2Speech");
		frmAdvancedtextspeech.setBounds(100, 100, 654, 525);
		frmAdvancedtextspeech.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdvancedtextspeech.getContentPane().setLayout(null);

		final JTextArea textArea = new JTextArea();
		textArea.setBounds(100, 63, 393, 149);
		frmAdvancedtextspeech.getContentPane().add(textArea);
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String msg = (String)cb.getSelectedItem();
				
				cmd.createCommand(msg,textArea);			
					
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"File", "Open", "Save", "Close", "Exit"}));
		comboBox.setBounds(10, 11, 54, 22);
		frmAdvancedtextspeech.getContentPane().add(comboBox);
		
		JButton text2speech = new JButton("Text2Speech");
		text2speech.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cmd.text2Speech();
			}
		});
		text2speech.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 13));
		text2speech.setBounds(207, 234, 169, 23);
		frmAdvancedtextspeech.getContentPane().add(text2speech);

		
	}
}
