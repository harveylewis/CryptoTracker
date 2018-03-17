import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Coin {
    private String coinName;
    private String coinTag;
    private String priceUrl;
    private float btcPrice;
    private float usdPrice;
//
    public Coin(String coinName, String coinTag){
        this.coinName = coinName;
        this.coinTag = coinTag;
        this.priceUrl = "https://min-api.cryptocompare.com/data/price?fsym=" + coinTag + "&tsyms=BTC,USD";
    }

    public void getPrice() throws Exception {

        String getUrl = this.priceUrl;

        URL obj = new URL(getUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String JsonResponse = response.toString();
        JSONObject Jsonobject = new JSONObject(JsonResponse);
        double btcDouble = Jsonobject.getDouble("BTC");
        double usdDouble = Jsonobject.getDouble("USD");

        this.btcPrice = (float)btcDouble;
        this.usdPrice = (float)usdDouble;
    }

    public float getBtcPrice() {
        return btcPrice;
    }
    public float getUsdPrice() {
        return usdPrice;
    }
    public String getCoinName(){return coinName;}
    public String getCoinTag() {return coinTag;}
}
