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
    System.out.println("--notification-------------------------------------------------");
    System.out.println("\"./*input\"フォルダ内にある，");
    System.out.println("MTSAで合成したControllerのファイル名(拡張子も含む)を入力してください．");
    System.out.println("---------------------------------------------------------------");
    System.out.print("file address : ./input/");
    Scanner scan = new Scanner(System.in);
    String file_name = scan.nextLine();
    System.out.println("-------------------------------");

    try{
      File file = new File("./*input/"+file_name);
      // File file = new File("./*sample/TwoRoom/Controller.txt");

      if (checkBeforeReadfile(file)){
        BufferedReader br = new BufferedReader(new FileReader(file));

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
        //System.out.println(lts_data);
        long start = System.currentTimeMillis();
        Converter.convert(lts_data);
        long end = System.currentTimeMillis();
        System.out.println("実行時間："+ (end - start)  + "ms");

        br.close();
      }else{
        System.out.println("ファイルが見つからないか開けません");
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