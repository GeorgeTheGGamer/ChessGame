# ♛ Chess Game

A fully functional chess game implemented in Java with a graphical user interface. This project demonstrates object-oriented programming principles, game logic implementation, and GUI development using Java Swing.

## 🎯 Features

### Core Gameplay
- ✅ **Complete Chess Implementation** - All standard chess rules and piece movements
- ✅ **Two-Player Mode** - Local multiplayer gameplay
- ✅ **Interactive GUI** - Point-and-click piece movement
- ✅ **Turn-Based System** - Alternating moves between white and black players
- ✅ **Move Validation** - Prevents illegal moves and enforces chess rules

### Chess Rules & Mechanics
- ♟️ **Standard Piece Movement** - Pawn, Rook, Knight, Bishop, Queen, King
- 🏰 **Special Moves** - Castling, En Passant, Pawn Promotion
- ⚠️ **Check Detection** - Automatic detection of check situations
- 🏁 **Checkmate & Stalemate** - Game-ending condition recognition
- 🔄 **Game Reset** - Start new games without restarting application

### User Interface
- 🎨 **Clean Visual Design** - Intuitive chessboard layout
- 🖱️ **Mouse Controls** - Click to select and move pieces
- 📊 **Game Status Display** - Current player turn and game state
- 🎯 **Move Highlighting** - Visual feedback for valid moves
- 📱 **Responsive Layout** - Proper window sizing and component arrangement

## 🛠️ Technology Stack

### Core Technologies
- **Java SE** - Core programming language
- **Java Swing** - GUI framework for the user interface
- **Java AWT** - Additional graphics and event handling
- **Object-Oriented Design** - Modular, maintainable code structure

### Development Environment
- **IDE**: Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)
- **JDK Version**: Java 8 or higher
- **Build System**: Standard Java compilation
- **Version Control**: Git

## 🚀 Getting Started

### Prerequisites
```bash
# Ensure Java is installed
java -version
javac -version
```

### Installation & Setup
1. **Clone the Repository**
   ```bash
   git clone https://github.com/GeorgeTheGGamer/ChessGame.git
   cd ChessGame
   ```

2. **Compile the Project**
   ```bash
   # Navigate to source directory
   cd src
   
   # Compile all Java files
   javac *.java
   
   # Or compile main class specifically
   javac Main.java
   ```

3. **Run the Game**
   ```bash
   # Execute the main class
   java Main
   
   # Or if using package structure
   java -cp . com.chess.Main
   ```

### Alternative IDE Setup
1. Import project into your preferred Java IDE
2. Ensure project SDK is set to Java 8+
3. Run the Main.java file
4. The chess game window should open automatically

## 🎮 How to Play

### Basic Controls
1. **Start Game** - Run the application to begin
2. **Select Piece** - Click on any of your pieces (white moves first)
3. **View Valid Moves** - Valid destinations will be highlighted
4. **Make Move** - Click on a highlighted square to move the piece
5. **Game Flow** - Turns alternate automatically between players

### Game Rules
- **Objective**: Checkmate your opponent's king
- **Piece Movement**: Each piece type has unique movement patterns
- **Special Rules**: 
  - Castling available when conditions are met
  - En passant capture for pawns
  - Pawn promotion when reaching the opposite end
- **Check**: Must move out of check when threatened
- **Checkmate**: Game ends when king cannot escape capture

### Controls Summary
| Action | Method |
|--------|--------|
| Select Piece | Left-click on your piece |
| Move Piece | Left-click on valid destination |
| New Game | Click reset button (if available) |
| Exit | Close window or use menu option |

## 🏗️ Project Structure

