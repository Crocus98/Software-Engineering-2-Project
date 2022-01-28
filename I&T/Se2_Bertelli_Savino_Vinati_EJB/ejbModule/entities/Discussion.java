package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;



@Entity
@Table(name = "discussion", schema="se2_bertelli_savino_vinati")
@NamedQuery(name="Discussion.findAll", query="SELECT d FROM Discussion d")
public class Discussion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;

	@OneToOne
	@JoinColumn(name="idfarmer")
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datehour;
	
	@OneToMany(mappedBy="discussion", cascade = CascadeType.ALL)
	private List<Post> posts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDatehour() {
		return datehour;
	}

	public void setDatehour(Date datehour) {
		this.datehour = datehour;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	

	
	

}
