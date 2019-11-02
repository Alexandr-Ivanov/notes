/**
 * 
 */
package ru.ivanov.cft_testcase.notes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author a.ivanov
 *
 */
public class NotesApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try (SessionFactory sessionFactory = initSessionFactory()) {
		Display display = new Display();

		Shell shell = newShell(display);


		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		display.dispose();
		}
	}

	private static Shell newShell(Display display) {
		Shell shell = new Shell(display); 
		// TODO Auto-generated method stub
		// the layout manager handle the layout
		// of the widgets in the container
		FillLayout layout = new FillLayout();
		shell.setLayout(layout);

		Button button = new Button(shell, SWT.PUSH);
		button.setText("Press me");

		// register listener for the selection event
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called!");
			}
		});
		
		return shell;
	}

	private static SessionFactory initSessionFactory() {
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}

}
