package Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentFrame extends JFrame
{
    private ArrayList<Student> mstudents=new ArrayList<Student>();
    private ArrayList<Lesson> mlessons=new ArrayList<Lesson>();
    private ArrayList<Enroll> menrolls=new ArrayList<Enroll>();
    private JTextArea showArea;
    private JMenu file;
    private JMenuItem load;
    
    
    void addLesson(){
        mlessons.add(new Lesson());
    }
    void addStudent(){
        mstudents.add(new Student());
    }
    void statementLesson(){
        String am[]=new String[mstudents.size()];
        for(int i=0;i<mstudents.size();i++){
            am[i]=Integer.toString(mstudents.get(i).getAM());
        }
        String inputAM = (String) JOptionPane.showInputDialog(null, "Α.Μ.:",
        "ΕΠΕΛΕΞΕ ΜΑΘΗΤΗ", JOptionPane.QUESTION_MESSAGE, null, 
        am, 
        am[0]);
        System.out.println(inputAM);
        
        String code[]=new String[mlessons.size()];
        for(int i=0;i<mlessons.size();i++){
            code[i]=Integer.toString(mlessons.get(i).getCode());
        }
        String inputCode = (String) JOptionPane.showInputDialog(null, "ΚΩΔΙΚΟΣ:",
        "ΕΠΕΛΕΞΕ ΚΩΔΙΚΟ ΜΑΘΗΜΑΤΟΣ", JOptionPane.QUESTION_MESSAGE, null, 
        code, 
        code[0]);
        System.out.println(inputCode);
        double grade=Double.parseDouble(JOptionPane.showInputDialog("ΒΑΘΜΟΣ:"));
        menrolls.add(new Enroll(Integer.parseInt(inputAM),Integer.parseInt(inputCode),grade));
    }
    void showStudent()
    {
        showArea.setText("");
        int am=Integer.parseInt(JOptionPane.showInputDialog("A.M:"));
        for(Student s:mstudents){
            if(s.getAM()==am){
                double S=0;
                int count=0;
                for(Enroll e:menrolls){
                    if(am==e.getAM()){
                        S+=e.getGrade();
                        count++;
                    }
                }
                double average=S/count;
                showArea.setText(s.toString()+" Average:"+average);
            }
        }
    }
    void deleteStudent(){
        int am=Integer.parseInt(JOptionPane.showInputDialog("A.M.:"));
        Iterator<Student>itrS=mstudents.iterator();
        while(itrS.hasNext()){
            Student s=itrS.next();
            if(s.getAM()==am){
                itrS.remove();
            }
        }
        Iterator<Enroll>itrE=menrolls.iterator();
        while(itrE.hasNext()){
            Enroll e=itrE.next();
            if(e.getAM()==am){
                itrE.remove();
            }
        }
    }
    void printGrades(){
        int code=Integer.parseInt(JOptionPane.showInputDialog("ΕΙΣΑΓΩΓΗ ΚΩΔΙΚΟΥ:"));
        String students="";
        for(Enroll e:menrolls){
            if(e.getCode()==code){
                int am=e.getAM();
                for(Student s:mstudents){
                    if(s.getAM()==am){
                        students+=s.toString()+" Grade:"+e.getGrade()+'\n';
                    }
                }
            }
        }
        showArea.setText(students);
    }
    void printSemesterGrades(){
        String semester=JOptionPane.showInputDialog("ΕΙΣΑΓΩΓΗ ΕΞΑΜΗΝΟΥ:");
        String courses="";
        for(Lesson l:mlessons){
            if(semester.equals(l.getSemester())){
                courses+=l.toString()+"\n";
            }
        }
        showArea.setText(courses);
    }
    void deleteCourse(){
        int code=Integer.parseInt(JOptionPane.showInputDialog("ΕΙΣΑΓΩΓΗ ΚΩΔΙΚΟΥ:"));
        Iterator<Lesson>itrL=mlessons.iterator();
        while(itrL.hasNext()){
            Lesson l=itrL.next();
            if(l.getCode()==code){
                itrL.remove();
            }
        }
        Iterator<Enroll>itrE=menrolls.iterator();
        while(itrE.hasNext()){
            Enroll e=itrE.next();
            if(e.getCode()==code){
                itrE.remove();
            }
        }
    }
    void saveLists()
    {
      JFileChooser chooser=new JFileChooser();
      int returnVal = chooser.showSaveDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) 
      {
          
          try {
              String filename=chooser.getSelectedFile().getAbsolutePath();
              FileWriter fw=null;
              fw = new FileWriter(filename);
              PrintWriter pw=new PrintWriter(fw);
              pw.println("Students");
              for(Student s:mstudents)
              {
                  pw.println(""+s);
              }
              pw.println("Lessons");
              for(Lesson l:mlessons)
              {
                  pw.println(""+l);
              }
              pw.println("Enrolls");
              for(Enroll e:menrolls)
              {
                  pw.println(""+e);
              }
              fw.close();
          } catch (IOException ex) {
              Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
          } 
          
      }
    }
    
    void loadLists()
    {
        JFileChooser chooser=new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
          
            try {
                String filename=chooser.getSelectedFile().getAbsolutePath();
                FileReader rw=new FileReader(filename);
                Scanner in=new Scanner(rw);
                
                if(in.hasNextLine())
                {
                    String line=in.nextLine();
                    if(line.equals("Students")){
                        while(!line.equals("Lessons")){
                            line=in.nextLine();
                            if(!line.equals("Lessons")){
                                String[] parts=line.split(",");
                                mstudents.add(new Student(parts[0],parts[1],Integer.parseInt(parts[2]),parts[3]));
                            }
                        }
                        while(!line.equals("Enrolls")){
                            line=in.nextLine();
                            if(!line.equals("Enrolls")){
                                String[] parts=line.split(",");
                                mlessons.add(new Lesson(parts[0],Integer.parseInt(parts[1]),parts[2]));
                            }
                        }
                        while(in.hasNextLine()){
                            line=in.nextLine();
                            String[] parts=line.split(",");
                            menrolls.add(new Enroll(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Double.parseDouble(parts[2])));
                        }
                    }
                    
                    
                }
            } catch (IOException ex) 
            {
              
            } 
        }
    }
    void makeMenus(){
        JMenu file = new JMenu("ΑΡΧΕΙΟ");
        JMenuItem load = new JMenuItem("ΦΟΡΤΩΣΗ");
        JMenuItem save = new JMenuItem("ΑΠΟΘΗΚΕΥΣΗ");
        JMenuItem exit = new JMenuItem("ΕΞΟΔΟΣ");
        file.add(load);
        file.add(save);
        file.add(exit);
        JMenuBar bar = new JMenuBar( );
        load.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                loadLists();
            }
            
        });
        save.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                saveLists();
            }
            
        });
        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                System.exit(0);
            }
            
        });
        
        JMenu student = new JMenu("ΜΑΘΗΤΗΣ");
        JMenuItem statement = new JMenuItem("ΔΗΛΩΣΗ ΜΑΘΗΜΑΤΟΣ");
        JMenuItem insert = new JMenuItem("ΝΕΟΣ ΜΑΘΗΤΗΣ");
        JMenuItem print = new JMenuItem("ΕΜΦΑΝΙΣΗ ΜΑΘΗΤΗ");
        JMenuItem delete = new JMenuItem("ΔΙΑΓΡΑΦΗ ΜΑΘΗΤΗ");
        student.add(insert);
        student.add(statement);
        student.add(print);
        student.add(delete);
        insert.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                addStudent();
            }
            
        });
        statement.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                statementLesson();
            }
        });
        print.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                showStudent();
            }
            
        });
        delete.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                deleteStudent();
            }
            
        });
        JMenu course = new JMenu("ΜΑΘΗΜΑ");
        JMenuItem newCourse = new JMenuItem("ΝΕΟ ΜΑΘΗΜΑ");
        JMenuItem showGrades = new JMenuItem("ΕΜΦΑΝΙΣΗ ΕΠΙΔΟΣΕΩΝ");
        JMenuItem selectSemester = new JMenuItem("ΕΠΙΛΟΓΗ ΕΞΑΜΗΝΟΥ");
        JMenuItem deleteCourse = new JMenuItem("ΔΙΑΓΡΑΦΗ ΜΑΘΗΜΑΤΟΣ");
        course.add(newCourse);
        course.add(showGrades);
        course.add(selectSemester);
        course.add(deleteCourse);
        newCourse.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                addLesson();
            }
            
        });
        showGrades.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                printGrades();
            }
        });
        selectSemester.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                printSemesterGrades();
            }
        });
        deleteCourse.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                deleteCourse();
            }
        });
        bar.add(file);
        bar.add(student);
        bar.add(course);
        setJMenuBar(bar);
        
        
    }
    public StudentFrame(String title)
    {
        super(title);
        setSize(600,400);
        setLayout(new FlowLayout());
        makeMenus();
        showArea=new JTextArea("");
        showArea.setRows(15);
        showArea.setColumns(50);
        showArea.setEditable(false);
        JScrollPane sp=new JScrollPane(showArea);
        add(sp);
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
