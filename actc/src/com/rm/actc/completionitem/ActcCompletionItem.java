package com.rm.actc.completionitem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Objects;
import javax.swing.text.JTextComponent;
import org.netbeans.lib.editor.codetemplates.api.CodeTemplate;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.CompletionUtilities;

/**
 *
 * @author Ricardo Marquez
 */
public class ActcCompletionItem implements CompletionItem {

  /**
   *
   * @param t
   * @return
   */
  public static String getKey(CodeTemplate t) {
    String result;
    if (t.getDescription() != null && t.getDescription().contains(":")) {
      result = fromCodeTemplate(t).getText();
    } else {
      result = null;
    }    
    return result;
  }

  private final String text;
  private final CodeTemplate codeTemplate;
  private final Color fieldColor = Color.decode("0x0000B2");

  /**
   *
   * @param e
   * @return
   */
  public static ActcCompletionItem fromCodeTemplate(CodeTemplate e) {
    ActcCompletionItem result = new ActcCompletionItem(e);
    return result;
  }

  /**
   *
   * @param text
   * @param codeTemplate
   */
  public ActcCompletionItem(String text) {
    this(text, null);
  }

  /**
   *
   * @param text
   * @param codeTemplate
   */
  public ActcCompletionItem(CodeTemplate codeTemplate) {
    this(textFromDescription(codeTemplate), codeTemplate);
  }

  /**
   *
   * @param codeTemplate
   * @return
   */
  private static String textFromDescription(CodeTemplate codeTemplate) {
    String description = codeTemplate.getDescription();
    if (description == null || !description.contains(":")) {
      throw new RuntimeException("Description should contain ':'." + codeTemplate.getAbbreviation());
    }
    String[] split = description.split(":");
    String result = split[0].trim();
    return result;
  }

  /**
   *
   * @param text
   * @param codeTemplate
   */
  private ActcCompletionItem(String text, CodeTemplate codeTemplate) {
    this.text = text;
    this.codeTemplate = codeTemplate;
  }

  public String getText() {
    return this.text;
  }

  /**
   *
   * @param jtc
   */
  @Override
  public void defaultAction(JTextComponent jtc) {
    RenderingUtils.insert(this, jtc);
  }

  /**
   *
   * @param ke
   */
  @Override
  public void processKeyEvent(KeyEvent ke) {
  }

  @Override
  public int getPreferredWidth(Graphics grphcs, Font font) {
    String desc = "";
    int result = CompletionUtilities.getPreferredWidth(this.text,
            desc, grphcs, font);
    return result;
  }

  @Override
  public void render(Graphics grphcs, Font font, // 
          Color defaultColor, Color bckgrndColor, //
          int width, int height, boolean selected) {
    Color color = selected ? Color.WHITE : this.fieldColor;
    if (this.codeTemplate == null) {
      color = Color.lightGray;
    }
    String desc = "";
    CompletionUtilities.renderHtml(null, this.text, desc, // 
            grphcs, font, color, //
            width, height, selected);
  }

  @Override
  public CompletionTask createDocumentationTask() {
    return null;
  }

  @Override
  public CompletionTask createToolTipTask() {
    return null;
  }

  @Override
  public boolean instantSubstitution(JTextComponent jtc) {
    return false;
  }

  @Override
  public int getSortPriority() {
    return 0;
  }

  @Override
  public CharSequence getSortText() {
    return this.text;
  }

  @Override
  public CharSequence getInsertPrefix() {
    return this.text;
  }

  /**
   *
   * @param text
   * @return
   */
  public String getGroupText(String text) {
    String result;
    if (!text.isEmpty()) {
      int indexOf = this.text.indexOf(".", text.length() + 1);
      if (indexOf > -1) {
        result = this.text.substring(0, indexOf + 1);
      } else {
        if (this.text.contains(text)) {
          result = this.text;
        } else {
          result = null;
        }

      }
    } else {
      result = null;
    }
    return result;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 23 * hash + Objects.hashCode(this.codeTemplate);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ActcCompletionItem other = (ActcCompletionItem) obj;
    if (!Objects.equals(this.codeTemplate, other.codeTemplate)) {
      return false;
    }
    return true;
  }

  /**
   *
   */
  public CodeTemplate getCodeTemplate() {
    return this.codeTemplate;
  }

}
