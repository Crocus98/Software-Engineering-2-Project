package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "post", schema="se2_bertelli_savino_vinati")
@NamedQueries({
	@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p"),
	@NamedQuery(name="Post.findByDiscussion", query="SELECT p FROM Post p WHERE p.discussion.id = ?1")
})
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="idfarmer")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="iddiscussion")
	private Discussion discussion;
	
	String comment;
	
	@Column(name="datehour", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datehour;
	
	public Post() {
		
	}
	
	public Post (User user, String comment) {
		this.setComment(comment);
		this.setUser(user);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Discussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
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
	
	

}
