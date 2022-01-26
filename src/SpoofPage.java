import org.jnetpcap.PcapIf;
import javax.swing.*;
import java.awt.*;

public class SpoofPage
          {
    JFrame frame = new JFrame();
    JButton myButton1 = new JButton("Run");
    JButton myButton2 = new JButton("Stop");
    PcapIf device;
    StringBuilder errorBuffer = new StringBuilder();
    public JTextArea textArea = new JTextArea ();

    SpoofPage(PcapIf finalJ){
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().setLayout(new FlowLayout());
        this.device = finalJ;
        frame.setTitle("Network Packet Sniffer");
        myButton1.setBounds(400,400,800,70);
       myButton1.setFocusable(false);
       myButton1.setBackground(Color.black);
       myButton1.setForeground(Color.white);
       myButton1.setSize(40,40);
        myButton1.setPreferredSize(new Dimension(70, 40));
        myButton2.setPreferredSize(new Dimension(70, 40));

        myButton2.setSize(40,40);
       // myButton1.setFocusable(true);
        myButton2.setBounds(400,400,800,70);
        myButton2.setFocusable(false);
        myButton2.setBackground(Color.CYAN);
        myButton2.setForeground(Color.orange);

        //myButton2.setFocusable(true);
        myButton2.setVisible(false);
        Packet p = new Packet(device,errorBuffer,textArea);
        myButton1.addActionListener(e -> {myButton1.setVisible(false);
            myButton2.setVisible(true);if(!p.isAlive()){p.start();}});
        myButton2.addActionListener(e -> {myButton1.setVisible(true);
            myButton2.setVisible(false);
            try {
                p.sleep(5000);
                p.terminate();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.gray);
        frame.add(panel2, BorderLayout.SOUTH);
        panel2.setLayout(new FlowLayout());
        frame.getContentPane().add(scrollableTextArea,BorderLayout.CENTER);
        panel2.add(myButton1);
        panel2.add(myButton2);
    }

    SpoofPage(StringBuilder error){
        errorBuffer.append(error);
    }

}