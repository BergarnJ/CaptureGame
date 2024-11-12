import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateWindow {

    private JFrame game_Window;
    private List<Rectangle> collectionSquares;
    private List<Color> collSquareColors;

    public CreateWindow() {
        // Set up JFrame
        game_Window = new JFrame();
        game_Window.setSize(1600, 800);
        game_Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define squares and their colors
        collectionSquares = new ArrayList<>();
        collSquareColors = new ArrayList<>();
        int collectionSize = 100; // Size of each square

         // Define positions and colors for each square (one in each corner)
         collectionSquares.add(new Rectangle(50, 50, collectionSize, collectionSize)); // Top-left corner
         collSquareColors.add(Color.RED);
 
         collectionSquares.add(new Rectangle(game_Window.getWidth() - (collectionSize + 50), 50, collectionSize, collectionSize)); // Top-right corner
         collSquareColors.add(Color.BLUE);
 
         collectionSquares.add(new Rectangle(50, game_Window.getHeight() - (collectionSize + 50), collectionSize, collectionSize)); // Bottom-left corner
         collSquareColors.add(Color.PINK);
 
         collectionSquares.add(new Rectangle(game_Window.getWidth() - (collectionSize + 50), game_Window.getHeight() - (collectionSize + 50), collectionSize, collectionSize)); // Bottom-right corner
         collSquareColors.add(Color.YELLOW);



        
        // Create DrawPanel with these squares and colors
        DrawPanel drawPanel = new DrawPanel(collectionSquares, collSquareColors);
        game_Window.add(drawPanel);
        drawPanel.setBackground(Color.darkGray);
        
        // Show the window
        game_Window.setVisible(true);
    }
}
