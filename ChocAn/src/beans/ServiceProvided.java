package beans;

import java.sql.Timestamp;
import java.sql.Date;

public class ServiceProvided {

	private Provider provider;

	private Member member;

	private Service service;

	private Timestamp currentDate;

	private Date occurrenceDate;

	private String comment;
	
	public ServiceProvided(){}
	
	public ServiceProvided(
			Provider provider,
			Member member,
			Service service,
			Timestamp currentDate,
			Date occurrenceDate,
			String comment
			){
		this.provider = provider;
		this.member = member;
		this.service = service;
		this.currentDate = currentDate;
		this.occurrenceDate = occurrenceDate;
		this.comment = comment;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
}
