package com.app.moneyapi.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class ResourceCreateEvent extends ApplicationEvent {

    private HttpServletResponse response;
    private Long idResource;

    public ResourceCreateEvent(Object source, HttpServletResponse response, Long idResource) {
        super(source);
        this.response = response;
        this.idResource = idResource;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Long getIdResource() {
        return idResource;
    }
}
