/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloworld;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author vidoa
 */
public class HelloWorldConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty
    private String template;
    @NotEmpty
    @JsonProperty
    private String defaultName = "Stranger";

    public String getTemplate() {
        return template;
    }

    public String getDefaultName() {
        return defaultName;
    }
}
