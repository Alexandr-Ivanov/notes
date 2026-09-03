/**
 *
 */
package ru.ivanov.cft_testcase.notes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
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
        addMenuItem(menuBar, ADD_NOTE, new AddNoteListener(shell, ADD_NOTE, controller));
        addMenuItem(menuBar, EDIT_NOTE, new EditNoteListener(shell, table, EDIT_NOTE, controller));
        addMenuItem(menuBar, DELETE_NOTE, new DeleteNoteListener(shell, table, DELETE_NOTE, controller));
    }

    private static void addMenuItem(Menu menuBar, String ItemTitle, SelectionListener listener) {
        var menuItem = new MenuItem(menuBar, SWT.PUSH);
        menuItem.setText(ItemTitle);
        menuItem.addSelectionListener(listener);
    }

    private static final String ADD_NOTE = " добавить запись";
    private static final String EDIT_NOTE = "изменить запись";
    private static final String DELETE_NOTE = "удалить запись";
}