package com.xuqian.game.disruptor.demo3;

import java.util.concurrent.CountDownLatch;

import com.lmax.disruptor.dsl.Disruptor;
import com.xuqian.game.disruptor.demo1.TradeTransaction;

//消息生产者
public class TradeTransactionPublisher implements Runnable {
	Disruptor<TradeTransaction> disruptor;
	private CountDownLatch latch;
	private static int LOOP=10000000;//模拟一千万次交易的发生
	
	public TradeTransactionPublisher(CountDownLatch latch,Disruptor<TradeTransaction> disruptor) {
		this.disruptor = disruptor;
		this.latch = latch;
	}


	public void run() {
		TradeTransactionEventTranslator  tradeTransloator=new TradeTransactionEventTranslator();  
        for(int i=0;i<LOOP;i++){  
            disruptor.publishEvent(tradeTransloator);  
        }  
        latch.countDown(); 
	}

}
