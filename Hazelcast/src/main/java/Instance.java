import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;

import java.net.UnknownHostException;

public class Instance {

    public static void StartInstance() throws UnknownHostException {
        Config config = HConfig.getConfig();
        Hazelcast.newHazelcastInstance(config);
    }
}
