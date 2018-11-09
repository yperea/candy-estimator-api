package com.pkty.vendor.pb.geolife;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class HousingTheme{

	@JsonProperty("rangeVariable")
	private List<RangeVariableItem> rangeVariable;

	@JsonProperty("individualValueVariable")
	private List<IndividualValueVariableItem> individualValueVariable;

	@JsonProperty("boundaryRef")
	private String boundaryRef;

	public void setRangeVariable(List<RangeVariableItem> rangeVariable){
		this.rangeVariable = rangeVariable;
	}

	public List<RangeVariableItem> getRangeVariable(){
		return rangeVariable;
	}

	public void setIndividualValueVariable(List<IndividualValueVariableItem> individualValueVariable){
		this.individualValueVariable = individualValueVariable;
	}

	public List<IndividualValueVariableItem> getIndividualValueVariable(){
		return individualValueVariable;
	}

	public void setBoundaryRef(String boundaryRef){
		this.boundaryRef = boundaryRef;
	}

	public String getBoundaryRef(){
		return boundaryRef;
	}

	@Override
 	public String toString(){
		return 
			"HousingTheme{" + 
			"rangeVariable = '" + rangeVariable + '\'' + 
			",individualValueVariable = '" + individualValueVariable + '\'' + 
			",boundaryRef = '" + boundaryRef + '\'' + 
			"}";
		}
}