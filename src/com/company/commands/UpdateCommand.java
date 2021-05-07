package com.company.commands;

import com.company.collection_manage.CollectionManagement;

public class UpdateCommand extends AbstractCommand {
    private CollectionManagement collectionManagement;

    public UpdateCommand(com.company.collection_manage.CollectionManagement collectionManagement) {
        this.collectionManagement = collectionManagement;
    }
    @Override
    public void execute() {

        collectionManagement.edit();
    }

    @Override
    public void describe() {
        System.out.println("Обновляет значение элемента коллекции, id которого равен заданному");
    }
}
