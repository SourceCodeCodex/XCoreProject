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

public class Rule20_4Test {
	private static XCProject project;
	private static Group<XCExpression> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test3");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule20_4();
	}
	
	@Test
	public void verifyNoOfFunctionsForDynamicHeapMemoryAllocation(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,5);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfFunctionsForDynamicHeapMemoryAllocation(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCExpression s: res.getElements()) 
			{  
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("file1.c8"); 
			newSet.add("file1.c10");
			newSet.add("file1.c15"); 
			newSet.add("file3.c31"); 
			newSet.add("file3.c41"); 
	        Assert.assertEquals(fileLine,newSet);
	}
}
