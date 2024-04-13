# Scrabble (SAE)

## Développement

```bash
# Pour démarrer la classe principale (`scrabble.application.ScrabbleConsoleApplication`)
mvn exec:java

# Pour exécuter les tests (situés @ `./src/test/java/scrabble/test/*)
mvn test
```

Si vous souhaitez exécuter une autre classe principale, il faut changer `mainClass` du plugin `exec-maven-plugin` dans le `pom.xml`.
