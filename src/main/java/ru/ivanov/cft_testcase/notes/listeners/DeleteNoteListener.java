package ru.ivanov.cft_testcase.notes.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import ru.ivanov.cft_testcase.notes.Domain;
import ru.ivanov.cft_testcase.notes.MenuFormer;

public class DeleteNoteListener extends SelectionAdapter {
    private final Shell shell;
    private final Table table;
    private final Domain domain;
    private final String title;

    public DeleteNoteListener(Shell shell, Table table, Domain domain, String title) {
        this.shell = shell;
        this.table = table;
        this.domain = domain;
        this.title = title;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        TableItem[] selection = table.getSelection();

        if (0 < selection.length) {
            TableItem item = selection[0];
            System.out.println(item.getText(0));
            MessageBox box = new MessageBox(shell, SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION | SWT.APPLICATION_MODAL);
            box.setMessage(title + ": " + item.getText(1));
            int result = box.open();
            System.out.println(result);

            if (SWT.OK == result) {
                domain.deleteNote(Long.parseLong(item.getText(0)));
                MenuFormer.refreshTable(table, domain);
            }
        }
    }
}