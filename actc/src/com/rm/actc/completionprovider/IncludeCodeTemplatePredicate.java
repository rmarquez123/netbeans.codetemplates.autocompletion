package com.rm.actc.completionprovider;

import com.rm.actc.completionitem.ActcCompletionItem;
import java.util.function.Predicate;
import org.netbeans.lib.editor.codetemplates.api.CodeTemplate;

/**
 *
 */
final class IncludeCodeTemplatePredicate implements Predicate<CodeTemplate> {

  private final String text;

  /**
   *
   * @param dcmnt
   * @param caretOffset
   */
  protected IncludeCodeTemplatePredicate(String text) {
    this.text = text;
  }

  /**
   *
   * @param t
   * @return
   */
  @Override
  public boolean test(CodeTemplate t) {
    String key = ActcCompletionItem.getKey(t);
    boolean result;
    if (key != null) {
      result = key.startsWith(this.text);
    } else {
      result = false;
    }
    return result;
  }

  /**
   *
   * @return
   */
  @Override
  public String toString() {
    return "IncludeCodeTemplatePredicate{" + "text=" + text + '}';
  }

}
