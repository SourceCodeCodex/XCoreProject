package tests;

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
        Assert.assertEquals(noOfElements,1);
	}

	
	@Test
	public void verifyLinesAndFileNameOfSignedBitFieldWith1BitLength(){
		    XCDeclaration d = res.getElements().get(0);
		    String s = d.fileName()+d.lineNumber();
	        Assert.assertEquals(s,"function2.c9");
	}

}
