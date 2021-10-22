package nl.testautomation.demoapplication.backend.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BaseController {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<String> index() {
        return ResponseEntity
            .status(HttpStatus.I_AM_A_TEAPOT)
            .body("Go to /swagger-ui/ for more information");
    }

    /**
     * Simple secure endpoint.
     *
     * @param authorization authorization header of any kind (as long as it is not empty)
     * @return Return OK or FORBIDDEN if authorization header is provided.
     */
    @PostMapping("/secure")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<String> secure(@RequestHeader(value = "Authorization", defaultValue = "") String authorization) {

        if(StringUtils.isEmpty(authorization)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authorization header required");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Requested a secure endpoint");
    }
}
