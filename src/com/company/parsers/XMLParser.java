package com.company.parsers;

import com.company.collection_manage.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XMLParser {
    private String filePath;
    public XMLParser(String filePath) {
        this.filePath = filePath;
    }
Coordinates coordinates = new Coordinates();
    public void getCollection() {

    }

     public CollectionManagement deParseCollection() {
        CollectionManagement collectionManagement = new CollectionManagement();

        //Найти метод депарсинга чтобы данные из файла кидать в коллекцию
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));
            NodeList SGElements = document.getDocumentElement().getElementsByTagName("StudyGroup");
            for (int i = 0; i < SGElements.getLength(); i++) {

                StudyGroup studyGroup = new StudyGroup();

                Element el = (Element) SGElements.item(i);

                Node id = el.getElementsByTagName("ID").item(0);
                studyGroup.setId(Integer.valueOf(id.getTextContent()));
                Node name = el.getElementsByTagName("Name").item(0);
                studyGroup.setName(name.getTextContent());
                Node coordinatesX = el.getElementsByTagName("Coordinates_X").item(0);
                studyGroup.getCoordinates().setX(Integer.valueOf(coordinatesX.getTextContent()));
                Node coordinatesY = el.getElementsByTagName("Coordinates_Y").item(0);
                studyGroup.getCoordinates().setY(Float.valueOf(coordinatesY.getTextContent()));
                Node studentsCount = el.getElementsByTagName("StudentsCount").item(0);
                studyGroup.setStudentsCount(Integer.valueOf(studentsCount.getTextContent()));
                Node formOfEducation = el.getElementsByTagName("FormOfEducation").item(0);
                studyGroup.setFormOfEducation(Enum.valueOf(FormOfEducation.class, formOfEducation.getTextContent()));
                Node semester = el.getElementsByTagName("Semester").item(0);
                studyGroup.setSemesterEnum(Semester.valueOf(semester.getTextContent()));
                Node creationDate = el.getElementsByTagName("CreationDate").item(0);
                studyGroup.getCreationDate(creationDate.getTextContent()); //Для этой херни создан метод getCreationDate в StudyGroup
                Node AdminElement = (el.getElementsByTagName("groupAdmin").item(0));

                Node groupAdminName = null;
                Node groupAdminPassport = null;
                Node groupAdminLocationX = null;
                Node groupAdminLocationY = null;
                Node groupAdminLocationZ = null;

                for( int I = 0; I < AdminElement.getChildNodes().getLength(); I++ ) {
                    if (AdminElement.getChildNodes().item(I).getNodeType() == Node.ELEMENT_NODE) {
                        Element elem = (Element) AdminElement.getChildNodes().item(I);

                        switch (elem.getTagName()) {
                            case ("GroupAdminName"):
                                groupAdminName = AdminElement.getChildNodes().item(I);
                                break;
                            case ("GroupAdminPassport"):
                                groupAdminPassport = AdminElement.getChildNodes().item(I);
                                break;
                            case ("GroupAdminLocationX"):
                                groupAdminLocationX = AdminElement.getChildNodes().item(I);
                                break;
                            case ("GroupAdminLocationY"):
                                groupAdminLocationY = AdminElement.getChildNodes().item(I);
                                break;
                            case ("GroupAdminLocationZ"):
                                groupAdminLocationZ = AdminElement.getChildNodes().item(I);
                        }
                    }
                }
                //el.getElementsByTagName("groupAdmin").item(0).getElementsByTagName("GroupAdminName").item(0).getTextContent()//Element AdminElement =(Element) AdminNode.getChildNodes().item(0);


                Person person = new Person(groupAdminName.getTextContent(), groupAdminPassport.getTextContent(),
                        new Location(groupAdminLocationX.getTextContent() + ";" + groupAdminLocationY.getTextContent() + ";" + groupAdminLocationZ.getTextContent()));
                studyGroup.setGroupAdmin(person);

                collectionManagement.getCollection().add(studyGroup);

            }



        } catch (ParserConfigurationException e) {
            System.out.println("Ошибка с конфигом парсера, уважаемый пекарь(((");
        } catch (SAXException e) {
            System.out.println("Ваш парсер не пашет, уважаемый пекарь(((");
        } catch (IOException e) {
            System.out.println("Ваш файл меня не устравивает, уважаемый пекарь(((");
        }

        return collectionManagement;
    }
    public void saveCollection(CollectionManagement collectionManagement) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to create a documentbuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // create a new document
            Document document = builder.newDocument();

            // Получаем корневой элемент
            Node root = document.createElement("root");
            document.appendChild(root);

            for(StudyGroup group: collectionManagement.getCollection()) {

                // Создаем новую группу по элементам
                // Сама studyGroup
                Element studyGroup = document.createElement("StudyGroup");
                // <id>
                Element id = document.createElement("ID");
                // Устанавливаем значение текста внутри тега
                id.setTextContent(String.valueOf(group.getid()));
                // <name>
                Element name = document.createElement("Name");
                name.setTextContent(group.getName());
                Element studentsCount = document.createElement("StudentsCount");
                studentsCount.setTextContent(group.getStudentsCount().toString());
                Element FormOfEducation = document.createElement("FormOfEducation");
                FormOfEducation.setTextContent(group.getFormOfEducation().toString());
                Element coordinatesX = document.createElement("Coordinates_X");
                coordinatesX.setTextContent(String.valueOf(group.getCoordinates().getX()));
                Element coordinatesY = document.createElement("Coordinates_Y");
                coordinatesY.setTextContent(String.valueOf(group.getCoordinates().getY()));
                Element date = document.createElement("CreationDate");
                date.setTextContent(group.getCreationDate().toString());
                Element semester = document.createElement("Semester");
                semester.setTextContent(group.getSemesterEnum().toString());
                Element groupAdminName = document.createElement("GroupAdminName");
                groupAdminName.setTextContent(group.getGroupAdmin().getName());
                Element groupAdminPassport = document.createElement("GroupAdminPassport");
                groupAdminPassport.setTextContent(group.getGroupAdmin().getPassportID());
                Element groupAdminLocationX = document.createElement("GroupAdminLocationX");
                groupAdminLocationX.setTextContent(group.getGroupAdmin().getLocation().getX().toString());
                Element groupAdminLocationY = document.createElement("GroupAdminLocationY");
                groupAdminLocationY.setTextContent(group.getGroupAdmin().getLocation().getY().toString());
                Element groupAdminLocationZ = document.createElement("GroupAdminLocationZ");
                groupAdminLocationZ.setTextContent(String.valueOf(group.getGroupAdmin().getLocation().getZ()));
                /** <Date>
                 Element date = document.createElement("Date");
                 date.setTextContent("2015");
                 // <ISBN>
                 Element isbn = document.createElement("ISBN");
                 isbn.setTextContent("0-06-999999-9");
                 // <Publisher>
                 Element publisher = document.createElement("Publisher");
                 publisher.setTextContent("Java-Course publisher");
                 // <Cost>
                 Element cost = document.createElement("Cost");
                 cost.setTextContent("499");
                 // Устанавливаем атрибут
                 cost.setAttribute("currency", "RUB");
                 */

                // Добавляем внутренние элементы книги в элемент <Book>
                studyGroup.appendChild(id);
                studyGroup.appendChild(name);
                studyGroup.appendChild(coordinatesX);
                studyGroup.appendChild(coordinatesY);
                studyGroup.appendChild(studentsCount);
                studyGroup.appendChild(FormOfEducation);
                studyGroup.appendChild(semester);
                studyGroup.appendChild(date);
                Element groupAdmin = document.createElement("groupAdmin");
                groupAdmin.appendChild(groupAdminName);
                groupAdmin.appendChild(groupAdminPassport);
                groupAdmin.appendChild(groupAdminLocationX);
                groupAdmin.appendChild(groupAdminLocationY);
                groupAdmin.appendChild(groupAdminLocationZ);
                studyGroup.appendChild(groupAdmin);
                root.appendChild(studyGroup);

            }

            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            //печатаем в консоль или файл
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File(filePath));

            //записываем данные
            transformer.transform(source, console);
            //transformer.transform(source, file);

            StringWriter strWriter = new StringWriter();
            transformer.transform(source, new StreamResult(strWriter));

            System.out.println("Создание XML файла закончено");
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath))) {
                writer.write(strWriter.toString());
            } catch (IOException ex) {
                System.out.println("Возникла непредвиденная ошибка. Коллекция не может быть записана в файл. Мне жаль, что всё так вышло.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
