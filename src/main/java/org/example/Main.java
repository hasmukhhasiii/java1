package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Student {

    private String name;
    private Integer addNo;
    private Integer marphy;
    private Integer marche;
    private Integer marmat;

    public Student(String name,Integer addNo,Integer marphy,Integer marche,Integer marmat){
        this.name=name;
        this.addNo=addNo;
        this.marphy=marphy;
        this.marche=marche;
        this.marmat=marmat;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", addNo=" + addNo +
                ", marphy=" + marphy +
                ", marche=" + marche +
                ", marmat=" + marmat +
                '}';
    }

    public String getName() {
        return name;
    }

    public Integer getAddNo() {
        return addNo;
    }

    public Integer getMarphy() {
        return marphy;
    }

    public Integer getMarche() {
        return marche;
    }

    public Integer getMarmat() {
        return marmat;
    }
}

class StudentOutput {

    private String name;
    private Integer addNo;
    private String percent;
    private Subject physics;
    private Subject chemistry;
    private Subject maths;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddNo(Integer addNo) {
        this.addNo = addNo;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public void setPhysics(Subject physics) {
        this.physics = physics;
    }

    public void setChemistry(Subject chemistry) {
        this.chemistry = chemistry;
    }

    public void setMaths(Subject maths) {
        this.maths = maths;
    }


    public String toString() {
        return "Name: " + name + "\n" +
                "Admission No: " + addNo + "\n" +
                "Percentage: " + percent + "\n" +
                "Physics:\n" + physics.toString() +
                "Chemistry:\n" + chemistry.toString() +
                "Maths:\n" + maths.toString();
    }
}

class Subject{
    private Integer mark;
    private String grade;
    private String gradePoint;

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGradePoint(String gradePoint) {
        this.gradePoint = gradePoint;
    }

        @Override
        public String toString() {
            return "\tMark: " + mark + "\n" +
                    "\tGrade: " + grade + "\n" +
                    "\tGrade Point: " + gradePoint + "\n";
        }
}


class converter
{

    public static void main(String[] args) {

        String filePath = "C:\\Users\\HASMUKH KHAN S\\Desktop\\javacode1/Book.xlsx";

        List<Student> marklist = null;
        try {
            marklist = new ArrayList<Student>();

            marklist = convertExcel(filePath);

        } catch (IOException e) {
            System.out.println("io error");
        }
        String flag = "Y";
        while(flag.equalsIgnoreCase("Y"))
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("\t"+"\t"+"\t"+"WELCOME TO MARKLIST MENU");
            System.out.println("\t"+"\t"+"\t"+"-------------------------");
            System.out.println("\t"+"\t"+"\t"+"You wish to find your account by "+"\n" + "\t"+"\t"+"\t"+ "1.NAME" +"\n" + "\t"+"\t"+"\t"+ "2.ADMISSION NUMBER "+
                    "\n" +"\t"+"\t"+"\t"+"3.EXIT");
            System.out.print("\t"+"\t"+"\t"+"KINDLY ENTER YOUR CHOICE (1/2/3):-");
            String choice =sc.nextLine();
            List <Student> selectedList = new ArrayList<>();


            switch (choice) {
                case "1":
                    System.out.println("KINDLY ENTER THE NAME:");
                    String nam = sc.next();

                    for (Student student : marklist) {
                        if (student.getName().toLowerCase().equals(nam.toLowerCase())) {
                            selectedList.add(student);
                            System.out.println(selectedList);
                        }
                    }
                    if(selectedList.isEmpty()){
                        System.out.println(" THERE IS NO ONE WITH THAT NAME  IN DATABASE");
                    }
                    break;
                case "2":
                    System.out.println("KINDLY ENTER THE ADMISSION NUMBER");
                    Integer addNo = sc.nextInt();
                    for (Student student : marklist) {
                        if (student.getAddNo().equals(addNo)) {
                            selectedList.add(student);
                            System.out.println(selectedList);
                        }

                    }
                    if(selectedList.isEmpty())
                    {
                        System.out.println(" THERE IS NO ONE WITH THAT ACCOUNT NUMBER  IN DATABASE");
                    }
                    break;

                case "3":
                    System.out.println("THANK YOU !!!!!!");
                    break;

                default:
                    System.out.println("INVALID ENTRY");
                    break;
            }


            StudentOutput so = new StudentOutput();
            for (Student s : selectedList) {
                //StudentOutput so =new StudentOutput();
                so.setName(s.getName());
                so.setAddNo(s.getAddNo());
                so.setPercent(((s.getMarphy() + s.getMarmat() + s.getMarche()) * 100) / 300 + "%");
                Subject phy = new Subject();
                phy.setMark(s.getMarphy());
                String grade = gradereturn(s.getMarphy());
                phy.setGrade(grade);
                String gradepoint = gradepoint(s.getMarphy());
                phy.setGradePoint(gradepoint);
                so.setPhysics(phy);
                Subject che = new Subject();
                che.setMark(s.getMarche());
                String grade1 = gradereturn(s.getMarche());
                che.setGrade(grade1);
                String gradepoint1 = gradepoint(s.getMarche());
                che.setGradePoint(gradepoint1);
                so.setChemistry(che);
                Subject math = new Subject();
                math.setMark(s.getMarmat());
                String grade2 = gradereturn(s.getMarmat());
                math.setGrade(grade2);
                String gradepoint2 = gradepoint(s.getMarmat());
                math.setGradePoint(gradepoint2);
                so.setMaths(math);
                System.out.println(so);
            }
//            flag="n";
            Scanner sc1 =new Scanner(System.in);
            System.out.println("DO YOU WANT TO CONTINUE (Y/N)");
            flag = sc1.nextLine();
            System.out.println(flag);
        }

    }

