package videopoker;

public enum Wins {
	

	Straight_Flush(40), Four_Of_A_Kind(20), Full_House(9), Flush(6), 
	Straight(5), Three_Of_A_Kind(3), Two_Pairs(2), Pair(1);
	
	int factor;
	
	Wins (int wins) {
		factor = wins;
	}
	
}
