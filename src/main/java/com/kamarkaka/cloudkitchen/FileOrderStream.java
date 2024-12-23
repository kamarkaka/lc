package com.kamarkaka.cloudkitchen;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileOrderStream implements OrderStream {
    private List<JsonOrder> jsonOrderList = new ArrayList<>();
    private int counter = 0;

    public FileOrderStream(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonOrderList = Arrays.asList(objectMapper.readValue(new File(filename), JsonOrder[].class));
            System.out.println("File " + filename + " loaded");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public Order nextOrder() {
        if (counter == jsonOrderList.size()) return null;
        return Order.convertFromJson(jsonOrderList.get(counter++));
    }

    public boolean isDrained() {
        return counter == jsonOrderList.size();
    }
}
