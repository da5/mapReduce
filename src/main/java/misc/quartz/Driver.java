package misc.quartz;

import misc.quartz.job.DemoJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by arindam on 20/1/17.
 */
public class Driver {

    private static final String jobId = "demo-job1";
    public static void main(String[] args) throws Exception{
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Beans.xml");
//        DemoJob instance = (DemoJob)applicationContext.getBean("demoJob");
//        System.out.print("Hello " + instance.getDemoTask().getName()+" : ");
//        instance.getDemoTask().printMe();

        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        stdSchedulerFactory.initialize("quartz.properties");
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.start();

        if(!scheduler.checkExists(JobKey.jobKey(jobId))){
            JobDetail jobDetail = JobBuilder.newJob(DemoJob.class).withIdentity(jobId).build();

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("demo-trigger1").withSchedule(
                    CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
            ).build();
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }
}
