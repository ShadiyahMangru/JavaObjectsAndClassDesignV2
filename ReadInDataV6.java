import java.io.*; 
import java.util.*;
import java.time.*;
import java.time.format.*;

class HockeyPlayer{
	//fields
	private String lastName;
	private String position;
	private int jersey;
	
	//constructor
	public HockeyPlayer(String lastName, String position, int jersey){
		this.lastName = lastName;
		this.position = position;
		this.jersey = jersey;
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
	
	//utility method
	@Override
	public String toString(){
		return lastName + " | #" + jersey;
	}
}

class Goalie extends HockeyPlayer{
	//fields
	private int gamesPlayed;
	private int winsPoints;
	private String sDate;
	private LocalDate date;
	private int saves;
	
	//constructor
	public Goalie(HockeyPlayer hp, int gamesPlayed, int winsPoints, String sDate, int saves){
		super(hp.getLastName(), hp.getPosition(), hp.getJersey());	
		this.gamesPlayed = gamesPlayed;
		this.winsPoints = winsPoints;
		this.sDate = sDate;
		setDate(sDate);
		this.saves = saves;
	}
	
	//setters
	public void setGamesPlayed(int gamesPlayed){
		this.gamesPlayed = gamesPlayed;	
	}
	
	public void setWinsPoints(int winsPoints){
		this.winsPoints = winsPoints;	
	}
	
	public void setSDate(String sDate){
		this.sDate = sDate;	
	}
	
	public void setDate(String dateString){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		this.date=LocalDate.parse(dateString, formatter);
	}
	
	public void setSaves(int saves){
		this.saves = saves;	
	}
	
	//getters
	public int getGamesPlayed(){
		return gamesPlayed;
	}	
	
	public int getWinsPoints(){
		return winsPoints;	
	}
	
	public String getSDate(){
		return sDate;	
	}
	
	public LocalDate getDate(){
		return date;	
	}
	
	public int getSaves(){
		return saves;	
	}
	
	//utility method
	@Override
	public String toString(){
		return String.format("%-19s | %-5s  %-16s  %-10s  %-10s %-16s  %n", getLastName(), "#" + getJersey() + ": ", getGamesPlayed() + " games played," , getWinsPoints() + " wins,", getSaves() + " saves", "(Last Updated: " + getDate() + ")");	
	}
}

class Skater extends HockeyPlayer{
	//fields
	private int gamesPlayed;
	private int winsPoints;
	private String sDate;
	private LocalDate date;
	private int shots;
	
	//constructor
	public Skater(HockeyPlayer hp, int gamesPlayed, int winsPoints, String sDate, int shots){
		super(hp.getLastName(), hp.getPosition(), hp.getJersey());	
		this.gamesPlayed = gamesPlayed;
		this.winsPoints = winsPoints;
		this.sDate = sDate;
		setDate(sDate);
		this.shots = shots;
	}
	
	//setters
	public void setGamesPlayed(int gamesPlayed){
		this.gamesPlayed = gamesPlayed;	
	}
	
	public void setWinsPoints(int winsPoints){
		this.winsPoints = winsPoints;	
	}
	
	public void setSDate(String sDate){
		this.sDate = sDate;	
	}
	
	public void setDate(String dateString){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		this.date=LocalDate.parse(dateString, formatter);
	}
	
	public void setShots(int shots){
		this.shots = shots;	
	}
	
	//getters
	public int getGamesPlayed(){
		return gamesPlayed;
	}	
	
	public int getWinsPoints(){
		return winsPoints;	
	}
	
	public String getSDate(){
		return sDate;	
	}
	
	public LocalDate getDate(){
		return date;	
	}
	
	public int getShots(){
		return shots;	
	}
	
	//utility method
	@Override
	public String toString(){
		return String.format("%-19s | %-5s  %-16s  %-10s  %-10s %-16s  %n", getLastName(), "#" + getJersey() + ": ", getGamesPlayed() + " games played," , getWinsPoints() + " points,", getShots() + " shots", "(Last Updated: " + getDate() + ")");	
	}
}

public class ReadInDataV6{ 
	//field
	private TreeMap<String, ArrayList<HockeyPlayer>> rosterMaps;
	
	//constructor
	public ReadInDataV6(){
		setRosterMaps();
	}
	
	//setter
	public void setRosterMaps(){
		rosterMaps = new TreeMap<>();
		try{
			File file = new File("C:\\Users\\593476\\Desktop\\Java Programs\\roster2018PositionStats.txt"); 
    			Scanner sc = new Scanner(file); 
    			int counter = 0;
    			String name = "";
    			String position = "";
    			int jersey = 0;
    			int gamesPlayed = 0;
    			int winsPoints = 0;
    			String date = "";
    			int savesShots = 0;
    			while (sc.hasNextLine()) {
    				String getData = sc.nextLine();
    				if(counter%8==0){
    				}
    				else if(counter%8==1){
    					name = getData.trim();
    				}
    				else if(counter%8==2){
    					position = getData.trim();	
    				}
    				else if(counter%8==3){
    					int colonIndex = getData.indexOf(":");
    					getData = getData.substring(colonIndex+1);
    					jersey = Integer.parseInt(getData.trim());	
    				}
    				else if(counter%8==4){
    					int colonIndex = getData.indexOf(":");
    					getData = getData.substring(colonIndex+1).trim();
    					date = getData.trim();
    				}
    				else if(counter%8==5){
    					int colonIndex = getData.indexOf(":");
    					getData = getData.substring(colonIndex+1);
    					gamesPlayed = Integer.parseInt(getData.trim());	
    				}
    				else if(counter%8==6){
    					int colonIndex = getData.indexOf(":");
    					getData = getData.substring(colonIndex+1);
    					winsPoints = Integer.parseInt(getData.trim());
    				}
    				
    				else if(counter%8==7){
    					int colonIndex = getData.indexOf(":");
    					getData = getData.substring(colonIndex+1);
    					savesShots = Integer.parseInt(getData.trim());
    					ArrayList<HockeyPlayer> players = new ArrayList<>();
    					if(rosterMaps.get(position) != null){
    						players.addAll(rosterMaps.get(position));
    					}
    					HockeyPlayer hp = new HockeyPlayer(name, position, jersey);
    					if(position.equals("Goalie")){
    						hp = new Goalie(hp, gamesPlayed, winsPoints, date, savesShots);	
    					}	
    					else{
    						hp = new Skater(hp, gamesPlayed, winsPoints, date, savesShots);	
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
    		ReadInDataV6 rid = new ReadInDataV6();
    		rid.mainMenu();
      } 
} 