package com.example.cvdatabase;

import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Model.Education;
import com.example.cvdatabase.Model.Person;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTreeItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Export {



    public static void buildCV(MFXTableView<Person> table) {
        ObservableMap<Integer, Person> listValues = table.getSelectionModel().getSelection();
        ObservableList<Person> personList = FXCollections.observableArrayList(listValues.values());
        System.out.println(personList.listIterator().next().getName());
        Person p = new Person();

        StringBuilder s = new StringBuilder();


        s.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>CV </title>\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\">\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "   \n" +
                "<div id=\"page\">\n" +
                "    <div class=\"photo-and-name\">\n" +
                "        \n" +
                "        <div class=\"contact-info-box\">\n" +
                "            <h1 class=\"name\">" +personList.listIterator().next().getName() +" " +personList.listIterator().next().getSurname() +"</h1>\n" +
                "            <br>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"bio-data\">\n" +
                "        <h3>Data</h3>\n" +
                "        <table>\n" +
                "            <tr>\n" +
                "                <td>Name:</td>\n" +
                "                <td><b>" +personList.listIterator().next().getName() +"</b></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Surname:</td>\n" +
                "                <td><b>" +personList.listIterator().next().getSurname() +"</b></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Date of Birth:</td>\n" +
                "\t\t\t\t<td><b>" +personList.listIterator().next().getBirthdate()+"</b></td>\n" +
                "                \n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>E-mail:</td>\n" +
                "                <td><b>" +personList.listIterator().next().getEmail() +"</b></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Phone:</td>\n" +
                "                <td><b>" +personList.listIterator().next().getPhone() +"</b></td>\n" +
                "            </tr>\n" +
                "        \n" +
                "        </table>\n" +
                "    </div>\n" +
                "    <div id=\"work\">\n" +
                "        <h3>Education</h3>\n" +
                "        <table>\n");

                for(int i = 0; i < personList.listIterator().next().getEducation().size(); i++) {
                    s.append("            <tr class=\"work-1\">\n" +
                            "               <td>" +personList.listIterator().next().getEducation().get(i).getStartDate() + " - "
                            + personList.listIterator().next().getEducation().get(i).getEndDate() +"</td>\n" +
                                            "<td>" +personList.listIterator().next().getEducation().get(i).getName() + "</td>\n" +
                            "            </tr>\n"
                    );
                }


                s.append(

                        "        </table>\n" +
                        "    </div>\n" +
                        "    <div id=\"work\">\n" +
                        "        <h3>Experience</h3>\n" +
                        "        <table>\n");

                for(int i = 0; i < personList.listIterator().next().getExperiences().size(); i++) {
                    s.append("            <tr class=\"work-1\">\n" +
                            "                <td>" + personList.listIterator().next().getExperiences().get(i).getStartDate() + " - " +
                            " "+ personList.listIterator().next().getExperiences().get(i).getEndDate()  +"</td>\n" +
                            "<td>" +personList.listIterator().next().getExperiences().get(i).getTitle() + "</td>\n" +
                            "            </tr>\n");
                }


                s.append(
                        " \n" +
                        "        </table>\n" +
                        "    </div>\n" +
                        "\t<div id=\"work\">\n" +
                        "        <h3>publications</h3>\n" +
                        "        <table>\n");

                for(int i = 0; i < personList.listIterator().next().getPublications().size(); i++) {
                    s.append("            <tr class=\"work-1\">\n" +
                            "               <td>" + personList.listIterator().next().getPublications().get(i).getPublicationDate() + "\n" +
                            "               <td>" + personList.listIterator().next().getPublications().get(i).getTitle() + "\n" +
                            "            </tr>\n");
                }

                s.append( "        </table>\n" +
                        "    </div>\n" +
                        "\t<div id=\"education\">\n" +
                        "        <h3>interests</h3>\n" +
                        "      <div id = \"list\"> \n");

                for(int i = 0; i < personList.listIterator().next().getInterests().size(); i++) {
                    s.append("\t\t\t<p>"+personList.listIterator().next().getInterests().get(i) +"</p>\n");
                }

                s.append("\t\t</div>\n" +
                        "    </div>\n" +
                        "\t<div id=\"education\">\n" +
                        "        <h3>Skills</h3>\n" +
                        "        \n" +
                        "\t\t<div id = \"list\"> \n");

                for(int i = 0; i < personList.listIterator().next().getSkills().size(); i++) {
                    s.append("\t\t\t<p>" +personList.listIterator().next().getSkills().get(i) +"</p>\n");
                }
                s.append("\t\t</div>\n" +
                        "        \n" +
                        "    </div>\n" +
                        "  \n" +
                        "</div>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");


        File f = new File("cv.html");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(String.valueOf(s));
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
