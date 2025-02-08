package com.chintan.appversion.appversion.Controller;

import com.chintan.appversion.appversion.Dto.AppVersionDto;
import com.chintan.appversion.appversion.Service.AppVersionService;;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/app-version")
public class AppVersionController {

    private final AppVersionService appVersionService;

    public AppVersionController(AppVersionService l) {
        this.appVersionService = l;
    }

    @GetMapping("/find-latest")
    public ResponseEntity<AppVersionDto> findLatestVersion() {
        return new ResponseEntity<>(appVersionService.findLatestVersion(), HttpStatus.OK);
    }

    /**
     * Cases
     * 1. bug fixed 1.0.1 : purpose - 1
     * 2. deployed on qa 1.1.0 : purpose - 2
     * 3. deployed on uat 1.2.0 : purpose - 2
     * 4. deployed on prod 2.0.0 : purpose - 3
     */
    @PostMapping("/add-version")
    public ResponseEntity<AppVersionDto> addVersion(@RequestBody AppVersionDto appVersionDto, @RequestParam Integer purpose) {
        return new ResponseEntity<>(appVersionService.addVersion(appVersionDto, purpose), HttpStatus.OK);
    }

}
