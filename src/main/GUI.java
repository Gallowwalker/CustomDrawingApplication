package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import models.Shape;
import processors.DialogProcessor;

public class GUI {
	
	private static boolean controlState = false;
	
	public static boolean getControlState() {
		return controlState;
	}
	
	public static void setControlState(boolean state) {
		controlState = state;
	}
	
	private DialogProcessor dialogProcessor = null;
	private JFrame frame = null;
	DrawArea drawArea = null;
	
	private JMenuBar menuBar;
	private JMenu menuFile, menuEdit, menuResizeShape, menuImage, menuHelp, menuScale;
	private JMenuItem menuItemNewFile, menuItemOpenFile, menuItemSaveFile, menuItemExitProgram, menuItemScaleUp, menuItemScaleDown;
	private JMenuItem menuItemRotateShape, menuItemChangeColor, menuItemResizeWidth, menuItemResizeHeight;
	private JToolBar toolBar;
	private JToggleButton toggleButtonSelect;
	private JButton buttonGroupShapes, buttonUngroupShapes, buttonResizeWidth, buttonResizeHeight, buttonColorChooser, buttonDrawRectangle, buttonDrawEllipse, buttonDrawCircle, buttonDrawPolygon, buttonDrawTriangle;
	private JButton buttonDeleteShape, buttonRotateShapeLeft, buttonRotateShapeRight, buttonDrawDot, buttonDrawLine, buttonCopy, buttonBorderThickness, buttonScale, buttonDrawNewShape;
	
	public GUI() {
		
		drawArea = new DrawArea(this);
		initialize();
		dialogProcessor = new DialogProcessor(this.frame);
	}
	
	private void initialize() {
		frame = new JFrame("Ivan Puntev's Custom Drawing Program");
		frame.setResizable(false);
		frame.setBounds(100, 50, 1680, 950);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1674, 30);
		frame.getContentPane().add(menuBar);
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		menuItemNewFile = new JMenuItem("New File");
		menuItemNewFile.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\new-file.png"));
		menuFile.add(menuItemNewFile);
		
