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

public class Rule8_12Test {

	private static XCProject project;
	private static Group<XCDeclaration> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test2","test2.zip");
		ICProject cProject = TestUtil.getProject("test2");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule8_12();
	}
	
	@Test
	public void verifyNoOfExternArrayDeclarationWithoutSize(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,2);
	}

	
	@Test
	public void verifyLinesAndFileNameOfExternArrayDeclarationWithoutSize(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCDeclaration s: res.getElements()) 
			{   
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("print.c7");  
		    newSet.add("main.c6");  
	        Assert.assertEquals(fileLine,newSet);
	}
	
}
