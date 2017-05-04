package demo.thrift.hello.client;

import java.util.Scanner;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.thrift.hello.client.service.HelloService;
import demo.thrift.hello.client.service.impl.HelloServiceImpl;

public class Client {
	
	private static final Logger log = LoggerFactory.getLogger(Client.class);
	
	public static void main(String[] args) throws TTransportException {
		
		TTransport transport = new TSocket("localhost", 9090);
		transport.open();
		
		TProtocol protocol = new TBinaryProtocol(transport);
		
		HelloService helloService = new HelloServiceImpl(protocol);
		String msg = null;
		int serial = 0;
		Scanner scan = new Scanner(System.in);
		do {
			if (scan.hasNext()) {
				msg = scan.next();
				try {
					String resp = helloService.echo(++serial, msg);
					log.debug(resp);
				} catch (TException e) {
					log.error("{}", e);
				}
			}
		} while (!"exit".equals(msg));
		scan.close();
		transport.close();
	}
}