		menuItemOpenFile = new JMenuItem("Open File");
		menuItemOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(menuItemOpenFile) == JFileChooser.APPROVE_OPTION) {
					//File file = fileChooser.getSelectedFile();
				}
			}
		});
		menuItemOpenFile.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\open-file.png"));
		menuFile.add(menuItemOpenFile);
		
		menuItemSaveFile = new JMenuItem("Save File");
		menuItemSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(menuItemSaveFile) == JFileChooser.APPROVE_OPTION) {
					String fileName = fileChooser.getSelectedFile().getName();
					
					
				}
			}
		});
		menuItemSaveFile.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\save-file.png"));
		menuFile.add(menuItemSaveFile);
		
		menuItemExitProgram = new JMenuItem("Exit Program");
		menuItemExitProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
				frame.dispose();
			}
		});
		menuItemExitProgram.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\delete-file.png"));
		menuFile.add(menuItemExitProgram);
		
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);
		
		menuResizeShape = new JMenu("Resize Shape");
		menuEdit.add(menuResizeShape);
		
		menuItemResizeWidth = new JMenuItem("Resize Width");
		menuItemResizeWidth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null  && !dialogProcessor.getSelectedShapes().isEmpty()) {
					dialogProcessor.showResizeWidthDialog(frame, "Resize Width", "Enter the new width.");
					drawArea.repaint();	
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
			}
		});
		menuResizeShape.add(menuItemResizeWidth);
		
		menuItemResizeHeight = new JMenuItem("Resize Height");
		menuItemResizeHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null  && !dialogProcessor.getSelectedShapes().isEmpty()) {
					dialogProcessor.showResizeHeightDialog(frame, "Resize height", "Enter the new height.");
					drawArea.repaint();
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
			}
		});
		menuResizeShape.add(menuItemResizeHeight);
		
		menuItemRotateShape = new JMenuItem("Rotate Shape");
		menuEdit.add(menuItemRotateShape);
		
		menuItemChangeColor = new JMenuItem("Change Color");
		menuItemChangeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null  && !dialogProcessor.getSelectedShapes().isEmpty()) {
					Object[] colorButtons = {"Fill Color", "Border Color", "Cancel"};
					int chosenOption = dialogProcessor.showColorDialog(frame, "Choose Color Option", "Which color do you want to set?", colorButtons);
					Color color = null;
					switch(chosenOption) {
						case 0:
							color = JColorChooser.showDialog(null, "Color Chooser", Color.WHITE);
							if (color != null) {
								dialogProcessor.setFillColor(color);
								//dialogProcessor.setGroupFillColor(color);
							}
							break;
						case 1:
							color = JColorChooser.showDialog(null, "Color Chooser", Color.WHITE);
							if (color != null) {
								dialogProcessor.setBorderColor(color);
								//dialogProcessor.setGroupBorderColor(color);
							}
							break;
						case 2: return;
						default:
							break;
					}
					drawArea.repaint();
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
			}
		});
		menuEdit.add(menuItemChangeColor);
		
		menuImage = new JMenu("Image");
		menuBar.add(menuImage);
		
		menuScale = new JMenu("Scale");
		menuBar.add(menuScale);
		
		menuItemScaleUp = new JMenuItem("Scale Up");
		menuItemScaleUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null  && !dialogProcessor.getSelectedShapes().isEmpty()) {
					dialogProcessor.showScaleDialog(frame, "Scale shape", "Choose how many time you want to scale up the shape(s).", "Up");
					drawArea.repaint();	
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
			}
		});
		menuScale.add(menuItemScaleUp);
		
		menuItemScaleDown = new JMenuItem("Scale Down");
		menuItemScaleDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null  && !dialogProcessor.getSelectedShapes().isEmpty()) {
					dialogProcessor.showScaleDialog(frame, "Scale shape", "Choose how many time you want to scale up the shape(s).", "Down");
					drawArea.repaint();	
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
			}
		});
		menuScale.add(menuItemScaleDown);
		
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 31, 1674, 48);
		frame.getContentPane().add(toolBar);
		
		toggleButtonSelect = new JToggleButton();
		toggleButtonSelect.setToolTipText("\u0421\u0435\u043B\u0435\u043A\u0446\u0438\u044F");
		toggleButtonSelect.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\pointer.png"));
		toggleButtonSelect.setSelected(true);
		toolBar.add(toggleButtonSelect);
		
		buttonDrawLine = new JButton();
		buttonDrawLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialogProcessor.addRandomLine(15, 15);
				drawArea.repaint();
				
			}
		});
		buttonDrawLine.setToolTipText("\u041B\u0438\u043D\u0438\u044F");
		buttonDrawLine.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\line2.png"));
		toolBar.add(buttonDrawLine);
		
		buttonDrawDot = new JButton();
		buttonDrawDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialogProcessor.addRandomOval(1, 1);
				drawArea.repaint();
				
			}
		});
		buttonDrawDot.setToolTipText("\u0422\u043E\u0447\u043A\u0430");
		buttonDrawDot.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\dot.png"));
		toolBar.add(buttonDrawDot);
		
		buttonDrawRectangle = new JButton();
		buttonDrawRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialogProcessor.addRandomRectangle();
				drawArea.repaint();
				
			}
		});
		buttonDrawRectangle.setToolTipText("\u041F\u0440\u0430\u0432\u043E\u044A\u0433\u044A\u043B\u043D\u0438\u043A");
		buttonDrawRectangle.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\rectangle.png"));
		toolBar.add(buttonDrawRectangle);
		
		buttonDrawTriangle = new JButton();
		buttonDrawTriangle.setToolTipText("\u0422\u0440\u0438\u044A\u0433\u044A\u043B\u043D\u0438\u043A");
		buttonDrawTriangle.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\triangle.png"));
		buttonDrawTriangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//dialogProcessor.showPolygonDialog(frame, "Polygon sides", "Enter how many sides you want your polygon to have.");
				dialogProcessor.addRandomPolygon(200, 200, 3);
				drawArea.repaint();
			}
		});
		toolBar.add(buttonDrawTriangle);
		
		buttonDrawCircle = new JButton();
		buttonDrawCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialogProcessor.addRandomOval(150, 150);
				drawArea.repaint();
				
			}
		});
		buttonDrawCircle.setToolTipText("\u041A\u0440\u044A\u0433");
		buttonDrawCircle.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\circle.png"));
		toolBar.add(buttonDrawCircle);
		
		buttonDrawEllipse = new JButton();
		buttonDrawEllipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialogProcessor.addRandomOval(200, 100);
				drawArea.repaint();
				
			}
		});
		buttonDrawEllipse.setToolTipText("\u0415\u043B\u0438\u043F\u0441\u0430");
		buttonDrawEllipse.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\ellipse.png"));
		toolBar.add(buttonDrawEllipse);
		
		buttonDrawPolygon = new JButton();
		buttonDrawPolygon.setToolTipText("\u041F\u043E\u043B\u0438\u0433\u043E\u043D");
		buttonDrawPolygon.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\polygon2.png"));
		buttonDrawPolygon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialogProcessor.showPolygonDialog(frame, "Polygon sides", "Enter how many sides you want your polygon to have.");
				drawArea.repaint();
			}
		});
		toolBar.add(buttonDrawPolygon);
		
		buttonGroupShapes = new JButton();
		buttonGroupShapes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialogProcessor.group();
				drawArea.repaint();
			}
		});
		buttonGroupShapes.setToolTipText("\u0413\u0440\u0443\u043F\u0438\u0440\u0430\u0439");
		buttonGroupShapes.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\group-shapes.png"));
		toolBar.add(buttonGroupShapes);
		
		buttonUngroupShapes = new JButton();
		buttonUngroupShapes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialogProcessor.unGroup();
				drawArea.repaint();
			}
		});
		buttonUngroupShapes.setToolTipText("\u0420\u0430\u0437\u0433\u0440\u0443\u043F\u0438\u0440\u0430\u0439");
		buttonUngroupShapes.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\ungroup-shapes.png"));
		toolBar.add(buttonUngroupShapes);
		
		buttonColorChooser = new JButton();
		buttonColorChooser.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\color-palette.png"));
		buttonColorChooser.setToolTipText("\u0426\u0432\u0435\u0442\u043E\u0432\u0430 \u041F\u0430\u043B\u0438\u0442\u0440\u0430");
		buttonColorChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null  && !dialogProcessor.getSelectedShapes().isEmpty()) {
					Object[] colorButtons = {"Fill Color", "Border Color", "Cancel"};
					int chosenOption = dialogProcessor.showColorDialog(frame, "Choose Color Option", "Which color do you want to set?", colorButtons);
					Color color = null;
					switch(chosenOption) {
						case 0:
							color = JColorChooser.showDialog(null, "Color Chooser", Color.WHITE);
							if (color != null) {
								dialogProcessor.setFillColor(color);
							}
							break;
						case 1:
							color = JColorChooser.showDialog(null, "Color Chooser", Color.WHITE);
							if (color != null) {
								dialogProcessor.setBorderColor(color);
							}
							break;
						case 2: return;
						default:
							break;
					}
					drawArea.repaint();
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
			}
		});
		toolBar.add(buttonColorChooser);
		
		buttonBorderThickness = new JButton();
		buttonBorderThickness.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\border.png"));
		buttonBorderThickness.setToolTipText("Дебелина на очертание");
		buttonBorderThickness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null  && !dialogProcessor.getSelectedShapes().isEmpty()) {
						dialogProcessor.showResizeBorderThicknessDialog(frame, "Resize border thickness", "Enter the new thickness.");
						drawArea.repaint();
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
			}
			
			
		});
		toolBar.add(buttonBorderThickness);
		
		buttonScale = new JButton();
		buttonScale.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\scale.png"));
		buttonScale.setToolTipText("Скалиране");
		buttonScale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null  && !dialogProcessor.getSelectedShapes().isEmpty()) {
						Object[] buttons = {"Scale Up", "Scale Down", "Cancel"};
						int choice = dialogProcessor.showDialog(frame, "Scale shape", "Do you want to scale up or down your shape?", buttons);
						switch (choice) {
							case 0: 
								dialogProcessor.showScaleDialog(frame, "Scale shape", "Choose how many time you want to scale up the shape(s).", "Up");
								break;
							case 1: 
								dialogProcessor.showScaleDialog(frame, "Scale shape", "Choose how many time you want to scale down the shape(s).", "Down");
								break;
							case 2: return;
							default: return;
						}
						drawArea.repaint();
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
			}
		});
		toolBar.add(buttonScale);
		
		buttonResizeWidth = new JButton();
		buttonResizeWidth.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\width.png"));
		buttonResizeWidth.setToolTipText("Смени широчина");
		buttonResizeWidth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null  && !dialogProcessor.getSelectedShapes().isEmpty()) {
						dialogProcessor.showResizeWidthDialog(frame, "Resize width", "Enter the new width.");
						drawArea.repaint();
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
			}
		});
		toolBar.add(buttonResizeWidth);
		
		buttonResizeHeight = new JButton();
		buttonResizeHeight.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\height.png"));
		buttonResizeHeight.setToolTipText("Смени височина");
		buttonResizeHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null  && !dialogProcessor.getSelectedShapes().isEmpty()) {
						dialogProcessor.showResizeHeightDialog(frame, "Resize height", "Enter the new height.");
						drawArea.repaint();
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
			}
		});
		toolBar.add(buttonResizeHeight);
		
		buttonRotateShapeLeft = new JButton();
		buttonRotateShapeLeft.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\rotate-left.png"));
		buttonRotateShapeLeft.setToolTipText("Завърти Наляво");
		buttonRotateShapeLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null && !dialogProcessor.getSelectedShapes().isEmpty()) {
					int degrees = dialogProcessor.showRotateDialog(frame, "Rotate Shapes", "How much degrees you want to rotate the selected shapes.");
					dialogProcessor.rotateSelectedShapesLeft(degrees);
					System.out.println(dialogProcessor.getSelectedShapes().size());
					drawArea.repaint();
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
				
				
				
				
				
			}
		});
		toolBar.add(buttonRotateShapeLeft);
		
		buttonRotateShapeRight = new JButton();
		buttonRotateShapeRight.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\rotate-right.png"));
		buttonRotateShapeRight.setToolTipText("Завърти Надясно");
		buttonRotateShapeRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null && !dialogProcessor.getSelectedShapes().isEmpty()) {
					int degrees = dialogProcessor.showRotateDialog(frame, "Rotate Shapes", "How much degrees you want to rotate the selected shapes.");
					dialogProcessor.rotateSelectedShapesRight(degrees);
					drawArea.repaint();
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
				
				
				
				
				
			}
		});
		toolBar.add(buttonRotateShapeRight);
		
		buttonDrawNewShape = new JButton("New Shape");
		buttonDrawNewShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialogProcessor.addRandomNewShape(150, 100, 0);
					
					drawArea.repaint();
				
				
				
				
				
				
			}
		});
		toolBar.add(buttonDrawNewShape);
		
		buttonCopy = new JButton();
		buttonCopy.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\copy.png"));
		buttonCopy.setToolTipText("Копирай");
		buttonCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String shapeName = null;
				Shape newShape = null;
				ShapeFactory factory = new ShapeFactory();
				for (Shape shape : dialogProcessor.getSelectedShapes()) {
					shapeName = shape.getClass().getSimpleName();
					newShape = factory.getShape(shapeName, shape.getLocation().x + 10, shape.getLocation().y + 10, shape.getWidth(), shape.getHeight(), shape.getSides(), shape.getFillColor(), shape.getBorderThickness(), shape.getBorderColor());
					dialogProcessor.shapeList.add(newShape);
				}
				drawArea.repaint();
			}
		});
		toolBar.add(buttonCopy);
		
		buttonDeleteShape = new JButton();
		buttonDeleteShape.setIcon(new ImageIcon("D:\\Software\\Projects\\EclipseProjects\\CustomDrawingApplication\\src\\img\\delete1.png"));
		buttonDeleteShape.setToolTipText("Изтрий");
		buttonDeleteShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialogProcessor.getSelectedShapes() != null && !dialogProcessor.getSelectedShapes().isEmpty()) {
					Object[] deleteButtons = {"Yes", "No"};
					int chosenOption = dialogProcessor.showDeleteDialog(frame, "Delete Shapes", "Are you sure you want to delete the selected shapes?", deleteButtons);
					switch(chosenOption) {
						case 0:
							dialogProcessor.deleteSelectedShapes();
							break;
						case 1:
							return;
						default:
							break;
					}
					drawArea.repaint();
				} else {
					Object[] errorButtons = {"Ok"};
					dialogProcessor.showErrorDialog(frame, "Error", "You must select shape or group first.", errorButtons);
				}
			}
		});
		toolBar.add(buttonDeleteShape);
		
		drawArea.setBounds(0, 79, 1674, 843);
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		frame.addKeyListener(new MyKeyListener());
		frame.getContentPane().add(drawArea);
		
		
		drawArea.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent event) {
	        	frame.requestFocus();
	            if (toggleButtonSelect.isSelected() && controlState == true) {
	            	Shape selectedShape = dialogProcessor.containsPoint(event.getPoint());
	           	 	if (selectedShape != null) {
	           	 		if (dialogProcessor.getSelectedShapes().contains(selectedShape)) {
	           	 			dialogProcessor.getSelectedShapes().remove(selectedShape);
	           	 		} else {
	           	 			dialogProcessor.getSelectedShapes().add(selectedShape);
	           	 		}
	           	 	}
	                if (dialogProcessor.getSelectedShapes() != null) {
                        dialogProcessor.setLastMouseLocation(event.getPoint());
                        drawArea.repaint();
	                }
	            }
	            
	        }
	        
	        public void mouseReleased(MouseEvent event) {
	        	
	        }
        });
		
		drawArea.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent event) {
            	Shape selectedShape = dialogProcessor.containsPoint(event.getPoint());
            	if (selectedShape != null) {
	                dialogProcessor.translateTo(event.getPoint());
	                drawArea.repaint();
            	}
            }
        });
		
		
	}
	
	public DialogProcessor getDialogProcessor() {
        return dialogProcessor;
    }
}
