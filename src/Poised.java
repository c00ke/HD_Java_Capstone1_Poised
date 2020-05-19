import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**Level 3, Task 08 - Capstone Project
 * Poised - A Project Management Program for an Engineering Firm
* @author Estine van der Berg
* @since 03 May 2020 (12 May 2020 Last update)
* @version 3
*/
public class Poised {
	static String filepath = "./ProjectDetails.txt";
	static Date today = Calendar.getInstance().getTime();
		
	/** Main Method
	 * @param args Not used.
	 * @throws FileNotFoundException If input or output exception occurred.
	 */
	public static void main(String[] args) throws FileNotFoundException {
	
		String input = "";
		Scanner menuIn = new Scanner(System.in);
		while(!input.equals("7")) {
			System.out.println("\n           WELCOME TO POISED");
			System.out.println("       A Project Management System"
					+ "\n________________________________________________\n");
			System.out.println("\n	    POISED MENU"
					         + "\n_________________________________________________\n"
					         + "\n 1 - Create New Project"
					         + "\n 2 - Edit Details of Project"
					         + "\n 3 - Finalize Project"
					         + "\n 4 - List Incomplete Projects"
					         + "\n 5 - List Projects Past Deadline"
					         + "\n 6 - Search For Project"
					         + "\n 0 - Quit"
					         + "\n_________________________________________________\n");
			System.out.println("\nPlease choose number from the menu above: ");
			String menuChoice = menuIn.nextLine();
			
			// If Menu Choice is 1, get input from user -> Call the createProject function to create a new project and add it to your list
			if (menuChoice.equals("1")) {
				
				createProject();
				System.out.println("\nYou have successfully created a new project\n");
				}
			
			// If the menuChoice is 2, enable the user to edit details of the project they searched for
			else if (menuChoice.equals("2")) {
				
				String editDetails = "";
				Scanner editScan = new Scanner(System.in);
				System.out.println("\nPlease enter project number you would like to edit: ");
				int projNumInput = editScan.nextInt();
											
				System.out.println("\n         PROJECT DETAILS:" 
								 + "\n________________________________________________\n");
				searchProject(projNumInput);

				while(!editDetails.equals("6")) {
					System.out.println("\n             EDIT MENU: " 
									 + "\n_________________________________________________\n"
						             + "\n1 - Total Amount Paid to Date"
									 + "\n2 - Project Deadline"
						             + "\n3 - Engineer Details"
									 + "\n4 - Architect Details"
						             + "\n5 - Customer Details"
									 + "\n6 - Exit to Main Menu"
						             + "\n________________________________________________\n");
					System.out.println("\nWhat would you like to edit: ");
					editScan.nextLine();
					editDetails = editScan.nextLine();
					
					// If edit details is 1, change the amount paid to date, calling updateFeePaid Method
					if (editDetails.equals("1")) {
						
						try {
							Scanner scan3 = new Scanner(System.in);
							System.out.println("Please enter the new Amount Paid to Date: ");
							Double newAmount = editScan.nextDouble();
							
							//UpdateFeePaid will write new fee input by user to database
							updateFeePaid(newAmount, projNumInput);
							}catch (InputMismatchException ex) {
								Scanner scancatch = new Scanner(System.in);
								System.out.println("Incorrect input, please input number");
								System.out.println("Please enter the new Amount Paid to Date: ");
								Double newAmount = editScan.nextDouble();
								updateFeePaid(newAmount, projNumInput);
							}
						
						System.out.println("\n________________________________________________\n" +
										   "\n           Amount Paid Updated" +
										   "\n________________________________________________\n");
						}
				
					// If edit details is 2, change the deadline by using updateDeadline method
					else if (editDetails.equals("2")) {
						
						Scanner scan4 = new Scanner(System.in);
						System.out.println("Please enter new Deadline for the project (yyyy/mm/dd): ");
						String newDeadline = scan4.nextLine();
						
						//UpdateDeadline method will write new deadline to DB
						updateDeadline(newDeadline, projNumInput);
						System.out.println("\n________________________________________________\n" +
								           "\n           Deadline Updated" + 
								           "\n________________________________________________\n");
						}
				
					else if (editDetails.equals("3")) {
						
						changeEngineerDetails(projNumInput);
						}
					
					else if (editDetails.equals("4")) {
						
						changeArchitectDetails(projNumInput);
						}
				
					else if (editDetails.equals("5")) {
						
						changeCustomerDetails(projNumInput);	
						}
					
					else if (editDetails.equals("6")) {
						
						break;
						}
					}
				}
	
			/*If  3 is chosen for menuChoice - finalize the project.
			* Create an invoice with the Customer name and contact Details, owing amount. If paid full fee, no invoice must be created
			*/
			else if (menuChoice.equals("3")) {
				
				Scanner finalScan = new Scanner(System.in);
				System.out.println("Please enter number of project you would like to finalize: ");
				int projectNum = finalScan.nextInt();
				System.out.println("\n         PROJECT DETAILS:" 
						+ "\n________________________________________________\n");
				
				//SearchProject Prints out a specific project based on project number
				searchProject(projectNum);
				
				//Format Date into String 
				String newDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
				
				//Set project to completed by changing adding today's date and setting it as finalized
				setCompleted(newDate, projectNum);
				float outstandingAmount = outstandingAmount(projectNum);
				
				if (outstandingAmount > 0) {
					
					createInvoice(projectNum);
					System.out.println("\nYour Invoice has been generated, and project has been marked as finalized\n");
					}

				else {
					
					System.out.println("The Customer has paid in full"  + "\nYour project has been finalized\n");
					completedProjectPrint(projectNum);
					}
					
				}
				
			// If Menu Choice is 4, List all projects that are incomplete
			else if (menuChoice.equals("4")) {
				
					System.out.println("\n       LIST OF INCOMPLETE PROJECTS: " +
							"\n________________________________________________");
					listIncompleteProjects();
					}
					
			// If Menu Choice is 5, Display all projects that are past Due Date
			else if (menuChoice.equals("5")) {

				System.out.println("\n       LIST OF OVERDUE PROJECTS: " +
						"\n________________________________________________");
				listOverdueProjects();
				}
			
			// If Menu Choice is 6, Search for project and display details
			else if (menuChoice.contentEquals("6")) {
				
				Scanner scanProjName = new Scanner(System.in);
				System.out.println("Please Type Project Name to search for project: ");
				String projName = scanProjName.nextLine();
				
				System.out.println("\n       PROJECT DETAILS: " +
						"\n________________________________________________\n");
				SearchProjectName(projName);
			}
			
			// If menuChoice is 6 , terminate the program and call method to overwrite file with changed list
			else if (menuChoice.equals("0")) {
				
				System.out.println("You have quite the program");
				break;	
			}
		}
	}
	
