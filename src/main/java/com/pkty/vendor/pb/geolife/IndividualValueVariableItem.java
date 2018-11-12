package com.pkty.vendor.pb.geolife;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class IndividualValueVariableItem{

	@JsonProperty("year")
	private String year;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("value")
	private String value;

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"IndividualValueVariableItem{" + 
			"year = '" + year + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}