package tests;

import java.util.HashSet;
import org.eclipse.cdt.core.model.ICProject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCComment;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule2_3Test{
	private static XCProject project;
	private static Group<XCComment> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test1");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule2_3();
	}
	
	@Test
	public void verifyNoOfNestedComments(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,2);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfNestedComments(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCComment s: res.getElements()) 
			{  
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("function1.c1"); 
			newSet.add("function2.c16");  
	        Assert.assertEquals(fileLine,newSet);
	}
}
