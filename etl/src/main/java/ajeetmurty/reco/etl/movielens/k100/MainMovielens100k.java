package ajeetmurty.reco.etl.movielens.k100;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMovielens100k {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());
	private final String inputMoviesFilePath = "input/10M/movies.dat";
	private final String inputRatingsFilePath = "input/10M/ratings.dat";
	private final String inputTagsFilePath = "input/10M/tags.dat";

	public static void main(String[] args) {
		new MainMovielens100k();
	}

	public MainMovielens100k() {
		mergeMovielensData();
	}

	private void mergeMovielensData() {
		logp.info("start");
		try {
			File inputMoviesFile = createFileObj(inputMoviesFilePath);
			logp.info("movies file: " + inputMoviesFile.getAbsolutePath());
			File inputRatingsFile = createFileObj(inputRatingsFilePath);
			logp.info("ratings file: " + inputRatingsFile.getAbsolutePath());
			File inputTagsFile = createFileObj(inputTagsFilePath);
			logp.info("tags files: " + inputTagsFile.getAbsolutePath());

			CreateMovieDictionary cmd = CreateMovieDictionary.INSTANCE;
			cmd.createDictionary(inputMoviesFile, inputRatingsFile, inputTagsFile);
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
		logp.info("stop");
	}

	private File createFileObj(String inputFilePath) throws Exception {
		File inputFile = new File(inputFilePath);
		if (inputFile.isFile() && inputFile.canRead()) {
			return inputFile;
		} else {
			throw new Exception("input file not readable: " + inputFilePath);
		}
	}
}
