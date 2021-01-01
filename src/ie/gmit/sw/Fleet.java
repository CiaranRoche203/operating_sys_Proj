package ie.gmit.sw;
//fleet class
public class Fleet {
	//variables
	String name;
	String age;
	String Id;
	
	String clubID;
	String Vendor;
	String Valuation;
	
	String lastKM;
	String lastDate;
	String nextKM;
	String currentKM;
	
	String serviceAgent;
	String kmMachine;
	String serverDesc;
	
	//constructor
	public Fleet(String name, String age, String id, String clubID, String vendor, String valuation, String lastKM,
			String lastDate, String nextKM, String currentKM, String serviceAgent, String kmMachine,
			String serverDesc) {
		super();
		this.name = name;
		this.age = age;
		Id = id;
		this.clubID = clubID;
		Vendor = vendor;
		Valuation = valuation;
		this.lastKM = lastKM;
		this.lastDate = lastDate;
		this.nextKM = nextKM;
		this.currentKM = currentKM;
		this.serviceAgent = serviceAgent;
		this.kmMachine = kmMachine;
		this.serverDesc = serverDesc;
	}
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		this.Id = id;
	}
	public String getClubID() {
		return clubID;
	}
	public void setClubID(String clubID) {
		this.clubID = clubID;
	}
	public String getVendor() {
		return Vendor;
	}
	public void setVendor(String vendor) {
		this.Vendor = vendor;
	}
	public String getValuation() {
		return Valuation;
	}
	public void setValuation(String valuation) {
		this.Valuation = valuation;
	}
	public String getLastKM() {
		return lastKM;
	}
	public void setLastKM(String lastKM) {
		this.lastKM = lastKM;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getNextKM() {
		return nextKM;
	}
	public void setNextKM(String nextKM) {
		this.nextKM = nextKM;
	}
	public String getCurrentKM() {
		return currentKM;
	}
	public void setCurrentKM(String currentKM) {
		this.currentKM = currentKM;
	}
	public String getServiceAgent() {
		return serviceAgent;
	}
	public void setServiceAgent(String serviceAgent) {
		this.serviceAgent = serviceAgent;
	}
	public String getKmMachine() {
		return kmMachine;
	}
	public void setKmMachine(String kmMachine) {
		this.kmMachine = kmMachine;
	}
	public String getServerDesc() {
		return serverDesc;
	}
	public void setServerDesc(String serverDesc) {
		this.serverDesc = serverDesc;
	}
	
	
}