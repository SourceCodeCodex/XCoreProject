package tests;

import java.util.HashSet;

import org.eclipse.cdt.core.model.ICProject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCStatement;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule13_4Test {
	private static XCProject project;
	private static Group<XCStatement> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test3");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule13_4();
	}
	
	@Test
	public void verifyNoForStatementWithFloatingObjectsInControllingExpression(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,3);
	}

	
	@Test
	public void verifyLinesAndFileNameOfForStatementWithFloatingObjectsInControllingExpression(){
		  HashSet<String> fileLine = new HashSet<String>(); 
			for(XCStatement s: res.getElements()) 
			{   
				fileLine.add(s.fileName()+s.lineNumber());
			}
			
			HashSet<String> newSet = new HashSet<String>(); 
		    newSet.add("file1.c32"); 
			newSet.add("main.c14");  
		    newSet.add("file3.c11");  
	        Assert.assertEquals(fileLine,newSet);
	}
	
}
