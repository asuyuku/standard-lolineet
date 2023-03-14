package com.lolineet.standard.starter.snowflake;

import com.lolineet.standard.starter.exception.impl.InvalidSystemClockException;

/**
 * @author zhanghongwei
 * @Descirption Twitter_Snowflake算法
 * SnowFlake的结构如下(每部分用-分开):
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0
 * 41位时间戳(毫秒级)，注意，41位时间戳不是存储当前时间的时间戳，而是存储时间戳的差值（当前时间戳 - 开始时间戳）得到的值。
 * 这里的的开始时间戳，一般是我们的id生成器开始使用的时间，由我们程序来指定的（startTime属性）。
 * 10位的数据机器位，可以部署在1024个节点，包括5位 data center ID 和5位 worker ID
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间戳)产生4096个ID序号
 * 加起来刚好64位，为一个Long型。并且整个分布式系统内不会产生ID碰撞
 * SnowFlake的优点是，整体上按照时间自增排序，(由数据中心ID和机器ID作区分)，并且效率较高。
 * @description 雪花算法 Facebook
 * @date 2020/9/21 10:35
 */
public class SnowFlakeGenerator {

    /**
     * 开始时间戳
     */
    private static final long orig = 631468800000L;

    /**
     * Worker ID所占的位数
     */
    private final long workerBits = 5L;

    /**
     * DataCenter ID所占的位数
     */
    private final long dataCenterBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkID = ~(-1L << workerBits);

    /**
     * 支持的最大数据中心ID
     */
    private final long maxDataCenterID = ~(-1L << dataCenterBits);

    /**
     * Sequence所占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * Worker ID移位
     */
    private final long workerIDShift = sequenceBits;

    /**
     * DateCenter ID移位
     */
    private final long dataCenterShift = sequenceBits + workerBits;

    /**
     * Timestamp左移位
     */
    private final long timestampLeftShift = sequenceBits + workerBits + dataCenterBits;

    /**
     * Sequence Mask
     */
    private final long sequenceMask = ~(-1L << sequenceBits);//-1L ^ (-1L << sequenceBits)

    private long workerID;
    private long dataCenterID;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowFlakeGenerator(long workerID, long dataCenterID) {

        if (workerID > maxWorkID || workerID < 0L) {

            throw new IllegalArgumentException(String.format("worker ID can't be greater than %d or less than 0", maxWorkID));

        }

        if (dataCenterID > maxDataCenterID || dataCenterID < 0L) {

            throw new IllegalArgumentException(String.format("data center ID can't be greater than %d or less than 0", maxDataCenterID));

        }

        this.workerID = workerID;

        this.dataCenterID = dataCenterID;

    }


    /**
     * 获取下一个ID
     */
    public synchronized long next() {

        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {

            throw new InvalidSystemClockException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));

        }

        if (timestamp == lastTimestamp) {

            sequence = (sequence + 1) & sequenceMask;

            if (sequence == 0) {
                timestamp = tillNextMillis(lastTimestamp);
            }

        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - orig) << timestampLeftShift) |
                (dataCenterID << dataCenterShift) |
                (workerID << workerIDShift) |
                sequence;


    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    protected long tillNextMillis(long lastTimestamp) {

        long timestamp = timeGen();

        while (timestamp <= lastTimestamp) {

            timestamp = timeGen();

        }

        return timestamp;

    }

}