package ie.gmit.sw;
//sales class
public class Sales {
	//variables
	String name;
	String Id;
	//constructor
	public Sales(String name, String id) {
		super();
		this.name = name;
		Id = id;
	}
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
}