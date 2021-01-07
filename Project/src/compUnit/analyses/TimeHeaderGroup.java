package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIncludeStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCIncludeStatement;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule20_12
 * #include <time.h> group
 */

@RelationBuilder
public class TimeHeaderGroup implements IRelationBuilder<XCIncludeStatement, XCCompUnit>{
	
	@Override
	public Group<XCIncludeStatement> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCIncludeStatement> res = new Group<>();
		
		try {
			m = arg0.getUnderlyingObject();
			a = m.getAST();
		}
		catch(CoreException e) 
		{
			e.printStackTrace();
		}
		
		IASTPreprocessorIncludeStatement[] includes = a.getIncludeDirectives();
		for(IASTPreprocessorIncludeStatement i:includes)
		{
			if(i.isPartOfTranslationUnitFile())
			{
				String name = i.getName().toString();
				
			    if(name.equals("time.h"))
			   { 
			    	XCIncludeStatement p = Factory.getInstance().createXCIncludeStatement(i);
			    	res.add(p);
			   }
			}
		}
		
		
		return res;

	}
	
}
	


	
	


	
	
