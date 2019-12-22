import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;


public class Reddit extends AnAction {
  public Reddit(){ }
  @Override
  public void actionPerformed(AnActionEvent event) {
    String redditSite = "https://www.reddit.com/";
    BrowserUtil.browse(redditSite);
  }
}
