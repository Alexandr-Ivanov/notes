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
        try (var sessionFactory = initSessionFactory()) {
            var display = new Display();
            var shell = newShell(display, new Domain(sessionFactory));
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
        var shell = createShell(display);
        var table = newTable(shell);
        var controller = new NotesController(table, domain);
        formMenu(shell, table, controller);
        controller.fillTable();
        return shell;
    }

    private static Shell createShell(Display display) {
        var shell = new Shell(display);
        shell.setText(TITLE);
        var layout = new FillLayout();
        shell.setLayout(layout);
        return shell;
    }

    private static void formMenu(Shell shell, Table table, NotesController controller) {
        var menuBar = new Menu(shell, SWT.BAR);
        MenuFormer.addMenuItems(shell, table, menuBar, controller);
        shell.setMenuBar(menuBar);
    }

    /**
     * @param shell
     * @return
     */
    private static Table newTable(Shell shell) {
        var table = new Table(shell, SWT.BORDER | SWT.SINGLE);
        var data = new GridData(SWT.RIGHT, SWT.LEFT, true, true);
        data.heightHint = 200;
        table.setLayoutData(data);
        var tableColumn = new TableColumn(table, SWT.RIGHT);
        tableColumn.setWidth(50);
        var tableColumn2 = new TableColumn(table, SWT.LEFT);
        tableColumn2.setWidth(500);
        return table;
    }

    private static SessionFactory initSessionFactory() {
        var configuration = new Configuration().configure();
        return configuration.buildSessionFactory();
    }

    private static final String TITLE = "Notes";
}