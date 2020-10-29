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

public class Rule8_6Test {
	private static XCProject project;
	private static Group<XCFunction> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test1","test1.zip");
		ICProject cProject = TestUtil.getProject("test1");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule8_6();
	}
	
	@Test
	public void verifyNoOfFunctionDeclarationAtBlockScope(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,2);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfFunctionDeclarationAtBlockScope(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCFunction s: res.getElements()) 
			{  
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("main.c16"); 
			newSet.add("function1.c7");  
	        Assert.assertEquals(fileLine,newSet);
	}
}
