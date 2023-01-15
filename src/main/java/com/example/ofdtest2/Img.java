package com.example.ofdtest2;


import org.springframework.javapoet.JavaFile;

import java.lang.module.ModuleDescriptor;

public class Img {
    
    private String outPutPath;
    private String type;
    private String outPutName;
    private String inPut;

    public String getOutPutPath() {
        return outPutPath;
    }

    public void setOutPutPath(String outPutPath) {
        this.outPutPath = outPutPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutPutName() {
        return outPutName;
    }

    public void setOutPutName(String outPutName) {
        this.outPutName = outPutName;
    }

    public String getInPut() {
        return inPut;
    }

    public void setInPut(String inPut) {
        this.inPut = inPut;
    }
}
