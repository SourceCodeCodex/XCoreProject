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

public class Rule16_5Test {
	private static XCProject project;
	private static Group<XCFunction> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test1","test1.zip");
		ICProject cProject = TestUtil.getProject("test1");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule16_5();
	}
	
	@Test
	public void verifyNoOfFunctionWithNoParam(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,6);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfFunctionWithNoParam(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCFunction s: res.getElements()) 
			{  System.out.println(s.fileName()+s.lineNumber());
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("main.c12"); 
			newSet.add("function1.c3");  
			newSet.add("function1.c5"); 
			newSet.add("function2.c24"); 
			newSet.add("main.c3"); 
			newSet.add("main.c16");
	        Assert.assertEquals(fileLine,newSet);
	}
}
