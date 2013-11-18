import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            if (fileChooser.showOpenDialog(OpenFileDialog.this) == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                logBoxAppend("opening: ", inputFile.getName());
                try {
                    readAndParseFile(inputFile);
                } catch (IOException e1) {
                    logBoxAppend("Exception in parseFile method");
                }
            } else {
                logBoxAppend("Open command cancelled by user.");
            }
            logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
        }
    }

    private void readAndParseFile(File inputFile) throws IOException {
        java.util.List<DataItem> data = TableParser.parseFile(inputFile.getPath());
        new WriteToFile(data).write(new File(inputFile.getPath() + ".xls"));
        logBoxAppend("File processed.", inputFile.getPath(), ".xls");
    }

    public void logBoxAppend(Object... items) {
        StringBuilder builder = new StringBuilder();
        for (Object item : items) builder.append(item.toString());
        builder.append("\n");
        logTextArea.append(builder.toString());
    }


}

