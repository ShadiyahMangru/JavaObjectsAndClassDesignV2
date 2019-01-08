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

public class DataReaderV2{ 
	//fields
	private TreeMap<String, ArrayList<HockeyPlayer>> rosterMaps;
	private TreeSet<Integer> yearsOfData;
	private ArrayList<String> avaPositions;
	
	//constructor
	public DataReaderV2(){
		setRosterMaps();
		setAvaPositions();
		setYearsOfData();
	}
	
	//setters
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
	
	public void setAvaPositions(){
		avaPositions = new ArrayList<>();
		avaPositions.addAll(getRosterMaps().keySet());	
	}
	
	public void setYearsOfData(){
		yearsOfData = new TreeSet<>();	
		for(int i = 0; i<getAvaPositions().size(); i++){
			ArrayList<HockeyPlayer> hpAL = new ArrayList<>(getRosterMaps().get(getAvaPositions().get(i)));
			for(HockeyPlayer hp : hpAL){
				for(Integer num : hp.getYearsWith()){
					yearsOfData.add(num);	
				}
			}
		}
	}

	//getters
	public TreeMap<String, ArrayList<HockeyPlayer>> getRosterMaps(){
		return rosterMaps;	
	}
	
	public ArrayList<String> getAvaPositions(){
		return avaPositions;	
	}
	
	public TreeSet<Integer> getYearsOfData(){
		return yearsOfData;	
	}
	
	//utility methods
	public void printTimeline(ArrayList<HockeyPlayer> hpList){
		ArrayList<Object> avaYears = new ArrayList<>();
		avaYears.addAll(getYearsOfData());
		Iterator iterator = getYearsOfData().iterator(); 
		int counter = 1;
		for(Object o : avaYears){
			System.out.println("\t" + o + " ---");
			for(HockeyPlayer h : hpList){
				if(h.getYearsWith().contains(o)){
					System.out.println("\t\t" + h.getLastName());
				}
			}
			counter++;
		}		
	}
	
	public void mainMenu(){
		System.out.println("******************************************************************");
		System.out.println("CURRENT ROSTER SENIORITY *TIMELINE* WIZARD");
		System.out.println("\n\tMAIN MENU:\n");
		System.out.println("\tSelect a Position to view Seniority Timeline");
		int counter = 1;
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
				System.out.println(getAvaPositions().get(userChoice-1) + " Timeline: " );
				hpList.clear();
				hpList.addAll(getRosterMaps().get(getAvaPositions().get(userChoice-1)));
				Collections.sort(hpList);
				printTimeline(hpList);
				System.out.println("\n\n");
				mainMenu();
			}
			else if(userChoice == getAvaPositions().size()+1){
				System.out.println("All Current Players Timeline: ");
				hpList.clear();
				for(int i = 0; i<getAvaPositions().size(); i++){
					hpList.addAll(getRosterMaps().get(getAvaPositions().get(i)));	
				}
				Collections.sort(hpList);
				printTimeline(hpList);
				System.out.println("\n\n");
				mainMenu();
			}
		}
		catch(Exception e){
			System.out.println("Exception: " + e + ".");
		}
	}
	
	public static void main(String[] args) throws Exception{ 
    		DataReaderV2 dr = new DataReaderV2();
    		dr.mainMenu();
      } 
} 