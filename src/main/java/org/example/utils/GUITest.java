package org.example.utils;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUITest {

        public static void main(String[] args) {
            int gap = 10;
            JFrame f = new JFrame("LoL");
            f.setSize(410, 400);
            f.setLocation(200, 200);
            f.setLayout(null);

            JPanel pInput = new JPanel();
            pInput.setBounds(gap, gap, 375, 120);
            pInput.setLayout(new GridLayout(4,3,gap,gap));




            JLabel location = new JLabel("地名:");
            JTextField locationText = new JTextField();


            JLabel type = new JLabel("公司类型:");
            JTextField typeText = new JTextField();


            JLabel name = new JLabel("公司名称:");
            JTextField nameText = new JTextField();


            JLabel bossname = new JLabel("老板名称:");
            JTextField bossnameText = new JTextField();


            JLabel money = new JLabel("金额:");
            JTextField moneyText = new JTextField();


            JLabel product = new JLabel("产品:");
            JTextField productText = new JTextField();


            JLabel measure = new JLabel("价格计量单位:");
            JTextField measureText = new JTextField();


            JButton b = new JButton("生成");

            pInput.add(location);
            pInput.add(locationText);
            pInput.add(type);
            pInput.add(typeText);
            pInput.add(name);
            pInput.add(nameText);
            pInput.add(bossname);
            pInput.add(bossnameText);
            pInput.add(money);
            pInput.add(moneyText);
            pInput.add(product);
            pInput.add(productText);
            pInput.add(measure);
            pInput.add(measureText);

            //文本域
            JTextArea ta = new JTextArea();
            ta.setLineWrap(true);
            b.setBounds(150, 120 + 30, 80, 30);
            ta.setBounds(gap, 150 + 60, 375, 120);



            f.add(pInput);
            f.add(b);
            f.add(ta);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            f.setVisible(true);
            //鼠标监听
            b.addActionListener(new ActionListener(){
                boolean checkedpass = true;
                public void actionPerformed(ActionEvent e){
                    checkedpass = true;
                    checkEmpty(locationText,"地名");
                    checkEmpty(typeText,"公司类型");
                    checkEmpty(nameText,"公司名称");
                    checkEmpty(bossnameText,"老板姓名");
                    checkNumber(moneyText,"金额");
                    checkEmpty(productText,"产品");
                    checkEmpty(measureText,"价格计量单位");

                    String location = locationText.getText();
                    String type = typeText.getText();
                    String companyName = nameText.getText();
                    String bossName = bossnameText.getText();
                    String money = moneyText.getText();
                    String product = productText.getText();
                    String unit = measureText.getText();

                    if(checkedpass){
                        String model = "%s最大%s%s倒闭了，王八蛋老板%s吃喝嫖赌，欠下了%s个亿，"
                                + "带着他的小姨子跑了!我们没有办法，拿着%s抵工资!原价都是一%s多、两%s多、三%s多的%s，"
                                + "现在全部只卖二十块，统统只要二十块!%s王八蛋，你不是人!我们辛辛苦苦给你干了大半年，"
                                + "你不发工资，你还我血汗钱，还我血汗钱!";
                        String result= String.format(model, location,type,companyName,bossName,money,product,unit,unit,unit,product,bossName);
                        ta.setText("");
                        ta.append(result);
                    }

                }

                //检验是否为空
                private void checkEmpty(JTextField tf, String msg){
                    if(!checkedpass)
                        return;
                    String value = tf.getText();
                    if(value.length()==0){
                        JOptionPane.showMessageDialog(f, msg + " 不能为空");
                        tf.grabFocus();
                        checkedpass = false;
                    }
                }
                //检验输入金额必须是整数
                private void checkNumber(JTextField tf, String msg){
                    if(!checkedpass)
                        return;
                    String value = tf.getText();
                    try {
                        Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(f, msg + " 必须是整数");
                        tf.grabFocus();
                        checkedpass = false;
                    }
                }


            });

        }
}
