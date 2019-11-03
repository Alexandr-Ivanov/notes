package ru.ivanov.cft_testcase.notes.views;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

public class StringDialog extends Dialog {

	protected String result;
	protected Shell shell;
	private Text text;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public StringDialog(Shell parent, int style, String title) {
		super(parent, style);
		setText(title);
	}

	public StringDialog(Shell parent, String title) {
		super(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		setText(title);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public String open(String oldText) {
		createContents(oldText);
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents(String oldText) {
		shell = new Shell(getParent(), getStyle());

		shell.setLayout(new GridLayout(2, true));
		shell.setSize(450, 300);
		shell.setText(getText());

		text = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		text.setText(oldText);
		GridData gridData = new GridData(GridData.VERTICAL_ALIGN_FILL, GridData.HORIZONTAL_ALIGN_FILL, true, true, 2, 1);
		text.setLayoutData(gridData);
		text.setSize(400, 25);

		Button buttonOK = new Button(shell, SWT.PUSH);
		buttonOK.setText("Ok");
		buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		Button buttonCancel = new Button(shell, SWT.PUSH);
		buttonCancel.setText("Cancel");
		buttonOK.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				result = text.getText();
				shell.dispose();
			}
		});
		buttonCancel.addListener(SWT.Selection, new Listener() {
	    	  @Override
		      public void handleEvent(Event event) {
		        result = null;
		        shell.dispose();
		      }
		    });
	}
}
