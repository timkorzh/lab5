package com.company.commands;

import com.company.collection_manage.CollectionManagement;
import com.company.collection_manage.StudyGroup;

public class AddCommand extends AbstractCommand {
    private CollectionManagement collectionManagement;

    public AddCommand(CollectionManagement collectionManagement) {
        this.collectionManagement = collectionManagement;
    }
    @Override
    public void execute() {
       collectionManagement.add(new StudyGroup());

    }

    @Override
    public void describe() {
        System.out.println("Добавляет новый элемент в коллекцию.");
    }

}
