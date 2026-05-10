# Notes App

Application Android simple de gestion de notes avec persistance locale.


# demo

https://github.com/user-attachments/assets/149fa31a-fd1a-4d56-8a01-19b2a159fdc7


Fonctionnalites

- Ajouter une note (titre + description)
- Supprimer une note (clic long)
- Supprimer toutes les notes
- Persistance des donnees (meme apres fermeture de l'application)
- Rotation d'ecran sans perte de donnees

Architecture MVVM

L'application suit l'architecture MVVM :

- View : MainActivity (interface utilisateur)
- ViewModel : NoteViewModel (logique de presentation)
- Repository : NoteRepository (acces aux donnees)
- Room : Base de donnees SQLite locale

Technologies utilisees

- Java
- Room Database (SQLite)
- ViewModel et LiveData
- RecyclerView
- CardView

Prerequis

- Android 7.0 (API 24) ou superieur
- Android Studio Hedgehog (2023.1.1) ou plus

Installation

1. Ouvrez le projet dans Android Studio
2. Synchronisez les dependances Gradle
3. Lancez l'application sur un emulateur ou un appareil physique

Structure du projet

app/src/main/java/com/example/notesapp/
├── data/
│   ├── Note.java
│   ├── NoteDao.java
│   ├── NoteDatabase.java
│   └── NoteRepository.java
├── viewmodel/
│   └── NoteViewModel.java
├── MainActivity.java
└── NoteAdapter.java

app/src/main/res/layout/
├── activity_main.xml
└── note_item.xml

Comment utiliser

1. Ajouter une note : Saisissez un titre et une description, puis cliquez sur "AJOUTER"
2. Supprimer une note : Faites un clic long sur la note
3. Supprimer toutes les notes : Cliquez sur "SUPPRIMER TOUT"
4. Afficher les details : Clic simple sur une note pour voir le titre

Configuration requise

Minimum SDK : API 24 (Android 7.0)
Target SDK : API 34 (Android 14)

Dependances principales

- Room: 2.6.1
- Lifecycle: 2.7.0
- RecyclerView: 1.3.2
- CardView: 1.0.0
- Material Design: 1.11.0

Problemes courants

L'application plante au demarrage : Verifiez que minSdk = 24 et faites Clean Project puis Rebuild

Les notes disparaissent apres rotation : Verifiez que vous utilisez bien ViewModel

La suppression ne fonctionne pas : Verifiez l'implementation du clic long dans l'adaptateur

Licence

Ce projet est libre d'utilisation pour l'apprentissage.
