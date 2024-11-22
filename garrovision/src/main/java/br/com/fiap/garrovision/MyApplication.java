package br.com.fiap.garrovision;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

import br.com.fiap.garrovision.infra.dao.CORSFilter;

@ApplicationPath("/api")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("br.com.fiap.garrovision");
        register(CORSFilter.class);
    }
}

