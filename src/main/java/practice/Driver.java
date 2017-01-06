package practice;

import org.apache.hadoop.mapreduce.Job;
import practice.wordCount.WordCountDriver;

/**
 * Created by arindam on 7/1/17.
 */
public class Driver {
    public static void main(String[] args) throws Exception{

        Job job = null;
        if(args[0].equals("wordCount")){
            job = new WordCountDriver(args[0], args[1], args[2]).getJob();
        }

        if(job!=null){
            System.exit(job.waitForCompletion(true)?0:1);
        }

    }
}
