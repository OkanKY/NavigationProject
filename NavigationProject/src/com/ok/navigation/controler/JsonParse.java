package com.ok.navigation.controler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ok.navigation.items.Edge;
import com.ok.navigation.items.Vertex;

public class JsonParse {

	private static final String filePath = "E:\\Map.json";
	private ArrayList<Vertex> vertices;
    public 	JsonParse()
    {
    	vertices = new ArrayList<Vertex>();
    }
	public ArrayList<Vertex> getVertices(Boolean distance)
	{
		
		try {
			// read the json file
			FileReader reader = new FileReader(filePath);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

			// get an array from the JSON object
			JSONArray lang= (JSONArray) jsonObject.get("map");
			//use Iterator Pattern
			Iterator i = lang.iterator();
			// take each value from the json array separately
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();

				Vertex v1,v2;
				if(getInculude(innerObj.get("city1").toString())==null)
				{	
					v1 = new Vertex(""+innerObj.get("city1"));
					v1.adjacencies = new ArrayList<Edge>();
					vertices.add(v1);
				}
				else
					v1 = getInculude(innerObj.get("city1").toString());

				if(getInculude(innerObj.get("city2").toString())==null)
				{	
					v2 = new Vertex(""+innerObj.get("city2"));
					v2.adjacencies = new ArrayList<Edge>();
					vertices.add(v2);
				}
				else
				v2 = getInculude(innerObj.get("city2").toString());
			//if Selected distance, record distance 
             if(distance)
				v1.adjacencies.add(new Edge(v2, Double.parseDouble(innerObj.get("distance").toString())));
            //if Selected average speed, record average speed 
             else
            	v1.adjacencies.add(new Edge(v2, Double.parseDouble(innerObj.get("distance").toString())/Double.parseDouble(innerObj.get("avgSpeed").toString())));

			}


		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

		return vertices;
	}
	public Vertex getInculude(String source)
	{
		//Vertex is empty return false
		if(vertices.isEmpty())
			return null;

		//if find equals return true
        for (Vertex v : vertices)
	   {
	     if(v.name.equals(source))
	     return v;
	   }
        //NOT Find and return null
        return null;
	}
	

}
