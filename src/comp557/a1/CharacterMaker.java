/*
 * COMP 557 ASSIGNMENT 1
 * NATHALIE RAFFRAY
 * 260682940
 */


package comp557.a1;

import javax.vecmath.Point3d;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;
import javax.vecmath.Tuple3d;

public class CharacterMaker {

	static public String name = "CHARLIE - NATHALIE RAFFRAY 260682940";
	
	/** 
	 * Creates a character.
	 * @return root DAGNode
	 */
	static public GraphNode create() {
		// TODO: use for testing, and ultimately for creating a character​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
		// Here we just return null, which will not be very interesting, so write
		// some code to create a charcter and return the root node.

		
		GraphNode load = CharacterFromXML.load("a1data/character.xml");
		
		return load;

	}
}
