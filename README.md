# WordCount

## Packaging de l'application
1. Afin de packager l'application, lancer la commande suivante :
```
sbt package
```

2. Envoyer le fichier .jar sur un hôte du cluster Cloudera (et non le manager)
```
scp /chemin/vers/le/fichier/source host@172.31.249.250:~/dossier/de/destination
```
Par exemple :
```
scp ./wordcount-by-cheikna-faizaan_2.11-0.1.jar host@172.31.249.250:~/jar-examples
```

## Lancement de l'application
1. Changer d'utilisateur afin d'être conecté avec l'utilisateur hdfs
```
sudo -u hdfs -s
```

2. Exécuter le .jar en lançant la commande suivante (en mode cluster)
```
spark-submit --master yarn --deploy-mode cluster --class WordCountMain ./wordcount-by-cheikna-faizaan_2.11-0.1.jar "<fichier_sur_lequel_il_faut_lire_le_nombre_doccurrence_des_mots>"
```
Par exemple (pour un fichier se trouvant sur HDFS) :
```
spark-submit --master yarn --deploy-mode cluster --class WordCountMain ./wordcount-by-cheikna-faizaan_2.11-0.1.jar "hdfs:///faizaan_cheikna/texts/les-miserables-chapitre-1.txt"
```