// The Contact class should implement the Comparable interface so that contacts can be sorted by name when added to the linked list.
public class Contact implements Comparable<Contact> {

	String firstName = "";
	String lastName = "";
	String phone = "";
	String email = "";
	String address = "";
	String birthday = "";
	String notes = "";

	public Contact() {
	}

	public Contact(String name) {
		String[] names = name.split(" ");
		if (names.length == 1) {
			this.firstName = names[0];
			this.lastName = "";
		} else if (names.length >= 2) {
			this.firstName = names[0];
			this.lastName = names[1];
		}
	}

	public Contact(String name, String phone, String email, String address,
			String birthday, String notes) {
		this(name);
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.notes = notes;
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}

	public int compareTo(Contact other) {
		String name = firstName + " " + lastName;
		String name2 = other.firstName + " " + other.lastName;
		return name.compareTo(name2);
	}

	// Contacts with same name or same phone number are equals
	public boolean equals(Object obj) {
		Contact other = (Contact) obj;
		if (firstName.equals(other.firstName)
				&& lastName.equals(other.lastName))
			return true;
		if (phone.equals(other.phone))
			return true;
		return false;
	}

	public String toString() {
		String str = "Name: " + firstName + " " + lastName + "\n"
				+ "Phone Number:" + phone + "\n" + "Email Address:" + email
				+ "\n" + "Address:" + address + "\n" + "Birthday:" + birthday
				+ "\n" + "Notes:" + notes + "\n";
		return str;
	}

	

}
