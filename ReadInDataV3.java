import java.io.*; 
import java.util.*;

public class ReadInDataV3{ 
	//fields
	private TreeMap<String, String> rosterMaps;
	
	//constructor
	public ReadInDataV3(){
		setRosterMaps();
	}
	
	//setters
	public void setRosterMaps(){
		rosterMaps = new TreeMap<>();
		try{
			File file = new File("C:\\Users\\593476\\Desktop\\Java Programs\\roster2018.txt"); 
    			Scanner sc = new Scanner(file); 
    			int counter = 0;
    			String position = "";
    			String player = "";
    			while (sc.hasNextLine()) {
    				String getData = sc.nextLine();
    				if(counter%3==0){
    				}
    				else if(counter%3==1){
    					player = getData;
    				}
    				else if(counter%3==2){
    					position = getData;	
    					if(rosterMaps.get(position) != null){
					player = rosterMaps.get(position) + ", " + player;
				}
				rosterMaps.put(position, player);
    				}
    				counter++;
    			}
    		}
    		catch(Exception e){
    			System.out.println("Exception: " + e);
    		}
	}

	//getters
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
			if(userChoice == avaPositions.size() + 1){
				System.out.println("You selected: EXIT");
				System.exit(0);
			}
			else if(userChoice <= avaPositions.size()){
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
    		ReadInDataV3 rid = new ReadInDataV3();
    		rid.mainMenu();
      } 
} 