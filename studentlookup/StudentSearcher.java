package assn1;

import java.util.ArrayList;

/**
 * Class that searches for StudentRecords within the CSCI 4300 student list. This class can be
 * converted to a JavaBean by adding appropriate fields and methods.
 */
public class StudentSearcher {

	private ArrayList<StudentRecord> studentList;
	private ArrayList<StudentRecord> searchResults;
	private String searchString;
	
	/**
	 * Initializes the student record list with all the current CSCI 4300 students.
	 */
	public StudentSearcher(){
			searchResults = new ArrayList<StudentRecord>();
			studentList = new ArrayList<StudentRecord>();
			studentList.add(new StudentRecord("Javid","Aceil","jaceil@uga.edu","jaceil.jpg"));
			studentList.add(new StudentRecord("Kashif M.","Ahmadi","ntropy@uga.edu","ntropy.jpg"));
			studentList.add(new StudentRecord("Sarah Kathlee","Allen","skate94@uga.edu","skate94.jpg"));
			studentList.add(new StudentRecord("Corey Evan","Anderson","coreya1@uga.edu","coreya1.jpg"));
			studentList.add(new StudentRecord("Aaron M","Barry","ab06086@uga.edu","ab06086.jpg"));
			studentList.add(new StudentRecord("Cecil James","Bennett","cecil@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Brian Sco","Benzinger","bb42392@uga.edu","bb42392.jpg"));
			studentList.add(new StudentRecord("Dylan A","Bowne","dylanab@uga.edu","generic.png"));
			studentList.add(new StudentRecord("David Peacock","Braun","dpb@uga.edu","dpb.jpg"));
			studentList.add(new StudentRecord("Vincent J","Capparelli","vince91@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Cameron Gantt","Day","camgday@uga.edu","camgday.jpg"));
			studentList.add(new StudentRecord("Michael Henry","Day","mday99@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Courtney Brook","Dean","cdean15.@uga.edu","cdean15.jpg"));
			studentList.add(new StudentRecord("Christoph","Donaldson","cdonald0@uga.edu","cdonald0.jpg"));
			studentList.add(new StudentRecord("Katrina Marie","Egan","kme3@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Ross Edmond","Freeman","ref513@uga.edu","ref513.jpg"));
			studentList.add(new StudentRecord("Christopher","Ghyzel","mrghyzel@uga.edu","mrghyzel.jpg"));
			studentList.add(new StudentRecord("Luke Walke","Grantham","lgrant2@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Daniel M","Heidenhain","danielh@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Logan Thomas","Henry","lthenry@uga.edu","lthenry.jpg"));
			studentList.add(new StudentRecord("David Clayto","Herald","jdherald@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Casey Lewis","Hetzler","chetzl3r@uga.edu","chetzl3r.jpg"));
			studentList.add(new StudentRecord("Alexander M","Hollums","hollums@uga.edu","hollums.jpg"));
			studentList.add(new StudentRecord("Edward Fr","Killmeier","efk4@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Jason Ricky","Kirby","kirby311@uga.edu","kirby311.jpg"));
			studentList.add(new StudentRecord("Carley Lynd","Leavitt","carley@uga.edu","carley.jpg"));
			studentList.add(new StudentRecord("Peter Duy","Luu","leralyth@uga.edu","leralyth.jpg"));
			studentList.add(new StudentRecord("David Le","McEwen","jaceil@uga.edu","dlmcewen.jpg"));
			studentList.add(new StudentRecord("Jared St","McReynolds","dlmcewen@uga.edu","jarmac76.jpg"));
			studentList.add(new StudentRecord("Andrew Thoma","Morris","atmorris@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Nicholas Barry","Moss","nbmoss@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Amanda Kath","Palusky","paluskya@uga.edu","paluskya.jpg"));
			studentList.add(new StudentRecord("Ami Jitendra","Patel","amipat66","amipat66.jpg"));
			studentList.add(new StudentRecord("Pinal","Patel","pinpat90@uga.edu","pinpat90.jpg"));
			studentList.add(new StudentRecord("Stephen Jose","Patton","spatton8@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Cody","Polakovic","cody6690@uga.edu","cody6690.jpg"));
			studentList.add(new StudentRecord("Kyle Martin","Rackley","kylerack@uga.edu","kylerack.jpg"));
			studentList.add(new StudentRecord("Justin Edwar","Rector","jrec15@uga.edu","jrec15.jpg"));
			studentList.add(new StudentRecord("William C","Spivey","wspivey@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Jason","Tan","jtan92@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Michael","Tankersley","mtank23@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Clinton Navy","Teague","cteague8@uga.edu","cteague8.jpg"));
			studentList.add(new StudentRecord("James Cliff","Vaughan","jamesv14@uga.edu","jamesv14.jpg"));
			studentList.add(new StudentRecord("Thinh Tuong","Vu","thinhvu@uga.edu","thinhvu.jpg"));
			studentList.add(new StudentRecord("Bradley Ben","Wheeler","bbw@uga.edu","generic.png"));
			studentList.add(new StudentRecord("Alexander Dea","White","awhite@uga.edu","awhite.jpg"));
			studentList.add(new StudentRecord("Travis Le","Whittaker","tlw11591@uga.edu","tlw11591.jpg"));
	}//constructor
	/**
	 * Returns a list of all the students whose first or last name contains 'searchString' as a substring. 
	 * The search results are case-insensitive.
	 */
	public ArrayList<StudentRecord> getSearchResults() {
		searchResults.clear();
		for(int i = 0; i < studentList.size(); i++)
		{  
			if(searchString.equals("") || searchString==null) 
				break;
			String temp = studentList.get(i).getFname().toLowerCase();
			String temp2 = studentList.get(i).getLname().toLowerCase();
			if(temp.contains(searchString.toLowerCase()) ||
				temp2.contains(searchString.toLowerCase()))
					searchResults.add(studentList.get(i));
		}
		return searchResults;
	}
		
	public void setSearchString(String s) {
		searchString = s;
	}
		
}//class


