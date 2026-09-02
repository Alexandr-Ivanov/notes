/**
 *
 */
package ru.ivanov.cft_testcase.notes;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import ru.ivanov.cft_testcase.notes.views.StringDialog;

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
    }

    private static void addEditNoteMenuItem(Shell shell, Table table, Domain domain, Menu menuBar) {
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
    }

    private static void addAddNoteMenuItem(Shell shell, Table table, Domain domain, Menu menuBar) {
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
    }

    protected static void refreshTable(Table table, Domain domain) {
        table.setLinesVisible(false);
        table.removeAll();
        fillTable(table, domain);
        table.setLinesVisible(true);
    }

    public static void fillTable(Table table, Domain domain) {
        List<Note> notes = domain.getAllNotes();

        for (Note note : notes) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[] { Long.toString(note.getId()), note.getContent() });
        }
    }

    private static final String ADD_NOTE = " добавить запись";
    private static final String EDIT_NOTE = "изменить запись";
    private static final String DELETE_NOTE = "удалить запись";
}