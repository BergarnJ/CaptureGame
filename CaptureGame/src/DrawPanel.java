import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DrawPanel extends JPanel {

    private final List<List<Point>> lines = new ArrayList<>();
    private final List<Rectangle> collectionSquares;
    private final List<Color> collSquareColors;
    private Color currentLineColor = Color.BLACK;
    private boolean canDraw = false;
    private List<Point> currentLine = new ArrayList<>();
    private Rectangle targetCollectionSquare;

    // Constructor that accepts squares and colors from CreateWindow
    public DrawPanel(List<Rectangle> collectionSquares, List<Color> collSquareColors) {
        this.collectionSquares = collectionSquares;
        this.collSquareColors = collSquareColors;


        
        // Add mouse listener to handle clicks
        addMouseListener(new MouseAdapter(){ 
            @Override
            public void mousePressed(MouseEvent e) {
                
                Point clickPoint = e.getPoint();
                canDraw = false;

                for (int i = 0; i < collectionSquares.size(); i++) {

                    if (collectionSquares.get(i).contains(clickPoint)) {

                        
                        currentLineColor = collSquareColors.get(i); //Set the currentLineColor to the same as the square you click on
                        targetCollectionSquare = collectionSquares.get(i);
                        canDraw = true; // Enable drawing since the click was over a square
                        repaint();      // Apply color change
                        break;          // Exit loop once a square is found

                    }

                }

            }

            public void mouseReleased(MouseEvent e) {
                if (!currentLine.isEmpty()) {
                    lines.add(new ArrayList<>(currentLine));
                }
                currentLine.clear();  
                canDraw = false;// Disable drawing after releasing the mouse
            }

        });

        // Set up mouse listener for real-time drawing
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(canDraw) { //Only allow drawing if canDraw = true
                    currentLine.add(e.getPoint());
                    repaint();
                }
               
            }

        });
         
    }

    public void update(float timestep) {
        Iterator<List<Point>> lineIterator = lines.iterator();
        while (lineIterator.hasNext()) {
            List<Point> line = lineIterator.next();

            boolean allPointsInSquare = true;
            for (Point p : line) {
                
                
                if (targetCollectionSquare != null) {
                    // Calculate direction vector
                    int dx = targetCollectionSquare.x + targetCollectionSquare.width / 2 - p.x;
                    int dy = targetCollectionSquare.y + targetCollectionSquare.height / 2 - p.y;
                    p.x += dx * timestep * 0.1;  // Adjust 0.1 as a pull strength factor
                    p.y += dy * timestep * 0.1;
                } 

                // Check if the point is inside any square
                if (!isPointInAnySquare(p)) {
                    allPointsInSquare = false;
                }
            }

            // Remove the line if all points are inside a square
            if (allPointsInSquare) {
                lineIterator.remove();
            }

        }
      
    }

    // Helper function to check if a point is inside any square
    private boolean isPointInAnySquare(Point p) {
        for (Rectangle square : collectionSquares) {
            if (square.contains(p)) {
                return true;
            }
        }
        return false;
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

        for (List<Point> line : lines) {
            if (line.size() > 1) {
                g.setColor(currentLineColor);
                for (int i = 1; i < line.size(); i++) {
                    Point p1 = line.get(i - 1);
                    Point p2 = line.get(i);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }

            }
        }

        g.setColor(currentLineColor);
        for (int i = 1; i < lines.size(); i++) {
            Point p1 = currentLine.get(i - 1);
            Point p2 = currentLine.get(i);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
        
    }
}
