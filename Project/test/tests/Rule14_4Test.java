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

public class Rule14_4Test{
	private static XCProject project;
	private static Group<XCStatement> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test0");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule14_4();
	}
	
	@Test
	public void verifyNoOfGotoStm(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,2);
	}

	@Test
	public void verifyLinesAndFileNameOfGotoStm(){
        HashSet<String> fileLine = new HashSet<String>(); 
		for(XCStatement s: res.getElements()) 
		{   
			fileLine.add(s.fileName()+s.lineNumber());
		}
		
		HashSet<String> newSet = new HashSet<String>();
		newSet.add("prog4.c13");  
	    newSet.add("prog3.c11");  
        Assert.assertEquals(fileLine,newSet);
	}
	
}
