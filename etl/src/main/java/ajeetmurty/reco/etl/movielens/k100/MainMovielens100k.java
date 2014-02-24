package ajeetmurty.reco.etl.movielens.k100;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMovielens100k {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new MainMovielens100k();
	}

	public MainMovielens100k() {
		mergeMovielensData();
	}

	private void mergeMovielensData() {
		logp.info("start");
		try {
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
		logp.info("stop");
	}
}
