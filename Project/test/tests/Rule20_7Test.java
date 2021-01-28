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

public class Rule20_7Test {
	private static XCProject project;
	private static Group<XCExpression> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test3");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule20_7();
	}
	
	@Test
	public void verifyNoOfSetjmpLongjmp(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,4);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfSetjmpLongjmp(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCExpression s: res.getElements()) 
			{  
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("file2.c29"); 
			newSet.add("file2.c42");
			newSet.add("file4.c7"); 
			newSet.add("file4.c14"); 
	        Assert.assertEquals(fileLine,newSet);
	}
}
