
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class OpenFileDialog extends JPanel
                            implements ActionListener {

    private JButton openButton;
    private JTextArea logTextArea;
    private JFileChooser fileChooser;

    public OpenFileDialog() {
        super(new BorderLayout());

        logTextArea = new JTextArea(5, 20);
        logTextArea.setMargin(new Insets(5, 5, 5, 5));
        logTextArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logTextArea);

        fileChooser = new JFileChooser();
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);

        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        List<DataItem> data;
        if (e.getSource() == openButton) {

            if (fileChooser.showOpenDialog(OpenFileDialog.this) == JFileChooser.APPROVE_OPTION) {
                File inpuFile = fileChooser.getSelectedFile();
                logBoxAppend("opening: ", inpuFile.getName());
                try {
                    data = TableParser.parseFile(inpuFile.getPath());
                    new WriteToFile(data).write(new File(inpuFile.getPath()+ ".xls"));
                    logBoxAppend("File processed.", inpuFile.getPath(), ".xls");
                } catch (IOException e1) {
                    logBoxAppend("Exception in parseFile method");
                }
            } else {
                logBoxAppend("Open command cancelled by user.");
            }
            logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
        }
    }

    public void logBoxAppend(Object ... items){
            StringBuilder builder = new StringBuilder();
            for( Object item : items ) builder.append(item.toString());
            builder.append("\n");
           logTextArea.append(builder.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {

        JFrame frame = new JFrame("OpenFileDialog");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new OpenFileDialog());
        frame.pack();
        frame.setVisible(true);
    }
}
