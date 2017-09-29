/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloworld;

import com.mycompany.helloworld.health.TemplateHealthCheck;
import com.mycompany.helloworld.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 *
 * @author vidoa
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
            Environment environment) {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.jersey().register(new HelloWorldResource(template, defaultName));
        environment.healthChecks().register("template", new TemplateHealthCheck(template));
    }
}
