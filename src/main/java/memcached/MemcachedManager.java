package memcached;

import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Memcached Sample.
 * WindyHana's Solanara - Memcached http://www.solanara.net/solanara/memcached
 * java test.MemcachedSample
 */
public class MemcachedManager {

    final static Logger logger = LoggerFactory.getLogger(MemcachedManager.class);
    /**
     * 테스트 함수
     * @param args
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        MemcachedClient c;
        try {
            c = new MemcachedClient(new InetSocketAddress("localhost", 11211));
            c.set("someKey", 3600, new HashMap<String, Object>());
            c.set("test", 1, "test");
            Map<String, Object> myObjeckt = (Map<String, Object>)c.get("someKey");
            myObjeckt.put("key", "value");
            c.set("someKey", 3600, myObjeckt);
            myObjeckt = (Map<String, Object>)c.get("someKey");
            logger.debug("Memcached accessed!");
            logger.debug(myObjeckt.toString());
            logger.debug((String)c.get("test"));
            myObjeckt.keySet().stream().forEach(key -> {
                logger.debug(key);
            });
            c.delete("someKey2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}