//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.03 at 01:56:44 PM CEST 
//


package ch.sbi.scraper.datatypes.marshalling;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subtitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="number-of-replies">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="number-of-hits">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{}flags"/>
 *         &lt;element ref="{}in-board"/>
 *         &lt;element name="firstpost" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element ref="{}post"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{}posts"/>
 *       &lt;/all>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "thread")
public class Thread {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String subtitle;
    @XmlElement(name = "number-of-replies", required = true)
    protected Thread.NumberOfReplies numberOfReplies;
    @XmlElement(name = "number-of-hits", required = true)
    protected Thread.NumberOfHits numberOfHits;
    @XmlElement(required = true)
    protected Flags flags;
    @XmlElement(name = "in-board", required = true)
    protected InBoard inBoard;
    protected Thread.Firstpost firstpost;
    @XmlElement(required = true)
    protected Posts posts;
    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger id;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the subtitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Sets the value of the subtitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubtitle(String value) {
        this.subtitle = value;
    }

    /**
     * Gets the value of the numberOfReplies property.
     * 
     * @return
     *     possible object is
     *     {@link Thread.NumberOfReplies }
     *     
     */
    public Thread.NumberOfReplies getNumberOfReplies() {
        return numberOfReplies;
    }

    /**
     * Sets the value of the numberOfReplies property.
     * 
     * @param value
     *     allowed object is
     *     {@link Thread.NumberOfReplies }
     *     
     */
    public void setNumberOfReplies(Thread.NumberOfReplies value) {
        this.numberOfReplies = value;
    }

    /**
     * Gets the value of the numberOfHits property.
     * 
     * @return
     *     possible object is
     *     {@link Thread.NumberOfHits }
     *     
     */
    public Thread.NumberOfHits getNumberOfHits() {
        return numberOfHits;
    }

    /**
     * Sets the value of the numberOfHits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Thread.NumberOfHits }
     *     
     */
    public void setNumberOfHits(Thread.NumberOfHits value) {
        this.numberOfHits = value;
    }

    /**
     * Gets the value of the flags property.
     * 
     * @return
     *     possible object is
     *     {@link Flags }
     *     
     */
    public Flags getFlags() {
        return flags;
    }

    /**
     * Sets the value of the flags property.
     * 
     * @param value
     *     allowed object is
     *     {@link Flags }
     *     
     */
    public void setFlags(Flags value) {
        this.flags = value;
    }

    /**
     * Gets the value of the inBoard property.
     * 
     * @return
     *     possible object is
     *     {@link InBoard }
     *     
     */
    public InBoard getInBoard() {
        return inBoard;
    }

    /**
     * Sets the value of the inBoard property.
     * 
     * @param value
     *     allowed object is
     *     {@link InBoard }
     *     
     */
    public void setInBoard(InBoard value) {
        this.inBoard = value;
    }

    /**
     * Gets the value of the firstpost property.
     * 
     * @return
     *     possible object is
     *     {@link Thread.Firstpost }
     *     
     */
    public Thread.Firstpost getFirstpost() {
        return firstpost;
    }

    /**
     * Sets the value of the firstpost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Thread.Firstpost }
     *     
     */
    public void setFirstpost(Thread.Firstpost value) {
        this.firstpost = value;
    }

    /**
     * Gets the value of the posts property.
     * 
     * @return
     *     possible object is
     *     {@link Posts }
     *     
     */
    public Posts getPosts() {
        return posts;
    }

    /**
     * Sets the value of the posts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Posts }
     *     
     */
    public void setPosts(Posts value) {
        this.posts = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element ref="{}post"/>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Firstpost {

        @XmlElement(required = true)
        protected Post post;

        /**
         * Gets the value of the post property.
         * 
         * @return
         *     possible object is
         *     {@link Post }
         *     
         */
        public Post getPost() {
            return post;
        }

        /**
         * Sets the value of the post property.
         * 
         * @param value
         *     allowed object is
         *     {@link Post }
         *     
         */
        public void setPost(Post value) {
            this.post = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class NumberOfHits {

        @XmlAttribute(name = "value", required = true)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger value;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class NumberOfReplies {

        @XmlAttribute(name = "value", required = true)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger value;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

    }

}