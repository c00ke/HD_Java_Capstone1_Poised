// Class Project to be used to create project objects
public class ProjectDetails{
	private String projectName;
	private String projectNumber;
	private String buildingType; 
	private String physicalAddress;
	private String numERF; 
	private double totalFee; 
	private double feePaidToDate; 
	private String deadline; 
	private String archName; 
	private String archNumber; 
	private String archEmail; 
	private String archAddress;
	private String custName; 
	private String custNumber; 
	private	String custEmail; 
	private	String custAddress;
	private	String conName; 
	private	String conNumber; 
	private	String contEmail; 
	private	String contAddress;
	private	String finalized;
	
	// Constructor
	public ProjectDetails (String projectName, String projectNumber, String buildingType, String physicalAddress, String numERF, double totalFee, double feePaidToDate, String deadline, String finalized,
			String archName, String archNumber, String archEmail, String archAddress, 
			String custName, String custNumber, String custEmail, String custAddress, 
			String conName, String contNumber, String contEmail, String contAddress) {
		this.projectName = projectName;
		this.projectNumber = projectNumber;
		this.buildingType = buildingType;
		this.physicalAddress = physicalAddress;
		this.numERF = numERF;
		this.totalFee = totalFee;
		this.feePaidToDate = feePaidToDate;
		this.deadline = deadline;
		this.archName = archName;
		this.archEmail = archEmail;
		this.archNumber = archNumber;
		this.archAddress= archAddress;
		this.custName = custName;
		this.custEmail = custEmail;
		this.custNumber = custNumber;
		this.custAddress = custAddress;
		this.conName = conName;
		this.contEmail = contEmail;
		this.conNumber = contNumber;
		this.contAddress= contAddress;
		this.finalized = finalized;
	}
	
	public ProjectDetails() {
	}
	
	// Getters and Setters
	public String getProjectName() {
		return projectName;
	}
	
	public String setProjectName(String projectName) {
		return this.projectName = projectName;
	}
	
	public String getProjectNumber() {
		return projectNumber;
	}
	
	public String setProjectNumber(String projectNumber) {
		return this.projectNumber = projectNumber;
	}
	
	public String getbuildingtype() {
		return buildingType;
	}
	public String setBuildingType(String buildingType) {
		return this.buildingType = buildingType;
	}
	
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	
	public String setPhysicalAddress(String physicalAddress) {
		return this.physicalAddress = physicalAddress;
	}
	
	public String getNumERF() {
		return numERF;
	}
	
	public String setNumERF(String numERF) {
		return this.numERF= numERF;
	}
	
	public Double getTotalFee() {
		return totalFee;
	}
	
	public Double setTotalFee(double totalfee2) {
		return this.totalFee = totalfee2;
	}
	
	public Double getFeePaidToDate() {
		return feePaidToDate;
	}
	
	public Double setFeePaidToDate(double feePaidToDate) {
		return this.feePaidToDate = feePaidToDate;
	}
	
	public String getDeadline() {
		return deadline;
	}
	public String setDeadline(String deadline) {
		return this.deadline = deadline;
	}
	
	public String getFinalized() {
		return finalized;
		}
	
	public String setFinalized(String finalized) {
		return this.finalized = finalized;
	}
	
	public String getArchName() {
		return archName;
	}
	
	public String setArchName(String archName) {
		return this.archName = archName;
	}
	
	public String getArchNumber() {
		return archNumber;
	}
	
	public String setArchNumber(String archNumber) {
		return this.archNumber = archNumber;
	}
	
	public String getArchEmail() {
		return archEmail;
	}
	
	public String setArchEmail(String archEmail) {
		return this.archEmail = archEmail;
	}
	
	public String getArchAddress() {
		return archAddress;
	}
	
	public String setArchAddress(String archAddress) {
		return this.archAddress = archAddress;
	}
	
	public String getCustName() {
		return custName;
	}
	
	public String setCustName(String custName) {
		return this.custName = custName;
	}
	
	public String getCustNumber() {
		return custNumber;
	}
	
	public String setCustNumber(String custNumber) {
		return this.custNumber = custNumber;
	}
	
	public String getcustEmail() {
		return custEmail;
	}
	
	public String setcustEmail(String custEmail) {
		return this.custEmail = custEmail;
	}
	
	public String getCustAddress() {
		return custAddress;
	}
	
	public String setCustAddress(String custAddress) {
		return this.custAddress = custAddress;
	}
	
	public String getContName() {
		return conName;
	}
	
	public String setContName(String conName) {
		return this.conName = conName;
	}
	
	public String getContNumber() {
		return conNumber;
	}
	
	public String setContNumber(String contNumber) {
		return this.conNumber = contNumber;
	}
	
	public String getContEmail() {
		return contEmail;
	}
	
	public String setContEmail(String contEmail) {
		return this.contEmail = contEmail;
	}
	
	public String getContAddress() {
		return contAddress;
	}
	
	public String setContAddress(String contAddress) {
		return this.contAddress = contAddress;
	}
	
	// To String methods
	public String writeToFile() {
	
		String output = projectName + "," + projectNumber + "," + buildingType + "," + physicalAddress + "," + numERF + "," + totalFee + "," + feePaidToDate + "," + deadline + "," + finalized;
	    output += "," + archName + "," + archNumber + "," + archEmail + "," + archAddress;
	    output += "," + custName + "," + custNumber + "," + custEmail + "," + custAddress;
	    output += "," + conName + "," + conNumber + "," + contEmail + "," + contAddress; 
	    return output;
	}
	
	public String toString() {
		String output = "Project Name: " + projectName;
		output += "\nProject Number: " + projectNumber;
		output += "\nBuilding Type: " + buildingType;
		output += "\nBuilding Address: " + physicalAddress;
		output += "\nERF Number: " + numERF; 
		output += "\nTotal Fee: R" + totalFee;
		output += "\nTotal amount Paid to Date: R" + feePaidToDate; 
		output += "\nDeadline: " + deadline;
		output += "\nCompleted: " + finalized;
		output += "\nName: " + archName;
		output += "\nTelephone Number: 0" + archNumber;
		output += "\nEmail Address: " + archEmail;
		output += "\nPhysical Address: " + archAddress;
		output += "\nName: " + custName;
		output += "\nTelephone Number: 0" + custNumber;
		output += "\nEmail Address: " + custEmail;
		output += "\nPhysical Address: " + custAddress;
		output += "\nName: " + conName;
		output += "\nTelephone Number: " + conNumber;
		output += "\nEmail Address: " + contEmail;
		output += "\nPhysical Address: " + contAddress;
		return output;
	}
}




