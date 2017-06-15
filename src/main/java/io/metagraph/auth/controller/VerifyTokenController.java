package io.metagraph.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhaoPeng
 */
@RestController
public class VerifyTokenController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/verifyToken")
    @ResponseBody
    public Map sayHello() {
        Map<String, String> map = new HashMap<>();
        map.put("tokenValid", "true");
        return map;
    }

}
