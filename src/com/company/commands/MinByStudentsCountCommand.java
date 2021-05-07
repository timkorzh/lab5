package com.company.commands;

import com.company.collection_manage.CollectionManagement;
import com.company.collection_manage.StudyGroup;

import java.util.Optional;

public class MinByStudentsCountCommand extends AbstractCommand {
    CollectionManagement collectionManagement;
    public MinByStudentsCountCommand(CollectionManagement collectionManagement) {
        this.collectionManagement = collectionManagement;
    }
    @Override
    public void execute() {
        //Способ 1 - лямбда выражение

        Optional<StudyGroup> MINGroup = collectionManagement.getCollection().stream().min((group1, group2)
                -> Integer.compare(group1.getStudentsCount(), group2.getStudentsCount()));

        if (MINGroup.isPresent()) {
            System.out.println("Группа с минимальным количеством студентов (способ 1): " + MINGroup.get().getId());
        }

        //вариант 2 через цикл
        int minIndex = 10000;
        StudyGroup minGroupCount = null;

        for (StudyGroup studyGroup : collectionManagement.getCollection()) {
            if (studyGroup.getStudentsCount() < minIndex) {
                minIndex = studyGroup.getStudentsCount();
                minGroupCount = studyGroup;
            }
        }
            System.out.println("Группа с минимальным количеством студентов (способ 2): " + minGroupCount.getId());

    }
    @Override
    public void describe() {
        System.out.println("Выводит любой объект из коллекции, значение поля studentsCount которого является минимальным");
    }
}
