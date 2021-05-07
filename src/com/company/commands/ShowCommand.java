package com.company.commands;

import com.company.collection_manage.CollectionManagement;

public class ShowCommand extends AbstractCommand {
    private CollectionManagement collectionManagement;

    public ShowCommand(CollectionManagement collectionManagement) {
        this.collectionManagement = collectionManagement;
    }

    @Override
    public void execute() {
        collectionManagement.show();
    }

    @Override
    public void describe() {
        System.out.println("Выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
    }
}
