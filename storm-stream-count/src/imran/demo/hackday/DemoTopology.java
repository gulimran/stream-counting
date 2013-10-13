package imran.demo.hackday;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

public class DemoTopology {

    public static StormTopology buildTopology() {
		TopologyBuilder builder = new TopologyBuilder();

		builder.setSpout("mySpout", new MySpout(), 1);

		// Various (2) bolts -> shuffle grouping from spout
		builder.setBolt("myBolt", new MyBolt(), 1).shuffleGrouping("mySpout");
		// One single listing bolt calculating statistics
//		builder.setBolt("myBolt2", new ListingBolt(), 1).globalGrouping("fetcherBolt");

		return builder.createTopology();
	}

	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		Config conf = new Config();
		conf.setDebug(true);
		conf.setNumWorkers(2);
		conf.setMaxSpoutPending(1);
		StormSubmitter.submitTopology("demoTopology", conf, buildTopology());
    }
}
