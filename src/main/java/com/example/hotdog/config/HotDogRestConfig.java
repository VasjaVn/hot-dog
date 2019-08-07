package com.example.hotdog.config;

import com.example.hotdog.rest.v1.HotDogResource;
import com.example.hotdog.rest.v1.exception.mapper.HotDogExceptionMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.base.JsonMappingExceptionMapper;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Configuration
@ApplicationPath("/")
public class HotDogRestConfig extends ResourceConfig {

    public HotDogRestConfig() {
        register(JacksonJaxbJsonProvider.class);
        register(HotDogResource.class);

        register(new HotDogRestConfig.ObjectMapperContextResolver());

        property(ServletProperties.FILTER_FORWARD_ON_404, true);

        register(JsonMappingExceptionMapper.class);
        register(HotDogExceptionMapper.class);
    }

    @Provider
    public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

        private final ObjectMapper mapper;

        ObjectMapperContextResolver() {
            mapper = new ObjectMapper();
            mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
            mapper.disableDefaultTyping();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        @Override
        public ObjectMapper getContext(Class<?> type) {
            return mapper;
        }
    }
}
