package tests;

import java.util.HashSet;

import org.eclipse.cdt.core.model.ICProject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCSpecifier;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule18_4Test {
	private static XCProject project;
	private static Group<XCSpecifier> res;
	
	@BeforeClass
	public static void setUpClass() {
		ICProject cProject = TestUtil.getProject("test0");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule18_4();
	}
	
	@Test
	public void verifyNoOfUnions(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,2);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfUnions(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCSpecifier s: res.getElements()) 
			{  
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("prog2.c4"); 
			newSet.add("prog5.c5");  
	        Assert.assertEquals(fileLine,newSet);
	}
}

