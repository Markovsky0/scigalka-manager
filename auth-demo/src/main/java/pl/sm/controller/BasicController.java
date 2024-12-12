package pl.sm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("/")
    public String unauthorizedAccess() {
        return "basic";
    }

    @GetMapping("/authorized")
    public String unauthorized() {
        return "authorized";
    }

    @GetMapping("/admion")
    @PreAuthorize("hasRole('admion')")
    public String admionAccess() {
        return "admion";
    }

    @GetMapping("/grzeczny")
    @PreAuthorize("hasAuthority('grzeczny_chlopczyk')")
    public String grzecznyChlopczykAccess() {
        return "grzeczny_chlopczyk";
    }

}
