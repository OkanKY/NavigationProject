package com.ok.navigation.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.ok.navigation.controler.Dijkstra;
import com.ok.navigation.items.Edge;
import com.ok.navigation.items.Vertex;

public class NavigationTest {

	private ArrayList<Vertex>  getVertices()
	{
		//implementation
		 Vertex v0 = new Vertex("A");
		 Vertex v1 = new Vertex("B");

	     v0.adjacencies = new ArrayList<Edge>();
	     v0.adjacencies.add(new Edge(v1, 5.0));
	     v1.adjacencies = new ArrayList<Edge>();

	    // ArrayList<Vertex>
	     ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	     vertices.add(v0);
	     vertices.add(v1);
      return vertices;
	}
	@Test
	public void testMinDistance() {

		 ArrayList<Vertex> vertices=getVertices();
	     Dijkstra.computePaths(vertices.get(0));
		 Double expected= (double) 5;
		 Double actual=vertices.get(1).minDistance;
	     Assert.assertEquals(expected, actual);
	}
	@Test
	public void testShortestPath()
	{
		//Show Short Path A and B
		 ArrayList<Vertex> vertices=getVertices();
	     Dijkstra.computePaths(vertices.get(0));
	     String expected=Dijkstra.getShortestPathTo(vertices.get(1)).toString();
	     String actual ="[A, B]";
	     Assert.assertEquals(expected, actual);
	}
	@Test
	public void testHavePath()
	{  
		//Have A and B Path Control
		 ArrayList<Vertex> vertices=getVertices();
	     Dijkstra.computePaths(vertices.get(0));
	     Boolean expected=Dijkstra.getPathTo(vertices.get(1));
	     Boolean actual =true;
	     Assert.assertEquals(expected, actual);
	}
}
