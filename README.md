# 🧩 Puzzle Game

## Overview

**PuzzleGame** is a simple Java-based puzzle game where players can rearrange pieces of an image to form a complete picture. The project showcases the use of Java Swing for building the user interface and basic game logic. This project is designed as an educational tool to demonstrate fundamental Java programming concepts, including event handling, GUI development, and file packaging.

<div align="center">
    <img width="607" alt="PuzzleGame" src="https://github.com/user-attachments/assets/2e81b5e0-e76f-42a0-ba27-5e978c021b20" alt="puzzle game" width="600">
</div>

## Features

- 🔄 **Puzzle Gameplay**: Rearrange the pieces to complete the image.
- 🎨 **Multiple Themes**: Choose from different image categories.
- 🎲 **Dynamic Image Selection**: Randomly selects images from the chosen category each time the game starts.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK) 14+**: Ensure JDK 14 or higher is installed on your machine.

### Running the Game

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/chihtengma/PuzzleGame.git
   cd PuzzleGame

2. **Compile the Project**:
   ```bash
   javac -d out src/*.java

3. **Run the Game**:
   ```bash
   java -jar out/artifacts/PuzzleGame_jar/PuzzleGame.jar

## Package the Game

- To create a platform-specific package:
  ```bash
  # For macOS DMG
  jpackage --input out/artifacts/PuzzelGame_jar/ --name PuzzleGame --main-jar PuzzleGame.jar --type dmg
  # For Windows EXE
  jpackage --input out/artifacts/PuzzelGame_jar/ --name PuzzleGame --main-jar PuzzleGame.jar --type exe
