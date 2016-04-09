package com.arhs.cube.thats.my.spot.data;

import java.util.ArrayList;
import java.util.List;

import com.arhs.cube.thats.my.spot.domain.BussLine;
import com.arhs.cube.thats.my.spot.domain.Station;

public class Busdata {

	
	public static List<BussLine> getData(){
		List<BussLine> bussLines = new ArrayList<>();
		
		BussLine bsOne = new BussLine();
		bsOne.setId("1");
		bsOne.getStations().add(new Station("49.60342017407767","6.085042683407664"));                  
		bsOne.getStations().add(new Station("49.6029960340824","6.088915793225169"));            
		bsOne.getStations().add(new Station("49.60395208213541","6.0931054037064305"));                 
		bsOne.getStations().add(new Station("49.60512365000469","6.095953909680247"));                 
		bsOne.getStations().add(new Station("49.605794594592766","6.102724727243185"));                 
		bsOne.getStations().add(new Station("49.606288237046535","6.106706969439983"));                 
		bsOne.getStations().add(new Station("49.61233668941133","6.124280802905559"));                  
		bsOne.getStations().add(new Station("49.613852161037805","6.128229014575481"));                  
		bsOne.getStations().add(new Station("49.61443609165705","6.13076101988554"));   
		bussLines.add(bsOne);
		
		BussLine bsOneTwo = new BussLine();
		bsOneTwo.setId("2");
		bsOneTwo.getStations().add(new Station("49.60350708755563","6.083616670221089"));
		bsOneTwo.getStations().add(new Station("49.605224466102214","6.0789174400269985"));
		bsOneTwo.getStations().add(new Station("49.609806163871966","6.071171220391989"));
		bsOneTwo.getStations().add(new Station("49.61450560674093","6.065422408282757"));
		bsOneTwo.getStations().add(new Station("49.61531197446829","6.05934988707304"));
		bsOneTwo.getStations().add(new Station("49.62185974245399","6.049908511340618"));
		bsOneTwo.getStations().add(new Station("49.62673871717805","6.03312861174345"));
		bsOneTwo.getStations().add(new Station("49.63183957281089","6.02115523070097"));
		bsOneTwo.getStations().add(new Station("49.640455608755836","6.003957241773605"));
		bsOneTwo.getStations().add(new Station("49.66179457714193","5.979752987623215"));
		bussLines.add(bsOneTwo);
		
		BussLine bsOneThree = new BussLine();
		bsOneTwo.setId("3");
		bsOneThree.getStations().add(new Station("49.60297865121701","6.08798548579216"));
		bsOneThree.getStations().add(new Station("49.60959759821483","6.097426861524582"));
		bsOneThree.getStations().add(new Station("49.616215646584564","6.099057644605636"));
		bsOneThree.getStations().add(new Station("49.62222116474464","6.105924099683761"));
		bsOneThree.getStations().add(new Station("49.63022737099707","6.110816448926926"));
		bsOneThree.getStations().add(new Station("49.64412390972648","6.102576702833176"));
		bsOneThree.getStations().add(new Station("49.64706946649089","6.085410565137862"));
		bsOneThree.getStations().add(new Station("49.65662740420531","6.0831789672374725"));
		bussLines.add(bsOneThree);
		
		return bussLines;
	}
	
}
