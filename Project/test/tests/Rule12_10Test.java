package tests;

import org.eclipse.cdt.core.model.ICProject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCExpression;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule12_10Test {
	private static XCProject project;
	private static Group<XCExpression> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test1","test1.zip");
		ICProject cProject = TestUtil.getProject("test1");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule12_10();
	}
	
	@Test
	public void verifyNoOfExpressionList(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,1);
	}
	
	
	@Test
	public void verifyLinesAndFileNameOfExpressionList(){
		    XCExpression e = res.getElements().get(0);
		    String s =e.fileName()+e.lineNumber();
		    Assert.assertEquals(s,"function2.c26");
	}
}
