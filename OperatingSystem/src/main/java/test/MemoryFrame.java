package frame;

import java.util.Random;
import java.awt.Color;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MemoryFrame extends JFrame{
	/**
	 * 主窗口
	 */
	private static final long serialVersionUID = 1L;
	private static int dp_method=0;
	public static StringBuffer ss = new StringBuffer();
	public MemoryFrame()
	{
		setTitle("内存管理模拟");
		setLayout(null);
		setBounds(100,50,1180,600);//,,右,下
		Font font = new Font("宋体", Font.BOLD, 14);
		setResizable(false);// 设置窗体不能改变尺寸
		int a[]=new int[10];
		
		//图标
		Toolkit tk=Toolkit.getDefaultToolkit();
		Image image=tk.createImage("src/frame/内存.jpg"); 
		this.setIconImage(image);
		
		Container co=getContentPane();
		co.setLayout(null);
	
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel panel5=new JPanel();
		JPanel panel6=new JPanel();
		
		panel1.setBounds(10, 10, 350, 635);
		panel1.setLayout(null);
						
		panel2.setBounds(370, 10, 780, 635);
		panel2.setLayout(null);

		panel3.setBounds(10, 5, 760, 500);
		panel3.setLayout(null);
		
		panel5.setBounds(25, 285, 300, 180);
		panel5.setLayout(null);
		
		panel6.setBounds(25, 480, 300, 140);
		panel6.setLayout(null);
		
		//设计Panel1
		
		Vector<String> colName=new Vector<>();
		colName.add("系统内存大小：64k");
		colName.add("可分配物理块:3个");
		colName.add("进程名");
		colName.add("进程页面号引用串");
		colName.add("生成");
		
		Vector<Vector<String>> tableValue=new Vector<>();
		JTable p1_jt1=new JTable();
		
		//设计Panel4
		final JTextArea p4_info=new JTextArea(10,50);
		p4_info.setBounds(10,20,740,280);
		p4_info.setFont(new Font("宋体", Font.BOLD, 13));
		p4_info.setEditable(false);
		
		JScrollPane p4_jscroll = new JScrollPane();// 将文本域放入滚动面板中
		p4_jscroll.setViewportView(p4_info);
		p4_jscroll.setBounds(10,20,740,280);		
		
		//设计Panel3
		final JTextArea p3_info=new JTextArea(10,50);
		p3_info.setBounds(10,20,800,280);
		p3_info.setFont(new Font("宋体", Font.BOLD, 13));
		p3_info.setEditable(false);
		
		JScrollPane p3_jscroll = new JScrollPane();// 将文本域放入滚动面板中
		p3_jscroll.setViewportView(p3_info);
		p3_jscroll.setBounds(10,20,740,470);
		panel3.add(p3_jscroll);
		
		//设计Panel1
		JLabel p1_memoryl=new JLabel("系统内存大小：64k");
		JLabel p1_namel=new JLabel("进程名:");
		JLabel p1_blockl=new JLabel("可分配物理块:3个");
		JLabel p1_numberl=new JLabel("进程页面号引用串：");
		JButton p1_autoplay=new JButton("生成");
		
		final JTextField p1_name=new JTextField();
		final JTextField p1_memory=new JTextField();
		final JTextField p1_block=new JTextField();
		final JTextField p1_number=new JTextField();
		p1_number.setEditable(false);
		
		JButton p1_clear=new JButton("清空");
		
		JButton p5_clear=new JButton("清空置换信息");
		JButton p5_start=new JButton("确定");
		
		final JComboBox<String> p5_box=new JComboBox<>();
		p5_box.addItem("--请选择置换算法--");
		p5_box.addItem("01-OPT");
		p5_box.addItem("02-FIFO");
		p5_box.addItem("03-LRU");
		p5_box.setFont(font);		
		p5_box.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(((String)p5_box.getSelectedItem()).equals("--请选择置换算法--"))
				{				
						JOptionPane.showMessageDialog(null, "请选择置换算法!", "置换算法", JOptionPane.INFORMATION_MESSAGE);
				}else {					
					dp_method=Integer.parseInt(((String)p5_box.getSelectedItem()).substring(1, 2));
				}
				
			}
		});
		panel5.add(p5_box);
		
		p1_clear.setFont(font);
		p1_autoplay.setFont(new Font("宋体", Font.BOLD, 16));	
		p1_autoplay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 Random r=new Random();
				 String s1;
				 int i;
				 for(i=0;i<a.length;i++)
				 {
					 a[i]=r.nextInt(9)+1; 
					 ss.append(a[i]);  
				 }
				s1=ss.toString();
				 p1_number.setText(s1);
			}
		});
		p5_clear.setFont(font);
		p5_start.setFont(font);		
		
		p1_clear.setBounds(200,220,80,30);
		
		p5_clear.setBounds(25,100,235,30);
		p5_start.setBounds(100,50,70,30);
		p5_box.setBounds(25,0,230,30);
	
		panel5.add(p5_clear);
		panel5.add(p5_start);
		p5_start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if((p1_name.getText().equals(""))||
						 (p1_number.getText().equals("")))
				 {
					 JOptionPane.showMessageDialog(null, "请完善信息", "添加失败", JOptionPane.INFORMATION_MESSAGE);
					 
				 }
				switch (dp_method) {
				case 1: {
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
			//				new OPT(p3_info);
						}
					}).start();
					break;
				}
				case 2: {
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
			//				new FIFO(p3_info);
						}
					}).start();
					break;
				}
				case 3:
				{
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
						//	new LRU(p3_info);
						}
					}).start();
					break;
				}
				default: {
					JOptionPane.showMessageDialog(null, "无效的置换算法", "置换失败", JOptionPane.ERROR_MESSAGE);
				}
				}
			}
		});
				
		panel1.setBorder(BorderFactory.createTitledBorder("系统基本信息"));
		panel3.setBorder(BorderFactory.createTitledBorder("页面置换"));
		
		panel1.add(p1_clear);
		panel1.add(p1_autoplay);
				
		p5_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				p4_info.setText("");
				p3_info.setText("");
				ss.setLength(0);
			}
		});
	
		p1_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 p1_name.setText("");
				 p1_number.setText("");
				 ss.setLength(0);
			}
		});
		
		p1_namel.setFont(font);
		p1_memoryl.setFont(font);
		p1_blockl.setFont(font);
		p1_numberl.setFont(font);
		
		p1_name.setFont(font);
		p1_memory.setFont(font);
		p1_block.setFont(font);
		p1_number.setFont(font);
		
		p1_memoryl.setBounds(50,30,200,30);
		p1_blockl.setBounds(50,60,200,30);
		p1_namel.setBounds(50,110,80,30);
		p1_numberl.setBounds(50,140,220,30);
		p1_autoplay.setBounds(200,145,80,30);
		
		p1_name.setBounds(130,110,150,30);
		p1_number.setBounds(50,180,230,30);
		
		panel1.add(p1_namel);
		panel1.add(p1_memoryl);
		panel1.add(p1_blockl);
		panel1.add(p1_numberl);
		
		panel1.add(p1_name);
		panel1.add(p1_memory);
		panel1.add(p1_block);
		panel1.add(p1_number);

		panel1.add(p1_clear);
		panel1.add(panel5);
		panel1.add(panel6);
		
		
		co.add(panel1);
		co.add(panel2);
		panel2.add(panel3);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MemoryFrame();
	}

}
