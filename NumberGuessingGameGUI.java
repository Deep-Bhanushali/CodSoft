import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class NumberGuessingGameGUI {
    private static int numberToGuess;
    private static int attempts;
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Number Guessing Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setResizable(false);

            // Main Panel
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(new Color(220, 230, 240)); // Light grayish-blue background
            frame.add(mainPanel);

            // Header Section
            JLabel titleLabel = new JLabel("Number Guessing Game");
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setFont(new Font("Verdana", Font.BOLD, 26));
            titleLabel.setForeground(new Color(33, 37, 41)); // Dark gray
            mainPanel.add(titleLabel, BorderLayout.NORTH);

            // Center Panel for Game
            JPanel gamePanel = new JPanel();
            gamePanel.setLayout(new GridLayout(5, 1, 10, 10));
            gamePanel.setBackground(new Color(220, 230, 240));
            mainPanel.add(gamePanel, BorderLayout.CENTER);

            JLabel instructionLabel = new JLabel("Guess the number (1-100):");
            instructionLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
            instructionLabel.setForeground(new Color(33, 37, 41)); // Dark gray
            instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gamePanel.add(instructionLabel);

            JTextField guessInput = new JTextField();
            guessInput.setFont(new Font("Verdana", Font.PLAIN, 16));
            guessInput.setHorizontalAlignment(SwingConstants.CENTER);
            guessInput.setPreferredSize(new Dimension(120, 20));
            gamePanel.add(guessInput);

            JLabel feedbackLabel = new JLabel("");
            feedbackLabel.setFont(new Font("Verdana", Font.BOLD, 16));
            feedbackLabel.setForeground(new Color(0, 128, 0)); // Dark green for correct feedback
            feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gamePanel.add(feedbackLabel);

            JLabel attemptsLabel = new JLabel("Attempts remaining: " + MAX_ATTEMPTS);
            attemptsLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
            attemptsLabel.setForeground(new Color(128, 0, 0)); // Maroon for attempts label
            attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gamePanel.add(attemptsLabel);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(220, 230, 240));
            gamePanel.add(buttonPanel);

            JButton guessButton = new JButton("Guess");
            JButton newGameButton = new JButton("New Game");

            guessButton.setFont(new Font("Verdana", Font.BOLD, 16));
            newGameButton.setFont(new Font("Verdana", Font.BOLD, 16));

            guessButton.setBackground(new Color(173, 216, 230)); // Muted light blue
            newGameButton.setBackground(new Color(144, 238, 144)); // Soft light green

            guessButton.setForeground(Color.BLACK);
            newGameButton.setForeground(Color.BLACK);

            buttonPanel.add(guessButton);
            buttonPanel.add(newGameButton);

            // Start a new game
            startNewGame();

            // Guess Button Action
            guessButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int guess = Integer.parseInt(guessInput.getText());
                        if (guess < 1 || guess > 100) {
                            feedbackLabel.setText("Enter a number between 1 and 100.");
                            feedbackLabel.setForeground(Color.RED);
                            return;
                        }
                        attempts++;
                        if (guess < numberToGuess) {
                            feedbackLabel.setText("Too low! Try again.");
                            feedbackLabel.setForeground(new Color(255, 140, 0)); // Soft orange
                        } else if (guess > numberToGuess) {
                            feedbackLabel.setText("Too high! Try again.");
                            feedbackLabel.setForeground(new Color(255, 140, 0)); // Soft orange
                        } else {
                            feedbackLabel.setText("Correct! You guessed it in " + attempts + " attempts.");
                            feedbackLabel.setForeground(new Color(34, 139, 34)); // Forest green
                            guessButton.setEnabled(false);
                        }

                        if (attempts == MAX_ATTEMPTS && guess != numberToGuess) {
                            feedbackLabel.setText("Out of attempts! The number was " + numberToGuess + ".");
                            feedbackLabel.setForeground(Color.RED);
                            guessButton.setEnabled(false);
                        }
                        attemptsLabel.setText("Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                    } catch (NumberFormatException ex) {
                        feedbackLabel.setText("Invalid input! Please enter a number.");
                        feedbackLabel.setForeground(Color.RED);
                    }
                }
            });

            // New Game Button Action
            newGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startNewGame();
                    feedbackLabel.setText("");
                    guessInput.setText("");
                    attemptsLabel.setText("Attempts remaining: " + MAX_ATTEMPTS);
                    guessButton.setEnabled(true);
                }
            });

            // Display Frame
            frame.setVisible(true);
        });
    }

    private static void startNewGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
    }
}
