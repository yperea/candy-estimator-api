package com.pkty.vendor.pb.geolife;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Demographic{

	@JsonProperty("themes")
	private Themes themes;

	@JsonProperty("boundaries")
	private Boundaries boundaries;

	public void setThemes(Themes themes){
		this.themes = themes;
	}

	public Themes getThemes(){
		return themes;
	}

	public void setBoundaries(Boundaries boundaries){
		this.boundaries = boundaries;
	}

	public Boundaries getBoundaries(){
		return boundaries;
	}

	@Override
 	public String toString(){
		return 
			"Demographic{" + 
			"themes = '" + themes + '\'' + 
			",boundaries = '" + boundaries + '\'' + 
			"}";
		}
}