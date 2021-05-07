package com.company.commands;

import com.company.work_client.Client;

public class HistoryCommand extends AbstractCommand {
    Client client;

    public HistoryCommand(Client client) {
        this.client = client;}
        @Override
        public void execute () {
        System.out.println("Последние команды:" + client.getCommandInvoker().History.toString());
        }

        @Override
        public void describe () {
        System.out.println("Выводит историю команд");
        }
}