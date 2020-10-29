package tests;

import java.util.HashSet;

import org.eclipse.cdt.core.model.ICProject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCFunction;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule16_1Test {
	private static XCProject project;
	private static Group<XCFunction> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test0","test0.zip");
		ICProject cProject = TestUtil.getProject("test0");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule16_1();
	}
	
	@Test
	public void verifyNoOfFunctionWithVariableNumberOfArgs(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,2);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfFunctionWithVariableNumberOfArgs(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCFunction s: res.getElements()) 
			{  
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("prog3.c21"); 
			newSet.add("prog5.c9");  
	        Assert.assertEquals(fileLine,newSet);
	}
}
