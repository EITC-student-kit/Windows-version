package javaCc.timetable;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Sten on 3/18/14.
 */
public class GetIcal {
    /**
     * @param
     * @throws java.io.IOException
     */
    public String grabIcal(String Strurl) throws IOException {
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
            System.out.println ("I/O error: ei leia noh " + e);
        }

        URL url = new URL("https://itcollege.ois.ee/timetable/ical?student_id=2800&key=50139976272982d7897e8225d45e36a5e74870f0");
        File fail = new File("C:/Users/Sten/Documents/ICal.txt");
        org.apache.commons.io.FileUtils.copyURLToFile(url, fail);

        return fail.getPath();
    }

}
