package com.thslmit.watz.atlas.gmaps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="result" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="location">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="lat" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                             &lt;element name="lng" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="elevation" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "status", "result" })
@XmlRootElement(name = "ElevationResponse")
public class ElevationResponse {

	@XmlElement(required = true)
	protected String status;
	@XmlElement(required = true)
	protected List<ElevationResponse.Result> result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String value) {
		this.status = value;
	}

	public List<ElevationResponse.Result> getResult() {
		if (result == null) {
			result = new ArrayList<ElevationResponse.Result>();
		}
		return this.result;
	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="location">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="lat" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
	 *                   &lt;element name="lng" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="elevation" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "location", "elevation" })
	public static class Result {

		@XmlElement(required = true)
		protected ElevationResponse.Result.Location location;
		@XmlElement(required = true)
		protected BigDecimal elevation;

		public ElevationResponse.Result.Location getLocation() {
			return location;
		}

		public void setLocation(ElevationResponse.Result.Location value) {
			this.location = value;
		}

		public BigDecimal getElevation() {
			return elevation;
		}

		public void setElevation(BigDecimal value) {
			this.elevation = value;
		}

		/**
		 * <p>
		 * Java class for anonymous complex type.
		 * 
		 * <p>
		 * The following schema fragment specifies the expected content
		 * contained within this class.
		 * 
		 * <pre>
		 * <complexType>
		 * 		<complexContent>
		 * 			<restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       		<sequence>
		 *         			<element name="lat" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
		 *         			<element name="lon" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
		 *       		</sequence>
		 *     		</restriction>
		 * 		</complexContent>
		 * </complexType>
		 * </pre>
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "lat", "lon" })
		public static class Location {

			@XmlElement(required = true)
			protected BigDecimal lat;
			@XmlElement(required = true)
			protected BigDecimal lng;

			public BigDecimal getLat() {
				return lat;
			}

			public void setLat(BigDecimal value) {
				this.lat = value;
			}

			public BigDecimal getLng() {
				return lng;
			}

			public void setLng(BigDecimal value) {
				this.lng = value;
			}

		}

	}

}