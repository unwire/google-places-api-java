package me.windwaker.places;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static me.windwaker.places.GooglePlaces.*;

/**
 * Represents a place returned by Google Places API_
 */
public class Place {
	private final GooglePlaces client;
	private final String id;
	private double lat = -1, lon = -1;
	private JSONObject json;
	private String iconUrl;
	private String name;
	private String addr;
	private String vicinity;
	private final List<String> types = new ArrayList<String>();
	private final List<Event> events = new ArrayList<Event>();
	private final List<Photo> photos = new ArrayList<Photo>();
	private final List<Review> reviews = new ArrayList<Review>();
	private final List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
	private double rating = -1;
	private Status status = Status.NONE;
	private Price price = Price.NONE;
	private String referenceId;
	private String phone, internationalPhone;
	private String googleUrl, website;
	private Hours hours;
	private int utcOffset;

	/**
	 * Creates a new place with the specified id.
	 *
	 * @param id to create location for
	 */
	public Place(GooglePlaces client, String id) {
		this.client = client;
		this.id = id;
	}

	/**
	 * Returns the id associated with this place.
	 *
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the latitude of the place.
	 *
	 * @return place latitude
	 */
	public double getLatitude() {
		return lat;
	}

	/**
	 * Sets the latitude of the place.
	 *
	 * @param lat latitude
	 * @return this
	 */
	public Place setLatitude(double lat) {
		this.lat = lat;
		return this;
	}

	/**
	 * Returns the longitude of this place.
	 *
	 * @return longitude
	 */
	public double getLongitude() {
		return lon;
	}

	/**
	 * Sets the longitude of this place.
	 *
	 * @param lon longitude
	 * @return this
	 */
	public Place setLongitude(double lon) {
		this.lon = lon;
		return this;
	}

	/**
	 * Returns the amount of seconds this place is off from the UTC timezone.
	 *
	 * @return seconds from timezone
	 */
	public int getUtcOffset() {
		return utcOffset;
	}

	/**
	 * Sets the amount of seconds this place is off from the UTC timezone.
	 *
	 * @param utcOffset in seconds
	 * @return this
	 */
	public Place setUtcOffset(int utcOffset) {
		this.utcOffset = utcOffset;
		return this;
	}

	/**
	 * Returns the {@link me.windwaker.places.Hours} for this place.
	 *
	 * @return hours of operation
	 */
	public Hours getHours() {
		return hours;
	}

	/**
	 * Sets the {@link me.windwaker.places.Hours} of this place.
	 *
	 * @param hours of operation
	 * @return this
	 */
	public Place setHours(Hours hours) {
		this.hours = hours;
		return this;
	}

	/**
	 * Returns true if this place is always opened.
	 *
	 * @return true if always opened
	 */
	public boolean isAlwaysOpened() {
		return hours.isAlwaysOpened();
	}

	/**
	 * Returns this Place's phone number.
	 *
	 * @return number
	 */
	public String getPhoneNumber() {
		return phone;
	}

	/**
	 * Sets this Place's phone number.
	 *
	 * @param phone number
	 * @return this
	 */
	public Place setPhoneNumber(String phone) {
		this.phone = phone;
		return this;
	}

	/**
	 * Returns the place's phone number with a country code.
	 *
	 * @return phone number
	 */
	public String getInternationalPhoneNumber() {
		return internationalPhone;
	}

	/**
	 * Sets the phone number with an international country code.
	 *
	 * @param internationalPhone phone number
	 * @return this
	 */
	public Place setInternationalPhoneNumber(String internationalPhone) {
		this.internationalPhone = internationalPhone;
		return this;
	}

	/**
	 * Returns the Google PLus page for this place.
	 *
	 * @return plus page
	 */
	public String getGoogleUrl() {
		return googleUrl;
	}

	/**
	 * Sets the Google Plus page for this place.
	 *
	 * @param googleUrl google plus page
	 * @return this
	 */
	public Place setGoogleUrl(String googleUrl) {
		this.googleUrl = googleUrl;
		return this;
	}

	/**
	 * Returns the website of this place.
	 *
	 * @return website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * Sets the website url associated with this place.
	 *
	 * @param website of place
	 * @return this
	 */
	public Place setWebsite(String website) {
		this.website = website;
		return this;
	}

