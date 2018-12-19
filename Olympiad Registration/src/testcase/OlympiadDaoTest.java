package testcase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import dao.OlympiadDaoImpl;
import entity.OlympiadForm;
import exception.OlympiadException;

public class OlympiadDaoTest {
	static OlympiadDaoImpl dao;
	static OlympiadForm form;
	
	@BeforeClass
	public static void initialize() {
		System.out.println("in before class");
		dao = new OlympiadDaoImpl();
		form = new OlympiadForm();
	}
	
	@Test
	public void testAddParticipants1() throws OlympiadException, ClassNotFoundException, IOException, SQLException {

		assertNotNull(dao.addParticipants(form));

	}
	
	@Ignore
	@Test
	public void testAddParticipants() throws OlympiadException, ClassNotFoundException, IOException, SQLException {
		
	  assertEquals(100, dao.addParticipants(form));
	}
	
	
	@Test
	public void testAddParticipants2() throws OlympiadException, ClassNotFoundException, IOException, SQLException {

		form.setName("jaya");
		form.setPhoneNumber("9876543210");
		form.setInstitution("hjsdfjgfsdjjk");
		form.setDob("12/10/1996");
		assertEquals("jaya",form.getName());

	}
	

	@Test
	public void testRetrieveAll() throws OlympiadException, ClassNotFoundException, IOException, SQLException  {
		assertNotNull(dao.retriveAllDetails());
	}
	
	
	@Test
	public void testCancellation() throws ClassNotFoundException, IOException, SQLException, OlympiadException {
		assertNotNull(dao.cancellation("100"));
	}
	
	
	
}
