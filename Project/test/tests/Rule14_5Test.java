package tests;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.model.ICProject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCContinueStatement;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule14_5Test extends TestClass  {

	private static XCProject project;
	private static Group<XCContinueStatement> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test","test.zip");
		ICProject cProject = TestUtil.getProject("test");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule14_5();
	}
	
	@Test
	public void verifyNoOfContinueStmTrue(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,3);
	}
	
	@Test
	public void verifyNoOfContinueStmFalse(){
        
        int noOfElements = res.getElements().size();
        Assert.assertNotEquals(noOfElements,5);
	}
	
	@Test
	public void verifyLinesOfContinueStm(){
       
		List<IASTNode> nodes = new ArrayList<IASTNode>();
		for(XCContinueStatement s: res.getElements()) 
		{
			nodes.add(s.getUnderlyingObject());
		}
		List<Integer> lines = getLineNoList(nodes);
        List<Integer> newList = new ArrayList<Integer>();
        newList.add(8);   newList.add(10);  newList.add(12);
        Assert.assertEquals(lines,newList);
	}

}
