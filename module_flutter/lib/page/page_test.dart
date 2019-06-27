import 'package:flutter/material.dart';

class TestPage extends StatefulWidget {
  @override
  _State createState() => _State();
}

class _State extends State<TestPage> {
  @override
  Widget build(BuildContext context) {
    print('初始化');
    return Scaffold(
      appBar: AppBar(
        title: Text('Test'),
      ),
      body: Center(
        child: FlatButton(onPressed: (){

        }, child: Text('跳转Native')),
      ),
    );
  }
}
