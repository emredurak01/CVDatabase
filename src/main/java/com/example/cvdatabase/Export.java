package com.example.cvdatabase;

import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Model.Person;
import io.github.palexdev.materialfx.controls.MFXTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Export {
    private static Stage stage;

    public static void buildCV(MFXTableView<Person> table) {
        ObservableMap<Integer, Person> listValues = table.getSelectionModel().getSelection();
        ObservableList<Person> personList = FXCollections.observableArrayList(listValues.values());

        if (personList.listIterator().next() == null) {
            Controller.createAlert("You must select a CV first", "");
        } else {

            StringBuilder s = new StringBuilder();
            s.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>" + personList.listIterator().next().getName() + " " + personList.listIterator().next().getSurname() + "</title>\n" +
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
                    "            <h1 class=\"name\">" + personList.listIterator().next().getName() + " " + personList.listIterator().next().getSurname() + "</h1>\n" +
                    "            <br>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <div id=\"bio-data\">\n" +
                    "        <h3>Data</h3>\n" +
                    "        <table>\n" +
                    "            <tr>\n" +
                    "                <td>Name:</td>\n" +
                    "                <td><b>" + personList.listIterator().next().getName() + "</b></td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td>Surname:</td>\n" +
                    "                <td><b>" + personList.listIterator().next().getSurname() + "</b></td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td>Date of Birth:</td>\n" +
                    "\t\t\t\t<td><b>" + personList.listIterator().next().getBirthdate() + "</b></td>\n" +
                    "                \n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td>E-mail:</td>\n" +
                    "                <td><b>" + personList.listIterator().next().getEmail() + "</b></td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td>Phone:</td>\n" +
                    "                <td><b>" + personList.listIterator().next().getPhone() + "</b></td>\n" +
                    "            </tr>\n" +
                    "        \n" +
                    "        </table>\n" +
                    "    </div>\n" +
                    "    <div id=\"work\">\n" +
                    "        <h3>Education</h3>\n" +
                    "        <table>\n");

            for (int i = 0; i < personList.listIterator().next().getEducation().size(); i++) {
                s.append("            <tr class=\"work-1\">\n" +
                        "               <td>" + personList.listIterator().next().getEducation().get(i).getStartDate() + " - "
                        + personList.listIterator().next().getEducation().get(i).getEndDate() + "</td>\n" +
                        "<td>" + personList.listIterator().next().getEducation().get(i).getName() + "</td>\n" +
                        "            </tr>\n"
                );
            }


            s.append(

                    "        </table>\n" +
                            "    </div>\n" +
                            "    <div id=\"work\">\n" +
                            "        <h3>Experience</h3>\n" +
                            "        <table>\n");

            for (int i = 0; i < personList.listIterator().next().getExperiences().size(); i++) {
                s.append("            <tr class=\"work-1\">\n" +
                        "                <td>" + personList.listIterator().next().getExperiences().get(i).getStartDate() + " - " +
                        " " + personList.listIterator().next().getExperiences().get(i).getEndDate() + "</td>\n" +
                        "<td>" + personList.listIterator().next().getExperiences().get(i).getTitle() + "</td>\n" +
                        "            </tr>\n");
            }


            s.append(
                    " \n" +
                            "        </table>\n" +
                            "    </div>\n" +
                            "\t<div id=\"work\">\n" +
                            "        <h3>publications</h3>\n" +
                            "        <table>\n");

            for (int i = 0; i < personList.listIterator().next().getPublications().size(); i++) {
                s.append("            <tr class=\"work-1\">\n" +
                        "               <td>" + personList.listIterator().next().getPublications().get(i).getPublicationDate() + "\n" +
                        "               <td>" + personList.listIterator().next().getPublications().get(i).getTitle() + ", " + personList.listIterator().next().getPublications().get(i).getPublisher() + "\n" +
                        "            </tr>\n");
            }

            s.append("        </table>\n" +
                    "    </div>\n" +
                    "\t<div id=\"education\">\n" +
                    "        <h3>interests</h3>\n" +
                    "      <div id = \"list\"> \n");

            for (int i = 0; i < personList.listIterator().next().getInterests().size(); i++) {
                s.append("\t\t\t<p>" + personList.listIterator().next().getInterests().get(i) + "</p>\n");
            }

            s.append("\t\t</div>\n" +
                    "    </div>\n" +
                    "\t<div id=\"education\">\n" +
                    "        <h3>Skills</h3>\n" +
                    "        \n" +
                    "\t\t<div id = \"list\"> \n");

            for (int i = 0; i < personList.listIterator().next().getSkills().size(); i++) {
                s.append("\t\t\t<p>" + personList.listIterator().next().getSkills().get(i) + "</p>\n");
            }
            s.append("\t\t</div>\n" +
                    "        \n" +
                    "    </div>\n" +
                    "  \n" +
                    "</div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");
            //////////////////////////////////////
            StringBuilder c = new StringBuilder();
            c.append("* {margin: 0;padding: 0;}\n" +
                    "\n" +
                    "body{font-family: 'Montserrat', sans-serif;}\n" +
                    "\n" +
                    "#page {\n" +
                    "    min-height: 200px;\n" +
                    "    width: 60%;\n" +
                    "    min-width: 600px;\n" +
                    "    background: whitesmoke;\n" +
                    "    margin: 50px auto;\n" +
                    "    padding: 30px;\n" +
                    "    color: #27aae1;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    ".photo {\n" +
                    "    width: 15%;\n" +
                    "    min-width: 130px;\n" +
                    "    float: left;\n" +
                    "    margin-right: 20px;\n" +
                    "}\n" +
                    "\n" +
                    ".contact-info-box {\n" +
                    "    width: 70.9%;\n" +
                    "    display: inline-block;\n" +
                    "}\n" +
                    "\n" +
                    ".name {\n" +
                    "    margin-bottom: -5px;\n" +
                    "}\n" +
                    ".job-title {\n" +
                    "    display: inline-block;\n" +
                    "}\n" +
                    "\n" +
                    ".contact-details {\n" +
                    "    background: #27aae1;\n" +
                    "    color: white;\n" +
                    "    text-align: center;\n" +
                    "    margin: auto;\n" +
                    "    margin-top: 25px;\n" +
                    "    padding: 5px;\n" +
                    "    font-size: 15px;\n" +
                    "}\n" +
                    "\n" +
                    "#objective {\n" +
                    "    \n" +
                    "}\n" +
                    "\n" +
                    "#objective h3 {\n" +
                    "    border: 1px solid #d3d3d3;\n" +
                    "    text-transform: uppercase;\n" +
                    "    padding: 5px;\n" +
                    "    border-radius: 5px;\n" +
                    "    margin: 30px 0 10px;\n" +
                    "}\n" +
                    "\n" +
                    "#objective p {\n" +
                    "    padding: 0 5px;\n" +
                    "    line-height: 25px;\n" +
                    "    font-size: 14px;\n" +
                    "    color: #000;\n" +
                    "}\n" +
                    "\n" +
                    "#education h3 {\n" +
                    "    border: 1px solid #d3d3d3;\n" +
                    "    text-transform: uppercase;\n" +
                    "    padding: 5px;\n" +
                    "    border-radius: 5px;\n" +
                    "    margin: 30px 0 10px;\n" +
                    "}\n" +
                    "\n" +
                    "#education table td {\n" +
                    "    padding: 5px;\n" +
                    "    font-size: 14px;\n" +
                    "    color: #000;\n" +
                    "}\n" +
                    "\n" +
                    "#education table tr.school-1 td:first-child {\n" +
                    "    width: 120px;\n" +
                    "    color: gray;\n" +
                    "    padding-bottom: 25px;\n" +
                    "}\n" +
                    "\n" +
                    "#education table tr.school-2 td:first-child {\n" +
                    "    padding-bottom: 25px;\n" +
                    "}\n" +
                    "\n" +
                    "#work h3 {\n" +
                    "    border: 1px solid #d3d3d3;\n" +
                    "    text-transform: uppercase;\n" +
                    "    padding: 5px;\n" +
                    "    border-radius: 5px;\n" +
                    "    margin: 30px 0 10px;\n" +
                    "}\n" +
                    "\n" +
                    "#work table td {\n" +
                    "    padding: 5px;\n" +
                    "    font-size: 14px;\n" +
                    "    color: #000;\n" +
                    "}\n" +
                    "\n" +
                    "#work table tr.work-1 td:first-child {\n" +
                    "    width: 120px;\n" +
                    "    color: gray;\n" +
                    "    padding-bottom: 25px;\n" +
                    "}\n" +
                    "\n" +
                    "#work table tr.work-1 td {\n" +
                    "    padding-bottom: 25px;\n" +
                    "}\n" +
                    "\n" +
                    "#work table tr.work-2 td:first-child {\n" +
                    "    width: 120px;\n" +
                    "    color: gray;\n" +
                    "}\n" +
                    "\n" +
                    "#bio-data h3 {\n" +
                    "    border: 1px solid #d3d3d3;\n" +
                    "    text-transform: uppercase;\n" +
                    "    padding: 5px;\n" +
                    "    border-radius: 5px;\n" +
                    "    margin: 30px 0 10px;\n" +
                    "}\n" +
                    "\n" +
                    "#bio-data table td {\n" +
                    "    padding: 8px;\n" +
                    "    font-size: 15px;\n" +
                    "    color: #000;\n" +
                    "}\n" +
                    "\n" +
                    "#bio-data table tr td:first-child {\n" +
                    "    width: 200px;\n" +
                    "}\n" +
                    "\n" +
                    "#bio-data table tr td:nth-child(2) {\n" +
                    "    width: 300px;\n" +
                    "}\n" +
                    "\n" +
                    "#list p {\n" +
                    "\tcolor: #000;\n" +
                    "}");


            try {

                File cvfile = new File(personList.listIterator().next().getName() +
                        personList.listIterator().next().getSurname()+".html");

                BufferedWriter bw = new BufferedWriter(new FileWriter(cvfile));
                bw.write(String.valueOf(s));
                bw.close();

                File cssfile = new File("style.css");
                BufferedWriter bwcss = new BufferedWriter(new FileWriter(cssfile));
                bwcss.write(String.valueOf(c));
                bwcss.close();

                Controller.createAlert("CV successfully exported.", "");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


      /*  try {
            Process p = new ProcessBuilder("myCommand", "myArg").start();
        } catch (IOException e) {
            throw new RuntimeException(e);
            If you want to convert html to pdf throguh bash script uncomment this part
            NOTE : bash script is : pandoc cv.html -t latex -o cv.pdf
            Install pandoc and latex
        }*/


    }

    public void setStage(Stage stage) {

        Export.stage = stage;

    }


}
