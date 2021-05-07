package com.company.commands;


import com.company.work_client.ClientCommandReceiver;

public class SaveCommand extends AbstractCommand {
    private ClientCommandReceiver clientCommandReceiver;

    public SaveCommand(ClientCommandReceiver clientCommandReceiver) {
        this.clientCommandReceiver = clientCommandReceiver;
    }
    @Override
    public void execute() {
        clientCommandReceiver.save();
    }

        @Override
        public void describe () {
        System.out.println("сохранить коллекцию в файл");
        }


    }