    public static String gradereturn(Integer marks)
    {
        if (marks >=91)
        {
            return "A1";
        }
        else if (marks >=81 && marks<=90)
        {
            return "A2";
        }
        else if (marks >=71 && marks<=80)
        {
            return "B1";
        }

        else if (marks >=61 && marks<=70)
        {
            return "B2";
        }
        else if (marks >=51 && marks<=60)
        {
            return "C1";
        }
        else if (marks >=41 && marks<=50)
        {
            return "C2";

        }
        else if (marks >=33 && marks<=40)
        {
            return "D";
        }
        else if (marks >=21 && marks<=32)
        {
            return "E1";
        }
        else if (marks >=00 && marks<=20)
        {
            return "E2";
        }
        else{
            return "invalid marks";
        }

    }

    public static String gradepoint(Integer marks)
    {
        if (marks >=91)
        {
            return "10.0";
        }
        else if (marks >=81 && marks<=90)
        {
            return "9.0";
        }
        else if (marks >=71 && marks<=80)
        {
            return "8.0";
        }

        else if (marks >=61 && marks<=70)
        {
            return "7.0";
        }
        else if (marks >=51 && marks<=60)
        {
            return "6.0";
        }
        else if (marks >=41 && marks<=50)
        {
            return "5.0";

        }
        else if (marks >=33 && marks<=40)
        {
            return "4.0";
        }
        else if (marks >=21 && marks<=32)
        {
            return "C";
        }
        else if (marks >=00 && marks<=20)
        {
            return "C";
        }
        else{
            return "invalid marks";
        }
    }

    public static List<Student> convertExcel(String filePath) throws IOException {

        List<Student> marklist = new ArrayList<Student>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {

            System.out.println("excel not found" + e);

        }
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            System.out.println("workbook not created ");
        }

        Sheet sh = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sh.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String name = row.getCell(0).getStringCellValue();
            Integer addNo = (int) row.getCell(1).getNumericCellValue();
            Integer marPhy = (int) row.getCell(2).getNumericCellValue();
            Integer marChe = (int) row.getCell(3).getNumericCellValue();
            Integer marmat = (int) row.getCell(4).getNumericCellValue();

            Student student1 = new Student(name, addNo, marPhy, marChe, marmat);
            marklist.add(student1);


        }
        return marklist;
    }
}