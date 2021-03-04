# Converter from MTSA to Node-RED
## [使い方]
①MTSAで生成したControllerのTransitionsを.txtとして出力/保存  
②ツール内の./inputフォルダのなかに①のファイルを保存 
③Controllable Actionのノード情報ファイル(.txt)を作成（記述方式は下の方に記載しています）
④ツール内の./inputフォルダのなかに②のファイルを保存 
⑤ツールを起動
```
/Node-RED-MTSA user$ javac Main.java
/Node-RED-MTSA user$ java Main
```
④ツールの指示通り，ファイル名を入力する 
※ <FILE NAME>には①と②のファイル名を入力します（ファイル拡張子を含む）  
```
= notification ================================================
Enter the address of the controller file & connexion file. 
[!] You need to write a file extension (eg ".txt").
 -------------------------------------------------------------
 > controller file : The name of the controller output file synthesized by MTSA.
 > connexion file  : The name of the file that describes the Controllable action and node information.
===============================================================

controller file address : ./*input/<FILE NAME>
connexion file address  : ./*input/<FILE NAME>
```
⑤変換完了．./outputフォルダ内の「Node-RED_flow.json」が自動変換されたファイルです．  
  
### Uncontrollable Actionsのrequsetの送信先] 
curl http://localhost:1880/Node-RED?req={アクション名}  

## [入力ファイルの形式]
### controller file
MTSAで合成したContorllerのtransitionsを保存したファイル．  
デフォルトではController.txtで出力される．

### connexion file
MTSAで合成したControllerにおけるactionが，Node-RED上でどのような役割を持つか記述したもの．  
記述形式は次のとおりである．

```
>>> <action名>
<json fileのノード情報>
```
例として次のようなものが挙げられる．
```
>>> allow_A[1],
"type": "http request",
"name": "allow_A[1]",
"method": "POST",
"ret": "txt",
"paytoqs": false,
"url": "https://maker.ifttt.com/trigger/allow_A[1]/with/key/cPFBtMhxOsUhT968fhqXb9",
"persist": false,
```
これをactionの数だけ記述する．
細かい規約は次のとおり．
```
・action名を記述する行「>>> <action名>」と「<json fileのノード情報>」を記述する行は必ず別々の行にする．
・<json fileのノード情報>には次の情報を含めてはならない．逆に次の情報以外は記述する必要がある．
　- id
　- z
　- x
　- y
　- wires
```

## [生成されたNode-REDプログラムにおける開発]
この変換器によって生成されたNode-REDプログラムは，設計者によってシステムの仕様変更が可能部分が存在する．
この変換器によって生成されたNode-REDプログラムにおいて，変更可能部分は次のとおりである．
```
・
・
・
```

## [対象システム]
本変換器はこのような目的で作成された．  
そのため，この変換器を用いた設計手法で設計できるNode-REDシステムは次のような要件を満たしていなければならない．
```
・
・
・
```

## [変換アルゴリズム]
本変換器は次のようなアルゴリズムによって，LTSの状態遷移モデルをNode-REDのフローモデルに変換している．



## [注意]
<!-- 今回の場合，Controllable ActionはIFTTT経由で発火することを想定しています．   -->
<!-- https://maker.ifttt.com/trigger/{アクション名}/with/key/{IFTTTアカウント固有キー}   -->
<!-- ですべて発火することが想定されています．   -->
  
現状，特になし