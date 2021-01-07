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

public class Rule20_5Test {
	private static XCProject project;
	private static Group<XCExpression> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test2");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule20_5();
	}
	
	@Test
	public void verifyNoOfErrno(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,3);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfErrno(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCExpression s: res.getElements()) 
			{  
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("print.c42"); 
			newSet.add("print.c41"); 
			newSet.add("sum.c34");  
	        Assert.assertEquals(fileLine,newSet);
	}
}
