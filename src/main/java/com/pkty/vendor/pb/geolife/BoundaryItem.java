package com.pkty.vendor.pb.geolife;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class BoundaryItem{

	@JsonProperty("boundaryId")
	private String boundaryId;

	@JsonProperty("boundaryRef")
	private String boundaryRef;

	@JsonProperty("boundaryType")
	private String boundaryType;

	public void setBoundaryId(String boundaryId){
		this.boundaryId = boundaryId;
	}

	public String getBoundaryId(){
		return boundaryId;
	}

	public void setBoundaryRef(String boundaryRef){
		this.boundaryRef = boundaryRef;
	}

	public String getBoundaryRef(){
		return boundaryRef;
	}

	public void setBoundaryType(String boundaryType){
		this.boundaryType = boundaryType;
	}

	public String getBoundaryType(){
		return boundaryType;
	}

	@Override
 	public String toString(){
		return 
			"BoundaryItem{" + 
			"boundaryId = '" + boundaryId + '\'' + 
			",boundaryRef = '" + boundaryRef + '\'' + 
			",boundaryType = '" + boundaryType + '\'' + 
			"}";
		}
}