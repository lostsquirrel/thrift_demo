package demo.thrift.server.impls.server.simple;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.thrift.server.impls.core.ISleepyService;
import demo.thrift.server.impls.server.service.SleepyService;

public class SimpleServer implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(SimpleServer.class);

	private ISleepyService.Processor<SleepyService> processor;

	private TServerTransport serverTransport;

	public SimpleServer(ISleepyService.Processor<SleepyService> processor) {
		this.processor = processor;
	}

	public void run() {
		String name = this.getClass().getSimpleName();
		try {
			serverTransport = new TServerSocket(9090);
		} catch (TTransportException e) {
			log.error("starting the {} failed, {}", name, e);
			e.printStackTrace();
		}
		TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
		log.info("Starting the {}...", name);
		server.serve();

	}

	public void stop() {
		serverTransport.close();
	}
	
	public void start() {
		SimpleServer target = new SimpleServer(processor);
		new Thread(target).start();
	}

}
