package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTArrayDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTArrayModifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTEqualsInitializer;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCDeclaration;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule8_12
 */

	@RelationBuilder
	public class ExternArrayDeclarationWithoutSizeGroup implements IRelationBuilder<XCDeclaration, XCCompUnit>{
		
		@Override
		public Group<XCDeclaration> buildGroup(XCCompUnit arg0) {
			IASTTranslationUnit a = null;
			ITranslationUnit m = null;
			Group<XCDeclaration> res = new Group<>();
			
			try {
				m = arg0.getUnderlyingObject();
				a = m.getAST();
			}
			catch(CoreException e) 
			{	
				e.printStackTrace();
			}
			
			ASTVisitor v = new ASTVisitor() {		
				
				public int visit(IASTDeclarator c) {
					
					if(c instanceof IASTArrayDeclarator && c.isPartOfTranslationUnitFile()) 
					{
						IASTNode p = c.getParent();
						if(p instanceof IASTSimpleDeclaration)
						{
							IASTDeclSpecifier b = ((IASTSimpleDeclaration) p).getDeclSpecifier();
						    
							if(b.getStorageClass() == IASTDeclSpecifier.sc_extern)
							{   
								int k1=0,k2=0;
								
								IASTArrayModifier l[] = ((IASTArrayDeclarator) c).getArrayModifiers();
								IASTExpression sizeExpression = l[0].getConstantExpression(); 
								
								if(sizeExpression != null)
								{
									k1 = 1;
								}
								else
								{	
									IASTNode children[] = c.getChildren();
							    
									for(IASTNode i:children)
									{
							
										if(i instanceof IASTEqualsInitializer)
										{	
											k2 = 1;
											break;
										}
									}
								}
								
							if(k2 == 0 && k1 == 0 )
							{ 
								XCDeclaration d=Factory.getInstance().createXCDeclaration(c);
								res.add(d);
							}
							}
						}
					}
					return PROCESS_CONTINUE;
				}
			};
			
			v.	shouldVisitDeclarators = true;
			a.accept(v);
			
			return res;
		
		}
	}