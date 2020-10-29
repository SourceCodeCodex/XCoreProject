package tests;
import java.util.HashSet;
import org.eclipse.cdt.core.model.ICProject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import project.metamodel.entity.XCComment;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;

public class Rule2_2Test extends TestClass {
	private static XCProject project;
	private static Group<XCComment> res;
	
	@BeforeClass
	public static void setUpClass() {
		
		TestUtil.importProject("test1","test1.zip");
		ICProject cProject = TestUtil.getProject("test1");
		project = Factory.getInstance().createXCProject(cProject);
		res = project.rule2_2();
	}
	
	@Test
	public void verifyNoOfCommentC99(){
		
        int noOfElements = res.getElements().size();
        Assert.assertEquals(noOfElements,4);
	}

	
	@Test
	public void verifyLinesAndFileNameOfCommentC99(){
		 HashSet<String> fileLine = new HashSet<String>(); 
			for(XCComment s: res.getElements()) 
			{   
				fileLine.add(s.fileName()+s.lineNumber());
			}
			HashSet<String> newSet = new HashSet<String>();
			newSet.add("function2.c15");  
		    newSet.add("main.c11");  
		    newSet.add("main.c17");  
		    newSet.add("main.c10"); 
	        Assert.assertEquals(fileLine,newSet);
	}
	
}
