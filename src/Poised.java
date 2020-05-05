import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**Level 2, Task 24 - Capstone Project
 * Poised - A Project Management Program for an Engineering Firm
* @author Estine van der Berg
* @since 03 May 2020
*/
public class Poised {
	static String filepath = "./ProjectDetails.txt";
	static Date today = Calendar.getInstance().getTime();
	
	/** Method to get user input for the project, contractor, architect and customer details.
	 * @return Returns a Project Object.
	 * @throws InputMismatchException Throws on input error.
	 */
	public static ProjectDetails createProject(){
		
		ProjectDetails project = new ProjectDetails();
		Scanner scanProject = new Scanner(System.in);
		System.out.println("\nTo create a new project, fill in the details below:");
		System.out.println("\nProject Name:");
		String name = scanProject.nextLine();
		project.setProjectName(name);
		System.out.println("\nProject Number: ");
		String number = scanProject.nextLine();
		project.setProjectNumber(number);
		System.out.println("\nType of Building: ");
		String buildingtype = scanProject.nextLine();
		project.setBuildingType(buildingtype);
		System.out.println("\nBuilding's Physical Address: ");
		String address = scanProject.nextLine();
		project.setPhysicalAddress(address);
		System.out.println("\nERF Number: ");
		String erf = scanProject.nextLine();	
		project.setNumERF(erf);
		try {
		System.out.println("\nTotal Fee: ");
		double totalfee = scanProject.nextDouble();	
		project.setTotalFee(totalfee);
		}
		catch (InputMismatchException ex) {
			scanProject.next();
			System.out.println("Incorrect Input, Please input number");
			System.out.println("\nTotal Fee: ");
			double totalfee = scanProject.nextDouble();	
			project.setTotalFee(totalfee);
		}
		
		scanProject.nextLine();	//throw away the \n not consumed by nextInt()
		try {
		System.out.println("\nTotal Amount Paid to Date: ");
		double paid = scanProject.nextDouble();
		project.setFeePaidToDate(paid);
		}
		catch (InputMismatchException ex) {
			scanProject.next();
			System.out.println("Incorrect Input, Please input number");
			System.out.println("\nTotal Amount Paid to Date: ");
			double paid = scanProject.nextDouble();
			project.setFeePaidToDate(paid);
		}
				
		scanProject.nextLine();		
		System.out.println("\nDeadline (dd/MM/yyyy): ");
		String deadline = scanProject.nextLine();
		project.setDeadline(deadline);
		//Assuming that new project will not be completed
		String finalized = "Incomplete";
		project.setFinalized(finalized.toLowerCase());
		System.out.println("\nArchitect Details:");
		System.out.println("Name: ");
		String nameA = scanProject.nextLine();
		project.setArchName(nameA);
		System.out.println("\nTelephone Number: ");
		String numA = scanProject.nextLine();
		project.setArchNumber(numA);
		System.out.println("\nEmail Address: ");
		String emailA = scanProject.nextLine();
		project.setArchEmail(emailA);
		System.out.println("\nPhysical Address: ");
		String addressA = scanProject.nextLine();	
		project.setArchAddress(addressA);
		System.out.println("\nContractor Details:");
		System.out.println("Name: ");
		String nameCt = scanProject.nextLine();
		project.setContName(nameCt);
		System.out.println("\nTelephone Number: ");
		String numCt = scanProject.nextLine();
		project.setContNumber(numCt);
		System.out.println("\nEmail Address: ");
		String emailCt = scanProject.nextLine();
		project.setContEmail(emailCt);
		System.out.println("\nPhysical Address: ");
		String addressCt = scanProject.nextLine();
		project.setContAddress(addressCt);
		System.out.println("\nCustomer Details:");
		System.out.println("Name: ");
		String nameCust= scanProject.nextLine();
		project.setCustName(nameCust);
		System.out.println("\nTelephone Number: ");
		String numCust = scanProject.nextLine();
		project.setCustNumber(numCust);
		System.out.println("\nEmail Address: ");
		String emailCust = scanProject.nextLine();
		project.setcustEmail(emailCust);
		System.out.println("\nPhysical Address: ");
		String addressCust = scanProject.nextLine();
		project.setCustAddress(addressCust);
		
		/* If project name was left blank, give it name of building type and customer's last name
		 * Create an array to store the customers first and last name as index 0 and 1, Set Project Name accordingly
		 */
		if (project.getProjectName().isEmpty()) {
			String [] custNameSplit = project.getCustName().split(" ");
			project.setProjectName(project.getbuildingtype() + " " + custNameSplit[1]);
		}
		
		ProjectDetails projectToFile = new ProjectDetails(project.getProjectName(), project.getProjectNumber(), project.getbuildingtype(), 
				project.getPhysicalAddress(), project.getNumERF(), project.getTotalFee(), project.getFeePaidToDate(), project.getDeadline(), project.getFinalized(),
				project.getArchName(), project.getArchNumber(), project.getArchEmail(), project.getArchAddress(), 
				project.getCustName(), project.getCustNumber(), project.getcustEmail(), project.getCustAddress(), 
				project.getContName(),project.getContNumber(), project.getContEmail(), project.getContAddress());
		
		/*Write all project information to a file called projectDetails
		 * Return the object so that it can be added to the arrayList created each time a new object is created
		 */ 
		try {
			FileOutputStream newFile = new FileOutputStream(filepath, true);
			PrintWriter pw = new PrintWriter(newFile);
			pw.println(projectToFile.writeToFile());
			pw.close();
		} 
		catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		
		return projectToFile; 
	}

