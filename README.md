# Escape From Woods

Escape From Woods is a challenging puzzle game that tasks you with finding the quickest way out of a dense forest using a map.

## Objective

Given a map, determine the shortest path to exit the forest. Start from the position marked by the symbol “X” and navigate through the empty spaces (symbol “ ”) to find an exit.

## Game Rules

- Your starting position is indicated by the symbol “X”. Your initial step count is 0.
- You can only navigate through empty spaces represented by the symbol “ ”.
- If the map file is corrupted or there's no exit, return 0.

## Map Characteristics

- The map is of a rectangular shape.
- Axis size limits: 5 <= X <= 11,000, 5 <= Y <= 11,000
- The map comprises three symbols:
  - “ ”: Represents empty spaces where you can move.
  - “1”: Represents the forest or trees.
  - “X”: Your start position.
- There can be between 0 to 1,000 exits on a map.

## Getting Started

1. Clone this repository.
2. Place your map files in the resources folder.
3. Run the solution to find out the quickest escape path.

## Other Requirements

- Ensure the code is clean and adheres to the best coding practices.
- Write unit tests for the solution.
- Do not print anything to the console.
- Do not make changes to the `Game.java` file.

## Testing

Test maps are available in the resources folder. To achieve accurate results, it's recommended to test the solution against multiple map files.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

---

You can save the above content in a `README.md` file in the root directory of your GitHub repository. Adjust any sections or details as necessary to fit the exact requirements or details of your project.
