package frame;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class MainFrame extends JFrame{
    private static final long serialVersionUID=1L;
   // private FileFrame fileIndex;
    public MainFrame(){
        setResizable(false);
        setBounds(250,17,900,700);
        setTitle("Double-Z");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar=new JMenuBar();
        this.setJMenuBar(menuBar);
        menuBar.setBackground(Color.black);
        JMenu aaa = new JMenu("                                                         "
                + "                                                                   "
                + "                                                                     ");
        menuBar.add(aaa);
        aaa.setEnabled(false);
        // 小组成员
        JMenu group = new JMenu("小组成员  ");
        String[] member = new String[] { "张甜甜", "张洪红" };
        for (int i = 0; i < 2; i++) {
            group.add(new JMenuItem(member[i]));
        }
        menuBar.add(group);
        // 帮助信息
        JMenu help = new JMenu("帮助::");
        String[] s = new String[] { "版本：1.1.2", "日期：2019-4" };
        for (int i = 0; i < 2; i++) {
            help.add(new JMenuItem(s[i]));
        }
        menuBar.add(help);
    }
    public static void main(String[] args) {

        JFrame frame=new MainFrame();
        //以windows风格显示
        String plaf="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try{
            UIManager.setLookAndFeel(plaf);
            SwingUtilities.updateComponentTreeUI(frame);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        frame.setVisible(true);
        //图标
        Toolkit tk=Toolkit.getDefaultToolkit();
        Image image=tk.createImage("src/frame/图标.jpg");
        frame.setIconImage(image);
        //时间
        JLabel time=new JLabel();
        time.setBounds(800,12, 200, 20);;
        frame.add(time);
        frame.setLayout(null);
        time.setForeground(Color.white);
        time.setBackground(Color.white);
        time.setFont(new Font("宋体", Font.BOLD, 14));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        time.setText(sdf.format(new Date()));
        //背景
        ImageIcon img=new ImageIcon("src/frame/背景.jpg");//设置的图片路径
        //将背景图片放在标签里
        JLabel imgLabel=new JLabel(img);
        //将背景标签添加到jfram的LayeredPane面板里。
        frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        //设置背景标签的位置
        imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
        JPanel jp = (JPanel)frame.getContentPane();
        jp.setOpaque(false);

        //文件管理图标
        JButton button1=new JButton();
        button1.setBounds(20, 20, 50, 50);
        ImageIcon logo1=new ImageIcon("src/frame/文件(1).jpg");
        button1.setIcon(logo1);
        frame.add(button1);
        button1.setBackground(Color.black);
        JLabel lb1=new JLabel("我的文件");
        lb1.setBounds(17, 72, 100, 20);;
        frame.add(lb1);
        frame.setLayout(null);
        lb1.setForeground(Color.white);
        lb1.setBackground(Color.white);
        lb1.setFont(new Font("宋体", Font.BOLD, 14));
        button1.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {
            //    if(e.getClickCount() == 2)
                  //  FileFrame.f();
            }
            @Override
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
            }});
        //内存图标
        JButton button2=new JButton();
        button2.setBounds(20, 120, 50, 50);
        ImageIcon logo2=new ImageIcon("src/frame/内存.jpg");
        button2.setIcon(logo2);
        frame.add(button2);
        button2.setBackground(Color.black);
        JLabel lb2=new JLabel("我的内存");
        lb2.setBounds(17, 174, 100, 20);;
        frame.add(lb2);
        frame.setLayout(null);
        lb2.setForeground(Color.white);
        lb2.setBackground(Color.white);
        lb2.setFont(new Font("宋体", Font.BOLD, 14));
        button2.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2)
                    new MemoryFrame();
            }
            @Override
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
            }});

        //进程图标
        JButton button3=new JButton();
        button3.setBounds(20, 230, 50, 50);
        ImageIcon logo3=new ImageIcon("src/frame/进程.jpg");
        button3.setIcon(logo3);
        frame.add(button3);
        button3.setBackground(Color.black);
        JLabel lb3=new JLabel("进程调度");
        lb3.setBounds(17, 286, 100, 20);;
        frame.add(lb3);
        frame.setLayout(null);
        lb3.setForeground(Color.white);
        lb3.setBackground(Color.white);
        lb3.setFont(new Font("宋体", Font.BOLD, 14));
        button3.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2)
                    new ProcessFrame();
            }
            @Override
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
            }});
        //关闭图标
        JButton button4=new JButton();
        button4.setBounds(15, 590, 50, 50);
        ImageIcon logo4=new ImageIcon("src/frame/关闭.jpg");
        button4.setIcon(logo4);
        frame.add(button4);
        button4.setBackground(Color.black);
        button4.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1)
                    System.exit(0);
            }
            @Override
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
            }});
    }
}
