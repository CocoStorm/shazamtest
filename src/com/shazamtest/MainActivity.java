package com.shazamtest;

import java.net.URL;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity  {

	XMLDataCollected info;
	String tags[], urls[];
	TextView tvTitle;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Run loadXML on a new thread
		new Thread(new Runnable() {
			public void run() {
				loadXML();
			}
		}).start();     
	}

	public void loadXML() {
		try {
			URL website = new URL("http://www.shazam.com/music/web/taglistrss?mode=xml&userName=shazam");
			//Get an instance of the SAXParser from the SAXParserFactory
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			//Get an instance of the XMLReader
			XMLReader xr = sp.getXMLReader();
			//Get an instance of the XMLHandler class which extends DefaultHandler
			XMLHandler handler = new XMLHandler();
			//Pass the handler to the XMLReader
			xr.setContentHandler(handler);
			//Create an InputSource object with a byte stream
			InputSource is = new InputSource(website.openStream());
			//Set the encoding to determine how to read the XML
			is.setEncoding("ISO-8859-1");
			//Parse the XML document with the InputSource object
			xr.parse(is);
		} catch (Exception e){
			e.printStackTrace();
		}


		runOnUiThread(new Runnable() {
			public void run() {
				//Assign the XMLDataCollected object from the XMLHandler class to this XMLDataCollected object.
				info = XMLHandler.info;
				//Initialise the arrays which will hold data collected from the XML document 
				tags = new String[info.getTitles().size()];
				urls = new String[info.getTitles().size()];
				
				/* For the number of elements that have been parsed and stored in the ArrayList in 
				 * the XMLDataCollected class, loop through and assign each element to a string which 
				 * gets added into the arrays initialised above.*/
				for (int i=0; i<info.getTitles().size(); i++) {
					String title = info.getTitles().get(i);
					String artist = info.getArtists().get(i);
					String link = info.getLinks().get(i);
					urls[i] = link;
					tags[i] = title + " - " + artist;
				}
				//Display the list view with the tags array passed in the parameter
				setListAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, tags));
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		/* Start a new Activity when a row (tag) has been clicked.  Pass the link associated with the tag 
		 * to the new Activity using a Bundle.  The position integer keeps track of which row has been clicked
		 * i.e the position of the array.  Therefore using urls[position] we can keep track of the associated link */
		Intent intent = new Intent("android.intent.action.OPENURL");
		Bundle b = new Bundle();
		b.putString("URL", urls[position]);
		intent.putExtras(b);
		startActivity(intent);
	}
}