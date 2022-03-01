package com.rm.actc.completionprovider;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.spi.editor.completion.CompletionItem;

/**
 *
 */
final class CompletionItemResultSet {

  protected List<CompletionItem> list = new ArrayList<>();

  /**
   *
   * @param key
   * @param value
   * @return
   */
  protected void storeCompletionItemResult(CompletionItem key, List<CompletionItem> value) {
    CompletionItem result;
    if (value.size() == 1) {
      result = value.get(0);
    } else {
      result = key;
    }
    list.add(result);
  }
  
}
