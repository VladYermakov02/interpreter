package com.company.compiler;

import java.util.LinkedList;
import java.util.List;

public class DescriptorsOutput {

    public void printFunc(List<FuncDescriptor> descriptors) {
        for (Descriptor d : descriptors) {
            System.out.println(d.toString());
        }
    }
}
