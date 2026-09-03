package ru.ivanov.cft_testcase.notes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import ru.ivanov.cft_testcase.notes.data.Note;

import java.util.List;

public class NotesController {
    private final Table table;
    private final Domain domain;

    public NotesController(Table table, Domain domain) {
        this.table = table;
        this.domain = domain;
    }

    public void  refreshTable() {
        refreshTable(table, domain);
    }

    public void fillTable() {
        doFillTable(table, domain);
        table.setLinesVisible(true);
    }

    public static void refreshTable(Table table, Domain domain) {
        table.setLinesVisible(false);
        table.removeAll();
        doFillTable(table, domain);
        table.setLinesVisible(true);
    }

    private static void doFillTable(Table table, Domain domain) {
        List<Note> notes = domain.getAllNotes();

        for (Note note : notes) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[] { Long.toString(note.getId()), note.getContent() });
        }
    }
}
