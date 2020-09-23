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
		
		/*ArtGarelly*/
		List<String> controllable_actions = Arrays.asList("allow_A[1]", "deny_A", "allow_B[1]", "deny_B", "allow_Out", "deny_Out");
		// List<String> controllable_actions = Arrays.asList("resRoomStatus", "allow_A", "deny_A", "allow_Out", "lock_A", "unLock_A");
		//List<String> controllable_actions = Arrays.asList("resRoomStatus", "lock_Hall", "lock_A", "lock_B", "lock_C", "lock_D", "unLock_Hall", "unLock_A", "unLock_B", "unLock_C", "unLock_D",
		//												  "allow_Hall", "allow_A", "allow_B", "allow_C", "allow_D", "allow_Out", "deny_Hall", "deny_A", "deny_B", "deny_C", "deny_D", "deny_Out");

		/*RailCab*/
		//List<String> controllable_actions = Arrays.asList("requestEnter", "brake", "emergencyBrake", "idle_c", "checkCrossingStatus");

		List<String> uncontrollable_actions = new ArrayList<String>();
		//System.out.println(controllable_actions);
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




		  //座標計算用の変数
		  int x_position = 700;
		  int y_position = 100;


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
		  bw.write("\"x\": "+x_position+",");
		  bw.newLine();
		  bw.write("\"y\": "+y_position+",");
		  bw.newLine();
		  bw.write("\"wires\": [[\"set_init_state\"]]");
		  bw.newLine();
		  bw.write("},");
		  bw.newLine();
		  y_position = y_position + 50;


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
		  bw.write("\"x\": "+x_position+",");
		  bw.newLine();
		  bw.write("\"y\": "+y_position+",");
		  bw.newLine();
		  bw.write("\"wires\": [[\"Q0\"]]");
		  bw.newLine();
		  bw.write("},");
		  bw.newLine();
		  y_position = y_position + 50;


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
			if(controllableAction_count == 1 && controllableAction_count == (transition_list.size()-1)/2)
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
				bw.write("\"wires\": [[\""+transition_list.get(0)+"_"+transition_list.get(1)+"_set\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();


				//set actionノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \""+transition_list.get(0)+"_"+transition_list.get(1)+"_set\",");
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
				bw.write("\"x\": "+ (x_position+200) +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": [[\""+transition_list.get(0)+"_"+transition_list.get(1)+"\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();


				//ifttt action triggerノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \""+transition_list.get(0)+"_"+transition_list.get(1)+"\",");
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
				bw.write("\"x\": "+ (x_position+400) +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": [[\"" + transition_list.get(2) + "\"]]");
				bw.newLine();
				bw.write("}");
				y_position = y_position + 50;

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
				y_position = y_position + 75;

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
				bw.write("\"x\": "+ (x_position) +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": ["); //１個目が次のState, ２個目が前のState
				for(int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					bw.write("[\"" + transition_list.get(2*n) + "\"],");
				}
				bw.write("[\"" + transition_list.get(0) + "_delay\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();

				//delay ノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(0) + "_delay\",");
				bw.newLine();
				bw.write("\"type\": \"delay\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"delay 100ms\",");
				bw.newLine();
				bw.write("\"pauseType\": \"delay\",");
				bw.newLine();
				bw.write("\"timeout\": \"100\",");
				bw.newLine();
				bw.write("\"timeoutUnits\": \"milliseconds\",");
				bw.newLine();
				bw.write("\"rate\": \"1\",");
				bw.newLine();
				bw.write("\"nbRateUnits\": \"1\",");
				bw.newLine();
				bw.write("\"rateUnits\": \"second\",");
				bw.newLine();
				bw.write("\"randomFirst\": \"1\",");
				bw.newLine();
				bw.write("\"randomLast\": \"5\",");
				bw.newLine();
				bw.write("\"randomUnits\": \"seconds\",");
				bw.newLine();
				bw.write("\"drop\": false,");
				bw.newLine();
				bw.write("\"x\": "+ (x_position-200) +",");
				bw.newLine();
				bw.write("\"y\": "+ (y_position-75) +",");
				bw.newLine();
				bw.write("\"wires\": [[\"" + transition_list.get(0) + "\"]]");
				bw.newLine();
				bw.write("}");
				y_position = y_position + 75;
			}
			else if (controllableAction_count == (transition_list.size()-1)/2)
			{
				System.out.println("  > Controllable State(ERROR)");

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
				bw.write("\"wires\": [[\"" + transition_list.get(0) + "_set\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();
				
				//action setノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(0) + "_set\",");
				bw.newLine();
				bw.write("\"type\": \"function\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"Modifiable Area\",");
				bw.newLine();
				bw.write("\"func\": \"var action = new Array(");
				for(int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					bw.write("\\\"" + transition_list.get(2*n-1) + "\\\"");
					if(n < ((transition_list.size()-1)/2)) bw.write(", ");
				}
				bw.write(");\\n var rnd = Math.floor( Math.random() * "+ ((transition_list.size()-1)/2) +");\\n flow.set(\\\"action\\\", action[rnd]);\\n return msg;\",");
				bw.newLine();
				bw.write("\"noerr\": 0,");
				bw.newLine();
				bw.write("\"x\": "+ (x_position+200) +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": [[\"" + transition_list.get(0) + "_check\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();

				//switch(変数判定)ノード
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
					bw.write("{\"t\": \"eq\",\"v\": \"" + transition_list.get(2*n-1) + "\",\"vt\": \"str\"}");
					if(n < ((transition_list.size()-1)/2)) bw.write(", ");
				}
				bw.write("],");
				bw.newLine();
				bw.write("\"checkall\": \"true\",");
				bw.newLine();
				bw.write("\"repair\": false,");
				bw.newLine();
				bw.write("\"x\": "+ (x_position+400) +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": ["); //１個目が次のState, ２個目が前のState
				for(int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					bw.write("[\""+ transition_list.get(0) +"_"+ transition_list.get(2*n-1) + "_set\"]");
					if(n < ((transition_list.size()-1)/2)) bw.write(", ");
				}
				bw.write("]");
				bw.newLine();
				bw.write("},");


				//アクションノード
				for (int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					//アクションセットノード
					bw.write("{");
					bw.newLine();
					bw.write("\"id\": \""+transition_list.get(0)+"_"+transition_list.get(2*n-1)+"_set\",");
					bw.newLine();
					bw.write("\"type\": \"function\",");
					bw.newLine();
					bw.write("\"z\": \"flow_name\",");
					bw.newLine();
					bw.write("\"name\": \"" + transition_list.get(2*n-1) + "_set\",");
					bw.newLine();
					bw.write("\"func\": \"msg.payload = \\\""+ transition_list.get(2*n-1) +"\\\";\\nflow.set(\\\"action\\\", msg.payload);\\nreturn msg;\",");
					bw.newLine();
					bw.write("\"noerr\": 0,");
					bw.newLine();
					bw.write("\"x\": "+ (x_position+700) +",");
					bw.newLine();
					bw.write("\"y\": "+ y_position +",");
					bw.newLine();
					bw.write("\"wires\": [[\""+transition_list.get(0)+"_"+transition_list.get(2*n-1)+"\"]]");
					bw.newLine();
					bw.write("},");
					bw.newLine();

					//iftttトリガーノード
					bw.write("{");
					bw.newLine();
					bw.write("\"id\": \""+transition_list.get(0)+"_"+transition_list.get(2*n-1)+"\",");
					bw.newLine();
					bw.write("\"type\": \"http request\",");
					bw.newLine();
					bw.write("\"z\": \"flow_name\",");
					bw.newLine();
					bw.write("\"name\": \"" + transition_list.get(2*n-1) + "\",");
					bw.newLine();
					bw.write("\"method\": \"POST\",");
					bw.newLine();
					bw.write("\"ret\": \"txt\",");
					bw.newLine();
					bw.write("\"paytoqs\": false,");
					bw.newLine();
					bw.write("\"url\": \"https://maker.ifttt.com/trigger/"+ transition_list.get(2*n-1) +"/with/key/"+ifttt_key+"\",");
					bw.newLine();
					bw.write("\"persist\": false,");
					bw.newLine();
					bw.write("\"x\": "+ (x_position+1000) +",");
					bw.newLine();
					bw.write("\"y\": "+ y_position +",");
					bw.newLine();
					bw.write("\"wires\": [[\"" + transition_list.get(2*n) + "\"]]");
					bw.newLine();
					if(n == ((transition_list.size()-1)/2))
					{
						bw.write("}");
						y_position = y_position + 50;
					}
					else
					{
						bw.write("},");
						y_position = y_position + 40;
					}
				}

			}
			else
			{
				System.out.println("  > Mixed State(ERROR)");

				for(int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					if(!controllable_actions.contains(transition_list.get(2*n-1)))
					{
						if(!uncontrollable_actions.contains(transition_list.get(2*n-1)))
						{
							uncontrollable_actions.add(transition_list.get(2*n-1));
						}
					}
				}

				int ua_count = 0; //Uncontrollable Actionsの数
				int ca_count = 0; //Controllable Actionsの数

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
				bw.write("\"wires\": [[\"" + transition_list.get(0) + "_check_request\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();
				y_position = y_position + 75;

				//request 受信ノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(0) + "_check_request\",");
				bw.newLine();
				bw.write("\"type\": \"switch\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"" + transition_list.get(0) + ": check request\",");
				bw.newLine();
				bw.write("\"property\": \"action\",");
				bw.newLine();
				bw.write("\"propertyType\": \"flow\",");
				bw.newLine();
				bw.write("\"rules\": [");
				for(int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					if(uncontrollable_actions.contains(transition_list.get(2*n-1)))
					{
						bw.write("{\"t\": \"eq\",\"v\": \"" + transition_list.get(2*n-1) + "\",\"vt\": \"str\"},");
						ua_count = ua_count+1;
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
					if(uncontrollable_actions.contains(transition_list.get(2*n-1)))
					{
						bw.write("[\"" + transition_list.get(2*n) + "\"],");
					}
				}
				bw.write("[\"" + transition_list.get(0) + "_wait\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();
				y_position = y_position + 75;

				//request waitノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(0) + "_wait\",");
				bw.newLine();
				bw.write("\"type\": \"function\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"wait request (10s)\",");
				bw.newLine();
				bw.write("\"func\": \"var d = new Date();\\n var ms = d.getTime();\\n var time = flow.get(\\\"Time\\\");\\n if(ms-time>10000)//10秒\\n {\\n    flow.set(\\\"boolean\\\", true);\\n }\\n else\\n {\\n    flow.set(\\\"boolean\\\", false);\\n }\\n return msg;\",");
				bw.newLine();
				bw.write("\"noerr\": 0,");
				bw.newLine();
				bw.write("\"x\": "+ x_position +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": [[\"" + transition_list.get(0) + "_check_time\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();

				//wait time確認ノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(0) + "_check_time\",");
				bw.newLine();
				bw.write("\"type\": \"switch\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"check passing time\",");
				bw.newLine();
				bw.write("\"property\": \"action\",");
				bw.newLine();
				bw.write("\"propertyType\": \"flow\",");
				bw.newLine();
				bw.write("\"rules\": [{\"t\": \"true\"},{\"t\":\"false\"}],");
				bw.newLine();
				bw.write("\"checkall\": \"true\",");
				bw.newLine();
				bw.write("\"repair\": false,");
				bw.newLine();
				bw.write("\"x\": "+ x_position +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": [[\""+ transition_list.get(0) +"_set\"],[\""+ transition_list.get(0) +"\"]]"); 
				bw.newLine();
				bw.write("},");
				bw.newLine();

				//controllable action setノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(0) + "_set\",");
				bw.newLine();
				bw.write("\"type\": \"function\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"Modifiable Area\",");
				bw.newLine();
				bw.write("\"func\": \"var action = new Array(");
				for(int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					if(controllable_actions.contains(transition_list.get(2*n-1)))
					{
						bw.write("\\\"" + transition_list.get(2*n-1) + "\\\"");
						ca_count = ca_count + 1;
						if(ca_count < (transition_list.size()-1)/2 - ua_count)
						{
							bw.write(", ");	
						}
					}
				}
				bw.write(");\\n var rnd = Math.floor( Math.random() * "+ ca_count +");\\n flow.set(\\\"action\\\", action[rnd]);\\n return msg;\",");
				bw.newLine();
				bw.write("\"noerr\": 0,");
				bw.newLine();
				bw.write("\"x\": "+ (x_position+200) +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": [[\"" + transition_list.get(0) + "_check_operation\"]]");
				bw.newLine();
				bw.write("},");
				bw.newLine();
				ca_count = 0;

				//controllable action決定ノード
				bw.write("{");
				bw.newLine();
				bw.write("\"id\": \"" + transition_list.get(0) + "_check_operation\",");
				bw.newLine();
				bw.write("\"type\": \"switch\",");
				bw.newLine();
				bw.write("\"z\": \"flow_name\",");
				bw.newLine();
				bw.write("\"name\": \"" + transition_list.get(0) + ": check operation\",");
				bw.newLine();
				bw.write("\"property\": \"action\",");
				bw.newLine();
				bw.write("\"propertyType\": \"flow\",");
				bw.newLine();
				bw.write("\"rules\": [");
				for(int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					if(controllable_actions.contains(transition_list.get(2*n-1)))
					{
						bw.write("{\"t\": \"eq\",\"v\": \"" + transition_list.get(2*n-1) + "\",\"vt\": \"str\"}");
						ca_count = ca_count + 1;
						if(ca_count < (transition_list.size()-1)/2 - ua_count)
						{
							bw.write(", ");	
						}
					}
				}
				ca_count = 0;
				bw.write("],");
				bw.newLine();
				bw.write("\"checkall\": \"true\",");
				bw.newLine();
				bw.write("\"repair\": false,");
				bw.newLine();
				bw.write("\"x\": "+ (x_position+400) +",");
				bw.newLine();
				bw.write("\"y\": "+ y_position +",");
				bw.newLine();
				bw.write("\"wires\": ["); //１個目が次のState, ２個目が前のState
				for(int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					if(controllable_actions.contains(transition_list.get(2*n-1)))
					{
						bw.write("[\""+ transition_list.get(0) +"_"+ transition_list.get(2*n-1) + "_set\"]");
						ca_count = ca_count + 1;
						if(ca_count < (transition_list.size()-1)/2 - ua_count)
						{
							bw.write(", ");	
						}
					}
				}
				ca_count = 0;
				bw.write("]");
				bw.newLine();
				bw.write("},");
				bw.newLine();

				//controllable action
				for (int n = 1 ; n <= (transition_list.size()-1)/2 ; n++)
				{
					if (controllable_actions.contains(transition_list.get(2*n-1)))
					{
						ca_count = ca_count + 1;
						//アクションセットノード
						bw.write("{");
						bw.newLine();
						bw.write("\"id\": \""+transition_list.get(0)+"_"+transition_list.get(2*n-1)+"_set\",");
						bw.newLine();
						bw.write("\"type\": \"function\",");
						bw.newLine();
						bw.write("\"z\": \"flow_name\",");
						bw.newLine();
						bw.write("\"name\": \"" + transition_list.get(2*n-1) + "_set\",");
						bw.newLine();
						bw.write("\"func\": \"msg.payload = \\\""+ transition_list.get(2*n-1) +"\\\";\\nflow.set(\\\"action\\\", msg.payload);\\nreturn msg;\",");
						bw.newLine();
						bw.write("\"noerr\": 0,");
						bw.newLine();
						bw.write("\"x\": "+ (x_position+700) +",");
						bw.newLine();
						bw.write("\"y\": "+ y_position +",");
						bw.newLine();
						bw.write("\"wires\": [[\""+transition_list.get(0)+"_"+transition_list.get(2*n-1)+"\"]]");
						bw.newLine();
						bw.write("},");
						bw.newLine();

						//iftttトリガーノード
						bw.write("{");
						bw.newLine();
						bw.write("\"id\": \""+transition_list.get(0)+"_"+transition_list.get(2*n-1)+"\",");
						bw.newLine();
						bw.write("\"type\": \"http request\",");
						bw.newLine();
						bw.write("\"z\": \"flow_name\",");
						bw.newLine();
						bw.write("\"name\": \"" + transition_list.get(2*n-1) + "\",");
						bw.newLine();
						bw.write("\"method\": \"POST\",");
						bw.newLine();
						bw.write("\"ret\": \"txt\",");
						bw.newLine();
						bw.write("\"paytoqs\": false,");
						bw.newLine();
						bw.write("\"url\": \"https://maker.ifttt.com/trigger/"+ transition_list.get(2*n-1) +"/with/key/"+ifttt_key+"\",");
						bw.newLine();
						bw.write("\"persist\": false,");
						bw.newLine();
						bw.write("\"x\": "+ (x_position+1000) +",");
						bw.newLine();
						bw.write("\"y\": "+ y_position +",");
						bw.newLine();
						bw.write("\"wires\": [[\"" + transition_list.get(2*n) + "\"]]");
						bw.newLine();

						if(ca_count < (transition_list.size()-1)/2 - ua_count)
						{
							bw.write("},");
							bw.newLine();
							y_position = y_position + 40;
						}
						else
						{
							bw.write("}");
							y_position = y_position + 50;
						}
					}
				}
				ca_count = 0;
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

		  System.out.println("-------------------------------");
		  System.out.println(controllable_actions);
		  System.out.println(uncontrollable_actions);
		}catch(IOException e){
		  System.out.println(e);
		}
	}
}