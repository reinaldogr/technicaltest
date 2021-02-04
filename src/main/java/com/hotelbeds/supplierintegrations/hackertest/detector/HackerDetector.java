package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.springframework.stereotype.Service;

@Service
public interface HackerDetector {
	String parseLine(String line);
}
