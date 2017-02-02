package misc.quartz.job;

import misc.quartz.task.DemoTask;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *  Created by arindam on 20/1/17.
 */
public class DemoJob implements Job {

    public void setDemoTask(DemoTask demoTask) {
        this.demoTask = demoTask;
    }

    public DemoTask getDemoTask() {
        return demoTask;
    }

    private DemoTask demoTask;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException{
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.print("URL : " + jobDataMap.getString("URL"));
        System.out.print(", emails : " + jobDataMap.getString("emails"));
        System.out.print(", timestamp : " + jobDataMap.getString("timestamp"));
        System.out.println(" Executed!");
    }


}
