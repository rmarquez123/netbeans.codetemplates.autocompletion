package com.rm.actc.completionprovider;

import com.rm.actc.completionitem.ActcCompletionItem;
import com.rm.actc.completionitem.GroupActcCompletionItem;
import org.netbeans.spi.editor.completion.CompletionItem;

/**
 *
 */
final class CompletionItemsGrouping {

  private final String text;

  /**
   * 
   * @param text 
   */
  public CompletionItemsGrouping(String text) {
    this.text = text;
  }

  /**
   *
   * @param item
   * @return
   */
  protected final CompletionItem group(CompletionItem item) {
    if (!(item instanceof ActcCompletionItem)) {
      throw new RuntimeException("Item is not instance of " + ActcCompletionItem.class.getSimpleName());
    }
    String groupText = ((ActcCompletionItem) item).getGroupText(this.text);
    CompletionItem result;
    if (groupText == null) {
      result = new GroupActcCompletionItem(null);
    } else if (groupText.equals(((ActcCompletionItem) item).getText())) {
      result = item;
    } else {
      result = new GroupActcCompletionItem(groupText);
    }
    return result;
  }
}
