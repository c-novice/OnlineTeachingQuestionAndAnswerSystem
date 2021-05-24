package frame;
import javax.swing.*;



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

import javax.swing.*;

//import manage.process.FCFS;
//import manage.process.HRRN;
//import process.*;
public class ProcessFrame extends JFrame {
}

//
//	private static final long serialVersionUID = 1L;
//	private static int dp_method=0;
//
//	public ProcessFrame()
//	{
//		setTitle("���̹���ģ��");
//		setLayout(null);
//		setBounds(100,20,1150,700);
//		Font font = new Font("����", Font.BOLD, 14);
//		setResizable(false);// ���ô��岻�ܸı�ߴ�
//
//
//		Toolkit tk=Toolkit.getDefaultToolkit();
//		Image image=tk.createImage("src/frame/����.jpg");
//		this.setIconImage(image);
//
//		Container co=getContentPane();
//		co.setLayout(null);
//
//		JPanel panel1=new JPanel();//�����Ϣ
//		JPanel panel2=new JPanel();//�ұ�
//		JPanel panel3=new JPanel();//���̵���
//		JPanel panel4=new JPanel();//���̹���
//		JPanel panel5=new JPanel();//���̹������
//
//		panel1.setBounds(10, 25, 350, 600);
//		panel1.setLayout(null);
//
//		panel2.setBounds(360, 10, 780, 635);
//		panel2.setLayout(null);
//
//		panel3.setBounds(10, 320, 760, 310);
//		panel3.setLayout(null);
//
//		panel4.setBounds(10, 25, 760, 310);
//		panel4.setLayout(null);
//
//		panel5.setBounds(30, 350, 300, 180);
//		panel5.setLayout(null);
//
//
//		//���Panel1
//
//		Vector<String> colName=new Vector<>();
//		colName.add("������");
//		colName.add("����ʱ��");
//		colName.add("����ʱ��");
//		colName.add("�豸");
//		colName.add("״̬");
//
//		Vector<Vector<String>> tableValue=new Vector<>();
//		JTable p1_jt1=new JTable();
//
//		//���Panel4
//		final JTextArea p4_info=new JTextArea(10,50);
//		p4_info.setBounds(10,20,740,280);
//		p4_info.setFont(new Font("����", Font.BOLD, 14));
//		p4_info.setEditable(false);
//
//		JScrollPane p4_jscroll = new JScrollPane();// ���ı��������������
//		p4_jscroll.setViewportView(p4_info);
//		p4_jscroll.setBounds(10,20,740,280);
//
//		panel4.add(p4_jscroll);
//
//		//���Panel3
//		final JTextArea p3_info=new JTextArea(10,50);
//		p3_info.setBounds(10,20,740,280);
//		p3_info.setFont(new Font("����", Font.BOLD, 14));
//		p3_info.setEditable(false);
//
//		JScrollPane p3_jscroll = new JScrollPane();// ���ı��������������
//		p3_jscroll.setViewportView(p3_info);
//		p3_jscroll.setBounds(10,20,740,280);
//		panel3.add(p3_jscroll);
//
//		//���Panel1
//		JLabel p1_namel=new JLabel("������:");
//		JLabel p1_arrivetimel=new JLabel("����ʱ��:");
//		JLabel p1_runtimel=new JLabel("����ʱ��:");
//		JLabel p1_equipl=new JLabel("�豸:");
//		JLabel p1_statusl=new JLabel("״̬:");
//
//		final JTextField p1_name=new JTextField();
//		final JTextField p1_arrivetime=new JTextField();
//		final JTextField p1_runtime=new JTextField();
//		final JComboBox<String> p1_equip=new JComboBox<>();
//		p1_equip.addItem("0");
//		p1_equip.addItem("1");
//		p1_equip.addItem("2");
//		p1_equip.addItem("3");
//		p1_equip.setFont(font);
//		final JLabel p1_status=new JLabel("wait");
//
//		JButton p1_submit=new JButton("ȷ��");
//		JButton p1_clear=new JButton("���");
//
//		JButton p5_clear=new JButton("��ս�����Ϣ");
//		JButton p5_block=new JButton("����");
//		JButton p5_wakeup=new JButton("����");
//		JButton p5_start=new JButton("��ʼ");
//		JButton p5_remove=new JButton("������ǰ����");
//
//		final JComboBox<String> p5_box=new JComboBox<>();
//		p5_box.addItem("--��ѡ������㷨--");
//		p5_box.addItem("01-FCFS");
//		p5_box.addItem("02-SF");
//		p5_box.addItem("03-HRRN");
//		p5_box.setFont(font);
//
//		p5_box.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				// TODO Auto-generated method stub
//				if(((String)p5_box.getSelectedItem()).equals("--��ѡ������㷨--"))
//				{
//						JOptionPane.showMessageDialog(null, "��ѡ������㷨!", "�����㷨", JOptionPane.INFORMATION_MESSAGE);
//				}else {
//					dp_method=Integer.parseInt(((String)p5_box.getSelectedItem()).substring(1, 2));
//				}
//
//			}
//		});
//		panel5.add(p5_box);
//		//����
//		p5_remove.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				MyProcess osp=new MyProcess(p1_name.getText()
//						 ,Integer.parseInt(p1_arrivetime.getText())
//						 ,Integer.parseInt(p1_runtime.getText())
//						 ,Integer.parseInt((String)p1_equip.getSelectedItem())
//						 ,p1_status.getText());
//				int index=MyQueue.ready.indexOf(osp);
//
//				if(index!=-1)
//				{
//					osp.setStatus("remove");
//					MyQueue.ready.get(index).setStatus("remove");
//
//					//�Ӿ�������ɾ������
//					MyQueue.process.clear();
//					for (int i = 0; i < MyQueue.ready.size(); i++) {
//						MyQueue.process.add(MyQueue.ready.get(i));
//					}
//					MyQueue.ready.clear();
//
//					for (int i = 0; i < MyQueue.process.size(); i++) {
//						if(!MyQueue.process.get(i).equals(osp))
//						{
//							MyQueue.ready.add(MyQueue.process.get(i));
//						}
//					}
//					MyQueue.process.clear();
//
//					JOptionPane.showMessageDialog(null, "�����ɹ�", "�����ɹ�", JOptionPane.INFORMATION_MESSAGE);
//				}else
//				{
//					index=MyQueue.block.indexOf(osp);
//					if(index!=-1)
//					{
//						osp.setStatus("remove");
//						MyQueue.block.get(index).setStatus("remove");
//
//						//����������ɾ������
//						MyQueue.process.clear();
//						for (int i = 0; i < MyQueue.block.size(); i++) {
//							MyQueue.process.add(MyQueue.block.get(i));
//						}
//						MyQueue.block.clear();
//
//						for (int i = 0; i < MyQueue.process.size(); i++) {
//							if(!MyQueue.process.get(i).equals(osp))
//							{
//								MyQueue.block.add(MyQueue.process.get(i));
//							}
//						}
//						MyQueue.process.clear();
//
//						JOptionPane.showMessageDialog(null, "�����ɹ�", "�����ɹ�", JOptionPane.INFORMATION_MESSAGE);
//
//					}else {
//						JOptionPane.showMessageDialog(null, "����/�������о�û�е�ǰ����", "����ʧ��", JOptionPane.ERROR_MESSAGE);
//					}
//				}
//			}
//		});
//
//		//����
//		p5_wakeup.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				MyProcess osp=new MyProcess(p1_name.getText()
//						 ,Integer.parseInt(p1_arrivetime.getText())
//						 ,Integer.parseInt(p1_runtime.getText())
//						 ,Integer.parseInt((String)p1_equip.getSelectedItem())
//						 ,p1_status.getText());
//				int index=MyQueue.block.indexOf(osp);
//				if(index!=-1)
//				{
//
//					osp.setStatus("wait");
//					MyQueue.block.get(index).setStatus("wait");
//					MyQueue.block.get(index).setFlag(true);
//					MyQueue.ready.add(MyQueue.block.get(index));//��ӵ����ж���
//					FCFS.sort();
//
//					//����������ɾ������
//					MyQueue.process.clear();
//					for (int i = 0; i < MyQueue.block.size(); i++) {
//						MyQueue.process.add(MyQueue.block.get(i));
//					}
//					MyQueue.block.clear();
//
//					for (int i = 0; i < MyQueue.process.size(); i++) {
//
//						if(!MyQueue.process.get(i).equals(osp))
//						{
//							MyQueue.block.add(MyQueue.process.get(i));
//						}
//					}
//					MyQueue.process.clear();
//
//					JOptionPane.showMessageDialog(null, "���ѳɹ�", "���ѳɹ�", JOptionPane.INFORMATION_MESSAGE);
//				}else
//				{
//					JOptionPane.showMessageDialog(null, "��������û�е�ǰ����", "����ʧ��", JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		});
//		//����
//		p5_block.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				MyProcess osp=new MyProcess(p1_name.getText()
//						 ,Integer.parseInt(p1_arrivetime.getText())
//						 ,Integer.parseInt(p1_runtime.getText())
//						 ,Integer.parseInt((String)p1_equip.getSelectedItem())
//						 ,p1_status.getText());
//				int index=MyQueue.ready.indexOf(osp);
//
//				if(index!=-1)
//				{
//					osp.setStatus("block");
//					MyQueue.ready.get(index).setStatus("block");
//					MyQueue.ready.get(index).setFlag(true);
//					MyQueue.block.add(MyQueue.ready.get(index));//��ӵ���������
//
//					//�����ж���ɾ������
//					MyQueue.process.clear();
//					for (int i = 0; i < MyQueue.ready.size(); i++) {
//						MyQueue.process.add(MyQueue.ready.get(i));
//					}
//					MyQueue.ready.clear();
//
//					for (int i = 0; i < MyQueue.process.size(); i++) {
//						if(!MyQueue.process.get(i).equals(osp))
//						{
//							MyQueue.ready.add(MyQueue.process.get(i));
//						}
//					}
//					MyQueue.process.clear();
//
//					JOptionPane.showMessageDialog(null, "�����ɹ�", "�����ɹ�", JOptionPane.INFORMATION_MESSAGE);
//				}else
//				{
//					index=MyQueue.running.indexOf(osp);
//					if(index!=-1)
//					{
//						osp.setStatus("block");
//						MyQueue.running.get(index).setStatus("block");
//						MyQueue.running.get(index).setFlag(true);
//						MyQueue.block.add(MyQueue.running.get(index));//��ӵ���������
//
//						//�����ж���ɾ������
//						MyQueue.process.clear();
//						for (int i = 0; i < MyQueue.running.size(); i++) {
//							MyQueue.process.add(MyQueue.running.get(i));
//						}
//						MyQueue.running.clear();
//
//						for (int i = 0; i < MyQueue.process.size(); i++) {
//							if(!MyQueue.process.get(i).equals(osp))
//							{
//								MyQueue.running.add(MyQueue.process.get(i));
//							}
//						}
//						MyQueue.process.clear();
//					}
//					else {
//
//						JOptionPane.showMessageDialog(null, "���ж���û�е�ǰ����", "����ʧ��", JOptionPane.ERROR_MESSAGE);
//					}
//				}
//			}
//		});
//
//
//		p1_submit.setFont(font);
//		p1_clear.setFont(font);
//
//		p5_clear.setFont(font);
//		p5_start.setFont(font);
//		p5_block.setFont(font);
//		p5_wakeup.setFont(font);
//		p5_remove.setFont(font);
//
//		p1_submit.setBounds(50,240,80,30);
//		p1_clear.setBounds(220,240,80,30);
//
//
//		p5_clear.setBounds(30,20,240,30);
//		p5_block.setBounds(30,60,70,30);
//		p5_wakeup.setBounds(110,60,70,30);
//		p5_start.setBounds(200,60,70,30);
//		p5_box.setBounds(30,100,240,30);
//		p5_remove.setBounds(30,140,240,30);
//
//
//		panel5.add(p5_clear);
//		panel5.add(p5_block);
//		panel5.add(p5_wakeup);
//		panel5.add(p5_remove);
//		panel5.add(p5_start);
//		p5_start.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				switch (dp_method) {
//				case 1: {
//					new Thread(new Runnable() {
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							new FCFS(p3_info);
//						}
//					}).start();
//					break;
//				}
//				case 2: {
//					new Thread(new Runnable() {
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							new SF(p3_info);
//						}
//					}).start();
//					break;
//				}
//				case 3:
//				{
//					new Thread(new Runnable() {
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							new HRRN(p3_info);
//						}
//					}).start();
//					break;
//				}
//				default: {
//					JOptionPane.showMessageDialog(null, "��Ч�ĵ����㷨", "����ʧ��", JOptionPane.ERROR_MESSAGE);
//				}
//				}
//			}
//		});
//
//
//		panel1.setBorder(BorderFactory.createTitledBorder("�����Ϣ"));
//		panel3.setBorder(BorderFactory.createTitledBorder("���̵���"));
//		panel4.setBorder(BorderFactory.createTitledBorder("���̹���"));
//		panel5.setBorder(BorderFactory.createTitledBorder("���̹������"));
//
//		panel1.add(p1_clear);
//		panel1.add(p1_submit);
//
//
//		p5_clear.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				p4_info.setText("");
//				p3_info.setText("");
//			}
//		});
//		p1_submit.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				 if((p1_name.getText().equals(""))||
//						 (p1_arrivetime.getText().equals(""))
//						 ||(p1_runtime.getText().equals(""))
//						 ||(p1_equip.getSelectedItem().equals(""))
//						 ||(p1_status.getText().equals("")))
//				 {
//					 JOptionPane.showMessageDialog(null, "��������Ϣ", "���ʧ��", JOptionPane.INFORMATION_MESSAGE);
//
//				 }
//				 else {
//					 MyProcess  pcb=new MyProcess(p1_name.getText()
//							 ,Integer.parseInt(p1_arrivetime.getText())
//							 ,Integer.parseInt(p1_runtime.getText())
//							 ,Integer.parseInt((String)p1_equip.getSelectedItem())
//							 ,p1_status.getText());
//					 p4_info.append(pcb.toString());//��������Ϣ��ӵ�panel4
//
//					 if(pcb.getStatus().trim().equals("block"))
//					 {
//						// pcb.setArrivetime(pcb.getArrivetime()+99999999);
//						 pcb.setFlag(true);
//						 MyQueue.block.add(pcb);
//					 }
//					 else if(pcb.getArrivetime()!=0)
//					 {
//						 MyQueue.block.add(pcb);
//					 }
//					 else if(pcb.getStatus().trim().equals("wait")){
//						 MyQueue.ready.add(pcb);//��ӵ���������
//					 }else {
//						 JOptionPane.showMessageDialog(null, "����״̬�Ƿ�", "���ʧ��", JOptionPane.INFORMATION_MESSAGE);
//					 }
//				 }
//			}
//
//		});
//
//		p1_clear.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				 p1_name.setText("");
//				 p1_arrivetime.setText("");
//				 p1_runtime.setText("");
//				 p1_equip.setSelectedIndex(0);
//			}
//		});
//
//		p1_namel.setFont(font);
//		p1_arrivetimel.setFont(font);
//		p1_runtimel.setFont(font);
//		p1_equipl.setFont(font);
//		p1_statusl.setFont(font);
//
//		p1_name.setFont(font);
//		p1_arrivetime.setFont(font);
//		p1_runtime.setFont(font);
//		p1_equip.setFont(font);
//		p1_status.setFont(font);
//
//		p1_namel.setBounds(50,40,80,30);
//		p1_arrivetimel.setBounds(50,80,80,30);
//		p1_runtimel.setBounds(50,120,80,30);
//		p1_equipl.setBounds(50,160,80,30);
//		p1_statusl.setBounds(50,200,80,30);
//
//		p1_name.setBounds(150,40,150,30);
//		p1_arrivetime.setBounds(150,80,150,30);
//		p1_runtime.setBounds(150,120,150,30);
//		p1_equip.setBounds(150,160,150,30);
//		p1_status.setBounds(150,200,150,30);
//
//		panel1.add(p1_namel);
//		panel1.add(p1_arrivetimel);
//		panel1.add(p1_runtimel);
//		panel1.add(p1_equipl);
//		panel1.add(p1_statusl);
//
//		panel1.add(p1_name);
//		panel1.add(p1_arrivetime);
//		panel1.add(p1_runtime);
//		panel1.add(p1_equip);
//		panel1.add(p1_status);
//
//		panel1.add(p1_submit);
//		panel1.add(p1_clear);
//		panel1.add(panel5);
//
//
//		co.add(panel1);
//		co.add(panel2);
//		panel2.add(panel3);
//		panel2.add(panel4);
//
//		setVisible(true);
//	}
//	public static void main(String[] args) {
//		new ProcessFrame();
//	}
//
//}
