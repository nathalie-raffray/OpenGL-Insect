/*
 * COMP 557 ASSIGNMENT 1
 * NATHALIE RAFFRAY
 * 260682940
 */


package comp557.a1;
 		  	  				   
import java.io.File;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

import javax.vecmath.Point3d;
import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple2d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector2d;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Loads an articulated character hierarchy from an XML file. 
 */
public class CharacterFromXML {

	public static GraphNode load( String filename ) {
		try {
			InputStream inputStream = new FileInputStream(new File(filename));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			return createScene( null, document.getDocumentElement() ); // we don't check the name of the document elemnet
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load simulation input file.", e);
		}
	}
	
	/**
	 * Load a subtree from a XML node.
	 * Returns the root on the call where the parent is null, but otherwise
	 * all children are added as they are created and all other deeper recursive
	 * calls will return null.
	 */
	public static GraphNode createScene( GraphNode parent, Node dataNode ) {
        NodeList nodeList = dataNode.getChildNodes();
        for ( int i = 0; i < nodeList.getLength(); i++ ) {
            Node n = nodeList.item(i);
            // skip all text, just process the ELEMENT_NODEs
            if ( n.getNodeType() != Node.ELEMENT_NODE ) continue;
            String nodeName = n.getNodeName();
            GraphNode node = null;
            if ( nodeName.equalsIgnoreCase( "node" ) ) {
            	node = CharacterFromXML.createJoint( n );
            } else if ( nodeName.equalsIgnoreCase( "geom" ) ) {        		
        		node = CharacterFromXML.createGeom( n ) ;            
            }
            // recurse to load any children of this node
            createScene( node, n );
            if ( parent == null ) {
            	// if no parent, we can only have one root... ignore other nodes at root level
            	return node;
            } else {
            	parent.add( node );
            }
        }
        return null;
	}
	
	/**​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
	 * Create a joint
	 * 
	 * TODO: Objective 5: Adapt commented code in createJoint() to create your joint nodes when loading from xml
	 */
	public static GraphNode createJoint( Node dataNode ) {
		String type = dataNode.getAttributes().getNamedItem("type").getNodeValue();
		String name = dataNode.getAttributes().getNamedItem("name").getNodeValue();
		Point3d position;
		
		if ( type.equals("freejoint") ) {
			FreeJoint joint = new FreeJoint( name );
			return joint;
		} else if ( type.equals("spherical") ) {
			// position is optional (ignored if missing) but should probably be a required attribute!​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
			// Could add optional attributes for limits (to all joints)
			Point3d euler;
			
			//xLimits.x = getTuple3dAttr(dataNode,"xLimits").x;
			
			//these are the default limits
			Vector2d xLimits = new Vector2d(-180, 180);
			Vector2d yLimits = new Vector2d(-180, 180);
			Vector2d zLimits = new Vector2d(-180, 180);
			
			if ( (getVector2dAttr(dataNode,"xLimits")) != null ) xLimits = new Vector2d(getVector2dAttr(dataNode,"xLimits"));
			if ( (getVector2dAttr(dataNode,"yLimits")) != null ) yLimits = new Vector2d(getVector2dAttr(dataNode,"yLimits"));
			if ( (getVector2dAttr(dataNode,"zLimits")) != null ) zLimits = new Vector2d(getVector2dAttr(dataNode,"zLimits"));
			

			if(getTuple3dAttr(dataNode,"euler") != null) {
				euler = new Point3d(getTuple3dAttr(dataNode,"euler"));
			}else {
				euler = new Point3d(0,0,0);
			}
			
			if(getTuple3dAttr(dataNode,"position") != null) {
				position = new Point3d(getTuple3dAttr(dataNode,"position"));
				SphericalJoint joint = new SphericalJoint(name, euler, position, xLimits, yLimits, zLimits);
				return joint;
			}else {
				System.out.println("Position is not specified for "+name+" with type "+type+". Please indicate position.");
				System.exit(1);
			}
			
		} else if ( type.equals("rotary") ) {
			// position and axis are required... passing null to set methods
			// likely to cause an execption (perhaps OK)
			
			Point3d axis;
			
			double min, max, setAngle;
			try {
				min = Double.parseDouble(dataNode.getAttributes().getNamedItem("min").getNodeValue());
			}catch(NullPointerException e) {
				min = 0;
			}//NumberFormatException?
			
			try {
				max = Double.parseDouble(dataNode.getAttributes().getNamedItem("max").getNodeValue());
			}catch(NullPointerException e) {
				max = 360;
			}//NumberFormatException?
			
			try {
				setAngle = Double.parseDouble(dataNode.getAttributes().getNamedItem("setAngle").getNodeValue());
			}catch(NullPointerException e) {
				setAngle = 0;
			}
			
	
			if(getTuple3dAttr(dataNode,"axis") != null && getTuple3dAttr(dataNode,"position") != null) {
				axis = new Point3d(getTuple3dAttr(dataNode,"axis"));
				position = new Point3d(getTuple3dAttr(dataNode,"position"));
				RotaryJoint joint = new RotaryJoint(name, min, max, setAngle, position, axis);
				return joint;
			}else {
				System.out.println("Position or axis is not specified for "+name+" with type "+type+". Please indicate position.");
				System.exit(1);
			}
			
			
		}
		return null;
	}

