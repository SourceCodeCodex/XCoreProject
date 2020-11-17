package tests;

import java.util.HashSet;
import org.eclipse.cdt.core.model.ICProject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCIncludeStatement;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule20_12Test {
	private static XCProject project;
	private static Group<XCIncludeStatement> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test0");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule20_12();
	}
	
	@Test
	public void verifyNoOfIncludeTime(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,4);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfIncludeTime(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCIncludeStatement s: res.getElements()) 
			{  
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("prog5.c3");  
		    newSet.add("prog1.c2");  
		    newSet.add("prog2.c2");  
		    newSet.add("prog3.c1"); 
	        Assert.assertEquals(fileLine,newSet);
	}
}
