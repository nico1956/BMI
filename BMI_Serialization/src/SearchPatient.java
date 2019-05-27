import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SearchPatient extends Dialog {

	protected Object result;
	protected Shell searchPatShell;
	private Text text_search;
	private Text src_list;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SearchPatient(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		searchPatShell.open();
		searchPatShell.layout();
		Display display = getParent().getDisplay();
		while (!searchPatShell.isDisposed()) {
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
		searchPatShell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		searchPatShell.setSize(875, 364);
		searchPatShell.setText(getText());
		
		Label lblName = new Label(searchPatShell, SWT.NONE);
		lblName.setBounds(66, 121, 70, 20);
		lblName.setText("Name");
		
		Label lblHeight = new Label(searchPatShell, SWT.NONE);
		lblHeight.setText("Height");
		lblHeight.setBounds(258, 121, 70, 20);
		
		Label lblWeigth = new Label(searchPatShell, SWT.NONE);
		lblWeigth.setText("Weight");
		lblWeigth.setBounds(383, 121, 70, 20);
		
		Label lblType = new Label(searchPatShell, SWT.NONE);
		lblType.setText("Type");
		lblType.setBounds(488, 121, 70, 20);
		
		Label lblBmi = new Label(searchPatShell, SWT.NONE);
		lblBmi.setText("BMI");
		lblBmi.setBounds(574, 121, 70, 20);
		
		Label lblStatus = new Label(searchPatShell, SWT.NONE);
		lblStatus.setText("Status");
		lblStatus.setBounds(719, 121, 40, 20);
		
		Button button = new Button(searchPatShell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				searchPatShell.close();
			}
		});
		button.setText("Return");
		button.setBounds(374, 242, 101, 38);
		
		Label label = new Label(searchPatShell, SWT.NONE);
		label.setText("Patient Name:");
		label.setBounds(98, 33, 96, 20);
		
		text_search = new Text(searchPatShell, SWT.BORDER);
		text_search.setBounds(98, 59, 261, 26);
		
		Button btnSearch = new Button(searchPatShell, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String patient = text_search.getText().toUpperCase();
					for (int i = 0; i <= MainForm.BMIList.size(); i++) {
						if(patient.equals(MainForm.BMIList.get(i).getName().trim().toUpperCase())) {
							src_list.setText(MainForm.BMIList.get(i).toString());
							break;
						}	
					}
				}	
				catch(Exception exc) {
					MessageBox messageBox = new MessageBox(searchPatShell, 0);
				    messageBox.setText("Patient Error");
				    messageBox.setMessage("Patient not found");
				    messageBox.open();
				}
			}	
		});
		btnSearch.setText("Search");
		btnSearch.setBounds(556, 47, 101, 38);
		
		src_list = new Text(searchPatShell, SWT.BORDER | SWT.H_SCROLL | SWT.CANCEL);
		src_list.setBounds(57, 147, 745, 76);
		
		Shell shell = new Shell(searchPatShell, SWT.DIALOG_TRIM | SWT.RESIZE);
		shell.setText("SWT Dialog");
		shell.setBounds(-18, 50, 875, 480);
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL);
		text.setBounds(43, 73, 762, 274);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("Name");
		label_1.setBounds(43, 36, 70, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("Height");
		label_2.setBounds(236, 36, 70, 20);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("Weight");
		label_3.setBounds(351, 36, 70, 20);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("Type");
		label_4.setBounds(464, 36, 70, 20);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("BMI");
		label_5.setBounds(558, 36, 70, 20);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("Status");
		label_6.setBounds(708, 36, 40, 20);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("Return");
		button_1.setBounds(373, 370, 101, 38);
	}	
}