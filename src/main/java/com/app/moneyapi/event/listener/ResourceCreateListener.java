package com.app.moneyapi.event.listener;

import com.app.moneyapi.event.ResourceCreateEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class ResourceCreateListener implements ApplicationListener<ResourceCreateEvent> {

    @Override
    public void onApplicationEvent(ResourceCreateEvent resourceCreateEvent) {
        HttpServletResponse response = resourceCreateEvent.getResponse();
        Long idResource = resourceCreateEvent.getIdResource();

        addHeaderLocation(response, idResource);
    }

    private void addHeaderLocation(HttpServletResponse response, Long idResource) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idResource}")
                .buildAndExpand(idResource).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
