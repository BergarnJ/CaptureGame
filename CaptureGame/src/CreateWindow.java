

import javax.swing.JFrame;


public class CreateWindow {
    JFrame game_Window = new JFrame();
    public CreateWindow(){
        game_Window.setSize(1600, 800);
        game_Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        game_Window.setVisible(true);
    }
}
