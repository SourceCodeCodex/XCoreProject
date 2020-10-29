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
 * Rule20_9
 * #include <stdio.h> group
 */

@RelationBuilder
public class StdioHeaderGroup implements IRelationBuilder<XCIncludeStatement, XCCompUnit>{
	
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
			String s = i.getRawSignature();
			int n1 = s.indexOf('<')+1;
			int n2 = s.indexOf('>');
			if(n1 > 0 && n2 > 0)
			{
				s = s.substring(n1,n2);
			}
			if(s.equals("stdio.h") && i.isPartOfTranslationUnitFile())
			{ 
				XCIncludeStatement p = Factory.getInstance().createXCIncludeStatement(i);
				res.add(p);
			}
		}
		
		
		return res;

	}
	
}
	


	
	


	
	

