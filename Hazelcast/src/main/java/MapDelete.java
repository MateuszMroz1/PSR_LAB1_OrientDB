import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.net.UnknownHostException;
import java.util.Map;

public class MapDelete {
    public static void DeleteByKey(Long key) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
        IMap<Long, Liga> ligi = client.getMap( "Liga" );
        for(Map.Entry<Long,Liga> lig : ligi.entrySet()){
            if(lig.getKey().equals(key)){
                ligi.remove(lig.getKey());
                System.out.print("Usunięto rekord:\n");
                System.out.println(lig.getKey() + " => " + lig.getValue());
            }

        }

    }


    public static void DeleteAll() throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
        IMap<Long, Liga> ligi = client.getMap( "Liga" );
        ligi.evictAll();
    }
}
