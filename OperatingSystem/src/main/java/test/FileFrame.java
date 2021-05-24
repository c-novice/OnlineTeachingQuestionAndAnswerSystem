package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.tree.*;

public class FileFrame extends JPanel {
}

//	private static final long serialVersionUID=1L;
//	private VirtualDisk disk;
//	private JTextField field;
//	private JTree fileTree;
//	public FileFrame(){
//		setLayout(new BorderLayout());
//		VirtualDisk disk=getVirtualDiskFromTxt();
//		add(disk,BorderLayout.CENTER);
//		//用户接口
//		JPanel commandPanel=new JPanel();
//
//		add(commandPanel,BorderLayout.NORTH);
//		//目录树
//		fileTree=disk.getFileDirectoryTree();
//		fileTree.setBackground(new Color(0xffffff));
//		fileTree.setComponentPopupMenu(getPopupMenu());
//		JScrollPane treePane=new JScrollPane(fileTree);
//		add(treePane,BorderLayout.WEST);
//		treePane.setPreferredSize(new Dimension(200,25));
//		treePane.setBorder(BorderFactory.createTitledBorder(
//				BorderFactory.createEtchedBorder(),"文件系统"));
//		//add(new SecondSystemClock(),BorderLayout.EAST);
//	}
//	private VirtualDisk getVirtualDiskFromTxt(){
//		ObjectInputStream in;
//		try {
//			in = new ObjectInputStream(new FileInputStream(new File("src/file/disk.txt")));
//			Object object=in.readObject();
//			disk=(VirtualDisk)object;
//			return disk;
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	private JPopupMenu getPopupMenu(){
//		JPopupMenu menu=new JPopupMenu();
//		JMenu newMenu=new JMenu("新建");
//		menu.add(newMenu);
//		JMenuItem newDirectory=new JMenuItem("文件夹");
//		newDirectory.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent event){
//				DefaultMutableTreeNode parent=(DefaultMutableTreeNode)
//					fileTree.getSelectionPath().getLastPathComponent();
//				VirtualFile parentFile=(VirtualFile)parent.getUserObject();
//				String childFileName=getUsableFileName(parent);
//				VirtualFile childFile=new VirtualFile(childFileName,VirtualFile.EXETENSION_DIRECTORY_FILE);
//				DefaultMutableTreeNode child=new DefaultMutableTreeNode(childFile);
//				disk.createFile(parentFile, childFile);
//				disk.getTreeModel().insertNodeInto(child, parent,parent.getChildCount());
//			}
//		});
//		newMenu.add(newDirectory);
//		JMenuItem newCharFile=new JMenuItem("字符文件");
//		newCharFile.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent event){
//				DefaultMutableTreeNode parent=(DefaultMutableTreeNode)
//				fileTree.getSelectionPath().getLastPathComponent();
//			VirtualFile parentFile=(VirtualFile)parent.getUserObject();
//			String childFileName=getUsableFileName(parent);
//			VirtualFile childFile=new VirtualFile(childFileName,VirtualFile.EXTENSION_CHAR_FILE);
//			DefaultMutableTreeNode child=new DefaultMutableTreeNode(childFile);
//			disk.createFile(parentFile, childFile);
//			disk.getTreeModel().insertNodeInto(child, parent,parent.getChildCount());
//			}
//		});
//		newMenu.add(newCharFile);
//		JMenuItem del=new JMenuItem("删除");
//		del.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent event){
//				DefaultMutableTreeNode choseNode=(DefaultMutableTreeNode)
//				fileTree.getSelectionPath().getLastPathComponent();
//				DefaultMutableTreeNode parent=(DefaultMutableTreeNode)
//				fileTree.getSelectionPath().getParentPath().getLastPathComponent();
//				disk.deleteFile(parent,choseNode);
//			}
//		});
//		menu.add(del);
//		JMenuItem edit=new JMenuItem("编辑");
//		edit.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent event){
//				DefaultMutableTreeNode choseNode=(DefaultMutableTreeNode)
//				fileTree.getSelectionPath().getLastPathComponent();
//				new EditFileDialogFrame(choseNode);
//			}
//		});
//		menu.add(edit);
//
//		return menu;
//	}
//	public String getUsableFileName(DefaultMutableTreeNode parent){
//		String[] name=new String[100];
//		for(int i=0;i<100;i++){
//			if(i<10)name[i]="f0"+i;
//			else name[i]="f"+i;
//		}
//		int count=parent.getChildCount();
//		boolean flag=false;
//		for(int j=0;j<100;j++){
//			for(int i=0;i<count;i++){
//				DefaultMutableTreeNode child=(DefaultMutableTreeNode)parent.getChildAt(i);
//				VirtualFile file=(VirtualFile)child.getUserObject();
//				if(file.getFileName().equals(name[j])){
//					flag=true;
//					break;
//				}
//			}
//			if(flag==false)
//				return name[j];
//			else flag=false;
//		}
//		return null;
//	}
//	private class EditFileDialogFrame extends JFrame{
//		//private static final long serialVersionUID = 1L;
//		private JTextField name,extension;
//		private JButton cancel,record;
//		private JTextArea txt;
//		public EditFileDialogFrame(DefaultMutableTreeNode choseNode){
//			final VirtualFile file=(VirtualFile)choseNode.getUserObject();
//			setTitle("文件编辑");
//			setBounds(300,100,450,580);
//			JPanel nPanel=new JPanel();
//			nPanel.setBorder(BorderFactory.createEtchedBorder());
//			add(nPanel,BorderLayout.NORTH);
//			nPanel.add(new JLabel("文件名"));
//			nPanel.add(name=new JTextField(10));
//			name.setText(file.getFileName());
//			nPanel.add(new JLabel("扩展名"));
//			nPanel.add(extension=new JTextField(5));
//			extension.setText(file.getFileExtension());
//			extension.setEditable(false);
//			JPanel cPanel=new JPanel();
//			add(cPanel,BorderLayout.CENTER);
//			cPanel.add(new JScrollPane(txt=new JTextArea(22,50)));
//			txt.setText(file.getFileContent());
//			cPanel.setBorder(BorderFactory.createEtchedBorder());
//			JPanel sPanel=new JPanel();
//			sPanel.setBorder(BorderFactory.createEtchedBorder());
//			add(sPanel,BorderLayout.SOUTH);
//			sPanel.add(record=new JButton("保存"));
//			record.addActionListener(new ActionListener(){
//				public void actionPerformed(ActionEvent event){
//					file.setContent(txt.getText());
//					file.setFileName(name.getText());
//					file.setFileLength(txt.getText().length()+8);
//					disk.changeFileContent(file, txt.getText(), name.getText());
//					setVisible(false);
//				}
//			});
//			sPanel.add(cancel=new JButton("取消"));
//			cancel.addActionListener(new ActionListener(){
//				public void actionPerformed(ActionEvent event){
//					setVisible(false);
//				}
//			});
//
//			setVisible(true);
//		}
//	}
//	public static void f(){
//		JFrame frame=new JFrame();
//		frame.add(new FileFrame());
//		frame.setTitle("文件管理模拟");
//		// 图标
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		Image image = tk.createImage("src/frame/文件(1).jpg");
//		frame.setIconImage(image);
//		Font font = new Font("宋体", Font.BOLD, 14);
//		frame.setBounds(200,100,850,550);
//		frame.setResizable(false);// 设置窗体不能改变尺寸
//		frame.setVisible(true);
//		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//}
