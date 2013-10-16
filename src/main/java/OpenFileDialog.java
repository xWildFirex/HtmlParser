
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class OpenFileDialog extends JPanel
                            implements ActionListener {
    static private final String newLine = "\n";
    private JButton openButton;
    private JTextArea logTextArea;
    private JFileChooser fileChoser;
    private String fileName;
    private List<DataItem> data;

    public OpenFileDialog() {
        super(new BorderLayout());

        logTextArea = new JTextArea(5, 20);
        logTextArea.setMargin(new Insets(5, 5, 5, 5));
        logTextArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logTextArea);

        fileChoser = new JFileChooser();
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);

        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void actionPerformed(ActionEvent e){

        //Handle open button action
        if (e.getSource() == openButton) {


            if (fileChoser.showOpenDialog(OpenFileDialog.this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChoser.getSelectedFile();
                //this is where a real application would open the file
                logTextArea.append("opening: " + file.getName() + "." + newLine);
                try {
                    data = TableParser.parseFile(file.getPath());
                    new WriteToFile(data).write(new File(file.getPath()+ ".xls"));
                    logTextArea.append("File processed." + file.getPath() + ".xls" + newLine);
                } catch (IOException e1) {
                    logTextArea.append("Exception in parseFile method" + newLine);
                }

            } else {
                logTextArea.append("Open command cancelled by user. " + newLine);
            }
            logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
        }
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
