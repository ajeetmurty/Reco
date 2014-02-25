package ajeetmurty.reco.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {
    private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        logp.info("start");
        logp.info("this is a ETL toolkit for the reco project.");
        logp.info("check individual packages for various tools.");
        logp.info("stop");
    }
}
