# Scrabble (SAE)

## Développement

### Console

```bash
# Pour démarrer la classe principale (`scrabble.application.ScrabbleConsoleApplication`)
mvn exec:java

# Pour exécuter les tests (situés @ `./src/test/java/scrabble/test/*)
mvn test
```

Si vous souhaitez exécuter une autre classe principale, il faut changer `mainClass` du plugin `exec-maven-plugin` dans le `pom.xml`.

### JavaFX

```bash
# Pour démarrer la classe principale (`scrabble.application.ScrabbleFXApplication`)
mvn javafx:run
```

Lors de l'utilisation d'un IDE tel qu'InteliJ IDEA, il est possible de démarrer l'application en exécutant la classe principale
et en ajoutant les paramètres de VM suivant :

```bash
--module-path "/chemin/absolu/vers/javafx-21/lib"
--add-modules javafx.controls,javafx.fxml
--add-opens javafx.graphics/javafx.stage=ALL-UNNAMED
--add-exports javafx.graphics/com.sun.javafx.tk.quantum=ALL-UNNAMED
```
