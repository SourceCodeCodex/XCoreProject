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

public class Rule14_8Test {
	private static XCProject project;
	private static Group<XCStatement> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test2","test2.zip");
		ICProject cProject = TestUtil.getProject("test2");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule14_8();
	}
	
	@Test
	public void verifyNoOfStatementWithoutEnclosedBody(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,4);
	}

	
	@Test
	public void verifyLinesAndFileNameOfStatementWithoutEnclosedBody(){
		  HashSet<String> fileLine = new HashSet<String>(); 
			for(XCStatement s: res.getElements()) 
			{   
				fileLine.add(s.fileName()+s.lineNumber());
			}
			
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("print.c11"); 
			newSet.add("main.c11");  
		    newSet.add("myColor.c9");  
		    newSet.add("sum.c15");  
	        Assert.assertEquals(fileLine,newSet);
	}

}
