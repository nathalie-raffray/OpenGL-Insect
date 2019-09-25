/*
 * COMP 557 ASSIGNMENT 1
 * NATHALIE RAFFRAY
 * 260682940
 */


package comp557.a1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import javax.vecmath.Point3f;

import mintools.parameters.DoubleParameter;

public class FreeJoint extends GraphNode {

	DoubleParameter tx;
	DoubleParameter ty;
	DoubleParameter tz;
	DoubleParameter rx;
	DoubleParameter ry;
	DoubleParameter rz;
		
	public FreeJoint( String name ) {
		super(name);
		dofs.add( tx = new DoubleParameter( "tx", 0, -2, 2 ) );		
		dofs.add( ty = new DoubleParameter( "ty", 0, -2, 2 ) );
		dofs.add( tz = new DoubleParameter( "tz", 0, -2, 2 ) );
		dofs.add( rx = new DoubleParameter( "rx", 25, -180, 180 ) );		
		dofs.add( ry = new DoubleParameter( "ry", -23, -180, 180 ) );
		dofs.add( rz = new DoubleParameter( "rz", 3.78, -180, 180 ) );
	}
	
	@Override
	public void display( GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		//gl.glPushMatrix();
		gl.glPushMatrix();
		
		gl.glTranslated(tx.getValue(), ty.getValue(), tz.getValue());
		gl.glRotated(rx.getValue(), 1, 0, 0);
		gl.glRotated(ry.getValue(), 0, 1, 0);
		gl.glRotated(rz.getValue(), 0, 0, 1);
		
		
		

		super.display(drawable);
		gl.glPopMatrix();

	}

	
}
