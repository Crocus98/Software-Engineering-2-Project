package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.persistence.internal.oxm.schema.model.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.*;
import classes.*;

class ClassesTest {

	//Test for Utility
	
	@Test
	void UtilityRoundTestStd() {
		Utility utility = new Utility();
		//Double a = 0.3456789;
		Double b = 3.7384735384;
		assertEquals(3.74, utility.round(b));
	}
	
	@Test
	void UtilityRoundTestZero() {
		Utility utility = new Utility();
		Double a = 0.000000;
		assertEquals(0.00, utility.round(a));
	}
	
	@Test
	void UtilityCalculateEntropyTest1Std() {
		Utility utility = new Utility();
		ArrayList<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(3);
		list.add(2);
		list.add(1);
		list.add(5);
		list.add(6);
		assertEquals(utility.round(2.25), utility.round(utility.calculateEntropy(list)));
	}
	
	@Test
	void UtilityCalculateEntropyTest2AllZero() {
		Utility utility = new Utility();
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		assertEquals(0, utility.round(utility.calculateEntropy(list)));
	}
	
	@Test
	void UtilityCalculateEntropyTest3Paired() {
		Utility utility = new Utility();
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(3);
		list.add(3);
		list.add(3);
		assertEquals(utility.round(1.0), utility.round(utility.calculateEntropy(list)));
	}
	
	@Test
	void UtilityCalculateEntropyTest4MinEntropy() {
		Utility utility = new Utility();
		ArrayList<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		assertEquals(utility.round(0.0), utility.round(utility.calculateEntropy(list)));
	}
	
	@Test
	void UtilityCompareTest1LessThan() {
		Utility utility = new Utility();
		Double a = 3.4;
		Double b = 9.5;
		assertEquals(-1, utility.compare(a, b)); //b<a=-1
	}
	
	@Test
	void UtilityCompareTest2MoreThan() {
		Utility utility = new Utility();
		Double a = -3.4;
		Double b = 9.5;
		assertEquals(1, utility.compare(b, a)); //b>a=1
	}
	
	@Test
	void UtilityCompareTest3Equal() {
		Utility utility = new Utility();
		Double a = 3.4;
		Double b = 3.4;
		assertEquals(0, utility.compare(b, a)); //b==a=0
	}
	
	@Test
	void UtilityCompareTest4Zero() {
		Utility utility = new Utility();
		Double a = 0.0;
		Double b = 0.0;
		assertEquals(0, utility.compare(b, a)); //b==a=0
	}
	


}
