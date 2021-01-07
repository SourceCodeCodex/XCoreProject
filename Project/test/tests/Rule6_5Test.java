package tests;

import java.util.HashSet;

import org.eclipse.cdt.core.model.ICProject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCDeclaration;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule6_5Test {

	private static XCProject project;
	private static Group<XCDeclaration> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test1");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule6_5();
	}
	
	@Test
	public void verifyNoOfSignedBitFieldWith1BitLength(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,2);
	}

	
	@Test
	public void verifyLinesAndFileNameOfSignedBitFieldWith1BitLength(){
		
	     
		HashSet<String> fileLine = new HashSet<String>(); 
		for(XCDeclaration s: res.getElements()) 
		{   
			fileLine.add(s.fileName()+s.lineNumber());
		}
		HashSet<String> newSet = new HashSet<String>();
		newSet.add("function2.c9");  
	    newSet.add("function2.c10");  
        Assert.assertEquals(fileLine,newSet);
	}

}
