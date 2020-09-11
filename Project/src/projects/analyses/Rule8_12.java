package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCDeclaration;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 8.12 (required): When an array is declared with external linkage, its size shall be
 *  stated explicitly or defined implicitly by initialisation.
 */

@RelationBuilder
public class Rule8_12 implements IRelationBuilder<XCDeclaration,XCProject>{
	
	@Override
	public Group<XCDeclaration> buildGroup(XCProject arg0) {
	
		Group<XCCompUnit> compU = new Group<>();
		Group<XCDeclaration> decl = new Group<>();
		Group<XCDeclaration> f = new Group<>();
		
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			f = cu.externArrayDeclarationWithoutSizeGroup();
			for(XCDeclaration fd:f.getElements()) 
			{
				decl.add(fd);
			}
		}
	
		
		return decl;
	}

}
