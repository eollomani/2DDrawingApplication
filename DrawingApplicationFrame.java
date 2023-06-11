/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Java2dDrawingApplication;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author endri
 */
public class DrawingApplicationFrame extends JFrame
{
    /**
     * @param args the command line arguments
     */
    //get rid of static
    //private  JFrame frame;
    //top panel
    private JPanel TopPanel;
    // variables for Line1
    private  JPanel line1;
    private  JLabel line1_shape;
    private  JComboBox shape_selection;
    private  JButton color1_button;
    private  JButton color2_button;
    private  JButton Undo;
    private  JButton Clear;
    //private static Color firstcolorselection;
    //private static Color secondcolorselection;
    private  Color color1 = Color.BLACK;
    private  Color color2 = Color.BLACK;
    private  ArrayList<String> shapes_select;
    private Paint paint;
    private Stroke stroke;
    //variables for Line2
    private  JPanel line2;
    private  JLabel option;
    private  JLabel line_width;
    private  JLabel dash_length;
    private  JCheckBox filled;
    private  JCheckBox use_gradient;
    private  JCheckBox dashed;
    private  JSpinner lw;
    private  JSpinner dl;
    
    //Variables for drawPanel
    private  DrawPanel drawPanel;
    private  ArrayList<MyShapes> Shapes = new ArrayList<>();
    //private static String shape, color;
    //private static Object shape;
    private Point start;
    private Point end;
        
