package com.company.work_client;

import com.company.collection_manage.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

// интерфейс для проверки вводимых значений
interface IValidator {
    boolean IsValid(String input);

    String ErrorMessage();
}


class NameValidator implements IValidator {

    @Override
    public boolean IsValid(String input) {
        return input.matches("\\w[\\w\\s]*");
    }

    @Override
    public String ErrorMessage() {
        return "Введите любые символы кроме пустой строки и пробела";
    }
}

//класс для проверки координат
class CoordinatesValidator implements IValidator {

    @Override
    public boolean IsValid(String input) {

        return input.matches("\\d+; ?[-]?[0-9]*\\.?,?[0-9]+");
    }

    @Override
    public String ErrorMessage() {
        return "Введите координаты в формате X; Y";
    }
}

class StudentsCountValidator implements IValidator {

    @Override
    public boolean IsValid(String input) {
         return input.matches("[1-9]\\d*{0,8}");
    }

    @Override
    public String ErrorMessage() {
        return "Введите число";
    }
}

class FormOfEducationValidator implements IValidator {


    @Override
    public boolean IsValid(String input) {
        try {
            Integer.parseInt(input);
            return(Integer.parseInt(input) < FormOfEducation.values().length);
        } catch (NumberFormatException e) {
            return  false;
        }
    }

    @Override
    public String ErrorMessage() {
        return "Введите одно из указанных чисел, уважаемый пекарь";
    }
}

class SemesterValidator implements IValidator {
    @Override
    public boolean IsValid(String input) {
        try {
            Integer.parseInt(input);
            return(Integer.parseInt(input) < Semester.values().length);
        } catch (NumberFormatException e) {
            return  false;
        }
    }

    @Override
    public String ErrorMessage() {
        return "Введите одно из указанных чисел, уважаемый пекарь";
    }
}

class AdminNameValidator implements IValidator {

    @Override
    public boolean IsValid(String input) {
        return input.matches("([A-Z,a-z,А-Я,а-я]+)\\s*([A-Z,a-z,А-Я,а-я]+)");
    }

    @Override
    public String ErrorMessage() {
        return "Введите имя в соответствии с форматом, уважаемый пекарь";
    }
}
class AdminPassportValidator implements IValidator {

    @Override
    public boolean IsValid(String input) {
        return input.matches("^\\d{4}\\s\\d{6}");
    }

    @Override
    public String ErrorMessage() {
        return "Введите паспортные данные в соответствии с форматом, уважаемый пекарь";
    }
}
class AdminLocationValidator implements IValidator {

    @Override
    public boolean IsValid(String input) {
        return input.matches("\\d+\\.?,?\\d*; ?\\d+;? ?[-]?\\d*\\.?,?\\d*");
    }

    @Override
    public String ErrorMessage() {
        return "Введите координаты в соответствии с форматом, уважаемый пекарь";
    }
}
public class InputDevice {

    ArrayList<quiz> questions = new ArrayList<>();

    public InputDevice() {

        questions.add(new quiz("Введите имя группы или exitcmd для выхода", new NameValidator()));
        questions.add(new quiz("Введите координаты группы в формате X; Y или exitcmd для выхода", new CoordinatesValidator()));
        questions.add(new quiz("Введите число студентов в группе или exitcmd для выхода", new StudentsCountValidator()));
        questions.add(new quiz("Введите форму обучения " + FormOfEducation.GetStringValues() + " или exitcmd для выхода", new FormOfEducationValidator()));
        questions.add(new quiz("Введите семестр " + Semester.GetStringValues() +  " или exitcmd для выхода", new SemesterValidator()));
        questions.add(new quiz("Введите имя админа группы(Фамилия Имя) или exitcmd для выхода", new AdminNameValidator()));
        questions.add(new quiz("Введите серию и номер паспорта(пример: 1234 123456) админа группы или exitcmd для выхода", new AdminPassportValidator()));
        questions.add(new quiz("Введите координаты в формате X; Y; Z  или exitcmd для выхода", new AdminLocationValidator()));

    }
    class quiz {
        public String question;
        public String answer;
        public IValidator validator;

        public quiz(String question) {
            this.question = question;

        }

        public quiz(String question, IValidator validator) {
            this.question = question;
            this.validator = validator;
        }
    }

