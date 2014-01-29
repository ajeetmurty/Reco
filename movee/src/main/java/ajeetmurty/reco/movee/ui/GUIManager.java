package ajeetmurty.reco.movee.ui;

import java.awt.*;
import java.util.ArrayList;

import ajeetmurty.reco.movee.db.DBReader;
import ajeetmurty.reco.movee.engine.RecomEngineMain;
import ajeetmurty.reco.movee.objs.movieObj;

public class GUIManager {
	public static MovieInfoPanel infoPnl;
	public static ratePanel ratePnl;
	public static recomPanel recomPnl;
	public static HelpUI helpPnl;
	public static RateMainPanel rateMain;
	public static mainScreen mainPnl;
	public static recommendationsList recom;
	public static recomTopPanel recomTop;
	public static recomBottomPanel recomBottom;

	public GUIManager() {
	}

	// register UI components
	public static void registerRecomTopPanel(recomTopPanel recomPnl) {
		recomTop = recomPnl;
	}

	public static void registerRecomBottomPanel(recomBottomPanel recomPnl) {
		recomBottom = recomPnl;
	}

	public static void registerRecommendationsList(recommendationsList recomTemp) {
		recom = recomTemp;
	}

	public static void registerMainScreen(mainScreen main) {
		mainPnl = main;
	}

	public static void registerRateMainPanel(RateMainPanel rate) {
		rateMain = rate;
	}

	public static void registerMovieInfo(MovieInfoPanel temp) {
		infoPnl = temp;
	}

	public static void registerRatePanel(ratePanel temp) {
		ratePnl = temp;
	}

	public static void registerRecomPanel(recomPanel temp) {
		recomPnl = temp;
	}

	public static void registerHelpUI(HelpUI temp) {
		helpPnl = temp;
	}

	// interpanel communications
	public static ArrayList getRecommendations() {
		return RecomEngineMain.getRecommendations();
	}

	public static void setMovieInfo(movieObj obj) {
		infoPnl.setMovieInfo(obj);
	}

	public static void refreshInfo() {
		ratePnl.refreshTable();
	}

	public static void recomputeRecommendations() {
		RecomEngineMain.recomputeRecommendations();
	}

	private static java.awt.Toolkit tk = Toolkit.getDefaultToolkit();

	public static int getScreenHeight() {
		return (int) tk.getScreenSize().getHeight();
	}

	public static int getScreenWidth() {
		return (int) tk.getScreenSize().getWidth();
	}

	public static int getMovieRatedCount() {
		return DBReader.getMoviesRateCount();
	}

	public static void refreshRecommendations() {
		recom.refreshTopPanel();
	}

	public static void refreshTopPanel() {
		recomTop.refreshMovieRecommendations();
	}

	public static void refreshRecommMovieInfo(movieObj obj) {
		recomBottom.setMovieInfo(obj);
	}
}
