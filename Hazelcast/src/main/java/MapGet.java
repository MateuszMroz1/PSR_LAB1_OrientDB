import com.hazelcast.aggregation.Aggregators;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.net.UnknownHostException;
import java.util.Map;

public class MapGet {

    public static void MinTeam() throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Long, Liga> ligi = client.getMap("Liga");
        for(Map.Entry<Long, Liga> e : ligi.entrySet()) {
            System.out.println(e.getKey() + " => " + e.getValue());
        }

        System.out.println("Najmniejszą ilością drużyn jest: "+ ligi.aggregate(Aggregators.integerMin("number_of_team")));
    }

    public static void ShowByKey(long key) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
        IMap<Long, Liga> ligi = client.getMap( "Liga" );
        System.out.println("Liga o kluczu "+ key+ ":\n");
        for(Map.Entry<Long, Liga> e : ligi.entrySet()){
            if(e.getKey().equals(key)) {
                System.out.println(e.getKey() + " => " + e.getValue());
            }
        }
    }


    public static void ShowAll() throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
        IMap<Long, Liga> ligi = client.getMap( "Liga" );
        System.out.println("Wszystkie ligi: ");
        for(Map.Entry<Long, Liga> e : ligi.entrySet()){
            System.out.println(e.getKey() + " => " + e.getValue());
        }
    }
}
