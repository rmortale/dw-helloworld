/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.kjetland.dropwizard.activemq.ActiveMQSender;
import com.mycompany.helloworld.Saying;
import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private final String template;
    private final String defaultName;
    private final String artemisIp;
    private final AtomicLong counter;
    private final ActiveMQSender sender;

    public HelloWorldResource(String template, String defaultName, String artemisIp, ActiveMQSender sender) {
        this.template = template;
        this.defaultName = defaultName;
        this.artemisIp = artemisIp;
        this.counter = new AtomicLong();
        this.sender = sender;
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        sender.send(name);
        return new Saying(counter.incrementAndGet(), String.format(template, name.or(artemisIp)));
    }
}
