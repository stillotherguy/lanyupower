package com.weaver.teams.monitor.quartz;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class TestCase {
	
	//@Test
	public void testRerun() throws SchedulerException, InterruptedException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sc = sf.getScheduler();
		JobDetail job1 = JobBuilder.newJob(Job1.class).withIdentity("job1", "group1").build();
		Trigger trigger1 = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger1", "group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3)).build();
		sc.scheduleJob(job1, trigger1);
		sc.start();
		TimeUnit.SECONDS.sleep(10);
		JobDetail job2 = JobBuilder.newJob(Job2.class).withIdentity("job2", "group2").build();
		Trigger trigger2 = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger2", "group2")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3)).build();
		sc.scheduleJob(job2, trigger2);
		TimeUnit.DAYS.sleep(1);
	}
	
	//妯℃嫙澶辫触閲嶈瘯浠诲姟
	@Test
	public void testContext() throws SchedulerException, InterruptedException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sc = sf.getScheduler();
		JobDetail job1 = JobBuilder
				.newJob(Job1.class)
				.withIdentity("job1", "group1").build();
		Trigger trigger1 = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger1", "group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(60)).build();
		sc.scheduleJob(job1, trigger1);
		sc.start();
		TimeUnit.DAYS.sleep(1);
	}
	
	@Test
	public void test() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sc = sf.getScheduler();
		JobDetail job2 = JobBuilder.newJob(Job2.class).withIdentity("job2", "group2").build();
		Trigger trigger2 = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger2", "group2")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1)).build();
		sc.scheduleJob(job2, trigger2);
		sc.start();
		sc.unscheduleJob(TriggerKey.triggerKey("trigger2", "group2"));
		TimeUnit.DAYS.sleep(1);
	}
	
	/**
	 * 妯℃嫙鏄惁澶辫触鍚庨噸璇曚娇鐢ㄧ殑鏄悓涓�涓猨ob瀹炰緥
	 * 鏄殑
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sc = sf.getScheduler();
		JobDetail job1 = JobBuilder.newJob(Job1.class).withIdentity("job1", "group1").build();
		Trigger trigger1 = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger1", "group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(100000)).build();
		sc.scheduleJob(job1, trigger1);
		sc.start();
		//sc.unscheduleJob(TriggerKey.triggerKey("trigger2", "group2"));
		TimeUnit.DAYS.sleep(1);
	}
	
	/**
	 * 娴嬭瘯reschedule鏂规硶(娉ㄦ剰鍒濆delay)
	 * 濡傛灉reschedule鏂规硶璋冪敤涔嬪悗锛屾棫鐨則rigger灏嗛攢姣侊紝涓簄ull锛堝鏋滄病鏈夊叾浠杍ob寮曠敤锛�
	 * 
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sc = sf.getScheduler();
		JobDetail job2 = JobBuilder.newJob(Job2.class).withIdentity("job2", "group2").build();
		Trigger trigger2 = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger2")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3)).build();
		Trigger trigger3 = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger3")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(10)).build();
		sc.scheduleJob(job2, trigger2);
		sc.start();
		System.out.println("------------trigger2" + sc.getTrigger(TriggerKey.triggerKey("trigger2")));
		System.out.println("------------trigger3" + sc.getTrigger(TriggerKey.triggerKey("trigger3")));
		TimeUnit.SECONDS.sleep(10);
		System.out.println("===========================");
		sc.rescheduleJob(TriggerKey.triggerKey("trigger2"), trigger3);
		//sc.unscheduleJob(TriggerKey.triggerKey("trigger2", "group2"));
		System.out.println("------------trigger2" + sc.getTrigger(TriggerKey.triggerKey("trigger2")));
		System.out.println("------------trigger3" + sc.getTrigger(TriggerKey.triggerKey("trigger3")));
		TimeUnit.DAYS.sleep(1);
	}
}
