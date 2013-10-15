
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: korbut-ve
 * Date: 30.09.13
 * Time: 9:46
 * To change this template use File | Settings | File Templates.
 */
public class OpenFileDialog extends JPanel
                            implements ActionListener {
    static private final String newLine = "\n";
    JButton openButton;
    JTextArea log;
    JFileChooser fc;
    private String fileName;
    TableParser tableParser;

    public OpenFileDialog() {
        super(new BorderLayout());

        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //create a file chooser
        fc = new JFileChooser();
        tableParser = new TableParser();
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
            int returnVal = fc.showOpenDialog(OpenFileDialog.this);

            if (fc.showOpenDialog(OpenFileDialog.this) == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //this is where a real application would open the file
                logBoxAppend("opening: ", file.getName(), "." );
                try {
                    tableParser.tableParser(file.getPath());
                    log.append("File processed." + file.getPath() + ".xls" + newLine);
                } catch (IOException e1) {
                    log.append("Exception in tableParser method" + newLine);
                }

            } else {
                log.append("Open command cancelled by user. " + newLine);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    public void logBoxAppend(Object ... items){
        StringBuilder builder = new StringBuilder();
        for( Object item : items ) builder.append(item.toString());
        builder.append("\n");
        log.append(builder.toString());
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
        //To change body of created methods use File | Settings | File Templates.
        JFrame frame = new JFrame("OpenFileDialog");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // add content
        frame.add(new OpenFileDialog());

        frame.pack();
        frame.setVisible(true);
    }

}
