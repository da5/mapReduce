package practice.songList.lib;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by arindam on 6/1/17.
 */
public class RadioUniqueListeners {

    public static class RadioFeedMapper extends Mapper<Object, Text, Text, Text> {
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException{
            StringTokenizer stringTokenizer = new StringTokenizer(value.toString(), "|");
            if(stringTokenizer.countTokens() == 5){
                Text userId = new Text(stringTokenizer.nextToken());
                Text trackId = new Text(stringTokenizer.nextToken());
                context.write(trackId, userId);
            }
        }
    }

    public static class RadioFeedReducer extends Reducer<Text, Text, Text, IntWritable> {
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
            Set<String> uniqueUserIds = new HashSet<String>();
            for(Text value: values){
                uniqueUserIds.add(value.toString());
            }
            context.write(key, new IntWritable(uniqueUserIds.size()));
        }
    }
/*
    public static void main(String[] args) throws Exception{
        Job job = Job.getInstance(new Configuration(), "uniqueListenerCount");
        job.setJarByClass(RadioUniqueListeners.class);
        job.setMapperClass(RadioFeedMapper.class);
        job.setReducerClass(RadioFeedReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true)?0:1);
    }
    */
}
