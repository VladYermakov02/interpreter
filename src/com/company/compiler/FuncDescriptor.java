package com.company.compiler;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Objects;

public class FuncDescriptor extends Descriptor {
    private String returnType;
    private String funcName;
    // first token id in block
    private Integer firstTokenId;
    // last token id in block
    private Integer lastTokenId;
    private LinkedList<FuncArgDescriptor> arguments;

    public FuncDescriptor() {
        arguments = new LinkedList<>();
    }

    // returns whether all fields are not nulls or not
    public boolean containNulls() {
        if (Objects.equals(returnType, null)) {
            return true;
        }
        if (Objects.equals(funcName, null)) {
            return true;
        }
        return false;
    }

    public void setFuncArg(String type, String name) {
        arguments.add(new FuncArgDescriptor(type, name));
    }

    public boolean argumentFullyFilled(int index) {
        if (Objects.equals(arguments.get(index).getType(), null)) {
            return true;
        }
        if (Objects.equals(arguments.get(index).getName(), null)) {
            return true;
        }
        return false;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public Integer getFirstTokenId() {
        return firstTokenId;
    }

    public void setFirstTokenId(Integer firstTokenId) {
        this.firstTokenId = firstTokenId;
    }

    public Integer getLastTokenId() {
        return lastTokenId;
    }

    public void setLastTokenId(Integer lastTokenId) {
        this.lastTokenId = lastTokenId;
    }

    public LinkedList<FuncArgDescriptor> getArguments() {
        return arguments;
    }

    public void setArguments(LinkedList<FuncArgDescriptor> arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "FuncDescriptor {" +
                "returnType='" + returnType + '\'' +
                ", funcName='" + funcName + '\'' +
                ", firstTokenId=" + firstTokenId +
                ", lastTokenId=" + lastTokenId +
                ", arguments=" + arguments +
                '}';
    }
}
