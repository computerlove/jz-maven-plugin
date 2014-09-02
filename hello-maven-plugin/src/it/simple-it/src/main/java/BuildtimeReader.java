
import java.io.IOException;
import java.lang.IllegalStateException;
import java.util.Properties;

public class BuildtimeReader {

    public String timestamp;

    public BuildtimeReader() throws IOException {
        Properties versionProps = new Properties();
        versionProps.load(getClass().getResourceAsStream("build.properties"));
        timestamp = versionProps.getProperty("build.timestamp");
        if(timestamp == null){
            throw new IllegalStateException("build.timestamp not found");
        }
    }
}