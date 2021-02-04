package com.hotelbeds.supplierintegrations.hackertest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import com.hotelbeds.supplierintegrations.hackertest.detector.impl.HackerDetectorImpl;
import com.hotelbeds.supplierintegrations.hackertest.service.LogFailureDetector;

public class HackerDetectionTest {

	private static String TEST_FAILURE_IP1 = "80.238.9.179";
	private static String TEST_FAILURE_IP2 = "81.238.9.179";
	private static String TEST_FILE_NAME1 = "accessLog1.txt";
	private static String TEST_FILE_NAME2 = "accessLog2.txt";
	private static String TEST_FILE_NAME3 = "accessLog3.txt";

	@InjectMocks
	HackerDetector hackerDetector = new HackerDetectorImpl();
	
	@InjectMocks
	LogFailureDetector logFailureDetector = new LogFailureDetector(hackerDetector);

	@SuppressWarnings("unchecked")
	@Test
	public void testHackerDetectionIP1() {
		String hackerIP = logFailureDetector.hackerIPDetection(TEST_FILE_NAME1);
		assertThat(hackerIP).isEqualTo(TEST_FAILURE_IP1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testHackerDetectionIP2() {
		String hackerIP = logFailureDetector.hackerIPDetection(TEST_FILE_NAME2);
		assertThat(hackerIP).isEqualTo(TEST_FAILURE_IP2);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testNoHackerIPDetection() {
		String hackerIP = logFailureDetector.hackerIPDetection(TEST_FILE_NAME3);
		assertThat(hackerIP).isNull();
	}
}
