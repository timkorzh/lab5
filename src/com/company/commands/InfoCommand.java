package com.company.commands;

import com.company.collection_manage.CollectionManagement;

public class InfoCommand extends AbstractCommand {
    CollectionManagement collectionManagement;

    public InfoCommand(CollectionManagement collectionManagement) {
        this.collectionManagement = collectionManagement;
    }

    @Override
    public void execute() {
        collectionManagement.info();
        System.out.println("Вывожу информацию...." + collectionManagement.info() + "и.т.д.");
    }

    @Override
    public void describe() {
        System.out.println("Выводит в стандартный поток вывода информацию о коллекции");
    }
}
