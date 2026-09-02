package ru.ivanov.cft_testcase.notes.listeners;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import ru.ivanov.cft_testcase.notes.Domain;
import ru.ivanov.cft_testcase.notes.Note;
import ru.ivanov.cft_testcase.notes.NotesController;
import ru.ivanov.cft_testcase.notes.views.StringDialog;

public class EditNoteListener extends SelectionAdapter {
    private final Shell shell;
    private final Table table;
    private final Domain domain;
    private final String title;

    public EditNoteListener(Shell shell, Table table, Domain domain, String title) {
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
            StringDialog dialog = new StringDialog(shell, title);
            String content = dialog.open(item.getText(1));

            if (null != content && !content.isEmpty()) {
                Note note = domain.getNote(Long.parseLong(item.getText(0)));
                note.setContent(content);
                domain.updateNote(note);
                NotesController.refreshTable(table, domain);
            }
        }
    }
}