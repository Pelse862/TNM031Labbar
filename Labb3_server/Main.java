import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.security.*;
import java.util.StringTokenizer;




public class Main {
    static final String KEYSTORE = "jpatkeystore.ks";
    static final String TRUSTSTORE = "jpattruststore.ks";
    static final String STOREPASSWD = "changeit";
    static final String ALIASPASSWD = "changeit";
    static final int DEFAULT_PORT = 8189;
    private int port;

    Main(int port){
        this.port = port;
    }


    public static void main(String[] args)
    {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);

        }

        Main addserver = new Main(port);
        addserver.run();
    }


    public void run(){
        try{
            KeyStore ks = KeyStore.getInstance("JCEKS");
            ks.load(new FileInputStream(KEYSTORE), STOREPASSWD.toCharArray());

            KeyStore ts = KeyStore.getInstance("JCEKS");
            ts.load(new FileInputStream(TRUSTSTORE), STOREPASSWD.toCharArray());

            KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" );
            kmf.init(ks, ALIASPASSWD.toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance( "SunX509" );
            tmf.init( ts );

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(),tmf.getTrustManagers(),null);
            SSLServerSocketFactory sslServerFactory = sslContext.getServerSocketFactory();
            SSLServerSocket sss = (SSLServerSocket) sslServerFactory.createServerSocket( port );
            sss.setEnabledCipherSuites(sss.getSupportedCipherSuites());

            System.out.println("\n>>>>> SecureAdditionServer: active");
            SSLSocket incoming = (SSLSocket)sss.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
            PrintWriter out = new PrintWriter(incoming.getOutputStream(),true);

            //Utför valt uppgift, spara hämta och deleta.


        }
        catch (Exception x)
        {

        }
    }


}
