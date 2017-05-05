package demo.thrift.server.impls.client.test.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestSimpleServer {

	public static void main(String[] args) {
		String host = args.length > 0 ? args[0] : "localhost";
		int port = args.length > 1 ? Integer.parseInt(args[1]) : 9090;
		int amount = args.length > 2 ? Integer.parseInt(args[2]) : 10;
		int timeout = args.length > 3 ? Integer.parseInt(args[3]) : 100;
		

		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(amount);
		
		for (int i = 0; i < amount; i++) {
			fixedThreadPool.execute(new SimpleServerClient(i, timeout, host, port));
		}
		
		try {
			TimeUnit.MILLISECONDS.sleep(timeout * amount + 3 * 1000);
			fixedThreadPool.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
