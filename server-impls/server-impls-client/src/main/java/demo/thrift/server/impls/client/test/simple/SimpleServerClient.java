package demo.thrift.server.impls.client.test.simple;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.thrift.server.impls.client.service.SleepyService;
import demo.thrift.server.impls.client.service.impl.SleepyServiceImpl;

public class SimpleServerClient implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(SimpleServerClient.class);
	
	private int clientNo;
	
	private int timeout;
	
	private TTransport transport;
	
	private SleepyService sleepyService;
	
	public SimpleServerClient(int clientNo, int timeout, String host, int port) {
		this.clientNo = clientNo;
		this.timeout = timeout;
		transport = new TSocket(host, port);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
		TProtocol protocol = new TBinaryProtocol(transport);
		
		sleepyService = new SleepyServiceImpl(protocol);
	}
	
	@Override
	public void run() {
		try {
			long s = System.currentTimeMillis();
			sleepyService.sleep(clientNo, timeout);
			long e = System.currentTimeMillis() - s;
			log.debug(String.format("client[%3d] took: %4d ms for timeout:%4d ms", this.clientNo, e ,timeout));
		} catch (TException e) {
			e.printStackTrace();
		}
		transport.close();
	}

}
