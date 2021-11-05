package drunk.tolstoy.ethWatch.Services;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CoinapiService {
    public String APIUri;
    @Autowired
    private Environment env;

    public CoinapiService() {
        this.APIUri = "https://rest-sandbox.coinapi.io/";
    }

    public String GetEthValue() {
        URL url;
        String inputLine;
        try {

            url = new URL(this.APIUri + "v1/trades/latest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-coinAPI-Key", env.getProperty("COINAPI_KEY"));
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
            return content.toString();

        } catch (MalformedURLException e) {
            System.err.println(e);
            return "";
        } catch (IOException e) {
            System.err.println(e);
            return "";
        }

    }
}
