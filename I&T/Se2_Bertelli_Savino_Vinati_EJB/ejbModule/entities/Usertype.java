package entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "usertype", schema="se2_bertelli_savino_vinati")
@NamedQuery(name="Usertype.findAll", query="SELECT u FROM Usertype u")
public class Usertype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;

	private String usertype;

	public Usertype() {
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

}