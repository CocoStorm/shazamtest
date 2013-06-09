package com.shazamtest;

import java.util.ArrayList;

public class XMLDataCollected {

	private ArrayList<String> titles = new ArrayList<String>();
	private ArrayList<String> artists = new ArrayList<String>();
	private ArrayList<String> links = new ArrayList<String>();

	/* Getter and setter methods for the data collected */
	
	public ArrayList<String> getTitles() {
        return titles;
    }
    public void setTitle(String title) {
        this.titles.add(title);
    }
   
    public ArrayList<String> getArtists() {
        return artists;
    }
    
    public void setArtist(String artist) {
        this.artists.add(artist);
    }
   
    public ArrayList<String> getLinks() {
    	return links;
    }
    
    public void setLinks(String link) {
    	this.links.add(link);
    }
    
}
