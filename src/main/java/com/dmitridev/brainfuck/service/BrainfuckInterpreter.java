package com.dmitridev.brainfuck.service;

import com.dmitridev.brainfuck.dto.CharacterResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

public class BrainfuckInterpreter {
    public List<Character> code;

    public BrainfuckInterpreter(String code) {
        this.code = code.chars().mapToObj(element -> (char) element).collect(Collectors.toList());
    }

    public List<Character> interpret(String buffer) throws Exception {
        List<Character> listBuffer = buffer.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        int bufferIndex = 0;
        List<Character> register = new ArrayList<>();
        int registerIndex = 0;

        List<Character> res = new ArrayList<>();
        Stack<Integer> stackOpenBraces = new Stack<>(), stackCloseBraces = new Stack<>();

        for (int i = 0; i < code.size(); i++) {
            Character ch = code.get(i);
            if (ch == '[')
                stackOpenBraces.push(i);
            else if (ch == ']')
                stackCloseBraces.push(i);
        }

        for (int index = 0; index < code.size(); index++) {
            switch (code.get(index)) {
                case '>': {
                    if (register.size() - 1 < index) register.add((char) 0);
                    registerIndex++;
                    break;
                }
                case '<': {
                    registerIndex--;
                    break;
                }
                case '+': {
                    if (register.size() == 0) register.add((char) 0);
                    Character ch = register.get(registerIndex);
                    if (ch != null) register.set(registerIndex, ++ch);
                    break;
                }
                case '-': {
                    if (register.size() == 0) register.add((char) 0);
                    Character ch = register.get(registerIndex);
                    if (ch != null) register.set(registerIndex, --ch);
                    break;
                }
                case '.': {
                    res.add(register.get(registerIndex));
                    break;
                }
                case ',': {
                    Character ch = register.get(registerIndex);
                    ch = listBuffer.get(bufferIndex);
                    bufferIndex++;
                    break;
                }
                case '[': {
                    Character ch = register.get(registerIndex);
                    if (ch == 0) {
                        int stackIndex = stackOpenBraces.search(index);
                        index = stackCloseBraces.get(stackIndex - 1);
                    }

                    break;
                }
                case ']': {
                    Character ch = register.get(registerIndex);
                    if (ch != 0) {
                        int stackIndex = stackCloseBraces.search(index);
                        index = stackOpenBraces.get(stackIndex - 1);
                    }
                    break;
                }
                case '\n':
                case '\r':
                case ' ': {
                    break;
                }
                default: {
                    throw new Exception("Не могу прочитать данный символ! Ошибка!");
                }
            }
        }

        return res;
    }

    public List<Map<String, CharacterResponse>> getResult(List<Character> code) {
        List<Map<String, CharacterResponse>> res = new ArrayList<>();
        Map<String,CharacterResponse> tmp = new HashMap<>();
        int index=0;
        for(int i=0;i<code.size();i++){
                tmp.put("r"+ (i%10),new CharacterResponse("r" + (i % 10),code.get(i)));
                index++;
             if(index > 9){
                res.add(tmp);
                tmp.forEach((key,value)->System.out.println("key="+key +"value="+value.getCh()));
                tmp = new HashMap<>();
                index=0;
            }
        }
        if(res.size() !=0) {
            res.add(tmp);
        }
        res.forEach(temp -> temp.forEach((key,value)->System.out.println("key="+key +"value="+value.getCh())));

        return res;
    }

}
