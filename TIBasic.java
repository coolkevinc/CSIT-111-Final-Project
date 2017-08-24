import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
public class TIBasic extends JFrame implements ActionListener{
	private JPanel contentPane;
	/**
	 * Create the buttons and sets keyboard to it
	 * The keys for adding, subtracting, multiplying, or dividing and getting the answer will count as a character, even if not printed, so the length is less than 9.
	 * The last character in the text field will be for adding, subtracting, multiplying, dividing, and getting the answer only.
	 * Once either the add, subtract, multiply, or divide is pressed on the keyboard, num2 is temporary set to 0, until another number is typed in.
	 * The negate button will be the escape key, the answer button will be the equals or enter key, the add button will be the shift and equals key, 
		   the subtract button will be the - key, the multiply button will be the shift and 8 key, the divide key will be the / key.
		   the clear button will be the delete key, the clear entry button will be the backspace key, and the pi button will be the p key.
	 * The clear entry (CE) button will clear the current number and the clear button will clear both num1 and num2.
	 * It is required to have 0 typed in to have decimal numbers greater than 0 and less than -1 to work.
	 */
	private static KeyAdapter key =new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
			if(textField.getText().length() < 8 || textField.getText().length() >= 8){
				char numdot = e.getKeyChar();
				if(Character.isDigit(numdot) && !textField.getText().contains(".")){
					if(textField.getText().length() < 8){ //Only accepts the numbers if the length is less than 8
						if(Double.parseDouble(String.valueOf(textField.getText())) != ans || Double.parseDouble(String.valueOf(textField.getText())) == 0){
							if(!textField.getText().startsWith("0")){
								textField.setText(textField.getText() + e.getKeyChar());
							}else if(textField.getText().startsWith("0")){
								textField.setText("" + e.getKeyChar());
							}else{
								textField.setText("" + e.getKeyChar());
							}
						}
					}
				}else if(Character.isDigit(numdot) || numdot == KeyEvent.VK_PERIOD){
					if(Double.parseDouble(String.valueOf(textField.getText())) != ans || Double.parseDouble(String.valueOf(textField.getText())) == 0){
						if(textField.getText().length() < 8){
							if(textField.getText().startsWith("0.")){
								textField.setText(textField.getText() + e.getKeyChar());
							}else{
								textField.setText(textField.getText()+e.getKeyChar());
							}
						}
					}
				}else if(e.getKeyChar() == KeyEvent.VK_P){
					if(textField.getText().equals("0")){
						DecimalFormat pi = new DecimalFormat("###.#########");
						textField.setText("" + pi.format(Math.PI));
					}
				}else if(e.getKeyChar() == KeyEvent.VK_ESCAPE){
					if(Double.parseDouble(String.valueOf(textField.getText())) != 0){
						negate = (Double.parseDouble(String.valueOf(textField.getText())));
						negate *= -1;
					}else{
						negate = 0;
					}
					if(String.valueOf(negate).endsWith(".0")){
						DecimalFormat tni = new DecimalFormat("###");
						textField.setText(String.valueOf(tni.format(negate)));
					}else{
						textField.setText(String.valueOf(negate));
					}
				}else if(e.getKeyChar() == '+'){
					if(e.isShiftDown()){
						addbtn.doClick();
						textField.setText("0");
					}
				}else if(e.getKeyChar() == '*'){
					if(e.isShiftDown()){
						multiplybtn.doClick();
						textField.setText("0");
					}
				}else if(e.getKeyChar() == '-'){
					subtractbtn.doClick();
					textField.setText("0");
				}else if(e.getKeyChar() == '/'){
					dividebtn.doClick();
					textField.setText("0");
				}else if(e.getKeyChar() == KeyEvent.VK_EQUALS || e.getKeyChar() == KeyEvent.VK_ENTER){
					num2 = Double.parseDouble(String.valueOf(textField.getText()));
					textField.setText(textField.getText());
					if(addClick == 1){
						ans = num1 + num2;
						if(String.valueOf(ans).endsWith(".0")){
							textField.setText(String.valueOf(Math.round(ans)));
						}else{
							textField.setText(String.valueOf(ans));
						}
						addClick = 0;
					}
					if(multiplyClick == 1){
						ans = num1 * num2;
						if(String.valueOf(ans).endsWith(".0")){
							textField.setText(String.valueOf(Math.round(ans)));
						}else{
							textField.setText(String.valueOf(ans));
						}
						multiplyClick = 0;
					}
					if(subtractClick == 1){
						ans = num1 - num2;
						if(String.valueOf(ans).endsWith(".0")){
							textField.setText(String.valueOf(Math.round(ans)));
						}else{
							textField.setText(String.valueOf(ans));
						}
						subtractClick = 0;
					}
					if(divideClick == 1){
						ans = num1 / num2;
						if(String.valueOf(ans).endsWith(".0")){
							textField.setText(String.valueOf(Math.round(ans)));
						}else{
							textField.setText(String.valueOf(ans));
						}
						divideClick = 0;
						if(num2 == 0){
							textField.setText("ERROR: DIVIDE BY 0");
						}
					}
				}
			}
			if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
				if(addClick == 0 && multiplyClick == 0 && subtractClick == 0 && divideClick == 0){
					textField.setText("0");
					num1 = 0;
				}else if(addClick == 1 || multiplyClick == 1 || subtractClick == 1 || divideClick == 1){
					textField.setText("0");
					num2 = 0;
				}
			}
			if(e.getKeyChar() == KeyEvent.VK_DELETE){
				textField.setText("0");
				num1 = 0;
				num2 = 0;
				ans = 0;
				addClick = 0;
				subtractClick = 0;
				multiplyClick = 0;
				divideClick = 0;
			}
		}
	};
	private static JButton equalsbtn, addbtn, subtractbtn, multiplybtn, dividebtn, clearbtn, negatebtn, 
	dotbtn, btnCE, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnpi;
	private static JTextField textField;
	private static double num1, num2, ans, negate;
	//Lets the program know if add, subtract, multiply, and divide, and clear buttons are clicked
	private static int addClick = 0, subtractClick = 0, multiplyClick = 0, divideClick = 0, clear = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TIBasic frame = new TIBasic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public TIBasic() {
		/**
		 * Creates the panel for the calculator
		 */
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Calculator");
		setSize(289,260);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setLocation(0, 0);
		contentPane.setSize(494, 71);
		//creates the buttons and set an action and the keyboard it
		btn9 = new JButton("9");// 9 button
		btn9.setBounds(142, 106, 70, 33);
		btn9.setBackground(new Color(176,196,222));
		btn9.addKeyListener(key);
		contentPane.setLayout(null);
		textField = new JTextField("0");
		textField.setBounds(2, 11, 280, 60);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		contentPane.add(btn9);
		btn9.addActionListener(this);
		btn8 = new JButton("8");//8 button
		btn8.setBounds(72, 106, 70, 33);
		btn8.setBackground(new Color(176,196,222));
		btn8.addKeyListener(key);
		contentPane.add(btn8);
		btn8.addActionListener(this);
		btn7 = new JButton("7");//7 button
		btn7.setBounds(2, 106, 70, 33);
		btn7.setBackground(new Color(176,196,222));
		btn7.addKeyListener(key);
		contentPane.add(btn7);
		btn7.addActionListener(this);
		multiplybtn = new JButton("\u00D7");//multiply button
		multiplybtn.setBounds(212, 106, 70, 33);
		multiplybtn.addKeyListener(key);
		btnpi = new JButton("\u03C0");//Pi button
		btnpi.setBounds(2, 75, 70, 33);
		btnpi.setBackground(new Color(176,196,222));
		btnpi.addKeyListener(key);
		contentPane.add(btnpi);
		btnpi.addActionListener(this);
		contentPane.add(multiplybtn);
		multiplybtn.addActionListener(this);
		dividebtn = new JButton("\u00F7");//divide button
		dividebtn.setBounds(212, 75, 70, 33);
		dividebtn.addKeyListener(key);
		btnCE = new JButton("CE");//Clear Entry button - the current number on the text field
		btnCE.setBounds(72, 75, 70, 33);
		btnCE.addKeyListener(key);
		contentPane.add(btnCE);
		btnCE.addActionListener(this);
		btn4 = new JButton("4");//4 button
		btn4.setBounds(2, 137, 70, 33);
		btn4.setBackground(new Color(176,196,222));
		btn4.addKeyListener(key);
		contentPane.add(btn4);
		btn4.addActionListener(this);
		btn5 = new JButton("5");//5 button
		btn5.setBounds(72, 137, 70, 33);
		btn5.setBackground(new Color(176,196,222));
		btn5.addKeyListener(key);
		contentPane.add(btn5);
		btn5.addActionListener(this);
		btn6 = new JButton("6");//6 button
		btn6.setBounds(142, 137, 70, 33);
		btn6.setBackground(new Color(176,196,222));
		btn6.addKeyListener(key);
		contentPane.add(btn6);
		btn6.addActionListener(this);
		negatebtn = new JButton("\u00B1");//negate button - makes number negative or positive
		negatebtn.setBounds(142, 199, 70, 33);
		negatebtn.addKeyListener(key);
		contentPane.add(negatebtn);
		negatebtn.addActionListener(this);
		addbtn = new JButton("+");//add button
		addbtn.setBounds(212, 168, 70, 33);
		addbtn.addKeyListener(key);
		contentPane.add(addbtn);
		addbtn.addActionListener(this);
		clearbtn = new JButton("C");//Clear button - clears both num1 and num2
		clearbtn.setBounds(142, 75, 70, 33);
		clearbtn.addKeyListener(key);
		contentPane.add(clearbtn);
		clearbtn.addActionListener(this);
		btn3 = new JButton("3");//3 button
		btn3.setBounds(142, 168, 70, 33);
		btn3.setBackground(new Color(176,196,222));
		btn3.addKeyListener(key);
		contentPane.add(btn3);
		btn3.addActionListener(this);
		btn2 = new JButton("2");//2 button
		btn2.setBounds(72, 168, 70, 33);
		btn2.setBackground(new Color(176,196,222));
		btn2.addKeyListener(key);
		contentPane.add(btn2);
		btn2.addActionListener(this);
		btn1 = new JButton("1");//1 button
		btn1.setBounds(2, 168, 70, 33);
		btn1.setBackground(new Color(176,196,222));
		btn1.addKeyListener(key);
		contentPane.add(btn1);
		btn1.addActionListener(this);
		btn0 = new JButton("0");//0 button
		btn0.setBounds(2, 199, 70, 33);
		btn0.setBackground(new Color(176,196,222));
		btn0.addKeyListener(key);
		contentPane.add(btn0);
		btn0.addActionListener(this);
		dotbtn = new JButton(".");//decimal point button
		dotbtn.setBounds(72, 199, 70, 33);
		dotbtn.setBackground(new Color(176,196,222));
		dotbtn.addKeyListener(key);
		contentPane.add(dotbtn);
		dotbtn.addActionListener(this);
		equalsbtn = new JButton("=");//equals button - click for answer
		equalsbtn.setBounds(212, 199, 70, 33);
		equalsbtn.addKeyListener(key);
		contentPane.add(equalsbtn);
		equalsbtn.addActionListener(this);
		contentPane.add(dividebtn);
		dividebtn.addActionListener(this);
		subtractbtn = new JButton("\u2212");//subtract button
		subtractbtn.setBounds(212, 137, 70, 33);
		subtractbtn.addKeyListener(key);
		contentPane.add(subtractbtn);
		subtractbtn.addActionListener(this);
		setContentPane(contentPane);
	}
	/**
	 * When clicking on the buttons an action is called
	 * The equals button will return the answer and change the color of the JPanel
	 * The answer then will be unmodifiable until the add, multiply, subtract, divide, or negate buttons are pressed
	 * Required to have 0 in the text field to have numbers less than 0 and greater than -1 work. For num2, must press 0 to get a number less than 0 and greater than -1.
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btn0){
			if(textField.getText().length() < 8){
				if(clear == 1){
					textField.setText("0");
					clear = 0;
				}else if(ans == 0){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("0");
					}else{
						textField.setText(textField.getText() + "0");
					}
					//if answer is found the button is disabled until the add, multiply, subtract, or divide button is clicked
				}else if(ans != 0 && ans != num1 + num2 && ans != num1 - num2 && ans != num1 * num2 && ans != num1 / num2 && ans != Math.sqrt(num1)){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("0");
					}else{
						textField.setText(textField.getText() + "0");
					}
				}
			}
		}
		if(arg0.getSource() == btn1){
			if(textField.getText().length() < 8){
				if(clear == 1){
					textField.setText("1");
					clear = 0;
				}else if(ans == 0){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("1");
					}else{
						textField.setText(textField.getText() + "1");
					}
					//if answer is found the button is disabled until the add, multiply, subtract, or divide button is clicked
				}else if(ans != 0 && ans != num1 + num2 && ans != num1 - num2 && ans != num1 * num2 && ans != num1 / num2 && ans != Math.sqrt(num1)){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("1");
					}else{
						textField.setText(textField.getText() + "1");
					}
				}
			}
		}
		if(arg0.getSource() == btn2){
			if(textField.getText().length() < 8){
				if(clear == 1){
					textField.setText("2");
					clear = 0;
				}else if(ans == 0){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("2");
					}else{
						textField.setText(textField.getText() + "2");
					}
					//if answer is found the button is disabled until the add, multiply, subtract, or divide button is clicked
				}else if(ans != 0 && ans != num1 + num2 && ans != num1 - num2 && ans != num1 * num2 && ans != num1 / num2 && ans != Math.sqrt(num1)){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("2");
					}else{
						textField.setText(textField.getText() + "2");
					}
				}
			}
		}
		if(arg0.getSource() == btn3){
			if(textField.getText().length() < 8){
				if(clear == 1){
					textField.setText("3");
					clear = 0;
				}else if(ans == 0){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("3");
					}else{
						textField.setText(textField.getText() + "3");
					}
					//if answer is found the button is disabled until the add, multiply, subtract, or divide button is clicked
				}else if(ans != 0 && ans != num1 + num2 && ans != num1 - num2 && ans != num1 * num2 && ans != num1 / num2){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("3");
					}else{
						textField.setText(textField.getText() + "3");
					}
				}
			}
		}
		if(arg0.getSource() == btn4){
			if(textField.getText().length() < 8){
				if(clear == 1){
					textField.setText("4");
					clear = 0;
				}else if(ans == 0){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("4");
					}else{
						textField.setText(textField.getText() + "4");
					}
					//if answer is found the button is disabled until the add, multiply, subtract, or divide button is clicked
				}else if(ans != 0 && ans != num1 + num2 && ans != num1 - num2 && ans != num1 * num2 && ans != num1 / num2){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("4");
					}else{
						textField.setText(textField.getText() + "4");
					}
				}
			}
		}
		if(arg0.getSource() == btn5){
			if(textField.getText().length() < 8){
				if(clear == 1){
					textField.setText("5");
					clear = 0;
				}else if(ans == 0){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("5");
					}else{
						textField.setText(textField.getText() + "5");
					}
					//if answer is found the button is disabled until the add, multiply, subtract, or divide button is clicked
				}else if(ans != 0 && ans != num1 + num2 && ans != num1 - num2 && ans != num1 * num2 && ans != num1 / num2){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("5");
					}else{
						textField.setText(textField.getText() + "5");
					}
				}
			}
		}
		if(arg0.getSource() == btn6){
			if(textField.getText().length() < 8){
				if(clear == 1){
					textField.setText("6");
					clear = 0;
				}else if(ans == 0){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("6");
					}else{
						textField.setText(textField.getText() + "6");
					}
					//if answer is found the button is disabled until the add, multiply, subtract, or divide button is clicked
				}else if(ans != 0 && ans != num1 + num2 && ans != num1 - num2 && ans != num1 * num2 && ans != num1 / num2){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("6");
					}else{
						textField.setText(textField.getText() + "6");
					}
				}
			}
		}
		if(arg0.getSource() == btn7){
			if(textField.getText().length() < 8){
				if(clear == 1){
					textField.setText("7");
					clear = 0;
				}else if(ans == 0){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("7");
					}else{
						textField.setText(textField.getText() + "7");
					}
					//if answer is found the button is disabled until the add, multiply, subtract, or divide button is clicked
				}else if(ans != 0 && ans != num1 + num2 && ans != num1 - num2 && ans != num1 * num2 && ans != num1 / num2){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("7");
					}else{
						textField.setText(textField.getText() + "7");
					}
				}
			}
		}
		if(arg0.getSource() == btn8){
			if(textField.getText().length() < 8){
				if(clear == 1){
					textField.setText("8");
					clear = 0;
				}else if(ans == 0){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("8");
					}else{
						textField.setText(textField.getText() + "8");
					}
					//if answer is found the button is disabled until the add, multiply, subtract, or divide button is clicked
				}else if(ans != 0 && ans != num1 + num2 && ans != num1 - num2 && ans != num1 * num2 && ans != num1 / num2){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("8");
					}else{
						textField.setText(textField.getText() + "8");
					}
				}
			}
		}
		if(arg0.getSource() == btn9){
			if(textField.getText().length() < 8){
				if(clear == 1){
					textField.setText("9");
					clear = 0;
				}else if(ans == 0){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("9");
					}else{
						textField.setText(textField.getText() + "9");
					}
					//if answer is found the button is disabled until the add, multiply, subtract, or divide button is clicked
				}else if(ans != 0 && ans != num1 + num2 && ans != num1 - num2 && ans != num1 * num2 && ans != num1 / num2){
					if(textField.getText().startsWith("0") && !textField.getText().contains(".")){
						textField.setText("9");
					}else{
						textField.setText(textField.getText() + "9");
					}
				}
			}
		}
		if(arg0.getSource() == btnpi){
			//disabled when the text field is not equals to 0
			if(textField.getText().equals("0")){
				DecimalFormat pi = new DecimalFormat("###.#########");
				textField.setText("" + pi.format(Math.PI));
			}
		}
		if(arg0.getSource() == negatebtn){
			if(Double.parseDouble(String.valueOf(textField.getText())) != 0){
				negate = (Double.parseDouble(String.valueOf(textField.getText())));
				negate *= -1;
			}else{
				negate = 0;
			}
			if(String.valueOf(negate).endsWith(".0")){
				DecimalFormat tni = new DecimalFormat("###");
				textField.setText(String.valueOf(tni.format(negate)));
			}else{
				textField.setText(String.valueOf(negate));
			}
		}
		if(arg0.getSource() == clearbtn){
			textField.setText("0");
			num1 = 0;
			num2 = 0;
			ans = 0;
			addClick = 0;
			subtractClick = 0;
			multiplyClick = 0;
			divideClick = 0;
		}
		if(arg0.getSource() == dotbtn){
			if(textField.getText().contains(".")){
				textField.setText(textField.getText());
			}else{
				textField.setText(textField.getText() + ".");
			}
		}
		if(arg0.getSource() == addbtn){
			if(textField.getText() != null){
				num1 = Double.parseDouble(String.valueOf(textField.getText()));
				addClick = 1;
				clear = 1;
				textField.setText("0");
			}
		}
		if(arg0.getSource() == subtractbtn){
			if(textField.getText() != null){
				num1 = Double.parseDouble(String.valueOf(textField.getText()));
				subtractClick = 1;
				clear = 1;
				textField.setText("0");
			}
		}
		if(arg0.getSource() == multiplybtn){
			if(textField.getText() != null){
				num1 = Double.parseDouble(String.valueOf(textField.getText()));
				multiplyClick = 1;
				clear = 1;
				textField.setText("0");
			}
		}
		if(arg0.getSource() == dividebtn){
			if(textField.getText() != null){
				num1 = Double.parseDouble(String.valueOf(textField.getText()));
				divideClick = 1;
				clear = 1;
				textField.setText("0");
			}
		}
		if(arg0.getSource() == btnCE){
			if(addClick == 0 && multiplyClick == 0 && subtractClick == 0 && divideClick == 0){
				textField.setText("0");
				num1 = 0;
			}else if(addClick == 1 || multiplyClick == 1 || subtractClick == 1 || divideClick == 1){
				textField.setText("0");
				num2 = 0;
			}
		}
		if(arg0.getSource() == equalsbtn){
			contentPane.setBackground(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
			num2 = Double.parseDouble(String.valueOf(textField.getText()));
			textField.setText(textField.getText());
			if(addClick == 1){
				ans = num1 + num2;
				if(String.valueOf(ans).endsWith(".0")){
					textField.setText(String.valueOf(Math.round(ans)));
				}else{
					textField.setText(String.valueOf(ans));
				}
				addClick = 0;
			}
			if(multiplyClick == 1){
				ans = num1 * num2;
				if(String.valueOf(ans).endsWith(".0")){
					textField.setText(String.valueOf(Math.round(ans)));
				}else{
					textField.setText(String.valueOf(ans));
				}
				multiplyClick = 0;
			}
			if(subtractClick == 1){
				ans = num1 - num2;
				if(String.valueOf(ans).endsWith(".0")){
					textField.setText(String.valueOf(Math.round(ans)));
				}else{
					textField.setText(String.valueOf(ans));
				}
				subtractClick = 0;
			}
			if(divideClick == 1){
				ans = num1 / num2;
				if(String.valueOf(ans).endsWith(".0")){
					textField.setText(String.valueOf(Math.round(ans)));
				}else{
					textField.setText(String.valueOf(ans));
				}
				divideClick = 0;
				if(num2 == 0){
					textField.setText("ERROR: DIVIDE BY 0");
				}
			}
		}	
	}
}