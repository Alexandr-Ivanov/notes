/**
 *
 */
package ru.ivanov.cft_testcase.notes;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import ru.ivanov.cft_testcase.notes.listeners.AddNoteListener;
import ru.ivanov.cft_testcase.notes.listeners.DeleteNoteListener;
import ru.ivanov.cft_testcase.notes.listeners.EditNoteListener;

/**
 * @author a.ivanov
 *
 */
public class MenuFormer {

    public static void addMenuItems(Shell shell, Table table, Domain domain, Menu menuBar) {
        addAddNoteMenuItem(shell, table, domain, menuBar);
        addEditNoteMenuItem(shell, table, domain, menuBar);
        addDeleteNoteMenuItem(shell, table, domain, menuBar);
    }

    private static void addDeleteNoteMenuItem(Shell shell, Table table, Domain domain, Menu menuBar) {
        MenuItem deleteNoteItem = new MenuItem(menuBar, SWT.PUSH);
        deleteNoteItem.setText(DELETE_NOTE);
        deleteNoteItem.addSelectionListener(new DeleteNoteListener(shell, table, domain, DELETE_NOTE));
    }

    private static void addEditNoteMenuItem(Shell shell, Table table, Domain domain, Menu menuBar) {
        MenuItem editNoteItem = new MenuItem(menuBar, SWT.PUSH);
        editNoteItem.setText(EDIT_NOTE);
        editNoteItem.addSelectionListener(new EditNoteListener(shell, table, domain, EDIT_NOTE));
    }

    private static void addAddNoteMenuItem(Shell shell, Table table, Domain domain, Menu menuBar) {
        MenuItem addNoteItem = new MenuItem(menuBar, SWT.PUSH);
        addNoteItem.setText(ADD_NOTE);
        addNoteItem.addSelectionListener(new AddNoteListener(shell, table, domain, ADD_NOTE));
    }

    private static final String ADD_NOTE = " добавить запись";
    private static final String EDIT_NOTE = "изменить запись";
    private static final String DELETE_NOTE = "удалить запись";
}