	/**
	 * Sets the "vicinity" the place is in. This is sometimes a substitute for address.
	 *
	 * @param vicinity of place
	 * @return this
	 */
	public Place setVicinity(String vicinity) {
		this.vicinity = vicinity;
		return this;
	}

	/**
	 * Returns the "vicinity" the place is in. This is sometimes a substitute for address.
	 *
	 * @return vicinity
	 */
	public String getVicinity() {
		return vicinity;
	}

	/**
	 * Sets the url of the icon to represent this place.
	 *
	 * @param iconUrl to represent place.
	 * @return this
	 */
	public Place setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
		return this;
	}

	/**
	 * Returns the url of the icon to represent this place.
	 *
	 * @return icon to represent
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * Sets the name of this place.
	 *
	 * @param name of place
	 * @return this
	 */
	public Place setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Returns the name of this place.
	 *
	 * @return name of place
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the address of this place.
	 *
	 * @return address of this place
	 */
	public String getAddress() {
		return addr;
	}

	/**
	 * Sets the address of this place.
	 *
	 * @param addr address
	 * @return this
	 */
	public Place setAddress(String addr) {
		this.addr = addr;
		return this;
	}

	/**
	 * Adds a collection of address components to this place.
	 *
	 * @param c components to add
	 * @return this
	 */
	public Place addAddressComponents(Collection<AddressComponent> c) {
		this.addressComponents.addAll(c);
		return this;
	}

	/**
	 * Adds an address component to this place.
	 *
	 * @param c component to add
	 * @return this
	 */
	public Place addAddressComponent(AddressComponent c) {
		addressComponents.add(c);
		return this;
	}

	/**
	 * Removes the specified address components.
	 *
	 * @param c component to remove
	 * @return this
	 */
	public Place removeAddressComponent(AddressComponent c) {
		addressComponents.remove(c);
		return this;
	}

	/**
	 * Clears the address components for this place.
	 *
	 * @return this
	 */
	public Place clearAddressComponent() {
		addressComponents.clear();
		return this;
	}

	/**
	 * Returns the address components for this place.
	 *
	 * @return address components
	 */
	public List<AddressComponent> getAddressComponents() {
		return Collections.unmodifiableList(addressComponents);
	}

	/**
	 * Adds a collection of photos to this place.
	 *
	 * @param photos to add
	 * @return this
	 */
	public Place addPhotos(Collection<Photo> photos) {
		this.photos.addAll(photos);
		return this;
	}

	/**
	 * Adds a photo.
	 *
	 * @param photo to add
	 * @return this
	 */
	public Place addPhoto(Photo photo) {
		photos.add(photo);
		return this;
	}

	/**
	 * Removes the specified photo.
	 *
	 * @param photo to remove
	 * @return this
	 */
	public Place removePhoto(Photo photo) {
		photos.remove(photo);
		return this;
	}

	/**
	 * Clears all photos for this place.
	 *
	 * @return this
	 */
	public Place clearPhotos() {
		photos.clear();
		return this;
	}

	/**
	 * Returns the photo references for this place.
	 *
	 * @return photos
	 */
	public List<Photo> getPhotos() {
		return Collections.unmodifiableList(photos);
	}

	/**
	 * Adds a collection of reviews to this place.
	 *
	 * @param reviews to add
	 * @return this
	 */
	public Place addReviews(Collection<Review> reviews) {
		this.reviews.addAll(reviews);
		return this;
	}

	/**
	 * Adds a review to this place.
	 *
	 * @param review to add
	 * @return this
	 */
	public Place addReview(Review review) {
		reviews.add(review);
		return this;
	}

	/**
	 * Removes the specified review.
	 *
	 * @param review to remove
	 * @return this
	 */
	public Place removeReview(Review review) {
		reviews.remove(review);
		return this;
	}

	/**
	 * Clears this place's reviews.
	 *
	 * @return this
	 */
	public Place clearReviews() {
		reviews.clear();
		return this;
	}

	/**
	 * Returns this place's reviews in an unmodifiable list.
	 *
	 * @return reviews
	 */
	public List<Review> getReviews() {
		return Collections.unmodifiableList(reviews);
	}

	/**
	 * Adds a collection of events to this place.
	 *
	 * @param events to add
	 * @return this
	 */
	public Place addEvents(Collection<Event> events) {
		this.events.addAll(events);
		return this;
	}

	/**
	 * Adds an event to this place.
	 *
	 * @param event to add
	 * @return this
	 */
	public Place addEvent(Event event) {
		events.add(event);
		return this;
	}

	/**
	 * Returns the events of this place in an unmodifiable list.
	 *
	 * @return unmodifiable list of events
	 */
	public List<Event> getEvents() {
		return Collections.unmodifiableList(events);
	}

	/**
	 * Removes the specified event from the place.
	 *
	 * @param event to remove
	 * @return this
	 */
	public Place removeEvent(Event event) {
		events.remove(event);
		return this;
	}

	/**
	 * Clears all events in this place.
	 *
	 * @return this
	 */
	public Place clearEvents() {
		events.clear();
		return this;
	}

	/**
	 * Adds a collection of string "types".
	 *
	 * @param types to add
	 * @return this
	 */
	public Place addTypes(Collection<String> types) {
		this.types.addAll(types);
		return this;
	}

	/**
	 * Adds a new type to this place.
	 *
	 * @param type to add
	 * @return this
	 */
	public Place addType(String type) {
		types.add(type);
		return this;
	}

	/**
	 * Returns all of this place's types in an unmodifiable list.
	 *
	 * @return types
	 */
	public List<String> getTypes() {
		return Collections.unmodifiableList(types);
	}

	/**
	 * Removes a type from this place.
	 *
	 * @param type to remove
	 * @return this
	 */
	public Place removeType(String type) {
		types.remove(type);
		return this;
	}

	/**
	 * Clears all types from this place.
	 *
	 * @return this
	 */
	public Place clearTypes() {
		types.clear();
		return this;
	}

	/**
	 * Returns the rating of this place.
	 *
	 * @return rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * Sets the rating of this place.
	 *
	 * @param rating of place
	 * @return this
	 */
	public Place setRating(double rating) {
		this.rating = rating;
		return this;
	}

	/**
	 * Returns the {@link me.windwaker.places.Status} of this place.
	 *
	 * @return status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the {@link me.windwaker.places.Status} of this place.
	 *
	 * @param status to set
	 * @return this
	 */
	public Place setStatus(Status status) {
		this.status = status;
		return this;
	}

	/**
	 * Returns the {@link me.windwaker.places.Price} of this place.
	 *
	 * @return price
	 */
	public Price getPrice() {
		return price;
	}

	/**
	 * Sets the {@link me.windwaker.places.Price} of this place.
	 *
	 * @param price to set
	 * @return this
	 */
	public Place setPrice(Price price) {
		this.price = price;
		return this;
	}

	/**
	 * Returns the reference id, used for getting more details about this place.
	 *
	 * @return reference id
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * Returns the reference id to find more details about this place.
	 *
	 * @param referenceId to get details from
	 * @return this
	 */
	public Place setReferenceId(String referenceId) {
		this.referenceId = referenceId;
		return this;
	}

	/**
	 * Sets the JSON representation of this Place.
	 *
	 * @param json representation
	 * @return this
	 */
	public Place setJson(JSONObject json) {
		this.json = json;
		return this;
	}

	/**
	 * Returns the JSON representation of this place. This does not build a JSON object, it only returns the JSON
	 * that was given in the initial response from the server.
	 *
	 * @return the json representation
	 */
	public JSONObject getJson() {
		return json;
	}

	/**
	 * Returns an updated Place object with more details than the Place object returned in an initial query.
	 *
	 * @param params extra params to include in the request url
	 * @return a new place with more details
	 * @throws IOException
	 */
	public Place getDetails(GooglePlaces.Param... params) throws IOException {
		String uri = String.format("%s%s/json?key=%s&reference=%s&sensor=%b", API_URL,
				METHOD_DETAILS, client.getApiKey(), referenceId, client.isSensorEnabled());
		uri = GooglePlaces.addExtraParams(uri, params);
		return parseDetails(client, HttpUtil.getResponse(client.getHttpClient(), uri));
	}

	/**
	 * Parses a detailed Place object.
	 *
	 * @param client api client
	 * @param rawJson json to parse
	 * @return a detailed place
	 */
	public static Place parseDetails(GooglePlaces client, String rawJson) {
		JSONObject json = new JSONObject(rawJson);

		JSONObject result = json.getJSONObject(OBJECT_RESULT);

		// easy stuff
		String id = result.getString(STRING_ID);
		String name = result.getString(STRING_NAME);
		String address = result.optString(STRING_ADDRESS, null);
		String phone = result.optString(STRING_PHONE_NUMBER, null);
		String iconUrl = result.optString(STRING_ICON, null);
		String internationalPhone = result.optString(STRING_INTERNATIONAL_PHONE_NUMBER, null);
		double rating = result.optDouble(DOUBLE_RATING, -1);
		String reference = result.optString(STRING_REFERENCE, null);
		String url = result.optString(STRING_URL, null);
		String vicinity = result.optString(STRING_VICINITY, null);
		String website = result.optString(STRING_WEBSITE, null);
		int utcOffset = result.optInt(INTEGER_UTC_OFFSET, -1);

		// grab the price rank
		Price price = Price.NONE;
		if (result.has(INTEGER_PRICE_LEVEL)) {
			price = Price.values()[result.getInt(INTEGER_PRICE_LEVEL)];
		}

		// location
		JSONObject location = result.getJSONObject(OBJECT_GEOMETRY).getJSONObject(OBJECT_LOCATION);
		double lat = location.getDouble(DOUBLE_LATITUDE), lng = location.getDouble(DOUBLE_LONGITUDE);

		// hours of operation
		JSONObject hours = result.optJSONObject(OBJECT_HOURS);
		Status status = Status.NONE;
		Hours schedule = new Hours();
		if (hours != null) {
			boolean statusDefined = hours.has(BOOLEAN_OPENED);
			status = statusDefined && hours.getBoolean(BOOLEAN_OPENED) ? Status.OPENED : Status.CLOSED;

			// periods of operation
			JSONArray jsonPeriods = hours.optJSONArray(ARRAY_PERIODS);
			if (jsonPeriods != null) {
				for (int i = 0; i < jsonPeriods.length(); i++) {
					JSONObject jsonPeriod = jsonPeriods.getJSONObject(i);

					// opening information (from)
					JSONObject opens = jsonPeriod.getJSONObject(OBJECT_OPEN);
					Day openingDay = Day.values()[opens.getInt(INTEGER_DAY)];
					String openingTime = opens.getString(STRING_TIME);

					// if this place is always open, break.
					boolean alwaysOpened = openingDay == Day.SUNDAY && openingTime.equals("0000") && !jsonPeriod.has(OBJECT_CLOSE);
					if (alwaysOpened) {
						schedule.setAlwaysOpened(true);
						break;
					}

					// closing information (to)
					JSONObject closes = jsonPeriod.getJSONObject(OBJECT_CLOSE);
					Day closingDay = Day.values()[closes.getInt(INTEGER_DAY)]; // to
					String closingTime = closes.getString(STRING_TIME);

					// add the period to the hours
					schedule.addPeriod(new Hours.Period().setOpeningDay(openingDay).setOpeningTime(openingTime)
							.setClosingDay(closingDay).setClosingTime(closingTime));
				}
			}
		}

		// photos
		JSONArray jsonPhotos = result.optJSONArray(ARRAY_PHOTOS);
		List<Photo> photos = new ArrayList<Photo>();
		if (jsonPhotos != null) {
			for (int i = 0; i < jsonPhotos.length(); i++) {
				JSONObject jsonPhoto = jsonPhotos.getJSONObject(i);
				String photoReference = jsonPhoto.getString(STRING_PHOTO_REFERENCE);
				int width = jsonPhoto.getInt(INTEGER_WIDTH), height = jsonPhoto.getInt(INTEGER_HEIGHT);
				photos.add(new Photo(photoReference, width, height));
			}
		}


		// address components
		JSONArray addrComponents = result.optJSONArray(ARRAY_ADDRESS_COMPONENTS);
		List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
		if (addrComponents != null) {
			for (int i = 0; i < addrComponents.length(); i++) {
				JSONObject ac = addrComponents.getJSONObject(i);
				AddressComponent addr = new AddressComponent();

				String longName = ac.optString(STRING_LONG_NAME, null);
				String shortName = ac.optString(STRING_SHORT_NAME, null);

				addr.setLongName(longName);
				addr.setShortName(shortName);

				// address components have types too
				JSONArray types = ac.optJSONArray(ARRAY_TYPES);
				if (types != null) {
					for (int a = 0; a < types.length(); a++) {
						addr.addType(types.getString(a));
					}
				}

				addressComponents.add(addr);
			}
		}

		// events
		JSONArray events = result.optJSONArray(ARRAY_EVENTS);
		List<Event> eventList = new ArrayList<Event>();
		if (events != null) {
			for (int i = 0; i < events.length(); i++) {
				JSONObject event = events.getJSONObject(i);
				String eventId = event.optString(STRING_EVENT_ID, null);
				long startTime = event.optLong(LONG_START_TIME, -1);
				String summary = event.optString(STRING_SUMMARY, null);
				String eventUrl = event.optString(STRING_URL, null);

				eventList.add(new Event(eventId).setSummary(summary).setUrl(eventUrl).setStartTime(startTime));
			}
		}

		// types
		JSONArray jsonTypes = result.optJSONArray(ARRAY_TYPES);
		List<String> types = new ArrayList<String>();
		if (jsonTypes != null) {
			for (int i = 0; i < jsonTypes.length(); i++) {
				types.add(jsonTypes.getString(i));
			}
		}

		// reviews
		JSONArray jsonReviews = result.optJSONArray(ARRAY_REVIEWS);
		List<Review> reviews = new ArrayList<Review>();
		if (jsonReviews != null) {
			for (int i = 0; i < jsonReviews.length(); i++) {
				JSONObject jsonReview = jsonReviews.getJSONObject(i);

				String author = jsonReview.optString(STRING_AUTHOR_NAME, null);
				String authorUrl = jsonReview.optString(STRING_AUTHOR_URL, null);
				String lang = jsonReview.optString(STRING_LANGUAGE, null);
				int reviewRating = jsonReview.optInt(INTEGER_RATING, -1);
				String text = jsonReview.optString(STRING_TEXT, null);
				long time = jsonReview.optLong(LONG_TIME, -1);

				// aspects of the review
				JSONArray jsonAspects = jsonReview.optJSONArray(ARRAY_ASPECTS);
				List<Review.Aspect> aspects = new ArrayList<Review.Aspect>();
				if (jsonAspects != null) {
					for (int a = 0; a < jsonAspects.length(); a++) {
						JSONObject jsonAspect = jsonAspects.getJSONObject(a);
						String aspectType = jsonAspect.getString(STRING_TYPE);
						int aspectRating = jsonAspect.getInt(INTEGER_RATING);
						aspects.add(new Review.Aspect(aspectRating, aspectType));
					}
				}

				reviews.add(new Review().addAspects(aspects).setAuthor(author).setAuthorUrl(authorUrl).setLanguage(lang)
						.setRating(reviewRating).setText(text).setTime(time));
			}
		}

		return new Place(client, id).setName(name).setAddress(address).setIconUrl(iconUrl).setPrice(price)
				.setLatitude(lat).setLongitude(lng).addEvents(eventList).addTypes(types).setRating(rating)
				.setReferenceId(reference).setStatus(status).setVicinity(vicinity).setPhoneNumber(phone)
				.setInternationalPhoneNumber(internationalPhone).setGoogleUrl(url).setWebsite(website)
				.addPhotos(photos).addAddressComponents(addressComponents).setHours(schedule).addReviews(reviews)
				.setUtcOffset(utcOffset).setJson(result);
	}

	@Override
	public String toString() {
		return String.format("Place{id=%s,loc=%f,%f,name=%s,addr=%s,ref=%s", id, lat, lon, name, addr, referenceId);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Place && ((Place) obj).id.equals(id);
	}
}