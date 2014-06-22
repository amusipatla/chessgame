//Amulya Musipatla 2014

package chess;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

 

public class Board implements MouseListener{
	private JButton buttonArray[][]= new JButton[8][8];
	private JComboBox pieces;
	private JLabel moves;
	private Color colorWhite= Color.white;
	private Color colorBlack= Color.red.darker();
	private
	final static boolean shouldFill = true;
	   final static boolean shouldWeightX = true;
	   final static boolean RIGHT_TO_LEFT = false;

    public  void addComponentsToPane(Container pane) {
	       if (RIGHT_TO_LEFT) {
	           pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	       }

	       //JButton button;
	   pane.setLayout(new GridBagLayout());
	  	   GridBagConstraints c = new GridBagConstraints();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Board board=new Board();
				board.chessFrame();
			}
			
		});
	
	}

	public void chessFrame(){
		JFrame frame= new JFrame("Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel= new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		
		String[] pieceslist = { "King", "Queen", "Knight", "Rook", "Bishop" };
		pieces = new JComboBox(pieceslist);
		pieces.setSelectedIndex(0);
		
		GridBagLayout gridbag=new GridBagLayout();
		GridBagConstraints c= new GridBagConstraints();	
		
		JLabel choose= new JLabel("Choose a piece: ");
		JLabel spot= new JLabel("Choose a spot on the board:");
		
		
		JPanel board= new JPanel();
		gridbag.setConstraints(board, c);
		board.setLayout(gridbag);

		moves= new JLabel(" ");
		
		
		for(int j=0; j<8; ++j){
		  	   for(int i=0; i<8; ++i){
			   if (shouldFill) {
				   //natural height, maximum width
				   c.fill = GridBagConstraints.HORIZONTAL;
			   }
			   JButton button = new JButton();
			   button.setName(i+""+j);
			   if (shouldWeightX) {
				   c.weightx = 0.5;
			   }
			   c.fill = GridBagConstraints.HORIZONTAL;
			   c.gridx = i;
			   c.gridy = j;
			   c.ipadx=20;
			   c.ipady=40;
			   if((j+2)%2==0){
				   if((i+2)%2==0)
					   button.setBackground(colorWhite);
				   else{
					   button.setBackground(colorBlack);
					   button.setForeground(colorWhite);
				   }
			   }
			   else{
				   if((i+2)%2==0){
					   button.setBackground(colorBlack);
					   button.setForeground(colorWhite);
				   }
				   else
					   button.setBackground(colorWhite);   
			   }
				
			   buttonArray[i][j]= button;
			   button.addMouseListener(this);
			   board.add(button, c); 
			   }
		  }
		
		choose.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(choose);
		pieces.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(pieces);
		spot.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(spot);
		board.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(board);
		moves.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(moves);
		
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		for(int j=0; j<8; ++j){
		  	   for(int i=0; i<8; ++i){
			   if((j+2)%2==0){
				   if((i+2)%2==0)
					   buttonArray[i][j].setBackground(colorWhite);
				   else{
					   buttonArray[i][j].setBackground(colorBlack);
					   buttonArray[i][j].setForeground(colorWhite);
				   }
			   }
			   else{
				   if((i+2)%2==0){
					   buttonArray[i][j].setBackground(colorBlack);
					   buttonArray[i][j].setForeground(colorWhite);
				   }
				   else
					   buttonArray[i][j].setBackground(colorWhite);   
			   } 
			   }
		  }
		
		int piece= pieces.getSelectedIndex();
		int i= ((JButton)e.getComponent()).getName().charAt(0)-48;
		int j= ((JButton)e.getComponent()).getName().charAt(1)-48;
		buttonArray[i][j].setBackground(Color.black);
		int m;
		
		if(piece==0)
			m=kingMoves(i, j);
		else if(piece==1)
			m=queenMoves(i, j);
		else if(piece==2)
			m=knightMoves(i,j);
		else if(piece==3)
			m=rookMoves(i, j);
		else
			m=bishopMoves(i, j);
		
		moves.setText("You can make "+ m + " moves." );
	}

	private int kingMoves(int i, int j){
		int b=0;
		
		if(i-1>-1){
			buttonArray[i-1][j].setBackground(buttonArray[i-1][j].getBackground().darker().darker().darker());
			++b;
		}
		if(i+1<8){
			buttonArray[i+1][j].setBackground(buttonArray[i+1][j].getBackground().darker().darker().darker());
			++b;
		}
		if(j-1>-1){
			buttonArray[i][j-1].setBackground(buttonArray[i][j-1].getBackground().darker().darker().darker());
			++b;
		}
		if(j+1<8){
			buttonArray[i][j+1].setBackground(buttonArray[i][j+1].getBackground().darker().darker().darker());
			++b;
		}
		if(i-1>-1 && j-1>-1){
			buttonArray[i-1][j-1].setBackground(buttonArray[i-1][j-1].getBackground().darker().darker().darker());
			++b;
		}
		if(i-1>-1 && j+1<8){
			buttonArray[i-1][j+1].setBackground(buttonArray[i-1][j+1].getBackground().darker().darker().darker());
			++b;
		}
		if(i+1<8 && j-1>-1){
			buttonArray[i+1][j-1].setBackground(buttonArray[i+1][j-1].getBackground().darker().darker().darker());
			++b;
		}
		if(i+1<8 && j+1<8){
			buttonArray[i+1][j+1].setBackground(buttonArray[i+1][j+1].getBackground().darker().darker().darker());
			++b;
		}
		
		return b;
	}
	
	private int queenMoves(int i, int j){
		return rookMoves(i,j)+bishopMoves(i,j);
	}
	
	private int knightMoves(int i, int j){
		int b=0;
		
		if(i-2>-1 && j-1>-1){
			buttonArray[i-2][j-1].setBackground(buttonArray[i-2][j-1].getBackground().darker().darker().darker());
			++b;
		}
		if(i-2>-1 && j+1<8){
			buttonArray[i-2][j+1].setBackground(buttonArray[i-2][j+1].getBackground().darker().darker().darker());
			++b;
		}
		if(i+2<8 && j-1>-1){
			buttonArray[i+2][j-1].setBackground(buttonArray[i+2][j-1].getBackground().darker().darker().darker());
			++b;
		}
		if(i+2<8 && j+1<8){
			buttonArray[i+2][j+1].setBackground(buttonArray[i+2][j+1].getBackground().darker().darker().darker());
			++b;
		}
		if(i-1>-1 && j-2>-1){
			buttonArray[i-1][j-2].setBackground(buttonArray[i-1][j-2].getBackground().darker().darker().darker());
			++b;
		}
		if(i+1<8 && j-2>-1){
			buttonArray[i+1][j-2].setBackground(buttonArray[i+1][j-2].getBackground().darker().darker().darker());
			++b;
		}
		if(i-1>-1 && j+2<8){
			buttonArray[i-1][j+2].setBackground(buttonArray[i-1][j+2].getBackground().darker().darker().darker());
			++b;
		}
		if(i+1<8 && j+2<8){
			buttonArray[i+1][j+2].setBackground(buttonArray[i+1][j+2].getBackground().darker().darker().darker());
			++b;
		}
		
		return b;
	}
	
	private int rookMoves(int i, int j){
		for(int a=0; a<8; ++a){
			buttonArray[i][a].setBackground(buttonArray[i][a].getBackground().darker().darker().darker());
			buttonArray[a][j].setBackground(buttonArray[a][j].getBackground().darker().darker().darker());
		}
		return 14;
	}
	
	private int bishopMoves(int i, int j){
		int b=0;
		for(int a=1; a<8; ++a){
			if(i-a>-1 && j-a>-1){
				buttonArray[i-a][j-a].setBackground(buttonArray[i-a][j-a].getBackground().darker().darker().darker());
				++b;
			}
		}
		for(int a=1; a<8; ++a){
			if(i-a>-1 && j+a<8){
				buttonArray[i-a][j+a].setBackground(buttonArray[i-a][j+a].getBackground().darker().darker().darker());
				++b;
			}
		}
		for(int a=1; a<8; ++a){
			if(i+a<8 && j-a>-1){			
				buttonArray[i+a][j-a].setBackground(buttonArray[i+a][j-a].getBackground().darker().darker().darker());
				++b;
			}
		}
		for(int a=1; a<8; ++a){
			if(i+a<8 && j+a<8){
				buttonArray[i+a][j+a].setBackground(buttonArray[i+a][j+a].getBackground().darker().darker().darker());
				++b;
			}
		}
		return b;
	}
	
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
