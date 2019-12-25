import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;

/**
 * Main panel component
 */
public class BrowserPanel extends JPanel {
  private BrowserView browserView;
  public JButton today;
  public JButton week;
  public JButton month;
  public JButton year;
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

    today = new JButton("Today");
    week = new JButton("Week");
    month = new JButton("Month");
    year = new JButton("Year");
    //today.setPreferredSize(new Dimension(40, 30));

    topControlPanel.add(today);
    topControlPanel.add(week);
    topControlPanel.add(month);
    topControlPanel.add(year);

    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.weightx = 0;
    gridBagConstraints.weighty = 0;
    topControlPanelLayout.setConstraints(today, gridBagConstraints);
    topControlPanelLayout.setConstraints(week, gridBagConstraints);
    topControlPanelLayout.setConstraints(month, gridBagConstraints);
    topControlPanelLayout.setConstraints(year, gridBagConstraints);

    today.setEnabled(true);
    week.setEnabled(true);
    month.setEnabled(true);
    year.setEnabled(true);

    today.addActionListener(actionEvent -> this.webTodayTop());
    week.addActionListener(actionEvent -> this.webWeekTop());
    month.addActionListener(actionEvent -> this.webMonthTop());
    year.addActionListener(actionEvent -> this.webYearTop());

    return topControlPanel;
  }

  private void webTodayTop(){
    String redditSite = "https://www.reddit.com/top/?t=day";
    browserView.load(redditSite);
  }

  private void webWeekTop(){
    String redditSite = "https://www.reddit.com/top/?t=week";
    browserView.load(redditSite);
  }

  private void webMonthTop(){
    String redditSite = "https://www.reddit.com/top/?t=month";
    browserView.load(redditSite);
  }

  private void webYearTop(){
    String redditSite = "https://www.reddit.com/top/?t=year";
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
      today.setEnabled(true);
    });
  }
}
