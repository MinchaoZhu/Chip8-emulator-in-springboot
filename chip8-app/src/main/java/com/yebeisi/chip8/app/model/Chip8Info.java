package com.yebeisi.chip8.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Chip8Info {

    private String user;

    private String sessionId;

    private String status;

    private Double acceleration;

}
