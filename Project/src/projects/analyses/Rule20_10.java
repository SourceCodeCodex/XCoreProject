package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpression;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
/**
 * Rule 20.10: The library functions atof, atoi and atol from library shall not be used.
 */

@RelationBuilder
public class Rule20_10 implements IRelationBuilder<XCExpression,XCProject>{
	
	@Override
	public Group<XCExpression> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCExpression> functionCall = new Group<>();
		Group<XCExpression> f = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			f = cu.atofAtoiAtolFunctionGroup();
			functionCall.addAll(f.getElements());
	       
		}
	
		return functionCall;
	}

}
