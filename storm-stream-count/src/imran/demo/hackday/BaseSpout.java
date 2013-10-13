package imran.demo.hackday;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;

import java.util.Map;

public class BaseSpout extends BaseRichSpout {

private static final long serialVersionUID = 1L;

	protected SpoutOutputCollector collector;
	Map conf;
	TopologyContext context;

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		this.conf = conf;
		this.context = context;
	}

	@Override
	public void close() {
	}

	@Override
	public void nextTuple() {
	}

	@Override
	public void ack(Object msgId) {
	}

	@Override
	public void fail(Object msgId) {
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}
}
