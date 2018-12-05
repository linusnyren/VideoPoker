package videopoker;

public class Player {
		int credits;
		String userName;
		
		
		public Player(int credits, String userName) {
			this.credits = credits;
			this.userName = userName;
		}
		
		public int getCredits() {
			return credits;
		}
		public void setCredits(int credits) {
			this.credits = credits;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getNameCredits() {
			return "Player " +userName +" got " +credits +" Smackischars";
		}
	}
