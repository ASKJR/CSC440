package beans;

import java.security.Timestamp;
import java.sql.Date;

public class ServiceProvided {

	private Provider provider;

	private Member member;

	private Service service;

	private Timestamp currentDate;

	private Date occurrenceDate;

	private String comments;
	
	public ServiceProvided(){}
	
	public ServiceProvided(
			Provider provider,
			Member member,
			Service service,
			Timestamp currentDate,
			Date occurrenceDate,
			String comments
			){
		this.provider = provider;
		this.member = member;
		this.service = service;
		this.currentDate = currentDate;
		this.occurrenceDate = occurrenceDate;
		this.comments = comments;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Timestamp getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Timestamp currentDate) {
		this.currentDate = currentDate;
	}

	public Date getOccurrenceDate() {
		return occurrenceDate;
	}

	public void setOccurrenceDate(Date occurrenceDate) {
		this.occurrenceDate = occurrenceDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	
}
