package kuis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.lang.Thread;
public class Kuis extends JFrame implements ActionListener


    {
    	private JTextField input = new JTextField();
    	public JTextArea jta = new JTextArea("", 24, 40);
    	private JScrollPane jsp = new JScrollPane (jta);
    	public JTextField output = new JTextField();
    	private JLabel JLinput = new JLabel("No Undian");
    	private JButton simpan = new JButton("Simpan");
    	private JButton undi = new JButton("Undi");
    	private JButton stop = new JButton("Stop");
    	private boolean isRunning = true;
    	public String isi = "";
    	private File file = new File("Data.txt");
    	public RandomThread rt = new RandomThread();
    	public Kuis()


        	{
        		super("Lottery");
        		setSize(400,450);
        		setLocation(300,100);
        		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		JDialog.setDefaultLookAndFeelDecorated(true);
        		JFrame.setDefaultLookAndFeelDecorated(true);
        		setResizable(false);
        		jta.setEditable(false);
        		JPanel atas = new JPanel();
        		atas.setLayout(new BorderLayout());
        		atas.add(JLinput, BorderLayout.WEST);
        		atas.add(input, BorderLayout.CENTER);
        		atas.add(simpan, BorderLayout.EAST);
        		
        		output.setFont(new Font("COMIC SAN MS", Font.BOLD, 20));
        		output.setEditable(false);
        		output.setHorizontalAlignment(JTextField.CENTER);
        		JPanel bawah = new JPanel();
        		bawah.add(undi);
        		bawah.add(stop);
        		JPanel tengah = new JPanel();
        		tengah.setLayout(new BorderLayout());
        		tengah.add(output, BorderLayout.CENTER);
        		JPanel jp = new JPanel();
        		jp.setLayout(new BorderLayout());
        		jp.add(tengah, BorderLayout.NORTH);
        		jp.add(bawah, BorderLayout.SOUTH);
        		getContentPane().setLayout(new BorderLayout() );
        		getContentPane().add(atas, BorderLayout.NORTH);
        		getContentPane().add(jsp, BorderLayout.CENTER);
        		getContentPane().add(jp, BorderLayout.SOUTH);
        		simpan.addActionListener(this);
        		undi.addActionListener(this);
        		stop.addActionListener(this);
        		input.addActionListener(this);
        		setVisible(true);
        	}
        	public void actionPerformed(ActionEvent e)


            	{
            		if ((e.getSource()==simpan) || (e.getSource()==input))
            		{	
            			inputNumber(input.getText());
            			input.setText("");
            		}
            		if (e.getSource()==undi)


                		{
                			rt.start();
                		}
                		if (e.getSource()==stop)


                    		{
                    			rt.stopRunning();
                    			String s = output.getText();
                    			JOptionPane.showMessageDialog(null, "Pemenangnya adalah nomor undian " +s, "Selamat!!!", JOptionPane.INFORMATION_MESSAGE); 
                    		}
                    	}
                    	public void inputNumber(String nomor)


                        	{
                        		try


                            		{
                            			isi = isi +"\n"+nomor;
                            			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                            			byte[]b = (isi).getBytes();
                            			out.write(b,0,b.length);
                            			jta.append(nomor+"\n");
                            			out.close();
                            		}
                                                
                            		catch (Exception a)


                                		{
                                			JOptionPane.showMessageDialog(null, "Kesalahan dalam menginputkan", "ERROR!!!", JOptionPane.WARNING_MESSAGE); 
                                		}
                                	}
                                	public static void main(String[]args)


                                    	{
                                    		new Kuis();
                                    	}
                                    class RandomThread extends Thread


                                        {
                                        	public void run()


                                            	{
                                            		RandomThread myThread = new RandomThread();
                                            		String isi = myThread.getContentFile();
                                            		String []cetak = isi.split("\n");
                                            		int i=0;
                                            		int fileLen = cetak.length;
                                            		while(isRunning=true)


                                                		{
                                                			i=(int)(Math.random()*fileLen-1);
                                                			output.setText(cetak[i]);
                                                			try


                                                    			{
                                                    				Thread.sleep(100);
                                                    			}
                                                    			catch(Exception b)


                                                        			{
                                                        				JOptionPane.showMessageDialog(null, "Kesalahan dalam thread", "ERROR!!!", JOptionPane.WARNING_MESSAGE); 
                                                        			}
                                                        			repaint();	
                                                        		}
                                                        	}
                                                        	public void stopRunning()
                                                        	{		
                                                        		try


                                                            		{
                                                            			Thread.sleep(100);
                                                            		}
                                                            		catch(Exception c)


                                                                		{
                                                                			JOptionPane.showMessageDialog(null, "Kesalahan dalam thread", "ERROR!!!", JOptionPane.WARNING_MESSAGE); 
                                                                		}
                                                                	}
                                                                	public String getContentFile()


                                                                    	{
                                                                    		String temp = "";
                                                                    		try


                                                                        		{
                                                                        			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                                                                        			byte[] b = new byte[bis.available()];
                                                                        			bis.read(b,0,b.length);
                                                                        			temp = new String(b,0,b.length);
                                                                        			bis.close();
                                                                        		}
                                                                        		catch (Exception d)


                                                                            		{
                                                                            			JOptionPane.showMessageDialog(null, "Kesalahan dalam pengambilan data", "ERROR!!!", JOptionPane.WARNING_MESSAGE); 
                                                                            		}
                                                                            		return temp;
                                                                            	}
                                                                        }
                                                                    }