package com.ok.navigation;

import java.util.ArrayList;
import java.util.List;

import com.ok.navigation.controler.Dijkstra;
import com.ok.navigation.controler.JsonParse;
import com.ok.navigation.items.Vertex;

public class Navigation {
    public static void main(String[] args)
    {
    	
       JsonParse jParse =new JsonParse();
       /*
        * 
        * Selected distance
        * 
        * */
       System.out.println("*** According to calculation  minimum Distance ***");
       //data is read from the file
       ArrayList<Vertex> verticesDistance=jParse.getVertices(true);
       //The starting point is selected
       Dijkstra.computePaths(verticesDistance.get(0));
      //Shows all path
        for (Vertex v : verticesDistance)
	   {   	
	    System.out.println("Distance to " + v + ": " + v.minDistance);
	    List<Vertex> path = Dijkstra.getShortestPathTo(v);
	    System.out.println("Path: " + path);
	    System.out.println("Have Path : " + Dijkstra.getPathTo(v));
	   }
        
        
        /*
         * 
         * Selected average speed
         * 
         * */
        System.out.println("*** According to calculation  minimum Average Speed ***");
        //data is read from the file
        ArrayList<Vertex> verticesAvgSpeed=jParse.getVertices(false);
        //The starting point is selected
        Dijkstra.computePaths(verticesAvgSpeed.get(0));
       //Shows all path
         for (Vertex v : verticesAvgSpeed)
 	   {   	
 	    System.out.println("average speed to " + v + ": " + v.minDistance);
 	    List<Vertex> path = Dijkstra.getShortestPathTo(v);
 	    System.out.println("Path: " + path);
 	    System.out.println("Have Path : " + Dijkstra.getPathTo(v));
 	   }
        
    }
}
