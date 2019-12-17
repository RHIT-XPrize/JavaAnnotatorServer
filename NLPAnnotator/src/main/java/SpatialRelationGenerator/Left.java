package SpatialRelationGenerator;

import org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates;

public class Left implements SpatialRelation {

	@Override
	public void checkRelation(SphericalCoordinates coords, BlockWrapper currentWrapper, BlockWrapper otherWrapper, InnerBlock current, InnerBlock other) {
		
		if ((coords.getTheta() >= Math.PI/2) && (coords.getPhi() <= (3.0*Math.PI)/4.0)){
			current.left.add(otherWrapper);
			other.right.add(currentWrapper);
		}
	}

}
