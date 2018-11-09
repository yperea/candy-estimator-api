package com.pkty.vendor.pb.geolife;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Boundaries{

	@JsonProperty("boundary")
	private List<BoundaryItem> boundary;

	public void setBoundary(List<BoundaryItem> boundary){
		this.boundary = boundary;
	}

	public List<BoundaryItem> getBoundary(){
		return boundary;
	}

	@Override
 	public String toString(){
		return 
			"Boundaries{" + 
			"boundary = '" + boundary + '\'' + 
			"}";
		}
}