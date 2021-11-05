package drunk.tolstoy.ethWatch.Services;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.TimeZone;

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

    private String CallAPI(String function) {
        URL url;
        String inputLine;
        try {

            url = new URL(this.APIUri + function);
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

    private String DateToISO1806(LocalDateTime d) {
        DateTimeFormatter tz = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
        return d.atOffset(ZoneOffset.UTC).format(tz);
    }

    public String GetEthValue() {
        return this.CallAPI("v1/exchangerate/ETH/USD");
    }

    public String GetHistory() {
        String now = DateToISO1806(LocalDateTime.now());
        String weekBefore = DateToISO1806(LocalDateTime.now().minus(1, ChronoUnit.WEEKS));
        return this
                .CallAPI("v1/exchangerate/ETH/USD/history?time_period_start=" + weekBefore + "&time_period_end=" + now);
    }
}
