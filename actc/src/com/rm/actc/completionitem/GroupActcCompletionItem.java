package com.rm.actc.completionitem;

import java.util.Objects;

/**
 *
 * @author Ricardo Marquez
 */
public final class GroupActcCompletionItem extends ActcCompletionItem {

  private final String textCopy;

  /**
   *
   * @param text
   */
  public GroupActcCompletionItem(String text) {
    super(text);
    this.textCopy = text;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.textCopy);
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
    final GroupActcCompletionItem other = (GroupActcCompletionItem) obj;
    if (!Objects.equals(this.textCopy, other.textCopy)) {
      return false;
    }
    return true;
  }
  

}
