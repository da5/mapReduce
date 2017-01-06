package practice.songList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import practice.songList.lib.RadioUniqueListeners;

/**
 * Created by arindam on 6/1/17.
 */
public class Driver {

    private static final String UNIQUE_LISTENER_COUNT = "uniqueListenerCount";

    private static Job uniqueListenerCount(String inputPath, String outputPath)throws Exception{
        Job job = Job.getInstance(new Configuration(), UNIQUE_LISTENER_COUNT);
        job.setJarByClass(RadioUniqueListeners.class);
        job.setMapperClass(RadioUniqueListeners.RadioFeedMapper.class);
        job.setReducerClass(RadioUniqueListeners.RadioFeedReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        return job;
    }

    public static void main(String[] args) throws Exception{

        Job job = null;
        if(args[0].equals(UNIQUE_LISTENER_COUNT)){
            job = uniqueListenerCount(args[1],args[2]);
        }

        if(job!=null){
            System.exit(job.waitForCompletion(true)?0:1);
        }

    }
}
