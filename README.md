# Node-RED-MTSA
## [使い方]
①MTSAで生成したControllerのTransitionsを.txtとして出力/保存  
②ツール内の./inputフォルダのなかに①のファイルを保存  
③ツールを起動
```
/Node-RED-MTSA user$ javac Main.java
/Node-RED-MTSA user$ java Main
```
④ツールの指示通り，ファイル名を入力する  
```
--notification-------------------------------------------------
"./*input"フォルダ内にある，
MTSAで合成したControllerのファイル名(拡張子も含む)を入力してください．
---------------------------------------------------------------
file address : ./input/{ここにファイル名を入力}
```
⑤変換完了．./outputフォルダ内の「Node-RED_flow.json」が自動変換されたファイルです．  
  
[Uncontrollable Actionsのrequsetの送信先]  
curl http://localhost:1880/Node-RED?req={アクション名}  

## [注意]
今回の場合，Controllable ActionはIFTTT経由で発火することを想定しています．  
https://maker.ifttt.com/trigger/{アクション名}/with/key/{IFTTTアカウント固有キー}  
ですべて発火することが想定されています．  
  
複数のControllable Actionが発火可能なStateや，Mixed StateはERRORとして処理されます．  
この2つの状況を判定する部分は作っているので．  
この際の挙動を追加する時などはそこに新たにメソッドを追加すればいいと思います．  
  
Controllable ActionのAction Setは今ハードコーディングしています.（Converter.java:20行目）  
他のモデルで試す際は，ここを書き換えてください．  
(Controllable Action Setの入力の仕様が明確に決まり次第，変更します．)