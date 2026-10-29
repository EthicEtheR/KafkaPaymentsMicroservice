package com.aether.TransferService.service;

import com.aether.TransferService.error.TransferServiceException;
import com.aether.TransferService.model.TransferEventEntity;
import com.aether.core.error.NotRetryableException;
import com.aether.core.error.RetryableException;
import com.aether.core.events.DepositReqEvent;
import com.aether.core.events.WithdrawReqEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService{

    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final Environment environment;
    private final RestTemplate restTemplate;

    @Override
    @Transactional(value = "kafkaTransactionManager",rollbackFor ={TransferServiceException.class,
            ConnectException.class} )
    public String transfer(TransferEventEntity eventEntity) {
        log.info("Going to creating both events in same transaction");

        WithdrawReqEvent withdrawReqEvent=new WithdrawReqEvent(eventEntity.getSenderId(),
                eventEntity.getReceiverId(),
                eventEntity.getAmount());

        DepositReqEvent depositReqEvent =new DepositReqEvent(eventEntity.getSenderId(),
                eventEntity.getReceiverId(),
                eventEntity.getAmount());
        try{
            kafkaTemplate.send(environment.getProperty("withdraw.money.topic"),withdrawReqEvent);
            log.info("Sent event to withdraw topic");

            callExternalApi();

            kafkaTemplate.send(environment.getProperty("deposit.money.topic"), depositReqEvent);
            log.info("Sent event to deposit topic");
        } catch (Exception e) {
            log.error("Got an exception: {}", String.valueOf(e));
            throw new TransferServiceException("Transfer Exception occurs ,Rolling back");
        }



        return "Transfer Completed Successfully ";
    }


    private void callExternalApi(){
        String url="http://localhost:8083/check/200";
          log.info("Calling external API")  ;

        try {
            ResponseEntity<String>response=
                    restTemplate.exchange(url, HttpMethod.GET,null,String.class);
            if(response.getStatusCode().is2xxSuccessful())
                 log.info("External API response is: {}",response.getBody());
        } catch (ResourceAccessException e) {
            throw new RetryableException("External service down retry again " +e);
        }catch (Exception e){
            throw  new NotRetryableException("This event going to .dlt Topic");
        }


    }
}
