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
import org.eclipse.swt.widgets.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ru.ivanov.cft_testcase.notes.Domain;
import ru.ivanov.cft_testcase.notes.MenuFormer;
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
        MenuFormer.addMenuItems(shell, table, domain, menuBar);
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
        return configuration.buildSessionFactory();
    }

    private static final String TITLE = "Notes";
    // Moved to MenuFormer: ADD_NOTE, EDIT_NOTE, DELETE_NOTE
}