import java.util.ArrayList;

public class SimpleTester {
	
	public static void main(String[] args) {
		
		//People
		Person michelle = new Person("Michelle", new Image("Michelle.png"));
		Person barack = new Person("Barack", new Image("Barack.png"));
		Person kevin = new Person("Kevin", new Image("Kevin.png"));
		Person ina = new Person("Ina", new Image("Ina.png"));
		Person joe = new Person("Joe", new Image("Joe.png"));
		Person malia = new Person("Malia", new Image("Malia.png"));
		
		//Pets
		Pet bo = new Pet("Bo", new Image("Bo.png"));
		Pet sunny = new Pet("Sunny", new Image("Sunny.png"));

		bo.setOwner(barack);
		sunny.setOwner(michelle);
		
		//Friends
		ArrayList<FacebukUser> michelleFriends = new ArrayList<FacebukUser>();
		michelleFriends.add(barack);
		michelleFriends.add(kevin);
		michelleFriends.add(ina);
		michelle.setFriends(michelleFriends);
		
		//Moments

	}

}
