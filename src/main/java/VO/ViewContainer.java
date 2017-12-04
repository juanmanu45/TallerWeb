/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;



/**
 *
 * @author JCBOT
 */
public class ViewContainer extends ViewElement{
    
    public int id;
    public boolean isLandMark;
    public boolean isDefault;
    public boolean isXOR;
    public boolean Protected;
    
    
    public ViewContainer(String name) {
        super(name);
    }

    public ViewContainer() {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public boolean isIsLandMark() {
        return isLandMark;
    }

    public void setIsLandMark(boolean isLandMark) {
        this.isLandMark = isLandMark;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean isIsXOR() {
        return isXOR;
    }

    public void setIsXOR(boolean isXOR) {
        this.isXOR = isXOR;
    }

    public boolean isProtected() {
        return Protected;
    }

    public void setProtected(boolean Protected) {
        this.Protected = Protected;
    }
    
    
    
   
    
    
    
}
