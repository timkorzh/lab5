package com.company.commands;

import com.company.collection_manage.CollectionManagement;
import com.company.work_client.InputDevice;

public class RemoveLowerCommand extends AbstractCommand {

    CollectionManagement collectionManagement;
    public RemoveLowerCommand(CollectionManagement collectionManagement) {
        this.collectionManagement = collectionManagement;
    }


    @Override
    public void execute() {
        InputDevice device = new InputDevice();
        device.removeIdLow(collectionManagement);
    }

    @Override
    public void describe() {
        System.out.println("Удаляет из коллекции все элементы, меньшие, чем заданный");
    }
}
