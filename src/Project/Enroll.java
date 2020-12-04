/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

class Enroll{
    private int AM;
    private int code;
    private double grade;

    public Enroll(int AM, int code, double grade) {
        this.AM = AM;
        this.code = code;
        this.grade = grade;
    }

    public int getAM() {
        return AM;
    }

    public int getCode() {
        return code;
    }

    public double getGrade() {
        return grade;
    }

    public void setAM(int AM) {
        this.AM = AM;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return AM + "," + code + "," + grade;
    }
    
}