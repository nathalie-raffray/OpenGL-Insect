/*
 * COMP 557 ASSIGNMENT 1
 * NATHALIE RAFFRAY
 * 260682940
 */


package comp557.a1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;
import javax.vecmath.Tuple2d;
import javax.vecmath.Vector2d;
import javax.vecmath.Point3d;

public class SphericalJoint extends GraphNode {

	DoubleParameter rx;
	DoubleParameter ry;
	DoubleParameter rz;
	
	Point3d position;
	

		
	public SphericalJoint( String name, Point3d euler, Point3d position, Vector2d xLimits, Vector2d yLimits,
					Vector2d zLimits) {
		super(name);
		dofs.add( rx = new DoubleParameter("SJrx", euler.x,  xLimits.x, xLimits.y) );	
		dofs.add( ry = new DoubleParameter("SJry", euler.y, yLimits.x, yLimits.y) );	
		dofs.add( rz = new DoubleParameter("SJrz", euler.z, zLimits.x, zLimits.y ) );	
		
		this.position = position;

	}
	
	
	@Override
	public void display( GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glPushMatrix();
		
		
		gl.glTranslated(position.x, position.y, position.z);
		gl.glRotated(rx.getValue(), 1, 0, 0);	
		gl.glRotated(ry.getValue(), 0, 1, 0);
		gl.glRotated(rz.getValue(), 0, 0, 1);

		super.display(drawable);
		gl.glPopMatrix();

	}

	
}
