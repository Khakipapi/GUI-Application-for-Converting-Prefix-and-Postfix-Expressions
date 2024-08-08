/*
CMSC 350 Data Structures and Analysis
Project 1
Jose Reyes
UMGC
October 30, 2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main   {

    private JButton preToPost1 = new JButton("Prefix to Postfix");
    private JButton postToPre1 = new JButton("Postfix to Prefix");
    private final JTextField input = new JTextField(20), output = new JTextField(20);

    private static PrePost prePost = new PrePost();
    private static PostPre postPre = new PostPre();

    public Main() {
        JLabel expression = new JLabel("Enter Expression");
        JLabel result = new JLabel("Result");

        JPanel redPanel = new JPanel();
        JPanel bluePanel = new JPanel();
        JPanel greenPanel = new JPanel();

        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setTitle("Expression Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(3, 1));

        redPanel.add(expression);
        redPanel.add(input);
        frame.add(redPanel);

        bluePanel.setLayout(new FlowLayout());
        bluePanel.add(preToPost1);
        preToPost1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //try {
                try {
                    output.setText(prePost.prefixToPostfix(input.getText()));
                } catch (SyntaxError e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);

            } catch (java.util.EmptyStackException e) {
                    JFrame err1 = new JFrame();
                    JOptionPane.showMessageDialog(err1, "'Popped' and empty stack, check equation for accuracy", "Alert", JOptionPane.WARNING_MESSAGE);
                } catch (StackNotEmpty e) {
                    JFrame err2 = new JFrame();
                    JOptionPane.showMessageDialog(err2, "Incorrect Format: Items left in Stack!", "ALERT", JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {
                    JFrame err3 = new JFrame();
                    JOptionPane.showMessageDialog(err3, e, "System Generated", JOptionPane.WARNING_MESSAGE);
                }

            }
    });

        bluePanel.add(postToPre1);
        postToPre1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {

                    output.setText(postPre.postfixToPrefix(input.getText()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SyntaxError e) {
                    JFrame err1 = new JFrame();
                    JOptionPane.showMessageDialog(err1, "Error: Stack is not empty. Please check expression.", "Alert", JOptionPane.WARNING_MESSAGE);


            }catch (java.util.EmptyStackException e) {
                    JFrame err1 = new JFrame();
                    JOptionPane.showMessageDialog(err1, "'Popped' an empty stack, check equation for accuracy", "Alert", JOptionPane.WARNING_MESSAGE);
                } catch (StackNotEmpty e) {
                    JFrame err2 = new JFrame();
                    JOptionPane.showMessageDialog(err2, "Items left in Stack! Check your equation format.", "ALERT", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

                frame.add(bluePanel);
                greenPanel.add(result);
                greenPanel.add(output);
                output.setEditable(false);
                frame.add(greenPanel);
                frame.getContentPane().setBackground(Color.LIGHT_GRAY);
                frame.setVisible(true);

            }

            public static void main(String[] args) {
                new Main();
            }
        }

