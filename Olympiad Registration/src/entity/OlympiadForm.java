package entity;

public class OlympiadForm {
	private String registrationNumber;
	private String name;
	private String dob;
	private String phoneNumber;
	private String institution;
	private String field;
	private long fee;
	
	
	
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getName() {
		return name;
	}

	public String setName(String name) {
		return this.name = name;
	}

	

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}


	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}


	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

	@Override
	public String toString() {
		return "OlympiadForm [registrationNumber=" + registrationNumber + ", name=" + name + ", dob=" + dob
				+ ", phoneNumber=" + phoneNumber + ", institution=" + institution + ", field=" + field + "]";
	}

	

}
	
	
	