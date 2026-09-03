/**
 *
 */
package ru.ivanov.cft_testcase.notes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.hibernate.SessionFactory;
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
            Shell shell = newShell(display, new Domain(sessionFactory));
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
        Shell shell = createShell(display);
        Table table = newTable(domain, shell);
        formMenu(shell, table, domain);
        NotesController.fillTable(table, domain);
        return shell;
    }

    private static Shell createShell(Display display) {
        Shell shell = new Shell(display);
        shell.setText(TITLE);
        FillLayout layout = new FillLayout();
        shell.setLayout(layout);
        return shell;
    }

    private static void formMenu(Shell shell, Table table, Domain domain) {
        Menu menuBar = new Menu(shell, SWT.BAR);
        MenuFormer.addMenuItems(shell, table, domain, menuBar);
        shell.setMenuBar(menuBar);
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
        return table;
    }

    private static SessionFactory initSessionFactory() {
        Configuration configuration = new Configuration().configure();
        return configuration.buildSessionFactory();
    }

    private static final String TITLE = "Notes";
}