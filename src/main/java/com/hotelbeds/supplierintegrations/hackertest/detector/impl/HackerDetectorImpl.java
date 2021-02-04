package com.hotelbeds.supplierintegrations.hackertest.detector.impl;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;

public class HackerDetectorImpl implements HackerDetector {

	@Override
	public String parseLine(String line) {
		String[] splitedLine = line.split(",");
		if (splitedLine[2].equals("SIGNIN_FAILURE")) {
			return splitedLine[0];
		}
		return null;
	}
}
