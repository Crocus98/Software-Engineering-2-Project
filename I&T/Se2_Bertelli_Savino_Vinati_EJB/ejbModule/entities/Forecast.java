package entities;

import java.io.Serializable;
import javax.persistence.*;

import enums.Classification;

import java.util.Date;


@Entity
@Table(name = "forecast", schema="se2_bertelli_savino_vinati")
@NamedQuery(name="Forecast.findAll", query="SELECT f FROM Forecast f")
public class Forecast implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private Classification classification;

	@Temporal(TemporalType.DATE)
	private Date creationdate;

	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne
	@JoinColumn(name="idarea")
	private Area area;

	private int value;

	public Forecast() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationdate() {
		return this.creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

}