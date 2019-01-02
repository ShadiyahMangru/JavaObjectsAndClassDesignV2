import java.io.*; 
import java.util.*; 

public class ReadInData{ 
	//fields
	private TreeMap<Integer, String> rosterMaps;
	private String rosterData;
	private Set<String> franchiseRoster;
	
	//constructor
	public ReadInData(){
		setRosterData();
		setRosterMaps();
		setFranchiseRoster();
	}
	
	//setters
	public void setRosterData(){
		try{
			File file = new File("C:\\Users\\593476\\Desktop\\Java Programs\\rosters.txt"); 
    			Scanner sc = new Scanner(file); 
    			while (sc.hasNextLine()) 
    				rosterData +=sc.nextLine();
    		}
    		catch(Exception e){
    			System.out.println("Exception: " + e);
    		}
	}
	
	public void setRosterMaps(){	
		rosterMaps= new TreeMap<>();
		Integer key = 0;
		String roster = "";
		for(int i = 0; i< spliceStringIndices().size() - 1; i++){
			if(i%2 == 0){
				key = Integer.parseInt(rosterData.substring((spliceStringIndices().get(i) + 1), spliceStringIndices().get(i+1)));	
			}
			else{
				roster = 	rosterData.substring(spliceStringIndices().get(i), spliceStringIndices().get(i+1));
				rosterMaps.put(key, roster);
			}
		}
	}
	
	public void setFranchiseRoster(){
		franchiseRoster = new TreeSet<String>();
		Object[] seasons = rosterMaps.keySet().toArray();
		String roster;
		String[] rosterArray;
		for(Object year : seasons){
			roster = rosterMaps.get(year);
			rosterArray = roster.split(", ");
			for(String hp : rosterArray){
				franchiseRoster.add(hp);
			}
		}
	}
	
	//getters	
	public String getRosterData(){
		return rosterData;	
	}
	
	public TreeMap<Integer, String> getRosterMaps(){
		return rosterMaps;	
	}
	
	public Set<String> getFranchiseRoster(){
		return franchiseRoster;	
	}
	
	//utility methods
	public ArrayList<Integer> spliceStringIndices(){
	    	int index = 0;
	    	ArrayList<Integer> splitHere = new ArrayList<>();
	    	while(index < rosterData.length()){
	    		index = rosterData.indexOf("*", index);
	    		splitHere.add(index);
	    		index++;
	    	}
	    	return splitHere;
	}
	
	public int playerFrequency(String player){
		int countSeasons = 0;
		Object[] seasons = rosterMaps.keySet().toArray();
		for(Object year : seasons){
			if(rosterMaps.get(year).contains(player)){
				countSeasons++;		
			}
		}
		return countSeasons;
	}
	
	public void mainMenu(){
		System.out.println("******************************************************************");
		System.out.println("FRANCHISE ROSTER HISTORY WIZARD");
		System.out.println("\n\tMAIN MENU:\n");
		System.out.println("\tSelect a Player to Determine Number of Seasons with CAPS since 2013-2014 Regular Season");
		ArrayList<Object> fRoster = new ArrayList<>();
		fRoster.addAll(getFranchiseRoster());
		Iterator iterator = getFranchiseRoster().iterator(); 
		int counter = 1;
		for(Object o : fRoster){
			System.out.println("\t" + counter + ".) " + o);
			counter++;
		}
		System.out.println("\n\n\t" + counter + ".) EXIT\n");
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter number of selection: ");
			int userChoice = Integer.parseInt(reader.readLine());
			if(userChoice == fRoster.size() + 1){
				System.out.println("You selected: EXIT");
				System.exit(0);
			}
			else if(userChoice <= fRoster.size()){
				System.out.println("You selected: " + fRoster.get(userChoice-1));
				int seasonsPlayed = playerFrequency((String)fRoster.get(userChoice-1));
				System.out.println(fRoster.get(userChoice-1) + " has played " + seasonsPlayed + " season(s) with the CAPS since 2013.");
			}
		}
		catch(Exception e){
			System.out.println("Exception: " + e + ".");
		}
	}
	
	public static void main(String[] args) throws Exception{ 
    		ReadInData rid = new ReadInData();
    		rid.mainMenu();
      } 
} 