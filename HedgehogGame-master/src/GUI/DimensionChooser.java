package GUI;

import DataClasses.Variables;

import javax.swing.*;
import java.awt.*;

public class DimensionChooser {

    private static final Dimension DEFAULT = Variables.DIMENSIONS[0];
    private static Box box;
    private static ButtonGroup group;

    public static Dimension getDimension(){
        JPanel panel = new JPanel();
        box = Box.createVerticalBox();
        group = new ButtonGroup();
        for(int i = 0; i < Variables.DIMENSIONS.length; i++){
            addButton(i);
        }
        panel.add(box);
        JOptionPane.showMessageDialog(null, panel);
        int index = Integer.parseInt(group.getSelection().getActionCommand());
        return Variables.DIMENSIONS[index];
    }

    private static void addButton(int index){
        Dimension d = Variables.DIMENSIONS[index];
        String text = "" + (int)d.getWidth() + "x" + (int)d.getHeight();
        JRadioButton button = new JRadioButton(text);
        if (d == DEFAULT)
            button.setSelected(true);
        button.setActionCommand("" + index);
        group.add(button);
        box.add(button);
    }

}
