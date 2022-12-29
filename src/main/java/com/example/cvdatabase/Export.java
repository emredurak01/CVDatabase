package com.example.cvdatabase;

import com.example.cvdatabase.Model.Person;

import java.io.*;

public class Export {


    public static void buildCV() {
        Person p = new Person();
        p.setName("Emre");
        p.setSurname("Durak");
        p.setEmail("emre@");
        p.setBirthdate("01.01.2001");
        p.setPhone("505123542");


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
                "            <h1 class=\"name\">" +p.getName() +" " +p.getSurname() +"</h1>\n" +
                "            <br>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"bio-data\">\n" +
                "        <h3>Data</h3>\n" +
                "        <table>\n" +
                "            <tr>\n" +
                "                <td>Name:</td>\n" +
                "                <td><b>" +p.getName() +"</b></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Surname:</td>\n" +
                "                <td><b>" +p.getSurname() +"</b></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Date of Birth:</td>\n" +
                "\t\t\t\t<td><b>" +p.getBirthdate() +"</b></td>\n" +
                "                \n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>E-mail:</td>\n" +
                "                <td><b>" +p.getEmail() +"</b></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Phone:</td>\n" +
                "                <td><b>" +p.getPhone() +"</b></td>\n" +
                "            </tr>\n" +
                "        \n" +
                "        </table>\n" +
                "    </div>\n" +
                "    <div id=\"work\">\n" +
                "        <h3>Education</h3>\n" +
                "        <table>\n");

                for(int i = 0; i < 2; i++) {
                    s.append("            <tr class=\"work-1\">\n" +
                            "               <td>" +"p.getEducation.get(i).getstartdate" + " - " + "p.getEducation.get(i).getenddate" +"</td>\n" +
                            "                <td> p.getEducation.get(i).getname</td>\n" +
                            "            </tr>\n"
                    );
                }


                s.append(

                        "        </table>\n" +
                        "    </div>\n" +
                        "    <div id=\"work\">\n" +
                        "        <h3>Experience</h3>\n" +
                        "        <table>\n");

                for(int i = 0; i < 2; i++) {
                    s.append("            <tr class=\"work-1\">\n" +
                            "                <td>+\"p.getExperience.get(i).getstartdate" + " - " + "p.getExperience.get(i).getenddate" +"</td>\n" +
                            "                <td>p.getExperience.getname</td>\n" +
                            "            </tr>\n");
                }


                s.append(
                        " \n" +
                        "        </table>\n" +
                        "    </div>\n" +
                        "\t<div id=\"work\">\n" +
                        "        <h3>publications</h3>\n" +
                        "        <table>\n");

                for(int i = 0; i < 3; i++) {
                    s.append("            <tr class=\"work-1\">\n" +
                            "                <td>p.getPublication.get(i).getyear</td>\n" +
                            "                <td>p.getPublication..getname</td>\n" +
                            "            </tr>\n");
                }

                s.append( "        </table>\n" +
                        "    </div>\n" +
                        "\t<div id=\"education\">\n" +
                        "        <h3>interests</h3>\n" +
                        "      <div id = \"list\"> \n");

                for(int i = 0; i < 3; i++) {
                    s.append("\t\t\t<p>"+"+p.getInterests().get(i)" +"</p>\n");
                }

                s.append("\t\t</div>\n" +
                        "    </div>\n" +
                        "\t<div id=\"education\">\n" +
                        "        <h3>Skills</h3>\n" +
                        "        \n" +
                        "\t\t<div id = \"list\"> \n");

                for(int i = 0; i < 5; i++) {
                    s.append("\t\t\t<p>" +"+p.getSkills().get(i)" +"</p>\n");
                }
                s.append("\t\t</div>\n" +
                        "        \n" +
                        "    </div>\n" +
                        "  \n" +
                        "</div>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");


        System.out.println(s);


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
