package com.company.commands;

import com.company.collection_manage.CollectionManagement;
import com.company.work_client.InputDevice;

public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManagement collectionManagement;
    public RemoveByIdCommand(CollectionManagement collectionManagement) {
        this.collectionManagement = collectionManagement;
    }

    @Override
    public void execute() {
        InputDevice device = new InputDevice();
        device.remove(collectionManagement);
    }

    @Override
    public void describe() {
        System.out.println("Удаляет элемент из коллекции по его id");
    }
}
