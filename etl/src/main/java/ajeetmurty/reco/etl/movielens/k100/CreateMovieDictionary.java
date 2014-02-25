package ajeetmurty.reco.etl.movielens.k100;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum CreateMovieDictionary {
	INSTANCE;
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public void createDictionary(File inputMoviesFile, File inputRatingsFile, File inputTagsFile) {
		logp.info("creating dictionary");
	}
}
