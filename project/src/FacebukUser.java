import java.util.ArrayList;

public class FacebukUser extends FacebookObject{
	//private  String name;
	private Image image;
	private ArrayList<FacebukUser> friends;
	public ArrayList<Moment> moments;  // SHOULD THIS BE PUBLIC???
	
	public FacebukUser()
	{
		super();
	}
	public FacebukUser(String name, Image image)
	{
		super(name);
		this.image = image;
	}
//	public String getName()
//	{
//		return name;
//	}
	public ArrayList<FacebukUser> getFriends() 
	{
		return friends;
	}
	public void setFriends(ArrayList<FacebukUser> friends)
	{
		this.friends = friends;
	}
	public void setMoments(ArrayList<Moment> moments)
	{
		this.moments = moments;
	}
	public FacebukUser getFriendWithWhomIAmHappiest()
	{
		if(this.friends.size() > 0) 
		{
			FacebukUser HappiestWithFriend = new FacebukUser();
			float maxHappyLevel = -100000;
			// get each friend		
			for(int i = 0; i < this.friends.size(); i++)
			{
				FacebukUser f = this.friends.get(i);
				// find max Happy Value with main User 
				float friendMax = maxHappyLevelOfPersonInMoments(FacebukUser.this, f.moments); 
				// ******NOTE: ^^^^ is FacebukUser.this main User!!!!!!!??? ******
				if(friendMax > maxHappyLevel)
				{	
					// new happiest friend
					HappiestWithFriend = f;
					maxHappyLevel = friendMax;
				}
			}
			return HappiestWithFriend;
		} else 
		{
			// No friends :(
			return null;
		}		
	}
	private Float maxHappyLevelOfPersonInMoments(FacebukUser user, ArrayList<Moment> moments)
	{	
		float maxHappyVal = -100000; 
		if(moments != null && moments.size() > 0)
		{
			for(int i = 0; i < moments.size(); i++)
			{
				Moment m = moments.get(i);
				if(m.participants != null && m.participants.size() > 0)
				{
					for(int k = 0; k < m.participants.size(); k++)
					{
						FacebukUser p = m.participants.get(k);
						if(p.equals(user))
						{
							// User is in this moment!!!
							if(m.smileValues.get(k) > maxHappyVal)
							{
								// Add to the maxHappyVal
								maxHappyVal = m.smileValues.get(k);
							}
						}
					}
				}
			}
		}
		// will return -10000 if no moments found with FacebukUser
		return maxHappyVal;
	}
	public Moment getOverallHappiestMoment()
	{
		if(this.moments.size() > 0)
		{
			Moment bestMoment = new Moment(); 
			float maxHappyAvg = -10000;
			for(Moment m : this.moments)
			{
				float happySum = 0;
				for(float val : m.smileValues)
				{
					happySum += val;
				}
				float happyAvg = happySum / m.smileValues.size();
				if(happyAvg > maxHappyAvg)
				{
					// new best moment
					maxHappyAvg = happyAvg;
					bestMoment = m;
				}
			}
			return bestMoment;
		} else
		{
			// No moments :(
			return null;
		}
	}
	//Returns a list of the largest possible group of facebuk users who are friends with the target user
//	public ArrayList<FacebukUser> findMaximumCliqueOfFriends()
//	{
//		
//	}
	private ArrayList<FacebukUser> findClique()
	{
		ArrayList<FacebukUser> collection = new ArrayList<FacebukUser>();
		
		for(FacebukUser f: this.friends)
		{
			ArrayList<FacebukUser> pClique = new ArrayList<FacebukUser>();
			pClique.add(f);
			
			for(FacebukUser newF: this.friends)
			{
				if(!pClique.contains(newF))
				{
					ArrayList<FacebukUser> temp = pClique;
					temp.add(newF);
					if(isClique(temp))
					{
						pClique.add(newF);
					}
				}
			}
			for(FacebukUser clique:  pClique)
				{
					collection.add(clique);
				}
		}
		return getLargest(collection);	
	}
	
	private ArrayList<FacebukUser> getLargest(ArrayList<ArrayList<FacebukUser>> list)
	{
		ArrayList<FacebukUser> temp;
		for(int i=0; i< list.size();i++)
		{
			if(list.get(i).size() > temp.size())
				{
					temp = list.get(i);
				}
		}
		return temp;
		
	}
	public static boolean isClique (ArrayList<FacebukUser> set)
	{
		boolean test;
		if(set != null && set.size() > 0)
		{
			test = true;
			for(FacebukUser user: set)
			{
				for(FacebukUser otherUser: set)
				{
					if(!user.equals(otherUser) && user.friends != null && !user.friends.contains(otherUser))
					{
						test = false;
						break;
					}
				}
			}
		} else
		{
			test = false;
		}
		return test;
	}
	
	public ArrayList<ArrayList<FacebukUser>> generateList()
	{
		ArrayList<ArrayList<FacebukUser>> list = new ArrayList<ArrayList<FacebukUser>>();
		if(this.friends != null && this.friends.size() > 0) 
		{
			for(FacebukUser f: this.friends)
			{
				ArrayList<FacebukUser> list2 = new ArrayList<FacebukUser>();
				if(f.friends != null && f.friends.size() > 0)
				{
					for(FacebukUser f2: f.friends)
					{
						list2.add(f2);
					}
				}
				list.add(list2);
			}
		}
		return list;
	}
	
}
