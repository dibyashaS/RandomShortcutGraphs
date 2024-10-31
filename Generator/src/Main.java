import java.io.FileNotFoundException;

public class Main {

        public static void main(String[] args) throws FileNotFoundException {
        		System.out.println("Creating the graph file");
                        // 3 or more intra links per group
                        
        		Shortcut sc = new Shortcut(64, 15, 16, 1 ,"../graph.txt");
                System.out.println(sc);
//              NonRandom nr = new NonRandom(1024,28);                                     
//              System.out.println(nr);                                                    
//                                                                                         
//              while(sc.hasNextLine()) {                                                  
//                      String i=sc.nextLine();                                            
//                      System.out.println(i);                                             
//              }                                                                          
//              sc.close();                                                                
        }
}