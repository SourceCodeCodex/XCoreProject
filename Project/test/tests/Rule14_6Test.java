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

public class Rule14_6Test {
	private static XCProject project;
	private static Group<XCStatement> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test3");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule14_6();
	}
	
	@Test
	public void verifyNoLoopWithMoreThanABreakStatement(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,5);
	}

	
	@Test
	public void verifyLinesAndFileNameOfLoopWithMoreThanABreakStatement(){
		  HashSet<String> fileLine = new HashSet<String>(); 
			for(XCStatement s: res.getElements()) 
			{   
				fileLine.add(s.fileName()+s.lineNumber());
			}
			
			HashSet<String> newSet = new HashSet<String>(); 
		    newSet.add("main.c21"); 
		    newSet.add("main.c22"); 
			newSet.add("file2.c8");  
		    newSet.add("file2.c17");  
		    newSet.add("file3.c19"); 
	        Assert.assertEquals(fileLine,newSet);
	}
	
}
