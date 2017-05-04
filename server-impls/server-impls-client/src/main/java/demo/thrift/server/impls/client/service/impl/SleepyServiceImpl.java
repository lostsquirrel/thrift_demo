package demo.thrift.server.impls.client.service.impl;

import org.apache.thrift.protocol.TProtocol;

import demo.thrift.server.impls.client.service.SleepyService;
import demo.thrift.server.impls.core.ISleepyService;

public class SleepyServiceImpl extends ISleepyService.Client implements SleepyService {

	public SleepyServiceImpl(TProtocol prot) {
		super(prot);
	}

}
