/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyek.part.pkg2;



import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author ADMIN
 */
public class NewJFrame_master extends javax.swing.JFrame {

    JTable table;
    DefaultTableModel model;
    Object []columnName;
    String[][] data;
    int row, col;
    int rowCount, colCount;
    String expression;
    
    /**
     * Creates new form NewJFrame_master
     */
    public NewJFrame_master() {
        initComponents();
        
        
    }
    
     public static String IntToLetter(int Int) {
    if (Int<27){
      return Character.toString((char)(Int+96));
    } else {
      if (Int%26==0) {
        return IntToLetter((Int/26)-1)+IntToLetter(((Int-1)%26+1));
      } else {
        return IntToLetter(Int/26)+IntToLetter(Int%26);
      }
    }
  }
    
     private void tableSetting(int col, int row){
        
        
        
        data = new String[row][col+1];
        columnName = new Object[col+1];

         for (int i = 0; i <= col; i++) {
             if(i==0){
                 columnName[i]="";
             }
             else if(i<=26){
                 columnName[i] = Character.toString((char) (i+64));
             }
             else if(i>=27){
                 columnName[i] =  IntToLetter(i).toUpperCase();
             }
         }
        
         for (int i = 0; i <row; i++) {
            for (int j = 0; j <=col; j++) {
                data[i][j]="";
            }
        }
         
        for (int i = 0; i <row; i++) {
            for (int j = 0; j <=col; j++) {
                
                if(j==0){
                    data[i][j]=(i+1)+"";
                }
            }
        }
        
       
    }
    
    public void loadTable(){
        
        model = new DefaultTableModel(data, columnName);
        table = new JTable(model);
        

        jTable_bilangan.setModel(model);
        
    }
    
    public void concat( int row1, int col1, int row2, int col2 ){
        String temp1 = jTable_bilangan.getValueAt(row1, col1)+"";
        String temp2 = jTable_bilangan.getValueAt(row2, col2)+"";
        String hasil = temp1 + temp2;
        
        
        for (int j = 1; j <= colCount; j++) {
            for (int i = 0; i < rowCount; i++) {
                if(i==row && j==col){
                    data[i][j]=hasil;
                }
            }
        }
        loadTable();
    }
    
    public static boolean isOperator (char c){
        return c == '+' || c=='-' || c=='*' || c=='/' || c=='^';
    }
    
    public static int postfixHierarchy( char ch){
        
            switch (ch)
            {        
                             
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
            return 2;
      
        case '^':
            return 3;
        }
        
        return -1;
    }
    
    public void average(){
        try {
                
            Stack<String> st = new Stack();
            int startKurung = 0;
            st.push("$");
            for(int i = 0;i<expression.length();i++){
                char temp = expression.charAt(i);
                if(temp=='('){
                    startKurung = i + 1;
                    break;
                }
            }
            while(expression.charAt(startKurung)!=')'){
                char temp = expression.charAt(startKurung);    
                st.push(temp+"");
                startKurung = startKurung + 1;
            }
            
            int row2 = 0;
            String col2;
            row2 = Integer.parseInt(st.pop())-1;
            col2 = st.pop();
            
            st.pop();
            
            int row1 = 0;
            String col1;
            row1 = Integer.parseInt(st.pop())-1;
            col1 = st.pop();
            
            char ke2 = col2.charAt(0);
            char ke1 = col1.charAt(0);
            
            int kolomke1 = ke1;
            kolomke1 = kolomke1-64;
            int kolomke2 = ke2;
            kolomke2 = kolomke2-64;
            Queue<Float> queue = new LinkedList<>();

            
            for (int j = 1; j <=colCount; j++) {
            
            
            
            for (int i = 0; i < rowCount; i++) {
            
                
                if(i>=row1 && i <=row2 && j==kolomke1 ){
                    if(!jTable_bilangan.getValueAt(i, j).toString().equals("")){
                        queue.add(Float.parseFloat(jTable_bilangan.getValueAt(i, j).toString()) );
                    }
                    data[i][j]=jTable_bilangan.getValueAt(i, j).toString();
                }
                
                
            }
            
        }
            Iterator<Float> itr = queue.iterator();
            float hasil =0;
                    
                    
                    while(itr.hasNext()){
                        hasil+=itr.next();
                        
                    }
                    hasil = hasil/(row2-row1+1);
                    data[this.row][this.col]=hasil+"";
               
        loadTable();
}
catch(Exception e) {
        data[this.row][this.col]=0+"";
        loadTable();
}
    }
    
    public void left(){
        Stack<String> st = new Stack();
            int startKurung = 0;
            st.push("$");
            for(int i = 0;i<expression.length();i++){
                char temp = expression.charAt(i);
                if(temp=='('){
                    startKurung = i + 1;
                    break;
                }
            }
            while(expression.charAt(startKurung)!=')'){
                char temp = expression.charAt(startKurung);    
                st.push(temp+"");
                startKurung = startKurung + 1;
            }
            int row = 0;
            String col;
            row = Integer.parseInt(st.pop())-1;
            col = st.pop();
            char kol = col.charAt(0);
            int kolom = kol;
            kolom = kolom-64;
            char left = jTable_bilangan.getValueAt(row, kolom).toString().charAt(0);
            for (int j = 1; j <= colCount; j++) {
                         for (int i = 0; i < rowCount; i++) {
                            if(j==this.col && i==this.row)
                                data[i][j]=left+"";
                        }
                    }
            loadTable();
    }
    
