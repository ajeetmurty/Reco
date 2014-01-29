package ajeetmurty.reco.movee.objs;

public class userObj {
	private int userId;
	private int rating;

	public userObj(int id, int rate) {
		userId = id;
		rating = rate;
	}

	public int getId() {
		return userId;
	}

	public int getRate() {
		return rating;
	}
}
