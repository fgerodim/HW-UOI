/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import javax.swing.JOptionPane;

public class Lesson{
    private String tittle;
    private int code;
    private String semester;

    public Lesson(){
        tittle=JOptionPane.showInputDialog("ΕΙΣΑΓΩΓΗ ΤΙΤΛΟΥ: ");
        semester=JOptionPane.showInputDialog("ΕΙΣΑΓΩΓΗ ΕΞΑΜΗΝΟΥ: ");
        code=Integer.parseInt(JOptionPane.showInputDialog("ΕΙΣΑΓΩΓΗ ΚΩΔΙΚΟΥ: "));
    }
    public Lesson(String tittle, int code, String semester) {
        this.tittle = tittle;
        this.code = code;
        this.semester = semester;
    }

    public String getTittle() {
        return tittle;
    }

    public int getCode() {
        return code;
    }

    public String getSemester() {
        return semester;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return  tittle + "," + code + "," + semester;
    }
    
}