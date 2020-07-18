package DiskSace;

import java.awt.EventQueue;
import java.util.Timer;

import DiskSace.ScheduledTask;

public class Email {

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
		
		Timer time = new Timer(); 
			ScheduledTask st = new ScheduledTask(); 
			time.schedule(st, 0,1800000);

			//30 min = 1800000
			 //Terminates the program
			//System.exit(0);
	}

}
