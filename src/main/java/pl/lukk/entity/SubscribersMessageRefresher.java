package pl.lukk.entity;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.lukk.controller.HomeController;
import pl.lukk.service.MessageService;

@Component
public class SubscribersMessageRefresher
{
    @Autowired
    MessageService messageService;

//    @Scheduled(fixedRate = 500)
//    public void scheduleTaskWithFixedRate() throws JMSException
//    {
//        messageService.getTopicMessage(HomeController.logedSubscriverEmail);
//    }
}
