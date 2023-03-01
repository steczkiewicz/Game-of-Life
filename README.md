# Game of Life

Projekt Game of Life stworzony na potrzeby przedmiotu Programowanie Obiektowe (https://github.com/apohllo/obiektowe-lab).

Aplikacja używa okienka stworzonego za pomocą JavyFX do wczytania parametrów startowych symulacji. Następnie w konsoli wyświetlane są koordynaty oraz statystyki zwierząt i roślin z symulacji.

---

### Klasy

#### Animal
Klasa reprezentująca zwierzę na mapie. Może się ono poruszać, jeść oraz rozmnażać. Ma określoną energię oraz DNA, z którego dziedziczą jego potomkowie.

---
#### DarwinMap
Klasa reprezentująca mapę, na której znajdują się zwierzęta oraz rośliny.

---
#### DNA
Klasa reprezentująca geny zwierząt. 

---
#### Grass
Klasa reprezentująca rośliny, które mogą być jedzone przez zwierzęta.

---
#### IMapElement
Interfejs reprezentujący elementy na mapie. Korzystają z niego klasy Animal oraz Grass.

---
#### MapDirection
Typ wyliczeniowy służący do określania kierunku w którym zwrócone są zwierzęta.

---
#### MoveDirection
Typ wyliczeniowy służący kierunek w którym mogą poruszać się zwierzęta.

---
#### Simulation Engine
Klasa reprezentująca silnik symulacji.

---
#### Vector2d
Klasa reprezentująca wektor 2d. Korzystają z niego zwierzęta oraz rośliny.

---
#### World
Klasa startowa z metodą Application.launch. Służy do wystartowania aplikacji