    public void right(){
        Stack<String> st = new Stack();
            int startKurung = 0;
            st.push("$");
            for(int i = 0;i<expression.length();i++){
                char temp = expression.charAt(i);
                if(temp=='('){
                    startKurung = i + 1;
                    break;
                }
            }
            while(expression.charAt(startKurung)!=')'){
                char temp = expression.charAt(startKurung);    
                st.push(temp+"");
                startKurung = startKurung + 1;
            }
            int row = 0;
            String col;
            row = Integer.parseInt(st.pop())-1;
            col = st.pop();
            char kol = col.charAt(0);
            int kolom = kol;
            kolom = kolom-64;
            String executedString =jTable_bilangan.getValueAt(row, kolom).toString();
            char right = executedString.charAt(executedString.length()-1);
            for (int j = 1; j <= colCount; j++) {
                         for (int i = 0; i < rowCount; i++) {
                            if(j==this.col && i==this.row)
                                data[i][j]=right+"";
                        }
                    }
            loadTable();
    }
    
    public void mid(){
        Stack<String> st = new Stack();
            int startKurung = 0;
            st.push("$");
            for(int i = 0;i<expression.length();i++){
                char temp = expression.charAt(i);
                if(temp=='('){
                    startKurung = i + 1;
                    break;
                }
            }
            while(expression.charAt(startKurung)!=')'){
                char temp = expression.charAt(startKurung);    
                st.push(temp+"");
                startKurung = startKurung + 1;
            }
            int row = 0;
            String col;
            row = Integer.parseInt(st.pop())-1;
            col = st.pop();
            char kol = col.charAt(0);
            int kolom = kol;
            kolom = kolom-64;
            String executedString =jTable_bilangan.getValueAt(row, kolom).toString();
            char mid = executedString.charAt(executedString.length()/2);
            for (int j = 1; j <= colCount; j++) {
                         for (int i = 0; i < rowCount; i++) {
                            if(j==this.col && i==this.row)
                                data[i][j]=mid+"";
                        }
                    }
            loadTable();
    }
    
    public void sum(){
        try {
            
                Stack<String> st = new Stack();
            int startKurung = 0;
            st.push("$");
            for(int i = 0;i<expression.length();i++){
                char temp = expression.charAt(i);
                if(temp=='('){
                    startKurung = i + 1;
                    break;
                }
            }
            while(expression.charAt(startKurung)!=')'){
                char temp = expression.charAt(startKurung);    
                st.push(temp+"");
                startKurung = startKurung + 1;
            }
            
            int row2 = 0;
            String col2;
            row2 = Integer.parseInt(st.pop())-1;
            col2 = st.pop();
            
            st.pop();
            
            int row1 = 0;
            String col1;
            row1 = Integer.parseInt(st.pop())-1;
            col1 = st.pop();
            
            char ke2 = col2.charAt(0);
            char ke1 = col1.charAt(0);
            
            int kolomke1 = ke1;
            kolomke1 = kolomke1-64;
            int kolomke2 = ke2;
            kolomke2 = kolomke2-64;
            Queue<Float> queue = new LinkedList<>();
            float hasil=0;

            for (int j = 1; j <=colCount; j++) {
            
             
            for (int i = 0; i < rowCount; i++) {

                
                if(i>=row1 && i <=row2 && j==kolomke1 ){
                    if(!jTable_bilangan.getValueAt(i, j).toString().equals("")){
                        queue.add(Float.parseFloat(jTable_bilangan.getValueAt(i, j).toString()));
                    }
                }   
            }
            }
            Iterator<Float> itr = queue.iterator();
            
                    
                    
                    while(itr.hasNext()){
                        hasil+=itr.next();
                        
                    }
                    
                    data[this.row][this.col]=hasil+"";
                    
              
            loadTable();
            }
            catch(Exception e) {
              data[this.row][this.col]=0+"";
              loadTable();
            }
    }
    
