/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LuffarSchack;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author seb
 */
public class DBMethods {

    public static String[] getHeaders(ResultSet rs) throws SQLException {
        ResultSetMetaData meta;
        String[] headers;
        meta = rs.getMetaData();
        headers = new String[meta.getColumnCount()];
        for (int i = 0; i < headers.length; i++) {
            headers[i] = meta.getColumnLabel(i + 1);
        }
        return headers;
    }

    public static Object[][] getContent(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmt;
        Object[][] content;
        int rows, cols;
        rsmt = rs.getMetaData();
        rs.last();
        rows = rs.getRow();
        cols = rsmt.getColumnCount();
        content = new Object[rows][cols];

        for (int row = 0; row < rows; row++) {
            rs.absolute(row + 1);  // Flytta till rätt rad i resultatmängden
            for (int col = 0; col < cols; col++) {
                content[row][col] = rs.getObject(col + 1);
            }
        }

        return content;
    }

    public static void setColumnWidth(JTable table, int[] colWidth) {
        TableColumnModel columnModel = table.getColumnModel();
        int count = Math.min(table.getColumnCount(), colWidth.length);
        for (int i = 0; i < count; i++) {
            columnModel.getColumn(i).setPreferredWidth(colWidth[i]);
        }
    }
}
