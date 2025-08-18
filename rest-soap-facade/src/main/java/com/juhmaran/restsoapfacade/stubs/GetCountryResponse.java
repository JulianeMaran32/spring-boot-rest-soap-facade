//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.3.2 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2025.08.17 às 07:19:02 PM BRT 
//


package com.juhmaran.restsoapfacade.stubs;

import jakarta.xml.bind.annotation.*;


/**
 * <p>Classe Java de anonymous complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="country" type="{http://www.example.com/soap/paises}country"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
  "country"
})
@XmlRootElement(name = "getCountryResponse")
public class GetCountryResponse {

  @XmlElement(required = true)
  protected Country country;

  /**
   * Obtém o valor da propriedade country.
   *
   * @return possible object is
   * {@link Country }
   */
  public Country getCountry() {
    return country;
  }

  /**
   * Define o valor da propriedade country.
   *
   * @param value allowed object is
   *              {@link Country }
   */
  public void setCountry(Country value) {
    this.country = value;
  }

}
