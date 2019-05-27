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

public class ImperialBMI extends Dialog {

	protected Object result;
	protected Shell imperialShell;
	static BMI ImperialBMI = new BMI();   //Instantiate new object BMI for imperial entries
	private Text text_NAME;
	private Text text_IN;
	private Text text_LBS;
	private Text text_3;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ImperialBMI(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		imperialShell.open();
		imperialShell.layout();
		Display display = getParent().getDisplay();
		while (!imperialShell.isDisposed()) {
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
		imperialShell = new Shell(getParent(), SWT.DIALOG_TRIM);
		imperialShell.setSize(537, 343);
		imperialShell.setText(getText());
		
		Label lblPatientName = new Label(imperialShell, SWT.NONE);
		lblPatientName.setBounds(52, 42, 96, 20);
		lblPatientName.setText("Patient Name:");
		
		text_NAME = new Text(imperialShell, SWT.BORDER);
		text_NAME.setBounds(52, 68, 194, 26);
		
		Button btnSubmit = new Button(imperialShell, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String patientName;
				int mHeight;
				int mWeight;
				int iOption = 2;
				try {
					patientName = text_NAME.getText();
					mHeight = Integer.parseInt(text_IN.getText());
					mWeight = Integer.parseInt(text_LBS.getText());

					ImperialBMI.setName(patientName);
					ImperialBMI.setHeight(mHeight, iOption);                  //Call setHeight method and set height value for BMI metric object
					ImperialBMI.setWeight(mWeight, iOption);
					ImperialBMI.BMIcalc(iOption);                            //Call BMIcalc method for final metric calculation 
					
					BMI s = new BMI(patientName, mWeight, mHeight, iOption);
					MainForm.BMIList.add(s);
					text_3.setText("Entry submitted!");
					
				} catch (Exception m) {
					//System.out.println("Only numbers allowed. Please re-enter.");
					text_3.setText("Only numbers allowed.");
				}
			}
		});
		btnSubmit.setBounds(388, 225, 107, 43);
		btnSubmit.setText("Submit");
		
		Button btnReturn = new Button(imperialShell, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				imperialShell.close();
			}
		});
		btnReturn.setText("Return");
		btnReturn.setBounds(257, 225, 107, 43);
		
		Label lblCm = new Label(imperialShell, SWT.NONE);
		lblCm.setBounds(52, 127, 70, 20);
		lblCm.setText("In:");
		
		Label lblKgs = new Label(imperialShell, SWT.NONE);
		lblKgs.setText("Lbs:");
		lblKgs.setBounds(176, 127, 70, 20);
		
		Button btnClear = new Button(imperialShell, SWT.NONE);
		btnClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			      text_NAME.setText("");
			      text_IN.setText("");
			      text_LBS.setText("");
			      text_3.setText("");
			}
		});
		btnClear.setText("Clear");
		btnClear.setBounds(126, 225, 107, 43);
		
		text_IN = new Text(imperialShell, SWT.BORDER);
		text_IN.setBounds(52, 153, 70, 26);
		
		text_LBS = new Text(imperialShell, SWT.BORDER);
		text_LBS.setBounds(176, 153, 70, 26);
		
		text_3 = new Text(imperialShell, SWT.NONE);
		text_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		text_3.setBounds(319, 127, 177, 43);
		imperialShell.setTabList(new Control[]{text_NAME, text_IN, text_LBS, btnClear, btnReturn, btnSubmit});

	}
}
