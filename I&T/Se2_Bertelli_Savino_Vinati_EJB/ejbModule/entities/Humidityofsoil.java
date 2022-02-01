package entities;

import java.io.Serializable;
import javax.persistence.*;

import enums.ClassificationH;

import java.util.Date;


@Entity
@Table(name = "humidityofsoil", schema="se2_bertelli_savino_vinati")
@NamedQueries({
	@NamedQuery(name="Humidityofsoil.findAll", query="SELECT h FROM Humidityofsoil h"),
	@NamedQuery(name="Humidityofsoil.findByData", query="SELECT h FROM Humidityofsoil h WHERE h.date = ?1 and h.farm.id = ?2")
})
public class Humidityofsoil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private ClassificationH classification;

	@ManyToOne
	@JoinColumn(name="idfarm")
	private Farm farm;

	public Humidityofsoil() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	public ClassificationH getClassification() {
		return classification;
	}

	public void setClassification(ClassificationH classification) {
		this.classification = classification;
	}

}