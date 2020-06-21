package com.dmitridev.brainfuck.dto;

import org.springframework.boot.autoconfigure.domain.EntityScan;


public class CodeRequest{
    String code;
    String params;
    public CodeRequest(){}
    public String getCode(){return code;}
    public void setCode(String code){this.code = code;}
    public String getParams(){return params;}
    public void setParams(String params){this.params = params;}
}
