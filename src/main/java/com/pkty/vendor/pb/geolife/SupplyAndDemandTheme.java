package com.pkty.vendor.pb.geolife;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SupplyAndDemandTheme{

	@JsonProperty("individualValueVariable")
	private List<IndividualValueVariableItem> individualValueVariable;

	@JsonProperty("boundaryRef")
	private String boundaryRef;

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
			"SupplyAndDemandTheme{" + 
			"individualValueVariable = '" + individualValueVariable + '\'' + 
			",boundaryRef = '" + boundaryRef + '\'' + 
			"}";
		}
}