package demo.thrift.server.impls.server.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.thrift.server.impls.server.service.SleepyService;

public class SleepyServiceImpl implements SleepyService {

	private static final Logger log = LoggerFactory.getLogger(SleepyServiceImpl.class);
	
	@Override
	public void sleep(int clientNo, int timeout) throws TException {
		try {
			log.debug("going to process sleeping [{}]{}ms...", clientNo, timeout);
			TimeUnit.MILLISECONDS.sleep(timeout);
			log.debug("process sleep [{}]{}ms done!", clientNo, timeout);
		} catch (InterruptedException e) {
			log.error("process sleep failed [{}]{}", clientNo, e);
			e.printStackTrace();
		}
	}

}
