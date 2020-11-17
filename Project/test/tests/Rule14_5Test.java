package tests;
import java.util.HashSet;
import org.eclipse.cdt.core.model.ICProject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCStatement;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule14_5Test  {

	private static XCProject project;
	private static Group<XCStatement> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test0");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule14_5();
	}
	
	@Test
	public void verifyNoOfContinueStm(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,3);
	}

	
	@Test
	public void verifyLinesAndFileNameOfContinueStm(){
		  HashSet<String> fileLine = new HashSet<String>(); 
			for(XCStatement s: res.getElements()) 
			{   
				fileLine.add(s.fileName()+s.lineNumber());
			}
			
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("prog5.c13"); 
			newSet.add("prog1.c10");  
		    newSet.add("prog2.c17");  
	        Assert.assertEquals(fileLine,newSet);
	}
}