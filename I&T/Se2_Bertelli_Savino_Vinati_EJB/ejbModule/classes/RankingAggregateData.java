package classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import entities.User;

public class RankingAggregateData {

	private String nameSurname;
	private String mail;
	private String area;
	private int farmDimension;
	private double productionM2;
	private double forecastEntropy;
	private double waterConsumptionM2;
	private double humidityEntropy;

	public RankingAggregateData(User farmer, Date date) { // leave date null if not specified
		this.setNameSurname(farmer.getName() + " " + farmer.getSurname());
		this.setMail(farmer.getMail());
		this.setFarmDimension(farmer.getFarm().getDimension());
		this.setArea(farmer.getFarm().getArea().getName());
		this.setProductionM2(this.round(farmer.getFarm().getProductionAmountM2(date)));
		this.setForecastEntropy(this.round(farmer.calculateEntropy(farmer.getFarm().getArea().getForecastsValue(date))));
		this.setWaterConsumptionM2(this.round(farmer.getFarm().getWaterconsumptionM2(date)));
		this.setHumidityEntropy(this.round(farmer.calculateEntropy(farmer.getFarm().getHumidityofsoilValue(date))));
	}

	public String getNameSurname() {
		return nameSurname;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getFarmDimension() {
		return farmDimension;
	}

	public void setFarmDimension(int farmDimension) {
		this.farmDimension = farmDimension;
	}

	public double getProductionM2() {
		return productionM2;
	}

	public void setProductionM2(Double productionM2) {
		this.productionM2 = productionM2;
	}

	public double getForecastEntropy() {
		return forecastEntropy;
	}

	public void setForecastEntropy(double forecastEntropy) {
		this.forecastEntropy = forecastEntropy;
	}

	public double getWaterConsumptionM2() {
		return waterConsumptionM2;
	}

	public void setWaterConsumptionM2(double waterConsumptionM2) {
		this.waterConsumptionM2 = waterConsumptionM2;
	}

	public double getHumidityEntropy() {
		return humidityEntropy;
	}

	public void setHumidityEntropy(double humidityEntropy) {
		this.humidityEntropy = humidityEntropy;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public double round (double numberToRound) {
		BigDecimal temp = new BigDecimal(numberToRound);
		return Double.parseDouble(temp.setScale(2, RoundingMode.HALF_UP).toString());
	}
}
