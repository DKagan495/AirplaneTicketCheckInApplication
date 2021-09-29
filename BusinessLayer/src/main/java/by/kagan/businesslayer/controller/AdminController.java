package by.kagan.businesslayer.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "Admin Panel")
@RequestMapping(value = "/api/admin")
public class AdminController {



}
