


public class CaptureGame {
	
	public static void main(String[] args) throws Exception {
        
                CreateWindow game_window = new CreateWindow();
                
                

                boolean running = true;
                long lastTime = System.currentTimeMillis();

                while(running){
                        long time = System.currentTimeMillis();
                        if (time > lastTime) {
                                float timestep = (float) (0.001 * (time - lastTime));
                                

                                System.out.println("Timestep = " + timestep);
                        }
                       
                        
                        lastTime = time;
                }

        }

   
    
}

