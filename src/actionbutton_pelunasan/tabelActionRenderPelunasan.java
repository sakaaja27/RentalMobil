/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionbutton_pelunasan;
import ActionButton.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author sakab
 */
public class tabelActionRenderPelunasan extends  DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        PanelActionPelunasan action = new PanelActionPelunasan();
//        if (isSeleted == false && row % 2 == 0) {
//            action.setBackground(Color.PINK);
//        } else {
            action.setBackground(com.getBackground());
//        } 
        return action;
    }
    

}
