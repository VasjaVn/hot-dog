package com.example.hotdog.rest.v1.dto.response;

import com.example.hotdog.rest.v1.dto.HotDogDto;
import com.example.hotdog.rest.v1.dto.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseHotDogDto implements Serializable {
    private ResponseStatus status;

    @JsonProperty("result")
    private HotDogDto hotDogDto;

    private String msg;
}
