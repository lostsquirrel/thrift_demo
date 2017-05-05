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
import demo.thrift.server.impls.server.service.impl.SleepyServiceImpl;

public class SimpleServer {

	private static final Logger log = LoggerFactory.getLogger(SimpleServer.class);

	public static void main(String[] args) {
		SleepyService service = new SleepyServiceImpl();
		ISleepyService.Processor<SleepyService> processor = new ISleepyService.Processor<SleepyService>(service);

		Runnable simple = new Runnable() {
			public void run() {
				TServerTransport serverTransport;
				try {
					serverTransport = new TServerSocket(9090);
					TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
					log.info("Starting the simple server...");
					server.serve();
				} catch (TTransportException e) {
					log.error("starting the simple server failed, {}", e);
					e.printStackTrace();
				}
			}
		};

		new Thread(simple).start();
	}
}
