/**
 *
 */
package ru.ivanov.cft_testcase.notes;

import org.eclipse.swt.widgets.*;

/**
 * @author a.ivanov
 *
 */
public class NotesApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try (var sessionFactory = Config.initSessionFactory()) {
            var display = new Display();
            var shell = Config.newShell(display, new Domain(sessionFactory));
            shell.open();

            while (!shell.isDisposed()) {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
            }

            display.dispose();
        }
    }

}