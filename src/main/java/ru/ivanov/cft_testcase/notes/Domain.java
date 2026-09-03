/**
 * 
 */
package ru.ivanov.cft_testcase.notes;

import java.util.List;

import org.hibernate.SessionFactory;
import ru.ivanov.cft_testcase.notes.data.Note;

/**
 * @author papa
 *
 */
public class Domain {
	private final SessionFactory sessionFactory;

	private static final String COUNT_ALL = "SELECT count(note) FROM Note note";
	public static final String FROM_NOTE_NOTE = "FROM Note note";

	public Domain(SessionFactory factory) {
		sessionFactory = factory;
	}

	public void addNote(String content) {
		var note = new Note();
		note.setContent(content);
		addNote(note);
	}
	
	public long addNote(Note note) {
		try (var session = sessionFactory.openSession()) {
			var transaction = session.beginTransaction();
			
			try {
				session.persist(note);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw e;
			}

			return note.getId();
		}
	}

	public void deleteNote(Note note) {
		try (var session = sessionFactory.openSession()) {
			var transaction = session.beginTransaction();
			
			try {
				session.remove(note);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw e;
			}
		}
	}
	
	public void deleteNote(long noteId) {
		var note = new Note();
		note.setId(noteId);
		deleteNote(note);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Note> getAllNotes() {
		try (var session = sessionFactory.openSession()) {
			var query = session.createQuery(FROM_NOTE_NOTE);
			return query.getResultList();
		}
		
	}
	
	public Note getNote(long noteId) {
		try (var session = sessionFactory.openSession()) {
			return session.find(Note.class, noteId);
		}
	}

	public void updateNote(Note note) {
		try (var session = sessionFactory.openSession()) {
			var transaction = session.beginTransaction();
			
			try {
				session.merge(note);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw e;
			}
		}
	}
	
	public long noteCount() {
		try (var session = sessionFactory.openSession()) {
			var query = session.createQuery(COUNT_ALL);
			return (Long) query.uniqueResult();
		}
	}
}
