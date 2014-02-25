package ajeetmurty.reco.etl.movielens.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenreDetector {
    private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());
    private final String inputMoviesFilePath = "input/10M/movies.dat";
    private final String regexMovieLine = "^(\\d+)::(.+)::(.+)$";
    private final int genrePosition = 3;

    public static void main(String[] args) {
        new GenreDetector();
    }

    private GenreDetector() {
        detectGenres();
    }

    private void detectGenres() {
        logp.info("start");
        try {
            logp.info("detecting genres");
            parseGenres();
        } catch (Exception e) {
            logp.error(e.getMessage(), e);
        }
        logp.info("stop");
    }

    private void parseGenres() throws Exception {
        File inputMoviesFile = createFileObj(inputMoviesFilePath);
        logp.info("movies file: " + inputMoviesFile.getAbsolutePath());
        BufferedReader br = new BufferedReader(new FileReader(inputMoviesFile));

        Pattern pattern = Pattern.compile(regexMovieLine);
        Matcher matcher = pattern.matcher("");
        HashSet<String> genreSet = new HashSet<String>();
        long linesTotal = 0, linesMatched = 0;
        String line = null;

        while ((line = br.readLine()) != null) {
            linesTotal++;
            if (matcher.reset(line).matches()) {
                linesMatched++;
                String[] parts = matcher.group(genrePosition).split("\\|");
                for (String genre : parts) {
                    genreSet.add(genre);
                }
            } else {
                logp.error("foobar line found: " + line);
            }
        }
        br.close();

        logp.info(String.format("total lines|matched|failed: %1$s|%2$s|%3$s.", linesTotal, linesMatched, (linesMatched - linesTotal)));
        logp.info("total genres: " + genreSet.size());
        logp.info("list of genres: " + genreSet.toString());
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
