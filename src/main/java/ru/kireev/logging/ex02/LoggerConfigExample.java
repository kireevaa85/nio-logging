package ru.kireev.logging.ex02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergey
 * created on 13.08.18.
 */
public class LoggerConfigExample {
  private static final Logger logger = LoggerFactory.getLogger(LoggerConfigExample.class);
  private long counter = 0;

  public static void main(String[] args) throws InterruptedException {
    new LoggerConfigExample().loop();
  }

  private void loop() throws InterruptedException {
    while (!Thread.currentThread().isInterrupted()) {
      logger.info("info level:{}", counter);
      logger.error("error level:{}", counter);
      counter++;
      Thread.sleep(3_000);
    }
  }
}
