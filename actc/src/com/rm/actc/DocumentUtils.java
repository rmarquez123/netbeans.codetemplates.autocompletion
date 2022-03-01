package com.rm.actc;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Ricardo Marquez
 */
public class DocumentUtils {

  /**
   *
   */
  private DocumentUtils() {
  }

  /**
   *
   * @param dcmnt
   * @param caretOffset
   * @throws RuntimeException
   */
  public static String extractText(Document dcmnt, int caretOffset) {
    int offset = getWordOffSet(dcmnt, caretOffset);
    int length = caretOffset - offset;
    String text;
    try {
      text = dcmnt.getText(offset, length);
    } catch (BadLocationException ex) {
      throw new RuntimeException(ex);
    }
    return text;
  }

  /**
   *
   * @param d
   * @param caretOffset
   * @return
   */
  public static int getWordOffSet(Document doc, int caretOffset) {
    if ( !(doc instanceof StyledDocument)) {
      throw new RuntimeException();
    }
    Element lineElement = ((StyledDocument) doc).getParagraphElement(caretOffset);
    int result = lineElement.getStartOffset();
    while (result + 1 < lineElement.getEndOffset()) {
      try {
        if (doc.getText(result, 1).charAt(0) != ' ') {
          break;
        }
      } catch (BadLocationException ex) {
        throw new RuntimeException(ex);
      }
      result++;
    }
    return result;
  }
}
