package SpatialRelationGenerator;

import java.util.List;

import annotatorServer.AnnotationType;

public class SpatialRelationBlockType extends AnnotationType{

	List<OutputBlock> blocks;
	public SpatialRelationBlockType(String name, List<OutputBlock> blocks) {
		super(name);
		this.blocks = blocks;
	}

	@Override
	public List<String> getFields() {
		// TODO Auto-generated method stub
		return null;
	}

}
