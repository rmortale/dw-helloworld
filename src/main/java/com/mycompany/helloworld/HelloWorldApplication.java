/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloworld;

import com.kjetland.dropwizard.activemq.ActiveMQBundle;
import com.kjetland.dropwizard.activemq.ActiveMQSender;
import com.mycompany.helloworld.health.TemplateHealthCheck;
import com.mycompany.helloworld.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vidoa
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private ActiveMQBundle activeMQBundle;

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        // Create the bundle and store reference to it
        this.activeMQBundle = new ActiveMQBundle();
        // Add the bundle
        bootstrap.addBundle(activeMQBundle);
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
            Environment environment) {
        final String queueName = configuration.getQueueName();

        // Set up the sender for our queue
        ActiveMQSender sender = activeMQBundle.createSender(queueName, false);

        // Set up a receiver that just logs the messages we receive on our queue
        activeMQBundle.registerReceiver(
                queueName,
                (animal) -> log.info("\n*****\nWe received an animal from activeMq: \n{}\n*****", animal),
                String.class,
                true);
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        final String artemisIp = configuration.getArtemisIp();
        environment.jersey().register(new HelloWorldResource(template, defaultName, artemisIp, sender));
        environment.healthChecks().register("template", new TemplateHealthCheck(template));
    }
}
