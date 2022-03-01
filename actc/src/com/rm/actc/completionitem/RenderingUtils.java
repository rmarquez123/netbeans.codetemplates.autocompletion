package com.rm.actc.completionitem;

import com.rm.actc.DocumentUtils;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.completion.Completion;

/**
 *
 * @author Ricardo Marquez
 */
class RenderingUtils {
  private RenderingUtils() {

  }
  
  /**
   * 
   * @param item
   * @param jtc 
   */
  static void insert(ActcCompletionItem item, JTextComponent jtc) {
    try {
      int caretoffset = jtc.getCaretPosition();
      StyledDocument doc = (StyledDocument) jtc.getDocument();
      String s = DocumentUtils.extractText(doc, caretoffset);
      int len = s.length();

      int offset = caretoffset - len;
      doc.remove(offset, len);
      if (item.getCodeTemplate() != null) {
        String code = item.getCodeTemplate().getParametrizedText();
        doc.insertString(offset, code, null);
      } else {
        doc.insertString(offset, item.getText(), null);
      }
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
    Completion.get().hideAll();
  }



}
