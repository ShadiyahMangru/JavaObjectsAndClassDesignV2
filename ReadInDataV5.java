import java.io.*; 
import java.util.*;

class HockeyPlayer{
	//fields
	private String lastName;
	private String position;
	private int jersey;
	private int gamesPlayed;
	private int winsPoints;
	private String date;
	
	//constructor
	public HockeyPlayer(String lastName, String position, int jersey, int gamesPlayed, int winsPoints, String date){
		this.lastName = lastName;
		this.position = position;
		this.jersey = jersey;
		this.gamesPlayed = gamesPlayed;
		this.winsPoints = winsPoints;
		this.date = date;
	}
	
	//setters
	public void setLastName(String lastName){
		this.lastName = lastName;	
	}
	
	public void setPosition(String position){
		this.position = position;	
	}
	
	public void setJersey(int jersey){
		this.jersey = jersey;	
	}
	
	public void setGamesPlayed(int gamesPlayed){
		this.gamesPlayed = gamesPlayed;	
	}
	
	public void setWinsPoints(int winsPoints){
		this.winsPoints = winsPoints;	
	}
	
	public void setDate(String date){
		this.date = date;	
	}
	
	//getters
	public String getLastName(){
		return lastName;	
	}
	
	public String getPosition(){
		return position;	
	}
	
	public int getJersey(){
		return jersey;	
	}
	
	public int getGamesPlayed(){
		return gamesPlayed;
	}	
	
	public int getWinsPoints(){
		return winsPoints;	
	}
	
	public String getDate(){
		return date;	
	}
	
	//utility method
	@Override
	public String toString(){
		return lastName + " | #" + jersey + " | " + gamesPlayed + " games played | " + winsPoints + " points/wins | Last Updated: " + date;
	}
}

class Goalie extends HockeyPlayer{
	//constructor
	public Goalie(HockeyPlayer hp){
		super(hp.getLastName(), hp.getPosition(), hp.getJersey(), hp.getGamesPlayed(), hp.getWinsPoints(), hp.getDate());	
	}
	
	//utility method
	@Override
	public String toString(){
		return getLastName() + " | #" + getJersey() + " | " + getGamesPlayed() + " games played | " + getWinsPoints() + " win(s) | Last Updated: " + getDate();
	}
}

class Skater extends HockeyPlayer{
	//constructor
	public Skater(HockeyPlayer hp){
		super(hp.getLastName(), hp.getPosition(), hp.getJersey(), hp.getGamesPlayed(), hp.getWinsPoints(), hp.getDate());	
	}
	
	//utility method
	@Override
	public String toString(){
		return getLastName() + " | #" + getJersey() + " | " + getGamesPlayed() + " games played | " + getWinsPoints() + " point(s) | Last Updated: " + getDate();
	}
}

public class ReadInDataV5{ 
	//field
	private TreeMap<String, ArrayList<HockeyPlayer>> rosterMaps;
	
	//constructor
	public ReadInDataV5(){
		setRosterMaps();
	}
	
	//setter
	public void setRosterMaps(){
		rosterMaps = new TreeMap<>();
		try{
			File file = new File("C:\\Users\\593476\\Desktop\\Java Programs\\roster2018Stats.txt"); 
    			Scanner sc = new Scanner(file); 
    			int counter = 0;
    			String name = "";
    			String position = "";
    			int jersey = 0;
    			int gamesPlayed = 0;
    			int winsPoints = 0;
    			String date = "";
    			while (sc.hasNextLine()) {
    				String getData = sc.nextLine();
    				if(counter%7==0){
    				}
    				else if(counter%7==1){
    					name = getData.trim();
    				}
    				else if(counter%7==2){
    					position = getData.trim();	
    				}
    				else if(counter%7==3){
    					int colonIndex = getData.indexOf(":");
    					getData = getData.substring(colonIndex+1);
    					jersey = Integer.parseInt(getData.trim());	
    				}
    				else if(counter%7==4){
    					int colonIndex = getData.indexOf(":");
    					getData = getData.substring(colonIndex+1);
    					gamesPlayed = Integer.parseInt(getData.trim());	
    				}
    				else if(counter%7==5){
    					int colonIndex = getData.indexOf(":");
    					getData = getData.substring(colonIndex+1);
    					winsPoints = Integer.parseInt(getData.trim());
    				}
    				
    				else if(counter%7==6){
    					int colonIndex = getData.indexOf(":");
    					getData = getData.substring(colonIndex+1);
    					date = getData.trim();
    					ArrayList<HockeyPlayer> players = new ArrayList<>();
    					if(rosterMaps.get(position) != null){
    						players.addAll(rosterMaps.get(position));
    					}
    					HockeyPlayer hp = new HockeyPlayer(name, position, jersey, gamesPlayed, winsPoints, date);
    					if(position.equals("Goalie")){
    						hp = new Goalie(hp);	
    					}	
    					else{
    						hp = new Skater(hp);	
    					}
    					players.add(hp);
    					rosterMaps.put(position, players);
				}
    				counter++;
    			}
    		}
    		catch(Exception e){
    			System.out.println("Exception: " + e);
    		}
	}

	//getter
	public TreeMap<String, ArrayList<HockeyPlayer>> getRosterMaps(){
		return rosterMaps;	
	}
	
	//utility methods
	public void mainMenu(){
		System.out.println("******************************************************************");
		System.out.println("CURRENT ROSTER (POSITION) WIZARD");
		System.out.println("\n\tMAIN MENU:\n");
		System.out.println("\tSelect a Position");
		int counter = 1;
		ArrayList<String> avaPositions = new ArrayList<>();
		avaPositions.addAll(getRosterMaps().keySet());
		for(String o : avaPositions){
			System.out.println("\t" + counter + ".) " + o);
			counter++;
		}
		System.out.println("\n\n\t" + counter + ".) EXIT\n");
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter number of selection: ");
			int userChoice = Integer.parseInt(reader.readLine());
			if(userChoice == avaPositions.size() + 1){
				System.out.println("You selected: EXIT");
				System.exit(0);
			}
			else if(userChoice <= avaPositions.size()){
				System.out.println(avaPositions.get(userChoice-1) + ": " );
				ArrayList<HockeyPlayer> hpList = new ArrayList<>();
				hpList.addAll(getRosterMaps().get(avaPositions.get(userChoice-1)));
				for(HockeyPlayer h : hpList){
					System.out.println("\t" + h);
				}
				System.out.println("\n\n");
				mainMenu();
			}
		}
		catch(Exception e){
			System.out.println("Exception: " + e + ".");
		}
	}
	
	public static void main(String[] args) throws Exception{ 
    		ReadInDataV5 rid = new ReadInDataV5();
    		rid.mainMenu();
      } 
} 