package com.moshclouds.DockerMultiStage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moshclouds.DockerMultiStage.Dto.ApiResponse;

@RestController
public class ServerHealth {
    @GetMapping("/")
    public ApiResponse<Void> rootStatus() {
        return new ApiResponse<>("success", "Server is online", null);
    }
}
