package gg.bayes.challenge.match.engine.impl;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gg.bayes.challenge.match.engine.MatchProcesser;
import gg.bayes.challenge.match.engine.MatchType;
import gg.bayes.challenge.match.engine.ProcesserEngine;
import gg.bayes.challenge.match.engine.ProcesserFactory;


@Component
public class ProcesserEngineImpl implements ProcesserEngine{
	
	@Autowired
    private ProcesserFactory factory;
	
	public Map<MatchType, List<String>> process(String payload, Long matchId) throws Exception {
		Map<MatchType, List<String>> validInvalidQuery = null;
		
		Exception eexception = null;
		try (Stream<String> stream = new BufferedReader(new StringReader(payload))
		        .lines()) {//Files.lines(Paths.get(fileName))

			//1. filter line 3
			//2. convert all content to upper case
			//3. convert it into a List
			validInvalidQuery = stream
					.map(line -> createInsertQuery(line, matchId))
					.collect(Collectors.groupingBy(_log -> {
						if(_log.toString().trim().equals("")) {
							return MatchType.NOT_INTERESTED;
						} else if(_log.toString().indexOf("invalid event format") > -1) {
							return MatchType.INVALID;
						} else {
							return MatchType.VALID;
						}
					}));
			
		} catch (Exception e) {
			eexception = e;
		}
		
		if(eexception!=null) {
			throw eexception;
		}
		
		return validInvalidQuery;
		
	}
	
	private String createInsertQuery(String logEvent, Long matchId) {
    	String query = "";
    	try {
    		MatchProcesser matchProcesser = factory.getProcessor(logEvent);
    		if(matchProcesser!=null)
    			query = matchProcesser.processLine(logEvent, matchId);
    	} catch(Exception e) {
    		query = "invalid event format >> "+ logEvent;
    	}
    	return query;
    }

}
