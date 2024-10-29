import java.io.FileNotFoundException;

public class Main {

        public static void main(String[] args) throws FileNotFoundException {
        		System.out.println("Creating the graph file");
        		Shortcut sc = new Shortcut(64, 7, 8, 2 ,"../graph.txt");
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