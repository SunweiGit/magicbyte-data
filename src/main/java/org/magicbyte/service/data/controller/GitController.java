package org.magicbyte.service.data.controller;

import lombok.AllArgsConstructor;
import org.magicbyte.service.data.config.GitAutoRefreshConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RefreshScope
@RestController
@AllArgsConstructor
public class GitController {


    private final GitAutoRefreshConfig gitAutoRefreshConfig;


    @GetMapping(value = "autoShow")
    public Object autoShow() {
        return gitAutoRefreshConfig;
    }
}