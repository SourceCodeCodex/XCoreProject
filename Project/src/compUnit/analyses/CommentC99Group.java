package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.IASTComment;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCComment;
import project.metamodel.entity.XCCompUnit;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule2_2
 * C99 style comments and C++ style comments
 */

@RelationBuilder
public class CommentC99Group implements IRelationBuilder<XCComment, XCCompUnit>{
	
	@Override
	public Group<XCComment> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCComment> res = new Group<>();
		
		try {
				m = arg0.getUnderlyingObject();
				a = m.getAST();
		} 
		catch(CoreException e)
		{
				e.printStackTrace();
		}
		
		IASTComment comm[] = a.getComments();
		for(IASTComment c:comm)
		{
				String s = c.toString();
				if(s.charAt(0) == s.charAt(1) && s.charAt(1) == '/')
				{
					if(c.isPartOfTranslationUnitFile())
					{
						XCComment comment = Factory.getInstance().createXCComment(c);
						res.add(comment);
					}
				}
		}
		
		return res;
	}
}
	
	
