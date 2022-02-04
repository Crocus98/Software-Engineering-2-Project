package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "farm", schema="se2_bertelli_savino_vinati")
@NamedQuery(name="Farm.findAll", query="SELECT f FROM Farm f")
public class Farm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	private int dimension;
	
	@OneToOne
	@JoinColumn(name="idfarmer")
	private User user;

	@ManyToOne
	@JoinColumn(name="idarea")
	private Area area;
	
	@OneToMany(mappedBy="farm", cascade = CascadeType.ALL)
	private List<Production> productions;
	
	@OneToMany(mappedBy="farm")
	private List<Waterconsumption> waterconsumptions;
	
	@OneToMany(mappedBy="farm")
	private List<Humidityofsoil> humidityofsoil;

	public Farm() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getDimension() {
		return this.dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public List<Production> getProductions() {
		return productions;
	}
	
	public void addProduction(Production production) {
		this.getProductions().add(production);
	}
	
	//Method that return the whole production of the farm from the fromDate normalized for field dimension.
	public Double getProductionAmountM2(Date fromDate) {
		Double temp = 0.0;
		if(this.getProductions() != null) {
			for (int i = 0; i < this.getProductions().size(); i++) {
				if(fromDate == null || this.getProductions().get(i).getDate().after(fromDate)) {
					temp += this.getProductions().get(i).getAmount();
				}
			}
			temp /= (double)this.dimension;
		}
		return temp;
	}
	
	//Method that return the whole water consumption of the farm from the fromDate normalized for field dimension.
	public Double getWaterconsumptionM2(Date fromDate, boolean normalizeForDimension) {
		Double temp = 0.0;
		if(this.getWaterconsumptions() != null) {
			for (int i = 0; i < this.getWaterconsumptions().size(); i++) {
				if(fromDate == null || this.getWaterconsumptions().get(i).getDate().after(fromDate)) {
					temp += this.getWaterconsumptions().get(i).getAmount();
				}
			}
			if(normalizeForDimension == true) {
				temp /= (double)this.dimension;
			}
		}
		return temp;
	}
	
	//Method that return the list of humidity of soil values of the farm from the fromDate.
	public List<Integer> getHumidityofsoilValue(Date fromDate) {
		List<Integer> humidityofsoilValues = new ArrayList<>();
		if(this.getHumidityofsoil() != null) {
			for (int i = 0; i < this.getHumidityofsoil().size(); i++) {
				if(fromDate == null || this.getHumidityofsoil().get(i).getDate().after(fromDate)) {
					humidityofsoilValues.add(this.getHumidityofsoil().get(i).getClassification().getValue());
				}
			}
		}
		return humidityofsoilValues;
	}
	
	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Waterconsumption> getWaterconsumptions() {
		return waterconsumptions;
	}

	public void setWaterconsumptions(List<Waterconsumption> waterconsumptions) {
		this.waterconsumptions = waterconsumptions;
	}

	public List<Humidityofsoil> getHumidityofsoil() {
		return humidityofsoil;
	}

	public void setHumidityofsoil(List<Humidityofsoil> humidityofsoil) {
		this.humidityofsoil = humidityofsoil;
	}

}