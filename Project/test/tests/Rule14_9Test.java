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

public class Rule14_9Test {
	private static XCProject project;
	private static Group<XCStatement> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test2");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule14_9();
	}
	
	@Test
	public void verifyNoIfWithoutCompoundStatement(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,4);
	}

	
	@Test
	public void verifyLinesAndFileNameOfIfWithoutCompoundStatement(){
		  HashSet<String> fileLine = new HashSet<String>(); 
			for(XCStatement s: res.getElements()) 
			{   
				fileLine.add(s.fileName()+s.lineNumber());
			}
			
			HashSet<String> newSet = new HashSet<String>(); 
		    newSet.add("sum.c32"); 
			newSet.add("print.c20");  
		    newSet.add("myColor.c13");  
		    newSet.add("print.c39"); 
	        Assert.assertEquals(fileLine,newSet);
	}
	
}
