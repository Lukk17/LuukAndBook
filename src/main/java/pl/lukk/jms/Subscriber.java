package pl.lukk.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber
{
    private String          clientId;
    private Connection      connection;
    private Session         session;
    private MessageConsumer messageConsumer;

    public void closeConnection() throws JMSException
    {
        connection.close();
    }

    public void create(String clientId, String topicName) throws JMSException
    {
        this.clientId = clientId;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        messageConsumer = session.createConsumer(topic);
        connection.start();
    }

    public String getName(int timeout) throws JMSException
    {
        String receivedName = "no message was sent";
        Message message = messageConsumer.receive(timeout);
        if (message != null)
        {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            receivedName = text;
        }
        return receivedName;
    }

}
