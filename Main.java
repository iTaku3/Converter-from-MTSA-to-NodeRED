//ArrayList
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//ファイル入力まわり
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

class Main
{
  public static void main(String args[])
  {
    System.out.println();
    System.out.println("= notification ================================================");
    System.out.println("Enter the address of the controller file & connexion file. ");
    System.out.println("[!] You need to write a file extension (eg \".txt\").");
    System.out.println(" -------------------------------------------------------------");
    System.out.println(" > controller file : The name of the controller output file synthesized by MTSA.");
    System.out.println(" > connexion file  : The name of the file that describes the Controllable action and node information.");
    System.out.println("===============================================================");
    System.out.println();
    System.out.print("controller file address : ./*input/");
    Scanner scan = new Scanner(System.in);
    String controller_file_name = scan.nextLine();
    System.out.print("connexion file address  : ./*input/");
    String connexion_file_name = scan.nextLine();
    System.out.println();
    System.out.println("--------------------------------------------------------------");

    System.out.println(controller_file_name);  
    System.out.println(connexion_file_name);

    //Sample
    // controller_file_name = "Controller.txt";
    // connexion_file_name  = "Connexion.txt";

    try{
      File controller_file = new File("./*input/"+controller_file_name);
      File connexion_file = new File("./*input/"+connexion_file_name);
      //File controller_file = new File("./*sample/TwoRoom/Controller.txt");

      if (checkBeforeReadfile(controller_file)){
        
        //Controllerの読み込み
        BufferedReader br = new BufferedReader(new FileReader(controller_file));

        String str;
        List<String> lts_data = new ArrayList<String>();
        while((str = br.readLine()) != null){
          if(str.contains("|")){
            lts_data.set(lts_data.size()-1,lts_data.get(lts_data.size()-1) + str.trim());
          }else{
            lts_data.add(str.trim());
          }
        }
        for (int count = 0 ; count<6 ; count++ ){
        	lts_data.remove(0);
        }
        br.close();

        //Connexionの読み込み
        br = new BufferedReader(new FileReader(connexion_file));
        List<String> action_data = new ArrayList<String>();
        List<List<String>> node_data = new ArrayList<List<String>>();
        List<String> js_data = new ArrayList<String>();

        while((str = br.readLine()) != null)
        {  
          if(str.contains(">>>"))
          {
            if(!js_data.isEmpty())
            {
              node_data.add(js_data);
              js_data = new ArrayList<String>();
            }
            str = str.replace(">>>", "").replace(",", "");
            action_data.add(str.trim());

          }else{
            if(!str.equals("")) js_data.add(str.trim());
          }
        }
        node_data.add(js_data);
        br.close();

        //System.out.println(lts_data);
        // System.out.println(action_data+"\n");
        // System.out.println(node_data+"\n");
        long start = System.currentTimeMillis();
        Converter.convert(lts_data, action_data, node_data);
        long end = System.currentTimeMillis();
        System.out.println("Conversion complete🍺　[execution time："+ (end - start)  + "ms]");
        System.out.println("Please read the output file (\'./*output/Node-RED_flow.json\') with Node-RED.");
        System.out.println();
      }else{
        System.out.println("File not found.");
      }
    }catch(FileNotFoundException e){
      System.out.println(e);
    }catch(IOException e){
      System.out.println(e);
    }
  }

  private static boolean checkBeforeReadfile(File file){
    if (file.exists()){
      if (file.isFile() && file.canRead()){
        return true;
      }
    }

    return false;
  }
}