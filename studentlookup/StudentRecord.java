package assn1;

/**
 * Represents a student
 *TODO add getter and setter methods as appropriate.
 *
 */
public class StudentRecord {
	private String fname;
	private String lname;
	private String emailAddr;
	private String imageLocation;
	
	/** Default constructor for a generic instance of a student record */
	public StudentRecord() {
		fname = "first name";
		lname = "last name";
		emailAddr = "abc@default.net";
		imageLocation = "images/generic.png";
	}

        /**
	 * Creates a StudentRecord with specified first name, last name, email address,
	 * and image file location
	 * @param fname first name of the student
	 * @param lName last name of the student
	 * @param email email address of the student
	 * @param imageFileName file name of the image of the student, within the "images" directory
	 */
	public StudentRecord (String fname, String lname, String email, String imageFileName){
		this.fname = fname;
		this.lname = lname;
		emailAddr = email;
		imageLocation = "images/"+imageFileName;
	}

		public String getFname() {
			return fname;
		}

		public String getLname() {
			return lname;
		}

		public String getEmailAddr() {
			return emailAddr;
		}

		public String getImageLocation() {
			return imageLocation;
		}
	
}
