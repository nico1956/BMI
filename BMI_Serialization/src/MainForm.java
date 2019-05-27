/* This program will calculate your BMI in metric or imperial units. It will use the public class BMI to create
 * new BMI objects. The user will be able to look a list of all the entries and search for a specific on a GUI.
 * 
 * Nico Busatto 04/18/2019
 *
 */

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainForm {

	protected Shell MainShell;
	private static File file;
	private final static String BMI = "BMI.ser";
	public static List<BMI> BMIList = new ArrayList<BMI>();
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainForm window = new MainForm();
			window.open();
				
			try
	        {
				//output file is being appended
	            FileOutputStream outFile = new FileOutputStream(BMI, false); 
	            //output file is being rewritten, not appending
	            //FileOutputStream outFile = new FileOutputStream(FILENAME, true); 
				ObjectOutputStream outStream = new ObjectOutputStream(outFile); 	              	               
	            outStream.writeObject(BMIList);	          
	            outStream.close(); 
	            outFile.close();
	        } 
	        catch(IOException ex) 
	        { 
	            System.out.println("Error writing songs to file" + ex.getMessage()); 
	        }
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
			
			try
	        {
				file = new File(BMI);
				if(file.exists()) {
					FileInputStream inFile = new FileInputStream(BMI);
					ObjectInputStream inStream = new ObjectInputStream(inFile);            
					BMIList.add(new BMI());
		            //BMIValue = BMIList.size();
		            inStream.close(); 
		            inFile.close();  
				}
	        } 
	        catch(IOException ex) 
	        { 
	            System.out.println("Error reading Patient file"); 
	        }
	}	

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		MainShell.open();
		MainShell.layout();
		while (!MainShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	@SuppressWarnings("unchecked")
	protected void createContents() {
		MainShell = new Shell();
		MainShell.setSize(574, 367);
		MainShell.setText("SWT Application");
		
		Button btnMetricBMI = new Button(MainShell, SWT.NONE);
		btnMetricBMI.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MetricBMI mBMI = new MetricBMI(MainShell, 0);
				mBMI.open();
			}
		});
		btnMetricBMI.setBounds(119, 68, 134, 67);
		btnMetricBMI.setText("Metric BMI");
		
		Button btnImperialBmi = new Button(MainShell, SWT.NONE);
		btnImperialBmi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ImperialBMI iBMI = new ImperialBMI(MainShell, 0);
				iBMI.open();
			}
		});
		btnImperialBmi.setText("Imperial BMI");
		btnImperialBmi.setBounds(296, 68, 127, 67);
		
		Button btnAllPatients = new Button(MainShell, SWT.NONE);
		btnAllPatients.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AllPatients allPats = new AllPatients(MainShell, 0);
				allPats.open();
			}
		});
		btnAllPatients.setText("All Patients");
		btnAllPatients.setBounds(71, 191, 118, 52);
		
		Button btnExit = new Button(MainShell, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MainShell.close();
			}
		});
		btnExit.setText("Exit");
		btnExit.setBounds(219, 191, 118, 52);
		
		Button btnSearchPatient = new Button(MainShell, SWT.NONE);
		btnSearchPatient.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchPatient srcPat = new SearchPatient(MainShell, 0);
					srcPat.open();
			}
		});
		btnSearchPatient.setText("Search Patient");
		btnSearchPatient.setBounds(364, 191, 118, 52);
		
		try
        {
			file = new File(BMI);
			if(file.exists()) {
				FileInputStream inFile = new FileInputStream(BMI);
				ObjectInputStream inStream = new ObjectInputStream(inFile);            
	            BMIList = (ArrayList<BMI>)inStream.readObject();
	            inStream.close(); 
	            inFile.close();  
			}
        } 
        catch(IOException ex) 
        { 
            System.out.println("Error reading Patient file"); 
        } 
		catch(ClassNotFoundException ex) 
        { 
            
        }
	}
}
