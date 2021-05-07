package com.company.commands;

import com.company.collection_manage.CollectionManagement;
import com.company.work_client.InputDevice;

public class FilterBySemCommand extends AbstractCommand {

    CollectionManagement collectionManagement;
    public FilterBySemCommand(CollectionManagement collectionManagement) {
        this.collectionManagement = collectionManagement;
    }
    @Override
    public void execute() {
        InputDevice device = new InputDevice();
        device.filterBySem(collectionManagement);
    }

    @Override
    public void describe() {
        System.out.println("Выводит элементы, значение поля semesterEnum которых равно заданному");
    }
}
