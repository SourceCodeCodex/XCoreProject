package projects.analyses;

import project.metamodel.entity.XCComment;
import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 2.3 (required): The character sequence /* shall not be used within a comment
 */

@RelationBuilder
public class Rule2_3 implements IRelationBuilder<XCComment,XCProject>{
	
	@Override
	public Group<XCComment> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCComment> comments = new Group<>();
		Group<XCComment> comm = new Group<>();
		
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			comm = cu.nestedCommentsGroup();
	        for(XCComment cs:comm.getElements()) 
	        {
				comments.add(cs);
	        }
		}
	
		return comments;
	}

}