```
ChessGame/
├── src/
│   ├── Main.java              # Application entry point
│   ├── ChessGame.java         # Main game controller
│   ├── ChessBoard.java        # Board representation and logic
│   ├── GamePanel.java         # GUI panel for the chess board
│   ├── pieces/
│   │   ├── Piece.java         # Abstract piece class
│   │   ├── Pawn.java          # Pawn piece implementation
│   │   ├── Rook.java          # Rook piece implementation
│   │   ├── Knight.java        # Knight piece implementation
│   │   ├── Bishop.java        # Bishop piece implementation
│   │   ├── Queen.java         # Queen piece implementation
│   │   └── King.java          # King piece implementation
│   └── utils/
│       ├── GameState.java     # Game state management
│       └── MoveValidator.java # Move validation logic
├── resources/
│   └── images/                # Chess piece images (if used)
├── README.md
└── LICENSE
```

## 🔧 Architecture & Design

### Object-Oriented Principles
- **Inheritance** - Abstract Piece class with concrete piece implementations
- **Polymorphism** - Different piece types with unified interface
- **Encapsulation** - Private fields with public methods for controlled access
- **Abstraction** - Simplified interfaces hiding complex implementation details

### Design Patterns Used
- **Model-View-Controller (MVC)** - Separation of game logic and UI
- **Strategy Pattern** - Different movement strategies for each piece type
- **Observer Pattern** - UI updates based on game state changes
- **Factory Pattern** - Piece creation and initialization

### Key Classes
- **ChessGame** - Main game controller and state management
- **ChessBoard** - 8x8 board representation and piece positioning
- **Piece** - Abstract base class for all chess pieces
- **GamePanel** - Swing component for rendering the chess board
- **MoveValidator** - Logic for validating legal moves

## 🧪 Testing

### Manual Testing Checklist
- [ ] All pieces move according to chess rules
- [ ] Check detection works correctly
- [ ] Checkmate properly ends the game
- [ ] Special moves (castling, en passant) function properly
- [ ] UI responds correctly to user input
- [ ] Game state updates appropriately

### Automated Testing (Future Enhancement)
```java
// Example test structure
@Test
public void testPawnMovement() {
    // Test pawn forward movement
    // Test pawn capture diagonally
    // Test en passant capture
}

@Test
public void testCheckDetection() {
    // Set up board position with check
    // Verify check is detected
    // Verify only legal moves are allowed
}
```

## 🚧 Future Enhancements

### Planned Features
- [ ] **AI Opponent** - Single-player mode with computer opponent
- [ ] **Move History** - Record and display move notation
- [ ] **Game Save/Load** - Persist game state to file
- [ ] **Timer/Clock** - Add time controls for competitive play
- [ ] **Sound Effects** - Audio feedback for moves and captures
- [ ] **Themes** - Multiple board and piece visual themes

### Technical Improvements
- [ ] **Unit Testing** - Comprehensive test suite
- [ ] **Code Documentation** - Javadoc comments for all classes
- [ ] **Performance Optimization** - Faster move generation and validation
- [ ] **Network Play** - Multiplayer over network
- [ ] **Chess Notation** - PGN (Portable Game Notation) support

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork the Repository**
2. **Create Feature Branch** (`git checkout -b feature/new-feature`)
3. **Commit Changes** (`git commit -m 'Add new feature'`)
4. **Push to Branch** (`git push origin feature/new-feature`)
5. **Open Pull Request**

### Development Guidelines
- Follow Java naming conventions
- Add comments for complex logic
- Test new features thoroughly
- Maintain consistent code formatting
- Update documentation as needed

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**GeorgeTheGGamer**
- GitHub: [@GeorgeTheGGamer](https://github.com/GeorgeTheGGamer)
- Project: [ChessGame](https://github.com/GeorgeTheGGamer/ChessGame)

## 🙏 Acknowledgments

- Chess piece movement algorithms based on standard chess rules
- Java Swing documentation and tutorials
- Object-oriented programming best practices
- Chess programming community resources

---

*This project was created as a learning exercise in Java programming, object-oriented design, and GUI development. Perfect for understanding game logic implementation and user interface design patterns.*
