import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

//Level 2, Task 7 - Capstone Project 1
//Estine van der Berg, 7 April 2020 
public class Poised {

	public static void createProject() {
		// Get input from user for the project details
		// Create a scanner
				
		Scanner scan1 = new Scanner(System.in);
		System.out.println("\nTo create a new project, fill in the details below:");
		
		System.out.println("\nProject Name:");
		Project.projectName = scan1.nextLine();
		
		System.out.println("\nProject Number: ");
		Project.projectNumber = scan1.nextInt();
		scan1.nextLine();			//throw away the \n not consumed by nextInt()			
					
		System.out.println("\nType of Building: ");
		Project.buildingType = scan1.nextLine();
					
		System.out.println("\nBuilding's Physical Address: ");
		Project.physicalAddress = scan1.nextLine();
					
		System.out.println("\nERF Number: ");
		Project.numERF = scan1.nextInt();
					
		System.out.println("\nTotal Fee: ");
		Project.totalFee = scan1.nextDouble();
					
		System.out.println("\nTotal Amount Paid to Date: ");
		Project.feePaidToDate = scan1.nextDouble();
		scan1.nextLine();
					
		System.out.println("\nDeadline: ");
		Project.deadline = scan1.nextLine();
		
		// Get architect Details
					
		System.out.println("\nArchitect Details:");
		System.out.println("Name: ");
		personDetails.archName = scan1.nextLine();
					
		System.out.println("\nTelephone Number: ");
		personDetails.archTelNum = scan1.nextInt();
		scan1.nextLine();
					
		System.out.println("\nEmail Address: ");
		personDetails.archEmailAddr = scan1.nextLine();
					
		System.out.println("\nPhysical Address: ");
		personDetails.archPhysicalAddr = scan1.nextLine();
					
		// Get Contractor details
					
		System.out.println("\nContractor Details:");
		System.out.println("Name: ");
		personDetails.contName = scan1.nextLine();
					
		System.out.println("\nTelephone Number: ");
		personDetails.contTelNum= scan1.nextInt();
		scan1.nextLine();
					
		System.out.println("\nEmail Address: ");
		personDetails.contEmailAddr = scan1.nextLine();
					
		System.out.println("\nPhysical Address: ");
		personDetails.contPhysicalAddr = scan1.nextLine();
					
		// Get Customer Details
					
		System.out.println("\nCustomer Details:");
		System.out.println("Name: ");
		personDetails.custName = scan1.nextLine();
					
		System.out.println("\nTelephone Number: ");
		personDetails.custTelNum = scan1.nextInt();
		scan1.nextLine();
					
		System.out.println("\nEmail Address: ");
		personDetails.custEmailAddr = scan1.nextLine();
					
		System.out.println("\nPhysical Address: ");
		personDetails.custPhysicalAddr = scan1.nextLine();		
		
		System.out.println("Your Project has been created!\n");
	}

