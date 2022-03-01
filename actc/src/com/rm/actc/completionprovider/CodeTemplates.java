package com.rm.actc.completionprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;
import javax.swing.text.Document;
import org.netbeans.lib.editor.codetemplates.api.CodeTemplate;
import org.netbeans.lib.editor.codetemplates.api.CodeTemplateManager;

/**
 *
 * @author Ricardo Marquez
 */
class CodeTemplates {
  private final Collection<? extends CodeTemplate> list;

  protected CodeTemplates(Collection<? extends CodeTemplate> list) {
    this.list = list;
  }
  
  /**
   * 
   * @param document
   * @return 
   */
  protected static CodeTemplates create(Document document) {
    Collection<? extends CodeTemplate> list = newList(document);    
    CodeTemplates result = new CodeTemplates(list);
    return result;
  }
  
  /**
   * 
   * @param document
   * @return 
   */
  private static Collection<? extends CodeTemplate> newList(Document document) {
    CodeTemplateManager ctmanager = CodeTemplateManager.get(document);
    Collection<CodeTemplate> list = new ArrayList<>(ctmanager.getCodeTemplates());
    return list;
  }

  /**
   * 
   * @return 
   */
  Stream<? extends CodeTemplate> stream() {
    return this.list.stream();
  }
}
