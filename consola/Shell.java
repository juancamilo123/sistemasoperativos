import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Shell extends JFrame implements ActionListener{
	
	//elementos gráficos
        JLabel titulo;
	JTextField tComando;
	JButton bEjecutar;
	JTextArea tResultado;
	JScrollPane sPane;
        JMenu menug;
        JMenuBar barra;
        JMenuItem item1,item2,item3;
	//oyente de click de botón
	ActionListener alEjecutar;
        String so=System.getProperty("os.name");

	public Shell(){
		setSize(800,700);
		setTitle(so);
                setResizable(false);
                setLocationRelativeTo(null);
                setBackground(new Color(255,0,0));
                
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void graficos(){
            
		setLayout(null);
                getContentPane().setBackground(Color.darkGray);
                //titulo
                titulo = new JLabel("Consola de comandos");
                titulo.setBounds(200,10,500,120);
                titulo.setBackground(Color.black);
                titulo.setForeground(Color.BLUE.brighter());
                titulo.setFont(new Font("consolas",1,40));
                add(titulo);
		//cuadro de texto
		tComando = new JTextField();                
		tComando.setBounds(240,120,300,30);
                tComando.setBackground(Color.LIGHT_GRAY);
                tComando.setForeground(Color.blue);
                tComando.setCaretColor(Color.green);
                tComando.setFont(new Font("consolas",2,14));
                add(tComando);                
                tComando.addKeyListener(new KeyAdapter(){
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode()== KeyEvent.VK_ENTER){
                            ejecutar();
                        }    
                }
                    
                });              
		//botón para ejecutar comando
		bEjecutar = new JButton("Ejecutar");
		bEjecutar.setBounds(620,120,150,30);
		add(bEjecutar);
		bEjecutar.addActionListener(alEjecutar);
                //menu
                barra = new JMenuBar();
                setJMenuBar(barra);
                menug= new JMenu("Archivo");
                barra.add(menug);
                item1=new JMenuItem("Ejecutar");
                item1.addActionListener(this);
                menug.add(item1);
                item2=new JMenuItem("Limpiar       clear");
                item2.addActionListener(this);
                menug.add(item2);
                item3=new JMenuItem("Salir");
                item3.addActionListener(this);
                menug.add(item3);
                //área de texto
		tResultado = new JTextArea("Consola de "+so+"\n"); 
		tResultado.setBackground(Color.BLACK);
		tResultado.setForeground(Color.blue);
                tResultado.setFont(new Font("consolas",2,14));
		//scroll pane
		sPane = new JScrollPane(tResultado);
		sPane.setBounds(10,220,750,400);
		add(sPane);
		//
                
		setVisible(true);
	}

	private void acciones(){
		alEjecutar = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ejecutar();
			}
		};              
                        
                
	}
        
        public void actionPerformed(ActionEvent e) {
            Container f=this.getContentPane();
            if (e.getSource()==item1) {
                ejecutar();
            }
            if (e.getSource()==item2) {
                tResultado.setText("Consola de "+so+"\n");
            }
            if (e.getSource()==item3) {
            System.exit(0);
            }      
        }

	private void ejecutar(){

		Process proc; 
		InputStream is_in;
		String s_aux;
		BufferedReader br;
                Object aux="clear";
                if(tComando.getText().equals(aux)){
                    tResultado.setText("Consola de "+so+"\n");
                }

		try{
			proc = Runtime.getRuntime().exec(tComando.getText());
			is_in=proc.getInputStream();
			br=new BufferedReader (new InputStreamReader (is_in));
			s_aux = br.readLine();                        
            while (s_aux!=null){
            	tResultado.setText(tResultado.getText()+s_aux+"\n");
                s_aux = br.readLine();
                
            } 
		}
		catch(Exception e)
		{
			e.getMessage();
		}


	}

	public static void main(String args[]){
		Shell ventana = new Shell();
		ventana.acciones();	
		ventana.graficos();	
	}
}

    