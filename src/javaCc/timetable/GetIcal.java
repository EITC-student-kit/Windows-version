package javaCc.timetable;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

/**
 * Created by Sten on 3/18/14.
 */
public class GetIcal {

    public FileOutputStream grabIcal(String Strurl) {
        FileOutputStream fos = null;
        try {

            URL url = new URL(Strurl);
            System.out.println("Opening connection to " + Strurl + "...");
            trustManager();
            //Open connection with url
            URLConnection urlC = url.openConnection();

            // Copy resource to local file, use remote file
            // if no local file name specified
            InputStream is = url.openStream();

            if (Strurl.length() < 2) {
                String localFile = null;
                // Get only file name
                StringTokenizer st = new StringTokenizer(url.getFile(), "/");
                while (st.hasMoreTokens())
                    localFile = st.nextToken();
                assert localFile != null;
                fos = new FileOutputStream(localFile);
            } else
                fos = new FileOutputStream("C:/Users/Sten/Documents/ICal.txt");
            int oneChar;
            while ((oneChar = is.read()) != -1) {
                fos.write(oneChar);
            }
            is.close();
            fos.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        return fos;
    }

    private void trustManager() {
        System.setProperty("jsse.enableSNIExtension", "false");
        // Create a new trust manager that trust all certificates
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.println("I/O error: " + e);
        }

    }
}
