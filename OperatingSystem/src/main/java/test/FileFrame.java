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
//		//�û��ӿ�
//		JPanel commandPanel=new JPanel();
//
//		add(commandPanel,BorderLayout.NORTH);
//		//Ŀ¼��
//		fileTree=disk.getFileDirectoryTree();
//		fileTree.setBackground(new Color(0xffffff));
//		fileTree.setComponentPopupMenu(getPopupMenu());
//		JScrollPane treePane=new JScrollPane(fileTree);
//		add(treePane,BorderLayout.WEST);
//		treePane.setPreferredSize(new Dimension(200,25));
//		treePane.setBorder(BorderFactory.createTitledBorder(
//				BorderFactory.createEtchedBorder(),"�ļ�ϵͳ"));
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
//		JMenu newMenu=new JMenu("�½�");
//		menu.add(newMenu);
//		JMenuItem newDirectory=new JMenuItem("�ļ���");
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
//		JMenuItem newCharFile=new JMenuItem("�ַ��ļ�");
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
//		JMenuItem del=new JMenuItem("ɾ��");
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
//		JMenuItem edit=new JMenuItem("�༭");
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
//			setTitle("�ļ��༭");
//			setBounds(300,100,450,580);
//			JPanel nPanel=new JPanel();
//			nPanel.setBorder(BorderFactory.createEtchedBorder());
//			add(nPanel,BorderLayout.NORTH);
//			nPanel.add(new JLabel("�ļ���"));
//			nPanel.add(name=new JTextField(10));
//			name.setText(file.getFileName());
//			nPanel.add(new JLabel("��չ��"));
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
//			sPanel.add(record=new JButton("����"));
//			record.addActionListener(new ActionListener(){
//				public void actionPerformed(ActionEvent event){
//					file.setContent(txt.getText());
//					file.setFileName(name.getText());
//					file.setFileLength(txt.getText().length()+8);
//					disk.changeFileContent(file, txt.getText(), name.getText());
//					setVisible(false);
//				}
//			});
//			sPanel.add(cancel=new JButton("ȡ��"));
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
//		frame.setTitle("�ļ�����ģ��");
//		// ͼ��
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		Image image = tk.createImage("src/frame/�ļ�(1).jpg");
//		frame.setIconImage(image);
//		Font font = new Font("����", Font.BOLD, 14);
//		frame.setBounds(200,100,850,550);
//		frame.setResizable(false);// ���ô��岻�ܸı�ߴ�
//		frame.setVisible(true);
//		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//}
