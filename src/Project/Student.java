
package Project;

import javax.swing.JOptionPane;

public class Student{
    private String fname;
    private String lname;
    private int AM;
    private String semester;
    
    public Student(){
        fname=JOptionPane.showInputDialog("ΕΙΣΑΓΩΓΗ ΟΝΟΜΑΤΟΣ: ");
        lname=JOptionPane.showInputDialog("ΕΙΣΑΓΩΓΗ ΕΠΩΝΥΜΟΥ: ");
        AM=Integer.parseInt(JOptionPane.showInputDialog("ΕΙΣΑΓΩΓΗ AM: "));
        semester=JOptionPane.showInputDialog("ΕΙΣΑΓΩΓΗ ΕΞΑΜΗΝΟΥ: ");
    }

    
    public Student(String fname, String lname, int AM, String semester){
        this.fname=fname;
        this.lname=lname;
        this.AM=AM;
        this.semester=semester;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAM() {
        return AM;
    }

    public void setAM(int AM) {
        this.AM = AM;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    @Override
    public String toString() {
        return fname + "," + lname + "," + AM + "," + semester;
    }
}