	/**
	 * Creates a geometry DAG node 
	 * 
	 * TODO: Objective 5: Adapt commented code in greatGeom to create your geometry nodes when loading from xml
	 */
	public static GraphNode createGeom( Node dataNode ) {
		String type = dataNode.getAttributes().getNamedItem("type").getNodeValue();
		String name = dataNode.getAttributes().getNamedItem("name").getNodeValue();
		Tuple3d t;
		String stacks; 
		String slices;
		
		if(type.equals("wireCube" ) || type.equals("wireSphere") || type.equals( "solidCube" ) || type.equals("solidSphere")
				|| type.equals("wireDodec") || type.equals("solidDodec") || type.equals("wireTetra") || type.equals("solidTetra")
				|| type.equals("solidCone") || type.equals("wireCone")){
			
			Geometry geom = new Geometry(name, type);
			if ( (t=getTuple3dAttr(dataNode,"position")) != null ) geom.setPosition( t );
			if ( (t=getTuple3dAttr(dataNode,"scale")) != null ) geom.setScale( t );
			if ( (t=getTuple3dAttr(dataNode,"color")) != null ) geom.setColor( t );
			if ( (t=getTuple3dAttr(dataNode,"fixedRotation")) != null ) geom.setFixedRotation( t );
			
			if(dataNode.getAttributes().getNamedItem("wink") != null) geom.setWink();
			
			if ( type.equals("wireCube" ) || type.equals("wireSphere") || type.equals("solidCone") || type.equals("wireCone")) {
				if ( (dataNode.getAttributes().getNamedItem("stacks")) != null) {
					geom.setStacks( Integer.parseInt(dataNode.getAttributes().getNamedItem("stacks").getNodeValue()) );
				}
				if ( (dataNode.getAttributes().getNamedItem("slices")) != null) {
					geom.setSlices( Integer.parseInt(dataNode.getAttributes().getNamedItem("slices").getNodeValue()) );
				}
				
			}
			return geom;
		}
		
		return null;		
		
	}
	
	/**
	 * Loads tuple3d attributes of the given name from the given node.
	 * @param dataNode
	 * @param attrName
	 * @return null if attribute not present
	 */
	public static Tuple3d getTuple3dAttr( Node dataNode, String attrName ) {
		Node attr = dataNode.getAttributes().getNamedItem( attrName);
		Vector3d tuple = null;
		if ( attr != null ) {
			Scanner s = new Scanner( attr.getNodeValue() );
			tuple = new Vector3d( s.nextDouble(), s.nextDouble(), s.nextDouble() );			
			s.close();
		}
		return tuple;
	}
	public static Vector2d getVector2dAttr( Node dataNode, String attrName ) {
		Node attr = dataNode.getAttributes().getNamedItem( attrName);
		Vector2d tuple = null;
		if ( attr != null ) {
			Scanner s = new Scanner( attr.getNodeValue() );
			tuple = new Vector2d( s.nextDouble(), s.nextDouble());			
			s.close();
		}
		return tuple;
	}

}