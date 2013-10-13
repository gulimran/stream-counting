package imran.demo.hackday;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.utils.Utils;
import org.junit.Test;
import org.mortbay.log.Log;

public class DemoTopologyTest {

    @Test
    public void testDemoTopology() {
        Config conf = new Config();
		conf.setDebug(false);
		conf.setNumWorkers(2);

        Log.info("backtype.storm.Config=" + conf.entrySet());

		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Demo", conf, DemoTopology.buildTopology());
		Utils.sleep(30000);
		cluster.killTopology("Demo");
		cluster.shutdown();
    }
}
