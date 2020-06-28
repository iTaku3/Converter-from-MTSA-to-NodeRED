//ArrayList
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


class Converter
{
	public static void convert(List<String> lts_data)
	{
		//Controllable Actionsも外部から読み込み？
		// List<String> controllable_actions = new ArrayList<String>();
		// controllable_actions.addAll(Arrays.asList("resRoomStatus", "allow_A", "deny_A", "allow_Out", "lock_A", "unLock_A"));
		List<String> controllable_actions = Arrays.asList("resRoomStatus", "allow_A", "deny_A", "allow_Out", "lock_A", "unLock_A");
		List<String> uncontrollable_actions = new ArrayList<String>();
		System.out.println(controllable_actions);
		// List<List<String>> transitions_list = new ArrayList<>();
		
		//自身の使う環境に応じて変更
		String ifttt_key = "cPFBtMhxOsUhT968fhqXb9";

		try{
		  File file = new File("./*output/Node-RED_flow.json");
		  BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		  // bw.write("");
		  // bw.newLine();


		  bw.write("[");
		  bw.newLine();

		  //フローの設定
		  bw.write("{");
		  bw.newLine();
		  bw.write("\"id\": \"flow_name\","); //ZはこのIDを参照
		  bw.newLine();
		  bw.write("\"type\": \"tab\",");
		  bw.newLine();
		  bw.write("\"label\": \"Converted Controller\",");
		  bw.newLine();
		  bw.write("\"disabled\": false");
		  bw.newLine();
		  bw.write("},");
		  bw.newLine();

		  //request受口(url:/node-RED)
		  bw.write("{");
		  bw.newLine();
		  bw.write("\"id\": \"get_request\",");
		  bw.newLine();
		  bw.write("\"type\": \"http in\",");
		  bw.newLine();
		  bw.write("\"z\": \"flow_name\",");
		  bw.newLine();
		  bw.write("\"disabled\": false,");
		  bw.newLine();
		  bw.write("\"name\": \"get_request\",");
		  bw.newLine();
		  bw.write("\"url\": \"/Node-RED\",");
		  bw.newLine();
		  bw.write("\"method\": \"get\",");
		  bw.newLine();
		  bw.write("\"upload\": false,");
		  bw.newLine();
		  bw.write("\"x\": 200,");
		  bw.newLine();
		  bw.write("\"y\": 100,");
		  bw.newLine();
		  bw.write("\"wires\": [[\"extract_request\"]]");
		  bw.newLine();
		  bw.write("},");
		  bw.newLine();

		  bw.write("{");
		  bw.newLine();
		  bw.write("\"id\": \"extract_request\",");
		  bw.newLine();
		  bw.write("\"type\": \"function\",");
		  bw.newLine();
		  bw.write("\"z\": \"flow_name\",");
		  bw.newLine();
		  bw.write("\"name\": \"extract_request\",");
		  bw.newLine();
		  bw.write("\"func\": \"var req = msg.payload;\\n msg.payload = req['req'];\\nreturn msg;\",");
		  bw.newLine();
		  bw.write("\"noerr\": 0,");
		  bw.newLine();
		  bw.write("\"x\": 200,");
		  bw.newLine();
		  bw.write("\"y\": 150,");
		  bw.newLine();
		  bw.write("\"wires\": [[\"set_flow_action\"]]");
		  bw.newLine();
		  bw.write("},");
		  bw.newLine();

		  bw.write("{");
		  bw.newLine();
		  bw.write("\"id\": \"set_flow_action\",");
		  bw.newLine();
		  bw.write("\"type\": \"change\",");
		  bw.newLine();
		  bw.write("\"z\": \"flow_name\",");
		  bw.newLine();
		  bw.write("\"name\": \"set_flow_action\",");
		  bw.newLine();
		  bw.write("\"rules\": [{\"t\": \"set\",\"p\": \"action\",\"pt\": \"flow\",\"to\": \"payload\",\"tot\": \"msg\"}],");
		  bw.newLine();
		  bw.write("\"reg\": false,");
		  bw.newLine();
		  bw.write("\"x\": 200,");
		  bw.newLine();
		  bw.write("\"y\": 200,");
		  bw.newLine();
		  bw.write("\"wires\": [[\"response_request\"]]");
		  bw.newLine();
		  bw.write("},");
		  bw.newLine();

		  bw.write("{");
		  bw.newLine();
		  bw.write("\"id\": \"response_request\",");
		  bw.newLine();
		  bw.write("\"type\": \"http response\",");
		  bw.newLine();
		  bw.write("\"z\": \"flow_name\",");
		  bw.newLine();
		  bw.write("\"name\": \"response_request\",");
		  bw.newLine();
		  bw.write("\"x\": 200,");
		  bw.newLine();
		  bw.write("\"y\": 250,");
		  bw.newLine();
		  bw.write("\"wires\": []");
		  bw.newLine();
		  bw.write("},");
		  bw.newLine();




		  //initノード(type:inject)
		  bw.write("{");
		  bw.newLine();
		  bw.write("\"id\": \"init\",");
		  bw.newLine();
		  bw.write("\"type\": \"inject\",");
		  bw.newLine();
		  bw.write("\"z\": \"flow_name\",");
		  bw.newLine();
		  bw.write("\"name\": \"init\",");
		  bw.newLine();
		  bw.write("\"payload\": \"\",");
		  bw.newLine();
		  bw.write("\"payloadType\": \"date\",");
		  bw.newLine();
		  bw.write("\"repeat\": \"\",");
		  bw.newLine();
		  bw.write("\"crontab\": \"\",");
		  bw.newLine();
		  bw.write("\"once\": false,");
		  bw.newLine();
		  bw.write("\"onceDelay\": 0.1,");
		  bw.newLine();
		  bw.write("\"x\": 500,");
		  bw.newLine();
		  bw.write("\"y\": 100,");
		  bw.newLine();
		  bw.write("\"wires\": [[\"set_init_state\"]]");
		  bw.newLine();
		  bw.write("},");
		  bw.newLine();


		  //set init stateノード
		  bw.write("{");
		  bw.newLine();
		  bw.write("\"id\": \"set_init_state\",");
		  bw.newLine();
		  bw.write("\"type\": \"change\",");
		  bw.newLine();
		  bw.write("\"z\": \"flow_name\",");
		  bw.newLine();
		  bw.write("\"name\": \"set_init_state\",");
		  bw.newLine();
		  bw.write("\"rules\": [{\"t\": \"set\",\"p\": \"action\",\"pt\": \"flow\",\"to\": \"null\",\"tot\": \"str\"}],");
		  bw.newLine();
		  bw.write("\"reg\": false,");
		  bw.newLine();
		  bw.write("\"x\": 500,");
		  bw.newLine();
		  bw.write("\"y\": 150,");
		  bw.newLine();
		  bw.write("\"wires\": [[\"Q0\"]]");
		  bw.newLine();
		  bw.write("},");
		  bw.newLine();


		  //座標計算用の変数
		  int x_position = 500;
		  int y_position = 200;

		  int transition_count = 0;
		  for(String transition : lts_data)
		  {
			List<String> transition_list 
				= new ArrayList<String>(Arrays.asList(transition.split("[|=,()-/>\\s\\t]")));
			
			while(transition_list.contains(""))
			{
				transition_list.remove("");
			}
			//String[] transition_array = transition_list.toArray(new String[transition_list.size()]);
			System.out.print(transition_list);

			int controllableAction_count = 0;
			for(String controllable_action : controllable_actions)
			{
				if(transition_list.contains(controllable_action))
				{
					controllableAction_count++;
				}
			}

			//状態の判定
			if(controllableAction_count == 1)
			{
				System.out.println("  > Controllable State");

				//set stateノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(0) + "\",");
				bw.newLine();
				bw.write("\"type\": \"function\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"" + transition_list.get(0) + "\",");
				bw.newLine();
				bw.write("\"func\": \"flow.set(\\\"State\\\", \\\""+ transition_list.get(0) +"\\\");\\n msg.payload = flow.get(\\\"State\\\");\\n return msg;\",");
				bw.newLine();
				bw.write("\"noerr\": 0,");
				bw.newLine();
				bw.write("\"x\": "+ x_position +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": [[\"" + transition_list.get(1) + "_set\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();


				//set actionノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(1) + "_set\",");
				bw.newLine();
				bw.write("\"type\": \"function\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"" + transition_list.get(1) + "_set\",");
				bw.newLine();
				bw.write("\"func\": \"msg.payload = \\\""+ transition_list.get(1) +"\\\";\\nflow.set(\\\"action\\\", msg.payload);\\nreturn msg;\",");
				bw.newLine();
				bw.write("\"noerr\": 0,");
				bw.newLine();
				bw.write("\"x\": "+ (x_position+300) +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": [[\"" + transition_list.get(1) + "\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();


				//ifttt action triggerノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(1) + "\",");
				bw.newLine();
				bw.write("\"type\": \"http request\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"" + transition_list.get(1) + "\",");
				bw.newLine();
				bw.write("\"method\": \"POST\",");
				bw.newLine();
				bw.write("\"ret\": \"txt\",");
				bw.newLine();
				bw.write("\"paytoqs\": false,");
				bw.newLine();
				bw.write("\"url\": \"https://maker.ifttt.com/trigger/"+ transition_list.get(1) +"/with/key/"+ifttt_key+"\",");
				bw.newLine();
				bw.write("\"persist\": false,");
				bw.newLine();
				bw.write("\"x\": "+ (x_position+600) +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": [[\"" + transition_list.get(2) + "\"]]");
				bw.newLine();
				bw.write("}");
				y_position = y_position + 50;

			}
			else if (controllableAction_count == (controllable_actions.size()-1)/2)
			{
				System.out.println("  > Controllable State(ERROR)");
				break;
			}
			else if (controllableAction_count == 0)
			{
				System.out.println("  > Uncontrollable State");

				//set stateノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(0) + "\",");
				bw.newLine();
				bw.write("\"type\": \"function\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"" + transition_list.get(0) + "\",");
				bw.newLine();
				bw.write("\"func\": \"flow.set(\\\"State\\\", \\\""+ transition_list.get(0) +"\\\");\\n msg.payload = flow.get(\\\"State\\\");\\n return msg;\",");
				bw.newLine();
				bw.write("\"noerr\": 0,");
				bw.newLine();
				bw.write("\"x\": "+ x_position +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": [[\"" + transition_list.get(0) + "_check\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();
				y_position = y_position + 50;

				//request 受信ノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(0) + "_check\",");
				bw.newLine();
				bw.write("\"type\": \"switch\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"" + transition_list.get(0) + "_check\",");
				bw.newLine();
				bw.write("\"property\": \"action\",");
				bw.newLine();
				bw.write("\"propertyType\": \"flow\",");
				bw.newLine();
				bw.write("\"rules\": [");
				for(int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					bw.write("{\"t\": \"eq\",\"v\": \"" + transition_list.get(2*n-1) + "\",\"vt\": \"str\"},");
					if(!uncontrollable_actions.contains(transition_list.get(2*n-1)))
					{
						uncontrollable_actions.add(transition_list.get(2*n-1));
					}
				}
				bw.write("{\"t\": \"else\"}],");
				bw.newLine();
				bw.write("\"checkall\": \"true\",");
				bw.newLine();
				bw.write("\"repair\": false,");
				bw.newLine();
				bw.write("\"x\": "+ x_position +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": ["); //１個目が次のState, ２個目が前のState
				for(int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					bw.write("[\"" + transition_list.get(2*n) + "\"],");
				}
				bw.write("[\"" + transition_list.get(0) + "\"]]");
				bw.newLine();
				bw.write("}");
				y_position = y_position + 50;
			}
			else
			{
				System.out.println("  > Mixed State(ERROR)");
				break;
			}

			transition_count++;
			if (transition_count < lts_data.size())
			{
				bw.write(",");
			}
			bw.newLine();
		}

		  bw.write("]");
		  bw.newLine();

		  bw.close();

		  System.out.println(uncontrollable_actions);
		}catch(IOException e){
		  System.out.println(e);
		}
	}
}