package projects;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCContinueStatement;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCSourceRoot;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class Rule14_5 implements IPropertyComputer<String,XCProject>{
	
	@Override
	public String compute(XCProject arg0) {
    String s=new String();
	Group<XCSourceRoot> sourceR= new Group<>();
	Group<XCCompUnit> compU= new Group<>();
	Group<XCContinueStatement> continueS = new Group<>();
	
	sourceR=arg0.sourceRootGroup();
	
	for(XCSourceRoot sr:sourceR.getElements()) {
		compU=sr.compUnitGroup();
		for(XCCompUnit cu: compU.getElements()) {
			continueS = cu.continueStatementGroup();
			for(XCContinueStatement cs:continueS.getElements()) {
				s = s +cs.toString()+ " ";
			}
		}
	}
		
		return s;
	}

}
