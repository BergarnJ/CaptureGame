


public class CaptureGame {
	
	public static void main(String[] args) throws Exception {
        
                CreateWindow game_window = new CreateWindow();
                
                

                boolean running = true;
                
                long lastTime = System.currentTimeMillis();
                while(running){
                        long time = System.currentTimeMillis();
                        float timestep = (float) (0.001 * (time - lastTime));
                        if (timestep <= 0 || timestep > 1.0) {
                                timestep = (float) 0.001;
                        }
                        
                        lastTime = time;
                }

        }

   
    
}

