import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;


public class Reddit extends AnAction {
  public Reddit(){
    super(null, null, new ImageIcon(Reddit.class.getClassLoader().getResource("images/reddit.png")));
  }
  @Override
  public void actionPerformed(AnActionEvent event) {
    String redditSite = "https://www.reddit.com/";
    BrowserUtil.browse(redditSite);
  }
}
