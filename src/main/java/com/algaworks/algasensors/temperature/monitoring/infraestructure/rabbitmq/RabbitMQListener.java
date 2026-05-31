package com.algaworks.algasensors.temperature.monitoring.infraestructure.rabbitmq;

import com.algaworks.algasensors.temperature.monitoring.api.model.TemperatureLogData;
import com.algaworks.algasensors.temperature.monitoring.domain.service.TemperatureMonitoringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQListener {

    private final TemperatureMonitoringService temperatureMonitoringService;

    public RabbitMQListener(TemperatureMonitoringService temperatureMonitoringService) {
        this.temperatureMonitoringService = temperatureMonitoringService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handle(@Payload TemperatureLogData temperatureLog) {
        temperatureMonitoringService.processTemperatureReading(temperatureLog);
    }

}