	private static void SearchProjectName(String projName) {
		
		try {
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			ResultSet results;
						
			results = statement.executeQuery("SELECT * "
										   + "FROM project "
										   + "WHERE Proj_Name like '%" + projName + "%'"
										   );
			while (results.next()) {
				
				System.out.println("\nProject Number______ " + results.getInt("Proj_Num")
								 + "\nProject Name________ " + results.getString("Proj_Name") 
								 + "\nBuilding Type_______ " + results.getString("Building_Type") 
								 + "\nPhysical Address____ " + results.getString("Phys_Addr") 
								 + "\nERF Number__________ " + results.getString("ERF_Num") 
								 + "\nTotal Fee__________R " + results.getFloat("Total_Fee") 
								 + "\nTotal Paid_________R " + results.getFloat("Total_Paid") 
								 + "\nDeadline____________ " + results.getDate("Deadline") 
								 + "\nFinalized___________ " + results.getString("Proj_Complete")
						);
				searchContact(results.getInt("Proj_Num"));
				
				}
			
			results.close();
			statement.close();
			connection.close();
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	

	/** Method will list all overdue projects by checking if project is complete and if project is past deadline
	 */
	private static void listOverdueProjects() {
		
		try {
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			ResultSet results;
			String newDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
			
			results = statement.executeQuery("SELECT * "
										   + "FROM project "
										   + "WHERE Proj_Complete = 'incomplete'"
										   + "AND Deadline < '" + newDate + "'");
			while (results.next()) {
				
				System.out.println("\nProject Number______ " + results.getInt("Proj_Num")
								 + "\nProject Name________ " + results.getString("Proj_Name") 
								 + "\nBuilding Type_______ " + results.getString("Building_Type") 
								 + "\nPhysical Address____ " + results.getString("Phys_Addr") 
								 + "\nERF Number__________ " + results.getString("ERF_Num") 
								 + "\nTotal Fee__________R " + results.getFloat("Total_Fee") 
								 + "\nTotal Paid_________R " + results.getFloat("Total_Paid") 
								 + "\nDeadline____________ " + results.getDate("Deadline") 
								 + "\nFinalized___________ " + results.getString("Proj_Complete")
						);
				}
			
			results.close();
			statement.close();
			connection.close();
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	/**Method will list all incomplete project by checking for all projects with incomplete status
	 */
	private static void listIncompleteProjects() {
		
		try {
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			ResultSet results;
			
			results = statement.executeQuery("SELECT * FROM project WHERE proj_complete = 'incomplete'");
			
			while (results.next()) {
				
				System.out.println("\nProject Number______ " + results.getInt("Proj_Num")
								 + "\nProject Name________ " + results.getString("Proj_Name") 
								 + "\nBuilding Type_______ " + results.getString("Building_Type") 
								 + "\nPhysical Address____ " + results.getString("Phys_Addr") 
								 + "\nERF Number__________ " + results.getString("ERF_Num") 
								 + "\nTotal Fee__________R " + results.getFloat("Total_Fee") 
								 + "\nTotal Paid_________R " + results.getFloat("Total_Paid") 
								 + "\nDeadline____________ " + results.getDate("Deadline") 
								 + "\nFinalized___________ " + results.getString("Proj_Complete")
						);
				}
			
			results.close();
			statement.close();
			connection.close();
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	/** Method will Create an Invoice
	 * Working out Total Outstanding amount still owed with today's date
	 * @param projectNum The Project Number to be modified
	 */
	private static void createInvoice(int projectNum) {
		
		float totalOutstanding = outstandingAmount(projectNum);
		String newDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
		try {
			
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			ResultSet results;
			
			results = statement.executeQuery("SELECT * FROM customer WHERE proj_num = " + projectNum);
			
			while (results.next()) {
				
				System.out.println("\n              POISED INVOICE: "
					     + "\n________________________________________________"
						 + "\nCustomer______________________ " + results.getString("Customer_Name")
						 + "\nTelephone Number______________ " + results.getString("Number")
						 + "\nEmail Address_________________ " + results.getString("Email")
						 + "\nTotal Outstanding Amount_____R " + totalOutstanding
						 + "\nProject Status________________ Finalized"
						 + "\nCompletion Date_______________ " + newDate
					     +  "\n________________________________________________");
				}
			
			results.close();
			statement.close();
			connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	/** Method will print out project of choice
	 * @param projectNum The project Number to printed
	 */
	public static void completedProjectPrint(int projectNum) {
		
		//Print out All Project Details
		searchProject(projectNum);
		try {
			
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			ResultSet results;
			
			results = statement.executeQuery("SELECT * FROM contact WHERE Proj_Num = " + projectNum);
			
			while (results.next()) {
				System.out.println("\nProject Manager______ " + results.getString("Proj_Manager")
								 + "\nEngineer_____________ " + results.getString("Engineer_Name")
								 + "\nArchitect____________ " + results.getString("Architect_Name")
								 + "\nCustomer_____________ " + results.getString("Customer_Name")
								 );
				}
			
			results.close();
			statement.close();
			connection.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	/** This Method will work out the outstanding amount of project 
	 * by subtracting the total amount paid from the total fee
	 * @param projectNum Is used for finding project index in DB
	 * @return will return the outstanding amount
	 */
	private static float outstandingAmount(int projectNum) {
		
		float totalOwed = 0;
		try {
			
		//Connect to Server
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
				"otheruser", 
				"vytoyx");
		Statement statement = connection.createStatement();
		ResultSet results;
		
		results = statement.executeQuery("SELECT Total_Fee, Total_Paid FROM project WHERE Proj_Num = " + projectNum);
		
		while (results.next()) {
			
			float totalFee = results.getFloat("Total_Fee");
			float totalPaid = results.getFloat("Total_Paid");
			totalOwed = totalFee - totalPaid;
		}

		results.close();	
		statement.close();
		connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return totalOwed;
	}

	/** This Method will change the proj_complete column in the project table to finalized and
	 * add today's date to the date_compeleted column
	 * @param newDate Is used for today's date
	 * @param projectNum Is used to find index of project to change
	 */
	private static void setCompleted(String newDate, int projectNum) {
		
		try {
			
		//Connect to Server
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
				"otheruser", 
				"vytoyx");
		Statement statement = connection.createStatement();
		
		int query1 = statement.executeUpdate("UPDATE project SET Proj_Complete = 'finalized' " + " WHERE Proj_Num = " + projectNum);
		int query2 = statement.executeUpdate("UPDATE project SET Date_Complete = '" + newDate + "' WHERE Proj_Num = " + projectNum);
		
		if (query1 == 1 && query2 == 1) {
			System.out.println("\n**UPDATE COMPLETE!**");
		}
		else {
			System.out.println("\n**UPDATE INCOMPLETE!**");
		}
			
		statement.close();
		connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** This Method will update the project table with a new
	 * fee paid input by User
	 * @param newAmount This parameter will be used as the new fee paid
	 * @param projNumInput This Parameter is used for indexing the correct project
	 */
	private static void updateFeePaid(Double newAmount, int projNumInput) {
		
		try {
			
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			
			int query1 = statement.executeUpdate("UPDATE project SET Total_Paid = " + newAmount + "WHERE Proj_Num = " + projNumInput);
			System.out.println("\n**UPDATE COMPLETE!**");
			if (query1 == 0) {
				
				System.out.println("\n**UPDATE INCOMPLETE!**");
			}
				
			statement.close();
			connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	/** This Method will update all information for tables architect, engineer and customer
	 * @param tableName Used to identify which table 
	 * @param updateItem Used to identify which item to updated
	 * @param updateValue New value of item
	 * @param projNumInput Which project to update
	 */
	private static void updateContacts(String tableName, String updateItem, String updateValue, int projNumInput) {
		
		try {
			
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			
			int query1 = statement.executeUpdate("UPDATE " + tableName + " SET " + updateItem + " = '" + updateValue + 
					"' WHERE Proj_Num = " + projNumInput);
			System.out.println("\n**UPDATE COMPLETE!**");
			if (query1 == 0) {
				
				System.out.println("\n**UPDATE INCOMPLETE!**");
			}
				
			statement.close();
			connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	
	/** Method will enable the user to change the customer details,
	 * Based on project name parameter that was selected in main menu.
	 * @param projectNum The project Number
	 */
	public static void changeCustomerDetails(int projectNum) {
		
	    String input = "";
	    Scanner custIn = new Scanner(System.in);
		System.out.println("\n     CUSTOMER DETAILS"
				+ "\n________________________________________________");
	    
		// Method prints out project @ projuctNum input by user
	    searchCustomer(projectNum);
	    
	    while(!input.equals("5")) {
	    	
			System.out.println("\n    CUSTOMER: EDIT MENU: "
			+ "\n________________________________________________"
			+ "\n1 - Customer Name"
			+ "\n2 - Customer Email Address"
			+ "\n3 - Customer Telephone Number"
			+ "\n4 - Customer Physical Address"
			+ "\n5 - Exit to Edit Menu"
			+ "\n________________________________________________");
			System.out.println("\nWhat would you like to edit: ");
			String custMenu = custIn.nextLine();
			
			// Use if else statements to edit the table for customer in our DB
			if (custMenu.equals("1")) {
				
				System.out.println("\nPlease enter new customer name: ");
				String newCustName = custIn.nextLine();
				
				//Updates customer table to new inputs
				updateContacts("customer", "customer_name", newCustName, projectNum);
				System.out.println("\n________________________________________________" +
		           		   "\n      Customer Name Updated");
				}
		
			else if (custMenu.equals("2")) {
				
				System.out.println("\nPlease enter new customer email address: ");
				String newCustEmail = custIn.nextLine();
				updateContacts("customer", "email", newCustEmail, projectNum);
				System.out.println("\n________________________________________________" +
		           		   "\n      Customer Email Updated");
				}
		
			else if (custMenu.equals("3")) {
				
				System.out.println("\nPlease enter new customer telephone number: ");
				String newNumCust = custIn.nextLine();
				updateContacts("customer", "number", newNumCust, projectNum);
				System.out.println("\n________________________________________________" +
		           		   "\n      Customer Number Updated");
				}
		
			else if (custMenu.equals("4")){
				
				System.out.println("\nPlease enter new customer physical address: ");
				String newCusAddr = custIn.nextLine();
				updateContacts("customer", "address", newCusAddr, projectNum);
				System.out.println("\n________________________________________________" +
		           		   "\n      Customer Address Updated");
				}
		
			else if (custMenu.equals("5")) {
				break;
				}
			}
		}
	
	/** This method will edit all architect details
	 * Based on project name parameter that was selected in main menu,
	 * @param projNumInput The project number input by user
	 */
	public static void changeArchitectDetails(int projNumInput) {
		
		System.out.println("\n     ENGINEER DETAILS"
				+ "\n________________________________________________");
		searchArchitect(projNumInput);
	    String input = "";
	    Scanner archIn = new Scanner(System.in);
	    while(!input.equals("5")) {
	    	
			System.out.println("\n    ARCHITECT: EDIT MENU: "
					         + "\n________________________________________________"
					         + "\n1 - Architect Name"
					         + "\n2 - Architect Email Address"
					         + "\n3 - Architect Telephone number"
					         + "\n4 - Architect Physical Address"
					         + "\n5 - Exit to Edit Menu"
					         + "\n________________________________________________");
			System.out.println("What would you like to edit: ");
			String archMenu = archIn.nextLine();
			
			// Use if else statements to edit the class variables of the architect using setters
			if (archMenu.equals("1")) {
				
				System.out.println("\nPlease enter new architect name: ");
				String newAname = archIn.nextLine();
				updateContacts("architect", "architect_name", newAname, projNumInput);
				
				System.out.println("\n________________________________________________"+
		           		   "\n      Architect Name Updated");
				}
			
			else if (archMenu.equals("2")) {
				
				System.out.println("\nPlease enter new architect email address: ");
				String newAemail = archIn.nextLine();
				updateContacts("architect", "email", newAemail, projNumInput);
				
				System.out.println("\n________________________________________________" +
		           		   "\n      Architect Email Updated");
				}
			
			else if (archMenu.equals("3")) {
				
				System.out.println("\nPlease enter new architect telephone number: ");
				String newAnum = archIn.nextLine();
				updateContacts("architect", "number", newAnum, projNumInput);
				System.out.println("\n________________________________________________" +
		           		   "\n      Architect Number Updated");
				}
			
			else if (archMenu.equals("4")){
				
				System.out.println("\nPlease enter new architect physical address: ");
				String newAaddr = archIn.nextLine();
				updateContacts("architect", "address", newAaddr, projNumInput);
				System.out.println("\n________________________________________________" +
		           		   "\n     Architect Address Updated");
				}
			
			else if (archMenu.equals("5")) {
				
				break;
				}
			}
		}
	
	/** This method will edit all engineer details,
	 * Based on project name parameter that was selected in main menu.
	 * @param objectName The object name.
	 */
	public static void changeEngineerDetails(int projNumInput) {
				
		String input = "";
		Scanner conIn = new Scanner(System.in);
		System.out.println("\n     ENGINEER DETAILS"
				+ "\n________________________________________________");
		searchEngineer(projNumInput);
		while(!input.equals("5")) {

			System.out.println("\n    ENGINEER: EDIT MENU: "
				           	 + "\n________________________________________________"
					         + "\n1 - Engineer Name"
					         + "\n2 - Engineer Email Address"
					         + "\n3 - Engineer Telephone number"
					         + "\n4 - Engineer Physical Address"
					         + "\n5 - Exit to Edit Menu"
					         + "\n________________________________________________");
			System.out.println("\nWhat would you like to edit: ");
			String engineerMenu = conIn.nextLine();
			
			// Use if else statements to edit the class variables of the engineer using setters
			if (engineerMenu.equals("1")) {
				
				System.out.println("\nPlease enter new Engineer name: ");
				String newNameEng = conIn.nextLine();
				updateContacts("engineer", "engineer_name", newNameEng, projNumInput);
				System.out.println("\n________________________________________________\n"+
		           		   "\n      Engineer Name Updated" +
		           		   "\n________________________________________________\n");
				}
			
			else if (engineerMenu.equals("2")) {
				
				System.out.println("\nPlease enter new Engineer email address: ");
				String newEngEmail = conIn.nextLine();
				updateContacts("engineer", "email", newEngEmail, projNumInput);
				System.out.println("\n________________________________________________\n"+
		           		   "\n      Engineer Email Updated" +
		           		   "\n________________________________________________\n");
				}
			
			else if (engineerMenu.equals("3")) {
				
				System.out.println("\nPlease enter new Engineer telephone number: ");
				String newEngNumber = conIn.nextLine();
				updateContacts("engineer", "number", newEngNumber, projNumInput);
				System.out.println("\n________________________________________________\n" +
		           		   "\n      Engineer Number Updated" +
		           		   "\n________________________________________________\n");
				}
			
			else if (engineerMenu.equals("4")){
				
				System.out.println("\nPlease enter new Engineer physical address: ");
				String newEngAddress = conIn.nextLine();
				updateContacts("engineer", "address", newEngAddress, projNumInput);
				System.out.println("\n________________________________________________\n" +
							"\n      Engineer Address Updated" +
							"\n________________________________________________\n");
				}
			
			else if (engineerMenu.equals("5")) {
				
				break;
				}
			}
		}

	/** Method to get user input for the project, engineer, architect and customer details.
	 * @throws InputMismatchException Throws on input error.
	 */
	public static void createProject(){
		
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
		System.out.println("\nDeadline (yyyy/mm/dd): ");
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
		System.out.println("\nEngineer Details:");
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
		System.out.println("\nProject Manager: ");
		String projectManager = scanProject.nextLine();
		project.setProjectManager(projectManager);
		
		/* If project name was left blank, give it name of building type and customer's last name
		 * Create an array to store the customers first and last name as index 0 and 1, Set Project Name accordingly
		 */
		if (project.getProjectName().isEmpty()) {
			
			String [] custNameSplit = project.getCustName().split(" ");
			project.setProjectName(project.getbuildingtype() + " " + custNameSplit[1]);
		}
		
		ProjectDetails projectToDB = new ProjectDetails(project.getProjectName(), project.getProjectNumber(), project.getbuildingtype(), project.getPhysicalAddress(), 
				project.getNumERF(), project.getTotalFee(), project.getFeePaidToDate(), project.getDeadline(), project.getFinalized(), project.getProjectManager(),
				project.getArchName(), project.getArchNumber(), project.getArchEmail(), project.getArchAddress(), 
				project.getCustName(), project.getCustNumber(), project.getcustEmail(), project.getCustAddress(), 
				project.getContName(),project.getContNumber(), project.getContEmail(), project.getContAddress());
		
		//Write all project information to a DB called PoisePMS
		WriteToPoisePMS(projectToDB);
	}

	/** Store object created in createProject() into poisePMS_db
	 * Use Object to insert new rows into the tables in your DB
	 * @param projectToDB This is a Project Object
	 */
	public static void	WriteToPoisePMS(ProjectDetails projectToDB) {
		
		ProjectDetails project = projectToDB; 
		try {
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			
			int query1 = statement.executeUpdate("INSERT INTO project VALUES (" + project.getProjectNumber() + ", '" 
					+ project.getProjectName() + "', '" 
					+ project.getbuildingtype() + "', '"
					+ project.getPhysicalAddress() + "', '"
					+ project.getNumERF() + "', '"
					+ project.getTotalFee() + "', '"
					+ project.getFeePaidToDate() + "', '"
					+ project.getDeadline() + "', '"
					+ project.getFinalized() + "', "
					+ "null)"
					);
			
			int query2 = statement.executeUpdate("INSERT INTO contact VALUES (" + project.getProjectNumber() + ", '" 
					+ project.getProjectName() + "', '"
					+ project.getArchName() + "', '"
					+ project.getContName()  + "', '"
					+ project.getCustName() + "', '"
					+ project.getArchName() + "')"
					);
			
			int query3 = statement.executeUpdate("INSERT INTO Architect VALUES (" + project.getProjectNumber() + ", '"
					+ project.getArchName() + "', '"
					+ project.getArchNumber() + "', '"
					+ project.getArchEmail() + "', '"
					+ project.getArchAddress() + "')"
					);
			
			int query4 = statement.executeUpdate("INSERT INTO Engineer VALUES (" + project.getProjectNumber() + ", '"
					+ project.getContName() + "', '"
					+ project.getContNumber() + "', '"
					+ project.getContEmail() + "', '"
					+ project.getContAddress() + "')"
					);
			
			int query5 = statement.executeUpdate("INSERT INTO customer VALUES (" + project.getProjectNumber() + ", '"
					+ project.getCustName() + "', '"
					+ project.getCustNumber() + "', '"
					+ project.getcustEmail() + "', '"
					+ project.getCustAddress() + "')"
					);
					
			if (query1 == 1 && query2 == 1 && query3 == 1 && query4 == 1 && query5 == 1) {
				
				System.out.println("\n**NEW PROJECT ADDED!**");
			}
			
			else {
				
				System.out.println("\n**PROJECT FAILED TO ADD**");
			}
			
			statement.close();
			connection.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	/** This Method will search the ArrayList created and return the index of the project name.
	 * @param listName The list containing all objects. 
	 * @param projectName The object name
	 * @return Returns the index of the object identity within the ArrayList.
	 */
	public static void searchProject(int projectNum) {
		
		try {
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			ResultSet results;
			
			results = statement.executeQuery("SELECT * FROM project WHERE Proj_Num = " + projectNum);
		
			while (results.next()) {
				
				System.out.println("\nProject Number______ " + results.getInt("Proj_Num")
								 + "\nProject Name________ " + results.getString("Proj_Name") 
								 + "\nBuilding Type_______ " + results.getString("Building_Type") 
								 + "\nPhysical Address____ " + results.getString("Phys_Addr") 
								 + "\nERF Number__________ " + results.getString("ERF_Num") 
								 + "\nTotal Fee__________R " + results.getFloat("Total_Fee") 
								 + "\nTotal Paid_________R " + results.getFloat("Total_Paid") 
								 + "\nDeadline____________ " + results.getDate("Deadline") 
								 + "\nFinalized___________ " + results.getString("Proj_Complete")
						);
				}
			
			results.close();
			statement.close();
			connection.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	/** This method will search through the table engineer
	 * @param projectNum The Project Number to find architect index
	 */
	public static void searchEngineer(int projectNum) {
		
		try {
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			ResultSet results;
			
			results = statement.executeQuery("SELECT * FROM engineer WHERE Proj_Num = " + projectNum);
		
			while (results.next()) {
				
				System.out.println("\nProject Number______ " + results.getInt("Proj_Num")
						+ "\nEngineer Name________ " + results.getString("Engineer_Name") 
						+ "\nNumber_______ " + results.getString("Number") 
						+ "\nAddress____ " + results.getString("Address") 
						+ "\nEmail__________ " + results.getString("Email") 
						);
			}
			
			results.close();
			statement.close();
			connection.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	/** This method will search through the table customer
	 * @param projectNum The Project Number to find architect index
	 */
	private static void searchCustomer(int projectNum) {
		
		try {
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			ResultSet results;
			
			results = statement.executeQuery("SELECT * FROM customer WHERE Proj_Num = " + projectNum);
		
			while (results.next()) {
				
				System.out.println("\nProject Number______ " + results.getInt("Proj_Num")
						+ "\nCustomer Name________ " + results.getString("customer_Name") 
						+ "\nNumber_______ " + results.getString("Number") 
						+ "\nAddress____ " + results.getString("Address") 
						+ "\nEmail__________ " + results.getString("Email") 
						);
				}
			
			results.close();
			statement.close();
			connection.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	/** This method will search through the table architect
	 * @param projectNum The Project Number to find architect index
	 */
	private static void searchArchitect(int projectNum) {
		
		try {
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			ResultSet results;
			
			results = statement.executeQuery("SELECT * FROM architect WHERE Proj_Num = " + projectNum);
		
			while (results.next()) {
				
				System.out.println("\nProject Number______ " + results.getInt("Proj_Num")
						+ "\nArchitect Name________ " + results.getString("architect_Name") 
						+ "\nNumber_______ " + results.getString("Number") 
						+ "\nAddress____ " + results.getString("Address") 
						+ "\nEmail__________ " + results.getString("Email") 
						);
				}
			
			results.close();
			statement.close();
			connection.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	/** This method will search through the table contact
	 * @param projectNum The Project Number to find index
	 */
	private static void searchContact(int projectNum) {
		
		try {
			
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			ResultSet results;
			
			results = statement.executeQuery("SELECT * FROM contact WHERE Proj_Num = " + projectNum);
			
			while (results.next()) {
				System.out.println("\nProject Manager______ " + results.getString("Proj_Manager")
								 + "\nEngineer_____________ " + results.getString("Engineer_Name")
								 + "\nArchitect____________ " + results.getString("Architect_Name")
								 + "\nCustomer_____________ " + results.getString("Customer_Name")
								 );
				}
			
			results.close();
			statement.close();
			connection.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	/** Method will update the Deadline in the project table
	 * @param newDeadline The New Deadline input by user
	 * @param projNumInput The Project Number to edit input by user
	 */
	private static void updateDeadline(String newDeadline, int projNumInput) {
	
		try {
			//Connect to Server
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false", 
					"otheruser", 
					"vytoyx");
			Statement statement = connection.createStatement();
			
			int query1 = statement.executeUpdate("UPDATE project SET Deadline = '" + newDeadline + "' WHERE Proj_Num = " + projNumInput);
			System.out.println("\n**UPDATE COMPLETE!**");
			if (query1 == 0) {
				
				System.out.println("\n**UPDATE INCOMPLETE!**");
				}
				
			statement.close();
			connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
	
