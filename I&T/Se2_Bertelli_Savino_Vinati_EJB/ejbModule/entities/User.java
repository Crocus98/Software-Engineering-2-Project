package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import classes.Utility;
import enums.Usertype;

@Entity
@Table(name = "user", schema = "se2_bertelli_savino_vinati")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findAllFarmers", query = "SELECT u FROM User u WHERE u.usertype = enums.Usertype.Farmer"),
		@NamedQuery(name = "User.findAllFarmersPerArea", query = "SELECT u FROM User u WHERE u.farm.area.id = ?1"),
		@NamedQuery(name = "User.checkCredentials", query = "SELECT u FROM User u  WHERE u.mail = ?1 and u.password = ?2") })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String surname;

	private String mail;

	private String password;

	private Usertype usertype;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Farm farm;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Discussion> discussions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usertype getUsertype() {
		return usertype;
	}

	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	//Method compareTo useful to compare users (e.g. for sort function).
	public int compareTo(User user, Date date) {
		double value_this = this.getFarm().getProductionAmountM2(date);
		double value_that = user.getFarm().getProductionAmountM2(date);

		if (Utility.compare(value_this, value_that) == 0) {
			value_this = Utility.calculateEntropy(this.getFarm().getArea().getForecastsValue(date));
			value_that = Utility.calculateEntropy(user.getFarm().getArea().getForecastsValue(date));
			if (Utility.compare(value_that, value_this) == 0) {
				value_this = this.getFarm().getWaterconsumptionM2(date, true);
				value_that = user.getFarm().getWaterconsumptionM2(date, true);
				if (Utility.compare(value_that, value_this) == 0) {
					value_this = Utility.calculateEntropy(this.getFarm().getHumidityofsoilValue(date));
					value_that = Utility.calculateEntropy(user.getFarm().getHumidityofsoilValue(date));
				}
			}
		}
		return Utility.compare(value_this, value_that);
	}

	public List<Discussion> getDiscussions() {
		return discussions;
	}

	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}

	public void addDiscussion (Discussion discussion) {
		this.getDiscussions().add(discussion);
	}
}