package demo.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import demo.thrift.hello.Calculator;

public class SimpleClientPingDemo {

	public static void main(String[] args) throws TTransportException {
		TTransport transport = new TSocket("localhost", 9090);
		transport.open();
		
		TProtocol protocol = new TBinaryProtocol(transport);
		Calculator.Client client = new Calculator.Client(protocol);
		
		try {
			client.ping();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			
			transport.close();
		}
		
	}
}
