package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.TemplateManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.UserService;
import services.RequestService;

import entities.User;
import enums.Usertype;
import exceptions.CredentialsException;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import static org.mockito.Mockito.*;

class TestingWebTest {
	
	UserService userService;
	

	
	@BeforeEach
	void setUp() throws Exception {
		userService = mock(UserService.class);
		
		User userTest = new User();
		userTest.setName("Bruno");
		userTest.setSurname("Bucciarati");
		userTest.setMail("farmer@mail.com");
		userTest.setId(3);
		userTest.setPassword("farmer");
		userTest.setUsertype(Usertype.Farmer);
		
		when(userService.checkCredentials("farmer@mail.com","farmer")).thenReturn(userTest);
	}
		
		
		@Test
		void test() {
			boolean a = true;
			assertEquals(true, a);
		}
		
		@Test
		void test2() {
			boolean a = true;
			assertFalse(false);
		}
		
		
		@Test
		void test3() {
			UserService userService = new UserService();
			assertNotNull(userService);
		}
		
		@Test
		void test4() {
			RequestService requestService = new RequestService();
			assertNotNull(requestService);
		}


@Test
void testUserServiceFakeUnit () throws CredentialsException {
	
	
	//UserService userService= new UserService();
	
	
	User userTest = new User();
	userTest.setName("Bruno");
	userTest.setSurname("Bucciarati");
	userTest.setMail("farmer@mail.com");
	userTest.setId(3);
	userTest.setPassword("farmer");
	userTest.setUsertype(Usertype.Farmer);
	
	User userTest2 = new User();

	userTest2 = userService.checkCredentials("farmer@mail.com","farmer");
	
	assertEquals(userTest.getName(), userTest2.getName());
	
	
	
} 

}

/*


	
	
	
	 
	
	 
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		boolean a = true;
		assertEquals(true, a);
	}
	
	@Test
	void test2() {
		boolean a = true;
		assertFalse(false);
	}
*/	


