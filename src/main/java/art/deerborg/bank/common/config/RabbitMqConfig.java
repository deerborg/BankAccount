package art.deerborg.bank.common.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("default")
    String exchange;

    @Value("firstStepQueue")
    String queueName;

    @Value("sample.routingKey")
    String routingKey;

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }
    @Bean
    Queue firstStepQueue() {
        return new Queue(queueName, false);
    }
    @Bean
    Queue secondStepQueue() {
        return new Queue("secondStepQueue", true);
    }
    @Bean
    Queue thirdStepQueue() {
        return new Queue("thirdStepQueue", true);
    }
    @Bean
    Binding binding(Queue firstStepQueue, DirectExchange exchange) {
        return BindingBuilder.bind(firstStepQueue).to(exchange).with(routingKey);
    }
    @Bean
    Binding secondStepBinding(Queue secondStepQueue, DirectExchange exchange) {
        return BindingBuilder.bind(secondStepQueue).to(exchange).with("secondRoute");
    }
    @Bean
    Binding thirdStepBinding(Queue thirdStepQueue, DirectExchange exchange) {
        return BindingBuilder.bind(thirdStepQueue).to(exchange).with("thirdRoute");
    }
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
