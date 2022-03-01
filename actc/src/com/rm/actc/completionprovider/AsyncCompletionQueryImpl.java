package com.rm.actc.completionprovider;

import com.rm.actc.DocumentUtils;
import com.rm.actc.completionitem.ActcCompletionItem;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.text.Document;
import org.netbeans.lib.editor.codetemplates.api.CodeTemplate;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;

/**
 *
 * @author Ricardo Marquez
 */
class AsyncCompletionQueryImpl extends AsyncCompletionQuery {

  private final CodeTemplates codeTemplates;

  /**
   *
   * @param codeTemplates
   */
  protected AsyncCompletionQueryImpl(CodeTemplates codeTemplates) {
    this.codeTemplates = Objects.requireNonNull(codeTemplates);
  }

  /**
   *
   * @param crs
   * @param dcmnt
   * @param i
   */
  @Override
  protected void query(CompletionResultSet crs, Document dcmnt, int caretOffset) {
    try {
      boolean validInputs = this.isValidDocument(dcmnt, caretOffset);
      if (validInputs) {
        String text = DocumentUtils.extractText(dcmnt, caretOffset);
        List<CompletionItem> list = this.getItemsForText(text);
        crs.addAllItems(list);
      } else {
        System.out.println("invalid document or caret");
      }
    } finally {
      crs.finish();
    }
  }

  /**
   *
   * @param dcmnt
   * @param caretOffset
   * @return
   */
  private boolean isValidDocument(Document dcmnt, int caretOffset) {
    return dcmnt != null && caretOffset > -1;
  }

  /**
   *
   * @param text
   * @param crs
   */
  private List<CompletionItem> getItemsForText(String text) {
    CompletionItemsGrouping grouping = new CompletionItemsGrouping(text);
    CompletionItemResultSet resultSet = new CompletionItemResultSet();
    this.codeTemplates.stream()
                    .filter(new CodeTemplateContainsTextPredicate(text))
                    .map(this::mapCodeTemplate)
                    .collect(Collectors.groupingBy(grouping::group))
                    .forEach(resultSet::storeCompletionItemResult);
    List<CompletionItem> result = resultSet.list;
    return result;
  }

  /**
   *
   * @param e
   * @return
   */
  private CompletionItem mapCodeTemplate(CodeTemplate e) {
    CompletionItem result = ActcCompletionItem.fromCodeTemplate(e);
    return result;
  }
}
