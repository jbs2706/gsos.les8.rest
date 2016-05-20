package nl.hu.fnt.gsos.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NumberOfTracks {
	private int number;

	public NumberOfTracks (){
	}
	
	public NumberOfTracks (int number){
		this.number = number;
	}
	
	public int getNumberOfTracks() {
		return number;
	}

	public void setNumberOfTracks(int number) {
		this.number = number;
	}

}
