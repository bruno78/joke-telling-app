# Telling Joke App

<p align="center"><img src="https://cdn.rawgit.com/bruno78/joke-telling-app/9bb56dc8/assets/telling-joke-demo.gif" width="200" alt="demo of telling joke app" /></p>

## Project Overview (Gradle for Android and Java Final Project)

In this project, you will create an app with multiple flavors that uses multiple libraries and Google Cloud Endpoints. 
The finished app will consist of four modules:

1. A Java library that provides jokes
2. A Google Cloud Endpoints (GCE) project that serves those jokes
3. An Android Library containing an activity for displaying jokes
4. An Android app that fetches jokes from the GCE module and passes them to the Android Library for display

When done the project will have this scheme:

<p align="center"><img src="https://cdn.rawgit.com/bruno78/joke-telling-app/9bb56dc8/assets/application-scheme.png" width="600" alt="application scheme"></p>

## Why this Project

As Android projects grow in complexity, it becomes necessary to customize the
behavior of the Gradle build tool, allowing automation of repetitive tasks.
Particularly, factoring functionality into libraries and creating product
flavors allow for much bigger projects with minimal added complexity.

## What Will I Learn?

You will learn the role of Gradle in building Android Apps and how to use
Gradle to manage apps of increasing complexity. You'll learn to:

* Add free and paid flavors to an app, and set up your build to share code between them
* Factor reusable functionality into a Java library
* Factor reusable Android functionality into an Android library
* Configure a multi project build to compile your libraries and app
* Use the Gradle App Engine plugin to deploy a backend
* Configure an integration test suite that runs against the local App Engine development server

# Rubric

### Required Components

- [x] Project contains a Java library for supplying jokes
- [x] Project contains an Android library with an activity that displays jokes passed to it as intent extras.
- [x] Project contains a Google Cloud Endpoints module that supplies jokes from the Java library. Project loads jokes from GCE module via an async task.
- [x] Project contains connected tests to verify that the async task is indeed loading jokes.
- [x] Project contains paid/free flavors. The paid flavor has no ads, and no unnecessary dependencies.

### Required Behavior

- [x] App retrieves jokes from Google Cloud Endpoints module and displays them via an Activity from the Android Library.

### Optional Components

Once you have a functioning project, consider adding more features to test your Gradle and Android skills. Here are a few suggestions:

- [x] Make the free app variant display interstitial ads between the main activity and the joke-displaying activity.
- [x] Have the app display a loading indicator while the joke is being fetched from the server.
- [ ] Write a Gradle task that starts the GCE dev server, runs all the Android tests, and shuts down the dev server.
