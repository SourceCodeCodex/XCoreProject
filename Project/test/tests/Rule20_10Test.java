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

public class Rule20_10Test {
	private static XCProject project;
	private static Group<XCExpression> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test2","test2.zip");
		ICProject cProject = TestUtil.getProject("test2");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule20_10();
	}
	
	@Test
	public void verifyNoOfSomeFunctionFromLibraryStdlib(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,3);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfSomeFunctionFromLibraryStdlib(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCExpression s: res.getElements()) 
			{  
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("print.c32"); 
			newSet.add("print.c33"); 
			newSet.add("sum.c4");  
	        Assert.assertEquals(fileLine,newSet);
	}
}
