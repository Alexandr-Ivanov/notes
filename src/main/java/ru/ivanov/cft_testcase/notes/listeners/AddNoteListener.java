package ru.ivanov.cft_testcase.notes.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;

import ru.ivanov.cft_testcase.notes.NotesController;
import ru.ivanov.cft_testcase.notes.views.StringDialog;

public class AddNoteListener implements SelectionListener {
    private final Shell shell;
    private final String title;
    private final NotesController controller;

    public AddNoteListener(Shell shell, String title, NotesController controller) {
        this.shell = shell;
        this.title = title;
        this.controller = controller;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        StringDialog dialog = new StringDialog(shell, title);
        String content = dialog.open("");

        if (null != content && !content.isEmpty()) {
            controller.addNote(content);
        }
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {

    }
}