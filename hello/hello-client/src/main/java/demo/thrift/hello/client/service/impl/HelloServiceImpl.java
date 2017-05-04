package demo.thrift.hello.client.service.impl;

import org.apache.thrift.protocol.TProtocol;

import demo.thrift.hello.client.service.HelloService;
import demo.thrift.hello.core.IHelloService;

public class HelloServiceImpl extends IHelloService.Client implements HelloService {

	public HelloServiceImpl(TProtocol prot) {
		super(prot);
	}
}
