# Graphs Search #


### How it works ###

* The program receives a data file (txt) and a commands file (txt)
* It converts data file to a graph with vertexes and edges
* Executes each command line on command file

### Available commands ###

* **distance**

e.g.
```
distance A-B-C
```

* **trips**¹

e.g.
```
trips C-C <=3
```

* **shortest**

e.g.
```
shortest A-C
```

* **routes**¹

e.g.
```
routes C-C <30
```

¹ - It uses operation signals.

### Available Operation Signals ###

* LESS_THAN ( **<** )
 
e.g.
```
routes C-C <30
```

* LESS_EQUAL_THAN ( **<=** )
 
e.g.
```
routes C-C <=30
```

* EQUAL_THAN ( **=** )
 
e.g.
```
routes C-C =32
```

* GREATER_EQUAL_THAN ( **>** )
 
e.g.
```
routes C-C >50
```

* GREATER_EQUAL_THAN ( **>=** )
 
e.g.
```
routes C-C >=50
```

```
java -jar target/GraphsSearch.jar data.txt commands.txt
```