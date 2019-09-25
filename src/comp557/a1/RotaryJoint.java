/*
 * COMP 557 ASSIGNMENT 1
 * NATHALIE RAFFRAY
 * 260682940
 */


package comp557.a1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;

import javax.vecmath.Point3d;

public class RotaryJoint extends GraphNode {

	DoubleParameter r;
	Point3d position;
	Point3d axis;
	

		
	public RotaryJoint( String name, double min, double max, double setAngle, Point3d position, 
						 Point3d axis) {
		super(name);
		dofs.add( r = new DoubleParameter("RJr", setAngle, min, max ) );	
		
		this.position = position;
		this.axis = axis;

	}
	
	@Override
	public void display( GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glPushMatrix();
		
		gl.glTranslated(position.x, position.y, position.z);
		
		gl.glRotated(r.getValue(), axis.x, axis.y, axis.z);	

		super.display(drawable);
		gl.glPopMatrix();


	}

	
}
