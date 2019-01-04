import java.io.*; 
import java.util.*;

class HockeyPlayer{
	//fields
	private String lastName;
	private String position;
	private int jersey;
	private int gamesPlayed;
	
	//constructor
	public HockeyPlayer(String lastName, String position, int jersey, int gamesPlayed){
		this.lastName = lastName;
		this.position = position;
		this.jersey = jersey;
		this.gamesPlayed = gamesPlayed;
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
	
	//utility method
	@Override
	public String toString(){
		return lastName + " | #" + jersey + " | " + gamesPlayed + " games played";
	}
}

public class ReadInDataV4{ 
	//fields
	private TreeMap<String, ArrayList<HockeyPlayer>> rosterMaps;
	
	//constructor
	public ReadInDataV4(){
		setRosterMaps();
	}
	
	//setters
	public void setRosterMaps(){
		rosterMaps = new TreeMap<>();
		try{
			File file = new File("C:\\Users\\593476\\Desktop\\Java Programs\\roster2018GP.txt"); 
    			Scanner sc = new Scanner(file); 
    			int counter = 0;
    			String name = "";
    			String position = "";
    			int jersey = 0;
    			int gamesPlayed = 0;
    			while (sc.hasNextLine()) {
    				String getData = sc.nextLine();
    				if(counter%5==0){
    				}
    				else if(counter%5==1){
    					name = getData;
    				}
    				else if(counter%5==2){
    					position = getData;	
    				}
    				else if(counter%5==3){
    					jersey = Integer.parseInt(getData);	
    				}
    				else if(counter%5==4){
    					gamesPlayed = Integer.parseInt(getData);	
    					ArrayList<HockeyPlayer> players = new ArrayList<>();
    					if(rosterMaps.get(position) != null){
    						players.addAll(rosterMaps.get(position));
    					}
    					HockeyPlayer hp = new HockeyPlayer(name, position, jersey, gamesPlayed);
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

	//getters
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
    		ReadInDataV4 rid = new ReadInDataV4();
    		rid.mainMenu();
      } 
} 