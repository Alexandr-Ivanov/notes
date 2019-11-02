/**
 * 
 */
package ru.ivanov.cft_testcase.notes;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author papa
 *
 */
public class DomainTest {
	@Test
	public void noteCountTest() {
		try (SessionFactory sessionFactory = initSessionFactory()) {
			Domain domain = new Domain(sessionFactory);
			Assert.assertTrue(0 <= domain.noteCount());
		}
	}
	
	@Test
	public void addNoteTest() {
		try (SessionFactory sessionFactory = initSessionFactory()) {
			Domain domain = new Domain(sessionFactory);
			long oldCount = domain.noteCount();
			Note note = new Note();
			note.setContent("note content");
			domain.addNote(note);
			Assert.assertEquals(++ oldCount, domain.noteCount());
		}
	}
	
	@Test
	public void deleteExistedNoteTest() {
		try (SessionFactory sessionFactory = initSessionFactory()) {
			Domain domain = new Domain(sessionFactory);
			long oldCount = domain.noteCount();
			Note note = new Note();
			note.setContent("note content");
			domain.addNote(note);
			domain.deleteNote(note);
			Assert.assertEquals(oldCount, domain.noteCount());
		}
	}
	
	@Test
	public void deleteUnexistedNoteTest() {
		try (SessionFactory sessionFactory = initSessionFactory()) {
			Domain domain = new Domain(sessionFactory);
			long oldCount = domain.noteCount();
			
			try {
				domain.deleteNote(1L);
				oldCount = domain.noteCount();
				domain.deleteNote(1L);
			} catch (Exception e) {
			}
			
			Assert.assertEquals(oldCount, domain.noteCount());
		}
	}

	@Test
	public void getNoteTest() {
		try (SessionFactory sessionFactory = initSessionFactory()) {
			Domain domain = new Domain(sessionFactory);
			Note note = new Note();
			note.setContent("getNoteTest");
			long nodeId = domain.addNote(note);
			Note noteFromDb = domain.getNote(nodeId);
			Assert.assertNotNull(noteFromDb);
			Assert.assertEquals("getNoteTest", noteFromDb.getContent());
			domain.deleteNote(nodeId);
			Assert.assertNull(domain.getNote(nodeId));
		}
	}
	
	@Test
	public void updateNoteTest() {
		try (SessionFactory sessionFactory = initSessionFactory()) {
			Domain domain = new Domain(sessionFactory);
			Note note = new Note();
			note.setContent("before update");
			long nodeId = domain.addNote(note);
			Note noteFromDb = domain.getNote(nodeId);
			noteFromDb.setContent("new content");
			domain.updateNote(noteFromDb);
			Note updatedNote = domain.getNote(nodeId);
			Assert.assertEquals("new content", updatedNote.getContent());
		}
	}
	
	private SessionFactory initSessionFactory() {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}
}
