package com.company.commands;

import com.company.work_client.CommandInvoker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ExecuteCommand extends AbstractCommand {

    CommandInvoker commandInvoker;

    public ExecuteCommand(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }
    @Override
    public void execute() {
        System.out.println("Введите путь  к файлу, который надо исполнить");
        Scanner scanner = new Scanner(System.in);
        String ExecuteFilePath = scanner.next();
        Path path = Paths.get(ExecuteFilePath);
        if (!((new File(path.toString())).exists())) {
            System.out.println("Не нашёл такой файл, пекарб((");
            return;
        }

        try {
            Scanner FileScanner = new Scanner(path);
            FileScanner.useDelimiter(System.getProperty("line.separator"));
            while(FileScanner.hasNext()) {
                commandInvoker.execute(FileScanner.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void describe() {
        System.out.println("Считывает и исполняет команды из файла");
    }
}
