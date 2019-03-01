import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

//Code taken from provided material http://marjavamitjava.com/calculate-cpu-usage-java-thread-not-just-whole-process/
public class MeasurementHelper {
	private int sampleTime = 10100;
	private ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
	private RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
	private OperatingSystemMXBean osMxBean = ManagementFactory.getOperatingSystemMXBean();
	private Map<Long, Long> threadInitialCPU = new HashMap<Long, Long>();
	private Map<Long, Float> threadCPUUsage = new HashMap<Long, Float>();
	private long initialUptime = runtimeMxBean.getUptime();

	public static void main(String[] args) {

		Table table = new Table();
		Agent agent = new Agent("Agent1", 20, 2, table);
		Chef c1 = new Chef(Ingredient.BREAD, table);
		Chef c2 = new Chef(Ingredient.JAM, table);
		Chef c3 = new Chef(Ingredient.PEANUTBUTTER, table);

		System.out.println("Program Started");
		Thread[] threadPool = new Thread[4];
		threadPool[0] = new Thread(agent);
		threadPool[1] = new Thread(c1);
		threadPool[2] = new Thread(c2);
		threadPool[3] = new Thread(c3);

		for (Thread t : threadPool) {
			t.start();
		}
		new MeasurementHelper().measure();
		try {
			Thread.sleep(1000);// Made to make the output more readable
		} catch (InterruptedException e) {
			System.out.println("Agent sleep error " + e.getLocalizedMessage());
		}
		while (table.isEmpty()) {
			long endTime = System.nanoTime();
			long agentTimeElapsed = endTime - agent.startTime();
			System.out.println(20 + " sandwiches made Agent is done.");
			System.out.println("Agent took " + agentTimeElapsed / 1000000 + " milliseconds to complete.");
			System.out.println("Chef " + c1.toString() + " took " + (endTime - c1.startTime()) / 1000000
					+ " milliseconds to complete. With an average time of "
					+ ((endTime - c1.startTime()) / 1000000) / 20 + " milliseconds to make a sandwich");
			System.out.println("Chef " + c2.toString() + " took " + (endTime - c2.startTime()) / 1000000
					+ " milliseconds to complete. With an average time of "
					+ ((endTime - c2.startTime()) / 1000000) / 20 + " milliseconds to make a sandwich");
			System.out.println("Chef " + c3.toString() + " took " + (endTime - c3.startTime()) / 1000000
					+ " milliseconds to complete. With an average time of "
					+ ((endTime - c3.startTime()) / 1000000) / 20 + " milliseconds to make a sandwich");
			System.exit(0);
		}
	}

	private void measure() {

		ThreadInfo[] threadInfos = threadMxBean.dumpAllThreads(false, false);
		for (ThreadInfo info : threadInfos) {
			threadInitialCPU.put(info.getThreadId(), threadMxBean.getThreadCpuTime(info.getThreadId()));
		}

		try {
			Thread.sleep(sampleTime);
		} catch (InterruptedException e) {
		}

		long upTime = runtimeMxBean.getUptime();

		Map<Long, Long> threadCurrentCPU = new HashMap<Long, Long>();
		threadInfos = threadMxBean.dumpAllThreads(false, false);
		for (ThreadInfo info : threadInfos) {
			threadCurrentCPU.put(info.getThreadId(), threadMxBean.getThreadCpuTime(info.getThreadId()));
		}

		// CPU over all processes
		int nrCPUs = osMxBean.getAvailableProcessors();
		// total CPU: CPU % can be more than 100% (devided over multiple cpus)
		// long nrCPUs = 1;
		// elapsedTime is in ms.
		long elapsedTime = (upTime - initialUptime);
		for (ThreadInfo info : threadInfos) {
			// elapsedCpu is in ns
			Long initialCPU = threadInitialCPU.get(info.getThreadId());
			if (initialCPU != null) {
				long elapsedCpu = threadCurrentCPU.get(info.getThreadId()) - initialCPU;
				float cpuUsage = elapsedCpu * 100 / (elapsedTime * 1000000F * nrCPUs);
				threadCPUUsage.put(info.getThreadId(), cpuUsage);
			}
		}

		// threadCPUUsage contains cpu % per thread
		System.out.println(threadCPUUsage);
		// You can use osMxBean.getThreadInfo(theadId) to get information on
		// every thread reported in threadCPUUsage and analyze the most CPU
		// intentive threads

	}
}