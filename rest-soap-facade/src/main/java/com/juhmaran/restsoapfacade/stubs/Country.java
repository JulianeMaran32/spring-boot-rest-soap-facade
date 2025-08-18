//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.3.2 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2025.08.17 às 07:19:02 PM BRT 
//


package com.juhmaran.restsoapfacade.stubs;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Classe Java de country complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="country"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="capital" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "country", propOrder = {
  "name",
  "capital",
  "currency"
})
public class Country {

  @XmlElement(required = true)
  protected String name;
  @XmlElement(required = true)
  protected String capital;
  @XmlElement(required = true)
  protected String currency;

  /**
   * Obtém o valor da propriedade name.
   *
   * @return possible object is
   * {@link String }
   */
  public String getName() {
    return name;
  }

  /**
   * Define o valor da propriedade name.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setName(String value) {
    this.name = value;
  }

  /**
   * Obtém o valor da propriedade capital.
   *
   * @return possible object is
   * {@link String }
   */
  public String getCapital() {
    return capital;
  }

  /**
   * Define o valor da propriedade capital.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setCapital(String value) {
    this.capital = value;
  }

  /**
   * Obtém o valor da propriedade currency.
   *
   * @return possible object is
   * {@link String }
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Define o valor da propriedade currency.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setCurrency(String value) {
    this.currency = value;
  }

}
