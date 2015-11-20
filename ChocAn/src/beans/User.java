package beans;

public class User {

	public static final int MANAGER = 1;
	public static final int PROVIDER = 2;
	public static final int OPERATOR = 3;
	public static final int MEMBER = 4;
	
	public static final int NOT_FOUND = -1;
	public static final int TYPE_ERROR = -2;
	
	private int idUser;
	private String stAddr;
	private String addrComp;
	private String city;
	private String state;
	private String zipCode;
	private String fstName;
	private String lstName;
	private String cellPhone;
	private String homePhone;
	private String workPhone;
	private String email;
	
	public User(){
		
	}
	
	public User(
			int idUser,
			String stAddr,
			String addrComp,
			String city,
			String state,
			String zipCode,
			String fstName,
			String lstName,
			String cellPhone,
			String homePhone,
			String workPhone,
			String email
			){
		this.idUser = idUser;
		this.stAddr = stAddr;
		this.addrComp = addrComp;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.fstName = fstName;
		this.lstName = lstName;
		this.cellPhone = cellPhone;
		this.homePhone = homePhone;
		this.workPhone = workPhone;
		this.email = email;
	}
	
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getStAddr() {
		return stAddr;
	}

	public void setStAddr(String stAddr) {
		this.stAddr = stAddr;
	}

	public String getAddrComp() {
		return addrComp;
	}

	public void setAddrComp(String addrComp) {
		this.addrComp = addrComp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getFstName() {
		return fstName;
	}

	public void setFstName(String fstName) {
		this.fstName = fstName;
	}

	public String getLstName() {
		return lstName;
	}

	public void setLstName(String lstName) {
		this.lstName = lstName;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
