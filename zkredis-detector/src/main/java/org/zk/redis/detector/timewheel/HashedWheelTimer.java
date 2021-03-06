package org.zk.redis.detector.timewheel;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**  
 *   
 * 描述：一个基于时间hash的wheel超时器  
 * 创建时间：2011-9-16上午11:44:02  
 * @author yq76034150  
 *  
 */  
public class HashedWheelTimer {  
    volatile int tick = 1;  
    volatile int currentWheel;  
    private Queue<TimeOut>[] wheel;  
    final int mask;  
    private long delay;  
    //private int ticksPerWheel;  
    private long tickDuration;  
    //private long roundDuration;  
      
    /**  
     * 描述：  
     * @param ticksPerWheel 一圈多少tick  
     */  
    public HashedWheelTimer(int ticksPerWheel, long tickDuration, long delay){  
        mask = ticksPerWheel - 1;  
        this.delay = delay;   
        //this.ticksPerWheel = ticksPerWheel;  
        this.tickDuration = tickDuration;  
        //this.roundDuration = tickDuration * ticksPerWheel;  
        createWheel(ticksPerWheel);  
    }  
      
    /**  
     *   
     * 描述：wheel中填充数据  
     * @param timeout  
     */  
    public void newTimeOut(TimeOut timeout){  
        long shift = delay / tickDuration;  
        //long remainRounds = delay / roundDuration;  
        int stopIndex = currentWheel + (int)shift & mask;  
          
        wheel[stopIndex].offer(timeout);  
    }  
      
    /**  
     *   
     * 描述：外部线程调用，外部线程调用间隔必须和tickDuration一致  
     */  
    public void run(long start){  
        //模拟外部执行线程执行间隔数  
        try {  
            Thread.sleep(tickDuration);  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        tick++;  
        currentWheel = currentWheel + 1 & mask;  
        long deadline = start + tick * tickDuration;  
          
        Queue<TimeOut> queue = wheel[currentWheel];  
        TimeOut timeOut = queue.peek();  
        if(timeOut != null){  
            long firstDeadline = timeOut.getDeadline();  
            boolean isDead = firstDeadline <= deadline;  
            //if(firstDeadline <= deadline){  
                while(isDead){ //对一个tick中的队列元素递归检查超时  
                    queue.remove();  
                    System.out.println("tick: " + tick + " delete: " + currentWheel + timeOut);  
                      
                    timeOut = queue.peek();  
                    if(timeOut != null){  
                        firstDeadline = timeOut.getDeadline();  
                        isDead = firstDeadline <= deadline;  
                    } else {  
                        break;  
                    }  
                }  
            //}  
              
        }  
    }  
  
    /**  
     *   
     * 描述：初始化wheel  
     * @param ticksPerWheel  
     */  
    private void createWheel(int ticksPerWheel) {  
        wheel = new Queue[ticksPerWheel];  
        for(int i = 0; i < ticksPerWheel; i++){  
            wheel[i] = new ConcurrentLinkedQueue<TimeOut>();  
        }  
    }  
  
    /**  
     * 描述：  
     * @param args  
     */  
    public static void main(String[] args) {  
        long tickDuration = 1000;  
        long delay = 9000;  
        HashedWheelTimer timer = new HashedWheelTimer(4, tickDuration, delay);  
          
        long start = System.currentTimeMillis();  
        TimeOut timeout = new TimeOut(start, start + delay, 1);  
        timer.newTimeOut(timeout);  
        timer.run(start);  
          
        //4s后 保证和之前的落在一个区间  
        for(int i = 0; i < 3; i++){  
            timer.run(start);  
        }  
  
        TimeOut timeout2 = new TimeOut(System.currentTimeMillis(), System.currentTimeMillis() + delay, 2);  
        timer.newTimeOut(timeout2);  
        timer.run(start);  
              
        //更改表头的时间 queue结构的就会阻塞  
        timeout.setTime(start + 19000);  
        timeout.setDeadline(start + 19000 + delay);  
        timer.newTimeOut(timeout);  
        timer.run(start);  
          
        for(int i = 0; i < 30; i++){  
            timer.run(start);  
        }  
    }  
  
}  