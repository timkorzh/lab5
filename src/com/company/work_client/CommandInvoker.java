package com.company.work_client;

import com.company.commands.AbstractCommand;

import java.util.ArrayDeque;
import java.util.HashMap;

public class CommandInvoker {
    private HashMap<String, AbstractCommand> hashMap;

    public CommandInvoker() {
        this.hashMap = new HashMap<>();
        History = new ArrayDeque<>(7);
    }
    public void register(String commandName, AbstractCommand command) {
        hashMap.put(commandName, command);
    }
    public ArrayDeque<String> History;


    public HashMap<String, AbstractCommand> getHashMap() {
        return hashMap;
    }

    public void execute(String commandName) {
        try {
            hashMap.get(commandName).execute();
            History.push(commandName);
            if (History.size() > 7) {
                History.removeLast();
            }

        } catch (NullPointerException e) {
            System.out.println("Пекарь, незарегистрированная команда. Похудей пальчики или напиши help.");
        }
    }

}
