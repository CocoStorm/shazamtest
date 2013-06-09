package com.shazamtest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import android.util.Log;

public class XMLHandler extends DefaultHandler {
	String elementValue = null;
	Boolean elementOn = false;
	Boolean itemOn = false;
	String storedTag;
	public static XMLDataCollected info = null;
	public static XMLDataCollected getXMLData() {
		return info;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		elementOn = true;
		if (localName.equals("channel")) {
			/* If the parser reads the 'channel' element in the XML document then
			 * create a new instance of the XMLDataCollected object which will be 
			 * used by the MainActivity*/
			info = new XMLDataCollected();
		} else if (localName.equals("item")) {
			/*To determine whether we are currently within an item element, we keep
			track with the itemOn boolean*/
			itemOn=true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		elementOn = false;
		/* Call the setter methods in the XMLDataCollected to add the data
		 * to the arrayLists based on which element we are currently in */
		if (localName.equals("item")) {
			itemOn=false;
		} else if (localName.equals("trackName") && itemOn) {
			info.setTitle(elementValue);
		} else if (localName.equals("trackArtist") && itemOn){
			info.setArtist(elementValue); 
		} else if (localName.equals("link") && itemOn) {
			info.setLinks(elementValue);
		} else if (localName.equals("item")) {
			itemOn = false;
		}  
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		//Set the elementValue to what has been read
		if (elementOn) {
			elementValue = new String(ch, start, length);
			elementOn = false;
		}
	}
}