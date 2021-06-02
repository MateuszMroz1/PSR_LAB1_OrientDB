import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;

public class MapPut {

    final private static Random r = new Random(System.currentTimeMillis());

    public static String NewLigPut(String name, int number_of_team) throws UnknownHostException {
        Config config = HConfig.getConfig();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        Map<Long, Liga> ligi = instance.getMap("Liga");
        Long key1 = (long) Math.abs(r.nextInt());
        Liga liga = new Liga(name, number_of_team);

        ligi.put(key1, liga);
        return "PUT " + key1 + " => " + liga;
    }
}
