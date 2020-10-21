package tests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.model.ICProject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCGotoStatement;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule14_4Test extends TestClass{
	private static XCProject project;
	private static Group<XCGotoStatement> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test","test.zip");
		ICProject cProject = TestUtil.getProject("test");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule14_4();
	}
	
	@Test
	public void verifyNoOfContinueStmTrue(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,2);
	}
	
	@Test
	public void verifyNoOfContinueStmFalse(){
        
        int noOfElements = res.getElements().size();
        Assert.assertNotEquals(noOfElements,4);
	}
	
	@Test
	public void verifyLinesOfContinueStm(){
       
		List<IASTNode> nodes = new ArrayList<IASTNode>();
		for(XCGotoStatement s: res.getElements()) 
		{
			nodes.add(s.getUnderlyingObject());
		}
		HashSet<Integer> lines = getLineNoList(nodes);
        HashSet<Integer> newList = new HashSet<Integer>();
        newList.add(7);   newList.add(12);  
        Assert.assertEquals(lines,newList);
	}


}
