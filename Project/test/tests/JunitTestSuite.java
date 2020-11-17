package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({ Rule12_10Test.class, Rule12_13Test.class, Rule12_9Test.class, Rule14_4Test.class, Rule14_5Test.class,
		Rule14_8Test.class, Rule15_3Test.class, Rule16_1Test.class, Rule16_5Test.class, Rule18_4Test.class,
		Rule2_2Test.class, Rule2_3Test.class, Rule20_10Test.class, Rule20_11Test.class, Rule20_12Test.class,
		Rule20_8Test.class, Rule20_9Test.class, Rule6_4Test.class, Rule6_5Test.class, Rule8_12Test.class,
		Rule8_6Test.class, Rule9_3Test.class })

public class JunitTestSuite {
	
	@BeforeClass
	public static void setUpClass() {
		TestUtil.importProject("test0","test0.zip");
		TestUtil.importProject("test1","test1.zip");
		TestUtil.importProject("test2","test2.zip");
	}
	
	@AfterClass
	public static void tearDownClass() {
		TestUtil.deleteProject("test0");
		TestUtil.deleteProject("test1");
		TestUtil.deleteProject("test2");
	}

}
