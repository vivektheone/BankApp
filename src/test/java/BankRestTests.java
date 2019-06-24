import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankRestTests {

    private BasicHttp http;

    @BeforeAll
    public static void appHealthCheck(){
        BankAppSparkStarter.get("localhost", "/health" ).startSparkAppIfNotRunning(4567);
    }

    @BeforeEach
    public void setupBasicHttp(){
        http = new BasicHttp("http","localhost", 4567);
    }

    @Test
    public void canAccessHealthEndpoint(){
        Assertions.assertEquals("Bank is up!", http.getPageContents("/health"));
    }

    @Test
    public void canAccessAccounts(){
        Assertions.assertEquals("Watch console...", http.getPageContents("/accounts"));
    }



}