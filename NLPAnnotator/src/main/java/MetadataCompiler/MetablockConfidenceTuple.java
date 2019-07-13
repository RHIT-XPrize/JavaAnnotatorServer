package MetadataCompiler;

public class MetablockConfidenceTuple {
	
	public MetaBlock metaBlock;
	public double confidence;
	
	public MetablockConfidenceTuple(MetaBlock metaBlock, double confidence) {
		this.confidence = confidence;
		this.metaBlock = metaBlock;
	}
}
