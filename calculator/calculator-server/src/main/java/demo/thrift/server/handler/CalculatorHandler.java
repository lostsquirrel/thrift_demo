package demo.thrift.server.handler;

import demo.thrift.hello.Calculator;
import demo.thrift.hello.InvalidOperation;
import demo.thrift.hello.Work;

public class CalculatorHandler implements Calculator.Iface {


	public void ping() {
		System.out.println("ping()");
	}

	public int calculate(int logid, Work work) throws InvalidOperation {
		System.out.println("calculate(" + logid + ", {" + work.op + "," + work.num1 + "," + work.num2 + "})");
		int val = 0;
		switch (work.op) {
		case ADD:
			val = work.num1 + work.num2;
			break;
		case SUBTRACT:
			val = work.num1 - work.num2;
			break;
		case MULTIPLY:
			val = work.num1 * work.num2;
			break;
		case DIVIDE:
			if (work.num2 == 0) {
				InvalidOperation io = new InvalidOperation();
				io.whatOp = work.op.getValue();
				io.why = "Cannot divide by 0";
				throw io;
			}
			val = work.num1 / work.num2;
			break;
		default:
			InvalidOperation io = new InvalidOperation();
			io.whatOp = work.op.getValue();
			io.why = "Unknown operation";
			throw io;
		}

		return val;
	}

}
