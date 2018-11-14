package com.pkty.vendor.pb.geolife;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Themes{

	@JsonProperty("householdsTheme")
	private HouseholdsTheme householdsTheme;

	@JsonProperty("supplyAndDemandTheme")
	private SupplyAndDemandTheme supplyAndDemandTheme;

	@JsonProperty("housingTheme")
	private HousingTheme housingTheme;

	@JsonProperty("employmentTheme")
	private EmploymentTheme employmentTheme;

	@JsonProperty("incomeTheme")
	private IncomeTheme incomeTheme;

	@JsonProperty("assetsAndWealthTheme")
	private AssetsAndWealthTheme assetsAndWealthTheme;

	@JsonProperty("healthTheme")
	private HealthTheme healthTheme;

	@JsonProperty("educationTheme")
	private EducationTheme educationTheme;

	@JsonProperty("raceAndEthnicityTheme")
	private RaceAndEthnicityTheme raceAndEthnicityTheme;

	@JsonProperty("expenditureTheme")
	private ExpenditureTheme expenditureTheme;

	@JsonProperty("populationTheme")
	private PopulationTheme populationTheme;

	public void setHouseholdsTheme(HouseholdsTheme householdsTheme){
		this.householdsTheme = householdsTheme;
	}

	public HouseholdsTheme getHouseholdsTheme(){
		return householdsTheme;
	}

	public void setSupplyAndDemandTheme(SupplyAndDemandTheme supplyAndDemandTheme){
		this.supplyAndDemandTheme = supplyAndDemandTheme;
	}

	public SupplyAndDemandTheme getSupplyAndDemandTheme(){
		return supplyAndDemandTheme;
	}

	public void setHousingTheme(HousingTheme housingTheme){
		this.housingTheme = housingTheme;
	}

	public HousingTheme getHousingTheme(){
		return housingTheme;
	}

	public void setEmploymentTheme(EmploymentTheme employmentTheme){
		this.employmentTheme = employmentTheme;
	}

	public EmploymentTheme getEmploymentTheme(){
		return employmentTheme;
	}

	public void setIncomeTheme(IncomeTheme incomeTheme){
		this.incomeTheme = incomeTheme;
	}

	public IncomeTheme getIncomeTheme(){
		return incomeTheme;
	}

	public void setAssetsAndWealthTheme(AssetsAndWealthTheme assetsAndWealthTheme){
		this.assetsAndWealthTheme = assetsAndWealthTheme;
	}

	public AssetsAndWealthTheme getAssetsAndWealthTheme(){
		return assetsAndWealthTheme;
	}

	public void setHealthTheme(HealthTheme healthTheme){
		this.healthTheme = healthTheme;
	}

	public HealthTheme getHealthTheme(){
		return healthTheme;
	}

	public void setEducationTheme(EducationTheme educationTheme){
		this.educationTheme = educationTheme;
	}

	public EducationTheme getEducationTheme(){
		return educationTheme;
	}

	public void setRaceAndEthnicityTheme(RaceAndEthnicityTheme raceAndEthnicityTheme){
		this.raceAndEthnicityTheme = raceAndEthnicityTheme;
	}

	public RaceAndEthnicityTheme getRaceAndEthnicityTheme(){
		return raceAndEthnicityTheme;
	}

	public void setExpenditureTheme(ExpenditureTheme expenditureTheme){
		this.expenditureTheme = expenditureTheme;
	}

	public ExpenditureTheme getExpenditureTheme(){
		return expenditureTheme;
	}

	public void setPopulationTheme(PopulationTheme populationTheme){
		this.populationTheme = populationTheme;
	}

	public PopulationTheme getPopulationTheme(){
		return populationTheme;
	}

	@Override
 	public String toString(){
		return 
			"Themes{" + 
			"householdsTheme = '" + householdsTheme + '\'' + 
			",supplyAndDemandTheme = '" + supplyAndDemandTheme + '\'' + 
			",housingTheme = '" + housingTheme + '\'' + 
			",employmentTheme = '" + employmentTheme + '\'' + 
			",incomeTheme = '" + incomeTheme + '\'' + 
			",assetsAndWealthTheme = '" + assetsAndWealthTheme + '\'' + 
			",healthTheme = '" + healthTheme + '\'' + 
			",educationTheme = '" + educationTheme + '\'' + 
			",raceAndEthnicityTheme = '" + raceAndEthnicityTheme + '\'' + 
			",expenditureTheme = '" + expenditureTheme + '\'' + 
			",populationTheme = '" + populationTheme + '\'' + 
			"}";
		}
}