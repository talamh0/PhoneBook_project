// The Contact class should implement the Comparable interface so that events can be sorted by title when added to the array.
public class Event implements Comparable<Event> {
	String title = "";
	String time = "";
	String location = "";
	String type = "event";
	//Contact contact = null;
	String contacts = "";

	public Event() {
		contacts = "";
	}

	public Event(String title, String time, String location, String type, String contacts) {
		this.title = title;
		this.time = time;
		this.location = location;
		this.type = type;
		this.contacts = contacts;
	}

	public int compareTo(Event other) {
		return title.compareTo(other.title);
	}

	// Events with same title are equals
	public boolean equals(Object obj) {
		Event other = (Event) obj;
		if (title.equals(other.title))
			return true;
		return false;
	}
	
	public boolean find(Contact contact) {
		for (String name : contacts.split(","))
			if (name.equalsIgnoreCase(contact.firstName + " " + contact.lastName))
				return true;
		return false;
	}

	public String toString() {
		String str = type + " title: " + title + "\n" + "Contact name: "
				+ contacts + "\n"
				+ type + " date and time (MM/DD/YYYY HH:MM): " + time + "\n"
				+ type + " location: " + location + "\n";
		return str;
	}
}
