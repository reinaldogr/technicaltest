package com.hotelbeds.supplierintegrations.hackertest.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import com.hotelbeds.supplierintegrations.hackertest.domain.LogInfo;
import com.hotelbeds.supplierintegrations.hackertest.utils.Util;

@Service
public class LogFailureDetector {

	Map<String, LogInfo> logInfoMap = new HashMap<>();
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private HackerDetector hackerDetector;

	@Autowired
	public LogFailureDetector(HackerDetector hackerDetector) {
		this.hackerDetector = hackerDetector;
	}

	public String hackerIPDetection(String fileName) {

		try {
			// Load the log file
			File file = ResourceUtils.getFile("classpath:" + fileName);
			FileInputStream fstream = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				final String ipFailure = hackerDetector.parseLine(currentLine);
				if (ipFailure != null) {
					final String[] splitedLine = currentLine.split(",");
					LogInfo logInfo = logInfoMap.get(ipFailure);
					if (logInfo == null) {
						logInfo = new LogInfo(Long.valueOf(splitedLine[1]), 1);
						logInfoMap.put(ipFailure, logInfo);
					} else {
						// Compare timestamps
						final Timestamp firstFailureTime = Timestamp.from(Instant.ofEpochSecond(logInfo.getTime()));
						final Timestamp actualLogTime = Timestamp
								.from(Instant.ofEpochSecond(Long.valueOf(splitedLine[1])));
						final Long timeDifference = Util.timeCalculation(firstFailureTime, actualLogTime);
						Integer failuresCount = logInfo.getFailuresCount();

						if (timeDifference <= 5) {
							if (failuresCount == 5) {
								return ipFailure;
							} else {
								logInfoMap.get(ipFailure).setFailuresCount(++failuresCount);
							}
						} else {
							logInfoMap.remove(ipFailure);
						}
					}
				} else {
					logInfoMap.remove(ipFailure);
				}
			}
		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
		return null;
	}
}
