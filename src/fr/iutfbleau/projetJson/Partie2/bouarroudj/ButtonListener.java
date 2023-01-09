import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Cursor;
import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.Border;
import java.io.*;
import java.lang.Thread;
import java.sql.SQLException;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ButtonListener implements MouseListener{
    JButton button;

    public ButtonListener(JButton button) {
        this.button = button;
    }



    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {
        this.button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

}