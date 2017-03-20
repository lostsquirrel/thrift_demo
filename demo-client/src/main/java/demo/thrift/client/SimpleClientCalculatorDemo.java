package demo.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import demo.thrift.hello.Calculator;
import demo.thrift.hello.InvalidOperation;
import demo.thrift.hello.Operation;
import demo.thrift.hello.Work;

public class SimpleClientCalculatorDemo {

	public static void main(String[] args) throws TTransportException {
		TTransport transport = new TSocket("localhost", 9090);
		transport.open();
		
		TProtocol protocol = new TBinaryProtocol(transport);
		Calculator.Client client = new Calculator.Client(protocol);
		
		try {
			Work work = new Work();

			work.op = Operation.DIVIDE;
			work.num1 = 1;
			work.num2 = 0;
			try {
				int quotient = client.calculate(1, work);
				System.out.println("Whoa we can divide by 0"+quotient);
			} catch (InvalidOperation io) {
				System.out.println("Invalid operation: " + io.why);
			}

			work.op = Operation.SUBTRACT;
			work.num1 = 15;
			work.num2 = 10;
			try {
				int diff = client.calculate(1, work);
				System.out.println("15-10=" + diff);
			} catch (InvalidOperation io) {
				System.out.println("Invalid operation: " + io.why);
			}
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			
			transport.close();
		}
		
	}
}
