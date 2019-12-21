package com.tudor.software.SongBookCreator;

public class SongDTO {
	public String title;
	public String lyrics;
	public String order;
	public SongDTO(String tilte, String lyrics, String order) {
		super();
		this.title = tilte;
		this.lyrics = lyrics;
		this.order = order;
	}	

}
