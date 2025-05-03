# Scientific Calculator

A modern, responsive scientific calculator built with Java Swing that supports both standard and scientific calculation modes.

## Features

- **Dual Mode Operation**
  - Standard calculator mode for basic arithmetic
  - Scientific calculator mode with advanced mathematical functions

- **Mathematical Operations**
  - Basic: Addition, subtraction, multiplication, division
  - Advanced: Trigonometric functions (sin, cos, tan), logarithms (log, ln), power, square root
  - Support for parentheses for complex expressions

- **User Interface**
  - Clean, modern interface with responsive design
  - Toggle between calculator modes through a slide-out menu
  - Color-coded buttons for better usability
  - Visual feedback on button hover

- **Error Handling**
  - Prevents invalid operator sequences
  - Alerts for mathematical errors (e.g., division by zero)
  - Provides clear error messages

## Installation

### Prerequisites
- Java Development Kit (JDK) 16 or newer
- Git (optional, for cloning the repository)

### Steps to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/soham-droid-pixel/scientific-calculator.git
   cd scientific-calculator
   ```

2. **Compile the Java file**
   ```bash
   javac -d bin -cp . swing/Prac1.java
   ```

3. **Run the application**
   ```bash
   java -cp bin swing.Prac1
   ```

## Usage Guide

### Basic Operation
- Enter numbers using the number pad
- Perform operations by clicking on operator buttons
- Press "=" to calculate the result
- Use "C" to clear the display
- Use "⌫" to delete the last entered character

### Scientific Functions
- Switch to Scientific mode using the hamburger menu
- Available scientific functions:
  - `sin`: Calculate sine (input in degrees)
  - `cos`: Calculate cosine (input in degrees)
  - `tan`: Calculate tangent (input in degrees)
  - `log`: Calculate base-10 logarithm
  - `ln`: Calculate natural logarithm
  - `^`: Calculate power
  - `√`: Calculate square root
  - `π`: Insert the value of pi

## Technical Implementation

The calculator uses:
- Java Swing for the UI components
- Custom color schemes for better visual hierarchy
- Stack-based algorithm for expression evaluation
- Infix to postfix conversion for mathematical operations
- Event-driven architecture for button interactions

## Contributing

Contributions are welcome! Here's how you can contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Potential Improvements

Future enhancements could include:
- Memory functions (M+, M-, MR, MC)
- History of calculations
- More scientific functions (factorial, absolute value, etc.)
- Customizable themes
- Keyboard input support

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Inspired by modern calculator applications
- Built as a demonstration of Java Swing capabilities for GUI applications
