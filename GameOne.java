import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameOne extends JFrame implements KeyListener {
    private int[][] matrix;
    private int rows = 7;
    private int cols = 7;
    private int currentRow = 5; // Starting in middle row
    private int currentCol = 3; // Starting in middle column
    private JTextArea displayArea;
    
    public GameOne() {
        // Set up the matrix with 0s and 1s
        matrix = new int[rows][cols];
        matrix[currentRow][currentCol] = 1;
        
        // Set up the window
        setTitle("Live Movable Matrix");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create text area with monospaced font for even spacing
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        add(displayArea);
        
        // Set up key listener
        addKeyListener(this);
        setFocusable(true);
        
        // Update display initially
        updateDisplay();
        
        // Show the window
        setVisible(true);
    }
    
    private void moveLeft() {
        if (currentCol > 0) {
            matrix[currentRow][currentCol] = 0;
            currentCol--;
            matrix[currentRow][currentCol] = 1;
            updateDisplay();
        }
    }
    
    private void moveRight() {
        if (currentCol < cols - 1) {
            matrix[currentRow][currentCol] = 0;
            currentCol++;
            matrix[currentRow][currentCol] = 1;
            updateDisplay();
        }
    }
    
    private void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("Press 'a' for left, 'd' for right\n\n");
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        displayArea.setText(sb.toString());
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        char key = Character.toLowerCase(e.getKeyChar());
        
        if (key == 'a') {
            moveLeft();
        } else if (key == 'd') {
            moveRight();
        } else if (key == 'q') {
            System.exit(0); // Quit the app
        }
    }
    
    // Required by KeyListener interface but not used
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    public static void main(String[] args) {
        // Run the popup
        SwingUtilities.invokeLater(() -> new GameOne());
    }
}