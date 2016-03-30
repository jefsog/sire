/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_recipes;
import java.io.IOException;
import org.jdom.input.*;
import org.jdom.JDOMException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
/**
 *
 * @author Jeff_2
 */
public class RecipeReader extends javax.swing.JFrame {

    /**
     * Creates new form RecipeReader
     */
    public RecipeReader() {
        initComponents();
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
        jTextAreaRecipe = new javax.swing.JTextArea();
        jButtonNext = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaRecipe.setColumns(20);
        jTextAreaRecipe.setRows(5);
        jScrollPane1.setViewportView(jTextAreaRecipe);

        jButtonNext.setText("Next");
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Jianfeng Song");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 304, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    int count=0;
    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        // TODO add your handling code here:
        
        jTextAreaRecipe.setText(getRecipe(count));
        count++;
    }//GEN-LAST:event_jButtonNextActionPerformed

    private String getRecipe(int index){
        index=index%list.size();//get a  index to read recipe from beginning if reach the end of list
        
        String recipe;
        Element e=(Element)list.get(index);//return the recipe refered by the index
        System.out.println(index);
        if(e.getChildren().size()<1){//the children is an empty one, then jump over it;
            index++;
            count++;
            index=index%list.size();
        }
       
        e=(Element)list.get(index);//if the previous is an empty one, read the next child again.
        recipe="Name: "+e.getChildText("title")
                +"\n"+"Contributor: "+e.getChildText("contributor")
                +"\n"+"Date: "+e.getChildText("date")+"\n"+"Ingredient:"+"\n";//read title, contributor, and date
        List ingredients=e.getChildren("ingredient");//read all the ingredients into a list.
        for(int i=0; i<ingredients.size();i++ ){//loop over the ingredient list to read name, amount, unit;
            Element ingredient=(Element)ingredients.get(i);
            recipe+=ingredient.getAttributeValue("name")+" "
                    +ingredient.getAttributeValue("amount")+" "
                    +ingredient.getAttributeValue("unit")+"\n";
        }
        Element preparation=e.getChild("preparation");//read the element of preparation
        //System.out.println(preparation.getChildrenText("step"));
        List steps=preparation.getChildren("step");//return the children of preparation and put them into a list.
        //System.out.println(steps.size());
        for(int j=0; j<steps.size(); j++){//read sept out one by one from the element of preparation.
            Element step=(Element)steps.get(j);
            //System.out.println(step.getChildText("step"));
            recipe+=(j+1)+". "+step.getText()+"\n";
        }
        return recipe;
    }
    
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
            java.util.logging.Logger.getLogger(RecipeReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecipeReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecipeReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecipeReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecipeReader().setVisible(true);
                SAXBuilder builder=new SAXBuilder();
                try{
                    //read recipes.xml from Scott's webpage
                    URL url=new URL("http://munro.humber.ca/~fielder/CPAN202-XML/recipes.xml");
                    Document doc=builder.build(url);//put recipes.xml in a Document type
                    //DocType docType=doc.getDocType();
                    //System.out.println("Doc type:"+docType.getElementName());
                    Element root=doc.getRootElement(); //read root emelment, which is "<book></book>"
                    //System.out.println("Root Element"+root.toString());
                    //System.out.println(root.getChildText("description"));
                    list=root.getChildren();//get the children list, that is book (description,recipe*)
                    //System.out.println("List length: "+list.size());
                    

                }catch(JDOMException e){
                    System.out.println(e.toString());
                }catch(MalformedURLException e){
                    System.out.println(e.toString());
                }catch(IOException e){
                    System.out.println(e.toString());
                }
            }
        });
    }
    private static List list;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaRecipe;
    // End of variables declaration//GEN-END:variables

}
