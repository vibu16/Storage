package DiskSace;


import java.util.TimerTask;
import java.io.File;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.Date;

public class ScheduledTask  extends TimerTask{


	Date now;

	public void run() {
		now = new Date(); 
		File cDrive = new File("C:");
		float total = cDrive.getTotalSpace() /1073741824;
		float avalible = cDrive.getFreeSpace() /1073741824;
		System.out.println("Time:"+ now +"Total Space:" +total + "Avalible Space:"+avalible);
		int a =0;
		if(a == 0){
		if(cDrive.getFreeSpace() /1073741824 < 150){
			System.out.println("Your Diskspace is low.");
		}else{
			System.out.println("Your Diskspace is not low.");
		}
		}
		
	}
}
