# Es9 - Javassist

Esercizio 9 svolto con Javassist.

Ci sono 3 cartelle:
- **custom_class_with_translator:** l'esercizio è stato svolto usando una classe custom che simula la classe di sistema StringBuilder. Viene usato il `Translator`
- **custom_class_without_translator:** come i precedente ma **non** viene usato il `Translator`
- **system_class_test:** Varie prove cercando di modificare la classe di sostema `StringBuilder`. Niente di particolarmente funzionante.

# Come eseguire il codice

Per il codice presente in *custom_class_with_translator* e *custom_class_without_translator* bastano questi comandi:

## Build
`javac -cp ".:javassist.jar" *.java`

## Esecuzione
`java -cp ".:javassist.jar" Main`

