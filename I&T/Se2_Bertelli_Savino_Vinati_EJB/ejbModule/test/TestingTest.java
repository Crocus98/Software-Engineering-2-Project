package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import classes.TemplateManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.DisplayName;
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

import services.UserService;
import services.RequestService;

import entities.User;
import enums.Usertype;
import exceptions.CredentialsException;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



class TestingTest {
	//@EJB(name = "services/UserService")
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	
	@BeforeEach
	public void initEach(){
		
	}
	
	@BeforeEach
	public void setupClass () throws NamingException {
		ejbContainer = EJBContainer.createEJBContainer();
		ctx = ejbContainer.getContext();
		System.out.println("starting EJB container");
		System.out.println(ejbContainer.getContext());
		System.out.println();
		
		System.out.println("ohh:" + ctx.lookup("").toString());
		
	}
	
	@AfterEach
	public void stopClass () {
		ejbContainer.close();
		System.out.println("Closing EJB container");
		
	}
	
	@Test
	void testUserServiceFakeUnit () throws CredentialsException, NamingException {
		
		/*
		UserService userService= (UserService)ctx.lookup("java:global/ejbModule/services/UserService.java") ;
		//UserService userService= (UserService)ctx.lookup("") ;
		//System.out.println(ctx.lookup("").toString());
		
		
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
		
		*/
		
	}
	
	@Test
	void test() {
		boolean a = true;
		assertEquals(true, a);
	}
	
	@Test
	void test2() {
		//boolean a = true;
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
	
	
	
	

}
