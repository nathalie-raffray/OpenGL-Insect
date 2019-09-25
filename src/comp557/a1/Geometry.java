/*
 * COMP 557 ASSIGNMENT 1
 * NATHALIE RAFFRAY
 * 260682940
 */

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
	
	double centerX = 0;
	double centerY = 0;
	double centerZ = 0;
	

	int slices = 50;
	int stacks = 50;
	
	//colors: red green blue
	double r=1;
	double g=0;
	double b=0;
	
	String type;

	public Geometry(String name, String type) {
		super(name);
		this.type = type;
		position = new Point3d(0, 0, 0);
		fixedRotation = new Point3d(0, 0, 0);
		scale = new Point3d(1, 1, 1);	
	}
	
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
		
	}
	public void setPosition(Tuple3d position) {
		this.position.x = position.x;
		this.position.y = position.y;
		this.position.z = position.z;
			
	}
	public void setFixedRotation(Tuple3d fixedRotation) {
		this.fixedRotation.x = fixedRotation.x;
		this.fixedRotation.y = fixedRotation.y;
		this.fixedRotation.z = fixedRotation.z;
		
	}
	public void setColor(Tuple3d color) {
		r = color.x;
		g = color.y;
		b = color.z;
		
	}
	
	@Override
	public void display( GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glPushMatrix();
		
	
		gl.glTranslated(position.x, position.y, position.z);
		
		gl.glRotated(fixedRotation.x, 1, 0, 0);	
		gl.glRotated(fixedRotation.y, 0, 1, 0);
		gl.glRotated(fixedRotation.z, 0, 0, 1);
		
		if(wink) {
			gl.glScaled(1, winkWink.getValue(), 1);
		}else {
			gl.glScaled(scale.x, scale.y, scale.z);
		}

		gl.glColor3d(r, g, b);

		
		if(type.equals("solidSphere")) {
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
