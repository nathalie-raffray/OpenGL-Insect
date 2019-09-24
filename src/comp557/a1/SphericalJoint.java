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
	
	//DELETE AFTER
	DoubleParameter tx;
	DoubleParameter ty;
	DoubleParameter tz;
	
	
	Point3d position;
	
//	Tuple2d xLimits;
//	Tuple2d yLimits;
//	Tuple2d zLimits;
	//Point3d axis;

		
	public SphericalJoint( String name, Point3d euler, Point3d position, Vector2d xLimits, Vector2d yLimits,
					Vector2d zLimits) {
		super(name);
		dofs.add( rx = new DoubleParameter("RJr", euler.x,  xLimits.x, xLimits.y) );	
		dofs.add( ry = new DoubleParameter("RJr", euler.y, yLimits.x, yLimits.y) );	
		dofs.add( rz = new DoubleParameter("RJr", euler.z, zLimits.x, zLimits.y ) );	
		
		this.position = position;
		//DELETE AFTER
		dofs.add( tx = new DoubleParameter( "tx", position.x, -50, 50 ) );		
		dofs.add( ty = new DoubleParameter( "ty", position.y, -50, 50 ) );
		dofs.add( tz = new DoubleParameter( "tz", position.z, -50, 50 ) );
		//this.axis = axis;
	}
	
	
	@Override
	public void display( GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glPushMatrix();
		
		gl.glTranslated(tx.getValue(), ty.getValue(), tz.getValue());
		
		//gl.glTranslated(position.x, position.y, position.z);
		gl.glRotated(rx.getValue(), 1, 0, 0);	
		gl.glRotated(ry.getValue(), 0, 1, 0);
		gl.glRotated(rz.getValue(), 0, 0, 1);

		super.display(drawable);
		gl.glPopMatrix();

		// TODO: implement the rest of this method​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
	}

	
}
