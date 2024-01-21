/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import za.ac.tut.StudentFrame.addStudent;

/**
 *
 * @author HOME
 */
public class StudentFrame extends JFrame{
      StudentManagerDB db;
    JPanel mainPnl, namePnl, weightPnl, agePnl, genderPnl, headingPnl, nameWeightAgeGenderCmbPnl
            ,scrllButtonsPnl , btnsPnl;
    JLabel headingLbl, nameLbl, weightLbl, genderLbl, ageLbl;
    JTextField nameTxtFld, weightTxtFld;
    JComboBox ageCmbBx;
    JRadioButton genderF, genderM;
    ButtonGroup btnGrp;
    JTextArea textArea;
    JScrollPane scrllPn;
    JButton addBtn, deleteBtn, updateBtn, getStudent, getAllSudents, clearTxtArea;
    
    public StudentFrame() throws SQLException{
        
        db = new StudentManagerDB("jdbc:derby://localhost:1527/GuiDB;create=true", "123", "app");
        setSize(550, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setTitle("STUDENTS FORM");
        
        mainPnl = new JPanel(new BorderLayout());
        namePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        weightPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        agePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headingPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nameWeightAgeGenderCmbPnl = new JPanel(new  GridLayout(4, 1, 1, 1));
        scrllButtonsPnl = new JPanel(new GridLayout(2, 1, 1, 1));
        btnsPnl = new JPanel(new FlowLayout());
        
        headingLbl = new JLabel("Student form");
        nameLbl = new JLabel("Name: ");
        weightLbl = new JLabel("Weight: ");
        genderLbl = new JLabel("Gender: ");
        ageLbl = new JLabel("Age: ");
        
        nameTxtFld = new JTextField(10);
        weightTxtFld = new JTextField(3);
        ageCmbBx =  new JComboBox();
        ageCmbBx.addItem("10-20");
        ageCmbBx.addItem("21-30");
        genderM = new JRadioButton("Male");
        genderF = new JRadioButton("Female");
        btnGrp = new ButtonGroup();
        
        textArea = new JTextArea(10, 30);
        scrllPn = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        addBtn = new JButton("add student");
        addBtn.addActionListener(new addStudent());
        deleteBtn = new JButton("delete student");
        deleteBtn.addActionListener(new deleteStudent());
        updateBtn = new JButton("update student");
        updateBtn.addActionListener(new updateStudent());
        getStudent = new JButton("display student");
        getStudent.addActionListener(new getStudent());
        getAllSudents = new JButton("display all student");
        getAllSudents.addActionListener(new getAllStudent());
        clearTxtArea = new JButton("clear-text-area");
        clearTxtArea.addActionListener(new ClearTextAre());
        
         
        headingPnl.add(headingLbl);
        namePnl.add(nameLbl);
        namePnl.add(nameTxtFld);
        weightPnl.add(weightLbl);
        weightPnl.add(weightTxtFld);
        agePnl.add(ageLbl);
        agePnl.add(ageCmbBx);
        genderPnl.add(genderLbl);
        btnGrp.add(genderF);
        btnGrp.add(genderM);
        genderPnl.add(genderF);
        genderPnl.add(genderM);
        
        nameWeightAgeGenderCmbPnl.add(namePnl);
        nameWeightAgeGenderCmbPnl.add(weightPnl);
        nameWeightAgeGenderCmbPnl.add(agePnl);
        nameWeightAgeGenderCmbPnl.add(genderPnl);
        btnsPnl.add(addBtn);
        btnsPnl.add(deleteBtn);
        btnsPnl.add(updateBtn);
        btnsPnl.add(getStudent);
        btnsPnl.add(getAllSudents);
        btnsPnl.add(clearTxtArea);
        
        scrllButtonsPnl.add(scrllPn);
        scrllButtonsPnl.add(btnsPnl);
        
        mainPnl.add(headingPnl, BorderLayout.NORTH);
        mainPnl.add(nameWeightAgeGenderCmbPnl, BorderLayout.CENTER);
        mainPnl.add(scrllButtonsPnl, BorderLayout.SOUTH);
        
        add(mainPnl);
        pack();
        setVisible(true);
    }

    private class addStudent implements ActionListener {

        public addStudent() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = nameTxtFld.getText();
                Double weight = Double.valueOf(weightTxtFld.getText());
                String age = ageCmbBx.getSelectedItem().toString();
                Character gender = 'F';
                if(genderM.isSelected()){
                    gender = 'M';
                }
                Student s1 = new Student(name, weight, age, gender);
                db.add(s1);
            } catch (SQLException ex) {
                Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

                   }
    }

    private class deleteStudent implements ActionListener {

        public deleteStudent() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = nameTxtFld.getText();
                db.delete(name);
            } catch (SQLException ex) {
                Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class updateStudent implements ActionListener {

        public updateStudent() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = nameTxtFld.getText();
                Double weight = Double.parseDouble(weightTxtFld.getText());
                db.update(name, weight);
                
            } catch (SQLException ex) {
                Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class getStudent implements ActionListener {

        public getStudent() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Student student;
            try {
                String name = nameTxtFld.getText();
                 student = db.get(name);
//                 Double weight = student.getWeight();
//                 String age = student.getAge();
//                 Character gender = student.getGender();
//                 Student s1 = new Student(name, weight, age, gender);
                textArea.setText(student.toString());
            } catch (SQLException ex) {
                Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class getAllStudent implements ActionListener {

        public getAllStudent() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Student> students= db.getAll();
                String info = "";
                for (Student student : students) {
                    info += student.toString()+"\n";
                    
                }
                textArea.setText(info);
            } catch (SQLException ex) {
                Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class ClearTextAre implements ActionListener {

        public ClearTextAre() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
           textArea.setText(" ");
        }
    }

    
}
