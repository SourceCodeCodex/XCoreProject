package tests;

import java.util.HashSet;

import org.eclipse.cdt.core.model.ICProject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCDeclaration;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule6_4Test {
	private static XCProject project;
	private static Group<XCDeclaration> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test1","test1.zip");
		ICProject cProject = TestUtil.getProject("test1");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule6_4();
	}
	
	@Test
	public void verifyNoOfBitFieldDeclaredWithoutAppropriateType(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,2);
	}

	
	@Test
	public void verifyLinesAndFileNameOfBitFieldDeclaredWithoutAppropriateType(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCDeclaration s: res.getElements()) 
			{   
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("function2.c8");  
		    newSet.add("main.c8");  
	        Assert.assertEquals(fileLine,newSet);
	}
	

}