	public static void main(String[] args)  {
		
		//Create a while loop for the program so that you can return to the main menu
		while (true) {
	
			// Create a Menu for the User to choose from 
			System.out.println("Welcome to Poised");
			System.out.println("A Project Management System\n");
			
			//Create a scanner to capture users choice
			Scanner scan = new Scanner(System.in);
			
			System.out.println("\n	Poised Menu"
					         + "\n-----------------------------------"
					         + "\n 1 - Create New Project"
					         + "\n 2 - Edit Details of Project"
					         + "\n 3 - Finalize Project"
					         + "\n-----------------------------------");
			
			System.out.println("\nPlease choose from the menu above: ");
			int menuChoice = scan.nextInt();
			
			// If Menu Choice is 1, get input from user -> Call the createProject function
			if (menuChoice == 1) {
				createProject();
			}
				
			
			// If the menuChoice is 2, enable the user to edit details of the project
			// Give the user a choice of what they would like to edit
			else if (menuChoice == 2) {
				Scanner scan2 = new Scanner(System.in);
	
				// Give user a menu on what they would like to change about the project
				System.out.println("\nEdit Menu: "
					             + "\n -------------------------------"
					             + "\n1 - Total Amount Paid to Date"
					             + "\n2 - Project Due Date"
					             + "\n3 - Contractor Details"
					             + "\n4 - Architect Details"
					             + "\n5 - Customer Details"
				                 + "\n6 - Exit to Main Menu"
					             + "\n -------------------------------");
	
				System.out.println("What would you like to edit: ");
				int editDetails = scan2.nextInt();
				
				// If user chooses 1, Change the class variable of the fee paid to date
				if (editDetails == 1) {
					Scanner scan3 = new Scanner(System.in);
					System.out.println("Please enter the new Amount Paid to Date: ");
					Project.feePaidToDate = scan3.nextDouble();	
				}
				// If user chooses 2, Change the class variable of deadline
				else if (editDetails == 2) {
					Scanner scan4 = new Scanner(System.in);
					System.out.println("Please enter new Due Date for the project: ");
					Project.deadline = scan4.nextLine();
				}
				// If user chooses 3, give them a menu to choose from to change Contractor details
				else if (editDetails == 3) {
					Scanner scan5 = new Scanner (System.in);
					
					System.out.println("Edit Contractor Menu: "
						           	 + "\n -------------------------------"
							         + "\n1 - Contractor Name"
							         + "\n2 - Contractor Email Address"
							         + "\n3 - Contractor Telephone number"
							         + "\n4 - Contractor Physical Address"
							         + "\n5 - Exit to Main Menu"
							         + "\n -------------------------------\n");
					
					System.out.println("What would you like to edit: ");
					int contMenu = scan5.nextInt();
					
					// Use if else statements to edit the class variables of the contractor
					if (contMenu == 1) {
						Scanner conName = new Scanner(System.in);
						System.out.println("Please enter new contractor name: ");
						personDetails.contName = scan5.nextLine();
					}
					
					else if (contMenu ==2) {
						Scanner conEmail = new Scanner(System.in);
						System.out.println("Please enter new contractor email address: ");
						personDetails.contEmailAddr = conEmail.nextLine();
					}
					
					else if (contMenu == 3) {
						Scanner conTel= new Scanner(System.in);
						System.out.println("Please enter new contractor telephone number: ");
						personDetails.contTelNum = conTel.nextInt();
					}
					
					else {
						Scanner conAddr = new Scanner(System.in);
						System.out.println("Please enter new contractor physical address: ");
						personDetails.contPhysicalAddr = conAddr.nextLine();
					}
				}
				
				// If user select 4, give them a menu to choose from to change Architect details
				else if (editDetails == 4) {
					Scanner scan6 = new Scanner(System.in);
					
					System.out.println("Edit Architect Menu: "
							         + "\n -------------------------------"
							         + "\n1 - Architect Name"
							         + "\n2 - Architect Email Address"
							         + "\n3 - Architect Telephone number"
							         + "\n4 - Architect Physical Address"
							         + "\n5 - Exit to Main Menu"
							         + "\n -------------------------------\n");
					
					System.out.println("What would you like to edit: ");
					int contMenu = scan6.nextInt();
					
					// Use if else statements to edit the class variables of the contractor
					if (contMenu == 1) {
						Scanner aName = new Scanner(System.in);
						System.out.println("Please enter new Architect name: ");
						personDetails.archName = aName.nextLine();
					}
					
					else if (contMenu ==2) {
						Scanner aEmail = new Scanner(System.in);
						System.out.println("Please enter new Architect email address: ");
						personDetails.archEmailAddr = aEmail.nextLine();
					}
					
					else if (contMenu == 3) {
						Scanner aNum = new Scanner(System.in);
						System.out.println("Please enter new Architect telephone number: ");
						personDetails.archTelNum = aNum.nextInt();
					}
					
					else {
						Scanner aAddr = new Scanner(System.in);
						System.out.println("Please enter new Architect physical address: ");
						personDetails.archPhysicalAddr = aAddr.nextLine();
					}
				}
				// If user select 5, give them a menu to choose from to change Customer details
				else if (editDetails == 5) {
					Scanner scan7 = new Scanner(System.in);
					
					System.out.println("Edit Customer Menu: "
								     + "\n -------------------------------"
								     + "\n1 - Customer Name"
								     + "\n2 - Customer Email Address"
								     + "\n3 - Customer Telephone number"
							    	 + "\n4 - Customer Physical Address"
						     		 + "\n5 - Exit to Main Menu"
								     + "\n -------------------------------\n");
					
					System.out.println("What would you like to edit: ");
					int contMenu = scan7.nextInt();
					
					// Use if else statements to edit the class variables of the contractor
					if (contMenu == 1) {
						Scanner cusName = new Scanner(System.in);
						System.out.println("Please enter new Customer name: ");
						personDetails.custName = cusName.nextLine();
					}
					
					else if (contMenu ==2) {
						Scanner cusEmail = new Scanner(System.in);
						System.out.println("Please enter new Customer email address: ");
						personDetails.custEmailAddr = cusEmail.nextLine();
					}
					
					else if (contMenu == 3) {
						Scanner cusTel = new Scanner(System.in);
						System.out.println("Please enter new Customer telephone number: ");
						personDetails.custTelNum = cusTel.nextInt();
					}
					
					else {
						Scanner cusAddr = new Scanner(System.in);
						System.out.println("Please enter new Customer physical address: ");
						personDetails.custPhysicalAddr = cusAddr.nextLine();
					}
				}
			}
			// If  3 is chosen for menuChoice - finalize the project.
			else if (menuChoice == 3) {
				// Create an invoice with the Customer name and contact Details, owing amount. If paid full fee, no invoice must be created
				// Work out the amount owing for the invoice
				double outstandingAmount = Project.totalFee - Project.feePaidToDate;
				
				//Create a calendar instance and get the date from that instance; it defaults to "now"
				Date today = Calendar.getInstance().getTime();
				
				// If there is an outstanding amount, generate the invoice and print it out on the screen
				if (outstandingAmount > 0) {
					System.out.println("\nInvoice: "
								     + "\n---------------------------------------------------"
									 + "\nCustomer: " + personDetails.custName
									 + "\nTelephone Number: " + personDetails.custTelNum
									 + "\nEmail Address: " + personDetails.custEmailAddr
									 + "\nTotal Outstanding Amount: R" + outstandingAmount
									 + "\nProject Status: Finalized"
									 + "\nCompletion Date: " + today
								     + "\n---------------------------------------------------\n");
					
					// Add the project details to a file called Completed Project and mark as finalized
					String printToDoc = ("Project Details: "
							           + "\nProject Status: Finalized"
							           + "\nCompletion date: " + today);
					
					//Use class constructors for the project details
					Project project1 = new Project(Project.projectName, Project.projectNumber, Project.buildingType, Project.physicalAddress, Project.numERF, Project.totalFee, Project.feePaidToDate, Project.deadline );
					personDetails personDetails1 = new personDetails (personDetails.archName, personDetails.archTelNum, personDetails.archEmailAddr, personDetails.archPhysicalAddr, personDetails.custName, personDetails.custTelNum, personDetails.custEmailAddr, personDetails.custPhysicalAddr, personDetails.contName, personDetails.contTelNum, personDetails.contEmailAddr, personDetails.contPhysicalAddr);
					
					//Write all project information to a file called completed project, enclose it in a try, catch in case of exceptions
					FileOutputStream fos;
					try {
						fos = new FileOutputStream("CompletedProject.txt", true);
						PrintWriter pw = new PrintWriter(fos);
						pw.println(printToDoc);
						pw.println(project1);
						pw.println(personDetails1);
						pw.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					System.out.println("Your Invoice has been generated, and project has been marked as finalized\n");
				}
				
				// if there is no money outstanding:
				else {
					System.out.println(personDetails.custName + " has paid in full"
									   + "\nYour project has been finalized\n");
					
					// Add the project details to a file called Completed Project and mark as finalized
					String printToDoc = ("Project Details: "
							           + "\nProject Status: Finalized"
							           + "\nCompletion date: " + today);
					
					//Use class constructors for the project details
					Project project1 = new Project(Project.projectName, Project.projectNumber, Project.buildingType, Project.physicalAddress, Project.numERF, Project.totalFee, 
												   Project.feePaidToDate, Project.deadline );
					personDetails personDetails1 = new personDetails (personDetails.archName, personDetails.archTelNum, personDetails.archEmailAddr, personDetails.archPhysicalAddr, personDetails.custName, personDetails.custTelNum, 
																	  personDetails.custEmailAddr, personDetails.custPhysicalAddr, personDetails.contName, personDetails.contTelNum, personDetails.contEmailAddr, personDetails.contPhysicalAddr);
					
					//Write all project information to a file called completed project, enclose it in a try, catch in case of exceptions
					FileOutputStream fos;
					try {
						fos = new FileOutputStream("CompletedProject.txt", true);
						PrintWriter pw = new PrintWriter(fos);
						pw.println(printToDoc);
						pw.println(project1);
						pw.println(personDetails1);
						pw.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
					
			}
		}
	}
}
