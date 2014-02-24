package ajeetmurty.reco.etl.movielens.m10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMovielens10m {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new MainMovielens10m();
	}

	public MainMovielens10m() {
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
