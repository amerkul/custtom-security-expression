package com.example.demo.controller;

import com.example.demo.entity.Organization;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/fine")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('FOO_READ_PRIVILEGE')")
    public String getFine() {
        return "Everything is fine! - FOO_READ_PRIVILEGE";
    }

    @GetMapping("/read")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('foo', 'read')")
    public String read() {
        return "Everything is fine! - foo, 'read'";
    }

    @GetMapping("/write")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('foo', 'write')")
    public String write() {
        return "Everything is fine! - #foo, 'write'";
    }

    @PreAuthorize("isMember(#id)")
    @GetMapping("/organizations/{id}")
    public String findOrgById(@PathVariable long id) {
        return "That user is a member of this organization";
    }

}
