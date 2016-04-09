package com.arhs.cube.thats.my.spot.domain;

import java.util.ArrayList;
import java.util.List;

public class BussLine {
	private String id;
	
	private List<Station> stations= new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Station> getStations() {
		if(stations == null){
			stations = new ArrayList<>();
		}
		
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
}
