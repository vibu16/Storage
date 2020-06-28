package DiskSace;

import java.awt.EventQueue;

import javax.swing.JFrame;
import eu.hansolo.steelseries.extras.Radar;
import eu.hansolo.steelseries.gauges.RadialCounterBeanInfo;
import eu.hansolo.steelseries.gauges.Radial;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Performance {

	private JFrame frame;
	private Radial radial;
	private JLabel Total;
	private JLabel Avalible;
	private JLabel Message;
	private JLabel Percentage1;
	private JLabel Percentage;
	private JLabel lblNewLabel;
	
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
		label.setBounds(10, 80, 73, 15);
		frame.getContentPane().add(label);
		
		Total = new JLabel("");
		Total.setBounds(108, 75, 99, 20);
		frame.getContentPane().add(Total);
		
		JLabel label_2 = new JLabel("Avalible Space :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(10, 119, 85, 15);
		frame.getContentPane().add(label_2);
		
		Avalible = new JLabel("");
		Avalible.setBounds(108, 114, 100, 20);
		frame.getContentPane().add(Avalible);
		
		Message = new JLabel("");
		Message.setBounds(10, 226, 227, 30);
		frame.getContentPane().add(Message);
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NumberFormat nf = NumberFormat.getNumberInstance();
				for (Path root : FileSystems.getDefault().getRootDirectories()) {

				   // System.out.print(root + ": \n");
				    try {
				        FileStore store = Files.getFileStore(root);
				        Total.setText(String.format("%.2f GB",
				      		  (double)store.getTotalSpace() /1073741824));
				        Avalible.setText(String.format("%.2f GB", 
				      		  (double)store.getUsableSpace() /1073741824));	
				       //Percentage calculation
				        double total=((double)store.getTotalSpace() /1073741824);
						double avalible=((double)store.getUsableSpace() /1073741824);
						int percentage;
						percentage = (int) (avalible/total*100);
						Percentage.setText(String.format("%.2f %%", 
					      		  (double)percentage));
						radial.setValueAnimated(percentage);
				        if(store.getUsableSpace() /1073741824 < 80){
				      			Message.setText("Your Diskspace is low.");
				    		}else{
				    			Message.setText("Your Diskspace is Normal.");
				    		}
				    } catch (IOException e) {
				        System.out.println("error querying space: " + e.toString());
				    }
				}
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnShow.setBounds(14, 179, 69, 23);
		frame.getContentPane().add(btnShow);
		
		Percentage1 = new JLabel("Percentage :");
		Percentage1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Percentage1.setBounds(10, 145, 85, 23);
		frame.getContentPane().add(Percentage1);
		
		Percentage = new JLabel("");
		Percentage.setBounds(108, 144, 99, 20);
		frame.getContentPane().add(Percentage);
		
		lblNewLabel = new JLabel("Storage Space");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(160, 11, 100, 17);
		frame.getContentPane().add(lblNewLabel);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		radial = new Radial();
		radial.setTitle("Storage");
		radial.setUnitString("Percentage");
		radial.setLcdUnitString("Percentage");
		radial.setBounds(217, 59, 207, 210);
		frame.getContentPane().add(radial);
	}
}
