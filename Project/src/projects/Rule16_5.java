package projects;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCFunctionDeclarator;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCSourceRoot;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class Rule16_5 implements IPropertyComputer<String,XCProject>{
	
	@Override
	public String compute(XCProject arg0) {
    String s=new String();
	Group<XCSourceRoot> sourceR= new Group<>();
	Group<XCCompUnit> compU= new Group<>();
	Group<XCFunctionDeclarator> funcD = new Group<>();
	
	sourceR=arg0.sourceRootGroup();
	
	for(XCSourceRoot sr:sourceR.getElements()) {
		compU=sr.compUnitGroup();
		for(XCCompUnit cu: compU.getElements()) {
			funcD = cu.functionsWithNoParamGroup();
			for(XCFunctionDeclarator fd:funcD.getElements()) {
				s = s +fd.toString()+ " ";
			}
		}
	}
		
		return s;
	}

}
