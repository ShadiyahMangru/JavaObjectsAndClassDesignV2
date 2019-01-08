import java.io.*; 
import java.util.*;

class HockeyPlayer implements Comparable<HockeyPlayer>{
	//fields
	private String lastName;
	private String position;
	private ArrayList<Integer> yearsWith;
	
	//constructor
	public HockeyPlayer(String lastName, String position, ArrayList<Integer> yearsWith){
		this.lastName = lastName;
		this.position = position;
		setYearsWith(yearsWith);
	}
	
	@Override
    	public int compareTo(HockeyPlayer other) {
    		if(this.getYearsWith().size() < other.getYearsWith().size()) {
			return 1;
		}
		else if (this.getYearsWith().size() == other.getYearsWith().size()) { 
			return 0;
		}
		else{
			return -1;
		}
	}
	
	//setters
	public void setLastName(String lastName){
		this.lastName = lastName;	
	}
	
	public void setPosition(String position){
		this.position = position;	
	}
	
	public void setYearsWith(ArrayList<Integer> yearsWith){
		this.yearsWith = new ArrayList<>(yearsWith);
	}
	
	//getters
	public String getLastName(){
		return lastName;	
	}
	
	public String getPosition(){
		return position;	
	}
	
	public ArrayList<Integer> getYearsWith(){
		return yearsWith;	
	}
	
	//utility method
	@Override
	public String toString(){
		return lastName + ": " + yearsWith.size() + " year(s) of CAPS seniority.";
	}
}

public class DataReader{ 
	//field
	private TreeMap<String, ArrayList<HockeyPlayer>> rosterMaps;
	
	//constructor
	public DataReader(){
		setRosterMaps();
	}
	
	//setter
	public void setRosterMaps(){
		rosterMaps = new TreeMap<>();
		try{
			File file = new File("C:\\Users\\593476\\Desktop\\Java Programs\\roster2018Seniority.txt"); 
    			Scanner sc = new Scanner(file); 
    			int counter = 0;
    			String name = "";
    			String position = "";
    			int year = 0;
    			ArrayList<Integer> years = new ArrayList<>();
    			while (sc.hasNextLine()) {
    				String getData = sc.nextLine();
    				if(counter==0){
    					name = getData.trim();
    				}
    				else if(counter==1){
    					position = getData.trim();	
    				}
    				else if(counter>=2 && (!getData.equals("*"))){
    					year = Integer.parseInt(getData.trim());
    					years.add(year);
				}
				counter++;
    				if(getData.equals("*")){
    					ArrayList<HockeyPlayer> players = new ArrayList<>();
    					if(rosterMaps.get(position) != null){
    						players.addAll(rosterMaps.get(position));
    					}
    					HockeyPlayer hp = new HockeyPlayer(name, position, years);
    					players.add(hp);
    					rosterMaps.put(position, players);   					
    					counter = 0;
    					years.clear();
    				}
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
		System.out.println("CURRENT ROSTER POSITION SENIORITY WIZARD");
		System.out.println("\n\tMAIN MENU:\n");
		System.out.println("\tSelect a Position");
		int counter = 1;
		ArrayList<String> avaPositions = new ArrayList<>();
		avaPositions.addAll(getRosterMaps().keySet());
		for(String o : avaPositions){
			System.out.println("\t" + counter + ".) " + o);
			counter++;
		}
		System.out.println("\t" + counter + ".) All");
		System.out.println("\n\n\t" + (counter+1) + ".) EXIT\n");
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter number of selection: ");
			int userChoice = Integer.parseInt(reader.readLine());
			if(userChoice == avaPositions.size() + 2){
				System.out.println("You selected: EXIT");
				System.exit(0);
			}
			else if(userChoice <= avaPositions.size()){
				System.out.println(avaPositions.get(userChoice-1) + ": " );
				ArrayList<HockeyPlayer> hpList = new ArrayList<>();
				hpList.addAll(getRosterMaps().get(avaPositions.get(userChoice-1)));
				Collections.sort(hpList);
				for(HockeyPlayer h : hpList){
					System.out.println("\t" + h);
				}
				System.out.println("\n\n");
				mainMenu();
			}
			else if(userChoice == avaPositions.size()+1){
				System.out.println("All");
				ArrayList<HockeyPlayer> hpList = new ArrayList<>();
				for(int i = 0; i<avaPositions.size(); i++){
					hpList.addAll(getRosterMaps().get(avaPositions.get(i)));	
				}
				Collections.sort(hpList);
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
    		DataReader dr = new DataReader();
    		dr.mainMenu();
      } 
} 