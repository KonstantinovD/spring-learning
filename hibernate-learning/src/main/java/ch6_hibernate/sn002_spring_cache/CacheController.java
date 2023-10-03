package ch6_hibernate.sn002_spring_cache;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;


@RestController
@RequiredArgsConstructor
public class CacheController {

    private final TestNoteService service;

    @RequestMapping("/run-cache")
    public String toTrack() {
        return String.format("%s\n%s", LocalTime.now(), service.runCache());
    }

}
