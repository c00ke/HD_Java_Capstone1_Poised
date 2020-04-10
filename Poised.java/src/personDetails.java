
public class personDetails {
	static String archName; 
	static int archTelNum; 
	static String archEmailAddr; 
	static String archPhysicalAddr;
	static String custName; 
	static int custTelNum; 
	static String custEmailAddr; 
	static String custPhysicalAddr;
	static String contName; 
	static int contTelNum; 
	static String contEmailAddr; 
	static String contPhysicalAddr; 
	
	public personDetails (String archName, int archTelNum, String archEmailAddr, String archPhysicalAddr, String custName, int custTelNum, String custEmailAddr, String custPhysicalAddr, String contName, int contTelNum, String contEmailAddr, String contPhysicalAddr) {
		
		this.archName = archName;
		this.archTelNum = archTelNum;
		this.archEmailAddr = archEmailAddr; 
		this.archPhysicalAddr = archPhysicalAddr;
		this.custName = custName;
		this.custTelNum = custTelNum;
		this.custEmailAddr = custEmailAddr; 
		this.custPhysicalAddr = custPhysicalAddr;
		this.contName = contName;
		this.contTelNum = contTelNum;
		this.contEmailAddr = contEmailAddr; 
		this.contPhysicalAddr = contPhysicalAddr;	
	}
	
	public String getArchName() {
		return archName;
	}
	
	public Integer getArchTelNum() {
		return archTelNum;
	}
	
	public String getArchEmailAddr() {
		return archEmailAddr;
	}
	
	public String getArchPhysicalAddr() {
		return archPhysicalAddr;
	}
	
	public String getCustName() {
		return custName;
	}
	
	public Integer getCustTelNum() {
		return custTelNum;
	}
	
	public String getCustEmailAddr() {
		return custEmailAddr;
	}
	
	public String getCustPhysicalAddr() {
		return custPhysicalAddr;
	}
	
	public String getContName() {
		return contName;
	}
	
	public Integer getContTelNum() {
		return contTelNum;
	}
	
	public String getContEmailAddr() {
		return contEmailAddr;
	}
	
	public String getContPhysicalAddr() {
		return contPhysicalAddr;
	}

	public String toString() {
		String output = "Architect: ";
		output += "Name: " + archName;
		output += "\nTelephone Number: 0" + archTelNum;
		output += "\nEmail Address: " + archEmailAddr;
		output += "\nPhysical Address: " + archPhysicalAddr;
		output = "Contractor: ";
		output += "Name: " + contName;
		output += "\nTelephone Number: 0" + contTelNum;
		output += "\nEmail Address: " + contEmailAddr;
		output += "\nPhysical Address: " + contPhysicalAddr;
		output += "Customer: ";
		output += "Name: " + custName;
		output += "\nTelephone Number: 0" + custTelNum;
		output += "\nEmail Address: " + custEmailAddr;
		output += "\nPhysical Address: " + custPhysicalAddr;
		
		return output;
	}
}
