package com.company.commands;

import com.company.work_client.ClientCommandReceiver;

public class HelpCommand extends AbstractCommand {
    private ClientCommandReceiver clientCommandReceiver;

    public HelpCommand(ClientCommandReceiver clientCommandReceiver) {
        this.clientCommandReceiver = clientCommandReceiver;
    }
    @Override
    public void describe() {
        System.out.println("Выводит справку о командах");
    }

    @Override
    public void execute() {
        clientCommandReceiver.help();
    }
}
