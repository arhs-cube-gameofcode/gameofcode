package com.arhs.cube.thats.my.spot;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BussWrapper {
private List<Bus> data ;

public List<Bus> getData() {
	return data;
}

public void setData(List<Bus> data) {
	this.data = data;
}


}
