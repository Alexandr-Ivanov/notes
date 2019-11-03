package ru.ivanov.cft_testcase.notes.views;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

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
		shell.setSize(450, 300);
		shell.setText(getText());

		text = new Text(shell, SWT.BORDER);
		text.setBounds(106, 44, 73, 25);
		text.setText(oldText);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 44, 68, 17);
		lblNewLabel.setText("New Label");

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				result = text.getText();
				shell.close();
			}
		});
		
		btnNewButton.setBounds(106, 231, 92, 33);
		btnNewButton.setText("New Button");

	}
}
