
public class Project{
	
	//Attributes
	static String projectName;
	static int projectNumber;
	static String buildingType; 
	static String physicalAddress;
	static int numERF; 
	static double totalFee; 
	static double feePaidToDate; 
	static String deadline; 
	
	//Methods (getters and setters)
	public Project (String projectName, int projectNumber, String buildingType, String physicalAddress, int numERF, double totalFee, double feePaidToDate, String deadline) {
		this.projectName = projectName;
		this.projectNumber = projectNumber;
		this.buildingType = buildingType;
		this.physicalAddress = physicalAddress;
		this.numERF = numERF;
		this.totalFee = totalFee;
		this.feePaidToDate = feePaidToDate;
		this.deadline = deadline;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public Integer getProjectNumber() {
		return projectNumber;
	}
	
	public String getBuildingType() {
		return buildingType;
	}
	
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	
	public Integer getNumERF() {
		return numERF;
	}
	
	public Double getTotalFee() {
		return totalFee;
	}
	
	public Double getFeePaidToDate() {
		return feePaidToDate;
	}
	
	public String getDeadline() {
		return deadline;
	}
	
	public String toString() {
		String output = "Project Number: " + projectNumber;
		output += "\nProject Name: " + projectName;
		output += "\nBuilding Type: " + buildingType;
		output += "\nBuilding Address: " + physicalAddress;
		output += "\nERF Number: " + numERF; 
		output += "\nTotal Fee: R" + totalFee;
		output += "\nTotal amount Paid to Date: R" + feePaidToDate; 
		output += "\nDeadline: " + deadline;
		
		return output;
	}
}