    //Variables for southPanel;
    private  JPanel statusPanel;
    private  JLabel statusLabel;
   
    
       public DrawingApplicationFrame()
    {
        // add widgets to panels
        //frame = new JFrame();
        setLayout(new BorderLayout());
        // firstLine widgets
        line1 = new JPanel();
        line1_shape = new JLabel("Shape: ");
        String[] shapesselect = {"line", "oval", "rectangle"};
        shape_selection = new JComboBox(shapesselect);
        color1_button = new JButton("1st Color...");
        color2_button = new JButton("2nd Color...");
        Undo = new JButton("Undo");
        Clear = new JButton("Clear");
        
        
        
        
        //adding line1 widgets
        line1.add(line1_shape);
        line1.add(shape_selection);
        line1.add(color1_button);
        line1.add(color2_button);
        line1.add(Undo);
        line1.add(Clear);
        line1.setBackground(Color.cyan);
        line1.setSize(750,35);
        line1.setLayout(new FlowLayout(FlowLayout.CENTER,10,1));
        //add(line1);
     
        // secondLine widgets
        line2 = new JPanel();
        option = new JLabel("Options: ");
        filled = new JCheckBox("Filled", false);
        use_gradient = new JCheckBox("Use Gradient", false);
        dashed = new JCheckBox("Dashed", false);
        lw = new JSpinner(new SpinnerNumberModel(1,1,100,1));
        line_width = new JLabel("Line Width:");
        dash_length = new JLabel("Dash Length:");
        dl = new JSpinner(new SpinnerNumberModel(1,1,100,1));
        
        //adding Line2 widgets
        line2.add(option);
        line2.add(filled);
        line2.add(use_gradient);
        line2.add(dashed);
        line2.add(line_width);
        line2.add(lw);
        line2.add(dash_length);
        line2.add(dl);
        //line2.setSize(750,70);
        line2.setBackground(Color.cyan);
        line2.setLayout(new FlowLayout());
        //TopPanel.add(line2, BorderLayout.SOUTH);
        
        
        //TopPanel.setLayout(new BorderLayout());
        //topPanel 
        TopPanel = new JPanel();
        TopPanel.setLayout(new BorderLayout());
        TopPanel.add(line1, BorderLayout.NORTH);
        TopPanel.add(line2, BorderLayout.SOUTH);
        add(TopPanel, BorderLayout.NORTH);
        
        //drawPannel Widgets
        drawPanel = new DrawPanel();
        //drawPanel.setSize(750,500);
        //drawPanel.setBackground(Color.WHITE);
        //drawPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,70));
        add(drawPanel, BorderLayout.CENTER);
        
        
        //statusPanel Widgets
        statusPanel = new JPanel();
        statusLabel = new JLabel("(,)");
        //statusPanel.setSize(750,550);
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,500));
        statusPanel.add(statusLabel);
        add(statusLabel, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Java 2D Drawing");
        setSize(750,550);
        
        
        //add listeners and event handlers
        

        
        //Color 1 Button
        color1_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                color1 = JColorChooser.showDialog(null,"1st Color",color1);
            }
        });
        
        //Color 2 Button
        color2_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                color2 = JColorChooser.showDialog(null, "2nd Color", color2);            }
        });
        
        //Undo Button
        Undo.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent event){
               if(!Shapes.isEmpty()){
                   Shapes.remove(Shapes.size()-1);
                   repaint();
               }
           } 
        });
        
        //Clear Button
        Clear.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent event){
               Shapes.clear();
               repaint();
           } 
        });
        
        setVisible(true);
    }
   
    
    private class DrawPanel extends JPanel
    {
        public DrawPanel()
        {
       
            start = null;
            end = null;
            setBackground(Color.WHITE);
            MouseHandler handle = new MouseHandler();
            addMouseListener(handle);
            addMouseMotionListener(handle);
            //add event handler to draw panel
            
            //new mouse handler for mouselistener and mouse motionlistener
        //}
        
        }
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            for (MyShapes shape: Shapes){
               shape.draw(g2d);
            }

        }
        


        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {

            public void mousePressed(MouseEvent event)
            {
                
            //code
            //start point
            start = new Point(event.getX(), event.getY());
            //paint variable
            paint = color1;
            Paint gradientpaint = new GradientPaint(0, 0, color1, 50, 50, color2, true);
            //stroke variable
            
            if (dashed.isSelected())
            {
                stroke = new BasicStroke((Integer)lw.getValue(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10,new float[]{(Integer)dl.getValue()}, 0);
            } else
            {
                stroke = new BasicStroke((Integer)lw.getValue(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            }
            //creates a new line if line is slected
            if(shape_selection.getSelectedItem() == "line"){
                if(use_gradient.isSelected()){
                    MyShapes current = new MyLine(start, start, gradientpaint, stroke);
                    Shapes.add(current);
                }else{
                    MyShapes current = new MyLine(start, start, paint, stroke);
                    Shapes.add(current);
                }
            }
            //creates a new oval if oval is selected
            if(shape_selection.getSelectedItem()=="oval"){
                if(use_gradient.isSelected()){
                    MyShapes current = new MyOval(start, start, gradientpaint, stroke, filled.isSelected());
                    Shapes.add(current);
                }else{
                    MyShapes current = new MyOval(start, start, paint, stroke, filled.isSelected());
                    Shapes.add(current); 
                }
            }
            //creates a new rectangle if rectangle is slected
            if(shape_selection.getSelectedItem() == "rectangle"){
                if(use_gradient.isSelected()){
                    MyShapes current = new MyRectangle(start, start, gradientpaint, stroke, filled.isSelected());
                    Shapes.add(current);
                }else{
                    MyShapes current = new MyRectangle(start, start, paint, stroke, filled.isSelected());
                    Shapes.add(current);
                }
            }
             //set paint varaiable
             //create stroke varaiable
             //determine which shape is being drawn and determine if you need a new my...()
                
             //add shape to array list shapes
             
             //call repaint mouse released
             
             //event.getpoint =start point
            }

            public void mouseReleased(MouseEvent event)
            {
            
             
             if (Shapes.size()!=0){
               Shapes.get(Shapes.size()-1).setEndPoint(new Point(event.getX(), event.getY()));
               //repaint();
            }
            }

            @Override
            public void mouseDragged(MouseEvent event)
            {
             // update end point
             if (Shapes.size()!=0){
               Shapes.get(Shapes.size()-1).setEndPoint(event.getPoint());
               repaint();
            }
            }

            @Override
            public void mouseMoved(MouseEvent event)
            {
                statusLabel.setText(event.getX()+","+event.getY());
            }
        }

    }
}



