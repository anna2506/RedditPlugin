import javax.swing.*;

/**
 * Browser interface
 */
public interface BrowserView {
  public void init();
  public void load(String url);
  public void reload();
  public JComponent getNode();
  public void setPanel(BrowserPanel panel);
}
