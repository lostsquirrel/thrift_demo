package demo.thrift.server.impls.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.thrift.server.impls.core.ISleepyService;
import demo.thrift.server.impls.server.service.SleepyService;
import demo.thrift.server.impls.server.service.impl.SleepyServiceImpl;

public class Server {

	private static final Logger log = LoggerFactory.getLogger(Server.class);
	
	
	public static void main(String[] args) {
		SleepyService service = new SleepyServiceImpl();
		ISleepyService.Processor<SleepyService> processor = new ISleepyService.Processor<SleepyService>(service);

	}
}
