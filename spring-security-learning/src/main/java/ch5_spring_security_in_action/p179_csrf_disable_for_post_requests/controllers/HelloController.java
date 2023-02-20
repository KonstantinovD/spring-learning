package ch5_spring_security_in_action.p179_csrf_disable_for_post_requests.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    // allowed HTTP methods [HEAD, DELETE, POST, GET, OPTIONS, PATCH, PUT]
    // TRACE нельзя, да его и в Postman нету

    @GetMapping("/get")
    public String get() {
        return "get request";
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/options")
    public String options() {
        return "options request";
    }

    // Аналогичен методу GET, за исключением того, что в ответе сервера отсутствует тело
    // Запрос HEAD обычно применяется для извлечения метаданных, проверки наличия ресурса
    // (валидация URL) и чтобы узнать, не изменился ли он с момента последнего обращения.
    @RequestMapping(method = RequestMethod.HEAD, value = "/head")
    public String head() {
        return "head request";
    }

    // ----------- Эти методы не работают с дефолтным csrf - возвращают 403 ----------

    @PostMapping("/post")
    public String post() {
        return "post request";
    }

    @PutMapping("/put")
    public String put() {
        return "put request";
    }

    @PatchMapping("/patch")
    public String patch() {
        return "patch request";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "delete request";
    }



}
