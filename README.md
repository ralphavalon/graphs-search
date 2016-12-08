# Graphs Search #


### How to config ###

* On Eclipse: File -> Import -> Existing Maven Projects

### How to build ###

* With Maven on path, go on prompt/terminal and execute:

```
mvn package
```

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

### How it is designed ###

#### Commands ####

* Available commands are in _com.graphs.enums.Commands_
* Each new command should implement _com.graphs.commands.Command_ interface and should be added to _com.graphs.enums.Commands_
* _com.graphs.service.CommandService_ is the responsible interface for calling commands.

#### Graphs ####

* Graphs have a Set of edges and a Set of vertexes.
* _com.graphs.service.GraphService_ is the responsible interface for getting the graph from data file.
* Graphs can return an edge by source and destination vertexes and can return vertex by its id.

#### Operations ####

* _com.graphs.enums.Operation_ is responsible for the available operations.
* It has an _evaluate_ method that is overriden with the expected operation. 

#### Path Finder ####

* _com.graphs.service.PathFinder_ is the responsible class for finding available paths.
* It receives a graph and uses Breadth-First-Search method to map neighbors.
* Has the _findPaths_ method that search available paths between two vertexes and it uses recursion.

### How to execute ###

* Once generated, run:

```
java -jar target/GraphsSearch.jar data.txt commands.txt
```