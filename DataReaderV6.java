import java.io.*; 
import java.util.*;

class HockeyPlayer{
	//fields
	private String lastName;
	private String position;
	private int jersey;
	private int pointsWins;
	
	//constructor
	public HockeyPlayer(String lastName, String position, int jersey, int pointsWins){
		this.lastName = lastName;
		this.position = position;
		this.jersey = jersey;
		this.pointsWins = pointsWins;
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
	
	public void setPointsWins(int pointsWins){
		this.pointsWins = pointsWins;	
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
	
	public int getPointsWins(){
		return pointsWins;	
	}
	
	//utility method
	@Override
	public String toString(){
		return lastName;
	}
}

public class DataReaderV6{ 
	//fields
	private TreeMap<String, ArrayList<HockeyPlayer>> rosterMaps;
	private ArrayList<String> avaPositions;
	private ArrayList<Integer> intervals;
	private int intervalDist;
	
	//constructor
	public DataReaderV6(int intervalDist){
		this.intervalDist = intervalDist;
		setRosterMaps();
		setAvaPositions();
		setIntervals(intervalDist);
	}
	
	//setters
	public void setRosterMaps(){
		rosterMaps = new TreeMap<>();
		try{
			File file = new File("C:\\Users\\593476\\Desktop\\Java Programs\\roster2018PointsWins.txt"); 
    			Scanner sc = new Scanner(file); 
    			int counter = 0;
    			String name = "";
    			String position = "";
    			int jersey = 0;
    			int pointsWins = 0;
    			while (sc.hasNextLine()) {
    				String getData = sc.nextLine();
    				if(counter==0){
    					name = getData.trim();
    				}
    				else if(counter==1){
    					position = getData.trim();	
    				}
    				else if(counter==2){
    					jersey=Integer.parseInt(getData.trim());	
    				}
    				else if(counter==3 ){
    					pointsWins = Integer.parseInt(getData.trim());
				}
				counter++;
    				if(getData.equals("*")){
    					ArrayList<HockeyPlayer> players = new ArrayList<>();
    					if(rosterMaps.get(position) != null){
    						players.addAll(rosterMaps.get(position));
    					}
    					HockeyPlayer hp = new HockeyPlayer(name, position, jersey, pointsWins);
    					players.add(hp);
    					rosterMaps.put(position, players);   					
    					counter = 0;
    				}
    			}
    		}
    		catch(Exception e){
    			System.out.println("Exception: " + e);
    		}
	}
	
	public void setAvaPositions(){
		avaPositions = new ArrayList<>();
		avaPositions.addAll(getRosterMaps().keySet());	
	}

	public void setIntervals(int intervalDist){
		ArrayList<HockeyPlayer> hpAL = new ArrayList<>();
		for(int i = 0; i<getAvaPositions().size(); i++){
			hpAL.addAll(getRosterMaps().get(getAvaPositions().get(i)));	
		}
		ArrayList<Integer> hpPointsWinsAL = new ArrayList<>();
		for(HockeyPlayer hp: hpAL){
			hpPointsWinsAL.add(hp.getPointsWins());	
		}
		Collections.sort(hpPointsWinsAL);
		Collections.reverse(hpPointsWinsAL);
		int max = hpPointsWinsAL.get(0);
		while(max%intervalDist!=0){
			max+=1;	
		}
		intervals = new ArrayList<>();
		for(int i = 0; i<=max; i+=intervalDist){
			intervals.add(i);	
		}
	}
	
	//getters
	public TreeMap<String, ArrayList<HockeyPlayer>> getRosterMaps(){
		return rosterMaps;	
	}
	
	public ArrayList<String> getAvaPositions(){
		return avaPositions;	
	}
	
	public ArrayList<Integer> getIntervals(){
		return intervals;	
	}
	
	//utility methods
	public void printHorizLines(){
		System.out.println();
		System.out.print("_____________");
		for(Integer num : intervals){
			System.out.print("______");
		}
		System.out.println();
	}
	
	public void printXAxisLabels(){
		System.out.print(String.format("%-18s | ", "Player (Pts/Wins)"));	
		for(Integer num : intervals){
			System.out.print(String.format("%-2s | ", num));
		}
	}
	
	public void printBarGraph(ArrayList<HockeyPlayer> hpList){
		printHorizLines();
		printXAxisLabels();
		printHorizLines();
		//print y-axis labels and plot graph
		for(HockeyPlayer hp : hpList){
			System.out.print(String.format("%-18s | ", hp.getLastName() + " (" + hp.getPointsWins() + ")"));
			for(int i = 0; i<getIntervals().size(); i++){
				if(hp.getPointsWins()>= getIntervals().get(i)){
					System.out.print(String.format("%-2s   ", "X"));
				}
				else if(hp.getPointsWins()>getIntervals().get(i-1) & hp.getPointsWins() < getIntervals().get(i)){
						System.out.print(String.format("%-2s   ", "+"));		
				}
				else{
					System.out.print(String.format("%-2s   ", ""));
				}
			}
			printHorizLines();
		}
		printXAxisLabels();	
		printHorizLines();
		System.out.println("\n\tLegend: \n\tX -- player's points/wins are greater than or equal to this interval's max value \n\t+ -- player's points/wins are less than this interval's max value");
	}
	
	public void mainMenu(){
		System.out.println("******************************************************************");
		System.out.println("CURRENT ROSTER POINTS/WINS *HORIZONTAL BAR GRAPH* WIZARD");
		System.out.println("\n\tMAIN MENU:\n");
		System.out.println("\tSelect a Position to view Points/Wins Horizontal Bar Graph");
		int counter = 1;
		String title;
		for(String o : getAvaPositions()){
			System.out.println("\t" + counter + ".) " + o);
			counter++;
		}
		System.out.println("\t" + counter + ".) All");
		System.out.println("\n\n\t" + (counter+1) + ".) EXIT\n");
		ArrayList<HockeyPlayer> hpList = new ArrayList<>();
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter number of selection: ");
			int userChoice = Integer.parseInt(reader.readLine());
			if(userChoice == getAvaPositions().size() + 2){
				System.out.println("You selected: EXIT");
				System.exit(0);
			}
			else if(userChoice <= getAvaPositions().size()){
				title = "\n" + getAvaPositions().get(userChoice-1) + " Points/Wins Horizontal Bar Graph: ";
				System.out.println(title.toUpperCase());
				hpList.clear();
				hpList.addAll(getRosterMaps().get(getAvaPositions().get(userChoice-1)));
				printBarGraph(hpList);
				System.out.println("\n\n");
				mainMenu();
			}
			else if(userChoice == getAvaPositions().size()+1){
				title = "\nAll Current Players Points/Wins Horizontal Bar Graph: ";
				System.out.println(title.toUpperCase());
				hpList.clear();
				for(int i = 0; i<getAvaPositions().size(); i++){
					hpList.addAll(getRosterMaps().get(getAvaPositions().get(i)));	
				}
				printBarGraph(hpList);
				System.out.println("\n\n");
				mainMenu();
			}
		}
		catch(Exception e){
			System.out.println("Exception: " + e + ".");
		}
	}
	
	public static void main(String[] args) throws Exception{ 
    		DataReaderV6 dr = new DataReaderV6(3);
    		dr.mainMenu();
      } 
} 