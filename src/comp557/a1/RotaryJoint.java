package comp557.a1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;

import javax.vecmath.Point3d;

public class RotaryJoint extends GraphNode {

	DoubleParameter r;
	Point3d position;
	Point3d axis;
	
	//DELETE AFTER
	DoubleParameter tx;
	DoubleParameter ty;
	DoubleParameter tz;

		
	public RotaryJoint( String name, double min, double max, double setAngle, Point3d position, 
						 Point3d axis) {
		super(name);
		dofs.add( r = new DoubleParameter("RJr", setAngle, min, max ) );	
		
		this.position = position;
		this.axis = axis;
		
		dofs.add( tx = new DoubleParameter( "tx", position.x, -50, 50 ) );		
		dofs.add( ty = new DoubleParameter( "ty", position.y, -50, 50 ) );
		dofs.add( tz = new DoubleParameter( "tz", position.z, -50, 50 ) );
	}
	
	@Override
	public void display( GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glPushMatrix();
		
		//gl.glTranslated(position.x, position.y, position.z);
		
		//DELETE THIS
		gl.glTranslated(tx.getValue(), ty.getValue(), tz.getValue());
		gl.glRotated(r.getValue(), axis.x, axis.y, axis.z);	

		super.display(drawable);
		gl.glPopMatrix();

		// TODO: implement the rest of this method​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
	}

	
}
