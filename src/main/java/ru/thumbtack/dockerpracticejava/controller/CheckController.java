package ru.thumbtack.dockerpracticejava.controller;

import org.springframework.web.bind.annotation.*;
import ru.thumbtack.dockerpracticejava.repositories.UserRepository;
import ru.thumbtack.dockerpracticejava.repositories.SessionRepository;

import java.util.HashMap;

/**
 * Check controller.
 */
@RestController
@RequestMapping(value = "/api/check")
public class CheckController {

    private final UserRepository bookService;
    private final SessionRepository sessionRepository;

    /**
     * Parametrized constructor.
     */
    public CheckController(UserRepository bookService,
                           SessionRepository sessionRepository) {
        this.bookService = bookService;
        this.sessionRepository = sessionRepository;
    }

    /**
     * Check postgres connection
     */
    @GetMapping(value = "postgres")
    public HashMap<String, String> checkPostgres() {

        HashMap<String, String> map = new HashMap<>();
        map.put("status", "Postgres connected");
        try {
            bookService.findAll();
        } catch (Exception error) {
            map.put("status", "Postgres not connected");
        }

        return map;
    }

    /**
     * Check redis connection
     */
    @GetMapping(value = "redis")
    public HashMap<String, String> checkRedis() {

        HashMap<String, String> map = new HashMap<>();
        map.put("status", "Redis connected");
        try {
            sessionRepository.findAll();
        } catch (Exception error) {
            map.put("status", "Redis not connected");
        }

        return map;
    }

}
