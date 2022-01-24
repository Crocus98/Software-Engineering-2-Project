package entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "farm", schema="se2_bertelli_savino_vinati")
@NamedQuery(name="Farm.findAll", query="SELECT f FROM Farm f")
public class Farm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	private int dimension;
	
	@OneToOne
	@JoinColumn(name="idfarmer")
	private User user;

	@ManyToOne
	@JoinColumn(name="idarea")
	private Area area;

	public Farm() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getDimension() {
		return this.dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

}