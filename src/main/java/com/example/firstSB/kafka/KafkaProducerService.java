package com.example.firstSB.kafka;

import com.example.firstSB.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaProducerService {

    private static String topicName = "employee.firstSB.topic";

    @Autowired
    KafkaTemplate<String, Employee> kafkaTemplate;

    public String sendSimpleMessage(Employee employee) throws JsonProcessingException {

        Message<Employee> message = MessageBuilder
                .withPayload(employee)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        CompletableFuture future = (CompletableFuture) kafkaTemplate.send(message);

        /*
        ListenableFuture<SendResult<String, Employee>> future = (ListenableFuture<SendResult<String, Employee>>) kafkaTemplate.send(message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Employee>>() {
            public void onSuccess(SendResult<String, Employee> result) {
                System.out.println("Sent message=[" + employee.getEmpid() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + employee.getEmpid() + "] due to : " + ex.getMessage());
            }
        });
        */
        return "Success";
    }
}
