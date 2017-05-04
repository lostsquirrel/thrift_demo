package demo.thrift.server.impls.client.service.impl;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.thrift.server.impls.client.service.SleepyService;
import demo.thrift.server.impls.core.ISleepyService;

public class SleepyServiceAsyncImpl extends ISleepyService.AsyncClient implements SleepyService {

	
	private static final Logger log = LoggerFactory.getLogger(SleepyServiceAsyncImpl.class);
	
	private AsyncMethodCallback<Void> resultHandler;

	public SleepyServiceAsyncImpl(TProtocolFactory protocolFactory, TAsyncClientManager clientManager,
			TNonblockingTransport transport) {
		super(protocolFactory, clientManager, transport);
		resultHandler = new AsyncMethodCallback<Void>() {

			@Override
			public void onComplete(Void response) {
				log.info("sleepy done");
			}

			@Override
			public void onError(Exception exception) {
				log.info("sleepy error");
			}
			
		};
	}

	@Override
	public void sleep(int clientNo, int timeout) throws TException {
		super.sleep(clientNo, timeout, resultHandler);
	}

}