    public void remove(CollectionManagement collectionManagement) {
        StudyGroup GroupElement = null;
        //questions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID группы для удаления");
        try {
            Integer GroupId = scanner.nextInt();
            if (collectionManagement.getCollection().stream().noneMatch(a -> a.getId() == GroupId)) {
                System.out.println("Ничего не нашёл по этому номеру:((");
                return;
            }
            for (StudyGroup studyGroup : collectionManagement.getCollection()) {

                if (GroupId == studyGroup.getid()) {
                    collectionManagement.getCollection().remove(studyGroup);
                }
            }

        }
        catch (InputMismatchException Ex) {
            System.out.println("Введите число");
            return;
        }

    }

    public void removeIdLow(CollectionManagement collectionManagement) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID группы для удаления");
        try {
            Integer GroupId = scanner.nextInt();
            collectionManagement.getCollection().removeIf(a -> a.getId() < GroupId);
        }
        catch (InputMismatchException Ex) {
            System.out.println("Введите число");
        }
    }
    public void removeIdHigh(CollectionManagement collectionManagement) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID группы для удаления");
        try {
            Integer GroupId = scanner.nextInt();
            collectionManagement.getCollection().removeIf(a -> a.getId() > GroupId);
        }
        catch (InputMismatchException Ex) {
            System.out.println("Введите число");
        }
    }

    public void countFormOfEducation(CollectionManagement collectionManagement) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите FormOfEducation группы: "+ FormOfEducation.GetStringValues());
        try {
            Integer FormEducation = scanner.nextInt();
            FormOfEducationValidator I = new FormOfEducationValidator();
            if(I.IsValid(FormEducation.toString())) {
                long b = collectionManagement.getCollection().stream().filter(a -> a.getFormOfEducation().ordinal() > FormEducation).count();
                System.out.println("Количество элементов, значение поля formOfEducation которых больше заданного: " + b);
            }
            else {
                System.out.println(I.ErrorMessage());
            }
        }
        catch (InputMismatchException Ex) {
            System.out.println("Введите число");
        }
    }

    public void filterBySem(CollectionManagement collectionManagement) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите semester, по которому хотите отсортировать: " + Semester.GetStringValues());
        try {
            Integer Sem = scanner.nextInt();
            SemesterValidator I = new SemesterValidator();
            if (I.IsValid(Sem.toString())) {
                Stream<StudyGroup> b = collectionManagement.getCollection().stream().filter(a -> a.getSemesterEnum().ordinal() == Sem);
                b.forEach(n -> System.out.println("Элементы, значение поля semesterEnum которых равно заданному: " + n.getId() + " "+ n.getName()));
            } else {
                System.out.println(I.ErrorMessage());
            }
        }
        catch (InputMismatchException Ex) {
            System.out.println("Введите число");
        }
    }

    public void edit(CollectionManagement collectionManagement) {

        StudyGroup GroupElement = null;
        //questions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID группы для редактирования");

        try {
            Integer GroupId = scanner.nextInt();
            for (StudyGroup studyGroup : collectionManagement.getCollection()) {
                if (GroupId == studyGroup.getid()) {
                    GroupElement = studyGroup;
                }
            }
            if (GroupElement == null) {
                System.out.println("Ничего не нашёл по этому номеру:((");
                return;
            }
        }
        catch (InputMismatchException Ex) {
            System.out.println("Введите число");
            return;
        }

        for (quiz quiz : questions) {

            boolean ResultOK = false;
            //до тех пор пока не ввел правильное
            while (ResultOK != true) {

                System.out.println("(" + (questions.indexOf(quiz) + 1) + "/" + questions.size()+ ")" + quiz.question);
                String name = scanner.nextLine();
                //Проверяем надо ли что-то менять
                if (name.equals("N")) {
                    ResultOK = true;
                }
                else {
                        if (name.equals("exitcmd")) {
                            System.out.println("Выходим из команды add, пекарь");
                            return;
                        }//если не надо анализируем ввод
                        else {
                            // если есть валидатор, проверяем значение через него
                            if (quiz.validator != null) {
                                if (quiz.validator.IsValid(name)) {
                                    quiz.answer = name;
                                    ResultOK = true;
                                } else {
                                    System.out.println(quiz.validator.ErrorMessage());
                                }
                                //если нет валидатора - берем значение
                            } else {
                                quiz.answer = name;
                                ResultOK = true;
                            }
                        }
                        //проверяем надо ли выйти из команды

                    }
                    }
            }

        for (quiz answer : questions) {
            System.out.println(answer.answer);
        }
        Person Admin = GroupElement.getGroupAdmin();
        if (questions.get(5).answer != null) {
            Admin.setName(questions.get(5).answer);
        }
        if (questions.get(6).answer != null) {
            Admin.setPassportID(questions.get(6).answer);
        }
        if (questions.get(7).answer != null) {
            Admin.setLocation(new Location(questions.get(7).answer));
        }
        if (questions.get(0).answer != null) {
            GroupElement.setName(questions.get(0).answer);
        }
        if (questions.get(1).answer != null) {
            GroupElement.getCoordinates().parseString(questions.get(1).answer);
        }
        if (questions.get(2).answer != null) {
            GroupElement.setStudentsCount(Integer.parseInt(questions.get(2).answer));
        }
        if (questions.get(3).answer != null) {
            GroupElement.setFormOfEducation(FormOfEducation.values()[Integer.parseInt(questions.get(3).answer)]);
        }
        if (questions.get(4).answer != null) {
            GroupElement.setSemesterEnum(Semester.values()[Integer.parseInt(questions.get(4).answer)]);
        }

    }
    public StudyGroup add() {
        //questions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (quiz quiz : questions) {
            boolean ResultOK = false;
            //до тех пор пока не ввел правильное
            while (ResultOK != true) {

                System.out.println("(" + (questions.indexOf(quiz) + 1) + "/" + questions.size()+ ")" + quiz.question);
                String name = scanner.nextLine();
                //проверяем надо ли выйти из команды
                if (name.equals("exitcmd")) {
                    System.out.println("Выходим из команды add, пекарь");
                    return null;
                }//если не надо анализируем ввод
                else {
                    // если есть валидатор, проверяем значение через него
                    if (quiz.validator != null) {
                        if (quiz.validator.IsValid(name)) {
                            quiz.answer = name;
                            ResultOK = true;
                        } else {
                            System.out.println(quiz.validator.ErrorMessage());
                        }
                        //если нет валидатора - берем значение
                    } else {
                        quiz.answer = name;
                        ResultOK = true;
                    }
                }

            }
        }

        for (quiz answer : questions) {
            System.out.println(answer.answer);
        }
        Person Admin = new Person();
        Admin.setName(questions.get(5).answer);
        Admin.setPassportID(questions.get(6).answer);
        Admin.setLocation(new Location(questions.get(7).answer));
        StudyGroup studyGroup = new StudyGroup(questions.get(0).answer,
                new Coordinates(questions.get(1).answer),
                Integer.parseInt(questions.get(2).answer),
                FormOfEducation.values()[Integer.parseInt(questions.get(3).answer)],
                Semester.values()[Integer.parseInt(questions.get(4).answer)],
                Admin);
        return studyGroup;

       /* Scanner exitcmd = null;
        if(scanner == exitcmd) {
            return null;
        }
        System.out.println("(1/11) Введите имя группы или exitcmd для выхода");
        String name = scanner.next();
        System.out.println("(2/11) Ведите координаты X группы или exitcmd для выхода");
        Coordinates coordinates = new Coordinates();
        coordinates.setX(scanner.nextInt());
        System.out.println("(3/11) Введите координаты Y группы или exitcmd для выхода");
        coordinates.setY(scanner.nextFloat());
        System.out.println("(4/11) Введите количество студентов в группе или exitcmd для выхода");
        Integer studentCount = scanner.nextInt();
        System.out.println("(5/11) Введите форму обучения или exitcmd для выхода");
        String formOfEducation = scanner.next();
        FormOfEducation formOfEducationEnum = FormOfEducation.valueOf(formOfEducation);
        System.out.println("(6/11) Введите семестр или exitcmd для выхода");
        String semester = scanner.next();
        Semester semesterEnum = Semester.valueOf(semester);
        System.out.println("(7/11) Введите имя админа группы или exitcmd для выхода");
        Person groupAdmin = new Person();
        groupAdmin.setName(scanner.next());
        System.out.println("(8/11) Введите номер паспорта админа группы или exitcmd для выхода");
        groupAdmin.setPassportID(scanner.next());
        System.out.println("(9/11) Введите координаты X админа группы или exitcmd для выхода");
        Location location = new Location();
        location.setX(scanner.nextDouble());
        System.out.println("(10/11) Введите координаты Y админа группы или exitcmd для выхода");
        location.setY(scanner.nextLong());
        System.out.println("(11/11) Введите координаты Z админа группы или exitcmd для выхода");
        location.setZ(scanner.nextFloat());
        groupAdmin.setLocation(location);
        StudyGroup studyGroup = new StudyGroup(name, coordinates, studentCount, formOfEducationEnum, semesterEnum, groupAdmin);
        return studyGroup; */
    }
}
 