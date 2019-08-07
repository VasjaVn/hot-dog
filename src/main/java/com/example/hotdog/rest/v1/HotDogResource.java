package com.example.hotdog.rest.v1;

import com.example.hotdog.rest.v1.dto.HotDogDto;
import com.example.hotdog.rest.v1.dto.ResponseStatus;
import com.example.hotdog.rest.v1.dto.response.ResponseAllHotDogsDto;
import com.example.hotdog.rest.v1.dto.response.ResponseHotDogDto;
import com.example.hotdog.service.HotDogService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HotDogResource {
    private final HotDogService hotDogService;

    @Inject
    public HotDogResource(HotDogService hotDogService) {
        this.hotDogService = hotDogService;
    }

    @GET
    @Path("/hotdog/{hotDogId}")
    public ResponseHotDogDto getHotDog(@PathParam("hotDogId")Long hotDogId) {
        return ResponseHotDogDto.builder()
                .status(ResponseStatus.SUCCESS)
                .hotDogDto(hotDogService.getHotDog(hotDogId))
                .build();
    }

    @GET
    @Path("/hotdogs")
    public ResponseAllHotDogsDto getHotDogs() {
        return ResponseAllHotDogsDto.builder()
                .status(ResponseStatus.SUCCESS)
                .listHotDogsDto(hotDogService.getAllHotDogs())
                .build();
    }

    @POST
    @Path("/hotdog")
    public ResponseHotDogDto createHotDog(@RequestBody HotDogDto hotDogDto) {
        return ResponseHotDogDto.builder()
                .status(ResponseStatus.SUCCESS)
                .hotDogDto(hotDogService.createHotDog(hotDogDto))
                .build();
    }

    @PUT
    @Path("/hotdog")
    public ResponseHotDogDto updateHotDog(@RequestBody HotDogDto hotDogDto) {
        hotDogService.updateHotDog(hotDogDto);
        return ResponseHotDogDto.builder()
                .status(ResponseStatus.SUCCESS)
                .hotDogDto(hotDogDto)
                .build();
    }

    @DELETE
    @Path("hotdog/{hotDogId}")
    public ResponseHotDogDto deleteHotDog(@PathParam("hotDogId")Long hotDogId) {
        hotDogService.deleteHotDog(hotDogId);
        return ResponseHotDogDto.builder()
                .status(ResponseStatus.SUCCESS)
                .build();
    }
}
