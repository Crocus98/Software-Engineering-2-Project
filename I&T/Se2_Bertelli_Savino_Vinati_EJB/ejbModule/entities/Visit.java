package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "visit", schema="se2_bertelli_savino_vinati")
@NamedQuery(name="Visit.findAll", query="SELECT v FROM Visit v")
public class Visit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datehour;

	private String comment;
	
	private int state;
	
	@ManyToOne
	@JoinColumn(name="iddailyplan")
	private Dailyplan dailyplan;
	
	@ManyToOne
	@JoinColumn(name="idfarm")
	private Farm farm;
	
	public Visit() {
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatehour() {
		return datehour;
	}

	public void setDatehour(Date datehour) {
		this.datehour = datehour;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Dailyplan getDailyplan() {
		return dailyplan;
	}

	public void setDailyplan(Dailyplan dailyplan) {
		this.dailyplan = dailyplan;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}


}