    public void and(){
        Stack<String> stack = new Stack<>();
            ArrayList<String> arr = new ArrayList();
            int startKurung = 0;
            for(int i = 0;i<expression.length();i++){
                char temp = expression.charAt(i);
                if(temp=='('){
                    startKurung = i + 1;
                    break;
                }
            }
            String temp="";
            String temp_operator="";
            boolean allLetter=true;
            boolean kalkulator =true;
            for (int i = startKurung; i < expression.length(); i++) {
                char c=expression.charAt(i);
                if(Character.isLetter(c)){
                    kalkulator=false;
                }
            }
            for (int i = startKurung; i < expression.length(); i++) {
                char c=expression.charAt(i);
                if(Character.isDigit(c)){
                    allLetter=false;
                }
            }
            for (int i = startKurung; i < expression.length(); i++) {
                char c=expression.charAt(i);
                if(Character.isLetterOrDigit(c)){
                    if(!temp_operator.equals("")&&!temp_operator.equals(",")){
                        arr.add(temp_operator);
                    }
                    temp_operator="";
                    temp+= Character.toString(c);
                    
                }
                else  {
                    if(!temp.equals("") && !temp.equals(",")){
                        arr.add(temp);
                    }
                    
                    temp="";
                    temp_operator+=Character.toString(c);
                }
            }
            if(!temp.equals("")){
                arr.add(temp);
            }
            
            for (int i = 0; i < arr.size(); i++) {
                String get = arr.get(i);
                System.out.println(get);
            }
            for (int i = arr.size()-1; i>= 0; i--) {
                String get = arr.get(i);
                stack.push(get);
            }
            float val1 = 0;
            boolean bool1 = false;
            int row1 = 0, kolomke1 = 0;
            String cell1 = stack.pop();
            if(!Character.isDigit(cell1.charAt(0))){
                String col1 = Character.toString(cell1.charAt(0));
                char ke1 = col1.charAt(0);
                kolomke1 = ke1;
                kolomke1 = kolomke1-64;
                System.out.println("kolomke1 " + kolomke1);
                row1 = Integer.parseInt(Character.toString(cell1.charAt(1)))-1;
                        System.out.println("row1 " + row1);
                if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("TRUE")){
                    bool1=true;
                }
                else if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("FALSE")){
                    bool1=false;
                } 
                else{
                    val1= Float.parseFloat((String) jTable_bilangan.getValueAt(row1, kolomke1));
                }
                
            }
            else{
                val1= Float.parseFloat(cell1);
            }
            String cell2, operatorPembanding1 = null;
            System.out.println("val1 "  + val1);
            if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("TRUE")||jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("FALSE")){
                cell2 = stack.pop();
            }
            else {
                operatorPembanding1 = stack.pop();
                System.out.println("operator pembanding1 " + operatorPembanding1);
                cell2 = stack.pop();
            }
            
            
            
            float val2 = 0;
            boolean bool2;
            if(!Character.isDigit(cell2.charAt(0))){
                String col2 = Character.toString(cell2.charAt(0));

            int row2 = Integer.parseInt(Character.toString(cell2.charAt(1)))-1;
            
            char ke2 = col2.charAt(0);
            
            int kolomke2 = ke2;
            kolomke2 = kolomke2-64;
            if(jTable_bilangan.getValueAt(row2, kolomke2).toString().equalsIgnoreCase("TRUE")){
                bool2=true;
                if(bool1&&bool2){
                    data[this.row][this.col]="TRUE";
                    loadTable();
                    return;
                }
                else if(!(bool1&&bool2)){
                    data[this.row][this.col]="FALSE";
                    loadTable();
                    return;
                }
            }
            else if(jTable_bilangan.getValueAt(row2, kolomke2).toString().equalsIgnoreCase("FALSE")){
                bool2=false;
                if(bool1&&bool2){
                    data[this.row][this.col]="TRUE";
                    loadTable();
                    return;
                }
                else{
                    data[this.row][this.col]="FALSE";
                    loadTable();
                    return;
                }
            }
            else if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("TRUE")){
                data[this.row][this.col]="TRUE";
                loadTable();
                return;
            }
            else if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("TRUE")){
                data[this.row][this.col]="FALSE";
                loadTable();
                return;
            }
            else{
                val2 = Float.parseFloat((String) jTable_bilangan.getValueAt(row2, kolomke2));
            }
            
            }
            else{
                val2=Float.parseFloat(cell2);
            }
            
            System.out.println("val2 " + val2);
            float val3;
            String cell3 = stack.pop();
            if(!Character.isDigit(cell3.charAt(0))){
                String col3 = Character.toString(cell3.charAt(0));
                char ke3 = col3.charAt(0);
                int kolomke3 = ke3;
                kolomke3 = kolomke3-64;
                System.out.println("kolomke3 " + kolomke3);
                int row3 = Integer.parseInt(Character.toString(cell3.charAt(1)))-1;
                        System.out.println("row3 " + row3);
                val3= Float.parseFloat((String) jTable_bilangan.getValueAt(row3, kolomke3));
            }
            else{
                val3= Float.parseFloat(cell3);
            }
            System.out.println("val 3 " + val3);
            String operatorPembanding2 = stack.pop();
            System.out.println("operator pembanding2 " + operatorPembanding2);
            String cell4 = stack.pop();
            float val4;
            if(!Character.isDigit(cell4.charAt(0))){
                String col4 = Character.toString(cell4.charAt(0));

            int row4 = Integer.parseInt(Character.toString(cell4.charAt(1)))-1;
            
            char ke4 = col4.charAt(0);
            
            int kolomke4 = ke4;
            kolomke4 = kolomke4-64;
            val4 = Float.parseFloat((String) jTable_bilangan.getValueAt(row4, kolomke4));
            }
            else{
                val4=Float.parseFloat(cell4);
            }
            System.out.println("val4 " + val4);
            boolean result1 = false;
            boolean result2 = false;

            if(operatorPembanding1.equals("=")){
                if(val1==val2){
                    result1=true;
                }
                else{
                    result1=false;
                }
            }
            else if(operatorPembanding1.equals("<>")){
                if(val1==val2){
                    result1=false;
                }
                else{
                    result1=true;
                }
            }
            else if(operatorPembanding1.equals("<")){
                if(val1<val2){
                    result1=true;
                }
                else{
                    result1=false;
                }
            }
            else if(operatorPembanding1.equals(">")){
                if(val1<val2){
                    result1=false;
                }
                else{
                    result1=true;
                }
            }
            else if(operatorPembanding1.equals("<=")){
                if(val1<=val2){
                    result1=true;
                }
                else{
                    result1=false;
                }
            }
            else if(operatorPembanding1.equals(">=")){
                if(val1>=val2){
                    result1=true;
                }
                else{
                    result1=false;
                }
            }
            
            if(operatorPembanding2.equals("=")){
                if(val3==val4){
                    result2=true;
                }
                else{
                    result2=false;
                }
            }
            else if(operatorPembanding2.equals("<>")){
                if(val3==val4){
                    result2=false;
                }
                else{
                    result2=true;
                }
            }
            else if(operatorPembanding2.equals("<")){
                if(val3<val4){
                    result2=true;
                }
                else{
                    result2=false;
                }
            }
            else if(operatorPembanding2.equals(">")){
                if(val3<val4){
                    result2=false;
                }
                else{
                    result2=true;
                }
            }
            else if(operatorPembanding2.equals("<=")){
                if(val3<=val4){
                    result2=true;
                }
                else{
                    result2=false;
                }
            }
            else if(operatorPembanding2.equals(">=")){
                if(val3>=val4){
                    result2=true;
                }
                else{
                    result2=false;
                }
            }
            System.out.println("result 1 " + result1);
            System.out.println("result 2 " + result2);
            if(result1&&result2){
                data[this.row][this.col]="TRUE";
            }
            else if(!(result1&&result2)){
                data[this.row][this.col]="FALSE";
            }
            loadTable();
    }
    
    public void not(){
        Stack<String> stack = new Stack<>();
            ArrayList<String> arr = new ArrayList();
            int startKurung = 0;
            for(int i = 0;i<expression.length();i++){
                char temp = expression.charAt(i);
                if(temp=='('){
                    startKurung = i + 1;
                    break;
                }
            }
            String temp="";
            String temp_operator="";
            boolean allLetter=true;
            boolean kalkulator =true;
            for (int i = startKurung; i < expression.length(); i++) {
                char c=expression.charAt(i);
                if(Character.isLetter(c)){
                    kalkulator=false;
                }
            }
            for (int i = startKurung; i < expression.length(); i++) {
                char c=expression.charAt(i);
                if(Character.isDigit(c)){
                    allLetter=false;
                }
            }
            for (int i = startKurung; i < expression.length(); i++) {
                char c=expression.charAt(i);
                if(Character.isLetterOrDigit(c)){
                    if(!temp_operator.equals("")){
                        arr.add(temp_operator);
                    }
                    temp_operator="";
                    temp+= Character.toString(c);
                    
                }
                else  {
                    if(!temp.equals("")){
                        arr.add(temp);
                    }
                    
                    temp="";
                    temp_operator+=Character.toString(c);
                }
            }
            if(!temp.equals("")){
                arr.add(temp);
            }
            for (int i = 0; i < arr.size(); i++) {
                String get = arr.get(i);
                System.out.println(get);
            }
            if(allLetter){
                for (int i = 0; i < arr.size(); i++) {
                    String get = arr.get(i);
                    if(get.equals("TRUE")){
                        data[this.row][this.col]="FALSE";
                        break;
                    }
                    else{
                        data[this.row][this.col]="TRUE";
                        break;
                    }
                }
                loadTable();
            }
            else if(kalkulator){
                for (int i = arr.size()-1; i>= 0; i--) {
                    String get = arr.get(i);
                    stack.push(get);
                }
                float operand1 = Float.parseFloat(stack.pop());
                
                String operatorPembanding = stack.pop();
                
                float operand2 = Float.parseFloat(stack.pop());
                
                if(operatorPembanding.equals("=")){
                    if(operand1==operand2){
                        data[this.row][this.col]="FALSE";
                    }
                    else{
                        data[this.row][this.col]="TRUE";
                    }
                }
                else if (operatorPembanding.equals("<>")){
                    if(operand1==operand2){
                        data[this.row][this.col]="TRUE";
                    }
                    else{
                        data[this.row][this.col]="FALSE";
                    }
                }
                else if (operatorPembanding.equals("<")){
                    if(operand1<operand2){
                        data[this.row][this.col]="FALSE";
                    }
                    else{
                        data[this.row][this.col]="TRUE";
                    }
                }
                else if (operatorPembanding.equals(">")){
                    if(operand1<operand2){
                        data[this.row][this.col]="TRUE";
                    }
                    else{
                        data[this.row][this.col]="FALSE";
                    }
                }
                else if (operatorPembanding.equals("<=")){
                    if(operand1<=operand2){
                        data[this.row][this.col]="FALSE";
                    }
                    else{
                        data[this.row][this.col]="TRUE";
                    }
                }
                else if (operatorPembanding.equals(">=")){
                    if(operand1>=operand2){
                        data[this.row][this.col]="FALSE";
                    }
                    else{
                        data[this.row][this.col]="TRUE";
                    }
                }
                loadTable();
            }
            else{
                for (int i = arr.size()-1; i>= 0; i--) {
                String get = arr.get(i);
                stack.push(get);
            }
            float val1 = 0;
            boolean bool1 = false;
            String cell1 = stack.pop();
            int row1 = 0,kolomke1 = 0;
            if(!Character.isDigit(cell1.charAt(0))){
                String col1 = Character.toString(cell1.charAt(0));
                char ke1 = col1.charAt(0);
                kolomke1 = ke1;
                kolomke1 = kolomke1-64;
                System.out.println("kolomke1 " + kolomke1);
                row1 = Integer.parseInt(Character.toString(cell1.charAt(1)))-1;
                        System.out.println("row1 " + row1);
                if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("TRUE")){
                    bool1=false;
                    
                }
                else if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("FALSE")){
                    bool1=true;
                    
                }
                else{
                    val1= Float.parseFloat((String) jTable_bilangan.getValueAt(row1, kolomke1));
                }
                
            }
            else{
                val1= Float.parseFloat(cell1);
            }
            
            String operatorPembanding = stack.pop();
            System.out.println("operator pembanding " + operatorPembanding);
            String cell2 = stack.pop();
            float val2 = 0;
            boolean bool2;
            if(!Character.isDigit(cell2.charAt(0))){
                String col2 = Character.toString(cell2.charAt(0));

            int row2 = Integer.parseInt(Character.toString(cell2.charAt(1)))-1;
            
            char ke2 = col2.charAt(0);
            
            int kolomke2 = ke2;
            kolomke2 = kolomke2-64;
            if(jTable_bilangan.getValueAt(row2, kolomke2).toString().equalsIgnoreCase("TRUE")){
                    bool2=false;
                    if(operatorPembanding.equals("=")){
                        if(bool1==bool2){
                            data[this.row][this.col]="FALSE";
                        }
                        else{
                            data[this.row][this.col]="TRUE";
                        }
                    }
                    else if(operatorPembanding.equals("<>")){
                        if(bool1==bool2){
                            data[this.row][this.col]="TRUE";
                        }
                        else{
                            data[this.row][this.col]="FALSE";
                        }
                    }
                    
                }
                else if(jTable_bilangan.getValueAt(row2, kolomke2).toString().equalsIgnoreCase("FALSE")){
                    bool2=true;
                    if(operatorPembanding.equals("=")){
                        if(bool1==bool2){
                            data[this.row][this.col]="FALSE";
                            loadTable();
                            return;
                        }
                        else{
                            data[this.row][this.col]="TRUE";
                            loadTable();
                            return;
                        }
                    }
                    else if(operatorPembanding.equals("<>")){
                        if(bool1==bool2){
                            data[this.row][this.col]="TRUE";
                            loadTable();
                            return;
                        }
                        else{
                            data[this.row][this.col]="FALSE";
                            loadTable();
                            return;
                        }
                    }
                    
                }
                if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("TRUE")){
                    data[this.row][this.col]="FALSE";
                    loadTable();
                    return;
                }
                else if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("FALSE")){
                    data[this.row][this.col]="TRUE";
                    loadTable();
                    return;
                }
                else{
                    val2 = Float.parseFloat((String) jTable_bilangan.getValueAt(row2, kolomke2));
                }
            
            }
            else{
                val2=Float.parseFloat(cell2);
            }
            if(operatorPembanding.equals("=")){
                if(val1==val2){
                    data[this.row][this.col]="FALSE";
                }
                else{
                    data[this.row][this.col]="TRUE";
                }
            }
            else if (operatorPembanding.equals("<>")){
                if(val1==val2){
                    data[this.row][this.col]="TRUE";
                }
                else{
                    data[this.row][this.col]="FALSE";
                }
            }
            else if (operatorPembanding.equals("<")){
                if(val1<val2){
                    data[this.row][this.col]="FALSE";
                }
                else{
                    data[this.row][this.col]="TRUE";
                }
            }
            else if (operatorPembanding.equals(">")){
                if(val1<val2){
                    data[this.row][this.col]="TRUE";
                }
                else{
                    data[this.row][this.col]="FALSE";
                }
            }
            else if (operatorPembanding.equals("<=")){
                if(val1<=val2){
                    data[this.row][this.col]="FALSE";
                }
                else{
                    data[this.row][this.col]="TRUE";
                }
            }
            else if (operatorPembanding.equals(">=")){
                if(val1>=val2){
                    data[this.row][this.col]="FALSE";
                }
                else{
                    data[this.row][this.col]="TRUE";
                }
            }
            loadTable();
            }
    }
    
    public void ekspresiAritmatika(){
        Stack<Character> stack = new Stack<>();
            String postfix="";
            boolean kalkulator=true;
            ArrayList<String> arr = new ArrayList();
            String temp="";
            for (int i = 1; i < expression.length(); i++) {
                char c=expression.charAt(i);
                if(!isOperator(c)&& c!='(' && c!=')'){
                    
                    temp+= Character.toString(c);
                    
                }
                else  {
                    if(!temp.equals("")){
                        arr.add(temp);
                    }
                    
                    temp="";
                    arr.add(Character.toString(c));
                }
            }
            if(!temp.equals("")){
                arr.add(temp);
            }
            
            System.out.println(arr.size());
            for (int i = 1; i < expression.length(); i++) {
                char c=expression.charAt(i);
                if(Character.isLetter(c)){
                    kalkulator =false;
                }
                
            }
            if(!kalkulator){
                for (int i = 0; i < arr.size(); i++) {
                    String get = arr.get(i);
                    System.out.println(get);
                }
                for (int i = 0; i < arr.size(); i++) {
                String get = arr.get(i);
                char  [] c = get.toCharArray();
                float operand;
                    if(!isOperator(get.charAt(0))&& get.charAt(0)!='(' && get.charAt(0)!=')'){
                        if(Character.isLetter(get.charAt(0))){
                            int row = 0;
                    char col;
                    col = get.charAt(0);
                    row = Integer.parseInt(Character.toString((char)(get.charAt(1))))-1;
                    char ke2 = col;
                    int kolom = ke2;
                    kolom = kolom-64;
                    operand = Float.parseFloat(jTable_bilangan.getValueAt(row, kolom).toString());
                    postfix+=operand+" ";
                    }
                        else {
                            operand = Float.parseFloat(get);
                            postfix+= operand+ " ";
                        }
                        }
                else{
                    if(c[0]=='(') {
                    stack.push('(');
                    }
                    else if(c[0]==')'){
                        
                        while(!stack.isEmpty() && stack.peek()!='('){
                        postfix+=stack.pop()+" ";
                        }
                        stack.pop();
                    }
                    else if(isOperator(c[0])) {
                       
                        while(!stack.isEmpty()&& postfixHierarchy(c[0])<postfixHierarchy(stack.peek())){
                            postfix+=stack.pop() + " ";
                        }
                        stack.push(c[0]);
                    }
                }
                    
                
            }
             while(!stack.isEmpty()){
                    postfix+=stack.pop()+ " ";
                }
             
            }
            else if(kalkulator){
               for (int i = 0; i < arr.size(); i++) {
                   char[] c=arr.get(i).toCharArray();
                
                    if(!isOperator(c[0])&& c[0]!='(' && c[0]!=')'){
                    
                       
                    postfix+= arr.get(i)+" ";
                    
                    
                }
                
                
                
                else{
                    if(c[0]=='(') {
                    stack.push('(');
                    }
                    else if(c[0]==')'){
                        
                        while(!stack.isEmpty() && stack.peek()!='('){
                        postfix+=stack.pop()+" ";
                        }
                        stack.pop();
                    }
                    else if(isOperator(c[0])) {
                       
                        while(!stack.isEmpty()&& postfixHierarchy(c[0])<postfixHierarchy(stack.peek())){
                            postfix+=stack.pop() + " ";
                        }
                        stack.push(c[0]);
                    }
                }
                    
                
            
                    
                
            }
             while(!stack.isEmpty()){
                    postfix+=stack.pop()+ " ";
                }
           
            }
              
             
             Stack<Float> calc = new Stack();
            StringTokenizer st = new StringTokenizer(postfix);
     while (st.hasMoreTokens()) {
      String token = st.nextToken();
         System.out.println(token);
    float x = 0;
    float y = 0;
    float r = 0;
    
    if(token.equals("+")){
        x = calc.pop();
        y = calc.pop();
        r = x+y;
        calc.push(r);
    }
     else if(token.equals("-")){
        x = calc.pop();
        y = calc.pop();
        r = x-y;
        calc.push(r);
    }
     else if(token.equals("*")){
        x = calc.pop();
        y = calc.pop();
        r = x*y;
        calc.push(r);
    }
     else if(token.equals("/")){
        x = calc.pop();
        y = calc.pop();
        r = x/y;
        calc.push(r);
    }
    else if(token.equals("^")){
        x = calc.pop();
        y = calc.pop();
        r = (int) Math.pow(x, y);
        calc.push(r);
    }
    else{
       float t = Float.parseFloat(token);
        calc.push(t);
    }
}
 
 float a = calc.pop();
 data[this.row][this.col]=a+"";
            loadTable();
    }
    
    public void or(){
        Stack<String> stack = new Stack<>();
            ArrayList<String> arr = new ArrayList();
            int startKurung = 0;
            for(int i = 0;i<expression.length();i++){
                char temp = expression.charAt(i);
                if(temp=='('){
                    startKurung = i + 1;
                    break;
                }
            }
            String temp="";
            String temp_operator="";
            boolean allLetter=true;
            boolean kalkulator =true;
            for (int i = startKurung; i < expression.length(); i++) {
                char c=expression.charAt(i);
                if(Character.isLetter(c)){
                    kalkulator=false;
                }
            }
            for (int i = startKurung; i < expression.length(); i++) {
                char c=expression.charAt(i);
                if(Character.isDigit(c)){
                    allLetter=false;
                }
            }
            for (int i = startKurung; i < expression.length(); i++) {
                char c=expression.charAt(i);
                if(Character.isLetterOrDigit(c)){
                    if(!temp_operator.equals("")&&!temp_operator.equals(",")){
                        arr.add(temp_operator);
                    }
                    temp_operator="";
                    temp+= Character.toString(c);
                    
                }
                else  {
                    if(!temp.equals("") && !temp.equals(",")){
                        arr.add(temp);
                    }
                    
                    temp="";
                    temp_operator+=Character.toString(c);
                }
            }
            if(!temp.equals("")){
                arr.add(temp);
            }
            
            for (int i = 0; i < arr.size(); i++) {
                String get = arr.get(i);
                System.out.println(get);
            }
            for (int i = arr.size()-1; i>= 0; i--) {
                String get = arr.get(i);
                stack.push(get);
            }
            float val1 = 0;
            boolean bool1 = false;
            int row1 = 0, kolomke1 = 0;
            String cell1 = stack.pop();
            if(!Character.isDigit(cell1.charAt(0))){
                String col1 = Character.toString(cell1.charAt(0));
                char ke1 = col1.charAt(0);
                kolomke1 = ke1;
                kolomke1 = kolomke1-64;
                System.out.println("kolomke1 " + kolomke1);
                row1 = Integer.parseInt(Character.toString(cell1.charAt(1)))-1;
                        System.out.println("row1 " + row1);
                if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("TRUE")){
                    bool1=true;
                }
                else if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("FALSE")){
                    bool1=false;
                } 
                else{
                    val1= Float.parseFloat((String) jTable_bilangan.getValueAt(row1, kolomke1));
                }
                
            }
            else{
                val1= Float.parseFloat(cell1);
            }
            String cell2, operatorPembanding1 = null;
            System.out.println("val1 "  + val1);
            if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("TRUE")||jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("FALSE")){
                cell2 = stack.pop();
            }
            else {
                operatorPembanding1 = stack.pop();
                System.out.println("operator pembanding1 " + operatorPembanding1);
                cell2 = stack.pop();
            }
            
            
            
            float val2 = 0;
            boolean bool2;
            if(!Character.isDigit(cell2.charAt(0))){
                String col2 = Character.toString(cell2.charAt(0));

            int row2 = Integer.parseInt(Character.toString(cell2.charAt(1)))-1;
            
            char ke2 = col2.charAt(0);
            
            int kolomke2 = ke2;
            kolomke2 = kolomke2-64;
            if(jTable_bilangan.getValueAt(row2, kolomke2).toString().equalsIgnoreCase("TRUE")){
                bool2=true;
                if(bool1||bool2){
                    data[this.row][this.col]="TRUE";
                    loadTable();
                    return;
                }
                else if(!(bool1||bool2)){
                    data[this.row][this.col]="FALSE";
                    loadTable();
                    return;
                }
            }
            else if(jTable_bilangan.getValueAt(row2, kolomke2).toString().equalsIgnoreCase("FALSE")){
                bool2=false;
                if(bool1||bool2){
                    data[this.row][this.col]="TRUE";
                    loadTable();
                    return;
                }
                else{
                    data[this.row][this.col]="FALSE";
                    loadTable();
                    return;
                }
            }
            else if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("TRUE")){
                data[this.row][this.col]="TRUE";
                loadTable();
                return;
            }
            else if(jTable_bilangan.getValueAt(row1, kolomke1).toString().equalsIgnoreCase("TRUE")){
                data[this.row][this.col]="FALSE";
                loadTable();
                return;
            }
            else{
                val2 = Float.parseFloat((String) jTable_bilangan.getValueAt(row2, kolomke2));
            }
            
            }
            else{
                val2=Float.parseFloat(cell2);
            }
            
            System.out.println("val2 " + val2);
            float val3;
            String cell3 = stack.pop();
            if(!Character.isDigit(cell3.charAt(0))){
                String col3 = Character.toString(cell3.charAt(0));
                char ke3 = col3.charAt(0);
                int kolomke3 = ke3;
                kolomke3 = kolomke3-64;
                System.out.println("kolomke3 " + kolomke3);
                int row3 = Integer.parseInt(Character.toString(cell3.charAt(1)))-1;
                        System.out.println("row3 " + row3);
                val3= Float.parseFloat((String) jTable_bilangan.getValueAt(row3, kolomke3));
            }
            else{
                val3= Float.parseFloat(cell3);
            }
            System.out.println("val 3 " + val3);
            String operatorPembanding2 = stack.pop();
            System.out.println("operator pembanding2 " + operatorPembanding2);
            String cell4 = stack.pop();
            float val4;
            if(!Character.isDigit(cell4.charAt(0))){
                String col4 = Character.toString(cell4.charAt(0));

            int row4 = Integer.parseInt(Character.toString(cell4.charAt(1)))-1;
            
            char ke4 = col4.charAt(0);
            
            int kolomke4 = ke4;
            kolomke4 = kolomke4-64;
            val4 = Float.parseFloat((String) jTable_bilangan.getValueAt(row4, kolomke4));
            }
            else{
                val4=Float.parseFloat(cell4);
            }
            System.out.println("val4 " + val4);
            boolean result1 = false;
            boolean result2 = false;

            if(operatorPembanding1.equals("=")){
                if(val1==val2){
                    result1=true;
                }
                else{
                    result1=false;
                }
            }
            else if(operatorPembanding1.equals("<>")){
                if(val1==val2){
                    result1=false;
                }
                else{
                    result1=true;
                }
            }
            else if(operatorPembanding1.equals("<")){
                if(val1<val2){
                    result1=true;
                }
                else{
                    result1=false;
                }
            }
            else if(operatorPembanding1.equals(">")){
                if(val1<val2){
                    result1=false;
                }
                else{
                    result1=true;
                }
            }
            else if(operatorPembanding1.equals("<=")){
                if(val1<=val2){
                    result1=true;
                }
                else{
                    result1=false;
                }
            }
            else if(operatorPembanding1.equals(">=")){
                if(val1>=val2){
                    result1=true;
                }
                else{
                    result1=false;
                }
            }
            
            if(operatorPembanding2.equals("=")){
                if(val3==val4){
                    result2=true;
                }
                else{
                    result2=false;
                }
            }
            else if(operatorPembanding2.equals("<>")){
                if(val3==val4){
                    result2=false;
                }
                else{
                    result2=true;
                }
            }
            else if(operatorPembanding2.equals("<")){
                if(val3<val4){
                    result2=true;
                }
                else{
                    result2=false;
                }
            }
            else if(operatorPembanding2.equals(">")){
                if(val3<val4){
                    result2=false;
                }
                else{
                    result2=true;
                }
            }
            else if(operatorPembanding2.equals("<=")){
                if(val3<=val4){
                    result2=true;
                }
                else{
                    result2=false;
                }
            }
            else if(operatorPembanding2.equals(">=")){
                if(val3>=val4){
                    result2=true;
                }
                else{
                    result2=false;
                }
            }
            System.out.println("result 1 " + result1);
            System.out.println("result 2 " + result2);
            if(result1||result2){
                data[this.row][this.col]="TRUE";
            }
            else if(!(result1||result2)){
                data[this.row][this.col]="FALSE";
            }
            loadTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_bilangan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSpinner_col = new javax.swing.JSpinner();
        jSpinner_row = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jButton_apply = new javax.swing.JButton();
        jButton_loadTable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable_bilangan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_bilangan.setToolTipText("");
        jTable_bilangan.setRowSelectionAllowed(false);
        jTable_bilangan.getTableHeader().setReorderingAllowed(false);
        jTable_bilangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_bilanganMouseClicked(evt);
            }
        });
        jTable_bilangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable_bilanganKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_bilangan);

        jLabel1.setText("Ukuran : ");

        jLabel2.setText("x");

        jButton_apply.setText("Apply");
        jButton_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_applyActionPerformed(evt);
            }
        });

        jButton_loadTable.setText("LOAD TABLE");
        jButton_loadTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_loadTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1528, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_apply)
                        .addGap(208, 208, 208)
                        .addComponent(jButton_loadTable))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jSpinner_col, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner_row, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jSpinner_col, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner_row, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton_apply))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jButton_loadTable)))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_applyActionPerformed
        // TODO add your handling code here:
        this.colCount = Integer.parseInt(jSpinner_col.getValue().toString());
        this.rowCount = Integer.parseInt(jSpinner_row.getValue().toString());
        tableSetting(colCount, rowCount);
        loadTable();
    }//GEN-LAST:event_jButton_applyActionPerformed

    private void jTable_bilanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_bilanganMouseClicked
        // TODO add your handling code here:
        if(row!=-1 && col!=-1){
            String value = (String) jTable_bilangan.getValueAt(row, col);
        
            if(!value.isEmpty()){
                 data[row][col]= (String) jTable_bilangan.getValueAt(row, col);
                 
            }
        }
        
        for (int j = 1; j <=colCount; j++) {
            for (int i = 0; i < rowCount; i++) {
                data[i][j]= jTable_bilangan.getValueAt(i, j).toString();
            }
        }
