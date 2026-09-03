/**
 *
 */
package ru.ivanov.cft_testcase.notes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import ru.ivanov.cft_testcase.notes.listeners.AddNoteListener;
import ru.ivanov.cft_testcase.notes.listeners.DeleteNoteListener;
import ru.ivanov.cft_testcase.notes.listeners.EditNoteListener;

/**
 * @author a.ivanov
 *
 */
public class MenuFormer {

    public static void addMenuItems(Shell shell, Table table, Menu menuBar, NotesController controller) {
        addAddNoteMenuItem(shell, menuBar, controller);
        addEditNoteMenuItem(shell, table, menuBar, controller);
        addDeleteNoteMenuItem(shell, table, menuBar, controller);
    }

    private static void addDeleteNoteMenuItem(Shell shell, Table table, Menu menuBar, NotesController controller) {
        var deleteNoteItem = new MenuItem(menuBar, SWT.PUSH);
        deleteNoteItem.setText(DELETE_NOTE);
        deleteNoteItem.addSelectionListener(new DeleteNoteListener(shell, table, DELETE_NOTE, controller));
    }

    private static void addEditNoteMenuItem(Shell shell, Table table, Menu menuBar, NotesController controller) {
        var editNoteItem = new MenuItem(menuBar, SWT.PUSH);
        editNoteItem.setText(EDIT_NOTE);
        editNoteItem.addSelectionListener(new EditNoteListener(shell, table, EDIT_NOTE, controller));
    }

    private static void addAddNoteMenuItem(Shell shell, Menu menuBar, NotesController controller) {
        var addNoteItem = new MenuItem(menuBar, SWT.PUSH);
        addNoteItem.setText(ADD_NOTE);
        addNoteItem.addSelectionListener(new AddNoteListener(shell, ADD_NOTE, controller));
    }

    private static final String ADD_NOTE = " добавить запись";
    private static final String EDIT_NOTE = "изменить запись";
    private static final String DELETE_NOTE = "удалить запись";
}