	/**Method will read objects from file into an array.
	 * @param inputfile The only parameter of ReadFile method
	 * @return Returns an ArrayList of objects
	 * @throws FileNotFoundException Throws on file not found
	 */
	public static ArrayList<ProjectDetails> ReadFile(String inputfile) throws FileNotFoundException {
		
		ArrayList<ProjectDetails> projectObjectList = new ArrayList<ProjectDetails>();
		File myFile = new File(inputfile);
		Scanner sc = new Scanner(myFile);
		while (sc.hasNextLine()) {
			try {
				String[] line = sc.nextLine().split(",");
				
				// Add Object Project to temporary array (Line) to create object, add object to array list
				ProjectDetails project = new ProjectDetails(line[0], line[1], line[2], line[3], line[4], 
		            		Double.parseDouble(line[5]), Double.parseDouble(line[6]), line[7], line[8], 
		            		line[9], line[10], line[11], line[12], 
		            		line[13], line[14], line[15], line[16], 
		            		line[17], line[18], line[19], line[20]);
				
		        projectObjectList.add(project);
		        } 
			catch (Exception e) {
				System.out.println("No Projects Found");
				}
			}
		
		return projectObjectList;
		}

	/** Method will append the completed project to a document called Completed Project and mark it as finalized.
	 * @param objectName The only parameter for the completedProjectPrint method.
	 * @throws FileNotFoundException Throws if file not found.
	 */
	public static void completedProjectPrint(ProjectDetails objectName) throws FileNotFoundException {
		
		String printToDoc = ("Project Details:" + "\nCompletion date: " + today);
		try {
			FileOutputStream newFile = new FileOutputStream("CompletedProject.txt", true);
			PrintWriter pw = new PrintWriter(newFile);
			pw.println(printToDoc);
			pw.println(objectName);
			pw.println("\n");
			pw.close();
			} 
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			}
		}
	
	/** Method will enable the user to change the customer details,
	 * Based on project name parameter that was selected in main menu.
	 * Object setter is used to change variable value to a new value in the ArrayList.
	 * @param objectName The object name.
	 */
	public static void changeCustomerDetails(ProjectDetails objectName) {
		
	    String input = "";
	    Scanner custIn = new Scanner(System.in);
	    while(!input.equals("5")) {
			System.out.println("\n    CUSTOMER: EDIT MENU: "
			+ "\n-------------------------------"
			+ "\n1 - Customer Name"
			+ "\n2 - Customer Email Address"
			+ "\n3 - Customer Telephone Number"
			+ "\n4 - Customer Physical Address"
			+ "\n5 - Exit to Edit Menu"
			+ "\n-------------------------------");
			System.out.println("\nWhat would you like to edit: ");
			String custMenu = custIn.nextLine();
			
			// Use if else statements to edit the class variables of the contractor using setters
			if (custMenu.equals("1")) {
				System.out.println("\nPlease enter new customer name: ");
				String newCustName = custIn.nextLine();
				objectName.setCustName(newCustName);
				System.out.println("\n-----------------------------" +
		           		   "\n      Customer Name Updated");
				}
		
			else if (custMenu.equals("2")) {
				System.out.println("\nPlease enter new customer email address: ");
				String newCustEmail = custIn.nextLine();
				objectName.setcustEmail(newCustEmail);
				System.out.println("\n-----------------------------" +
		           		   "\n      Customer Email Updated");
				}
		
			else if (custMenu.equals("3")) {
				System.out.println("\nPlease enter new customer telephone number: ");
				String newNumCust = custIn.nextLine();
				objectName.setCustNumber(newNumCust);
				System.out.println("\n-----------------------------" +
		           		   "\n      Customer Number Updated");
				}
		
			else if (custMenu.equals("4")){
				System.out.println("\nPlease enter new customer physical address: ");
				String newCusAddr = custIn.nextLine();
				objectName.setCustAddress(newCusAddr);
				System.out.println("\n-----------------------------" +
		           		   "\n      Customer Address Updated");
				}
		
			else if (custMenu.equals("5")) {
				break;
				}
			}
		}
	
	/** This method will edit all architect details
	 * Based on project name parameter that was selected in main menu,
	 * Object setter is used to change variable value to a new value in the ArrayList.
	 * @param objectName The object name.
	 */
	public static void changeArchitectDetails(ProjectDetails objectName) {
		
	    String input = "";
	    Scanner archIn = new Scanner(System.in);
	    while(!input.equals("5")) {
			System.out.println("\n    ARCHITECT: EDIT MENU: "
					         + "\n-------------------------------"
					         + "\n1 - Architect Name"
					         + "\n2 - Architect Email Address"
					         + "\n3 - Architect Telephone number"
					         + "\n4 - Architect Physical Address"
					         + "\n5 - Exit to Edit Menu"
					         + "\n-------------------------------");
			System.out.println("What would you like to edit: ");
			String archMenu = archIn.nextLine();
			
			// Use if else statements to edit the class variables of the contractor using setters
			if (archMenu.equals("1")) {
				System.out.println("\nPlease enter new architect name: ");
				String newAname = archIn.nextLine();
				objectName.setArchName(newAname);
				System.out.println("\n-------------------------------"+
		           		   "\n      Architect Name Updated");
				}
			
			else if (archMenu.equals("2")) {
				System.out.println("\nPlease enter new architect email address: ");
				String newAemail = archIn.nextLine();
				objectName.setArchEmail(newAemail);
				System.out.println("\n-------------------------------" +
		           		   "\n      Architect Email Updated");
				}
			
			else if (archMenu.equals("3")) {
				System.out.println("\nPlease enter new architect telephone number: ");
				String newAnum = archIn.nextLine();
				objectName.setArchNumber(newAnum);
				System.out.println("\n-------------------------------" +
		           		   "\n      Architect Number Updated");
				}
			
			else if (archMenu.equals("4")){
				System.out.println("\nPlease enter new architect physical address: ");
				String newAaddr = archIn.nextLine();
				objectName.setArchAddress(newAaddr);
				System.out.println("\n-------------------------------" +
		           		   "\n     Architect Address Updated");
				}
			
			else if (archMenu.equals("5")) {
				break;
				}
			}
		}
	
	/** This method will edit all architect details,
	 * Based on project name parameter that was selected in main menu.
	 * Object setter is used to change variable value to a new value in the ArrayList.
	 * @param objectName The object name.
	 */
	public static void changeContractorDetails(ProjectDetails objectName) {
		
		String input = "";
		Scanner conIn = new Scanner(System.in);
		while(!input.equals("5")) {

			System.out.println("\n    CONTRACTOR: EDIT MENU: "
				           	 + "\n-------------------------------"
					         + "\n1 - Contractor Name"
					         + "\n2 - Contractor Email Address"
					         + "\n3 - Contractor Telephone number"
					         + "\n4 - Contractor Physical Address"
					         + "\n5 - Exit to Edit Menu"
					         + "\n-------------------------------");
			System.out.println("\nWhat would you like to edit: ");
			String contractorMenu = conIn.nextLine();
			
			// Use if else statements to edit the class variables of the contractor using setters
			if (contractorMenu.equals("1")) {
				System.out.println("\nPlease enter new contractor name: ");
				String newNameCon = conIn.nextLine();
				objectName.setContName(newNameCon);
				System.out.println(newNameCon);
				System.out.println("\n-------------------------------"+
		           		   "\n      Contractor Name Updated");
				}
			
			else if (contractorMenu.equals("2")) {;
				System.out.println("\nPlease enter new contractor email address: ");
				String newConEmail = conIn.nextLine();
				objectName.setContEmail(newConEmail);
				System.out.println("\n-------------------------------" +
		           		   "\n      Contractor Email Updated");
				}
			
			else if (contractorMenu.equals("3")) {
				System.out.println("\nPlease enter new contractor telephone number: ");
				String newConNumber = conIn.nextLine();
				objectName.setContNumber(newConNumber);
				System.out.println("\n-------------------------------" +
		           		   "\n      Contractor Number Updated");
				
				}
			
			else if (contractorMenu.equals("4")){
				System.out.println("\nPlease enter new contractor physical address: ");
				String newConAddress = conIn.nextLine();
				objectName.setContAddress(newConAddress);
				System.out.println("\n-------------------------------" +
							"\n      Contractor Address Updated");
				}
			
			else if (contractorMenu.equals("5")) {
				break;
				}
			}
		}
	
	/** This Method will search the ArrayList created and return the index of the project name.
	 * @param listName The list containing all objects. 
	 * @param projectName The object name
	 * @return Returns the index of the object identity within the ArrayList.
	 */
	public static int searchList(ArrayList<ProjectDetails> listName, String projectName) {
		
		for(int i = 0; i < listName.size(); i++) {
			if (listName.get(i).getProjectName().equals(projectName)) {
				return i;
				}
			}	
		return -1; 
		}
	
	/** Method writes the final changed array to a file when exiting program.
	 * @param filepath The path of the text file.
	 * @param listname The list containing all objects.
	 * @throws FileNotFoundException If input or output exception occurred.
	 */
	public static void save(String filepath, ArrayList<ProjectDetails> listname) throws FileNotFoundException {
		
		try {
			FileOutputStream fileOut = new FileOutputStream(filepath);
			PrintWriter writer = new PrintWriter(fileOut);
			// Loop through each project in list, and write each one to the file.
			for (int i = 0; i < listname.size(); i++) {
				ProjectDetails project = (listname.get(i));
				writer.println(project.writeToFile());
				}
			
			writer.close();
			} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			}
		}
	
	/**Method will print out the name of each project for user to choose from.
	 * @throws FileNotFoundException If input or output exception occurred.
	 */
	public static void eachProjectName() throws FileNotFoundException {
		
		File projDetFile = new File(filepath);
		Scanner sc = new Scanner(projDetFile);
		System.out.println("\n          LIST OF PROJECTS: " +
				"\n------------------------------------------");
		while (sc.hasNextLine()) {
			try {
				String[] line = sc.nextLine().split(",");
				System.out.println(line[0]);
			    }
			catch (Exception e) {
				System.out.println("No Projects Found ");
				}
			}
		}
	
	/** Main Method
	 * @param args Not used.
	 * @throws FileNotFoundException If input or output exception occurred.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		/* Instantiate ArrayList with all object from file
		 * For each Menu choice, implementing its defined method based on user choice
		 */
		ArrayList<ProjectDetails> projectList = ReadFile(filepath);
		String input = "";
		Scanner menuIn = new Scanner(System.in);
		while(!input.equals("6")) {
			System.out.println("\n        WELCOME TO POISED");
			System.out.println("    A Project Management System"
					+ "\n-----------------------------------");
			System.out.println("\n	 POISED MENU"
					         + "\n-----------------------------------"
					         + "\n 1 - Create New Project"
					         + "\n 2 - Edit Details of Project"
					         + "\n 3 - Finalize Project"
					         + "\n 4 - List Incomplete Projects"
					         + "\n 5 - List Projects Past Deadline"
					         + "\n 6 - Quit"
					         + "\n-----------------------------------");
			System.out.println("\nPlease choose number from the menu above: ");
			String menuChoice = menuIn.nextLine();
			
			// If Menu Choice is 1, get input from user -> Call the createProject function to create a new project and add it to your list
			if (menuChoice.equals("1")) {
				projectList.add(createProject());
				System.out.println("\nYou have successfully created a new project\n");
				}
			
			// If the menuChoice is 2, enable the user to edit details of the project they searched for
			else if (menuChoice.equals("2")) {
				eachProjectName();
				Scanner editScan = new Scanner(System.in);
				System.out.println("\nPlease enter project name you would like to edit: ");
				String projNameInput = editScan.nextLine();
				
				// By calling the searchList method, you find the index of the project name in your projectList, and get the project at that index 
				int index = (searchList(projectList, projNameInput));
				
				// If project name is not correct/found, -1 index will be returned which will cause an Index Out of Bounds error
				if (index == -1) {
					System.out.println("\n        NO PROJECT FOUND"
							+ "\n-----------------------------------");
					continue;
				}
				
				ProjectDetails editProject = projectList.get(index);
				System.out.println("\n         PROJECT DETAILS:" 
				+ "\n-------------------------------");
				System.out.println(editProject);
				while(!input.equals("6")) {
					System.out.println("\n        EDIT MENU: " 
							+ "\n-------------------------------"
						    + "\n1 - Total Amount Paid to Date"
						    + "\n2 - Project Due Date"
						    + "\n3 - Contractor Details"
						    + "\n4 - Architect Details"
						    + "\n5 - Customer Details"
					        + "\n6 - Exit to Main Menu"
						    + "\n-------------------------------");
					System.out.println("\nWhat would you like to edit: ");
					String editDetails = editScan.nextLine();
					
					// If edit details is 1, change the amount paid to date, using fee paid to date setter 
					if (editDetails.equals("1")) {
						try {
							Scanner scan3 = new Scanner(System.in);
							System.out.println("Please enter the new Amount Paid to Date: ");
							Double newAmount = scan3.nextDouble();
							editProject.setFeePaidToDate(newAmount);
							}
						catch (InputMismatchException ex) {
							Scanner scancatch = new Scanner(System.in);
							System.out.println("Incorrect input, please input number");
							System.out.println("Please enter the new Amount Paid to Date: ");
							Double newAmount = scancatch.nextDouble();
							editProject.setFeePaidToDate(newAmount);
							}
						
						System.out.println("\n-------------------------------" +
										   "\n      Amount Paid Updated");
						}
					
					// If edit details is 2, change the deadline by using deadline setter
					else if (editDetails.equals("2")) {
						Scanner scan4 = new Scanner(System.in);
						System.out.println("Please enter new Deadline for the project (dd/MM/yyyy): ");
						String newDeadline = scan4.nextLine();
						editProject.setDeadline(newDeadline);
						System.out.println("\n-------------------------------" +
								           "\n      Deadline Updated");
						}
				
					else if (editDetails.equals("3")) {
						changeContractorDetails(editProject);
						}
					
					else if (editDetails.equals("4")) {
						changeArchitectDetails(editProject);
						}
				
					else if (editDetails.equals("5")) {
						changeCustomerDetails(editProject);	
						}
					
					else if (editDetails.equals("6")) {
						break;
						}
					}
				}
			
			/* If  3 is chosen for menuChoice - finalize the project.
			* Create an invoice with the Customer name and contact Details, owing amount. If paid full fee, no invoice must be created
			*/
			else if (menuChoice.equals("3")) {
				eachProjectName();
				Scanner finalScan = new Scanner(System.in);
				System.out.println("Please enter name of project you would like to finalize: ");
				String projectname = finalScan.nextLine();
				
				//Find the index of the project you are finalizing with it's name using your search method
				int index = (searchList(projectList, projectname));
				
				// If project name is not correct/found, -1 index will be returned which will cause an Index Out of Bounds error
				if (index == -1) {
					System.out.println("\n        NO PROJECT FOUND"
							+ "\n-----------------------------------");
					continue;
				}
				
				ProjectDetails finalizeProject = projectList.get(index);
				System.out.println(finalizeProject);
				
				//Set selected project to completed
				finalizeProject.setFinalized("completed");
				double outstandingAmount = finalizeProject.getTotalFee() - finalizeProject.getFeePaidToDate();
				if (outstandingAmount > 0) {
					System.out.println("\n              POISED INVOICE: "
								     + "\n---------------------------------------------------"
									 + "\nCustomer: " + finalizeProject.getCustName()
									 + "\nTelephone Number: " + finalizeProject.getCustNumber()
									 + "\nEmail Address: " + finalizeProject.getcustEmail()
									 + "\nTotal Outstanding Amount: R" + outstandingAmount
									 + "\nProject Status: Finalized"
									 + "\nCompletion Date: " + today
								     + "\n---------------------------------------------------\n");
					
					// Call method to write completed project to Text File
					completedProjectPrint(finalizeProject);
					System.out.println("\nYour Invoice has been generated, and project has been marked as finalized\n");
					}

				else {
					System.out.println(finalizeProject.getCustName() + " has paid in full"  + "\nYour project has been finalized\n");
					completedProjectPrint(finalizeProject);
					}
				}
			
			// If Menu Choice is 4, List all projects that are incomplete
			else if (menuChoice.equals("4")) {
					System.out.println("\n       LIST OF INCOMPLETE PROJECTS: " +
							"\n---------------------------------------------------\n");
					
					//Loop through the Project List Array to find index of every project marked incomplete and display it
					for(int i = 0; i < projectList.size(); i++) {
						if (projectList.get(i).getFinalized().equals("incomplete")) {
							int index = i;
							ProjectDetails incompleteproject = projectList.get(index);
							System.out.println(incompleteproject);
							System.out.println("\n");
							}
						}
					} 
			
			// If Menu Choice is 5, Display all projects that are past Due Date
			else if (menuChoice.equals("5")) {
				File myFile = new File(filepath);
				Scanner sc = new Scanner(myFile);
				System.out.println("\n       LIST OF OVERDUE PROJECTS: " +
						"\n---------------------------------------------------\n");
				while (sc.hasNextLine()) {
					try {
						String[] line = sc.nextLine().split(",");
						
						// Convert string date from file to actual date
					    String stringDate = line[7]; 
					    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
					    String completed = line[8];
					    
					    // If string date is after todays date, the project is past due, display project 
						if (today.after(date1) && completed.contentEquals("incomplete")) {
							ProjectDetails pastdue = new ProjectDetails(line[0], line[1], line[2], line[3], line[4],
				            		Double.parseDouble(line[5]), Double.parseDouble(line[6]), line[7], line[8], 
				            		line[9], line[10], line[11], line[12], 
				            		line[13], line[14], line[15], line[16], 
				            		line[17], line[18], line[19], line[20]);
							System.out.println(pastdue + "\n");
							}
					    }
					catch (Exception e) {
						System.out.println("No Projects Found");
						}
					}
				}				

			// If menuChoice is 6 , terminate the program and call method to overwrite file with changed list
			else if (menuChoice.equals("6")) {
				save(filepath, projectList);
				System.out.println("You have quite the program");
				break;	
				}
			} 
		} // End of Main Method
	} // End of Class
//End of Program

