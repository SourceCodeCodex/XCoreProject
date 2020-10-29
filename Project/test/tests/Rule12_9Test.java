package tests;

import java.util.HashSet;

import org.eclipse.cdt.core.model.ICProject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCExpression;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule12_9Test {
	private static XCProject project;
	private static Group<XCExpression> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test1","test1.zip");
		ICProject cProject = TestUtil.getProject("test1");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule12_9();
	}
	
	@Test
	public void verifyNoOfUnsignedExpressionWithUnaryMinus(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,3);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfUnsignedExpressionWithUnaryMinus(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCExpression s: res.getElements()) 
			{  
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("function1.c22"); 
			newSet.add("function2.c20"); 
			newSet.add("main.c11"); 
	        Assert.assertEquals(fileLine,newSet);
	}
}
