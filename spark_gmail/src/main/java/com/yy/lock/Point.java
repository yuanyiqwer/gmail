package com.yy.lock;

import lombok.Data;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/** @ClassName Point @Author yy @Description @Date 2021/11/9 14:05 @Version 1.0 */
@Data
public class Point {
    private double x, y;
    private final StampedLock s1 = new StampedLock();

    /**
     * * 写锁
     *
     * @param deltaX
     * @param deltaY
     */
    void move(double deltaX, double deltaY) {
        final long stamp = s1.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            s1.unlockWrite(stamp);
        }
    }

    /***
     * 乐观锁
     * @return
     */
    double distanceFromOrigin() {
        //stamp==标记
        long stamp = s1.tryOptimisticRead();
        double currentX = x, currentY = y;
        //如果有写锁被占用，可能造成数据不一致，所以要切换到普通读锁模式
        if (!s1.validate(stamp)) {
            System.out.println(1);
            stamp = s1.readLock();
            try{
                currentX = x;
                currentY = y;
            }
            finally{
                s1.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX*currentX+currentY*currentY);
    }


    void  moveIfAtOrigin(double newX, double newY){
//        final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//        final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
//        readLock.lock();

        long stamp = s1.readLock();
         try{
             while (x==0.0&&y==0.0){
//                 数字0表示没有写锁被授权访问
                  long ws = s1.tryConvertToWriteLock(stamp);
                  if(ws!=0L){
                      stamp=ws;
                      x=newX;
                      y=newY;
                      break;
                  }else{
                      //显示转化为写锁
                      s1.unlockRead(stamp);
                      stamp=s1.writeLock();
                  }

             }
             x=x+1;
             y=y+1;
         }finally{
             //显示的释放锁（不管是什么锁）
             s1.unlock(stamp);
         }

    }

}
