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

public class Rule13_3Test {
	private static XCProject project;
	private static Group<XCExpression> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test3");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule13_3();
	}
	
	@Test
	public void verifyNoOfFloatingPointExpressionsTestedForEqualityInequality(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,6);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfFloatingPointExpressionsTestedForEqualityInequality(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCExpression s: res.getElements()) 
			{  
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("main.c4"); 
			newSet.add("file1.c22");
			newSet.add("file1.c24"); 
			newSet.add("file1.c32"); 
			newSet.add("main.c14"); 
			newSet.add("file3.c11"); 
	        Assert.assertEquals(fileLine,newSet);
	}
}
