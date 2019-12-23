import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;

/**
 * Main panel component
 */
public class BrowserPanel extends JPanel {
  private BrowserView browserView;
  public JButton buttonGo;
  /**
   * @param browserView BrowserView
   */
  public BrowserPanel(BrowserView browserView){
    this.browserView = browserView;
    initWebView();
  }

  public JPanel getTopControllers(){
    JPanel topControlPanel = new JPanel();
    GridBagLayout topControlPanelLayout = new GridBagLayout();

    topControlPanel.setLayout(topControlPanelLayout);

    buttonGo = new JButton("");
    buttonGo.setPreferredSize(new Dimension(40, 30));

    topControlPanel.add(buttonGo);

    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.weightx = 0;
    gridBagConstraints.weighty = 0;
    topControlPanelLayout.setConstraints(buttonGo, gridBagConstraints);

    buttonGo.setEnabled(true);

    buttonGo.addActionListener(event -> this.webBrowserLoad());

    return topControlPanel;
  }

  private void webBrowserLoad(){
    String redditSite = "https://www.reddit.com/";
    browserView.load(redditSite);
  }

  private void initWebView(){
    Platform.setImplicitExit(false);
    SwingUtilities.invokeLater(()->{
      removeAll();
      GridBagLayout layout = new GridBagLayout();
      setLayout(layout);

      JComponent topControllers = getTopControllers();
      add(topControllers);

      browserView.init();
      browserView.setPanel(this);

      JComponent webPanel = browserView.getNode();
      add(webPanel);

      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.fill = GridBagConstraints.BOTH;

      gridBagConstraints.gridwidth = 0;
      gridBagConstraints.weightx = 1;
      gridBagConstraints.weighty = 0;
      layout.setConstraints(topControllers, gridBagConstraints);

      gridBagConstraints.gridwidth = 0;
      gridBagConstraints.weightx = 1;
      gridBagConstraints.weighty = 10;
      layout.setConstraints(webPanel, gridBagConstraints);

      validate();
      repaint();
      buttonGo.setEnabled(true);
    });
  }
}
