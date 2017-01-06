package practice.wordCount;

import misc.WordCount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import practice.wordCount.lib.WordCountMapper;
import practice.wordCount.lib.WordCountReducer;

import java.io.IOException;

/**
 * Created by arindam on 7/1/17.
 */
public class WordCountDriver {

    private String jobName;
    private String inputPath;
    private String outputPath;

    private Job job;

    private void createJob()throws IOException, InterruptedException{
        Configuration conf = new Configuration();
        job = Job.getInstance(conf, jobName);
        job.setJarByClass(WordCountDriver.class);
        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(WordCountReducer.class);
        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));
    }


    public WordCountDriver(String jobName, String inputPath, String outputPath) throws IOException, InterruptedException{
        this.jobName = jobName;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        createJob();
    }

    public Job getJob() {
        return job;
    }
}
