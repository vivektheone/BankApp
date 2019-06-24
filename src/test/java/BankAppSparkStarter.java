
//import uk.co.compendiumdev.MainTestPages;
//import uk.co.compendiumdev.spark.app.CompendiumDevAppsForSpark;

import java.net.HttpURLConnection;
import java.net.URL;

public class BankAppSparkStarter extends SparkStarter {

    private static BankAppSparkStarter starter;
    private final String host;
    private final String heartBeatPath;

    private BankAppSparkStarter(String host, String heartBeatPath){
        this.host = host;
        this.heartBeatPath = heartBeatPath;
    }

    public static BankAppSparkStarter get(String host, String heartBeatPath){

        if(BankAppSparkStarter.starter==null) {
            BankAppSparkStarter.starter = new BankAppSparkStarter(host, heartBeatPath);
        }
        return BankAppSparkStarter.starter;
    }

    public boolean isRunning(){

        try{
            HttpURLConnection con = (HttpURLConnection)new URL("http",host, sparkport, heartBeatPath).openConnection();
            return con.getResponseCode()==200;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void startServer() {
        String[] args = {};
        Bank.main(args);
    }

}