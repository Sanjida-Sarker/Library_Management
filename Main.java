import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

class data {
    ArrayList<String> dataId = new ArrayList<String>();
    ArrayList<String> dataName = new ArrayList<String>();
    ArrayList<String> dataPass = new ArrayList<String>();
    ArrayList<String> bookName = new ArrayList<String>();
    ArrayList<String> bookPage = new ArrayList<String>();
    ArrayList<String> bookAuthor = new ArrayList<String>();
    ArrayList<String> availability = new ArrayList<String>();
    ArrayList<String> dataTemp = new ArrayList<String>();
    ArrayList<String> adminName = new ArrayList<String>();
    ArrayList<String> adminId = new ArrayList<String>();
    ArrayList<String> adminPass = new ArrayList<String>();

    data() {
        try {
            File dataFile = new File("security.txt");
            Scanner datasc = new Scanner(dataFile);
            File bookDataFile = new File("booksInfo.txt");
            Scanner bookDatasc = new Scanner(bookDataFile);
            File adminFile = new File("admins.txt");
            Scanner adminsc = new Scanner(adminFile);
            while (datasc.hasNext()) {
                dataId.add(datasc.next());
                dataName.add(datasc.next());
                dataPass.add(datasc.next());
            }
            while (adminsc.hasNext()) {
                adminId.add(adminsc.next());
                adminName.add(adminsc.next());
                adminPass.add(adminsc.next());
            }
            while (bookDatasc.hasNextLine()) {
                bookName.add(bookDatasc.nextLine());
                bookAuthor.add(bookDatasc.nextLine());
                bookPage.add(bookDatasc.nextLine());
                availability.add(bookDatasc.nextLine());
            }
            datasc.close();
            bookDatasc.close();
            adminsc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

class home extends data {
    String choice, temp, id, pass;
    int counter = 0;
    Scanner input = new Scanner(System.in);

    home() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***\n");
        frontSide();
    }

    void frontSide() {
        System.out.println("\tView Current Status( status )...");
        System.out.println("\tLogIn To The UU Library( signin )...");
        System.out.println("\tJoin UU Library( signup )...");
        System.out.println("\tExit From UU Library( exit )...");
        System.out.print("Type Command (status/signin/signup/exit): ");
        choice = input.nextLine();
        switch (choice) {
            case "status":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library - Status ***");
                status();
                frontSide();
                break;
            case "signin":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library - Sign In ***");
                signin();
                break;
            case "signup":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library - Sign Up ***");
                signup();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***\n");
                frontSide();
                break;
            case "exit":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                System.out.println("\t\t\t\t\t\t\" Today a reader tomorrow a leader \"");
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                System.out.println("\t\t\t\t\t\t  Invalid Command ! Try Again...");
                frontSide();
        }
    }

    void status() {
        int lines = 0;
        try {
            File file1 = new File("security.txt");
            Scanner sc1 = new Scanner(file1);
            File file2 = new File("booksInfo.txt");
            Scanner sc2 = new Scanner(file2);
            while (sc1.hasNext()) {
                lines++;
                temp = sc1.nextLine();
            }
            System.out.println("Total Users : " + dataId.size());
            lines = 0;
            while (sc2.hasNext()) {
                lines++;
                temp = sc2.nextLine();
            }
            System.out.println("Total Books : " + (lines / 3));
            sc1.close();
            sc2.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void signin() {
        System.out.println("Enter Your ID : ");
        id = input.nextLine();
        counter = 0;
        for (int i = 0; i < adminId.size(); i++) {
            if (adminId.get(i).compareTo(id) == 0) {
                System.out.println("Enter Your Password : ");
                pass = input.nextLine();
                if (adminPass.get(i).compareTo(pass) == 0) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Admin Panel ***");
                    admin();
                    break;
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                    System.out.println("\t\t\t\t\t\t  Invalid Password! Try Again...");
                    frontSide();
                    break;
                }
            }
            counter++;
        }
        if (counter >= adminId.size()) {
            counter = 0;
            for (int i = 0; i < dataId.size(); i++) {
                if (dataId.get(i).compareTo(id) == 0) {
                    System.out.println("Enter Your Password : ");
                    pass = input.nextLine();
                    if (dataPass.get(i).compareTo(pass) == 0) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                        user();
                        break;
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                        System.out.println("\t\t\t\t\t\t  Invalid Password! Try Again...");
                        frontSide();
                    }
                }
                counter++;
            }
            if (counter >= dataId.size()) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                System.out.println("\t\t\t\t\t\t Invalid User Name! Try Again...");
                frontSide();
            }
        }
    }

    void signup() {
        String id, userName, pass;
        System.out.println("Enter Your ID : ");
        id = input.nextLine();
        for (int i = 0; i < dataId.size(); i++) {
            if (dataId.get(i).compareTo(id) == 0) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                System.out.println("\t\t\t\t\t   This Id Is Alrady Present! Try Again...");
                frontSide();
            }
        }
        dataId.add(id);
        System.out.println("Enter Your Short name : ");
        userName = input.nextLine();
        dataName.add(userName);
        System.out.println("Enter Your Password : ");
        pass = input.nextLine();
        dataPass.add(pass);
        try {
            FileWriter fileWriter = new FileWriter("security.txt", true);
            fileWriter.write(id + " " + userName + " " + pass + "\n");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\t\t\t\t\t\t    *** Welcome To UU Library ***");
            System.out.println("\t\t\t\t\t\t      ---Sign Up Successful---");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void user() {
        System.out.println("Change Name ==> (change_name)");
        System.out.println("Change Password ==> (change)");
        System.out.println("My Books ==> (my)");
        System.out.println("Availbale Book List ==> (books)");
        System.out.println("Search Book ==> (search)");
        System.out.println("Show Library card ==> (card)");
        System.out.println("Exit ==>(exit)");
        choice = input.nextLine();
        switch (choice) {
            case "change_name":
                try {
                    String chid = "", chpass = "";
                    FileWriter fileWriter = new FileWriter("security.txt", true);
                    File file = new File("security.txt");
                    Scanner sc = new Scanner(file);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                    System.out.println("Enter Password : ");
                    temp = input.nextLine();
                    System.out.println(pass);
                    if (temp.compareTo(pass) == 0) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                        System.out.println("Enter New Name : ");
                        String tempname = input.nextLine();
                        System.out.println("Enter Confirm Name : ");
                        temp = input.nextLine();
                        if (tempname.compareTo(temp) == 0) {
                            for (int i = 0; i < dataId.size(); i++) {
                                if (pass.compareTo(dataPass.get(i)) == 0) {
                                    dataTemp.add(tempname);
                                } else {
                                    dataTemp.add(dataName.get(i));
                                }
                            }
                            dataName.clear();
                            for (int i = 0; i < dataTemp.size(); i++) {
                                dataName.add(dataTemp.get(i));
                            }
                            dataTemp.clear();
                            FileWriter fileWriter1 = new FileWriter("temp.txt");
                            File old = new File("security.txt");
                            Scanner scold = new Scanner(old);
                            while (scold.hasNext()) {
                                String tempid = scold.next();
                                temp = scold.next();
                                pass = scold.next();
                                if (id.compareTo(tempid) != 0) {
                                    fileWriter1.write(tempid + " " + temp + " " + pass + "\n");
                                } else {
                                    chid = tempid;
                                    chpass = pass;
                                }
                            }
                            fileWriter1.close();
                            scold.close();
                            try {
                                FileWriter fileWriter2 = new FileWriter("security.txt");
                                File nw = new File("temp.txt");
                                Scanner nwsc = new Scanner(nw);
                                while (nwsc.hasNext()) {
                                    String tempid = nwsc.next();
                                    temp = nwsc.next();
                                    pass = nwsc.next();
                                    fileWriter2.write(tempid + " " + temp + " " + pass + "\n");
                                }
                                fileWriter2.write(chid + " " + tempname + " " + chpass + "\n");
                                if (nw.delete()) {
                                    System.out.println("File is deleted");
                                }
                                fileWriter2.close();
                                nwsc.close();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                                System.out.println("\t\t\t\t\t\t\tName Change Successfully");
                                user();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                    fileWriter.close();
                    sc.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            case "change":
                try {
                    String chid = "", chname = "";
                    FileWriter fileWriter = new FileWriter("security.txt", true);
                    File file = new File("security.txt");
                    Scanner sc = new Scanner(file);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    System.out.println("Enter Password : ");
                    temp = input.nextLine();
                    System.out.println(pass);
                    if (temp.compareTo(pass) == 0) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                        System.out.println("Enter New Password : ");
                        String tempPass = input.nextLine();
                        System.out.println("Enter Confirm Password : ");
                        temp = input.nextLine();
                        if (tempPass.compareTo(temp) == 0) {
                            for (int i = 0; i < dataId.size(); i++) {
                                if (pass.compareTo(dataPass.get(i)) == 0) {
                                    dataTemp.add(tempPass);
                                } else {
                                    dataTemp.add(dataPass.get(i));
                                }
                            }
                            dataPass.clear();
                            for (int i = 0; i < dataTemp.size(); i++) {
                                dataPass.add(dataTemp.get(i));
                            }
                            dataTemp.clear();
                            FileWriter fileWriter1 = new FileWriter("temp.txt");
                            File old = new File("security.txt");
                            Scanner scold = new Scanner(old);
                            while (scold.hasNext()) {
                                String tempid = scold.next();
                                temp = scold.next();
                                pass = scold.next();
                                if (id.compareTo(tempid) != 0) {
                                    fileWriter1.write(tempid + " " + temp + " " + pass + "\n");
                                } else {
                                    chid = tempid;
                                    chname = temp;
                                }
                            }
                            fileWriter1.close();
                            scold.close();
                            try {
                                FileWriter fileWriter2 = new FileWriter("security.txt");
                                File nw = new File("temp.txt");
                                Scanner nwsc = new Scanner(nw);
                                while (nwsc.hasNext()) {
                                    String tempid = nwsc.next();
                                    temp = nwsc.next();
                                    pass = nwsc.next();
                                    fileWriter2.write(tempid + " " + temp + " " + pass + "\n");
                                }
                                fileWriter2.write(chid + " " + chname + " " + tempPass + "\n");
                                if (nw.delete()) {
                                    System.out.println("File is deleted");
                                }
                                fileWriter2.close();
                                nwsc.close();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                                frontSide();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                    fileWriter.close();
                    sc.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "my":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                System.out.println("My Books : ");
                counter = 0;
                for (int i = 0; i < bookAuthor.size(); i++) {
                    if (availability.get(i).compareTo("my") == 0) {
                        System.out.println(
                                "\t" + i + " " + bookName.get(i) + " (" + bookPage.get(i) + ")\n\t\""
                                        + bookAuthor.get(i) + "\"");
                        counter++;
                    }
                }
                if (counter <= 0) {
                    System.out.println("\tEmpty.");
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    user();
                } else {
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.println("Enter Book ID To Return It.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    if (choice.compareTo("#") == 0) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                        user();
                    } else {
                        for (int i = 0; i < bookName.size(); i++) {
                            temp = Integer.toString(i);
                            if (choice.compareTo(temp) == 0) {
                                availability.set(i, "available");
                            }
                        }
                    }
                    try {
                        FileWriter myFile = new FileWriter("booksInfo.txt");
                        for (int i = 0; i < bookName.size(); i++) {
                            myFile.write(bookName.get(i) + "\n");
                            myFile.write(bookAuthor.get(i) + "\n");
                            myFile.write(bookPage.get(i) + "\n");
                            myFile.write(availability.get(i) + "\n");
                        }
                        myFile.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    System.out.println("\t\t\t\t\t\t\t--- Return Successfully ---");
                    user();
                }

                break;
            case "books":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                System.out.println("Available Books : ");
                counter = 0;
                for (int i = 0; i < bookAuthor.size(); i++) {
                    if (availability.get(i).compareTo("available") == 0) {
                        System.out.println(
                                "\t" + i + " " + bookName.get(i) + " (" + bookPage.get(i) + ")\n\t\""
                                        + bookAuthor.get(i) + "\"");
                        counter++;
                    }
                }
                if (counter <= 0) {
                    System.out.println("\tEmpty.");
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    user();
                } else {
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.println("Enter Book ID To Take It.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    if (choice.compareTo("#") == 0) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                        user();
                    } else {
                        for (int i = 0; i < bookName.size(); i++) {
                            temp = Integer.toString(i);
                            if (choice.compareTo(temp) == 0) {
                                availability.set(i, "my");
                            }
                        }
                    }
                    try {
                        FileWriter myFile = new FileWriter("booksInfo.txt");
                        for (int i = 0; i < bookName.size(); i++) {
                            myFile.write(bookName.get(i) + "\n");
                            myFile.write(bookAuthor.get(i) + "\n");
                            myFile.write(bookPage.get(i) + "\n");
                            myFile.write(availability.get(i) + "\n");
                        }
                        myFile.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    System.out.println("\t\t\t\t\t\t\t    --- Book Added ---");
                    user();
                }
                break;
            case "search":
                String word;
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                System.out.println("Enter Book Name : ");
                choice = input.nextLine();
                int indexi = choice.indexOf(" ");
                if (indexi < 0) {
                    word = choice;
                } else {
                    word = choice.substring(0, indexi);
                }
                System.out.println("Books : ");
                counter = 0;
                for (int i = 0; i < bookName.size(); i++) {
                    int booki = bookName.get(i).indexOf(" ");
                    if (availability.get(i).compareTo("my") != 0) {
                        if (booki < 0) {
                            String com = bookName.get(i);
                            if (word.compareTo(com) == 0) {
                                counter++;
                                System.out.println("\t" + i + " " + bookName.get(i) + " (" + bookPage.get(i) + ")\n\t\""
                                        + bookAuthor.get(i) + "\" " + "<" + availability.get(i) + ">");
                            }
                        } else {
                            String com = bookName.get(i).substring(0, booki);
                            if (word.compareTo(com) == 0) {
                                counter++;
                                System.out.println("\t" + i + " " + bookName.get(i) + " (" + bookPage.get(i) + ")\n\t\""
                                        + bookAuthor.get(i) + "\" " + "<" + availability.get(i) + ">");
                            }
                        }
                    }
                }
                if (counter == 0) {
                    System.out.println("\tBook Not Found.");
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    System.out.println("\t\t\t\t\t\t\t    --- Book Added ---");
                    user();
                } else {
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.println("Enter Book ID To Take It.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    if (choice.compareTo("#") == 0) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                        user();
                    } else {
                        for (int i = 0; i < bookName.size(); i++) {
                            temp = Integer.toString(i);
                            if (choice.compareTo(temp) == 0) {
                                availability.set(i, "my");
                            }
                        }
                    }
                    try {
                        FileWriter myFile = new FileWriter("booksInfo.txt");
                        for (int i = 0; i < bookName.size(); i++) {
                            myFile.write(bookName.get(i) + "\n");
                            myFile.write(bookAuthor.get(i) + "\n");
                            myFile.write(bookPage.get(i) + "\n");
                            myFile.write(availability.get(i) + "\n");
                        }
                        myFile.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    System.out.println("\t\t\t\t\t\t\t    --- Book Added ---");
                    user();
                }
                break;
            case "card":
                System.out.println("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library ***\n\n");
                System.out.println("\t\t\t\t\t\t\t   LIBRARY CARD ");
                System.out.println("\t\t\t\t\t\t\t Uttara University \t\t ID ");
                for(int i=0;i<dataId.size();i++){
                    if(id.compareTo(dataId.get(i))==0){
                        System.out.println("\n\t\t\t\t Name       : " +dataName.get(i));
                        System.out.println("\t\t\t\t Stu ID     : " +dataId.get(i));
                        System.out.println("\t\t\t\t Semester   : Fall-22" );
                        System.out.println("\t\t\t\t Department : CSE");
                        System.out.println("\n\t\t\t\t Serial \t\t Book Name ");
                        int x=0;
                        counter=0;
                        for(int j=0;j<bookName.size();j++){
                            if(availability.get(j).compareTo("my")==0){
                                x++;
                                System.out.println("\t\t\t\t   " +x+ "\t\t" +bookName.get(j));
                            }
                            if(availability.get(j).compareTo("available")==0){
                                counter++;
                            }
                        }
                        System.out.println("\n\n\t\t\t\t Books available : " +counter);
                    }
                }
                break;
            case "exit":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                System.out.println("\t\t\t\t\t\t\" Today a reader tomorrow a leader \"");
                System.exit(1);
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                System.out.println("\t\t\t\t\t\t  Invalid Command ! Try Again...");
                user();
        }

    }

    void admin() {
        System.out.println("Change Name -->\"name\"");
        System.out.println("Change Password -->\"pass\"");
        System.out.println("Admin -->\"admin\"");
        System.out.println("Remove User -->\"user\"");
        System.out.println("Books -->\"book\"");
        System.out.println("Go Back -->\"#\"");
        System.out.println("For Exit -->\"exit\"");
        System.out.print("Enter Command : ");
        choice = input.nextLine();
        switch (choice) {
            case "name":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.print("Enter Password : ");
                choice = input.nextLine();
                if (choice.compareTo(pass) == 0) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                    System.out.print("Enter New Name : ");
                    String tempAdminName = input.nextLine();
                    System.out.print("Enter Confirm Name : ");
                    temp = input.nextLine();
                    if (tempAdminName.compareTo(temp) == 0) {
                        for (int i = 0; i < adminId.size(); i++) {
                            if (adminId.get(i).compareTo(id) == 0) {
                                adminName.set(i, tempAdminName);
                            }
                        }
                        try {
                            FileWriter adminFile = new FileWriter("admins.txt");
                            for (int i = 0; i < adminName.size(); i++) {
                                adminFile.write(adminId.get(i) + " ");
                                adminFile.write(adminName.get(i) + " ");
                                adminFile.write(adminPass.get(i) + "\n");
                            }
                            adminFile.close();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                            System.out.println("\t\t\t\t\t\t\tName Change Successfully");
                            admin();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                        System.out.println("\t\t\t\t\t\t\t    Name Dosen't Match.");
                        admin();
                    }
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                    System.out.println("\t\t\t\t\t\t\t    Incorrect Password.");
                    admin();
                }
                break;
            case "pass":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.print("Enter Password : ");
                choice = input.nextLine();
                if (choice.compareTo(pass) == 0) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                    System.out.print("Enter New Password : ");
                    String tempAdminName = input.nextLine();
                    System.out.print("Enter Confirm Password : ");
                    temp = input.nextLine();
                    if (tempAdminName.compareTo(temp) == 0) {
                        for (int i = 0; i < adminId.size(); i++) {
                            if (adminId.get(i).compareTo(id) == 0) {
                                adminPass.set(i, tempAdminName);
                            }
                        }
                        try {
                            FileWriter adminFile = new FileWriter("admins.txt");
                            for (int i = 0; i < adminPass.size(); i++) {
                                adminFile.write(adminId.get(i) + " ");
                                adminFile.write(adminName.get(i) + " ");
                                adminFile.write(adminPass.get(i) + "\n");
                            }
                            adminFile.close();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                            System.out.println("\t\t\t\t\t\t\tPassword Change Successfully");
                            admin();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                        System.out.println("\t\t\t\t\t\t\t    Password Dosen't Match.");
                        admin();
                    }
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                    System.out.println("\t\t\t\t\t\t\t    Incorrect Password.");
                    admin();
                }
                break;
            case "admin":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                admin_admin();
                break;
            case "user":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("Remove User : ");
                System.out.print("\tEnter Password : ");
                choice = input.nextLine();
                if (choice.compareTo(pass) == 0) {
                    for (int i = 0; i < dataId.size(); i++) {
                        System.out.println("\tId : " + dataId.get(i) + " Name : " + dataName.get(i));
                    }
                    System.out.print("\n\tEnter User Id : ");
                    choice = input.nextLine();
                    System.out.print("\tConfirm User Id : ");
                    temp = input.nextLine();
                    if (choice.compareTo(temp) == 0) {
                        for (int i = 0; i < dataId.size(); i++) {
                            if (dataId.get(i).compareTo(temp) == 0) {
                                dataId.remove(i);
                                dataName.remove(i);
                                dataPass.remove(i);
                            }
                        }
                        try {
                            FileWriter userFile = new FileWriter("security.txt");
                            for (int i = 0; i < dataId.size(); i++) {
                                userFile.write(dataId.get(i) + " ");
                                userFile.write(dataName.get(i) + " ");
                                userFile.write(dataPass.get(i) + "\n");
                            }
                            userFile.close();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                            System.out.println("\t\t\t\t\t\t\tUser Removed Successfully");
                            admin();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
                break;
            case "book":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                admin_book();
                break;
            case "#":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                frontSide();
                break;
            case "exit":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("\t\t\t\t\t\t\t\" Today a reader tomorrow a leader \"");
                System.exit(1);
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("\t\t\t\t\t\t  Invalid Command ! Try Again...");
                admin();
        }
    }

    void admin_admin() {
        String newAdminId, newAdminName, newAdminPass;
        System.out.println("Admin : ");
        System.out.println("\tAdd Admin -->\"add\"");
        System.out.println("\tRemove Admin -->\"remove\"");
        System.out.println("\tGo Back -->\"#\"");
        System.out.println("\tTo Close -->\"exit\"");
        System.out.print("\tEnter Command : ");
        choice = input.nextLine();
        switch (choice) {
            case "add":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("Admin : ");
                System.out.print("\tEnter Password : ");
                choice = input.nextLine();
                if (choice.compareTo(pass) == 0) {
                    System.out.print("\tEnter New Admin ID : ");
                    newAdminId = input.nextLine();
                    adminId.add(newAdminId);
                    System.out.print("\tEnter New Admin Name : ");
                    newAdminName = input.nextLine();
                    adminName.add(newAdminName);
                    System.out.print("\tEnter New Admin Password : ");
                    newAdminPass = input.nextLine();
                    adminPass.add(newAdminPass);
                    try {
                        FileWriter adminFile = new FileWriter("admins.txt");
                        for (int i = 0; i < adminPass.size(); i++) {
                            adminFile.write(adminId.get(i) + " ");
                            adminFile.write(adminName.get(i) + " ");
                            adminFile.write(adminPass.get(i) + "\n");
                        }
                        adminFile.close();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                        System.out.println("\t\t\t\t\t\t\tAdmin Added Successfully");
                        admin_admin();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                    System.out.println("\t\t\t\t\t\t\t    Incorrect Password.");
                    admin_admin();
                }
                break;
            case "remove":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("Admin : ");
                System.out.print("\tEnter Password : ");
                choice = input.nextLine();
                if (choice.compareTo(pass) == 0) {
                    System.out.print("\tEnter Admin ID : ");
                    newAdminId = input.nextLine();
                    System.out.print("\tEnter Confirm ID : ");
                    temp = input.nextLine();
                    if (newAdminId.compareTo(temp) == 0) {
                        for (int i = 0; i < adminId.size(); i++) {
                            if (adminId.get(i).compareTo(temp) == 0) {
                                adminId.remove(i);
                                adminName.remove(i);
                                adminPass.remove(i);
                            }
                        }
                    }
                    try {
                        FileWriter adminFile = new FileWriter("admins.txt");
                        for (int i = 0; i < adminPass.size(); i++) {
                            adminFile.write(adminId.get(i) + " ");
                            adminFile.write(adminName.get(i) + " ");
                            adminFile.write(adminPass.get(i) + "\n");
                        }
                        adminFile.close();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                        System.out.println("\t\t\t\t\t\t\tAdmin Removed Successfully");
                        admin();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                    System.out.println("\t\t\t\t\t\t\t    Incorrect Password.");
                    admin_admin();
                }
                break;
            case "#":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                admin();
                break;
            case "exit":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("\t\t\t\t\t\t\" Today a reader tomorrow a leader \"");
                System.exit(1);
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("\t\t\t\t\t\t  Invalid Command ! Try Again...");
                admin_admin();
        }
    }

    void admin_book() {
        String bName, bauthor, bPage;
        System.out.println("Book : ");
        System.out.println("\tView Books -->\"view\"");
        System.out.println("\tAdd Book -->\"add\"");
        System.out.println("\tRemove Book -->\"remove\"");
        System.out.println("\tChange Book Avaiability -->\"validity\"");
        System.out.println("\tGo Back -->\"#\"");
        System.out.println("\tExit -->\"exit\"");
        System.out.print("\tEnter Command : ");
        choice = input.nextLine();
        switch (choice) {
            case "view":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("Book : ");
                System.out.println("\t\t\t\t\t\t\t--- All Books ---");
                for(int i=0;i<bookName.size();i++){
                    System.out.print("\tBook Name : \""+bookName.get(i)+"\"\tNumber Of Page : \""+bookPage.get(i)+"\"\n\tAuthor : \""+bookAuthor.get(i)+"\"");
                    if(availability.get(i).compareTo("available")==0){
                        System.out.println("\tAvailability : \"available\"");
                    }else{
                        System.out.println("\tAvailability : \"unavailable\"");
                    }
                }
                System.out.print("Press # To Go Back : ");
                choice = input.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                admin_book();
                break;
            case "add":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("Book : ");
                System.out.print("\tEnter Book Name : ");
                bName = input.nextLine();
                bookName.add(bName);
                System.out.print("\tEnter Book Author Name : ");
                bauthor = input.nextLine();
                bookAuthor.add(bauthor);
                System.out.print("\tEnter Total Book Page : ");
                bPage = input.nextLine();
                bookPage.add(bPage);
                availability.add("available");
                try {
                    FileWriter adminBookFile = new FileWriter("booksInfo.txt");
                    for (int i = 0; i < bookName.size(); i++) {
                        adminBookFile.write(bookName.get(i) + "\n");
                        adminBookFile.write(bookAuthor.get(i) + "\n");
                        adminBookFile.write(bookPage.get(i) + "\n");
                        adminBookFile.write(availability.get(i) + "\n");
                    }
                    adminBookFile.close();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                    System.out.println("\t\t\t\t\t\t\tBook Added Successfully");
                    admin_book();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "remove":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("Book : ");
                System.out.print("\tEnter Book Name : ");
                choice = input.nextLine();
                choice = choice.toLowerCase();
                for (int i = 0; i < bookName.size(); i++) {
                    temp = bookName.get(i).toLowerCase();
                    if (choice.compareTo(temp) == 0) {
                        bookName.remove(i);
                        bookAuthor.remove(i);
                        bookPage.remove(i);
                        availability.remove(i);
                        System.out.println("remove");
                    }
                }
                try {
                    FileWriter adminBookFile = new FileWriter("booksInfo.txt");
                    for (int i = 0; i < bookName.size(); i++) {
                        adminBookFile.write(bookName.get(i) + "\n");
                        adminBookFile.write(bookAuthor.get(i) + "\n");
                        adminBookFile.write(bookPage.get(i) + "\n");
                        adminBookFile.write(availability.get(i) + "\n");
                    }
                    adminBookFile.close();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                    System.out.println("\t\t\t\t\t\t\tBook Removed Successfully");
                    admin_book();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "validity":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("Book : ");
                System.out.print("\tEnter Book Name : ");
                choice = input.nextLine();
                choice = choice.toLowerCase();
                for (int i = 0; i < bookName.size(); i++) {
                    temp = bookName.get(i).toLowerCase();
                    if (choice.compareTo(temp) == 0) {
                        System.out.print("\tEnter validity Mode(unavailable/available) : ");
                        choice = input.nextLine();
                        availability.set(i, choice);
                    }
                }
                try {
                    FileWriter adminBookFile = new FileWriter("booksInfo.txt");
                    for (int i = 0; i < bookName.size(); i++) {
                        adminBookFile.write(bookName.get(i) + "\n");
                        adminBookFile.write(bookAuthor.get(i) + "\n");
                        adminBookFile.write(bookPage.get(i) + "\n");
                        adminBookFile.write(availability.get(i) + "\n");
                    }
                    adminBookFile.close();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                    System.out.println("\t\t\t\t\t\t\tAvailability Changed Successfully");
                    admin_book();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "#":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                admin();
                break;
            case "exit":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("\t\t\t\t\t\t\" Today a reader tomorrow a leader \"");
                System.exit(1);
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library Admin Panel ***");
                System.out.println("\t\t\t\t\t\t  Invalid Command ! Try Again...");
                admin_book();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new data();
        new home();
    }
}
