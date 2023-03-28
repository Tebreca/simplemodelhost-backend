package com.tebreca.simplemodelhost.rest;

import com.tebreca.simplemodelhost.SimplemodelhostApplication;
import com.tebreca.simplemodelhost.SystemController;
import com.tebreca.simplemodelhost.TableManager;
import com.tebreca.simplemodelhost.pojo.Table;
import com.tebreca.simplemodelhost.rest.response.ReadyResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Interface {

    private static final SystemController systemController = SimplemodelhostApplication.SYSTEM_CONTROLLER;


    @GetMapping("/debug")
    public String debug(int code) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Test</title>\n" +
                "</head>\n" +
                "<body style=\" overflow: hidden\">\n" +
                "<h1 style=\"font-size: 60vh; margin-top: 20vh; text-align: center\">" + code + "</h1>\n" +
                "</body>\n" +
                "</html>>";
    }


    @GetMapping(value = "/ready", params = {"id"})
    public ReadyResponse readyResponse(HttpServletRequest request, int id) {
        String remoteAddress = request.getRemoteAddr();
        id = TableManager.getId(id);
        boolean on = systemController.isOn();
        TableManager.add(new Table(id, on, false, remoteAddress + ":8080"));
        return new ReadyResponse(id, on);
    }


}
