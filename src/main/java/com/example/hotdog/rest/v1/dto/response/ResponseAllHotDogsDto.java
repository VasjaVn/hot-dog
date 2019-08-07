package com.example.hotdog.rest.v1.dto.response;

import com.example.hotdog.rest.v1.dto.HotDogDto;
import com.example.hotdog.rest.v1.dto.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseAllHotDogsDto implements Serializable {
    private ResponseStatus status;

    @JsonProperty("result")
    private List<HotDogDto> listHotDogsDto;
}
