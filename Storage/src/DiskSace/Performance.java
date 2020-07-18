package DiskSace;

import java.awt.EventQueue;

import javax.swing.JFrame;
import eu.hansolo.steelseries.extras.Radar;
import eu.hansolo.steelseries.gauges.RadialCounterBeanInfo;
import eu.hansolo.steelseries.gauges.Radial;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import eu.hansolo.steelseries.tools.PointerType;
import java.util.Properties;
import java.util.Timer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message.RecipientType;
import javax.swing.JTabbedPane;
import java.awt.Window.Type;


public class Performance {
	private ConfigUtility configUtil = new ConfigUtility();
	JFrame frame;
	private Radial radial;
	private JLabel Total;
	private JLabel Avalible;
	private JLabel Message;
	private JLabel lblDiskName;
	private JTextField DriveName;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Performance window = new Performance();
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
	public Performance() {
		initialize();
		//radial.animate(true);
		radial.setTitle("Storage");
		radial.setUnitString("Percentage");
		
		JLabel label = new JLabel("Total Space :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 66, 87, 15);
		frame.getContentPane().add(label);
		
		Total = new JLabel("");
		Total.setBounds(107, 61, 99, 20);
		frame.getContentPane().add(Total);
		
		JLabel label_2 = new JLabel("Avalible Space :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(10, 99, 85, 15);
		frame.getContentPane().add(label_2);
		
		Avalible = new JLabel("");
		Avalible.setBounds(107, 94, 100, 20);
		frame.getContentPane().add(Avalible);
		
		Message = new JLabel("");
		Message.setBounds(101, 256, 227, 30);
		frame.getContentPane().add(Message);
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File cDrive = new File(String.format(DriveName.getText()+":"));
				 if (cDrive.exists()){
				Total.setText(String.format("%.2f GB",
					  (double)cDrive.getTotalSpace() /1073741824));
				Avalible.setText(String.format("%.2f GB", 
					  (double)cDrive.getUsableSpace() /1073741824));	
     //Percentage calculation
				double total=((double)cDrive.getTotalSpace() /1073741824);
				double avalible=((double)cDrive.getUsableSpace() /1073741824);
				int percentage;
				percentage = (int) (avalible/total*100);
				double Space = 100-percentage;
				
				radial.setValueAnimated(Space);
				if(cDrive.getUsableSpace() /1073741824 < 80){
						Message.setText("Your Diskspace is low.");
					}else{
						Message.setText("Your Diskspace is Normal.");
					}
		}else{
			Message.setText("Enter Valid Disk Name.");
		}
				//Email
				
				String toAddress = "vibuchakaravarthy@gmail.com";
				String subject =  "Hi";
				String message = "Send Through Performance";
				
				File[] attachFiles = null;
				
				
				try {
					Properties smtpProperties = configUtil.loadProperties();
					EmailUtility.sendEmail(smtpProperties, toAddress, subject, message, attachFiles);
					
					
				} catch (Exception ex) {
					
				}
				
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnShow.setBounds(10, 125, 69, 23);
		frame.getContentPane().add(btnShow);
		
		lblDiskName = new JLabel("Disk Name :");
		lblDiskName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDiskName.setBounds(10, 38, 85, 14);
		frame.getContentPane().add(lblDiskName);
		
		DriveName = new JTextField();
		DriveName.setBounds(107, 36, 86, 20);
		frame.getContentPane().add(DriveName);
		DriveName.setColumns(10);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Storage Space");
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 450, 358);
		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.dispose();
		radial = new Radial();
		radial.setThreshold(80.0);
		radial.setTitle("Storage");
		radial.setUnitString("Percentage");
		radial.setLcdUnitString("Percentage");
		radial.setBounds(216, 37, 207, 210);
		frame.getContentPane().add(radial);
	}
}
