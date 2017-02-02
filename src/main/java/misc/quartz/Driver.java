package misc.quartz;

import misc.quartz.job.DemoJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by arindam on 20/1/17.
 */
public class Driver {

    private static final String jobId = "demo-job1";
    public static void main(String[] args) throws Exception{

        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        stdSchedulerFactory.initialize("quartz.properties");
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.start();

        JobDetail jobDetail;
        if(!scheduler.checkExists(JobKey.jobKey(jobId))){
            jobDetail = JobBuilder.newJob(DemoJob.class)
                    .withIdentity(jobId)
                    .usingJobData("timestamp",String.valueOf(System.currentTimeMillis()))
                    .usingJobData("URL", "dummy_url")
                    .usingJobData("emails", "arindam.das@xyz.com, dasarindam@vmware.com")
                    .build();
        }else{
            jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobId)).getJobBuilder()
                    .usingJobData("timestamp",String.valueOf(System.currentTimeMillis()))
                    .build();
        }
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("demo-trigger1").withSchedule(
                CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
        ).build();
        Set<Trigger> triggerSet = new HashSet<Trigger>();
        triggerSet.add(trigger);
        scheduler.scheduleJob(jobDetail, triggerSet, true);
    }
}
