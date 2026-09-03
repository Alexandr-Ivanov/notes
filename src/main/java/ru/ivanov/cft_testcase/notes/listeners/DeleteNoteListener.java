package ru.ivanov.cft_testcase.notes.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import ru.ivanov.cft_testcase.notes.NotesController;

public class DeleteNoteListener implements SelectionListener {
    private final Shell shell;
    private final Table table;
    private final String title;
    private final NotesController controller;

    public DeleteNoteListener(Shell shell, Table table, String title, NotesController controller) {
        this.shell = shell;
        this.table = table;
        this.title = title;
        this.controller = controller;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        var selection = table.getSelection();

        if (0 < selection.length) {
            var item = selection[0];
            int result = getDecision(item);
            System.out.println(result);

            handleResult(result, item);
        }
    }

    private int getDecision(TableItem item) {
        System.out.println(item.getText(0));
        var box = new MessageBox(shell, SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION | SWT.APPLICATION_MODAL);
        box.setMessage(title + ": " + item.getText(1));
        return box.open();
    }

    private void handleResult(int result, TableItem item) {
        if (SWT.OK == result) {
            long noteId = Long.parseLong(item.getText(0));
            controller.deleteNote(noteId);
        }
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {

    }
}