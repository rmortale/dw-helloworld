/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloworld;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kjetland.dropwizard.activemq.ActiveMQConfig;
import com.kjetland.dropwizard.activemq.ActiveMQConfigHolder;
import io.dropwizard.Configuration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author vidoa
 */
public class HelloWorldConfiguration extends Configuration implements ActiveMQConfigHolder {

    @NotEmpty
    @JsonProperty
    private String template;
    @NotEmpty
    @JsonProperty
    private String defaultName = "Stranger";
    @JsonProperty
    private String artemisIp;
    @JsonProperty
    @NotNull
    @Valid
    private ActiveMQConfig activeMQ;
    @JsonProperty
    @NotNull
    private String queueName;

    public String getTemplate() {
        return template;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public String getArtemisIp() {
        return artemisIp;
    }

    @Override
    public ActiveMQConfig getActiveMQ() {
        return activeMQ;
    }

    public String getQueueName() {
        return queueName;
    }

}