//        System.out.println(row + " "+col);
    }//GEN-LAST:event_jTable_bilanganMouseClicked

    private void jTable_bilanganKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_bilanganKeyReleased
        // TODO add your handling code here:
        this.row = jTable_bilangan.getSelectedRow();
        this.col = jTable_bilangan.getSelectedColumn();
        String sintax = "";
        expression = (String) jTable_bilangan.getValueAt(row, col);
        char idx_0 = 0;
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if(!expression.isEmpty()){
                idx_0 = expression.charAt(0);
            }
            
            Stack<String> st = new Stack();
            st.push("$");
            for(int i = 0;i<expression.length();i++){
                char temp = expression.charAt(i);
                if(temp!='('){
                    st.push(temp+"");
                }else{
                    break;
                }
            }
            while(!st.peek().equals("$")){
                String param = st.pop();
                sintax = param + sintax;
            }
            //System.out.println("sintax : " + sintax);
        
        }
        if(sintax.equalsIgnoreCase("=concat")){
            Stack<String> st = new Stack();
            int startKurung = 0;
            st.push("$");
            for(int i = 0;i<expression.length();i++){
                char temp = expression.charAt(i);
                if(temp=='('){
                    startKurung = i + 1;
                    break;
                }
            }
            while(expression.charAt(startKurung)!=')'){
                char temp = expression.charAt(startKurung);    
                st.push(temp+"");
                startKurung = startKurung + 1;
            }
            
            int row2 = 0;
            String col2;
            row2 = Integer.parseInt(st.pop())-1;
            col2 = st.pop();
            
            st.pop();
            
            int row1 = 0;
            String col1;
            row1 = Integer.parseInt(st.pop())-1;
            col1 = st.pop();
            
            char ke2 = col2.charAt(0);
            char ke1 = col1.charAt(0);
            
            int kolomke1 = ke1;
            kolomke1 = kolomke1-64;
            int kolomke2 = ke2;
            kolomke2 = kolomke2-64;

            concat(row1,kolomke1,row2,kolomke2);
            }
        else if (sintax.equalsIgnoreCase("=left")){
            left();
            }
        else if (sintax.equalsIgnoreCase("=right")){
            right();
            }
        else if(sintax.equalsIgnoreCase("=mid")){
            mid();
            }
        else if (sintax.equalsIgnoreCase("=sum")){      
            sum();
        }
        else if( sintax.equalsIgnoreCase("=average")){
            average();
            
        }
        else if( sintax.equalsIgnoreCase("=if")){
            
        }
        else if ( sintax.equalsIgnoreCase("=and")){
            and();
        }
        else if ( sintax.equalsIgnoreCase("=or")){
            or();
        }
        else if ( sintax.equalsIgnoreCase("=not")){
            not();
        }
        else if (idx_0=='='){
            ekspresiAritmatika();
            }

        for (int j = 1; j <=colCount; j++) {
            for (int i = 0; i < rowCount; i++) {
                data[i][j]=jTable_bilangan.getValueAt(i, j).toString();
            }
        }
    }//GEN-LAST:event_jTable_bilanganKeyReleased

    private void jButton_loadTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_loadTableActionPerformed
        // TODO add your handling code here:
        loadTable();
    }//GEN-LAST:event_jButton_loadTableActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame_master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame_master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame_master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame_master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame_master().setVisible(true);
            }
        });
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_apply;
    private javax.swing.JButton jButton_loadTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner_col;
    private javax.swing.JSpinner jSpinner_row;
    private javax.swing.JTable jTable_bilangan;
    // End of variables declaration//GEN-END:variables
}
