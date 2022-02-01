package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.*;
import enums.*;

class EntitiesTest {

	//TEST FOR Answer
	
	@Test
	void AnswerCommentTest() {
		Answer answer = new Answer();
		answer.setComment("aa");
		assertEquals("aa", answer.getComment());
	}
	
	@Test
	void AnswerIdTest() {
		Answer answer = new Answer();
		answer.setId(11);
		assertEquals(11, answer.getId());
	}
	
	@Test
	void AnswerDateHourTest() {
		Date date = new Date();
		Answer answer = new Answer();
		answer.setDatehour(date);
		assertEquals(date, answer.getDatehour() );
	}
	
	
	@Test
	void AnswerRequestTest() {
		Answer answer = new Answer();
		Request request = new Request();
		request.setComment("the flavour of life");
		answer.setRequest(request);
		assertEquals(request.getComment(), answer.getRequest().getComment());
	}
	
	@Test
	void AnswerUserTest() {
		Answer answer = new Answer();
		User user = new User();
		user.setId(1);
		user.setName("bff");
		answer.setUser(user);;
		assertEquals(user, answer.getUser());
	}
	
	
	//Test for Area
	@Test
	void AreaIdTest() {
		Area area = new Area();
		area.setId(0);
		assertEquals(0, area.getId());
	}
	
	void AreaForecastTest() {
		Area area = new Area();
		Forecast f1 = new Forecast();
		f1.setArea(area);
		
		Forecast f2 = new Forecast();
		f2.setArea(area);
		
		ArrayList<Forecast> forecastList = new ArrayList();
		forecastList.add(f1);
		forecastList.add(f2);
		
		area.setForecasts(forecastList);
		assertEquals(forecastList, area.getForecasts());
	}
	
	@Test
	void AreaFarmTest() {
		Area area = new Area();
		Farm f1 = new Farm();
		Farm f2 = new Farm();
		
		ArrayList<Farm> farmList = new ArrayList();
		farmList.add(f1);
		farmList.add(f2);
		
		area.setFarms(farmList);
		assertEquals(farmList, area.getFarms());
	}
	
	@Test
	void AreaNameTest() {
		Area area = new Area();
		area.setName("Telan1");
		
		assertEquals("Telan1", area.getName());
	}
	
	@Test
	void AreaUserTest() {
		Area area = new Area();
		User user = new User();
		user.setId(0);
		area.setUser(user);
		assertEquals(user, area.getUser());
	}
	
	//Test for User
	
	@Test
	void UserIdTest() {
		User user = new User();
		user.setId(0);
		assertEquals(0,user.getId());
	}
	
	@Test
	void UserFarmTest() {
		User user = new User();
		Farm f1 = new Farm();
		user.setFarm(f1);
		assertEquals(f1,user.getFarm());
	}
	
	@Test
	void UserMailTest() {
		User user = new User();
		user.setMail("mail@mail.com");
		assertEquals("mail@mail.com",user.getMail());
	}
	
	@Test
	void UserNameTest() {
		User user = new User();
		user.setName("parvati");
		assertEquals("parvati",user.getName());
	}
	
	@Test
	void UserPasswordTest() {
		User user = new User();
		user.setPassword("pp");
		assertEquals("pp",user.getPassword());
	}
	
	@Test
	void UserSurnameTest() {
		User user = new User();
		user.setSurname("a");
		assertEquals("a",user.getSurname());
	}
	
	@Test
	void UserUserTypeTest() {
		User user = new User();
		user.setUsertype(Usertype.PolicyMaker);
		assertEquals(Usertype.PolicyMaker,user.getUsertype());
	}
	
	@Test
	void UserCompareTo1() {
		
		//set mock data for user1
				
		User user1 = new User();
		Area area1 = new Area();
		Farm farm1 = new Farm();
		farm1.setArea(area1);
		user1.setFarm(farm1);
		
		Production productionA = new Production();
		productionA.setId(0);
		productionA.setAmount(20);
		@SuppressWarnings("deprecation")
		Date dataA = new Date(2022,01,01);
		productionA.setDate(dataA);
		productionA.setFarm(farm1);
		
		Production productionB = new Production();
		productionB.setId(1);
		productionB.setAmount(40);
		@SuppressWarnings("deprecation")
		Date dataB = new Date(2022,01,05);
		productionB.setDate(dataB);
		productionB.setFarm(farm1);
		
		Waterconsumption waterA = new Waterconsumption();
		waterA.setId(0);
		waterA.setDate(dataA);
		waterA.setFarm(farm1);
		waterA.setAmount(100);
		
		Waterconsumption waterB = new Waterconsumption();
		waterB.setId(1);
		waterB.setDate(dataB);
		waterB.setFarm(farm1);
		waterB.setAmount(10);
		
		Humidityofsoil humA = new Humidityofsoil();
		humA.setId(0);
		humA.setDate(dataA);
		humA.setFarm(farm1);
		humA.setClassification(ClassificationH.Slightly_Arid);
		
		Humidityofsoil humB = new Humidityofsoil();
		humB.setId(1);
		humB.setDate(dataB);
		humB.setFarm(farm1);
		humB.setClassification(ClassificationH.Balanced);
		
		//set mock data for user2
				
		User user2 = new User();
		Farm farm2 = new Farm();
		farm2.setArea(area1);
		user2.setFarm(farm2);
		
		Production productionC = new Production();
		productionC.setId(2);
		productionC.setAmount(200);
		@SuppressWarnings("deprecation")
		Date dataC = new Date(2022,01,12);
		productionC.setDate(dataC);
		productionC.setFarm(farm2);
		
		Production productionD = new Production();
		productionD.setId(3);
		productionD.setAmount(35);
		@SuppressWarnings("deprecation")
		Date dataD = new Date(2022,01,23);
		productionD.setDate(dataC);
		productionD.setFarm(farm2);
		
		Waterconsumption waterC = new Waterconsumption();
		waterC.setId(2);
		waterC.setDate(dataC);
		waterC.setFarm(farm2);
		waterC.setAmount(50);
		
		Waterconsumption waterD = new Waterconsumption();
		waterD.setId(3);
		waterD.setDate(dataC);
		waterD.setFarm(farm2);
		waterD.setAmount(12);
		
		Humidityofsoil humC = new Humidityofsoil();
		humC.setId(2);
		humC.setDate(dataC);
		humC.setFarm(farm2);
		humC.setClassification(ClassificationH.Very_Humid);
		
		Humidityofsoil humD = new Humidityofsoil();
		humD.setId(1);
		humD.setDate(dataD);
		humD.setFarm(farm2);
		humD.setClassification(ClassificationH.Balanced);
		
		//assertEquals(0, user1.compareTo(user2, dataA));
		//assertEquals(0, user1.compareTo(user2, dataB));
		assertEquals(0, user1.compareTo(user2, dataC));
		//assertEquals(1, user1.compareTo(user2, dataD));
	}
	
	

}
