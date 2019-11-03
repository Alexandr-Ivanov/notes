/**
 * 
 */
package ru.ivanov.cft_testcase.notes;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ru.ivanov.cft_testcase.notes.views.StringDialog;

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
			Domain domain = new Domain(sessionFactory);
			Display display = new Display();

			Shell shell = newShell(display, domain);

			shell.open();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}

			display.dispose();
		}
	}

	private static Shell newShell(Display display, Domain domain) {
		Shell shell = new Shell(display);
		shell.setText(TITLE);
		FillLayout layout = new FillLayout();
		shell.setLayout(layout);

		Table table = newTable(domain, shell);
		table.setLinesVisible(true);
		formMenu(shell, table, domain);
		return shell;
	}

	private static void formMenu(Shell shell, Table table, Domain domain) {
		Menu menuBar = new Menu(shell, SWT.BAR);
		MenuItem addNoteItem = new MenuItem(menuBar, SWT.PUSH);
		addNoteItem.setText(ADD_NOTE);
		addNoteItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StringDialog dialog = new StringDialog(shell, ADD_NOTE);
				String content = dialog.open("");
				
				if (null != content && !content.isEmpty()) {
					domain.addNote(content);
					refreshTable(table, domain);
				}
			}
		});
		
		MenuItem editNoteItem = new MenuItem(menuBar, SWT.PUSH);
		editNoteItem.setText(EDIT_NOTE);
		editNoteItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selection = table.getSelection();
				
				if (0 < selection.length) {
					TableItem item = selection[0];
					System.out.println(item.getText(0));
					StringDialog dialog = new StringDialog(shell, EDIT_NOTE);
					String content = dialog.open(item.getText(1));
					
					if (null != content && !content.isEmpty()) {
						Note note = domain.getNote(Long.parseLong(item.getText(0)));
						note.setContent(content);
						domain.updateNote(note);
						refreshTable(table, domain);
					}
				}				
			}
		});
		
		MenuItem deleteNoteItem = new MenuItem(menuBar, SWT.PUSH);
		deleteNoteItem.setText(DELETE_NOTE);
		deleteNoteItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selection = table.getSelection();
				
				if (0 < selection.length) {
					TableItem item = selection[0];
					System.out.println(item.getText(0));
					MessageBox box = new MessageBox(shell, SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION | SWT.APPLICATION_MODAL);
					box.setMessage(DELETE_NOTE + ": " + item.getText(1));
					int result = box.open();
					System.out.println(result);
					
					if (SWT.OK == result) {
						domain.deleteNote(Long.parseLong(item.getText(0)));
						refreshTable(table, domain);
					}
				}
			}
		});
		
		shell.setMenuBar(menuBar);
	}

	protected static void refreshTable(Table table, Domain domain) {
		table.setLinesVisible(false);
		table.removeAll();
		fillTable(table, domain);
		table.setLinesVisible(true);
	}

	/**
	 * @param domain
	 * @param shell
	 * @return
	 */
	private static Table newTable(Domain domain, Shell shell) {
		Table table = new Table(shell, SWT.BORDER | SWT.SINGLE);
		GridData data = new GridData(SWT.RIGHT, SWT.LEFT, true, true);
		data.heightHint = 200;
		table.setLayoutData(data);
		TableColumn tableColumn = new TableColumn(table, SWT.RIGHT);
		tableColumn.setWidth(50);
		TableColumn tableColumn2 = new TableColumn(table, SWT.LEFT);
		tableColumn2.setWidth(500);
		fillTable(table, domain);
		return table;
	}

	private static void fillTable(Table table, Domain domain) {
		List<Note> notes = domain.getAllNotes();

		for (Note note : notes) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { Long.toString(note.getId()), note.getContent() });
		}
	}

	private static SessionFactory initSessionFactory() {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}

	private static final String TITLE = "Notes";
	private static final String ADD_NOTE = "добавить запись";
	private static final String EDIT_NOTE = "изменить запись";
	private static final String DELETE_NOTE = "удалить запись";
}
