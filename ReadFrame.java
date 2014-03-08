import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;


public class ReadFrame extends JFrame {

	private JPanel contentPane;
	private EqClass Eqv;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadFrame frame = new ReadFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public ReadFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 91, 668, 351);
		contentPane.add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 15));
		scrollPane.setViewportView(textArea);
		
		JButton read = new JButton("Read file");
		read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					JFileChooser fc = new JFileChooser();
					fc.showOpenDialog(new JFrame());
					Scanner read = new Scanner(fc.getSelectedFile());
					String tmp = read.nextLine();
					String tmp2 = tmp;
					tmp = tmp.replace(",", "");
					int lngth = tmp2.length() - tmp.length();
					String[] table = new String[lngth+1];
					tmp2 +=",";
					tmp2 = tmp2.replace(" ", "");
					for(int i=0; i<table.length; i++){
						table[i] = tmp2.substring(0, tmp2.indexOf(","));
						tmp2 = tmp2.substring(tmp2.indexOf(",")+1);
					}
					
					Eqv = new EqClass(table);
					while(read.hasNext()){
						tmp = read.nextLine();
						tmp = tmp.replace(" ", "");
						if(tmp.contains("?")){
							String a = tmp.substring(tmp.indexOf("?")+1, tmp.indexOf(","));
							String b = tmp.substring(tmp.indexOf(",")+1);
							textArea.setText(textArea.getText()+ Eqv.relative(a, b)+"\n");
						}else{
							String a = tmp.substring(0, tmp.indexOf(","));
							String b = tmp.substring(tmp.indexOf(",")+1);
							Eqv.Merge(a, b);
						}
					}
						
					
					
				}
				catch(Exception e){
				}
				
				
			}
		});
		read.setBounds(277, 11, 96, 36);
		contentPane.add(read);
		
		
	}
}
