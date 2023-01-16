JC = javac -encoding utf8 -implicit:none -d build/ -classpath build/ -sourcepath src
SRC = src/fr/iutfbleau/projetJson
BUILD = build/fr/iutfbleau/projetJson

.PHONY: compile
compile: 
	$(JC) $(SRC)/ainspecteur/Modele/*.java
	$(JC) $(SRC)/ainspecteur/Vue/display/*.java
	$(JC) $(SRC)/ainspecteur/Vue/*.java


.PHONY : jar
jar: compile
	jar cvfe Inspecteur.jar src.fr.iutfbleau.projetJson.ainspecteur.Vue.App -C build . -C res .

.PHONY : clean
clean:
	rm -rf $(BUILD)/* *.jar

.PHONY : run
run:
	java -jar Inspecteur.jar ex1.json

.PHONY : console
console: compile jar
	java -jar Inspecteur.jar 'file:res/ex1.json'

.PHONY : all
.DEFAULT_GOAL := all
all: compile jar
	java -jar Inspecteur.jar