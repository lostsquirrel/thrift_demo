package demo.thrift.server.impls.client.test.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestSimpleServer {

	public static void main(String[] args) {
		int amount = 10;
		int timeout = 100;
		if (args.length > 0) {
			amount = Integer.parseInt(args[0]);
		}
		if (args.length > 1) {
			amount = Integer.parseInt(args[1]);
		}
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(amount);
		
		for (int i = 0; i < amount; i++) {
			fixedThreadPool.execute(new SimpleServerClient(i, timeout));
		}
		
		try {
			TimeUnit.MILLISECONDS.sleep(timeout * amount + 3 * 1000);
			fixedThreadPool.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
