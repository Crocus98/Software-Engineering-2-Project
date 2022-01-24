package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "humidityofsoil", schema="se2_bertelli_savino_vinati")
@NamedQuery(name="Humidityofsoil.findAll", query="SELECT h FROM Humidityofsoil h")
public class Humidityofsoil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int humidity;

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

	public int getHumidity() {
		return this.humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

}