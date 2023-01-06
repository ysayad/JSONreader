JC = javac -implicit:none -d build -classpath build -sourcepath "src"
SRC = src/fr/iutfbleau/projetJson/
BUILD = build/fr/iutfbleau/projetJson/

.PHONY: compile
compile: 
	$(JC) $(SRC)Partie1/sayad/*.java

.PHONY : jar
jar: compile
	jar cvfe Main.jar fr.iutfbleau.projetJson.Partie1.sayad.Inspecteur -C build .

.PHONY : clean
clean:
	rm -rf $(BUILD)/* *.jar

.PHONY : run
run:
	java -jar Main.jar res/ex1.json

.PHONY : all
.DEFAULT_GOAL := all
all: compile jar
	java -jar Main.jar file://./res/ex1.json
