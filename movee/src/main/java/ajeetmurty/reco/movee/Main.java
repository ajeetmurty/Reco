package ajeetmurty.reco.movee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		doSomething();
	}

	private void doSomething() {
		logp.info("start");
		try {
			logp.info("doing info");
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
		logp.info("stop");
	}
}
