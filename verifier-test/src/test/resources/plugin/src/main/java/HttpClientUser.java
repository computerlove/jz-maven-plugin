
import org.apache.http.impl.client.HttpClients;

public class HttpClientUser {
    public org.apache.http.impl.client.CloseableHttpClient getClient(){
        return HttpClients.createDefault();
    }
}
