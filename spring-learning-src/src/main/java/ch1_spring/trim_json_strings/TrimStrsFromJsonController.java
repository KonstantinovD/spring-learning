package ch1_spring.trim_json_strings;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@RestController
public class TrimStrsFromJsonController {

    // Даже если строки будут одинаковые в dto, json-десериализатор из них сделает разные объекты
    @GetMapping("/test_strs")
    public void index(@RequestBody ItemDto itemDto) {
        assertFalse(itemDto.getId() == itemDto.getName());
        assertFalse(itemDto.getId() == itemDto.getCode());
        assertFalse(itemDto.getName() == itemDto.getCode());

        itemDto.setId(itemDto.getId().intern());
        itemDto.setName(itemDto.getName().intern());
        itemDto.setCode(itemDto.getCode().intern());

        assertTrue(itemDto.getId() == itemDto.getName());
        assertTrue(itemDto.getId() == itemDto.getCode());
        assertTrue(itemDto.getName() == itemDto.getCode());

        log.info("All checks are OK");
    }

}
