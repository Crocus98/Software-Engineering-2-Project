package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import enums.Usertype;

@Entity
@Table(name = "user", schema="se2_bertelli_savino_vinati")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.findAllFarmers", query="SELECT u FROM User u WHERE u.usertype = enums.Usertype.Farmer"),
	@NamedQuery(name="User.findAllFarmersPerArea", query="SELECT u FROM User u WHERE u.farm.area.id = ?1"),
	@NamedQuery(name = "User.checkCredentials", query = "SELECT u FROM User u  WHERE u.mail = ?1 and u.password = ?2")
	})
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
	
	@OneToOne(mappedBy="user")
	private Farm farm;

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

	public int compareTo(User user, Date date) {
		double value_this = this.getFarm().getProductionAmountM2(date);
		double value_that = user.getFarm().getProductionAmountM2(date);
		
		if(compare(value_this, value_that) == 0) {
			value_this = calculateEntropy(this.getFarm().getArea().getForecastsValue(date));
			value_that = calculateEntropy(user.getFarm().getArea().getForecastsValue(date));
			if(compare(value_that, value_this) == 0) {
				value_this = this.getFarm().getWaterconsumptionM2(date);
				value_that = user.getFarm().getWaterconsumptionM2(date);
				if(compare(value_that, value_this) == 0) {
					value_this = calculateEntropy(this.getFarm().getHumidityofsoilValue(date));
					value_that = calculateEntropy(user.getFarm().getHumidityofsoilValue(date));
					if(compare(value_that, value_this) == 0) {
						return 0;
					}
				}
			}
		}
		return compare(value_this, value_that);
	}
	
	private int compare(double value_this, double value_that) {
		double epsilon = 0.000001d;
		if(Math.abs(value_this - value_that) < epsilon) {
			return 0;
		}
		else if(value_this > value_that) {
			return 1;
		}
		else {
			return -1;
		}		
	}
	
	public Double calculateEntropy(List<Integer> values) {
		  Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		  //Count occurrences of each value
		  for (Integer item : values) {
		    if (!map.containsKey(item)) {
		      map.put(item, 0);
		    }
		    map.put(item, map.get(item) + 1);
		  }
		 
		  //Entropy calculation
		  Double result = 0.0;
		  for (Integer item : map.keySet()) {
		    Double frequency = (double) map.get(item) / values.size();
		    result -= frequency * (Math.log(frequency) / Math.log(2));
		  }
		 
		  return result;
		}

}