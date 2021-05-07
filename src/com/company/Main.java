package com.company;


import com.company.collection_manage.CollectionManagement;
import com.company.work_client.Client;

public class  Main {

    public static void main(String[] args) {
        String fileName = System.getenv("INPUT_FILE_PATH");
        Client client = new Client(new CollectionManagement(), fileName);
        client.start();



      /**  LinkedHashSet<StudyGroup> Groups = new LinkedHashSet<StudyGroup>();
        Groups.add(new StudyGroup());
        Groups.add(new StudyGroup());
        for(int i = 0; i < Groups.size(); i++) {
            System.out.printf("id: %d date: %s", ((StudyGroup)Groups.toArray()[i]).getid(), ((StudyGroup)Groups.toArray()[i]).getCreationDate());
        }

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("название документа");
            Node root = document.getDocumentElement();

            System.out.println("list of books");
            System.out.println();

            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for (int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(i);
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("===============>>>>");
                }
            }
         } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        /*String s = "";
        while (!s.equals("exit")) {

            try {

                // Создаем поток для чтения из консоли
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("*Введите команду или 'exit' для выхода*");
                System.out.println("  * help-помощь");
                System.out.print("> ");
                s = br.readLine();

                //выходим если попросили
                if (s.equals("exit")) {
                    return;
                }

                //проверка, что ввели число
                try {
                    int i = Integer.parseInt(s);
                } catch (NumberFormatException nfe) {
                    System.err.println("Введена неверная команда. Введите help для того, чтобы посмотреть список команд. Попробуйте ещё раз.");
                }

                switch (s) {
                    case "1":
                        // Вызовем подменю модуля с рефлексией
                        s = ReflectionExplorer.ShowSubMenu();
                        break;

                    case "10":
                        // запустим задание по массивам
                        s= edu.arrays.ArrayMain.showSubMenu();
                        break;
                    default:
                        System.out.println("Введено число, но такого нет в меню. Попробуйте еше раз.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } */


    }
}
