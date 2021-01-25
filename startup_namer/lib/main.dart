import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primaryColor: Colors.pink,
      ),
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
  void _pushSaved() {
    Navigator.of(context).push(MaterialPageRoute<void>(
        // NEW lines from here...
        builder: (BuildContext context) {
      final tiles = _saved.map(
        (WordPair pair) {
          return ListTile(
            title: Text(
              pair.asPascalCase,
              style: _biggerFont,
            ),
          );
        },
      );
      final divided = ListTile.divideTiles(
        context: context,
        tiles: tiles,
      ).toList();

      return Scaffold(
        appBar: AppBar(
          title: Text('Saved Suggestions'),
        ),
        body: ListView(children: divided),
      );
    }));
  }

  Widget _buildSuggestions() {
    return ListView.builder(
        //constructor de ListView
        padding: EdgeInsets.all(16.0), //padding
        itemBuilder: /*1*/ (context, i) {
          //propiedad itembuilder, se le tienen que pasar 2 parámetros: el contexto y el iterador
          if (i.isOdd)
            return Divider(); /*2*/ //si el iterador es impar coloca una línea divisoria entre pares de palabras

          final index = i ~/ 2; /*3*/ //Guarda
          if (index >= _suggestions.length) {
            _suggestions.addAll(generateWordPairs().take(10)); /*4*/
          }
          return _buildRow(_suggestions[index]);
        });
  }

  Widget _buildRow(WordPair pair) {
    //metodo encargado de crear la lista, se le pasa por parámetro una pareja de palabras
    final alreadySaved = _saved.contains(pair);
    return ListTile(
      title: Text(
        pair.asPascalCase, //coloca la pareja en UpperCamelCase
        style: _biggerFont, //aumenta el tamaño de la fuente
      ),
      trailing: Icon(
        alreadySaved ? Icons.favorite : Icons.favorite_border,
        color: alreadySaved ? Colors.red : null,
      ),
      onTap: () {
        setState(() {
          if (alreadySaved) {
            _saved.remove(pair);
          } else {
            _saved.add(pair);
          }
        });
      },
    );
  }

  final _suggestions = <WordPair>[]; //array de pares de palabras
  final _saved = Set<
      WordPair>(); // Guarda pares de palabras en un set, se usa set y no List porque evitas entradas duplicadas
  final _biggerFont = TextStyle(
      fontSize:
          18.0); //En flutter todo va por widgets, este se encarga de aumentar la fuente
  @override
  Widget build(BuildContext context) {
    //un widget que se encarga de crear la lista de palabras y renderizarlas
    return Scaffold(
      //scaffold es un objeto de material que renderiza una vista por defecto
      appBar: AppBar(
        //el scaffold tiene una appbar que es una barra superior donde hay un texto
        title: Text(
            'Startup Name Generator'), //appbar tiene una propiedad hijo que es title que se encarga de colocar con el atributo texto, un título.
        actions: [
          IconButton(icon: Icon(Icons.list), onPressed: _pushSaved),
        ],
      ),
      body: _buildSuggestions(), //llamada al método encargado de crear la lista
    );
  }
}
