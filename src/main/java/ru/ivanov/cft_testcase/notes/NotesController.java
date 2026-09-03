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

    public void addNote(String content) {
        domain.addNote(content);
        refreshTable();
    }

    public void deleteNote(long noteId) {
        domain.deleteNote(noteId);
        refreshTable();
    }

    public void updateNote(long noteId, String content) {
        Note note = domain.getNote(noteId);
        note.setContent(content);
        domain.updateNote(note);
        refreshTable();
    }

    public void  refreshTable() {
        table.setLinesVisible(false);
        table.removeAll();
        doFillTable();
        table.setLinesVisible(true);
    }

    public void fillTable() {
        doFillTable();
        table.setLinesVisible(true);
    }

    private void doFillTable() {
        List<Note> notes = domain.getAllNotes();

        for (Note note : notes) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[] { Long.toString(note.getId()), note.getContent() });
        }
    }
}
