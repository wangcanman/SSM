package com.crossoverJie.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author crossoverJie
 */
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    /**
     * 消费配置文件
     */
    private static String consumerProPath;

    public static void main(String[] args) throws IOException {
        // set up the producer
        consumerProPath = System.getProperty("product_path");
        KafkaProducer<String, String> producer = null;
        try {
            FileInputStream inputStream = new FileInputStream(new File(consumerProPath));
            Properties properties = new Properties();
            properties.load(inputStream);
            producer = new KafkaProducer<String, String>(properties);

        } catch (IOException e) {
            LOGGER.error("load config error", e);
        }

        try {
            // send lots of messages
            for (int i=0 ;i<10;i++){
                producer.send(new ProducerRecord<String, String>(
                        "TOPIC_USER_URL", "12345"));

            }
        } catch (Throwable throwable) {
            System.out.printf("%s", throwable.getStackTrace());
        } finally {
            producer.close();
        }

    }
}
