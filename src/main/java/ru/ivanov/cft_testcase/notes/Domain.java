/**
 * 
 */
package ru.ivanov.cft_testcase.notes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * @author papa
 *
 */
public class Domain {
	public Domain(SessionFactory factory) {
		sessionFactory = factory;
	}

	public long addNote(Note note) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			
			try {
				session.save(note);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw e;
			}

			return note.getId();
		}
	}

	public void deleteNote(Note note) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			
			try {
				session.delete(note);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw e;
			}
		}
	}
	
	public void deleteNote(long noteId) {
		Note note = new Note();
		note.setId(noteId);
		deleteNote(note);
	}
	
	public Note getNote(long noteId) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(Note.class, noteId);
		}
	}

	public void updateNote(Note note) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			
			try {
				session.update(note);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw e;
			}
		}
	}
	
	public long noteCount() {
		try (Session session = sessionFactory.openSession()) {
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(COUNT_ALL);
			return (Long) query.uniqueResult();
		}
	}

	private final SessionFactory sessionFactory;
	private static final String COUNT_ALL = "SELECT count(note) FROM Note note";
}
