package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "answer", schema="se2_bertelli_savino_vinati")
@NamedQueries({
	@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a"),
	@NamedQuery(name="Answer.findByRequest", query="SELECT a FROM Answer a WHERE a.request.id = ?1")
})
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String comment;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datehour;

	@ManyToOne
	@JoinColumn(name="idrequest")
	private Request request;
	
	@ManyToOne
	@JoinColumn(name="idreplier")
	private User user;
	
	public Answer() {
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDatehour() {
		return datehour;
	}

	public void setDatehour(Date datehour) {
		this.datehour = datehour;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}