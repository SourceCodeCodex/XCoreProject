package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpressionList;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 12.10: The comma operator shall not be used
 */


@RelationBuilder
public class Rule12_10 implements IRelationBuilder<XCExpressionList,XCProject>{
	
	@Override
	public Group<XCExpressionList> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCExpressionList> exprL = new Group<>();
		Group<XCExpressionList> exp = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			exp = cu.expressionListGroup();
	        for(XCExpressionList cs : exp.getElements()) 
	        {
				exprL.add(cs);
	        }
		}
	
		return exprL;
	}

}

