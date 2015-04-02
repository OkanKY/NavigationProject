package com.ok.navigation.test;

import org.junit.Assert;
import org.junit.Test;

import com.ok.navigation.controler.JsonParse;
import com.ok.navigation.items.Vertex;

public class JsonParseTest {

	
	@Test
	public void jsonParseTest() {
		//fail("Not yet implemented");
		JsonParse json =new JsonParse();
		String[] VertexArray = {"A","B","C","D"};
		int i=0;
        for (Vertex v : json.getVertices(true))
	   {

         String expected=v.name;
   	     String actual =VertexArray[i];
   	     Assert.assertEquals(expected, actual);
	     i++;
	   }
	}
	

}
