package comp557.a1;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;

import javax.vecmath.Point3d;
import javax.vecmath.Tuple3d;

public class Geometry extends GraphNode{

	Point3d position;
	Point3d scale;
	Point3d fixedRotation;
	
	boolean wink;
	DoubleParameter winkWink;
	DoubleParameter sx;
	DoubleParameter sy;
	DoubleParameter sz;
	DoubleParameter tx;
	DoubleParameter ty;
	DoubleParameter tz;
	DoubleParameter rx;
	DoubleParameter ry;
	DoubleParameter rz;
	DoubleParameter red;
	DoubleParameter blue;
	DoubleParameter green;
	//String name;
	
	double centerX = 0;
	double centerY = 0;
	double centerZ = 0;
	
	//default slices/stacks
	//double sphereRadius = 1;
	int slices = 50;
	int stacks = 50;
	
	//colors: red green blue
	double r=1;
	double g=0;
	double b=0;
	
	String type;
	
	//public Geometry(String name, Point3d scale, Point3d position, Point3d color)
	public Geometry(String name, String type) {
		super(name);
		this.type = type;
		position = new Point3d(0, 0, 0);
		fixedRotation = new Point3d(0, 0, 0);
		scale = new Point3d(1, 1, 1);
		
		
//		//this is for testing the scale
//		dofs.add( sx = new DoubleParameter( "sx", 1, -50, 50 ) );
//		dofs.add( sy = new DoubleParameter( "sy", 1, -50, 50 ) );
//		dofs.add( sz = new DoubleParameter( "sz", 1, -50, 50 ) );
		
//		//this is for testing the translation
//		dofs.add( tx = new DoubleParameter( "tx", 0, -20, 20 ) );		
//		dofs.add( ty = new DoubleParameter( "ty", 0, -20, 20 ) );
//		dofs.add( tz = new DoubleParameter( "tz", 0, -20, 20 ) );
		
		
		
		
	}
	
//	public void setRadius(double radius) {
//		sphereRadius = radius;
//	}
	public void setWink() {
		wink=true;
		dofs.add( winkWink = new DoubleParameter( "winkWink", 0.9, 0.025, 0.9 ) );
		
	}
	public void setSlices(int slices) {
		this.slices = slices;
	}
	public void setStacks(int stacks) {
		this.stacks = stacks;
	}
	public void setScale(Tuple3d scale) {
		this.scale.x = scale.x;
		this.scale.y = scale.y;
		this.scale.z = scale.z;
		
		//this is for testing the scale
		dofs.add( sx = new DoubleParameter( "sx", scale.x, -5, 5 ) );
		dofs.add( sy = new DoubleParameter( "sy", scale.y, -5, 5 ) );
		dofs.add( sz = new DoubleParameter( "sz", scale.z, -5, 5 ) );
	}
	public void setPosition(Tuple3d position) {
		this.position.x = position.x;
		this.position.y = position.y;
		this.position.z = position.z;
		
		//this is for testing the translation
		dofs.add( tx = new DoubleParameter( "tx", position.x, -50, 50 ) );		
		dofs.add( ty = new DoubleParameter( "ty", position.y, -50, 50 ) );
		dofs.add( tz = new DoubleParameter( "tz", position.z, -50, 50 ) );		
	}
	public void setFixedRotation(Tuple3d fixedRotation) {
		this.fixedRotation.x = fixedRotation.x;
		this.fixedRotation.y = fixedRotation.y;
		this.fixedRotation.z = fixedRotation.z;
		
		//this is for testing the rotation
		dofs.add( rx = new DoubleParameter("RJr", fixedRotation.x,  -180, 180) );	
		dofs.add( ry = new DoubleParameter("RJr", fixedRotation.y, -180, 180) );	
		dofs.add( rz = new DoubleParameter("RJr", fixedRotation.z, -180, 180 ) );		
	}
	public void setColor(Tuple3d color) {
		r = color.x;
		g = color.y;
		b = color.z;
		
		//this is for testing the color
		dofs.add( red = new DoubleParameter( "red", r, 0, 1 ) );		
		dofs.add( green = new DoubleParameter( "green", g, 0, 1 ) );
		dofs.add( blue = new DoubleParameter( "blue", b, 0, 1 ) );
	}
	
	@Override
	public void display( GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glPushMatrix();
		
		//declare dofs in constructor, edit them in set methods once values are passed by individual instances
		gl.glTranslated(tx.getValue(), ty.getValue(), tz.getValue());
		//gl.glTranslated(position.x, position.y, position.z);
		
		if((rx != null) && (ry != null) && (rz != null)) {
			gl.glRotated(rx.getValue(), 1, 0, 0);	
			gl.glRotated(ry.getValue(), 0, 1, 0);
			gl.glRotated(rz.getValue(), 0, 0, 1);
		}else {
			gl.glRotated(fixedRotation.x, 1, 0, 0);	
			gl.glRotated(fixedRotation.y, 0, 1, 0);
			gl.glRotated(fixedRotation.z, 0, 0, 1);
		}
		
		if(wink) {
			gl.glScaled(1, winkWink.getValue(), 1);
		}else {
			gl.glScaled(sx.getValue(), sy.getValue(), sz.getValue());
		}
		
		//gl.glScaled(scale.x, scale.y, scale.z);
		
		
		if((red != null) && (green != null) && (blue != null)) {
			gl.glColor3d(red.getValue(), green.getValue(), blue.getValue());
		}else {
			gl.glColor3d(r, g, b);
		}
		//gl.glColor3d(red.getValue(), green.getValue(), blue.getValue());
		//gl.glColor3d(r, g, b);
		
		if(type.equals("solidSphere")) {
			//System.out.println("HEY"); this just keeps printing out hey
			glut.glutSolidSphere(1, slices, stacks);
		}else if(type.equals("wireSphere")) {
			glut.glutWireSphere(1, slices, stacks);
		}else if(type.equals("solidCube")) {
			glut.glutSolidCube(1);
		}else if(type.equals("wireCube")) {
			glut.glutWireCube(1);
		}else if(type.equals("wireDodec")) {
			glut.glutWireDodecahedron();
		}else if(type.equals("solidDodec")) {
			glut.glutSolidDodecahedron();
		}else if(type.equals("solidTetra")) {
			glut.glutSolidTetrahedron();
		}else if(type.equals("wireTetra")) {
			glut.glutWireTetrahedron();
		}else if(type.equals("solidCone")) {
			glut.glutSolidCone(1, 1, slices, stacks);
		}else if(type.equals("wireCone")) {
			glut.glutWireCone(1, 1, slices, stacks);
		}
				
		super.display(drawable);
		gl.glPopMatrix();
		
	
	}
}
