package com.yebeisi.chip8.app.controller;

import com.yebeisi.chip8.app.base.BaseResponse;
import com.yebeisi.chip8.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/test")
    public BaseResponse test() {
        return BaseResponse.newSuccessResponse().result("hello").build();
    }

    @GetMapping("/new/{user}")
    public BaseResponse newClient(@PathVariable String user, HttpServletResponse response) {
        String sessionId = clientService.newClient(user);
        return BaseResponse.newSuccessResponse().result(sessionId).build();
    }

    @GetMapping("/command/{sessionId}/{commandCode}")
    public BaseResponse control(@PathVariable String sessionId, @PathVariable String commandCode) {
        clientService.doCommand(sessionId, commandCode);
        return BaseResponse.newSuccessResponse().result(true).build();
    }

    @GetMapping("/setSpeed/{sessionId}/{speed}")
    public BaseResponse setSpeed(@PathVariable String sessionId, @PathVariable Double speed) {
        clientService.setAcceleration(sessionId, speed);
        return BaseResponse.newSuccessResponse().result(true).build();
    }

    @GetMapping("/info/{sessionId}")
    public BaseResponse getClientInfo(@PathVariable String sessionId) {
        return BaseResponse.newSuccessResponse().result(clientService.getClientInfo(sessionId)).build();
    }

    @GetMapping("/graph/{sessionId}")
    public BaseResponse getGraph(@PathVariable String sessionId) {
        return BaseResponse.newSuccessResponse().result(clientService.getGraph(sessionId)).build();
    }

    @GetMapping("/key/press/{sessionId}/{keyCode}")
    public BaseResponse pressKey(@PathVariable String sessionId, @PathVariable String keyCode) {
        clientService.pressKey(sessionId, keyCode);
        return BaseResponse.newSuccessResponse().result(true).build();
    }

    @GetMapping("/key/release/{sessionId}/{keyCode}")
    public BaseResponse releaseKey(@PathVariable String sessionId, @PathVariable String keyCode) {
        clientService.releaseKey(sessionId, keyCode);
        return BaseResponse.newSuccessResponse().result(true).build();
    }

    @PostMapping("/uploadGame/{sessionId}")
    public BaseResponse uploadGame(@PathVariable String sessionId, @RequestPart MultipartFile file) throws IOException {
        clientService.readGameFile(sessionId, file.getBytes());
        return BaseResponse.newSuccessResponse().result(true).build();
    }

}
