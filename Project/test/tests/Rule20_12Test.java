package tests;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.model.ICProject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCIncludeStatement;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule20_12Test extends TestClass {
	private static XCProject project;
	private static Group<XCIncludeStatement> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test","test.zip");
		ICProject cProject = TestUtil.getProject("test");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule20_12();
	}
	
	@Test
	public void verifyNoOfIncludeTimeTrue(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,4);
	}
	
	@Test
	public void verifyNoOfIncludeTimeFalse(){
        
        int noOfElements = res.getElements().size();
        Assert.assertNotEquals(noOfElements,3);
	}
	
	@Test
	public void verifyLinesOfIncludeTime(){
       
		List<IASTNode> nodes = new ArrayList<IASTNode>();
		for(XCIncludeStatement s: res.getElements()) 
		{
			nodes.add(s.getUnderlyingObject());
		}
		List<Integer> lines = getLineNoList(nodes);
        List<Integer> newList = new ArrayList<Integer>();
   
        newList.add(3);   newList.add(2);  newList.add(2); newList.add(1);
        Assert.assertEquals(lines,newList);
	}

}
