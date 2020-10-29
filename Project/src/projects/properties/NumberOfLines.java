package projects.properties;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class NumberOfLines implements IPropertyComputer<Integer, XCProject>{
	
	@Override
	public Integer compute(XCProject arg0) {
		
		int noOfLines = 0;
		Group<XCCompUnit> comp = arg0.compUnitGroup();
		for(XCCompUnit c : comp.getElements()) {
			noOfLines += c.numberOfLines();
		}
		 return noOfLines;
		 
	}
	
}