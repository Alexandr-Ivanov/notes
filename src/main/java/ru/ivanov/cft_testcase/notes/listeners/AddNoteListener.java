package ru.ivanov.cft_testcase.notes.listeners;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import ru.ivanov.cft_testcase.notes.Domain;
import ru.ivanov.cft_testcase.notes.MenuFormer;
import ru.ivanov.cft_testcase.notes.views.StringDialog;

public class AddNoteListener extends SelectionAdapter {
    private final Shell shell;
    private final Table table;
    private final Domain domain;
    private final String title;

    public AddNoteListener(Shell shell, Table table, Domain domain, String title) {
        this.shell = shell;
        this.table = table;
        this.domain = domain;
        this.title = title;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        StringDialog dialog = new StringDialog(shell, title);
        String content = dialog.open("");

        if (null != content && !content.isEmpty()) {
            domain.addNote(content);
            MenuFormer.refreshTable(table, domain);
        }
    }
}