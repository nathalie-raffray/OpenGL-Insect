package comp557.a1;

import javax.vecmath.Point3d;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;
import javax.vecmath.Tuple3d;

public class CharacterMaker {

	static public String name = "CHARACTER NAME - YOUR NAME AND STUDENT NUMBER";
	
	/** 
	 * Creates a character.
	 * @return root DAGNode
	 */
	static public GraphNode create() {
		// TODO: use for testing, and ultimately for creating a character​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
		// Here we just return null, which will not be very interesting, so write
		// some code to create a charcter and return the root node.
	//FreeJoint charlie = new FreeJoint("Charlie");
		/*
		 * RotaryJoint Signature:
		 * public RotaryJoint( String name, double min, double max, Point3d position, 
						 Point3d axis) 
		 * */
		//Point3d axis = new Point3d(1, 1, 1);
		//Point3d position = new Point3d(10, 0, -5);
		//RotaryJoint leftElbow = new RotaryJoint("leftElbow", 0, 90, position, axis);
	//	charlie.add(leftElbow);
		
		/*
		 * SphericalJoint Signature:
		 * public SphericalJoint( String name, Point3d euler, Point3d position, Vector2d xLimits, Vector2d yLimits,
					Vector2d zLimits)
		 * */
		//position = new Point3d(0, 5, 0);
		//Point3d euler = new Point3d(0, 0, 0);
		//SphericalJoint head = new SphericalJoint("head", euler, position);
		//charlie.add(head);
		
		/*
		 * Geometry Signature:
		 * Geometry(String name, Point3d scale, Point3d position, String color)
		 * */
		//position = new Point3d(0, 5, 0);
		//Point3d positiong1 = new Point3d(2, 0, 0);
		//Point3d scale = new Point3d(5, 5, 5);
		//Point3d color = new Point3d(0.1, 0.3, 0.4);
		//Geometry arm = new Geometry("wireSphere", scale, position, color);
		//charlie.add(arm);
		//arm.add(null);
		//Geometry g1 = new Geometry("solidSphere", scale, new Point3d(2, 0, 0), color);
		//charlie.add(g1);
		
		GraphNode load = CharacterFromXML.load("a1data/character.xml");
		
		Geometry tail1 = new Geometry("tail1", "solidCube");
		tail1.setPosition(new Vector3d(-4.4, 0, 0));
		
		//Tuple3d scale = new Tuple3d(0, 2, 2);
		tail1.setScale(new Vector3d(0, 2, 2));
		//load.add(tail1);

		return load;
	
	
		//return null;
	}
}
