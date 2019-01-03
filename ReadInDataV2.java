import java.io.*; 
import java.util.*;

public class ReadInDataV2{ 
	//fields
	private ArrayList<String> rosterData;
	private TreeMap<String, String> rosterMaps;
	
	//constructor
	public ReadInDataV2(){
		setRosterData();
		setRosterMaps();
	}
	
	//setters
	public void setRosterData(){
		rosterData = new ArrayList<>();
		try{
			File file = new File("C:\\Users\\593476\\Desktop\\Java Programs\\roster2018.txt"); 
    			Scanner sc = new Scanner(file); 
    			int counter = 0;
    			while (sc.hasNextLine()) {
    				String getData = sc.nextLine();
    				if(counter%3==0){
    				}
    				else if(counter%3==1){
    					rosterData.add(getData);
    				}
    				else if(counter%3==2){
    					rosterData.add(getData);	
    				}
    				counter++;
    			}
    		}
    		catch(Exception e){
    			System.out.println("Exception: " + e);
    		}
	}
	
	public void setRosterMaps(){	
		rosterMaps= new TreeMap<>();
		String position = "";
		String player = "";
		for(int i = 0; i< getRosterData().size(); i++){
			if(i%2 == 0){
				player = getRosterData().get(i);	
			}
			else{
				position = getRosterData().get(i);
				if(rosterMaps.get(position) != null){
					player = rosterMaps.get(position) + ", " + player;
				}
				rosterMaps.put(position, player);
			}
		}
	}
	
	//getters	
	public ArrayList<String> getRosterData(){
		return rosterData;	
	}
	
	public TreeMap<String, String> getRosterMaps(){
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
			if(userChoice == getRosterMaps().keySet().size() + 1){
				System.out.println("You selected: EXIT");
				System.exit(0);
			}
			else if(userChoice <= getRosterMaps().keySet().size()){
				System.out.print(avaPositions.get(userChoice-1) + ": " );
				System.out.println(getRosterMaps().get(avaPositions.get(userChoice-1)) + "\n\n");
				mainMenu();
			}
		}
		catch(Exception e){
			System.out.println("Exception: " + e + ".");
		}
	}
	
	public static void main(String[] args) throws Exception{ 
    		ReadInDataV2 rid = new ReadInDataV2();
    		rid.mainMenu();
      } 
} 