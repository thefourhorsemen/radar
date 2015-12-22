# Radar

Simple clojure application to draw a radar (idea from [gigaquid](http://gigasquidsoftware.com/blog/2014/12/17/gigasquids-radar-2014)).

## Installation

Download standalone jar from available [releases](https://github.com/thefourhorsemen/radar/releases).

## Usage

    $ java -jar radar-1.0.0.jar [filename.ext]
where `filename.ext` is the name of the configuration file. Both JSON (`.json`) and text (`.txt`) formats are supported.

Note that an image, named `filename.png`, is automatically created in the current directory.

## Example

Hereafter is the gigasquid radar 2014 drawn with radar.

<img src="doc/sample.png" alt="Radar" align="center" size="0.4"/>

Here the content of the JSON configuration file leading to the sample radar above:
```json
[
  {
    "name": "Robots",
    "categories": [
      "Ar Drone",
      "Phamtom X Hexapod",
      "Myo Armband",
      "Roomba"
    ]
  },
  {
    "name": "Languages",
    "categories": [
      "Clojure",
      "Pixie",
      "Idris",
      "JavaScript"
    ]
  },
  {
    "name": "Cute Animals",
    "categories": [
      "Llamas",
      "Alpagas",
      "Wombats",
      "Hedgehogs"
    ]
  },
  {
    "name": "Tasty Food",
    "categories": [
      "Crumpets",
      "Mint Tim Tams",
      "Raclette",
      "Egg Nog"
    ]
  }
]
```

Here the content of the text configuration leading to the sample radar above:
```clojure
Robots: Ar Drone, Phamtom X Hexapod, Myo Armband, Roomba
Languages: Clojure, Pixie, Idris, JavaScript
Cute Animals: Llamas, Alpagas, Wombats, Hedgehogs
Tasty Food: Crumpets, Mint Tim Tams, Raclette, Egg Nog
```

## Depencencies
Radar uses:

* [clj-json](https://github.com/mmcgrana/clj-json), a clojure JSON library,
* [quil](https://github.com/quil/quil), a powerfull graphical clojure framework.

## License

Copyright © 2015

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
