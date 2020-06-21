package com.dmitridev.brainfuck.controller;

import com.dmitridev.brainfuck.dto.CharacterResponse;
import com.dmitridev.brainfuck.dto.CodeRequest;
import com.dmitridev.brainfuck.service.BrainfuckInterpreter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.SchemaOutputResolver;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BrainfuckController {

    @PostMapping("/interpreter")
    public List<Map<String,CharacterResponse>> interpret(@RequestBody CodeRequest request) throws Exception {
        BrainfuckInterpreter brainfuckInterpreter = new BrainfuckInterpreter(request.getCode());
        List<Character> characters = brainfuckInterpreter.interpret(request.getParams());
        return brainfuckInterpreter.getResult(characters);
    }
}

