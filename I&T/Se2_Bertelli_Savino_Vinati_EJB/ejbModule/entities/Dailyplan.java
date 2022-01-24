package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "dailyplan", schema="se2_bertelli_savino_vinati")
@NamedQuery(name="Dailyplan.findAll", query="SELECT d FROM Dailyplan d")
public class Dailyplan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date date;
	
	@ManyToOne
	@JoinColumn(name="idagronomist")
	private User user;

	public Dailyplan() {
		
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


}