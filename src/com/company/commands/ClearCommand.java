package com.company.commands;

import com.company.collection_manage.CollectionManagement;

public class ClearCommand extends AbstractCommand{
    private CollectionManagement collectionManagement;

    public ClearCommand(CollectionManagement collectionManagement) {
        this.collectionManagement = collectionManagement;
    }
    @Override
    public void describe() {
        System.out.println("Очищает коллекцию");
    }

    @Override
    public void execute() {
        collectionManagement.clear();
    }
}
