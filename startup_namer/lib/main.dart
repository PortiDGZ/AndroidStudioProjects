// Copyright 2018 The Flutter team. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';
void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Center(
          child: RandomWords(),
        ),
      ),
    );
  }
}


class RandomWords extends StatefulWidget {
  @override
  _RandomWordsState createState() => _RandomWordsState();
}

class _RandomWordsState extends State<RandomWords> {
  Widget _buildSuggestions() {
    return ListView.builder( //constructor de ListView
        padding: EdgeInsets.all(16.0), //padding
        itemBuilder: /*1*/ (context, i) { //propiedad itembuilder, se le tienen que pasar 2 parámetros: el contexto y el iterador
          if (i.isOdd) return Divider(); /*2*/ //si el iterador es impar coloca una línea divisoria entre pares de palabras

          final index = i ~/ 2; /*3*/ //Guarda
          if (index >= _suggestions.length) {
            _suggestions.addAll(generateWordPairs().take(10)); /*4*/
          }
          return _buildRow(_suggestions[index]);
        });
  }
  Widget _buildRow(WordPair pair) { //metodo encargado de crear la lista, se le pasa por parámetro una pareja de palabras
    final alreadySaved = _saved.contains(pair);
    return ListTile(
      title: Text(
        pair.asPascalCase, //coloca la pareja en UpperCamelCase
        style: _biggerFont, //aumenta el tamaño de la fuente
      ),
      trailing: Icon(   // NEW from here...
        alreadySaved ? Icons.favorite : Icons.favorite_border,
        color: alreadySaved ? Colors.red : null,
      ),                // ... to here.
    );
  }
  final _suggestions = <WordPair>[];  //array de pares de palabras
  final _saved = Set<WordPair>();     // NEW Guarda pares de palabras en un set, se usa set y no List porque evitas entradas duplicadas
  final _biggerFont = TextStyle(fontSize: 18.0); //En flutter todo va por widgets, este se encarga de aumentar la fuente
  @override
  Widget build(BuildContext context) { //un widget que se encarga de crear la lista de palabras y renderizarlas
    return Scaffold( //scaffold es un objeto de material que renderiza una vista por defecto
      appBar: AppBar( //el scaffold tiene una appbar que es una barra superior donde hay un texto
        title: Text('Startup Name Generator'), //appbar tiene una propiedad hijo que es title que se encarga de colocar con el atributo texto, un título.
      ),
      body: _buildSuggestions(), //llamada al método encargado de crear la lista
    );
}
}

