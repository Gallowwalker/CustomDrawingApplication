package main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class DrawArea extends JPanel {

	private static final long serialVersionUID = 1L;
	private GUI parent;
 	
    public DrawArea(GUI gui) {
    	super();
    	setLayout(null);
    	parent = gui;

    	
    	/*
    	 * 
 
    	 * vzimat se selectiranite elem i se mahat ot glavniq spisan i se pravi spisak i se vkarva spisaka v glavniq spisak
    	 * 
    	 * 
//    	 *  bug - moves only wrapping rectangle - in dialogprocessor
 * 
    	 *  
    	 *  
    	 *  
    	 *  after ungroup items must stay rotated for example
    	 * }
    	 * 
			
			selection = new list<shape>(shapelist); select all
			
			ctrl+ select    ctrl+r - draw rectangle
			

			save file - text file , xml, serialize
			
			in save button
			
			if(savedialog.showdialog == ok){
			
			dialogprocessor.saveas(savefiledialog.filename);
			
			}

			in save in dialog processor
			
			void saveAs(string filename) {
			filestream fs = new Filestream(filename, filemode.create);
				binaryformater bf = new ...
				bf.serialize(fs, shapelist);
			}




    	 * 
    	 * */
    }
    
    protected void paintComponent(Graphics graphics) {
    	super.paintComponent(graphics);
    	this.setBackground(Color.WHITE);
    	parent.getDialogProcessor().reDraw(this, graphics);
    }
    
	    
}
