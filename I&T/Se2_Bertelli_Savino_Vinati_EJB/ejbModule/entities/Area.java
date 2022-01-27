package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "area", schema="se2_bertelli_savino_vinati")
@NamedQuery(name="Area.findAll", query="SELECT a FROM Area a")
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@OneToOne
	@JoinColumn(name="idagronomist")
	private User user;
	
	@OneToMany(mappedBy="area")
	private List<Forecast> forecasts;

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
	
	public List<Integer> getForecastsValue(Date fromDate) {
		List<Integer> forecastValues = new ArrayList<>();
		if(this.getForecasts() != null) {
			for (int i = 0; i < this.getForecasts().size(); i++) {
				if(fromDate == null || this.getForecasts().get(i).getDate().after(fromDate)) {
					forecastValues.add(this.getForecasts().get(i).getClassification().getValue());
				}
			}
		}
		return forecastValues;
	}


	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}

}