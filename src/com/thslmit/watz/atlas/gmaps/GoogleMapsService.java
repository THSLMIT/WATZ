package com.thslmit.watz.atlas.gmaps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.prefs.Preferences;

import javax.naming.ServiceUnavailableException;
import javax.xml.bind.JAXBException;

public class GoogleMapsService extends ElevationResponse {
	private static final Preferences pref = Preferences
			.userNodeForPackage(GoogleMapsService.class);
	private static final String GOOGLE_MAPS_API_URL_PREFERENCE = "googleMapsApiUrl";
	private static final String OK = "OK";
	private static final String OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";

	public String getName() {
		return "Google Maps";
	}

	private static String getGoogleMapsApiUrl(String api, String payload) {
		String language = Locale.getDefault().getLanguage();
		return pref.get(GOOGLE_MAPS_API_URL_PREFERENCE,
				"http://maps.googleapis.com/")
				+ "maps/api/"
				+ api
				+ "/xml?"
				+ payload + "&sensor=false&language=" + language;
	}

	private static String getElevationUrl(String payload) {
		return getGoogleMapsApiUrl("elevation", payload);
	}

	private static String getGeocodingUrl(String payload) {
		return getGoogleMapsApiUrl("geocode", payload);
	}

	private Get get(String url) {
		Get get = new Get(url);
		get.setUserAgent("Mozilla/5.0 (Windows; U; MSIE 7.0; Windows NT 5.1)");
		return get;
	}

	public String getLocationFor(double longitude, double latitude)
			throws IOException {
		String url = getGeocodingUrl("latlng=" + latitude + "," + longitude);
		Get get = get(url);
		String result = get.executeAsString();
		if (get.isSuccessful())
			try {
				GeocodeResponse geocodeResponse = unmarshalGeocode(result);
				if (geocodeResponse != null) {
					String status = geocodeResponse.getStatus();
					if (status.equals(OK))
						return extractClosestLocation(
								geocodeResponse.getResult(), longitude,
								latitude);
					if (status.equals(OVER_QUERY_LIMIT))
						throw new ServiceUnavailableException("maps.googleapis.com", url);
				}
			} catch (JAXBException e) {
				throw new IOException("Cannot unmarshall " + result + ": " + e,
						e);
			}
		return null;
	}

	private String extractClosestLocation(List<GeocodeResponse.Result> results,
			final double longitude, final double latitude) {
		GeocodeResponse.Result[] resultsArray = results
				.toArray(new GeocodeResponse.Result[results.size()]);
		sort(resultsArray, new Comparator<GeocodeResponse.Result>() {
			public int compare(GeocodeResponse.Result p1,
					GeocodeResponse.Result p2) {
				GeocodeResponse.Result.Geometry.Location l1 = p1.getGeometry()
						.getLocation();
				GeocodeResponse.Result.Geometry.Location l2 = p2.getGeometry()
						.getLocation();
				double distance1 = calculateBearing(longitude, latitude,
						l1.getLng().doubleValue(), l1.getLat().doubleValue())
						.getDistance();
				double distance2 = calculateBearing(longitude, latitude,
						l2.getLng().doubleValue(), l2.getLat().doubleValue())
						.getDistance();
				return (int) (distance1 - distance2);
			}
		});
		return resultsArray[0].getFormattedAddress();
	}

	public NavigationPosition getPositionFor(String address) throws IOException {
		List<NavigationPosition> positions = getPositionsFor(address);
		return positions != null && positions.size() > 0 ? positions.get(0)
				: null;
	}

	public List<NavigationPosition> getPositionsFor(String address)
			throws IOException {
		String url = getGeocodingUrl("address=" + encodeUri(address));
		Get get = get(url);
		String result = get.executeAsString();
		if (get.isSuccessful())
			try {
				GeocodeResponse geocodeResponse = unmarshalGeocode(result);
				if (geocodeResponse != null) {
					String status = geocodeResponse.getStatus();
					if (status.equals(OK))
						return extractAdresses(geocodeResponse.getResult());
					if (status.equals(OVER_QUERY_LIMIT))
						throw new ServiceUnavailableException(
								"maps.googleapis.com", url);
				}
			} catch (JAXBException e) {
				throw new IOException("Cannot unmarshall " + result + ": " + e,
						e);
			}
		return null;
	}

	private List<NavigationPosition> extractAdresses(
			List<GeocodeResponse.Result> responses) {
		List<NavigationPosition> result = new ArrayList<>(responses.size());
		for (GeocodeResponse.Result response : responses) {
			GeocodeResponse.Result.Geometry.Location location = response
					.getGeometry().getLocation();
			result.add(new SimpleNavigationPosition(location.getLng()
					.doubleValue(), location.getLat().doubleValue(), 0.0d,
					response.getFormattedAddress()));
		}
		return result;
	}

	public Double getElevationFor(double longitude, double latitude)
			throws IOException {
		String url = getElevationUrl("locations=" + latitude + "," + longitude); // TODO
																					// could
																					// be
																					// up
																					// to
																					// 512
																					// locations
		Get get = get(url);
		String result = get.executeAsString();
		if (get.isSuccessful())
			try {
				ElevationResponse elevationResponse = unmarshalElevation(result);
				if (elevationResponse != null) {
					String status = elevationResponse.getStatus();
					if (status.equals(OK)) {
						List<Double> elevations = extractElevations(elevationResponse
								.getResult());
						return elevations != null && elevations.size() > 0 ? elevations
								.get(0) : null;
					}
					if (status.equals(OVER_QUERY_LIMIT))
						throw new ServiceUnavailableException(
								"maps.googleapis.com", url);
				}
			} catch (JAXBException e) {
				throw new IOException("Cannot unmarshall " + result + ": " + e,
						e);
			}
		return null;
	}

	private List<Double> extractElevations(
			List<ElevationResponse.Result> responses) {
		List<Double> results = new ArrayList<>(responses.size());
		for (ElevationResponse.Result response : responses) {
			results.add(response.getElevation().doubleValue());
		}
		return results;
	}

	public boolean isDownload() {
		return false;
	}

	public String getPath() {
		throw new UnsupportedOperationException();
	}

	public void setPath(String path) {
		throw new UnsupportedOperationException();
	}

	public void downloadElevationDataFor(
			List<LongitudeAndLatitude> longitudeAndLatitudes) {
		throw new UnsupportedOperationException();
	}
}
