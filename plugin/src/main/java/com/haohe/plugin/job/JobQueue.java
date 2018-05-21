package com.haohe.plugin.job;

import com.haohe.plugin.dom.model.ClassModel;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Administrator on 2016/12/17.
 */
public class JobQueue {
    static Queue<ClassModel> jobs = new ArrayBlockingQueue(200);

    public static void add(ClassModel classModel){
        jobs.add(classModel);
    }
    public static ClassModel next(){
        return jobs.poll();
    }
    public static boolean isEmpty(){
        return jobs.isEmpty();
    }
    public static Queue<ClassModel> getJobs() {
        return jobs;
    }
}
