package gg.bayes.challenge.match.engine.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import gg.bayes.challenge.match.engine.MatchProcesser;
import gg.bayes.challenge.match.engine.ProcesserFactory;


@Component
public class ProcesserFactoryImpl implements ProcesserFactory{
	
	@Autowired
    private ApplicationContext сontext;

	@Override
	public MatchProcesser getProcessor(String logEvent) {
		if(logEvent.indexOf("buys item") >= 0) {
			return сontext.getBean(BuyMatchProcesser.class);
    	} else if(logEvent.indexOf("hits") >= 0 && logEvent.indexOf("damage") >= 0) {
    		return сontext.getBean(DamageMatchProcesser.class);
    	} else if(logEvent.indexOf("casts ability") >= 0) {
    		return сontext.getBean(SpellMatchProcesser.class);
    	} else if(logEvent.indexOf("is killed by") >= 0) {
    		return сontext.getBean(KilledMatchProcesser.class);
    	} else {
    		return null;
    	}
	}

}
