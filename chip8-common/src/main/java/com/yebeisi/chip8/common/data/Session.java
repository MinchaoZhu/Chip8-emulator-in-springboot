package com.yebeisi.chip8.common.data;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Session {

    public final static String NEW = "new";

    public final static String READY = "ready";

    public final static String RUNNING = "running";

    public final static String STOP = "stop";

    public final static String PAUSE = "pause";

    public final static int BASE_FREQUENCY = 30;

    private String user;

    private String sessionId;

    private String status;

    private double acceleration;

    private String contextKey;

    private boolean isErrored;

    private Throwable error;

}
