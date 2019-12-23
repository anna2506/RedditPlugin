import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.*;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class JavaFxBrowserView implements BrowserView {
  private static final String userAgent = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:60.0) Gecko/20100101 Firefox/61.0";
  private WebView browser;
  private WebEngine webEngine;

  private JFXPanel jfxPanel;
  private BrowserPanel panel;

  static {
    TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
              public java.security.cert.X509Certificate[] getAcceptedIssuers(){
                return null;
              }
              @Override
              public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType){

              }

              @Override
              public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType){

              }
            }
    };

    //Install the all-trusting trust manager
    try{
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    } catch(GeneralSecurityException e){
    }
  }
  public JavaFxBrowserView(){
  }

  public void setPanel(BrowserPanel panel){
    this.panel = panel;
  }

  @Override
  public void init(){
    reload();
  }

  @Override
  public void load(String url){
    Platform.runLater(()-> {
      webEngine.load(url);
    });
  }

  @Override
  public void reload(){
    jfxPanel = new JFXPanel();
    Platform.runLater(() -> {
      browser = new WebView();
      webEngine = browser.getEngine();
      webEngine.setUserAgent(userAgent);
      webEngine.setUserStyleSheetLocation(getClass().getResource("/default.css").toExternalForm());
      webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {

      });
    });
  }

  @Override
  public JComponent getNode(){
    Platform.runLater(() -> {
      BorderPane borderPane = new BorderPane();
      borderPane.setCenter(browser);
      Scene scene = new Scene(borderPane);
      jfxPanel.setScene(scene);
    });

    return jfxPanel;
  }
}
