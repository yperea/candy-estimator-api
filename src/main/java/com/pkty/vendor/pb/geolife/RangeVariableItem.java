package com.pkty.vendor.pb.geolife;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class RangeVariableItem{

	@JsonProperty("field")
	private List<FieldItem> field;

	@JsonProperty("year")
	private String year;

	@JsonProperty("name")
	private String name;

	@JsonProperty("baseVariable")
	private String baseVariable;

	@JsonProperty("alias")
	private String alias;

	@JsonProperty("description")
	private String description;

	public void setField(List<FieldItem> field){
		this.field = field;
	}

	public List<FieldItem> getField(){
		return field;
	}

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

	public void setBaseVariable(String baseVariable){
		this.baseVariable = baseVariable;
	}

	public String getBaseVariable(){
		return baseVariable;
	}

	public void setAlias(String alias){
		this.alias = alias;
	}

	public String getAlias(){
		return alias;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	@Override
 	public String toString(){
		return 
			"RangeVariableItem{" + 
			"field = '" + field + '\'' + 
			",year = '" + year + '\'' + 
			",name = '" + name + '\'' + 
			",baseVariable = '" + baseVariable + '\'' + 
			",alias = '" + alias + '\'' + 
			",description = '" + description + '\'' + 
			"}";
		}
}