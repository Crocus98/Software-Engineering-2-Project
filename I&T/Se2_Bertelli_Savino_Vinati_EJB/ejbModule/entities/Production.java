package entities;

import java.io.Serializable;
import javax.persistence.*;

import enums.TypeOfProduct;

import java.util.Date;


@Entity
@Table(name = "production", schema="se2_bertelli_savino_vinati")
@NamedQuery(name="Production.findAll", query="SELECT p FROM Production p")
public class Production implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int amount;

	private String comment;

	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne
	@JoinColumn(name="idfarm")
	private Farm farm;

	private TypeOfProduct typeofproduct;

	public Production() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public TypeOfProduct getTypeofproduct() {
		return typeofproduct;
	}

	public void setTypeofproduct(TypeOfProduct typeofproduct) {
		this.typeofproduct = typeofproduct;
	}

}