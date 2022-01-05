package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "test", schema="se2_bertelli_savino_vinati")
@NamedQuery(name="Test.findAll", query="SELECT t FROM Test t") 
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String testvalue;


	public Test() {
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTestvalue() {
		return this.testvalue;
	}

	public void setTestvalue(String testvalue) {
		this.testvalue = testvalue;
	}
}