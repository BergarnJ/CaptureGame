import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    private final List<Point> points = new ArrayList<>();
    private final List<Rectangle> collectionSquares;
    private final List<Color> collSquareColors;
    private Color currentLineColor = Color.BLACK;

    // Constructor that accepts squares and colors from CreateWindow
    public DrawPanel(List<Rectangle> collectionSquares, List<Color> collSquareColors) {
        this.collectionSquares = collectionSquares;
        this.collSquareColors = collSquareColors;

        // Add mouse listener to handle clicks
        addMouseListener(new MouseAdapter(){ 
            @Override
            public void mousePressed(MouseEvent e) {
                
                Point clickPoint = e.getPoint();

                for (int i = 0; i < collectionSquares.size(); i++) {

                    if (collectionSquares.get(i).contains(clickPoint)) {

                        //Set the currentLineColor to the same as the square you click on
                        currentLineColor = collSquareColors.get(i);
                        repaint(); //apply color change

                        // Set up mouse listener for real-time drawing
                        addMouseMotionListener(new MouseMotionAdapter() {
                            @Override
                            public void mouseDragged(MouseEvent e) {
                                points.add(e.getPoint());
                                repaint();
                            }

                        });
                        
                    }

                    else {
                        
                    }

                }

            }

            public void mouseReleased(MouseEvent e) {

                points.removeAll(points);
            }

        });
         
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        super.paintComponent(g);


        // Draw each square with its current color
        for (int i = 0; i < collectionSquares.size(); i++) {
            g.setColor(collSquareColors.get(i));
            Rectangle square = collectionSquares.get(i);
            g.fillRect(square.x, square.y, square.width, square.height);
        }
        g.setColor(currentLineColor);
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}
