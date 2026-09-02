package ru.ivanov.cft_testcase.notes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import java.util.List;

public class NotesController {


    public static void refreshTable(Table table, Domain domain) {
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
}
