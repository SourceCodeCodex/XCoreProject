package tests;

import java.util.HashSet;

import org.eclipse.cdt.core.model.ICProject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCExpression;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule12_10Test {
	private static XCProject project;
	private static Group<XCExpression> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test1");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule12_10();
	}
	
	@Test
	public void verifyNoOfExpressionList(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,2);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfExpressionList(){
		HashSet<String> fileLine = new HashSet<String>(); 
		for(XCExpression s: res.getElements()) 
		{   
			fileLine.add(s.fileName()+s.lineNumber());
		}
		
		HashSet<String> newSet = new HashSet<String>();
		newSet.add("function2.c26"); 
		newSet.add("function2.c29");
        Assert.assertEquals(fileLine,newSet);
	}

}
