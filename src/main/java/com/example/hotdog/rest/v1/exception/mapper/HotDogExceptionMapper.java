package com.example.hotdog.rest.v1.exception.mapper;

import com.example.hotdog.exception.HotDogException;
import com.example.hotdog.rest.v1.dto.ResponseStatus;
import com.example.hotdog.rest.v1.dto.response.ResponseHotDogDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class HotDogExceptionMapper implements ExceptionMapper<HotDogException> {
    @Override
    public Response toResponse(HotDogException e) {
        return Response.ok(ResponseHotDogDto.builder()
                .status(ResponseStatus.FAILED)
                .msg(e.getMessage()).build())
                .build();
    }
}
