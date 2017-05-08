package demo.thrift.server.impls.server.pool;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.thrift.server.impls.core.ISleepyService;
import demo.thrift.server.impls.server.service.SleepyService;

public class PoolServer implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(PoolServer.class);
	
	private ISleepyService.Processor<SleepyService> processor;

	private TServerTransport serverTransport;

	private int maxWorkerThreads = 0;
	
	private int minWorkerThreads = 0;
	
	public PoolServer(ISleepyService.Processor<SleepyService> processor) {
		this.processor = processor;
	}

	public void run() {
		String name = this.getClass().getSimpleName();
		try {
			serverTransport = new TServerSocket(9090);
		} catch (TTransportException e) {
			log.error("starting the {} server failed, {}", name, e);
			e.printStackTrace();
		}
		TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
		if (maxWorkerThreads > 0) {
			args.maxWorkerThreads(maxWorkerThreads);
		}
		if (minWorkerThreads > 0) {
			args.minWorkerThreads(minWorkerThreads);
		}
		
		Args processor2 = args.processor(processor);
		TServer server = new TThreadPoolServer(processor2);
		log.info("Starting the {} ...", name);
		server.serve();

	}

	public void stop() {
		serverTransport.close();
	}
	
	public void start() {
		PoolServer target = new PoolServer(processor);
		new Thread(target).start();
	}

	public void setMaxWorkerThreads(int maxWorkerThreads) {
		this.maxWorkerThreads = maxWorkerThreads;
	}

	public void setMinWorkerThreads(int minWorkerThreads) {
		this.minWorkerThreads = minWorkerThreads;
	}
	
}
