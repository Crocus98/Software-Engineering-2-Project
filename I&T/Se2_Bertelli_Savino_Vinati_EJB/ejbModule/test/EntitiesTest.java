package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import entities.*;
import enums.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		
		ArrayList<Forecast> forecastList = new ArrayList<>();
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
		
		ArrayList<Farm> farmList = new ArrayList<>();
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
	
	//Test for discussion
	
	@Test
	void discussionIdTest() {
		Discussion discussion = new Discussion();
		discussion.setId(0);
		assertEquals(0,discussion.getId());
	}
	
	@Test
	void discussionDatehourTest() {
		Discussion discussion = new Discussion();
		@SuppressWarnings("deprecation")
		Date data = new Date(2022,01,23);
		discussion.setDatehour(data);
		assertEquals(data,discussion.getDatehour());
	}
	
	@Test
	void discussionTitleTest() {
		Discussion discussion = new Discussion();
		discussion.setTitle("Title");
		assertEquals("Title",discussion.getTitle());
	}
	
	@Test
	void discussionPostTest() {
		Discussion discussion = new Discussion();
		Post p1 = new Post();
		Post p2 = new Post();
		ArrayList<Post> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		discussion.setPosts(list);
		assertEquals(list,discussion.getPosts());
	}
	
	@Test
	void discussionUserTest() {
		Discussion discussion = new Discussion();
		User user = new User();
		discussion.setUser(user);
		assertEquals(user,discussion.getUser());
	}
	
	void discussionAddPostTest() {
		Discussion discussion = new Discussion();
		Post p1 = new Post();
		Post p2 = new Post();
		Post p3 = new Post();
		ArrayList<Post> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		discussion.setPosts(list);
		discussion.addPost(p3);
		list.add(p3);
		assertEquals(list,discussion.getPosts());
	}
	
	//Test for Farm
	
	@Test
	void farmAreaTest() {
		Farm farm = new Farm();
		Area area = new Area();
		farm.setArea(area);
		assertEquals(area,farm.getArea());
	}
	
	@Test
	void farmAddressTest() {
		Farm farm = new Farm();
		farm.setAddress("address");
		assertEquals("address",farm.getAddress());
	}
	
	@Test
	void farmDimensionTest() {
		Farm farm = new Farm();
		farm.setDimension(100);
		assertEquals(100,farm.getDimension());
	}
	
	@Test
	void farmDimensionTest2() {
		Farm farm = new Farm();
		farm.setDimension(0);
		assertEquals(0,farm.getDimension());
	}
	
	@Test
	void farmHumidityTest() {
		Farm farm = new Farm();
		Humidityofsoil h1 = new Humidityofsoil();
		Humidityofsoil h2 = new Humidityofsoil(); 
		ArrayList<Humidityofsoil> list = new ArrayList<>();
		list.add(h1);
		list.add(h2);
		farm.setHumidityofsoil(list);
		assertEquals(list,farm.getHumidityofsoil());
	}
	
	@Test
	void farmIDTest() {
		Farm farm = new Farm();
		farm.setId(0);
		assertEquals(0,farm.getId());
	}
	
	@Test
	void farmProductionTest() {
		Farm farm = new Farm();
		Production p1 = new Production();
		Production p2 = new Production();
		ArrayList<Production>list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		farm.setProductions(list);
		assertEquals(list,farm.getProductions());
	}
	
	@Test
	void farmProductionAmountM2Test() throws ParseException {
		Farm farm = new Farm();
		farm.setDimension(100);
		
		Production p1 = new Production();
		p1.setAmount(100);
		p1.setId(1);
		p1.setFarm(farm);
		String sData1="2022/02/01"; 
		Date data1 = new SimpleDateFormat("yyyy/MM/dd").parse(sData1);
		p1.setDate(data1);
		
		Production p2 = new Production();
		p2.setAmount(500);
		p2.setId(2);
		p2.setFarm(farm);
		String sData2="2022/02/09"; 
		Date data2 = new SimpleDateFormat("yyyy/MM/dd").parse(sData2);
		p2.setDate(data2);
		
		String sData3="2022/01/01"; 
		Date data3 = new SimpleDateFormat("yyyy/MM/dd").parse(sData3);
		ArrayList<Production>list = new ArrayList<>();
		list.add(p2);
		list.add(p1);
		farm.setProductions(list);
		assertEquals(6,farm.getProductionAmountM2(data3));
	}
	
	@Test
	void farmWaterComsunptionTest() {
		Farm farm = new Farm();
		Waterconsumption p1 = new Waterconsumption();
		Waterconsumption p2 = new Waterconsumption();
		ArrayList<Waterconsumption>list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		farm.setWaterconsumptions(list);
		assertEquals(list,farm.getWaterconsumptions());
	}
	
	@Test
	void farmWaterConsumptionM2Test() throws ParseException {
		Farm farm = new Farm();
		farm.setDimension(100);
		
		Waterconsumption p1 = new Waterconsumption();
		p1.setAmount(100);
		p1.setId(1);
		p1.setFarm(farm);
		String sData1="2022-02-01"; 
		Date data1 = new SimpleDateFormat("yyyy-MM-dd").parse(sData1);
		p1.setDate(data1);
		
		Waterconsumption p2 = new Waterconsumption();
		p2.setAmount(500);
		p2.setId(2);
		p2.setFarm(farm);
		String sData2="2022-02-09"; 
		Date data2 = new SimpleDateFormat("yyyy-MM-dd").parse(sData2);
		p2.setDate(data2);
		
		String sData3="2022-01-01"; 
		Date data3 = new SimpleDateFormat("yyyy-MM-dd").parse(sData3);
		ArrayList<Waterconsumption>list = new ArrayList<>();
		list.add(p2);
		list.add(p1);
		farm.setWaterconsumptions(list);
		assertEquals(600,farm.getWaterconsumptionM2(data3, false));
	}
	
	@Test
	void farmWaterConsumptionM2NormTest() throws ParseException {
		Farm farm = new Farm();
		farm.setDimension(100);
		
		Waterconsumption p1 = new Waterconsumption();
		p1.setAmount(100);
		p1.setId(1);
		p1.setFarm(farm);
		String sData1="2022-02-01"; 
		Date data1 = new SimpleDateFormat("yyyy-MM-dd").parse(sData1);
		p1.setDate(data1);
		
		Waterconsumption p2 = new Waterconsumption();
		p2.setAmount(500);
		p2.setId(2);
		p2.setFarm(farm);
		String sData2="2022-02-09"; 
		Date data2 = new SimpleDateFormat("yyyy-MM-dd").parse(sData2);
		p2.setDate(data2);
		
		String sData3="2022-01-01"; 
		Date data3 = new SimpleDateFormat("yyyy-MM-dd").parse(sData3);
		ArrayList<Waterconsumption>list = new ArrayList<>();
		list.add(p2);
		list.add(p1);
		farm.setWaterconsumptions(list);
		assertEquals(6,farm.getWaterconsumptionM2(data3, true));
	}
	
	@Test
	void farmWaterConsumptionM2NormTest2() throws ParseException {
		Farm farm = new Farm();
		farm.setDimension(100);
		
		Waterconsumption p1 = new Waterconsumption();
		p1.setAmount(100);
		p1.setId(1);
		p1.setFarm(farm);
		String sData1="2022-02-01"; 
		Date data1 = new SimpleDateFormat("yyyy-MM-dd").parse(sData1);
		p1.setDate(data1);
		
		Waterconsumption p2 = new Waterconsumption();
		p2.setAmount(500);
		p2.setId(2);
		p2.setFarm(farm);
		String sData2="2022-02-01"; 
		Date data2 = new SimpleDateFormat("yyyy-MM-dd").parse(sData2);
		p2.setDate(data2);
		
		String sData3="2022-01-01"; 
		Date data3 = new SimpleDateFormat("yyyy-MM-dd").parse(sData3);
		ArrayList<Waterconsumption>list = new ArrayList<>();
		list.add(p2);
		list.add(p1);
		farm.setWaterconsumptions(list);
		assertEquals(6,farm.getWaterconsumptionM2(data3, true));
	}
	
	
	//Test for Production
	
	@Test
	void productionAmountTest() {
		Production production = new Production();
		production.setAmount(9);
		assertEquals(9,production.getAmount());
		
	}
	
	@Test
	void productionCommentTest() {
		Production production = new Production();
		production.setComment("aa");
		assertEquals("aa", production.getComment());
		
	}
	
	@Test
	void productionFarmTest() {
		Production production = new Production();
		Farm farm = new Farm();
		production.setFarm(farm);
		assertEquals(farm,production.getFarm());
		
	}
	
	@Test
	void productionIdTest() {
		Production production = new Production();
		production.setId(2);
		assertEquals(2,production.getId());
		
	}
	
	@Test
	void productionTypeOfProductTest() {
		Production production = new Production();
		production.setTypeofproduct(TypeOfProduct.Rice);
		assertEquals(0 ,production.getId());
		
	}
	
	@Test
	void productionDateTest() {
		Production production = new Production();
		production.setAmount(100);
		@SuppressWarnings("deprecation")
		Date data1 = new Date(2022,01,12);
		production.setDate(data1);
		assertEquals(data1,production.getDate());
		
	}
	
	//Test for Post
	
	@Test
	void postIdTest() {
		Post post = new Post();
		post.setId(2);
		assertEquals(2 ,post.getId());
		
	}
	
	@Test
	void postCommentTest() {
		Post post = new Post();
		post.setComment("bb");
		assertEquals("bb" ,post.getComment());
		
	}
	
	@Test
	void postDiscussionTest() {
		Post post = new Post();
		Discussion discussion = new Discussion();
		post.setDiscussion(discussion);
		assertEquals(discussion, post.getDiscussion());
		
	}
	
	@Test
	void postUserTest() {
		Post post = new Post();
		User user = new User();
		post.setUser(user);
		assertEquals(user, post.getUser());
		
	}
	
	@Test
	void postDatehourTest() {
		Post post = new Post();
		@SuppressWarnings("deprecation")
		Date data1 = new Date(2022,01,12);
		post.setDatehour(data1);
		assertEquals(data1, post.getDatehour());
		
	}
	
	//Test for Forecast
	
	@Test
	void forecastAreaTest() {
		Forecast forecast = new Forecast();
		Area area = new Area();
		forecast.setArea(area);
		assertEquals(area, forecast.getArea());
		
	}
	
	@Test
	void forecastIdTest() {
		Forecast forecast = new Forecast();
		forecast.setId(2);
		assertEquals(2, forecast.getId());
	}
	
	@Test
	void forecastValueTest() {
		Forecast forecast = new Forecast();
		forecast.setValue(0);
		assertEquals(0, forecast.getValue());
	}
	
	@Test
	void forecastCreationDateTest() {
		Forecast forecast = new Forecast();
		@SuppressWarnings("deprecation")
		Date data1 = new Date(2022,01,12);
		forecast.setCreationdate(data1);
		assertEquals(data1, forecast.getCreationdate());
	}
	
	@Test
	void forecastDateTest() {
		Forecast forecast = new Forecast();
		@SuppressWarnings("deprecation")
		Date data1 = new Date(2022,01,12);
		forecast.setDate(data1);
		assertEquals(data1, forecast.getDate());
	}
	
	@Test
	void forecastClassificationTest() {
		Forecast forecast = new Forecast();
		forecast.setClassification(ClassificationF.Heavy_Rainfall);
		assertEquals(ClassificationF.Heavy_Rainfall, forecast.getClassification());
	}
	
	//Test for WaterConsumption
	
	@Test
	void WaterconsumptionIdTest() {
		Waterconsumption waterconsumption = new Waterconsumption();
		waterconsumption.setId(2);
		assertEquals(2 ,waterconsumption.getId());
		
	}
	
	@Test
	void WaterconsumptionAmountTest() {
		Waterconsumption waterconsumption = new Waterconsumption();
		waterconsumption.setAmount(9);
		assertEquals(9,waterconsumption.getAmount());
		
	}
	
	@Test
	void WaterconsumptionDateTest() {
		Waterconsumption waterconsumption = new Waterconsumption();
		@SuppressWarnings("deprecation")
		Date data1 = new Date(2022,01,12);
		waterconsumption.setDate(data1);
		assertEquals(data1, waterconsumption.getDate());
	}
	
	@Test
	void WaterconsumptionFarmTest() {
		Waterconsumption waterconsumption = new Waterconsumption();
		Farm farm = new Farm();
		waterconsumption.setFarm(farm);
		assertEquals(farm,waterconsumption.getFarm());
		
	}
	
	
	
	
	

}
