package github.jcext.applications;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Unstable API.
 * Timeout/delays abstraction.
 */
@FunctionalInterface
public interface Timer {

	void timeout(Runnable r, long timeout, TimeUnit unit);

	static Timer defaultInstance() {
		return DefaultImpl.timer;
	}


	class DefaultImpl {
		private static ScheduledExecutorService sched = Executors.newScheduledThreadPool(2);
		private static final Timer timer = (r, t, u) -> sched.schedule(r, t, u);
	}
}
