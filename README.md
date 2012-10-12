Xebia mobile backend
==========

Description 
----------

TODO

Roadmap
----------
TODO

Developper sur Xebia mobile backend
----------
### Prérequis
Le strict minimum est d'avoir une jvm, sbt 0.12.1, un clone du projet et un éditeur de texte.
Quelque soit votre mode de développement préféré il faut commencer par lancer `sbt` depuis le dossier server du workspace et mettre à jour les dépendances :

    $ sbt
    [info] Loading global plugins from D:\programs\Java\sbt\plugins
    [info] Loading project definition from D:\devel\perso\XBackend\project
    [info] Set current project to XBackend (in build file:/D:/devel/perso/XBackend/)
    sbt (XBackend)> update
    sbt (XBackend)> update-classifiers 
La seconde commande est optionnelle mais permet de télécharger les sources et les javadocs (pour les artéfacts qui les ont publiés). 

*Attention : sbt peut être capricieux et nous utilisons Play 2.1-SNAPSHOT qui mixe scala 2.9.2 et scala 2.10.0 en cas de problèmes commencez par supprimer ~/.sbt , ~/.ivy2 , target, project/target, project/project*

### Lancer la console play
Ca ne sert a rien mais ça affiche le logo :) Depuis la racine du projet lancez `sbt play` :

    $ sbt play
    [info] Loading project definition from /Users/jean/dev/sdev/src/work/xbackend/project
    [info] Set current project to XBackend (in build file:/Users/jean/dev/sdev/src/work/xbackend/)
           _            _
     _ __ | | __ _ _  _| |
    | '_ \| |/ _' | || |_|
    |  __/|_|\____|\__ (_)
    |_|            |__/

    play! 2.1-SNAPSHOT (using Scala 2.10.0-M7), http://www.playframework.org

    > Type "help play" or "license" for more information.
    > Type "exit" or use Ctrl+D to leave this console.

    [XBackend] $    

### Configurer un IDE
Play supporte en natif la configuration des deux plux gros IDE java : [Eclipse (ScalaIDE)](http://scala-ide.org/download/current.html), [IntelliJ (Community suffit)](http://www.jetbrains.com/idea/free_java_ide.html). Le détail des instructions pour eclipse est là http://www.playframework.org/documentation/2.0/IDE , en court il s'agit de lancer la console play et d'utiliser `eclipsify with-source=true` 
    
    [XBackend] $ eclipse with-source=true
    [info] About to create Eclipse project files for your project(s).
    [info] Successfully created Eclipse project files for project(s):                      
    [info] XBackend
    [XBackend] $    

Pour IntelliJ, c'est tout aussi simple avec `idea with-sources` : 

    [XBackend] $ idea with-sources=yes
    [info] Trying to create an Idea module XBackend
    [info] downloading half the universe ...
    ...
    [info] Excluding folder target
    [info] Created /Users/jean/dev/sdev/src/work/xbackend/.idea/IdeaProject.iml
    [info] Created /Users/jean/dev/sdev/src/work/xbackend/.idea
    [info] Excluding folder /Users/jean/dev/sdev/src/work/xbackend/target/scala-2.10/cache
    [info] Excluding folder /Users/jean/dev/sdev/src/work/xbackend/target/scala-2.10/classes
    [info] Excluding folder /Users/jean/dev/sdev/src/work/xbackend/target/scala-2.10/classes_managed
    [info] Excluding folder /Users/jean/dev/sdev/src/work/xbackend/target/native_libraries
    [info] Excluding folder /Users/jean/dev/sdev/src/work/xbackend/target/resolution-cache
    [info] Excluding folder /Users/jean/dev/sdev/src/work/xbackend/target/scala-2.10/resource_managed
    [info] Excluding folder /Users/jean/dev/sdev/src/work/xbackend/target/streams
    [info] Created /Users/jean/dev/sdev/src/work/xbackend/.idea_modules/XBackend.iml
    [info] Created /Users/jean/dev/sdev/src/work/xbackend/.idea_modules/XBackend-build.iml
    [XBackend] $    

### Les commandes sbt utiles : 

- `update` met à jour les dépendances
- `compile` compile l'application
- `test` joue les tests unitaires
- `~ [command]` joue [command] en continu (compile et test sont de bonnes idées)
- `run` lance l'application