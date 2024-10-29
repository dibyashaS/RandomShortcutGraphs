import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Main {

        public static void main(String[] args) throws FileNotFoundException {
        		String filename = "../graph.txt";
                System.out.println("Processing " + filename);
                Diameter d = new Diameter(new File(filename));
                System.out.println(d.getDiameter());
                System.out.println(d.getAverageDistance());

                //this is code to collect the data for the presentation                                                     

//              System.out.println(d.getAverageDistance());                                                                                    
//              System.out.println("Start");                                                                                                   
//              int[][] usage = d.getUsage();                                                                                                  
//              int[][]distance=d.getDistance();                                                                                               
//              List<Edge> use = new ArrayList<>();                                                                                            
//              Map<Integer,Integer> uses=new HashMap<>();                                                                                     
//              int n=usage.length;                                                                                                            
//              for(int i=0;i<n;i++) {                                                                                                         
//                      for(int j=i+2;j<n;j++) {                                                                                               
//                              if(i==0&&j==n-1)                                                                                               
//                                      continue;                                                                                              
//                              if(usage[i][j]>0) {                                                                                            
//                                      Edge e = new Edge(i,j,usage[i][j]);                                                                    
//                                      use.add(e);                                                                                            
//                                      if(uses.containsKey(usage[i][j])) {                                                                    
//                                              int temp=uses.get(usage[i][j])+1;                                                              
//                                              uses.put(usage[i][j],temp);                                                                    
//                                      } else {                                                                                               
//                                              uses.put(usage[i][j],0);                                                                       
//                                      } 
//              }                                                                                                              
//      }                                                                                                                      
//}                                                                                                                              
//use.sort(Comparator.comparing(Edge::getUsage));                                                                                
//String result="";                                                                                                              
//Set<Integer> use = uses.keySet();                                                                                              
//for(int e:use) {                                                                                                               
//      result+=" "+ uses.get(e)+ "\n";                                                                                        
//}                                                                                                                              
//                                                                                                                             
//System.out.println(result);                                                                                                    
//                                                                                                                             
//                                                                                                                             
//                                                                                                                             
//int s=d.getSize();                                                                                                             
//int D=d.getDiameter();                                                                                                         
//System.out.println(D);                                                                                                         
//int[][] numDistance= new int[D][s];                                                                                            
//for(int i=0;i<s;i++) {                                                                                                         
//      for(int j=1;j<=D;j++) {                                                                                                
//              for(int z=0;z<s;z++) {                                                                                         
//                      if(distance[i][z]==j) {  
//              numDistance[j-1][i]++;                                                                         
//      }                                                                                                      
//}                                                                                                              
//}                                                                                                                      
//}                                                                                                                              
//String result="";                                                                                                              
//for(int i=0;i<s;i++) {                                                                                                         
//for(int j=1;j<=D;j++)                                                                                                  
//result+=numDistance[j-1][i]+" ";                                                                               
//                                                                                                             
//result+="\n";                                                                                                          
//}                                                                                                                              
//System.out.println(result);                                                                                                    
}

}                
                