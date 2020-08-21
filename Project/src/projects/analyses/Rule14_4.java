package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCGotoStatement;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * The goto statement shall not be used
 */

@RelationBuilder
public class Rule14_4 implements IRelationBuilder<XCGotoStatement,XCProject>{
	
	@Override
	public Group<XCGotoStatement> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCGotoStatement> gotoS = new Group<>();
		Group<XCGotoStatement> gtS = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			gtS = cu.gotoStatementGroup();
	        for(XCGotoStatement cs:gtS.getElements()) 
	        {
				gotoS.add(cs);
	        }
		}
	
		return gotoS;
	}

}

