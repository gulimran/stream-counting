package imran.demo.hackday;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import org.mortbay.log.Log;

import java.io.IOException;
import java.util.*;

public class MySpout extends BaseSpout {

    private static final long serialVersionUID = 1L;
//    private static final String FILE_NAME = "5vals_5000000.nn";
    private static final String FILE_NAME = "5vals_5000000.nn";

	Queue<Long> numbersQueue = new LinkedList<Long>();
	List<Long> numbers;

    public static long timeInMilliseconds = 0;

	public MySpout() {
        try {
            this.numbers = NumbersQueue.readNumbers(FILE_NAME);
            Log.info("MySpout - Start time " + Calendar.getInstance().getTime());
//            timeInMilliseconds =  Calendar.getInstance().getTimeInMillis();
            Log.info("MySpout - numbers read from disk " + FILE_NAME);
        }
        catch (IOException e) {
            e.printStackTrace();
            this.numbers = new ArrayList<Long>();
        }
    }

	@Override
	public void nextTuple() {
		Long nextNumber = numbersQueue.poll();
		if(nextNumber != null) {
//            Log.info("MySpout - emitting " + nextNumber);
			collector.emit(new Values(nextNumber), nextNumber);
		}
	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		super.open(conf, context, collector);
		for(Long number : numbers) {
			numbersQueue.add(number);
		}

        timeInMilliseconds =  Calendar.getInstance().getTimeInMillis();
	}

	@Override
	public void ack(Object id) {
		numbersQueue.add((Long) id);
	}

	@Override
	public void fail(Object id) {
		numbersQueue.add((Long) id);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("number"));
	}


}
