package com.rm.actc.completionprovider;

import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.mimelookup.MimeRegistrations;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;

/**
 *
 * @author Ricardo Marquez
 */
@MimeRegistrations({
  @MimeRegistration(mimeType = "text/x-java", position = 0, service = CompletionProvider.class),
  @MimeRegistration(mimeType = "text/x-javascript", position = 1, service = CompletionProvider.class),
  @MimeRegistration(mimeType =  "text/xml", position = 2, service = CompletionProvider.class)
})
public class ActcCompletionProvider implements CompletionProvider{
  private CodeTemplates codetemplates = null;
  
  /**
   * 
   */
  public ActcCompletionProvider() {
  }

  /**
   *
   * @param i
   * @param jtc
   * @return
   */
  @Override
  public synchronized CompletionTask createTask(int queryType, JTextComponent jtc) {
    CompletionTask result;
    if (queryType == CompletionProvider.COMPLETION_QUERY_TYPE) {
      result = this.createTask(jtc);
    } else {
      return null;
    }
    return result;
  }
  
  /**
   * 
   * @param jtc
   * @return 
   */
  private synchronized CompletionTask createTask(JTextComponent jtc) {
    CompletionTask result;
    if (this.codetemplates == null) {
      Document document = jtc.getDocument();
      this.codetemplates = CodeTemplates.create(document);
    }
    AsyncCompletionQuery completionquery = new AsyncCompletionQueryImpl(this.codetemplates);
    result = new AsyncCompletionTask(completionquery, jtc);
    return result;
  }

  /**
   *
   * @param jtc
   * @param string
   * @return
   */
  @Override
  public int getAutoQueryTypes(JTextComponent jtc, String string) {
    return 0;
  }
}
