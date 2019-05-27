import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Control;

public class MetricBMI extends Dialog {

	protected Object result;
	protected Shell metriShell;
	private Text text_NAME;
	static BMI metricBMI = new BMI();   //Instantiate new object BMI for metric entries
	private Text text_CM;
	private Text text_KGS;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MetricBMI(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}
	
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		metriShell.open();
		metriShell.layout();
		Display display = getParent().getDisplay();
		while (!metriShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		metriShell = new Shell(getParent(), SWT.DIALOG_TRIM);
		metriShell.setSize(537, 343);
		metriShell.setText(getText());
		
		Label lblPatientName = new Label(metriShell, SWT.NONE);
		lblPatientName.setBounds(52, 42, 96, 20);
		lblPatientName.setText("Patient Name:");
		
		text_NAME = new Text(metriShell, SWT.BORDER);
		text_NAME.setBounds(52, 68, 194, 26);
		
		Button buttonSubmit = new Button(metriShell, SWT.NONE);
		buttonSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String patientName;
				int mHeight;
				int mWeight;
				int iOption = 1;
					patientName = text_NAME.getText();
					if (patientName.isEmpty()) {
						text_1.setText("Name cannot be empty! Defaulted to Unknown.");
					}
				try {	
					mHeight = Integer.parseInt(text_CM.getText());
					mWeight = Integer.parseInt(text_KGS.getText());

					metricBMI.setName(patientName);
					metricBMI.setHeight(mHeight, iOption);                  //Call setHeight method and set height value for BMI metric object
					metricBMI.setWeight(mWeight, iOption);
					metricBMI.BMIcalc(iOption);                            //Call BMIcalc method for final metric calculation 
					
					BMI s = new BMI(patientName, mWeight, mHeight, iOption);
					MainForm.BMIList.add(s);
					text_1.setText("Entry submitted!");
					
				} catch (Exception m) {
					//System.out.println("Only numbers allowed. Please re-enter.");
					text_1.setText("Only numbers allowed.");
				}
			}
		});
		buttonSubmit.setBounds(388, 225, 107, 43);
		buttonSubmit.setText("Submit");
		
		Button btnReturn = new Button(metriShell, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				metriShell.close();
			}
		});
		btnReturn.setText("Return");
		btnReturn.setBounds(257, 225, 107, 43);
		
		Label lblCm = new Label(metriShell, SWT.NONE);
		lblCm.setBounds(52, 127, 70, 20);
		lblCm.setText("CM:");
		
		Label lblKgs = new Label(metriShell, SWT.NONE);
		lblKgs.setText("Kgs:");
		lblKgs.setBounds(176, 127, 70, 20);
		
		Button buttonClear = new Button(metriShell, SWT.NONE);
		buttonClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			      text_NAME.setText("");
			      text_CM.setText("");
			      text_KGS.setText("");
			      text_1.setText("");
			}
		});
		buttonClear.setText("Clear");
		buttonClear.setBounds(129, 225, 107, 43);
		
		text_CM = new Text(metriShell, SWT.BORDER);
		text_CM.setEnabled(true);
		text_CM.setBounds(52, 153, 70, 26);
		
		text_KGS = new Text(metriShell, SWT.BORDER);
		text_KGS.setBounds(176, 153, 70, 26);
		
		text_1 = new Text(metriShell, SWT.NONE);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		text_1.setBounds(318, 108, 177, 62);
		metriShell.setTabList(new Control[]{text_NAME, text_CM, text_KGS, buttonClear, btnReturn, buttonSubmit});
	}
}
