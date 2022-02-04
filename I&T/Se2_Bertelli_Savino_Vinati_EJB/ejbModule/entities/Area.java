package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import classes.Utility;

@Entity
@Table(name = "area", schema = "se2_bertelli_savino_vinati")
@NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a")
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToOne
	@JoinColumn(name = "idagronomist")
	private User user;

	@OneToMany(mappedBy = "area")
	private List<Forecast> forecasts;

	@OneToMany(mappedBy = "area")
	private List<Farm> farms;

	public Area() {

	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Forecast> getForecasts() {
		return forecasts;
	}

	//Method that return the list of forecasts values of the farm from the fromDate.
	public List<Integer> getForecastsValue(Date fromDate) {
		List<Integer> forecastValues = new ArrayList<>();
		if (this.getForecasts() != null) {
			for (int i = 0; i < this.getForecasts().size(); i++) {
				if (fromDate == null || this.getForecasts().get(i).getDate().after(fromDate)) {
					forecastValues.add(this.getForecasts().get(i).getClassification().getValue());
				}
			}
		}
		return forecastValues;
	}

	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}

	//Method compareTo useful to compare areas (e.g. for sort function).
	public int compareTo(Area area, Date date) {
		Double value_this = 0.0;
		Double value_that = 0.0;
		int size = this.getFarms().size();
		for(int i=0; i < size; i++) {
			value_this += this.getFarms().get(i).getProductionAmountM2(date);
			if(i == size-1) {
				value_this /= (double)size;
			}
		}
		size = area.getFarms().size();
		for(int i=0; i < size; i++) {
			value_that += area.getFarms().get(i).getProductionAmountM2(date);
			if(i == size-1) {
				value_that /= (double)size;
			}
		}
		if (Utility.compare(value_this, value_that) == 0) {
			value_this = Utility.calculateEntropy(this.getForecastsValue(date));
			value_that = Utility.calculateEntropy(area.getForecastsValue(date));
		}
		return Utility.compare(value_this, value_that);
	}	

	public List<Farm> getFarms() {
		return farms;
	}

	public void setFarms(List<Farm> farms) {
		this.farms = farms;
	}

}