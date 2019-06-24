import spark.Spark;

public abstract class SparkStarter {

    protected int sparkport = 4567;

    public abstract boolean isRunning();
    public abstract void startServer();

    public void startSparkAppIfNotRunning(int expectedPort){
        sparkport = expectedPort;
        try {
            if(!isRunning()) {
                startServer();
            }
        }catch(IllegalStateException e){
            e.printStackTrace();
        }
        waitForServerToRun();
    }

    private void waitForServerToRun() {
        int tries = 10;
        while(tries>0) {
            if(!isRunning()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }else{
                return;
            }
            tries --;
        }
    }

    public void killServer(){
        Spark.stop();
        int tries = 10;
        while(tries>0) {
            if(isRunning()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }else{
                return;
            }
            tries --;
        }
    }

    public int getPort() {
        return sparkport;
    }
}