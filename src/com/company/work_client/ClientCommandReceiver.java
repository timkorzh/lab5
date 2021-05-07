package com.company.work_client;

import com.company.commands.AbstractCommand;
import com.company.parsers.XMLParser;

import java.util.HashMap;


public class ClientCommandReceiver {
    Client client;
    public ClientCommandReceiver(Client client) {
        this.client = client;
    }
    public void help() {
        HashMap<String, AbstractCommand> hashMap = client.getCommandInvoker().getHashMap();
        for(String commandName: hashMap.keySet()) {
            System.out.println(commandName);
            client.getCommandInvoker().getHashMap().get(commandName).describe();
        }
    }

    public void save() {
        XMLParser xmlParser = new XMLParser(client.getFilePath());
        xmlParser.saveCollection(client.getCollectionManagement());
    }

    public void exit() {

    }
}
