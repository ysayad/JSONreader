JC = javac -implicit:none -d build -classpath build -sourcepath "src"
SRC = src/fr/iutfbleau/projetJson/
BUILD = build/fr/iutfbleau/projetJson/

.PHONY: compile
compile : 
	$(JC) $(SRC) Partie


