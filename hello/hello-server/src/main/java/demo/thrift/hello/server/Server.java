package demo.thrift.hello.server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.thrift.hello.core.IHelloService;
import demo.thrift.hello.server.service.HelloService;
import demo.thrift.hello.server.service.impl.HelloServiceImpl;

public class Server {
	
	private static final Logger log = LoggerFactory.getLogger(Server.class);
	
	public static void main(String[] args) {
		HelloService helloServcie = new HelloServiceImpl();
		IHelloService.Processor<HelloService> processor = new IHelloService.Processor<HelloService>(helloServcie);
		
		Runnable simple = new Runnable() {
			public void run() {
				TServerTransport serverTransport;
				try {
					serverTransport = new TServerSocket(9090);
					TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
					log.info("Starting the simple server...");
					server.serve();
				} catch (TTransportException e) {
					e.printStackTrace();
				}
			}
		};

		new Thread(simple).start();
	}
}
