package imran.demo.hackday;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import org.mortbay.log.Log;

import java.util.Calendar;
import java.util.Map;

public class MyBolt extends BaseRichBolt {

    private static final long serialVersionUID = 1L;
	private OutputCollector collector;

    private long print = 0;
    private int counter = 0;
    private long current = 0;

    @Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

    @Override
	public void execute(Tuple input) {
        Long nextNumber = input.getLongByField("number");

        if (counter == 0) {
            current = nextNumber;
            counter = 1;
        }
        else if (current == nextNumber) {
            counter++;
        }
        else {
            counter--;
        }

        long diff = Calendar.getInstance().getTimeInMillis() - MySpout.timeInMilliseconds;

        print++;
        if (print > 10000) {
            print = 0;
            Log.info("MyBolt - "+ diff + " majority number=" + current);
        }
    }

    @Override
	public void cleanup() {
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("number", "date", "description"));
	}
}
