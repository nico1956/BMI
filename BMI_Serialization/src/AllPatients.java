import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AllPatients extends Dialog {

	protected Object result;
	protected Shell allPatsShell;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AllPatients(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		allPatsShell.open();
		allPatsShell.layout();
		Display display = getParent().getDisplay();
		while (!allPatsShell.isDisposed()) {
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
		allPatsShell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		allPatsShell.setSize(875, 480);
		allPatsShell.setText(getText());
		
		text = new Text(allPatsShell, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		text.setBounds(43, 73, 762, 274);
		
		String str = "";
		
		for(BMI b : MainForm.BMIList) {
        	str += b.toString() + "\n";
        	text.setText(str);
		}

		Label lblName = new Label(allPatsShell, SWT.NONE);
		lblName.setBounds(43, 36, 70, 20);
		lblName.setText("Name");
		
		Label lblHeight = new Label(allPatsShell, SWT.NONE);
		lblHeight.setText("Height");
		lblHeight.setBounds(236, 36, 70, 20);
		
		Label lblWeigth = new Label(allPatsShell, SWT.NONE);
		lblWeigth.setText("Weight");
		lblWeigth.setBounds(351, 36, 70, 20);
		
		Label lblType = new Label(allPatsShell, SWT.NONE);
		lblType.setText("Type");
		lblType.setBounds(464, 36, 70, 20);
		
		Label lblBmi = new Label(allPatsShell, SWT.NONE);
		lblBmi.setText("BMI");
		lblBmi.setBounds(558, 36, 70, 20);
		
		Label lblStatus = new Label(allPatsShell, SWT.NONE);
		lblStatus.setText("Status");
		lblStatus.setBounds(708, 36, 40, 20);
		
		Button btnReturn = new Button(allPatsShell, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				allPatsShell.close();
			}
		});
		btnReturn.setBounds(373, 370, 101, 38);
		btnReturn.setText("Return");
	}
}
