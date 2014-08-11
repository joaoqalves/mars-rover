package net.joaoqalves.rest.services;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import net.joaoqalves.rest.configuration.NasaConfiguration;
import net.joaoqalves.rest.resources.NasaResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by joaoqalves on 11/08/14.
 */
public class NasaService extends Service<NasaConfiguration> {

    public static void main(String[] args) throws Exception {
        new NasaService().run(new String[] { "server" });
    }

    @Override
    public void initialize(Bootstrap<NasaConfiguration> bootstrap) {
        bootstrap.setName("nasa");
    }

    @Override
    public void run(NasaConfiguration configuration, Environment environment) throws Exception {
        environment.addResource(new NasaResource());
    }

}
