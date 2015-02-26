/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LuffarSchack;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.sql.*;

/**
 *
 * @author seb
 */
public class ShowResults extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;  // Uppg 6
    private int spelID;

    public ShowResults(int spelID) {
        Container c = getContentPane();
        JButton bytData = new JButton("Byt data");
        JButton valdaRader = new JButton("Valda rader");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.spelID = spelID;
        try {
            MysqlDB.kopplaUpp();
            String query = "SELECT spelarnamn, poäng, datum, tid FROM highscores WHERE spelID ="+ "'" + spelID + "'";
            ResultSet rs = MysqlDB.statement.executeQuery(query);
            String[] headers = DBMethods.getHeaders(rs);
            Object[][] content = DBMethods.getContent(rs);
//            tableModel = new DefaultTableModel(content, headers);  // Uppg 6
//            table = new JTable(tableModel);         // Uppg 6
            table = new JTable(content, headers);     // Uppg 3
//            valdaRader.addActionListener(new VL()); // Uppg 5
//            bytData.addActionListener(new AL());    // Uppg 6
            scrollPane = new JScrollPane(table);
            c.add(scrollPane, BorderLayout.CENTER);
            //c.add(valdaRader, BorderLayout.NORTH);
            //c.add(bytData, BorderLayout.SOUTH);
        } catch(SQLException e) {
            System.out.println(e);
        }
        DBMethods.setColumnWidth(table, new int[]{120,120,40,40});  // Relativa bredder
        setVisible(true);
    }

    public void setTableContent(Object[][] content, Object[] headers) {
        tableModel.setDataVector(content, headers);
    }

    // Uppg 5
//    private class VL implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            int[] selectedRows = table.getSelectedRows();
//            String res="";
//            for(int i=0; i<selectedRows.length; i++) {
//                res += ""+selectedRows[i]+". "+table.getValueAt(selectedRows[i],0)+"\n";
//            }
//            System.out.println(res);
//        }
//    }

    // Uppg 6
//    private class AL implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            try {
//                ResultSet rs = MysqlDB.statement.executeQuery("SELECT * FROM spel");
//                tableModel.setDataVector(DBMethods.getContent(rs),
//                                         DBMethods.getHeaders(rs));
//            } catch (SQLException e1) {}
//        }
//    }

     public static void runMain(String classname) throws Exception {
        Class cls = Class.forName( classname );
        Method method = cls.getDeclaredMethod( "main", new Class[]{String[].class} );
        method.invoke( null, new Object[]{null} );
        System.out.println( " " + classname + " exekverad" );
    }

    public static void main(String[] args)  {
        new ShowResults(1);
    }
}


