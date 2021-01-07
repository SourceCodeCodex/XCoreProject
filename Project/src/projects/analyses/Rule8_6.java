package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCDeclaration;
import project.metamodel.entity.XCFunction;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 8.6 (required): Functions shall be declared at file scope.
 */

@RelationBuilder
public class Rule8_6 implements IRelationBuilder<XCDeclaration,XCProject>{
	
	@Override
	public Group<XCDeclaration> buildGroup(XCProject arg0) {
	
		Group<XCCompUnit> compU = new Group<>();
		Group<XCDeclaration> funcD = new Group<>();
		Group<XCDeclaration> f = new Group<>();
		
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			f = cu.functionDeclarationAtBlockScopeGroup();
			for(XCDeclaration fd:f.getElements()) 
			{
				funcD.add(fd);
			}
		}
	
		
		return funcD;
	}

}