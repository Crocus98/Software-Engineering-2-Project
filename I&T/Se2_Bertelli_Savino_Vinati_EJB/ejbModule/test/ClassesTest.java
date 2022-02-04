package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import classes.*;

class ClassesTest {

	// TEST for Utility

	@Test
	void UtilityRoundTestStd() {
		// Double a = 0.3456789;
		Double b = 3.7384735384;
		assertEquals(3.74, Utility.round(b));
	}

	@Test
	void UtilityRoundTestZero() {
		Double a = 0.000000;
		assertEquals(0.00, Utility.round(a));
	}

	@Test
	void UtilityCalculateEntropyTest1Std() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(3);
		list.add(2);
		list.add(1);
		list.add(5);
		list.add(6);
		assertEquals(Utility.round(2.25), Utility.round(Utility.calculateEntropy(list)));
	}

	@Test
	void UtilityCalculateEntropyTest2AllZero() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		assertEquals(0, Utility.round(Utility.calculateEntropy(list)));
	}

	@Test
	void UtilityCalculateEntropyTest3Paired() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(3);
		list.add(3);
		list.add(3);
		assertEquals(Utility.round(1.0), Utility.round(Utility.calculateEntropy(list)));
	}

	@Test
	void UtilityCalculateEntropyTest4MinEntropy() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		assertEquals(Utility.round(0.0), Utility.round(Utility.calculateEntropy(list)));
	}

	@Test
	void UtilityCompareTest1LessThan() {
		Double a = 3.4;
		Double b = 9.5;
		assertEquals(-1, Utility.compare(a, b)); // b<a=-1
	}

	@Test
	void UtilityCompareTest2MoreThan() {
		Double a = -3.4;
		Double b = 9.5;
		assertEquals(1, Utility.compare(b, a)); // b>a=1
	}

	@Test
	void UtilityCompareTest3Equal() {
		Double a = 3.4;
		Double b = 3.4;
		assertEquals(0, Utility.compare(b, a)); // b==a=0
	}

	@Test
	void UtilityCompareTest4Zero() {
		Double a = 0.0;
		Double b = 0.0;
		assertEquals(0, Utility.compare(b, a)); // b==a=0
	}

}
