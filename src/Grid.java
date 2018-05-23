import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
 
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
 
public class Grid extends JFrame{
	int fileToOpen;
        JFrame frame;
	int fileToSave;
	JFileChooser fileOpen;
	JFileChooser fileSave;
	Grid(){
            
                setIcon();
                this.setLocation(450, 200);
                frame = new JFrame();
		frame.setTitle("Notepad");
		
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        if (JOptionPane.showConfirmDialog(frame, "Are you sure to close notepad?", "Really Closing?",  JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            FirstMainFrame f=new FirstMainFrame();
            frame.setVisible(false);
            f.setVisible(true);
                    
        }
    }
});
                
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		MenuBar menuBar = new MenuBar();
		MenuItem menutem = new MenuItem();
		final JTextArea textArea = new JTextArea();
		frame.setMenuBar(menuBar);
		Menu file = new Menu("File");
		menuBar.add(file);
		MenuItem newOption = new MenuItem("New");
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");
		MenuItem close = new MenuItem("Exit");
		file.add(newOption);
		file.add(open);
		file.add(save);
		file.add(close);
                frame.setVisible(true);
		frame.getContentPane().add(textArea);
 
		newOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.setText("");
			}
		});
 
		open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				openFile();
				if (fileToOpen == JFileChooser.APPROVE_OPTION){
					textArea.setText("");
					try{
						Scanner scan = new Scanner(new FileReader(fileOpen.getSelectedFile().getPath()));
						while (scan.hasNext())
							textArea.append(scan.nextLine()+"\n");
					} catch (Exception ex){
						System.out.println(ex.getMessage());
					}
				}
			}
		});
 
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveFile();
				if (fileToSave == JFileChooser.APPROVE_OPTION){
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(fileSave.getSelectedFile().getPath()));
						String g=textArea.getText();
                                                System.out.println(g);
                                                out.write(g);
						out.close();
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
		});
 
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.setVisible(false);
                                FirstMainFrame f=new FirstMainFrame();
                                f.setVisible(true);
                                
                                        
			}
		});
	}
 
	public void openFile(){
		JFileChooser open = new JFileChooser();
		int option = open.showOpenDialog(this);
		fileToOpen = option;
		fileOpen = open;
	}
 
	public void saveFile(){
		JFileChooser save = new JFileChooser();
		int option = save.showOpenDialog(this);
		fileToSave = option;
		fileSave = save;
	}
        public static void main(String[] args)
        {
            new Grid();
        }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ravisha\\Desktop\\Pics\\JavaP\\note.PNG"));
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}