# Jetpack Compose - NY-News 

# Clean Architecture App

An Android app consuming nytimes API to display popular news.
it has been built with clean architecture principles, Repository Pattern and MVI
pattern with unit testing.

## Architecture

The Application is split into a three layer architecture:
- Presentation with module by feature
- Domain
- Data

![Architecture Flow Diagram](art/arch_flow.png)


The 3 layered architectural approach is majorly guided by clean architecture which provides
a clear separation of concerns with its Abstraction Principle.

#### Presentation

```news-list```  ```details``` modules contains the UI files and handles binding of DI components from other modules.
Binding of data is facilitated by jetpacks data binding by serving data from the viewmodel
to the UI.The data being received is part of a viewstate class that has properties contained in the
relevant state.

#### Domain

The ```domain``` module contains domain model classes which represent the
data we will be handling across presentation and data layer.

Use cases are also provided in the domain layer and orchestrate the flow
of data from the data layer onto the presentation layer and a split into
modular pieces serving one particular purpose.

#### Data

the ```data``` layer Handles data interacting with the network 

## Tech Stack

*  Jetpack compose
*  MVI pattern
*  Hilt
*  Unit testing
*  Coil
*  Retrofit
*  Shared transition animation

## Screenshots

| <img src="art/news.jpg" width=200/> | <img src="art/details.jpg" width=200/> |
|:-----------------------------------:|:--------------------------------------:|