package demo.thrift.server.impls.server;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.thrift.server.impls.core.ISleepyService;
import demo.thrift.server.impls.server.pool.PoolServer;
import demo.thrift.server.impls.server.service.SleepyService;
import demo.thrift.server.impls.server.service.impl.SleepyServiceImpl;
import demo.thrift.server.impls.server.simple.SimpleServer;

public class Server {
	
	private static final Logger log = LoggerFactory.getLogger(Server.class);
	
	public static void main(String[] args) {
		List<String> list = Arrays.asList(args);
		SleepyService service = new SleepyServiceImpl();
		ISleepyService.Processor<SleepyService> processor = new ISleepyService.Processor<SleepyService>(service);
		log.debug("get args: {}", list);
		if (list.contains("simple") || list.size() == 0) {
			new SimpleServer(processor).start();;
		} else if (list.contains("pool")) {
			new PoolServer(processor).start();
		}
	}
}
