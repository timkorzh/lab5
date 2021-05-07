package com.company.commands;

import com.company.collection_manage.CollectionManagement;
import com.company.work_client.InputDevice;

public class RemoveHigherCommand extends AbstractCommand {

    CollectionManagement collectionManagement;
    public RemoveHigherCommand(CollectionManagement collectionManagement) {
        this.collectionManagement = collectionManagement;
    }


    @Override
    public void execute() {
        InputDevice device = new InputDevice();
        device.removeIdHigh(collectionManagement);
    }

    @Override
    public void describe() {
        System.out.println("Удаляет из коллекции все элементы, превышающие заданный");
    }
}
