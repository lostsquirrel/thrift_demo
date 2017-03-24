package demo.thrift.hello.service.impl;

import org.apache.thrift.TException;

import demo.thrift.hello.service.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String echo(int serial, String msg) throws TException {
		return String.format("echo:%8d:%s", serial, msg);
	}

}
