package com.company.commands;

import com.company.collection_manage.CollectionManagement;
import com.company.work_client.InputDevice;

public class CountGreaterCommand extends AbstractCommand {

    CollectionManagement collectionManagement;
    public CountGreaterCommand(CollectionManagement collectionManagement) {
        this.collectionManagement = collectionManagement;
    }

    @Override
    public void execute() {
        InputDevice device = new InputDevice();
        device.countFormOfEducation(collectionManagement);

    }

    @Override
    public void describe() {
        System.out.println("Выводит количество элементов, значение поля formOfEducation которых больше заданного");
    }
}
