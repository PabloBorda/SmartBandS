/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.model.tags.android;

/**
 *
 * @author Pablo Tomas Borda Di Berardino
 */
public class Tag {
  private Integer _value;
  private String _label;
  
  public Tag(){
      
  }

    public String getLabel() {
        return _label;
    }

    public void setValue(Integer value) {
        this._value = value;
    }

    public Integer getValue() {
        return _value;
    }

    public void setLabel(String label) {
        this._label = label;
    }
  
  
}